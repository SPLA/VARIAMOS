package com.cfm.hlcl;

public class ComparisonExpression implements BooleanExpression{
	
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ComparisonExpression [left=" + left + ", right=" + right
				+ ", type=" + type + "]";
	}
	
	

}
