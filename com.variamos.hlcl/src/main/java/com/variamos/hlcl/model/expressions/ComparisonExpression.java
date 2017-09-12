package com.variamos.hlcl.model.expressions;

import com.variamos.hlcl.model.ComparisonTypeEnum;

public class ComparisonExpression implements IntBooleanExpression {

	protected IntExpression left, right;
	protected ComparisonTypeEnum type;

	ComparisonExpression() {
		super();
	}

	ComparisonExpression(IntExpression left, IntExpression right,
			ComparisonTypeEnum type) {
		super();
		this.left = left;
		this.right = right;
		this.type = type;
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	@Override
	public boolean isValidExpression() {
		if (left == null || right == null)
			return false;
		else if (!left.isValidExpression())
			return false;
		else if (!right.isValidExpression())
			return false;
		return true;
	};

	public IntExpression getLeft() {
		return left;
	}

	public void setLeft(IntExpression left) {
		this.left = left;
	}

	public IntExpression getRight() {
		return right;
	}

	public void setRight(NumericOperation right) {
		this.right = right;
	}

	public ComparisonTypeEnum getType() {
		return type;
	}

	public void setType(ComparisonTypeEnum type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComparisonExpression [left=" + left + ", right=" + right
				+ ", type=" + type + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof ComparisonExpression) {
			ComparisonExpression bE = (ComparisonExpression) obj;
			if (left.equals(bE.getLeft()) && right.equals(bE.getRight())
					&& type.equals(bE.getType()))
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public int hashCode() {
		String out = (left + "#" + right + "#" + type);
		return out.hashCode();
	}

}
