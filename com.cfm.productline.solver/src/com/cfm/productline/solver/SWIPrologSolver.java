package com.cfm.productline.solver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jpl.Compound;
import jpl.Query;
import jpl.Term;
import jpl.Variable;

import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.HlclUtil;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.LiteralBooleanExpression;
import com.cfm.productline.ProductLine;
import com.cfm.productline.prologEditors.Hlcl2SWIProlog;
import com.variamos.core.exceptions.TechnicalException;
import com.variamos.core.util.FileUtils;

public class SWIPrologSolver implements Solver {

	private boolean loaded;

	private HlclProgram hlclProgram = null;
	private String programPath = null;
	private Query qr;
	boolean sucessfullLoad;
	private Variable invocationVariable;

	public final static String PROGRAM_INVOCATION = "productline(L)";

	@Deprecated
	public SWIPrologSolver() {

	}

	public SWIPrologSolver(HlclProgram hlclProgram) {
		super();
		this.hlclProgram = hlclProgram;
		this.invocationVariable = new Variable("L");
	}

	public int getSolutionsCount() {
		return 0;
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
	public void setProductLine(ProductLine pl) {
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
		}
		if (qr == null) {
			doQuery(config, options);

		}

	}

	private void doQuery(Configuration config, ConfigurationOptions options) {

		hlclProgram = addParametersToProgram(hlclProgram, options);
		programPath = createPrologFile(hlclProgram);
		loadSWIProgram(programPath);
		qr = new Query(PROGRAM_INVOCATION);
		sucessfullLoad = qr.hasSolution();
		if (!sucessfullLoad) {
			throw new TechnicalException(
					"SWI prolog is not correctly initialized ");
		}

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

		List<Integer> configurationValues = new ArrayList<Integer>();
		// Obtiene del resultado de L los valores asignados a cada variable
		// descomponiendo el resultado
		configurationValues = getSolutionValues(invocationTerm.args(),
				configurationValues);

		// Obtiene la lista ordenada de identificadores del hlcl program
		// para asignarlos a la configuracion. En este mismo orden se crean
		// cuando se define el programa de prolog
		Set<Identifier> idsSet = HlclUtil.getUsedIdentifiers(hlclProgram);
		Configuration configuration = new Configuration();

		int i = 0;
		if (idsSet.size() == idsSet.size()) {
			for (Identifier identifier : idsSet) {
				int value = configurationValues.get(i);
				if (value == 0) {
					configuration.ban(identifier.getId());
				}
				if (value == 1) {
					configuration.enforce(identifier.getId());
				}
				i++;
			}
			return configuration;
		} else {
			throw new TechnicalException(
					"Configurated values and number of variables defined in expressions must be equals");
		}

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

	private HlclProgram addParametersToProgram(HlclProgram prog,
			ConfigurationOptions options) {

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
		return prog;
	}

	private String createPrologFile(HlclProgram hlclProgram) {
		Hlcl2SWIProlog swiPrologTransformer = new Hlcl2SWIProlog();
		String prologProgram = swiPrologTransformer.transform(hlclProgram);
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

}
