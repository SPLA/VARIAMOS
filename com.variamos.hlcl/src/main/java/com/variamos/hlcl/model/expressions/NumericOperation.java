package com.variamos.hlcl.model.expressions;

import com.variamos.hlcl.model.NumericOperatorEnum;

public class NumericOperation implements IntNumericExpression {

	protected IntNumericExpression left, right;
	protected NumericOperatorEnum operator;

	NumericOperation() {
		super();
	}

	NumericOperation(IntNumericExpression left, IntNumericExpression right,
			NumericOperatorEnum operator) {
		super();
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	@Override
	public boolean isValidExpression() {
		if (left == null || right == null || operator == null)
			return false;
		else if (!left.isValidExpression())
			return false;
		else if (!right.isValidExpression())
			return false;
		return true;
	};

	public IntNumericExpression getLeft() {
		return left;
	}

	public void setLeft(IntNumericExpression left) {
		this.left = left;
	}

	public IntNumericExpression getRight() {
		return right;
	}

	public void setRight(IntNumericExpression right) {
		this.right = right;
	}

	public NumericOperatorEnum getOperator() {
		return operator;
	}

	public void setOperator(NumericOperatorEnum operator) {
		this.operator = operator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NumericOperation [left=" + left + ", right=" + right
				+ ", operator=" + operator + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof NumericOperation) {
			NumericOperation bE = (NumericOperation) obj;
			if (left.equals(bE.getLeft()) && right.equals(bE.getRight())
					&& operator.equals(bE.getOperator()))
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public int hashCode() {
		String out = (left + "#" + right + "#" + operator);
		return out.hashCode();
	}

}
