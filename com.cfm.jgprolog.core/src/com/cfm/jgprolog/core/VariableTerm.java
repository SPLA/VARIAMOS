package com.cfm.jgprolog.core;

public interface VariableTerm extends Term{
	
	public String getName();
	public Term getValue();
	
//	public AtomTerm getAtom();
//	public FloatTerm getFloat();
//	public IntegerTerm getInteger();
//	
//	public boolean hasAtom();
//	public boolean hasFloat();
//	public boolean hasInteger();
}
