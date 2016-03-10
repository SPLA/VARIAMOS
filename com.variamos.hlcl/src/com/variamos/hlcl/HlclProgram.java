package com.variamos.hlcl;

import java.util.LinkedList;

public class HlclProgram extends LinkedList<BooleanExpression> implements
		BooleanExpression {

	private static final long serialVersionUID = -4199309663392940681L;

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	public boolean isValidExpression() {
		for (BooleanExpression e : this)
			if (e == null || !e.isValidExpression())
				return false;
		return true;
	};

}
