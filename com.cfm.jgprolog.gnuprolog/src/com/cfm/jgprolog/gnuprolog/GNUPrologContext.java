package com.cfm.jgprolog.gnuprolog;

import java.io.IOException;
import java.net.URL;

import com.cfm.jgprolog.core.PrologContext;
import com.cfm.jgprolog.core.PrologEngine;
import com.cfm.jgprolog.core.PrologTermFactory;

public class GNUPrologContext implements PrologContext{
	
	private static GNUPrologEngine engine;
	private static GNUPrologTermFactory termFactory;
	
	public GNUPrologContext(){
		
		try {
			NativeUtils.loadLibrary("GNUProlog");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public PrologEngine getEngine() {
		if( engine == null )
			engine = new GNUPrologEngine();
		
		return engine;
	}

	@Override
	public PrologTermFactory getTermFactory() {
		if( termFactory == null )
			termFactory = new GNUPrologTermFactory();
		
		return termFactory;
	}
}
