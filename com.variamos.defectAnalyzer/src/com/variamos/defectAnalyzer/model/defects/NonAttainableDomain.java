package com.variamos.defectAnalyzer.model.defects;

import com.cfm.hlcl.Expression;
import com.variamos.defectAnalyzer.model.VariabilityElement;
import com.variamos.defectAnalyzer.model.enums.DefectType;

public class NonAttainableDomain extends Defect {
	private VariabilityElement variabilityElement;
	private Integer notAttainableDomain;

	public NonAttainableDomain(VariabilityElement variabilityElement,
			Integer notAttainableDomain) {
		super();
		this.variabilityElement = variabilityElement;
		this.id = variabilityElement.getName();
		this.notAttainableDomain = notAttainableDomain;
		defectType = DefectType.NON_ATTAINABLE_DOMAIN;
	}
	
	public NonAttainableDomain(VariabilityElement variabilityElement,
			Integer notAttainableDomain,
			Expression verificationExpression) {
		this(variabilityElement,notAttainableDomain);
		this.verificationExpression=verificationExpression;
	}


	/**
	 * @return the variabilityElement
	 */
	public VariabilityElement getVariabilityElement() {
		return variabilityElement;
	}

	/**
	 * @param variabilityElement
	 *            the variabilityElement to set
	 */
	public void setVariabilityElement(VariabilityElement variabilityElement) {
		this.variabilityElement = variabilityElement;
	}

	/**
	 * @return the notAttainableDomain
	 */
	public Integer getNotAttainableDomain() {
		return notAttainableDomain;
	}

	/**
	 * @param notAttainableDomain
	 *            the notAttainableDomain to set
	 */
	public void setNotAttainableDomain(Integer notAttainableDomain) {
		this.notAttainableDomain = notAttainableDomain;
	}

}
