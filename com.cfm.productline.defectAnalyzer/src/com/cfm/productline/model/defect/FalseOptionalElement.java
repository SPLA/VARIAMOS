package com.cfm.productline.model.defect;

import com.cfm.hlcl.Expression;
import com.cfm.productline.model.defectAnalyzerModel.VariabilityElement;
import com.cfm.productline.model.enums.DefectType;


public class FalseOptionalElement extends Defect {

	private VariabilityElement falseOptionalElement;

	public FalseOptionalElement(VariabilityElement falseOptionalElement) {
		super();
		this.falseOptionalElement = falseOptionalElement;
		defectType=DefectType.FALSE_OPTIONAL_FEATURE;
		id=falseOptionalElement.getName();
	}
	
	public FalseOptionalElement(VariabilityElement falseOptionalElement,
			Expression verificationExpression) {
		this(falseOptionalElement);
		this.verificationExpression=verificationExpression;
	}

	
	
	/**
	 * @return the falseOptionalElement
	 */
	public VariabilityElement getFalseOptionalElement() {
		return falseOptionalElement;
	}

	/**
	 * @param falseOptionalElement the falseOptionalElement to set
	 */
	public void setFalseOptionalElement(VariabilityElement falseOptionalElement) {
		this.falseOptionalElement = falseOptionalElement;
	}



	
}
