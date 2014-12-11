package com.variamos.defectAnalyzer.model.defects;

import com.cfm.hlcl.Expression;
import com.variamos.defectAnalyzer.model.VariabilityElementDefAna;
import com.variamos.defectAnalyzer.model.enums.DefectType;

public class NonAttainableDomain extends Defect {
	private VariabilityElementDefAna variabilityElementDefAna;
	private Integer notAttainableDomain;

	public NonAttainableDomain(VariabilityElementDefAna variabilityElementDefAna,
			Integer notAttainableDomain) {
		super();
		this.variabilityElementDefAna = variabilityElementDefAna;
		this.id = variabilityElementDefAna.getName();
		this.notAttainableDomain = notAttainableDomain;
		defectType = DefectType.NON_ATTAINABLE_DOMAIN;
	}
	
	public NonAttainableDomain(VariabilityElementDefAna variabilityElementDefAna,
			Integer notAttainableDomain,
			Expression verificationExpression) {
		this(variabilityElementDefAna,notAttainableDomain);
		this.verificationExpression=verificationExpression;
	}


	/**
	 * @return the variabilityElement
	 */
	public VariabilityElementDefAna getVariabilityElement() {
		return variabilityElementDefAna;
	}

	/**
	 * @param variabilityElementDefAna
	 *            the variabilityElement to set
	 */
	public void setVariabilityElement(VariabilityElementDefAna variabilityElementDefAna) {
		this.variabilityElementDefAna = variabilityElementDefAna;
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
