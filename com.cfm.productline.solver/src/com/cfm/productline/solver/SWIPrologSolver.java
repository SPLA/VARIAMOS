package com.cfm.productline.solver;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import jpl.Compound;
import jpl.Query;
import jpl.Variable;

import com.cfm.productline.ProductLine;
import com.variamos.core.exceptions.TechnicalException;

public class SWIPrologSolver implements Solver {

	private boolean successfullLoad;
	public final static String PROGRAM_INVOCATION = "productline(L)";

	@Override
	public boolean isSatisfiable(String programPath) {
		successfullLoad=loadSWIProgram(programPath);
		String query = PROGRAM_INVOCATION;
		if (successfullLoad) {
			Query prologQuery = new Query(query);
			// Se obtiene una solución
			Hashtable[] table = prologQuery.nSolutions(1);
			if (table.length >= 1) {
				return true;
			}
		} else {
			throw new TechnicalException(
					"SWI prolog is not initialized correctly");
		}
		return false;
	}

	@Override
	public void setProductLine(ProductLine pl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void solve(Configuration config, ConfigurationOptions options) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasNextSolution() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Configuration getSolution() {
		return null;
	}

	public Configuration getSolution(String path) {
		boolean successfullLoad = loadSWIProgram(path);
		List<Hashtable> configurationList = new ArrayList<Hashtable>();
		List<List<Integer>> allConfigurationValues = new ArrayList<List<Integer>>();
		if (successfullLoad) {
			Query prologQuery = new Query(PROGRAM_INVOCATION);
			if (prologQuery.hasSolution()) {
				Variable L = new Variable();
				Hashtable[] configurations = prologQuery.nSolutions(1);
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
						values = getSolutionValues(configurationCompound,
								values);
						allConfigurationValues.add(values);

					}
				}
			}

		}
		// FIXME
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
		// TODO Auto-generated method stub

	}

	@Override
	public int getSolutionsCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getProductLine() {
		// TODO Auto-generated method stub
		return null;
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

}
