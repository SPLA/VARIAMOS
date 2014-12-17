package com.cfm.jgprolog.gnuprolog;

import com.cfm.jgprolog.core.AtomTerm;

class GNUPrologAtom extends GNUPrologTerm implements AtomTerm{
	
	private int atomIndex;
	
	protected GNUPrologAtom(String value) {
		super();
		atomIndex = JGProlog.Pl_Create_Allocate_Atom(value);
		plTerm = JGProlog.Pl_Mk_Atom(atomIndex);
	}
	
	protected GNUPrologAtom(int index){
		atomIndex = index;
		plTerm = JGProlog.Pl_Mk_Atom(index);
	}

	protected GNUPrologAtom(long address) {
		super(address);
		atomIndex = JGProlog.Pl_Rd_Atom(address);
	}

	public String getName(){
		return JGProlog.Pl_Atom_Name(atomIndex);
	}
}
