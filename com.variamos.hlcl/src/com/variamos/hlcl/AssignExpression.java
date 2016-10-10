package com.variamos.hlcl;

public class AssignExpression implements BooleanExpression {
	private Identifier identifier;
	private Expression rightExpression;
	protected AssignType type;

	public AssignType getType() {
		return type;
	}

	public void setType(AssignType type) {
		this.type = type;
	}

	public AssignExpression(Identifier identifier, Expression rightExpression) {
		super();
		this.identifier = identifier;
		this.rightExpression = rightExpression;
		this.type = AssignType.Assign;
	}

	public AssignExpression(Identifier identifier, Expression rightExpression,
			AssignType type) {
		super();
		this.identifier = identifier;
		this.rightExpression = rightExpression;
		this.type = type;
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	@Override
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
