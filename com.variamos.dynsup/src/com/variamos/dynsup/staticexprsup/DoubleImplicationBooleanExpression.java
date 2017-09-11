package com.variamos.dynsup.staticexprsup;

import java.util.List;
import java.util.Map;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntExpression;
import com.variamos.hlcl.model.expressions.IntNumericExpression;

/**
 * Class to create the DoubleImplication expression. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public class DoubleImplicationBooleanExpression extends
		AbstractBooleanExpression {
	public static final String TRANSFORMATION = "#<==>";

	public DoubleImplicationBooleanExpression(InstElement left,
			InstElement right, String leftAttributeName,
			String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public DoubleImplicationBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			AbstractExpression subExpression) {
		super(vertex, attributeName, replaceTarget, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public DoubleImplicationBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			IntBooleanExpression comparativeExpression) {
		super(vertex, attributeName, replaceTarget, comparativeExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public DoubleImplicationBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			IntNumericExpression comparativeExpression) {
		super(vertex, attributeName, replaceTarget, comparativeExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public DoubleImplicationBooleanExpression(
			AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public DoubleImplicationBooleanExpression() {
		super();
	}

	@Override
	public IntBooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) { 
		List<IntExpression> expressionTerms = expressionTerms(f, idMap);
		return f.doubleImplies((IntBooleanExpression) expressionTerms.get(0),
				(IntBooleanExpression) expressionTerms.get(1));
	}

	@Override
	public IntBooleanExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap, boolean negateLeft,
			boolean negateRight) {
		List<IntExpression> expressionTerms = expressionTermsNegation(f, idMap,
				false, false);
		return f.notEquals((IntBooleanExpression) expressionTerms.get(0),
				(IntBooleanExpression) expressionTerms.get(1));
	}
}
