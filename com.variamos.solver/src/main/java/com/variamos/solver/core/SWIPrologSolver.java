package com.variamos.solver.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jpl7.Compound;
import org.jpl7.PrologException;
import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Util;
import org.jpl7.Variable;

import com.variamos.common.core.exceptions.TechnicalException;
import com.variamos.common.core.utilities.FileUtils;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.core.HlclUtil;
import com.variamos.hlcl.model.Labeling;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.LiteralBooleanExpression;
import com.variamos.solver.core.compiler.Hlcl2SWIProlog;
import com.variamos.solver.core.compiler.PrologTransformParameters;
import com.variamos.solver.model.ConfigurationModeEnum;
import com.variamos.solver.model.ConfigurationOptionsDTO;
import com.variamos.solver.model.SolverSolution;

public class SWIPrologSolver implements IntSolver {

	private boolean loaded;
	private static Object monitor = new Object();

	private HlclProgram hlclProgram;
	private String programPath = null;
	private Query qr;
	boolean sucessfullLoad;
	private Map<String, Variable> vars;
	private int outLabels = 0;
	private List<Map<String, Variable>> labelVars;

	private long lastExecutionTime;

	public final static String PROGRAM_INVOCATION = "productline(L)";
	public final static String PARTIAL_INVOCATION_NAME = "productline";

	public SWIPrologSolver() {

	}

	public SWIPrologSolver(HlclProgram hlclProgram) {
		super();
		this.hlclProgram = hlclProgram;
	}

	@Override
	public int getSolutionsCount() {
		// FIXME: Although this way can become very lengthy, it's the only way
		// to get the solutions count right now
		return this.getAllSolutions().size();
	}

	@Override
	public boolean isSatisfiable(String programPath) {

		String query = PROGRAM_INVOCATION;
		if (loaded) {
			Query qr = new Query(query);
			return qr.hasSolution();

		}
		return false;
	}

	@Override
	public void solve(SolverSolution config, ConfigurationOptionsDTO options) {

		// Restarts the solver
		// synchronized (monitor) {
		if (options.isStartFromZero()) {
			endSolving();
			// jcmunoz: Define variables for each labeling
			if (options.getLabelings() == null) {
				vars = new TreeMap<>();
				labelVars = null;
			} else {
				labelVars = new ArrayList<Map<String, Variable>>();
				outLabels = 0;
				for (Labeling lab : options.getLabelings()) {
					if (lab.isOutputSet())
						outLabels++;
					TreeMap<String, Variable> tree = new TreeMap<String, Variable>();
					for (Identifier id : lab.getVariables()) {
						tree.put(id.getId(), new Variable(id.getId()));
					}
					labelVars.add(tree);
				}
			}
			doQuery(config, options);
		}
		// Creates the query
		consultProgram(config, options);
		System.out.println("");
		// }
	}
	
	public void solve(){				
		endSolving();

		//doQuery
		if ((hlclProgram == null || hlclProgram.isEmpty())) {
			throw new TechnicalException("HlclProgram was not initialized");

		}
		else {
			PrologTransformParameters params =null;
			programPath = createPrologFile(hlclProgram, params);
			long initTime = System.nanoTime();
			loadSWIProgram(programPath);
			lastExecutionTime = System.nanoTime() - initTime;
			
			String consulta= "productline(L).";
			 qr = new Query(consulta);
			 lastExecutionTime += System.nanoTime() - initTime;
			//System.out.println(q2.hasSolution());
//			Map<String, Term>[] table = q2.nSolutions(1);
//			salida= (table.length >= 1);
			//salida= q2.hasSolution();
		}

	}

	private void doQuery(SolverSolution config, ConfigurationOptionsDTO options) {

		if ((hlclProgram == null || hlclProgram.isEmpty())
				&& options.getProgramPath() == null) {
			throw new TechnicalException("HlclProgram was not initialized");
		} else if (hlclProgram == null) {
			// A predefined prolog file is charged
			programPath = options.getProgramPath();
		} else {

			// We create a copy of the HLCLprogram in order to don't modify the
			// real hlclprogram
			HlclProgram modifiedCopy = new HlclProgram();
			modifiedCopy.addAll(hlclProgram.subList(0, hlclProgram.size()));
			if (labelVars != null) {
				ArrayList<PrologTransformParameters> paramList = addParametersListToProgram(
						modifiedCopy, options);
				programPath = createPrologFile(modifiedCopy, paramList);
			} else {
				PrologTransformParameters params = addParametersToProgram(
						modifiedCopy, options);
				programPath = createPrologFile(modifiedCopy, params);
				Set<Identifier> identifiers = HlclUtil
						.getUsedIdentifiers(modifiedCopy);
				// Variables map is mandatory for dynamic configurations
				for (Identifier id : identifiers) {
					vars.put(id.getId(), new Variable(id.getId()));
				}
			}
		}
		long initTime = System.nanoTime();
		loadSWIProgram(programPath);
		lastExecutionTime = System.nanoTime() - initTime;
	}

	private void consultProgram(SolverSolution config,
			ConfigurationOptionsDTO options) {

		if (options.getProgramName() == null) {
			List<Compound> parts = new ArrayList<>();
			// Default labeling (L) uses the list of used variables
			if (labelVars == null) {
				Term[] varTermsArray = new Term[vars.size()];
				int count = 0;
				for (String id : vars.keySet()) {
					varTermsArray[count] = vars.get(id);
					count++;
				}
				// Generate the list of variables Ejm L={A,B,C}
				Term L = Util.termArrayToList(varTermsArray);

				// Generate the assigns for not ignored variables
				for (String id : config.getNotIgnored()) {
					// Create the atom
					if (config.stateOf(id) == (int) config.stateOf(id)) {
						org.jpl7.Integer i = new org.jpl7.Integer(
								(int) config.stateOf(id));
						Compound assign = new Compound("=", new Term[] {
								vars.get(id), i });
						parts.add(assign);
					} else {
						org.jpl7.Float i = new org.jpl7.Float(
								config.stateOf(id));
						// Create the compound for the assign.
						Compound assign = new Compound("=", new Term[] {
								vars.get(id), i });
						parts.add(assign);
					}

				}
				parts.add(new Compound(PARTIAL_INVOCATION_NAME,
						new Term[] { L }));

			}
			// Single/Multiple labeling ignores used variables and considers the
			// list
			else {
				Term[] terms = new Term[outLabels];
				int ii = 0;
				for (Labeling l : options.getLabelings()) {
					if (!l.isOutputSet())
						continue;
					Map<String, Variable> varmap = labelVars.get(ii);
					Term[] varTermsArray = new Term[varmap.size()];
					int count = 0;
					for (String id : varmap.keySet()) {
						varTermsArray[count] = varmap.get(id);
						count++;
					} // Generate the list of variables Ejm L={A,B,C}
					Term L = Util.termArrayToList(varTermsArray);

					// Generate the assigns for the not ignored variables
					for (String id : config.getNotIgnored()) { // Create the
																// atom
						org.jpl7.Float i = new org.jpl7.Float(
								config.stateOf(id));
						// Create the compound for the assign.
						Compound assign = new Compound("=", new Term[] {
								varmap.get(id), i });
						parts.add(assign);
					}
					terms[ii++] = L;
				}
				parts.add(new Compound(PARTIAL_INVOCATION_NAME, terms));
			}

			// Add all additional configuration options
			Compound query = addSubQueries(parts);
			// System.out.println(query.toString());
			long initTime = System.nanoTime();
			qr = new Query(query);
			lastExecutionTime += System.nanoTime() - initTime;
		} else { // Only user for a test class
			long initTime = System.nanoTime();
			qr = new Query(options.programName + "(L)");
			lastExecutionTime += System.nanoTime() - initTime;
		}

	}

	private static Compound addSubQueries(List<Compound> parts) {

		if (parts.size() == 0)
			return null;

		if (parts.size() == 1)
			return parts.get(0);

		int n = parts.size();

		Compound partial = new Compound(",", new Term[] { parts.get(n - 2),
				parts.get(n - 1) });
		for (int i = n - 3; i >= 0; i--)
			partial = new Compound(",", new Term[] { parts.get(i), partial });

		return partial;
	}

	@Override
	public boolean hasNextSolution() {

		if (qr == null) {
			throw new TechnicalException("Solve method was not invoked");
		}
		synchronized (monitor) {
			boolean result = qr.hasNext();
			return result;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.variamos.solver.Solver#getSolution()
	 */
	@Override
	public SolverSolution getSolution() {
		try {
			if (qr != null) {
				// synchronized (monitor) {
				if (hasNextSolution()) {
					long initTime = System.nanoTime();
					// More documentation about this method is available at
					// http://jpl7.org/doc/index.html
					Map<String, Term> configurationMap = qr.nextSolution();
					lastExecutionTime = System.nanoTime() - initTime;

					if (configurationMap != null) {
						SolverSolution configuration = makeConfiguration(configurationMap);
						return configuration;
					}
				}
				// }

			}
			return null;
		} catch (PrologException e) {
			throw new TechnicalException(e.getMessage());
		}

	}

	private SolverSolution makeConfiguration(
			Map<String, Term> configurationResultMap) {

		// FIXME: puede ser mejorado para quitar esta L quemada
		Term invocationTerm = configurationResultMap.get("L");
		SolverSolution configuration = new SolverSolution();
		// A predefined list of variables was sent
		if (invocationTerm != null) {
			List<Integer> configurationValues = new ArrayList<Integer>();
			// Obtiene del resultado de L los valores asignados a cada variable
			// descomponiendo el resultado
			getSolutionValues(invocationTerm.args(), configurationValues);

			// Get the ordered list of identifiers from the HLCL program to put
			// them into a new configuration.
			// We use the same order when we make a new prolog program
			Set<Identifier> idsSet = HlclUtil.getUsedIdentifiers(hlclProgram);
			int i = 0;
			if (idsSet.size() == idsSet.size()) {
				for (Identifier identifier : idsSet) {
					int value = configurationValues.get(i);
					if (value == 0) {
						configuration.ban(identifier.getId());
					}
					if (value == 1) {
						configuration.enforce(identifier.getId());
					} else {
						configuration.set(identifier.getId(), value);
					}
					i++;
				}
				return configuration;
			} else {
				throw new TechnicalException(
						"The values and the number of variables must be equals into the expression list ");
			}
		} else {
			Set<String> variables = configurationResultMap.keySet();

			Term term;
			String variable;
			Iterator<String> iterator = variables.iterator();
			while (iterator.hasNext()) {
				variable = iterator.next();
				term = configurationResultMap.get(variable);

				// if (term.isNumber()) {
				Float termValue = term.floatValue();
				if (termValue == 0) {
					configuration.ban(variable);
				} else if (termValue == 1) {
					configuration.enforce(variable);
				} else {
					configuration.set(variable, termValue);
				}

				// } else {
				// throw new TechnicalException(
				// "Swi prolog API error, please check ");
				// }
			}
		}
		return configuration;
	}

	private boolean loadSWIProgram(String temporalPath) {
		// To solve out of global stack
		org.jpl7.fli.Prolog.set_default_init_args(new String[] { "pl",
				"-nosignals", "-L128m", "-G128m" });
		Query q1 = new Query("consult('" + temporalPath + "')");
		return q1.hasSolution();
	}

	@Override
	public void nextSolution() {
		if (qr != null) {
			synchronized (monitor) {
				if (hasNextSolution())
					qr.nextSolution();
				else
					endSolving();
			}

		}

	}

	private void endSolving() {
		if (qr != null && qr.isOpen()) {
			try {
				qr.close();
				// To make safer the new call, reduce FATAL ERROR
				Thread.sleep(10);
			} catch (Exception e) {

			}

		}
		qr = null;
	}

	@Override
	public Map<String, List<Integer>> reduceDomain(SolverSolution config,
			ConfigurationOptionsDTO params) {
		// TODO Auto-generated method stub
		// FIXME
		return null;
	}

	private static List<Integer> getSolutionValues(Term[] terms,
			List<Integer> values) {

		if (terms[1].isAtom()) {
			Integer valueInteger = terms[0].intValue();
			values.add(valueInteger);
			return values;
		} else {
			Integer valueInteger = terms[0].intValue();
			values.add(valueInteger);
			getSolutionValues(((Compound) terms[1]).args(), values);

		}

		return values;
	}

	private PrologTransformParameters addParametersToProgram(HlclProgram prog,
			ConfigurationOptionsDTO options) {

		PrologTransformParameters params = getParamsFor(options);

		if (options != null) {
			// Add new literal expressions. All identifiers related with this
			// expression will have a binary domain by default
			for (String str : options.getAdditionalConstraints()) {
				prog.add(new LiteralBooleanExpression(str));
			}

			if (!options.getAdditionalConstraintExpressions().isEmpty()) {
				prog.addAll(options.getAdditionalConstraintExpressions());
			}
		}
		return params;
	}

	// jcmunoz: copied to support a list of parameters
	private ArrayList<PrologTransformParameters> addParametersListToProgram(
			HlclProgram prog, ConfigurationOptionsDTO options) {

		ArrayList<PrologTransformParameters> params = getParamListsFor(options);

		if (options != null) {
			// Add new literal expressions. All identifiers related with this
			// expression will have a binary domain by default
			for (String str : options.getAdditionalConstraints()) {
				prog.add(new LiteralBooleanExpression(str));
			}

			if (!options.getAdditionalConstraintExpressions().isEmpty()) {
				prog.addAll(options.getAdditionalConstraintExpressions());
			}
		}
		return params;
	}

	private PrologTransformParameters getParamsFor(
			ConfigurationOptionsDTO options) {
		PrologTransformParameters params = new PrologTransformParameters();

		params.setFdLabeling(options.getMode() == ConfigurationModeEnum.FULL);
		params.setFf(options.isFf());
		params.setOrder(options.isOrder());
		params.setLabelingOrder(options.getLabelingOrder());
		params.setOrderExpressions(options.getOrderExpressions());

		return params;
	}

	// jcmunoz: copied to support a list of parameters
	private ArrayList<PrologTransformParameters> getParamListsFor(
			ConfigurationOptionsDTO options) {
		ArrayList<PrologTransformParameters> paramList = new ArrayList<PrologTransformParameters>();
		for (Labeling lab : options.getLabelings()) {
			// FIXME: review if all the initial parameter should be from the
			// options or the labeling
			PrologTransformParameters params = new PrologTransformParameters();

			params.setFdLabeling(options.getMode() == ConfigurationModeEnum.FULL);
			params.setFf(options.isFf());
			params.setOnceLabeling(lab.isOnce());
			params.setIncludeLabel(lab.isIncludeLabel());
			params.setOutputSet(lab.isOutputSet());
			params.setOrder(lab.isOrder());
			params.setLabelingOrder(lab.getLabelingOrderList());
			params.setOrderExpressions(lab.getOrderExpressionList());
			params.setLabelId(lab.getLabelId());
			params.setIdentifiers(lab.getVariables());

			paramList.add(params);
		}

		return paramList;
	}


	private String createPrologFile(HlclProgram hlclProgram, PrologTransformParameters params) {
		String prologProgram ="";
		if (params==null){
			
			Hlcl2SWIProlog swiPrologTransformer = new Hlcl2SWIProlog();
			prologProgram = swiPrologTransformer.transform(hlclProgram);
			
		}else{
		Hlcl2SWIProlog swiPrologTransformer = new Hlcl2SWIProlog();
		prologProgram = swiPrologTransformer.transform(hlclProgram, params);}
		String path;
		try {
			// Create a temporary file
			File file = File.createTempFile("tmp", ".pl");
			path = FileUtils.writeFile(file, prologProgram);
			// Slash are replaced to avoid load problems with SWI prolog
			path = path.replace("\\", "/");
			file.deleteOnExit();
			return path;
		} catch (IOException e) {
			throw new TechnicalException(e);
		}
	}

	private String createPrologFile(HlclProgram hlclProgram,
			ArrayList<PrologTransformParameters> paramList) {
		Hlcl2SWIProlog swiPrologTransformer = new Hlcl2SWIProlog();
		String prologProgram = swiPrologTransformer.transform(hlclProgram,
				paramList);
		String path;
		try {
			// Create a temporary file
			File file = File.createTempFile("tmp", ".pl");
			path = FileUtils.writeFile(file, prologProgram);
			// Slash are replaced to avoid load problems with SWI prolog
			path = path.replace("\\", "/");
			file.deleteOnExit();
			return path;
		} catch (IOException e) {
			throw new TechnicalException(e);
		}
	}

	@Override
	public void setHLCLProgram(HlclProgram hlclProgram) {
		this.hlclProgram = hlclProgram;

	}

	@Override
	public boolean hasSolution() {
		if (qr != null) {
			synchronized (monitor) {
				return qr.hasNext();
			}
		} else {
			throw new TechnicalException("Solve method was not invoked");
		}

	}

	public HlclProgram getHlclProgram() {
		return hlclProgram;
	}

	@Override
	public long getLastExecutionTime() {
		return lastExecutionTime;
	}

	@Override
	public List<SolverSolution> getAllSolutions() {
		Map<String, Term>[] configurationResultMap;
		List<SolverSolution> configurations = new ArrayList<SolverSolution>();
		if (qr != null) {
			synchronized (monitor) {
				configurationResultMap = qr.allSolutions();
				for (Map<String, Term> configuration : configurationResultMap) {
					if (configuration != null) {
						configurations.add(this
								.makeConfiguration(configuration));
					}
				}
			}
		}
		return configurations;

	}

	@Override
	public void clearQueryMonitor() {
		/*
		 * if (qr != null && qr.isOpen()) {
		 * 
		 * try { qr.close(); } catch (Exception e) {
		 * System.out.println(e.toString()); throw new TechnicalException(
		 * "Problems cleaning the query monitor details:" + e.toString()); }
		 * 
		 * }
		 */
		qr = null;
		monitor = new Object();

	}

}
