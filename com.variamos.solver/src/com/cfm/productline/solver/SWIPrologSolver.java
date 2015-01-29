package com.cfm.productline.solver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Util;
import jpl.Variable;

import com.cfm.common.AbstractModel;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.LiteralBooleanExpression;
import com.cfm.productline.prologEditors.Hlcl2SWIProlog;
import com.cfm.productline.prologEditors.PrologTransformParameters;
import com.variamos.core.exceptions.TechnicalException;
import com.variamos.core.util.FileUtils;

public class SWIPrologSolver implements Solver {

	private boolean loaded;

	private HlclProgram hlclProgram;
	private String programPath = null;
	private Query qr;
	boolean sucessfullLoad;
	private Map<String, Variable> vars;

	public final static String PROGRAM_INVOCATION = "productline(L)";

	public SWIPrologSolver() {

	}

	public SWIPrologSolver(HlclProgram hlclProgram) {
		super();
		this.hlclProgram = hlclProgram;
	}

	public int getSolutionsCount() {
		return 0; // FIXME this method is hard to do in swi prolog
	}

	@Override
	public boolean isSatisfiable(String programPath) {

		String query = PROGRAM_INVOCATION;
		if (loaded) {
			Query prologQuery = new Query(query);
			// Se obtiene una solución
			Hashtable[] table = prologQuery.nSolutions(1);
			if (table.length >= 1) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Deprecated
	public void setProductLine(AbstractModel pl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void solve(Configuration config, ConfigurationOptions options) {

		// Reinicia el solver
		if (options.isStartFromZero()) {
			if (qr != null && qr.isOpen()) {
				qr.rewind();// Cierra las consultas que no se hayan explorado
				qr.close();

			}
			qr = null;
			vars = new TreeMap<>();
			doQuery(config, options);
		}
		// Creates the query
		consultProgram(config, options);
	}

	private void doQuery(Configuration config, ConfigurationOptions options) {

		if ((hlclProgram == null || hlclProgram.isEmpty()) && options.getProgramPath() == null) {
			throw new TechnicalException("HlclProgram was not initialized");
		}
		else if (hlclProgram == null) {
			// A predefined prolog file is charged
			programPath = options.getProgramPath();
		} else {

			// We create a copy of the HLCLprogram in order to don't modify the
			// real hlclprogram
			HlclProgram modifiedCopy = new HlclProgram();
			modifiedCopy.addAll(hlclProgram.subList(0, hlclProgram.size()));
			PrologTransformParameters params = addParametersToProgram(
					modifiedCopy, options);
			programPath = createPrologFile(modifiedCopy, params);
			Set<Identifier> identifiers = HlclUtil
					.getUsedIdentifiers(hlclProgram);
			// Variables map is mandatory for dynamic configurations
			for (Identifier id : identifiers) {
				vars.put(id.getId(), new Variable(id.getId()));
			}
		}
		loadSWIProgram(programPath);
	}

	private void consultProgram(Configuration config,
			ConfigurationOptions options) {
		if (options.getProgramName() == null) {
			List<Compound> parts = new ArrayList<>();
			Term[] varTermsArray = new Term[vars.size()];
			int count = 0;
			for (String id : vars.keySet()) {
				varTermsArray[count] = vars.get(id);
				count++;
			}
			// Generate the list of variables Ejm L={A,B,C}
			Term L = Util.termArrayToList(varTermsArray);

			// Generate the assigns for the not ignored variables
			for (String id : config.getNotIgnored()) {
				// Create the atom
				jpl.Integer i = new jpl.Integer(config.stateOf(id));
				// Create the compound for the assign.
				Compound assign = new Compound("=", new Term[] { vars.get(id),
						i });
				parts.add(assign);
			}

			parts.add(new Compound("productline", new Term[] { L }));

			// Add all additional configuration options
			Compound query = addSubQueries(parts);
			System.out.println(query.toString());
			qr = new Query(query);
		} else {
			qr = new Query(options.programName + "(L)");
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
		boolean result = qr.hasMoreElements();
		return result;
	}

	@Override
	public Configuration getSolution() {
		if (qr != null) {
			if (hasNextSolution()) {
				Hashtable<Variable, Term> configurationHashSet = qr
						.nextSolution();
				if (configurationHashSet != null) {
					Configuration configuration = makeConfiguration(configurationHashSet);
					return configuration;
				}
			}

		}
		return null;
	}

	private Configuration makeConfiguration(
			Hashtable<Variable, Term> configurationHashSet) {

		// FIXME: puede ser mejorado para quitar esta L quemada
		Term invocationTerm = (Term) configurationHashSet.get("L");
		Configuration configuration = new Configuration();
		if (invocationTerm != null) {
			List<Integer> configurationValues = new ArrayList<Integer>();
			// Obtiene del resultado de L los valores asignados a cada variable
			// descomponiendo el resultado
			configurationValues = getSolutionValues(invocationTerm.args(),
					configurationValues);

			// Obtiene la lista ordenada de identificadores del hlcl program
			// para asignarlos a la configuracion. En este mismo orden se crean
			// cuando se define el programa de prolog
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
						"Configurated values and number of variables defined in expressions must be equals");
			}
		} else {
			Set<Variable> variables = configurationHashSet.keySet();

			Term term;
			String variable;
			Iterator iterator = variables.iterator();
			while (iterator.hasNext()) {
				variable = (String) iterator.next();
				term = configurationHashSet.get(variable);

				if (term.isInteger()) {
					Integer termValue = term.intValue();
					if (termValue == 0) {
						configuration.ban(variable);
					} else if (termValue == 1) {
						configuration.enforce(variable);
					} else {
						configuration.set(variable, termValue);
					}

				}
			}
		}
		return configuration;
	}

	private boolean loadSWIProgram(String temporalPath) {
		// To solve out of global stack
		jpl.fli.Prolog.set_default_init_args(new String[] { "pl", "-nosignals",
				"-L128m", "-G128m" });
		Query q1 = new Query("consult('" + temporalPath + "')");
		return q1.hasSolution();
	}

	@Override
	public void nextSolution() {
		// disabled method in SWI prolog

	}

	@Override
	public Object getProductLine() {
		// TODO Auto-generated method stub
		throw new TechnicalException("Now, we don't use product line anymore");
	}

	@Override
	public Map<String, List<Integer>> reduceDomain(Configuration config,
			ConfigurationOptions params) {
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
			ConfigurationOptions options) {

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

	private PrologTransformParameters getParamsFor(ConfigurationOptions options) {
		PrologTransformParameters params = new PrologTransformParameters();

		params.setFdLabeling(options.getMode() == ConfigurationMode.FULL);
		params.setFf(options.isFf());
		params.setOrder(options.isOrder());
		params.setLabelingOrder(options.getLabelingOrder());
		params.setOrderExpressions(options.getOrderExpressions());

		return params;
	}

	private String createPrologFile(HlclProgram hlclProgram,
			PrologTransformParameters params) {
		Hlcl2SWIProlog swiPrologTransformer = new Hlcl2SWIProlog();
		String prologProgram = swiPrologTransformer.transform(hlclProgram,
				params);
		String path;
		try {
			// Create a temporary file
			File file = File.createTempFile("tmp", ".pl");
			path = FileUtils.writePrologFile(file, prologProgram);
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
			return qr.hasSolution();
		} else {
			throw new TechnicalException("Solve method was not invoked");
		}

	}

	public HlclProgram getHlclProgram() {
		return hlclProgram;
	}

}
