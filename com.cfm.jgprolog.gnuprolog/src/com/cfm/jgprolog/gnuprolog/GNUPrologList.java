package com.cfm.jgprolog.gnuprolog;

import java.nio.IntBuffer;

import com.cfm.jgprolog.core.ListTerm;
import com.cfm.jgprolog.core.Term;
import com.jogamp.common.nio.Buffers;
import com.jogamp.common.nio.PointerBuffer;

class GNUPrologList extends GNUPrologTerm implements ListTerm {
	
	private Term[] proxyTerms;

	protected GNUPrologList(long plTerm){
		super(plTerm);
	}
	
	protected GNUPrologList(Term[] values) {
		proxyTerms = values;
		makeProperList(values);
	}

	private void makeProperList(Term[] values) {
		
		int size = 0;
		if( values != null ){
			size = values.length;
		}
		
		PointerBuffer buf = PointerBuffer.allocate(size);
		
		if( values != null ){
			for(Term t : values ){
				//It has to be a GNUPrologTerm !!!
				if( ! (t instanceof GNUPrologTerm) )
					continue;

				GNUPrologTerm term = (GNUPrologTerm)t;
				buf.put(term.getAddress());
			}
		}else{
			//Empty list, put null
			buf.put(0);
		}
		
		buf.rewind();
		plTerm = JGProlog.Pl_Mk_Proper_List(size, buf);
	}

	@Override
	public Term getHead() {
		IntBuffer funcBuf = Buffers.newDirectIntBuffer(1);
		IntBuffer arityBuf = Buffers.newDirectIntBuffer(1);
		
		PointerBuffer buf = JGProlog.Pl_Rd_Compound(plTerm, funcBuf, arityBuf);

		//If it's empty
		if( buf == null )
			return null;
		
		return GNUPrologTermFactory.bindTerm(buf.get());
	}

	@Override
	public ListTerm getTail() {
		IntBuffer funcBuf = Buffers.newDirectIntBuffer(1);
		IntBuffer arityBuf = Buffers.newDirectIntBuffer(1);
		
		PointerBuffer buf = JGProlog.Pl_Rd_Compound(plTerm, funcBuf, arityBuf);
		
		//If it's empty
		if( buf == null )
			return null;
		
		return GNUPrologTermFactory.bindList(buf.get(1));
	}

	@Override
	public Term[] getAllTerms() {
		if( proxyTerms == null )
			loadTerms();
		
		return proxyTerms;
	}

	private void loadTerms() {
		
		int listLen = JGProlog.Pl_List_Length(plTerm);
		
		PointerBuffer buf = PointerBuffer.allocateDirect(listLen);
		
		JGProlog.Pl_Rd_Proper_List(plTerm, buf);
		
		//PointerBuffer buf = JGProlog.Pl_Rd_List(plTerm);
		
		proxyTerms = new Term[buf.capacity()];
		
		for(int i = 0; i < buf.capacity(); i++)
			proxyTerms[i] = GNUPrologTermFactory.bindTerm(buf.get(i));
	}

	@Override
	public boolean isEmpty() {
		return getHead() == null;
	}
}
