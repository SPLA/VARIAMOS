package com.variamos.reasoning.util;

import com.variamos.hlcl.core.HlclProgram;
import com.variamos.solver.core.IntSolver;
import com.variamos.solver.core.SWIPrologSolver;
import com.variamos.solver.model.ConfigurationOptionsDTO;
import com.variamos.solver.model.SolverSolution;

public class SolverOperationsUtil {

	private IntSolver solver;

	public SolverOperationsUtil() {
		super();
		solver= new SWIPrologSolver();
	}

		
	public boolean isSatisfiable(HlclProgram model) {
		solver.setHLCLProgram(model);
		solver.solve(new SolverSolution(), new ConfigurationOptionsDTO());
		return solver.hasSolution();

	}

	public boolean isSatisfiable(HlclProgram model,
			SolverSolution configuration,
			ConfigurationOptionsDTO configurationOptions) {
		solver.setHLCLProgram(model);
		solver.solve(configuration, configurationOptions);
		return solver.hasSolution();

	}

	public SolverSolution getConfiguration(HlclProgram model,
			SolverSolution configuration,
			ConfigurationOptionsDTO configurationOptions) {
		solver.setHLCLProgram(model);
		solver.solve(configuration, configurationOptions);
		return solver.getSolution();
	}

	public long getLastExecutionTime() {
		return solver.getLastExecutionTime();
	}

	/**
	 * This operation takes a an HLCL as input and returns true if at most one valid
	 * product can be configured with it. Although
	 * 
	 * @param path
	 * @param prologEditorType
	 * @return
	 */
	public boolean isFalseProductLine(HlclProgram model) {

		SolverSolution configuration = new SolverSolution();
		int count = 0;
		solver.setHLCLProgram(model);
		solver.solve(new SolverSolution(), new ConfigurationOptionsDTO());
		while (count <= 2) {
			configuration = solver.getSolution();
			if (configuration != null) {
				// System.out.println(configuration);
				count++;
			} else
				break;
		}
		if (count >= 2) {
			return true;
		} else {
			return false;
		}

	}
}
