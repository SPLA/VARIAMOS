package com.variamos.hlcl.model.expressions;

// jcmunoz added class to support floats
public class NumericFloatIdentifier implements IntNumericExpression {
	protected float floatValue;

	NumericFloatIdentifier(float value) {
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof NumericFloatIdentifier) {
			NumericFloatIdentifier bE = (NumericFloatIdentifier) obj;
			if (floatValue == bE.getValue())
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public int hashCode() {
		return (int) floatValue * 1000;
	}
}
