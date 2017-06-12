package com.variamos.hlcl;

public class ComparisonExpression implements BooleanExpression {

	protected Expression left, right;
	protected ComparisonType type;

	protected ComparisonExpression() {
		super();
	}

	protected ComparisonExpression(Expression left, Expression right,
			ComparisonType type) {
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

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public Expression getRight() {
		return right;
	}

	public void setRight(NumericOperation right) {
		this.right = right;
	}

	public ComparisonType getType() {
		return type;
	}

	public void setType(ComparisonType type) {
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
