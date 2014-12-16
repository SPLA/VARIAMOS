package com.cfm.productline.solver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import jpl.Compound;
import jpl.Query;
import jpl.Variable;

import com.cfm.hlcl.HlclProgram;
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

	public final static String PROGRAM_INVOCATION = "productline(L)";

	public SWIPrologSolver() {
		
	}
	
	public SWIPrologSolver(HlclProgram hlclProgram) {
		super();
		this.hlclProgram = hlclProgram;
	}

	public int getSolutionsCount() {
		// TODO Auto-generated method stub
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
		} else {

		}
		return false;
	}

	@Override
	public void setProductLine(ProductLine pl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void solve(Configuration config, ConfigurationOptions options) {

		// FIXME arreglar para incluir config en caso de que llegue
		hlclProgram = addParametersToProgram(hlclProgram, options);
		programPath = createPrologFile(hlclProgram);
		loadSWIProgram(programPath);
		qr = new Query(PROGRAM_INVOCATION);
		sucessfullLoad = qr.hasSolution();
		if (!sucessfullLoad) {
			throw new TechnicalException(
					"SWI prolog is not initialized correctly");
		}

	}

	@Override
	public boolean hasNextSolution() {
		if (qr == null) {
			throw new TechnicalException("Solve method was not invoked");
		}

		return qr.hasSolution();
	}

	@Override
	public Configuration getSolution() {
		List<Hashtable> configurationList = new ArrayList<Hashtable>();
		List<List<Integer>> allConfigurationValues = new ArrayList<List<Integer>>();
		if (sucessfullLoad) {
			Variable L = new Variable();
			Hashtable[] configurations = qr.nSolutions(1);
			// TODO ver si esta lista retorna la lista de variables,
			Hashtable variablesName=qr.getSubstWithNameVars();
			for (Hashtable configuration : configurations) {
				configurationList.add(configuration);
			}
		}

		if (!configurationList.isEmpty()) {
			for (Hashtable configuration : configurationList) {
				if (configuration != null) {
					List<Integer> values = new ArrayList<Integer>();
					Compound configurationCompound = (Compound) configuration
							.get("L");
					values = getSolutionValues(configurationCompound, values);
					allConfigurationValues.add(values);

				}
			}
		}

		// TODO asociar cada configuracion de la lista con los valores de las
		// variables

		return null;
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
		if (hasNextSolution())
			qr.nextSolution();
	}

	@Override
	public Object getProductLine() {
		// TODO Auto-generated method stub
		throw new TechnicalException(
				"Now, we don't use product line");
	}

	@Override
	public Map<String, List<Integer>> reduceDomain(Configuration config,
			ConfigurationOptions params) {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<Integer> getSolutionValues(Compound compound,
			List<Integer> values) {
		jpl.Term[] terms = compound.args();

		if (terms[1].isAtom()) {
			Integer valueInteger = terms[0].intValue();
			values.add(valueInteger);
			return values;
		} else {
			Integer valueInteger = terms[0].intValue();
			Compound compoundValues = (Compound) terms[1];
			values.add(valueInteger);
			getSolutionValues(compoundValues, values);

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

}
