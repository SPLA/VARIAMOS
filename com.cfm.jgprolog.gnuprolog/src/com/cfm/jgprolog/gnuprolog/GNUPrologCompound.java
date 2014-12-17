package com.cfm.jgprolog.gnuprolog;

import java.nio.IntBuffer;

import com.cfm.jgprolog.core.AtomTerm;
import com.cfm.jgprolog.core.CompoundTerm;
import com.cfm.jgprolog.core.Term;
import com.jogamp.common.nio.Buffers;
import com.jogamp.common.nio.PointerBuffer;

class GNUPrologCompound extends GNUPrologTerm implements CompoundTerm {
	
	private GNUPrologAtom proxyFunctor;
	private Term[] proxyArgs;
	
	public GNUPrologCompound(long address) {
		super(address);
	}
	
	protected GNUPrologCompound(String functor, Term... args){
		makeCompound(functor, args);
	}

	private void makeCompound(String functor, Term... args){
		
		//Create the atom if it doesn't exist.
		int func = JGProlog.Pl_Create_Allocate_Atom(functor);
		
		int arity = args.length;
		
		PointerBuffer buf = PointerBuffer.allocateDirect(arity);
		for(Term t : args ){
			//It has to be a GNUPrologTerm !!!
			if( ! (t instanceof GNUPrologTerm) )
				continue;

			GNUPrologTerm term = (GNUPrologTerm)t;
			buf.put(term.getAddress());
		}
		buf.rewind();
		
		plTerm = JGProlog.Pl_Mk_Compound(func, arity, buf);
		
		//Fill the proxy values, they are not supposed to change anymore
		proxyFunctor = new GNUPrologAtom(func);
		proxyArgs = args;
	}
	
	private boolean isLoaded(){
		return proxyFunctor != null || proxyArgs != null; 
	}
	
	private void loadIfNeeded(){
		if( isLoaded() )
			return;
		
		IntBuffer funcBuf = Buffers.newDirectIntBuffer(1);
		IntBuffer arityBuf = Buffers.newDirectIntBuffer(1);
		PointerBuffer buf = JGProlog.Pl_Rd_Compound(plTerm, funcBuf, arityBuf);
		
		int functorIndex = funcBuf.get();
		int arity = arityBuf.get();
		
		proxyFunctor = new GNUPrologAtom( functorIndex );
		
		proxyArgs = new Term[arity];
		
		for(int i = 0; i < arity; i++)
			proxyArgs[i] = GNUPrologTermFactory.bindTerm(buf.get());
	}
	
	@Override
	public int getArity() {
		loadIfNeeded();
		return proxyArgs.length;
	}

	@Override
	public Term getParameter(int index) {
		loadIfNeeded();
		return proxyArgs[index];
	}

	@Override
	public AtomTerm getFunctor() {
		loadIfNeeded();
		return proxyFunctor;
	}

}
