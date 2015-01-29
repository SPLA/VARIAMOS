package com.variamos.semantic.expressions;

import java.util.List;
import java.util.Map;

import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.Expression;
import com.variamos.hlcl.HlclFactory;
import com.variamos.hlcl.Identifier;
import com.variamos.hlcl.NumericExpression;
import com.variamos.hlcl.NumericIdentifier;
import com.variamos.syntax.instancesupport.InstElement;

/**
 * Class to create the GreaterOrEquals expression. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-14
 */
public class GreaterOrEqualsBooleanExpression extends
		AbstractBooleanExpression {
	public static final String TRANSFORMATION = "#>=";

	public GreaterOrEqualsBooleanExpression(InstElement left,
			InstElement right, String leftAttributeName,
			String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public GreaterOrEqualsBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceRight,
			AbstractExpression subExpression) {
		super(vertex, attributeName, replaceRight, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public GreaterOrEqualsBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceRight,
			BooleanExpression simpleExpression) {
		super(vertex, attributeName, replaceRight, simpleExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public GreaterOrEqualsBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceRight,
			NumericIdentifier numericIdentifier) {
		super(vertex, attributeName, replaceRight, numericIdentifier);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public GreaterOrEqualsBooleanExpression(
			AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	@Override
	public BooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<Expression> expressionTerms = expressionTerms(f, idMap);

		return f.greaterOrEqualsThan(
				(NumericExpression) expressionTerms.get(0),
				(NumericExpression) expressionTerms.get(1));
	}

	@Override
	public BooleanExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap, boolean negateLeft,
			boolean negateRight) {
		List<Expression> expressionTerms = expressionTermsNegation(f, idMap,
				false, false);

		return f.lessThan((NumericExpression) expressionTerms.get(0),
				(NumericExpression) expressionTerms.get(1));
	}

}
