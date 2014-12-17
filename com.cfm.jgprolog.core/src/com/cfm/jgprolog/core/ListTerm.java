package com.cfm.jgprolog.core;


public interface ListTerm extends Term{
	
	public Term getHead();
	
	public ListTerm getTail();
	
	public Term[] getAllTerms();
	
	public boolean isEmpty();
}
