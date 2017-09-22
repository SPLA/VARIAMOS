package com.variamos.reasoning.medic.model.graph;



public interface Variable {


	public String getId();
	
	public String getDomain();


	public int compareTo(Variable o);
}
