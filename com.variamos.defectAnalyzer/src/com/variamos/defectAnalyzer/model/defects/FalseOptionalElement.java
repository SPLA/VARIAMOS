package com.variamos.defectAnalyzer.model.defects;

import com.cfm.hlcl.Expression;
import com.variamos.defectAnalyzer.model.VariabilityElementDefAna;
import com.variamos.defectAnalyzer.model.enums.DefectType;


public class FalseOptionalElement extends Defect {

	private VariabilityElementDefAna falseOptionalElement;

	public FalseOptionalElement(VariabilityElementDefAna falseOptionalElement) {
		super();
		this.falseOptionalElement = falseOptionalElement;
		defectType=DefectType.FALSE_OPTIONAL_FEATURE;
		id=falseOptionalElement.getName();
	}
	
	public FalseOptionalElement(VariabilityElementDefAna falseOptionalElement,
			Expression verificationExpression) {
		this(falseOptionalElement);
		this.verificationExpression=verificationExpression;
	}

	
	
	/**
	 * @return the falseOptionalElement
	 */
	public VariabilityElementDefAna getFalseOptionalElement() {
		return falseOptionalElement;
	}

	/**
	 * @param falseOptionalElement the falseOptionalElement to set
	 */
	public void setFalseOptionalElement(VariabilityElementDefAna falseOptionalElement) {
		this.falseOptionalElement = falseOptionalElement;
	}



	
}
