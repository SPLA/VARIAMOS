package com.cfm.hlcl;

public class BooleanNegation implements BooleanExpression{
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

	public void setExpression(BooleanExpression expression) {
		this.expression = expression;
	}
}
