package com.variamos.refas.core.expressions;

import com.variamos.refas.core.sematicsmetamodel.SemanticVariable;

public class Variable extends Sentence {
	private SemanticVariable semanticVariable;
	
	public Variable()
	{}
	
	public Variable (SemanticVariable semanticVariable)
	{
		this.semanticVariable = semanticVariable;
	}
}
