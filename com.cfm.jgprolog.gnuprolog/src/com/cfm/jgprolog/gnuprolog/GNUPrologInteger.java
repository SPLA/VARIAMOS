package com.cfm.jgprolog.gnuprolog;

import com.cfm.jgprolog.core.IntegerTerm;

class GNUPrologInteger extends GNUPrologTerm implements IntegerTerm {
	
	public GNUPrologInteger(int i){
		super();
		plTerm = JGProlog.Pl_Mk_Integer(i);
	}

	public GNUPrologInteger(long address) {
		super(address);
	}
	
	public Integer getValue(){
		return (int)JGProlog.Pl_Rd_Integer(plTerm);
	}
}
