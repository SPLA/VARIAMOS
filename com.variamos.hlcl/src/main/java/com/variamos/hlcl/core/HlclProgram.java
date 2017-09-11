package com.variamos.hlcl.core;

import java.util.LinkedList;

import com.variamos.hlcl.model.expressions.IntBooleanExpression;

public class HlclProgram extends LinkedList<IntBooleanExpression> implements
		IntBooleanExpression {

	private static final long serialVersionUID = -4199309663392940681L;

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	@Override
	public boolean isValidExpression() {
		for (IntBooleanExpression e : this)
			if (e == null || !e.isValidExpression())
				return false;
		return true;
	};

}
