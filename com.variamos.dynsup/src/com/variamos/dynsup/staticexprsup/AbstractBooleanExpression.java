package com.variamos.dynsup.staticexprsup;

import java.util.Map;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntNumericExpression;

/**
 * Abstract Class to group at the BooleanTransformation. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public abstract class AbstractBooleanExpression extends
		AbstractExpression {

	public AbstractBooleanExpression() {
		super();
	}

	public AbstractBooleanExpression(
			AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
	}

	public AbstractBooleanExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
	}

	public AbstractBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			AbstractExpression subExpression) {
		super(vertex, attributeName, replaceTarget, subExpression);
	}

	public AbstractBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			IntBooleanExpression booleanExpression) {
		super(vertex, attributeName, replaceTarget, booleanExpression);
	}
	
	public AbstractBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			IntNumericExpression numericExpression) {
		super(vertex, attributeName, replaceTarget, numericExpression);
	}

	public AbstractBooleanExpression(InstElement left,
			String leftAttributeName) {
		super(left, leftAttributeName);
	}

	public abstract IntBooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap);
	
	public abstract IntBooleanExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap, boolean negateLeft, boolean negateRight);

}
