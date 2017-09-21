package com.variamos.hlcl.model.expressions;

public class NumericIdentifier implements IntNumericExpression {
	protected int intValue;
	protected float floatValue;

	NumericIdentifier(int value) {
		super();
		this.intValue = value;
	}

	NumericIdentifier(float value) {
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

	public int getValue() {
		return intValue;
	}

	public float getFloatValue() {
		return floatValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NumericIdentifier [value=" + intValue + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof NumericIdentifier) {
			NumericIdentifier bE = (NumericIdentifier) obj;
			if (intValue == bE.getValue() && floatValue == bE.getFloatValue())
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public int hashCode() {
		return intValue + (int) (floatValue * 1000);
	}

	public String toFloatString() {
		return "NumericIdentifier [value=" + floatValue + "]";
	}

}
