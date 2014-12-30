package com.variamos.refas.core.expressions;

import java.util.List;
import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.ComparisonExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.cfm.hlcl.NumericIdentifier;
import com.variamos.refas.core.simulationmodel.AbstractComparisonTransformation;
import com.variamos.refas.core.simulationmodel.AbstractTransformation;
import com.variamos.syntaxsupport.metamodel.InstElement;

/**
 * Class to create the Equals expression. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-14
 */
public class EqualsComparisonExpression extends
		AbstractComparisonTransformation {
	public static final String TRANSFORMATION = "#=";

	public EqualsComparisonExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public EqualsComparisonExpression(InstElement vertex,
			String attributeName, boolean replaceRight,
			AbstractTransformation subExpression) {
		super(vertex, attributeName, replaceRight, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public EqualsComparisonExpression(InstElement vertex,
			String attributeName, boolean replaceRight,
			BooleanExpression simpleExpression) {
		super(vertex, attributeName, replaceRight, simpleExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public EqualsComparisonExpression(InstElement vertex,
			String attributeName, boolean replaceRight,
			NumericIdentifier numericIdentifier) {
		super(vertex, attributeName, replaceRight, numericIdentifier);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public EqualsComparisonExpression(InstElement left,
			String attributeName, NumericExpression numericExpression) {
		super(left, attributeName, true, numericExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public EqualsComparisonExpression(
			AbstractTransformation leftSubExpression,
			AbstractTransformation rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	@Override
	public ComparisonExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<Expression> expressionTerms = expressionTerms(f, idMap);
		return f.equals((Expression) expressionTerms.get(0),
				(Expression) expressionTerms.get(1));
		// return f.equals( (BooleanExpression)expressionTerms.get(0),
		// (BooleanExpression)expressionTerms.get(1));
	}

	@Override
	public ComparisonExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<Expression> expressionTerms = expressionTermsNegation(f, idMap,
				false, false);
		return f.notEquals((Expression) expressionTerms.get(0),
				(Expression) expressionTerms.get(1));
		// return f.equals( (BooleanExpression)expressionTerms.get(0),
		// (BooleanExpression)expressionTerms.get(1));
	}

}
