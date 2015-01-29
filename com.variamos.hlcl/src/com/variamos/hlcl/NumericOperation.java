package com.variamos.hlcl;

public class NumericOperation implements NumericExpression{
	
	protected NumericExpression left, right;
	protected NumericOperator operator;
	
	protected NumericOperation() {
		super();
	}
	
	protected NumericOperation(NumericExpression left, NumericExpression right,
			NumericOperator operator) {
		super();
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
	
	public NumericExpression getLeft() {
		return left;
	}
	public void setLeft(NumericExpression left) {
		this.left = left;
	}
	public NumericExpression getRight() {
		return right;
	}
	public void setRight(NumericExpression right) {
		this.right = right;
	}
	public NumericOperator getOperator() {
		return operator;
	}
	public void setOperator(NumericOperator operator) {
		this.operator = operator;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NumericOperation [left=" + left + ", right=" + right
				+ ", operator=" + operator + "]";
	}
	
	
	
}
