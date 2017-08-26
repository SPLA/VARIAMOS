package com.variamos.dynsup.staticexprsup;

import java.util.Map;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.hlcl.model.expressions.IntNumericExpression;

/**
 * Abstract  Class to group the NUmericTranformation. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public abstract class AbstractNumericExpression extends AbstractExpression {

	public AbstractNumericExpression() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AbstractNumericExpression(
			AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
	}

	public AbstractNumericExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
	}

	public AbstractNumericExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			AbstractExpression subExpression) {
		super(vertex, attributeName, replaceTarget, subExpression);
	}

	public AbstractNumericExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			IntBooleanExpression booleanExpression) {
		super(vertex, attributeName, replaceTarget, booleanExpression);
	}
	
	public AbstractNumericExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			IntNumericExpression numericExpression) {
		super(vertex, attributeName, replaceTarget, numericExpression);
	}

	public abstract IntNumericExpression transform(HlclFactory f,
			Map<String, Identifier> idMap);

}
