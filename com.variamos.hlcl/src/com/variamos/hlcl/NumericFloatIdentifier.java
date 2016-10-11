package com.variamos.hlcl;

// jcmunoz added class to support floats
public class NumericFloatIdentifier implements NumericExpression {
	protected float floatValue;

	protected NumericFloatIdentifier(float value) {
		super();
		this.floatValue = value;
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	@Override
	public boolean isValidExpression() {
		return true;
	};

	public float getValue() {
		return floatValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NumericIdentifier [value=" + floatValue + "]";
	}
}
