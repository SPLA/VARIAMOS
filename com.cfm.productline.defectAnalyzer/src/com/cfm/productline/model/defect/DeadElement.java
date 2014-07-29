package com.cfm.productline.model.defect;

import com.cfm.hlcl.Expression;
import com.cfm.productline.model.defectAnalyzerModel.VariabilityElement;
import com.cfm.productline.model.enums.DefectType;

public class DeadElement extends Defect {

	private VariabilityElement deadElement;

	public DeadElement(VariabilityElement deadElement) {
		super();
		this.deadElement = deadElement;
		this.id = deadElement.getName();
		defectType = DefectType.DEAD_FEATURE;

	}

	public DeadElement(VariabilityElement deadElement,
			Expression verificationExpression) {
		this(deadElement);
		this.verificationExpression=verificationExpression;
	}

	/**
	 * @return the deadElement
	 */
	public VariabilityElement getDeadElement() {
		return deadElement;
	}

	/**
	 * @param deadElement
	 *            the deadElement to set
	 */
	public void setDeadElement(VariabilityElement deadElement) {
		this.deadElement = deadElement;
	}

}
