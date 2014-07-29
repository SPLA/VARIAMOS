package com.cfm.jgprolog.gnuprolog;

import com.cfm.jgprolog.core.Term;



/**
 * Protected class for holding the GNUProlog Terms
 * GNUProlog represents a Term as a PlTerm which is a long
 * 
 * @author David López
 *
 */
abstract class GNUPrologTerm implements Term{
	
	protected long plTerm;

	protected GNUPrologTerm(){
		this(0);
	}
	
	protected GNUPrologTerm(long address){
		this.plTerm = address;
	}

	public long getAddress() {
		return plTerm;
	}
	
	public boolean existsInProlog(){
		return plTerm != 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj == null || !(obj instanceof GNUPrologTerm) )
			return false;
		
		GNUPrologTerm other = (GNUPrologTerm)obj;
		return JGProlog.Pl_Builtin_Term_Eq(other.plTerm, plTerm);
	}
	
	@Override
	public String toString() {
		return JGProlog.Pl_Writeq_To_String(plTerm);
	}
}
