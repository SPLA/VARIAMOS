package com.cfm.jgprolog.core;

public interface PrologEngine {
	
	public void startProlog();
	
	public void stopProlog();
	
	public void resetProlog();
	
	public void consult(String absFilePath) throws PrologException;
	
	public QueryResult runQuery(CompoundTerm query) throws PrologException;
	
}
