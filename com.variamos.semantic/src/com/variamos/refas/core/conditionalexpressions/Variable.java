package com.variamos.refas.core.conditionalexpressions;

import com.variamos.refas.semantic.semanticsupport.SemanticVariable;

public class Variable extends Sentence {
	private SemanticVariable semanticVariable;
	
	public Variable()
	{}
	
	public Variable (SemanticVariable semanticVariable)
	{
		this.semanticVariable = semanticVariable;
	}
}
