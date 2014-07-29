package com.cfm.jgprolog.gnuprolog;

import com.cfm.jgprolog.core.FloatTerm;

class GNUPrologFloat extends GNUPrologTerm implements FloatTerm {

	public GNUPrologFloat(float value){
		plTerm = JGProlog.Pl_Mk_Float(value);
	}
	
	public GNUPrologFloat(long address) {
		super(address);
	}

	public Float getValue(){
		return (float)JGProlog.Pl_Rd_Float(plTerm);
	}
}
