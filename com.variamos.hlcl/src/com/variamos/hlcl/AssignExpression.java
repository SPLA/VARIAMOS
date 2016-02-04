package com.variamos.hlcl;

public class AssignExpression implements BooleanExpression {
	private Identifier identifier;
	private Expression rightExpression;

	public AssignExpression(Identifier identifier, Expression rightExpression) {
		super();
		this.identifier = identifier;
		this.rightExpression = rightExpression;
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	public boolean isValidExpression() {
		if (identifier == null || rightExpression == null)
			return false;
		else if (!rightExpression.isValidExpression())
			return false;
		return true;
	};

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public Expression getRightExpression() {
		return rightExpression;
	}

	public void setRightExpression(Expression rightExpression) {
		this.rightExpression = rightExpression;
	}

	@Override
	public String toString() {
		return "AssignExpression [left=" + identifier + ", right="
				+ rightExpression + ", operator=" + "assign" + "]";
	}

}
