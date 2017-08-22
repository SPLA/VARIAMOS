package com.variamos.solver;

import java.util.List;
import java.util.Map;

import com.cfm.productline.ProductLine;
import com.variamos.hlcl.HlclProgram;

public interface Solver {

	@Deprecated
	public void setProductLine(ProductLine pl);

	public void setHLCLProgram(HlclProgram hlclProgram);

	public void solve(Configuration config, ConfigurationOptions options);

	public boolean hasNextSolution();

	public Configuration getSolution();

	public void nextSolution();

	public boolean hasSolution();

	@Deprecated
	public Object getProductLine();

	public long getLastExecutionTime();

	// Proposed operations

	public int getSolutionsCount();

	public List<Configuration> getAllSolutions();

	/**
	 * @param programPath
	 * @return true, constraint program saved in @programPath is satisfiable,
	 *         false otherwise
	 */
	@Deprecated
	public boolean isSatisfiable(String programPath);

	@Deprecated
	public Map<String, List<Integer>> reduceDomain(Configuration config,
			ConfigurationOptions params);

	// Esto para qué sirve?: jcmunoz para que la ejecución ya no continue sobre
	// la iteración anterior, no funciona igual que en la version anterior
	public void clearQueryMonitor();
}
