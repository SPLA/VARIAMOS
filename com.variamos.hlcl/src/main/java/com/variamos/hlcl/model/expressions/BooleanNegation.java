package com.variamos.hlcl.model.expressions;

public class BooleanNegation implements IntBooleanExpression {
	protected IntBooleanExpression expression;

	BooleanNegation() {
		super();
	}

	BooleanNegation(IntBooleanExpression expression) {
		super();
		this.expression = expression;
	}

	public IntBooleanExpression getExpression() {
		return expression;
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	@Override
	public boolean isValidExpression() {
		if (expression == null)
			return false;
		else if (!expression.isValidExpression())
			return false;
		return true;
	};

	public void setExpression(IntBooleanExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "BooleanOperation [expression=" + expression + ", operator="
				+ "negation" + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof BooleanNegation)
			if (expression.equals(obj))
				return true;
			else
				return false;
		else
			return false;
	}

	@Override
	public int hashCode() {
		return expression.hashCode();
	}
}
