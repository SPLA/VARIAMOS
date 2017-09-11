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
 * Class to create the Prod expression. Part of PhD work at University of Paris
 * 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public class ProdNumericExpression extends AbstractNumericExpression {
	public static final String TRANSFORMATION = "*";

	public ProdNumericExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public ProdNumericExpression(InstElement vertex, String attributeName,
			boolean replaceRight, AbstractExpression subExpression) {
		super(vertex, attributeName, replaceRight, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public ProdNumericExpression(InstElement vertex, String attributeName,
			boolean replaceRight, IntBooleanExpression simpleExpression) {
		super(vertex, attributeName, replaceRight, simpleExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public ProdNumericExpression(InstElement vertex, String attributeName,
			boolean replaceRight, NumericIdentifier numericIdentifier) {
		super(vertex, attributeName, replaceRight, numericIdentifier);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public ProdNumericExpression(AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	@Override
	public IntNumericExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<IntExpression> expressionTerms = expressionTerms(f, idMap);
		return f.prod((IntNumericExpression) expressionTerms.get(0),
				(IntNumericExpression) expressionTerms.get(1));
	}

}
