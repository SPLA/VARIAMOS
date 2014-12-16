
package com.cfm.productline.solver;

import java.util.List;
import java.util.Map;

import com.cfm.hlcl.Domain;
import com.cfm.hlcl.HlclProgram;
import com.cfm.productline.ProductLine;

public interface Solver {
	@Deprecated
	public void setProductLine(ProductLine pl);
	
	public void setHLCLProgram(HlclProgram hlclProgram);

	public void solve(Configuration config, ConfigurationOptions options);
	
	
	public boolean hasNextSolution();
	

	public Configuration getSolution();
	@Deprecated
	public void nextSolution();
	
	@Deprecated
	public int getSolutionsCount();
	@Deprecated
	public Object getProductLine();
	
	
	//Proposed operations
	
	
	/**
	 * @param programPath
	 * @return true, constraint program saved in @programPath is satisfiable, false otherwise
	 */
	@Deprecated
	public boolean isSatisfiable(String programPath);
	@Deprecated
	public Map<String, List<Integer>> reduceDomain(Configuration config, ConfigurationOptions params);
}
