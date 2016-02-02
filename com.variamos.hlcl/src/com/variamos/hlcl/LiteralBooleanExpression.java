package com.variamos.hlcl;

import java.util.ArrayList;
import java.util.List;

public class LiteralBooleanExpression implements BooleanExpression {

	private String prologConstraint;
	List<Identifier> identifierExpressionList;

	public LiteralBooleanExpression() {
		identifierExpressionList = new ArrayList<Identifier>();
	}

	public LiteralBooleanExpression(String prologConstraint) {
		this();
		// If it has , at the end of the expression, it is removed
		String constraint = prologConstraint;
		if (prologConstraint.endsWith(",")) {
			constraint = constraint.substring(0, constraint.length() - 1);
		}
		this.prologConstraint = constraint;

	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	public boolean isValidExpression() {
		if (prologConstraint == null)
			return false;
		// TODO evaluate if is a valid constraint
		return true;
	};

	/**
	 * @return the prologConstraint
	 */
	public String getPrologConstraint() {
		return prologConstraint;
	}

	/**
	 * @param prologConstraint
	 *            the prologConstraint to set
	 */
	public void setPrologConstraint(String prologConstraint) {
		this.prologConstraint = prologConstraint;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LiteralBooleanExpression [prologConstraint=" + prologConstraint
				+ "]";
	}

	/**
	 * @return the identifierExpressionList
	 */
	public List<Identifier> getIdentifierExpressionList() {
		return identifierExpressionList;
	}

	/**
	 * @param identifierExpressionList
	 *            the identifierExpressionList to set
	 */
	public void setIdentifierExpressionList(
			List<Identifier> identifierExpressionList) {
		this.identifierExpressionList = identifierExpressionList;
	}

}
