package com.variamos.dynsup.staticexprsup;

import java.util.List;
import java.util.Map;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntExpression;

/**
 * Class to create the And expression. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public class AndBooleanExpression extends AbstractBooleanExpression {
	public static final String TRANSFORMATION = "#/\\";

	public AndBooleanExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public AndBooleanExpression(InstElement vertex, String attributeName,
			boolean replaceRight, AbstractExpression subExpression) {
		super(vertex, attributeName, replaceRight, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	/*
	 * public AndBooleanTransformation(InstVertex vertex, String attributeName,
	 * boolean replaceRight, BooleanExpression simpleExpression) { super(vertex,
	 * attributeName, replaceRight, simpleExpression);
	 * this.expressionConnectors.add(TRANSFORMATION); }
	 * 
	 * public AndBooleanTransformation(InstVertex vertex, String attributeName,
	 * boolean replaceRight, NumericIdentifier numericIdentifier) {
	 * super(vertex, attributeName, replaceRight, numericIdentifier);
	 * this.expressionConnectors.add(TRANSFORMATION); }
	 */
	public AndBooleanExpression(AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public AndBooleanExpression() {
		operation = TRANSFORMATION;
	}

	@Override
	public IntBooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<IntExpression> expressionTerms = expressionTerms(f, idMap);
		return f.and((IntBooleanExpression) expressionTerms.get(0),
				(IntBooleanExpression) expressionTerms.get(1));
	}

	@Override
	public IntBooleanExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap, boolean negateLeft,
			boolean negateRight) {
		List<IntExpression> expressionTerms = expressionTermsNegation(f, idMap,
				true, true);
		return f.or((IntBooleanExpression) expressionTerms.get(0),
				(IntBooleanExpression) expressionTerms.get(1));
	}

}
