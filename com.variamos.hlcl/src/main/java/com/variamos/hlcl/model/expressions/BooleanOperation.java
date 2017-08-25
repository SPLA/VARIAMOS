package com.variamos.hlcl.model.expressions;

import com.variamos.hlcl.model.BooleanOperatorEnum;

public class BooleanOperation implements IntBooleanExpression {
	protected IntBooleanExpression left, right;
	protected BooleanOperatorEnum operator;

	BooleanOperation() {
		super();
	}

	BooleanOperation(IntBooleanExpression left, IntBooleanExpression right,
			BooleanOperatorEnum operator) {
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

	public IntBooleanExpression getLeft() {
		return left;
	}

	public void setLeft(IntBooleanExpression left) {
		this.left = left;
	}

	public IntBooleanExpression getRight() {
		return right;
	}

	public void setRight(IntBooleanExpression right) {
		this.right = right;
	}

	public BooleanOperatorEnum getOperator() {
		return operator;
	}

	public void setOperator(BooleanOperatorEnum operator) {
		this.operator = operator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BooleanOperation [left=" + left + ", right=" + right
				+ ", operator=" + operator + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof BooleanOperation) {
			BooleanOperation bE = (BooleanOperation) obj;
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
