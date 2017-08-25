package com.cfm.jgprolog.core;

import java.util.TreeMap;

@SuppressWarnings("serial")
public class VariableSet extends TreeMap<String, VariableTerm>{
	
	public void addVariable(VariableTerm t){
		put(t.getName(), t);
	}
	
	public VariableTerm getVariable(String str){
		return get(str);
	}
}
