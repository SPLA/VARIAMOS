package com.variamos.semantic.expressionsupport;

import java.util.List;

import com.variamos.semantic.semanticsupport.AbstractSemanticElement;

/**
 * A class to store MetaExpression Sets. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-02-05
 */
public class SemanticExpressionSet {
	private String identifier;
	private String description;
	private boolean userSelectable;
	private int priority;
	private int responseAction;
	private String errorDescription;
	private List<AbstractSemanticElement> semanticElements;
	private List<ExpressionSetSubAction> expressionSetSubAction;
}
