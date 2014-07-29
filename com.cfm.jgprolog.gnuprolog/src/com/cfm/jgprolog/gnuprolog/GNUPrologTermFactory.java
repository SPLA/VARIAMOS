package com.cfm.jgprolog.gnuprolog;

import com.cfm.jgprolog.core.AtomTerm;
import com.cfm.jgprolog.core.CompoundTerm;
import com.cfm.jgprolog.core.FloatTerm;
import com.cfm.jgprolog.core.IntegerTerm;
import com.cfm.jgprolog.core.ListTerm;
import com.cfm.jgprolog.core.PrologTermFactory;
import com.cfm.jgprolog.core.Term;
import com.cfm.jgprolog.core.VariableTerm;

public class GNUPrologTermFactory implements PrologTermFactory{
	
//	private static Map<Long, GNUPrologVariable> varMap = new HashMap<>();
	
	@Override
	public AtomTerm newAtom(String value) {
		return new GNUPrologAtom(value);
	}

	@Override
	public FloatTerm newFloat(float f) {
		return new GNUPrologFloat(f);
	}

	@Override
	public IntegerTerm newInteger(int i) {
		return new GNUPrologInteger(i);
	}

	@Override
	public VariableTerm newVariable(String name) {
		GNUPrologVariable var = new GNUPrologVariable(name);

		//Register it for tracking
//		varMap.put(var.getAddress(), var);
		
		return var;
	}
	/*

	 	PL_PLV: Prolog variable.
		PL_FDV: finite domain variable.
		PL_INT: integer.
		PL_FLT: floating point number.
		PL_ATM: atom.
		PL_LST: list.
		PL_STC: structure

	 */
	
	
	
	protected static Term bindTerm(long plTerm){
		//TODO ask for these transformations
		switch( JGProlog.Pl_Type_Of_Term(plTerm) ){

			case JGProlog.PL_PLV:
				//Check if the variable already exists in the factory
//				if( varMap.containsKey(plTerm) )
//					return varMap.get(plTerm);
				
				return bindVariable(plTerm);
	
			case JGProlog.PL_FLT:
				return bindFloat(plTerm);
			
			case JGProlog.PL_INT:
				return bindInteger(plTerm);
				
			case JGProlog.PL_ATM:
				return bindAtom(plTerm);
				//int atom = JGProlog.Pl_Rd_Atom(plTerm);
				//return new GNUPrologAtom(JGProlog.Pl_Atom_Name(atom));
	
			case JGProlog.PL_STC:
				return bindCompound(plTerm);
				
			case JGProlog.PL_LST:
				return bindList(plTerm);
//				return createProperCompound(plTerm);
	
			case JGProlog.PL_FDV:
				return null;
		}
		return null;
	}

	private static Term bindInteger(long plTerm) {
		return new GNUPrologInteger(plTerm);
	}

	protected static GNUPrologList bindList(long plTerm) {
		return new GNUPrologList(plTerm);
	}

	
	protected static GNUPrologCompound bindCompound(long plTerm) {
		return new GNUPrologCompound(plTerm);
	}

	protected static GNUPrologAtom bindAtom(long plTerm) {
		return new GNUPrologAtom(plTerm);
	}

	protected static GNUPrologFloat bindFloat(long plTerm) {
		return new GNUPrologFloat(plTerm);
	}

	protected static GNUPrologVariable bindVariable(long plTerm) {
		return new GNUPrologVariable(plTerm);
	}
	
//	protected static CompoundTerm createProperCompound(long plTerm){
//		IntBuffer funcBuf = Buffers.newDirectIntBuffer(1);
//		IntBuffer arityBuf = Buffers.newDirectIntBuffer(1);
//		PointerBuffer buf = JGProlog.Pl_Rd_Compound(plTerm, funcBuf, arityBuf);
//		
//		int functorIndex = funcBuf.get();
//		int arity = arityBuf.get();
//		
//		GNUPrologAtom functor = new GNUPrologAtom( JGProlog.Pl_Atom_Name(functorIndex) );
//		
//		Term[] terms = new Term[arity];
//		
//		for(int i = 0; i < arity; i++){
//			long t = buf.get();
//			terms[i] = createProperTerm(t);
//		}
//		
//		return new CompoundTerm(functor, terms);
//	}

	@Override
	public CompoundTerm newCompound(String functor, Term... args) {
		return new GNUPrologCompound(functor, args);
	}

	@Override
	public ListTerm newList(Term... values) {
		return new GNUPrologList(values);
	}
	
	public Term fromString(String term){
		return bindTerm(JGProlog.Pl_Read_From_String(term));
	}
}
