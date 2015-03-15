package com.variamos.defectAnalyzer.util;

import com.cfm.jgprolog.gnuprolog.GNUPrologContext;
import com.variamos.core.enums.SolverEditorType;
import com.variamos.hlcl.HlclProgram;
import com.variamos.solver.Configuration;
import com.variamos.solver.ConfigurationOptions;
import com.variamos.solver.GNUPrologSolver;
import com.variamos.solver.SWIPrologSolver;
import com.variamos.solver.Solver;

public class SolverOperationsUtil {

	private Solver solver;

	public SolverOperationsUtil() {
		super();
	}

	public SolverOperationsUtil(SolverEditorType prologEditorType) {

		if (prologEditorType.equals(SolverEditorType.SWI_PROLOG)) {
			solver = new SWIPrologSolver();
		} else if (prologEditorType.equals(SolverEditorType.GNU_PROLOG)) {
			solver = new GNUPrologSolver(new GNUPrologContext());

		}
	}

	@Deprecated
	public boolean isSatisfiable(String programPath) {
		return solver.isSatisfiable(programPath);

	}

	public boolean isSatisfiable(HlclProgram model) {
		solver.setHLCLProgram(model);
		solver.solve(new Configuration(), new ConfigurationOptions());
		return solver.hasSolution();

	}

	public boolean isSatisfiable(HlclProgram model,
			Configuration configuration,
			ConfigurationOptions configurationOptions) {
		solver.setHLCLProgram(model);
		solver.solve(configuration, configurationOptions);
		return solver.hasSolution();

	}

	public Configuration getConfiguration(HlclProgram model,
			Configuration configuration,
			ConfigurationOptions configurationOptions) {
		solver.setHLCLProgram(model);
		solver.solve(configuration, configurationOptions);
		return solver.getSolution();
	}

	public long getLastExecutionTime() {
		return solver.getLastExecutionTime();
	}

	/**
	 * This operation takes a PLM as input and returns true if at most one valid
	 * product can be configured with it. Although
	 * 
	 * @param path
	 * @param prologEditorType
	 * @return
	 */
	public boolean isFalseProductLine(HlclProgram model) {

		Configuration configuration = new Configuration();
		int count = 0;
		solver.setHLCLProgram(model);
		solver.solve(new Configuration(), new ConfigurationOptions());
		while (count <= 2) {
			configuration = solver.getSolution();
			if (configuration != null) {
				count++;
			}
		}
		if (count >= 2) {
			return true;
		} else {
			return false;
		}

	}

}
