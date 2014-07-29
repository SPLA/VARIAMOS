package com.cfm.hlcl;

public class BooleanOperation implements BooleanExpression{
	protected BooleanExpression left, right;
	protected BooleanOperator operator;
	
	protected BooleanOperation() {
		super();
	}

	protected BooleanOperation(BooleanExpression left, BooleanExpression right,
			BooleanOperator operator) {
		super();
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	
	public BooleanExpression getLeft() {
		return left;
	}
	public void setLeft(BooleanExpression left) {
		this.left = left;
	}
	public BooleanExpression getRight() {
		return right;
	}
	public void setRight(BooleanExpression right) {
		this.right = right;
	}
	public BooleanOperator getOperator() {
		return operator;
	}
	public void setOperator(BooleanOperator operator) {
		this.operator = operator;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BooleanOperation [left=" + left + ", right=" + right
				+ ", operator=" + operator + "]";
	}
	
	
}
