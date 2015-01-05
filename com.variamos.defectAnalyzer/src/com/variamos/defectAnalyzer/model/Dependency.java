package com.variamos.defectAnalyzer.model;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.productline.prologEditors.Hlcl2SWIProlog;

public class Dependency implements Cloneable {

	private String originalRelationShipText;
	private Long relationShipNumber;

	private BooleanExpression constraintExpression;

	// Negación de la expresión. Sirve para identificar las redundancias
	// automáticamente en modelos de caraacteristicas. En las otras notaciones
	// si se debe escribir la expresión manualmente
	
	private BooleanExpression negationExpression;


	public Dependency() {
		super();

		constraintExpression = null;
	}

	public Dependency(String originalRelationShipText, Long relationShipNumber) {
		this();
		this.originalRelationShipText = originalRelationShipText;
		this.relationShipNumber = relationShipNumber;
	}

	/**
	 * @return the originalRelationShipText
	 */
	public String getOriginalRelationShipText() {
		return originalRelationShipText;
	}

	/**
	 * @param originalRelationShipText
	 *            the originalRelationShipText to set
	 */
	public void setOriginalRelationShipText(String originalRelationShipText) {
		this.originalRelationShipText = originalRelationShipText;
	}

	/**
	 * @return the relationShipNumber
	 */
	public Long getRelationShipNumber() {
		return relationShipNumber;
	}

	/**
	 * @param relationShipNumber
	 *            the relationShipNumber to set
	 */
	public void setRelationShipNumber(Long relationShipNumber) {
		this.relationShipNumber = relationShipNumber;
	}

	/**
	 * @return the constraintExpression
	 */
	public BooleanExpression getConstraintExpression() {
		return constraintExpression;
	}

	/**
	 * @param constraintExpression
	 *            the constraintExpression to set
	 */
	public void setConstraintExpression(BooleanExpression constraintExpression) {
		this.constraintExpression = constraintExpression;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//return getPrologInstruction(constraintExpression);
		 return originalRelationShipText;
		 
		// return relationShipNumber.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((originalRelationShipText == null) ? 0
						: originalRelationShipText.hashCode());
		result = prime
				* result
				+ ((relationShipNumber == null) ? 0 : relationShipNumber
						.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dependency other = (Dependency) obj;
		if (originalRelationShipText == null) {
			if (other.originalRelationShipText != null)
				return false;
		} else if (!originalRelationShipText
				.equals(other.originalRelationShipText))
			return false;
		if (relationShipNumber == null) {
			if (other.relationShipNumber != null)
				return false;
		} else if (!relationShipNumber.equals(other.relationShipNumber))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	/**
	 * @return the negationExpression
	 */
	public BooleanExpression getNegationExpression() {
		return negationExpression;
	}

	/**
	 * @param negationExpression
	 *            the negationExpression to set
	 */
	public void setNegationExpression(BooleanExpression negationExpression) {
		this.negationExpression = negationExpression;
	}

}
