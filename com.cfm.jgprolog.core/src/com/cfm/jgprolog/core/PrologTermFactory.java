package com.cfm.jgprolog.core;

public interface PrologTermFactory {
	public AtomTerm newAtom(String value);
	public FloatTerm newFloat(float f);
	public IntegerTerm newInteger(int i);
	public VariableTerm newVariable(String name);
	public CompoundTerm newCompound(String functor, Term... args);
	public ListTerm newList(Term... values);
}
