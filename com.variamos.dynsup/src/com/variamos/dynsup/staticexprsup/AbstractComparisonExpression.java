package com.variamos.dynsup.staticexprsup;

import java.util.Map;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.hlcl.model.expressions.ComparisonExpression;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntNumericExpression;

/**
 * Abstract Class to group at the ComparisonTransformation, currently only for
 * Equals. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public abstract class AbstractComparisonExpression extends AbstractExpression {

	public AbstractComparisonExpression() {
		super();
	}

	public AbstractComparisonExpression(AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
	}

	public AbstractComparisonExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
	}

	public AbstractComparisonExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			AbstractExpression subExpression) {
		super(vertex, attributeName, replaceTarget, subExpression);
	}

	public AbstractComparisonExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			IntBooleanExpression booleanExpression) {
		super(vertex, attributeName, replaceTarget, booleanExpression);
	}

	public AbstractComparisonExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			IntNumericExpression numericExpression) {
		super(vertex, attributeName, replaceTarget, numericExpression);
	}

	public AbstractComparisonExpression(IntNumericExpression numericExpression1,
			boolean replaceTarget, IntNumericExpression numericExpression2) {
		super(numericExpression1, replaceTarget, numericExpression2);
	}

	public abstract IntBooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap);

	public abstract ComparisonExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap);

}
