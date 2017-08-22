package com.variamos.solver.core;

import java.util.List;
import java.util.Map;

import com.cfm.productline.ProductLine;
import com.variamos.hlcl.HlclProgram;
import com.variamos.solver.model.SolverSolution;
import com.variamos.solver.model.ConfigurationOptionsDTO;

public interface IntSolver {

	@Deprecated
	public void setProductLine(ProductLine pl);

	public void setHLCLProgram(HlclProgram hlclProgram);

	public void solve(SolverSolution config, ConfigurationOptionsDTO options);

	public boolean hasNextSolution();

	public SolverSolution getSolution();

	public void nextSolution();

	public boolean hasSolution();

	@Deprecated
	public Object getProductLine();

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
