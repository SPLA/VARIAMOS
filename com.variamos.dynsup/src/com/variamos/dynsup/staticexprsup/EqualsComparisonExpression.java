package com.variamos.dynsup.staticexprsup;

import java.util.List;
import java.util.Map;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.hlcl.model.expressions.ComparisonExpression;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntExpression;
import com.variamos.hlcl.model.expressions.IntNumericExpression;
import com.variamos.hlcl.model.expressions.NumericIdentifier;

/**
 * Class to create the Equals expression. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-14
 */
public class EqualsComparisonExpression extends AbstractComparisonExpression {
	public static final String TRANSFORMATION = "#=";

	public EqualsComparisonExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public EqualsComparisonExpression(InstElement vertex, String attributeName,
			boolean replaceRight, AbstractExpression subExpression) {
		super(vertex, attributeName, replaceRight, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public EqualsComparisonExpression(InstElement vertex, String attributeName,
			boolean replaceRight, IntBooleanExpression simpleExpression) {
		super(vertex, attributeName, replaceRight, simpleExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public EqualsComparisonExpression(InstElement vertex, String attributeName,
			boolean replaceRight, NumericIdentifier numericIdentifier) {
		super(vertex, attributeName, replaceRight, numericIdentifier);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public EqualsComparisonExpression(InstElement left, String attributeName,
			IntNumericExpression numericExpression) {
		super(left, attributeName, true, numericExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public EqualsComparisonExpression(IntNumericExpression numericExpression1,
			boolean replaceRight, IntNumericExpression numericExpression2) {
		super(numericExpression1, replaceRight, numericExpression2);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public EqualsComparisonExpression(AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	@Override
	public IntBooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<IntExpression> expressionTerms = expressionTerms(f, idMap);
		return f.equals((IntExpression) expressionTerms.get(0),
				(IntExpression) expressionTerms.get(1));
		// return f.equals( (BooleanExpression)expressionTerms.get(0),
		// (BooleanExpression)expressionTerms.get(1));
	}

	@Override
	public ComparisonExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<IntExpression> expressionTerms = expressionTermsNegation(f, idMap,
				false, false);
		return f.notEquals((IntExpression) expressionTerms.get(0),
				(IntExpression) expressionTerms.get(1));
		// return f.equals( (BooleanExpression)expressionTerms.get(0),
		// (BooleanExpression)expressionTerms.get(1));
	}

}
