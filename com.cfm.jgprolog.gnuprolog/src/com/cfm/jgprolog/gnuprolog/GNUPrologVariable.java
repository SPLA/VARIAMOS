package com.cfm.jgprolog.gnuprolog;

import com.cfm.jgprolog.core.Term;
import com.cfm.jgprolog.core.VariableTerm;

public class GNUPrologVariable extends GNUPrologTerm implements VariableTerm, Comparable<GNUPrologVariable> {
	
	private String name;
	
	protected GNUPrologVariable(long address) {
		super(address);
		name = toString();
	}
	
	public GNUPrologVariable(String name) {
		super();
		plTerm = JGProlog.Pl_Mk_Variable();
		this.name = name;
	}

	protected int getGNUPrologType(){
		return JGProlog.Pl_Type_Of_Term(plTerm);
	}
	
	public void print() {
		JGProlog.Pl_Write(plTerm);
	}

	@Override
	public Term getValue() {
		return GNUPrologTermFactory.bindTerm(plTerm);
	}

	public boolean isTraceable(){
		return name.charAt(0) != '_';
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int compareTo(GNUPrologVariable other) {
		return name.compareTo(other.getName());
	}
}
