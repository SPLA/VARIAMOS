package com.variamos.solver.core;

import java.util.List;
import java.util.Map;

import com.variamos.hlcl.core.HlclProgram;
import com.variamos.solver.model.ConfigurationOptionsDTO;
import com.variamos.solver.model.SolverSolution;

public interface IntSolver {

		public void setHLCLProgram(HlclProgram hlclProgram);

	public void solve(SolverSolution config, ConfigurationOptionsDTO options);

	public boolean hasNextSolution();

	public SolverSolution getSolution();

	public void nextSolution();

	public boolean hasSolution();

	public long getLastExecutionTime();

	// Proposed operations

	public int getSolutionsCount();

	public List<SolverSolution> getAllSolutions();

	/**
	 * @param programPath
	 * @return true, constraint program saved in @programPath is satisfiable,
	 *         false otherwise
	 */
	@Deprecated
	public boolean isSatisfiable(String programPath);

	@Deprecated
	public Map<String, List<Integer>> reduceDomain(SolverSolution config,
			ConfigurationOptionsDTO params);

	// Esto para qué sirve?: jcmunoz para que la ejecución ya no continue sobre
	// la iteración anterior, no funciona igual que en la version anterior
	public void clearQueryMonitor();
}
