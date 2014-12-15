package com.cfm.productline.solver;

import java.util.List;
import java.util.Map;

import com.cfm.hlcl.Domain; 
import com.cfm.productline.ProductLine;

public interface Solver {
	public void setProductLine(ProductLine pl);
	public void solve(Configuration config, ConfigurationOptions options);
	public boolean hasNextSolution();
	public Configuration getSolution();
	public void nextSolution();
	public int getSolutionsCount();
	public Object getProductLine();
	
	
	//Proposed operations
	
	
	
	
	/**
	 * @param programPath
	 * @return true, constraint program saved in @programPath is satisfiable, false otherwise
	 */
	public boolean isSatisfiable(String programPath);
	public Map<String, List<Integer>> reduceDomain(Configuration config, ConfigurationOptions params);
}
