package com.variamos.refas.core.sematicsmetamodel;

import java.io.Serializable;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class ConditionalExpression implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7322131682623959882L;
	private SemanticAttribute[] sentences= new SemanticAttribute[2];
	private SemanticAttribute logicalOperator;
	
	public ConditionalExpression (SemanticAttribute semanticAttribute1, 
			SemanticAttribute semanticAttribute2, SemanticAttribute logicalOperator)
	{
		this.sentences[0] = semanticAttribute1;
		this.sentences[1] = semanticAttribute2;
		this.logicalOperator = logicalOperator;
	}

	public String getExpression() {
		return sentences[0].getName() + " "+ logicalOperator.getName() + " "+ sentences[1].getName();
	}
}
