package com.variamos.dynsup.staticexprsup;

import java.util.List;
import java.util.Map;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntExpression;

/**
 * Class to create the Not expression. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public class NotBooleanExpression extends AbstractBooleanExpression {
	public static final String TRANSFORMATION = "-";

	public NotBooleanExpression(InstElement left, String leftAttributeName) {
		super(left, leftAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public NotBooleanExpression(AbstractExpression subExpression) {
		super(null, null, false, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	@Override
	public IntBooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<IntExpression> expressionTerms = expressionTerms(f, idMap);
		return f.not((IntBooleanExpression) expressionTerms.get(0));
	}

	@Override
	public IntBooleanExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap, boolean negateLeft,
			boolean negateRight) {
		List<IntExpression> expressionTerms = expressionTerms(f, idMap);
		return (Identifier) expressionTerms.get(0);
	}

}
