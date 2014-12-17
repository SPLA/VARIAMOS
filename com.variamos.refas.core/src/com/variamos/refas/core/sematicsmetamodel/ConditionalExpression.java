package com.variamos.refas.core.sematicsmetamodel;

import java.io.Serializable;

import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

/**
 * A class to represent the conditional expressions. To be replaced by
 * BooleanExpression if it is enough for requirements. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 */
public class ConditionalExpression implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7322131682623959882L;
	private SemanticAttribute[] sentences = new SemanticAttribute[2];
	private SemanticAttribute logicalOperator;

	public ConditionalExpression() {
		this.sentences[0] = new SemanticAttribute("sentence1", "Class", true,
				"Sentence", "com.variamos.refas.core.expressions.Sentence", "",
				"Left Sentence");
		this.sentences[1] = new SemanticAttribute("sentence2", "Class", true,
				"Sentence", "com.variamos.refas.core.expressions.Sentence", "",
				"Right Sentence");
		this.logicalOperator = new SemanticAttribute("operator", "Enumeration",
				true, "Operator",
				"com.variamos.refas.core.expressions.LogicalOperator", "",
				"Operator");
	}

	public String getExpression() {
		return sentences[0].getName() + " " + logicalOperator.getName() + " "
				+ sentences[1].getName();
	}
}
