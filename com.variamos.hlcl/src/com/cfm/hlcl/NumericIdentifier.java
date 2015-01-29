package com.cfm.hlcl;

public class NumericIdentifier implements NumericExpression {
	protected int value;

	protected NumericIdentifier(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NumericIdentifier [value=" + value + "]";
	}
	
}
