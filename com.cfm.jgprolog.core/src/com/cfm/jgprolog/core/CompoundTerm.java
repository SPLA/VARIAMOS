package com.cfm.jgprolog.core;


public interface CompoundTerm extends Term {
	
	public int getArity();
	public AtomTerm getFunctor();
	public Term getParameter(int index);
	
//	private AtomTerm functor;
//	private Term[] arguments;
//
//	public CompoundTerm() {
//		super();
//	}
//
//	public CompoundTerm(AtomTerm functor, Term[] arguments) {
//		super();
//		this.functor = functor;
//		this.arguments = arguments;
//	}
//
//	public AtomTerm getFunctor() {
//		return functor;
//	}
//
//	public void setFunctor(AtomTerm functor) {
//		this.functor = functor;
//	}
//
//	public Term[] getArguments() {
//		return arguments;
//	}
//
//	public void setArguments(Term[] arguments) {
//		this.arguments = arguments;
//	}
//	
//	public boolean isList(){
//		return ".".equals(functor.getName()) && arguments.length == 2;
//	}
//	
//	public List<Term> readAsList(){
//		ArrayList<Term> terms = new ArrayList<>();
//		
//		AtomTerm f = functor;
//		Term[] args = arguments;
//		
//		while(".".equals(f.getName()) && args.length == 2){
//			terms.add(arguments[0]);
//			f = (AtomTerm)arguments[1];
//		}
//		
//		return terms;
//	}
}
