package com.cfm.jgprolog.gnuprolog;

import com.cfm.jgprolog.core.CompoundTerm;
import com.cfm.jgprolog.core.PrologException;
import com.cfm.jgprolog.core.QueryResult;
import com.cfm.jgprolog.core.ResultCallback;
import com.cfm.jgprolog.core.Term;
import com.cfm.jgprolog.core.VariableSet;
import com.jogamp.common.nio.PointerBuffer;

class GNUPrologQueryResult extends QueryResult{
	
	private final static int NO_RESULT = -1;
	
	private int result = NO_RESULT;
	
	private VariableSet vars;
	
	public GNUPrologQueryResult(CompoundTerm query) throws PrologException {
		super(query);
		runQuery();
	}
	
	protected GNUPrologQueryResult(CompoundTerm query, boolean recoverable) throws PrologException {
		super(query);
		runQuery(recoverable);
	}
	
	protected void runQuery(boolean recoverable) throws PrologException{
		JGProlog.Pl_Query_Begin(recoverable);
		
		PointerBuffer arg = getParametersBuffer();
		int func = JGProlog.Pl_Find_Atom(query.getFunctor().getName());
		
		result = JGProlog.Pl_Query_Call(func, query.getArity(), arg);
		
		if( result == JGProlog.PL_EXCEPTION )
			throw new GNUPrologErrorException();
	}
	
	private void runQuery() throws PrologException {
		runQuery(true);
	}

	private PointerBuffer getParametersBuffer() {
		PointerBuffer pb = PointerBuffer.allocateDirect(query.getArity());
		
		vars = new VariableSet();
		//for(Term t : query.getArguments()){
		for(int i = 0; i < query.getArity(); i++){
			
			Term t = query.getParameter(i);
			//It has to be a GNUPrologTerm !!!
			if( ! (t instanceof GNUPrologTerm) )
				continue;

			GNUPrologTerm term = (GNUPrologTerm)t;
			pb.put(term.getAddress());
			
			//Check if it's a variable so we can track it
			trackTerm(term);
		}
		pb.rewind();
		
		return pb;
	}
	private void trackTerm(Term term){
		if( term instanceof GNUPrologVariable ){
			GNUPrologVariable var = (GNUPrologVariable) term;
			if( var.isTraceable() )
				vars.addVariable(var);
		}
		if( term instanceof GNUPrologCompound ){
			trackCompound( (CompoundTerm ) term) ;
		}
		if( term instanceof GNUPrologList ){
			for( Term tList : ((GNUPrologList) term).getAllTerms()){
				trackTerm(tList);
			}
		}
	}
	private void trackCompound( CompoundTerm cmp){
		for(int i = 0; i < cmp.getArity(); i++){
			Term t = cmp.getParameter(i);
			//It has to be a GNUPrologTerm !!!
			if( ! (t instanceof GNUPrologTerm) )
				continue;
			
			GNUPrologTerm term = (GNUPrologTerm)t;
						
			//Check if it's a variable so we can track it
			trackTerm(term);
		}
	}

	@Override
	public boolean hasMoreSolutions() {
		return result == JGProlog.PL_SUCCESS;
	}
	
	@Override
	public void nextSolution(){
		result = JGProlog.Pl_Query_Next_Solution();
	}
	
	@Override
	public void close() {
		endQuery(JGProlog.PL_RECOVER);
		//JGProlog.Pl_Query_End(JGProlog.PL_RECOVER);
	}
	
	public void endQuery(int param){

		JGProlog.Pl_Query_End(param);
	}

	@Override
	public void forAllResults(ResultCallback callback) {
		while( hasMoreSolutions() ){
			callback.onResult(vars);
			nextSolution();
		}
	}

	@Override
	public VariableSet getVariableSet() {
		return vars;
	}
}
