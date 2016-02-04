package com.variamos.hlcl;

public class BooleanNegation implements BooleanExpression {
	protected BooleanExpression expression;

	protected BooleanNegation() {
		super();
	}

	protected BooleanNegation(BooleanExpression expression) {
		super();
		this.expression = expression;
	}

	public BooleanExpression getExpression() {
		return expression;
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	public boolean isValidExpression() {
		if (expression == null)
			return false;
		else if (!expression.isValidExpression())
			return false;
		return true;
	};

	public void setExpression(BooleanExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "BooleanOperation [expression=" + expression + ", operator="
				+ "negation" + "]";
	}
}
