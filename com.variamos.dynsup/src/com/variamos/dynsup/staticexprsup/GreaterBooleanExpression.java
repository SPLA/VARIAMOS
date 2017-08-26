package com.variamos.dynsup.staticexprsup;

import java.util.List;
import java.util.Map;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntExpression;
import com.variamos.hlcl.model.expressions.IntNumericExpression;
import com.variamos.hlcl.model.expressions.NumericIdentifier;

/**
 * Class to create the GreaterOrEquals expression. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-14
 */
public class GreaterBooleanExpression extends AbstractBooleanExpression {
	public static final String TRANSFORMATION = "#>";

	public GreaterBooleanExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public GreaterBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceRight,
			AbstractExpression subExpression) {
		super(vertex, attributeName, replaceRight, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public GreaterBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceRight,
			IntBooleanExpression simpleExpression) {
		super(vertex, attributeName, replaceRight, simpleExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public GreaterBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceRight,
			NumericIdentifier numericIdentifier) {
		super(vertex, attributeName, replaceRight, numericIdentifier);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public GreaterBooleanExpression(
			AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	@Override
	public IntBooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<IntExpression> expressionTerms = expressionTerms(f, idMap);

		return f.greaterThan((IntNumericExpression) expressionTerms.get(0),
				(IntNumericExpression) expressionTerms.get(1));
	}

	@Override
	public IntBooleanExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap, boolean negateLeft,
			boolean negateRight) {
		List<IntExpression> expressionTerms = expressionTermsNegation(f, idMap,
				false, false);

		return f.lessOrEqualsThan((IntNumericExpression) expressionTerms.get(0),
				(IntNumericExpression) expressionTerms.get(1));
	}

}
