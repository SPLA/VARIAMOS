package com.cfm.jgprolog.core;

import java.io.Closeable;

public abstract class QueryResult implements Closeable {
	
	protected CompoundTerm query;
	
	public QueryResult(CompoundTerm query){
		this.query = query;
	}
	
	public CompoundTerm getQuery(){
		return query;
	}
	
	public abstract VariableSet getVariableSet();
	public abstract boolean hasMoreSolutions();
	public abstract void nextSolution();
	public abstract void forAllResults( ResultCallback callback );
}
