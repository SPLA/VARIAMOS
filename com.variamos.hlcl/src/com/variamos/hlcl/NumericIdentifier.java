package com.variamos.hlcl;

public class NumericIdentifier implements NumericExpression {
	protected int value;

	protected NumericIdentifier(int value) {
		super();
		this.value = value;
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	public boolean isValidExpression() {
		return true;
	};

	public int getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NumericIdentifier [value=" + value + "]";
	}

}
