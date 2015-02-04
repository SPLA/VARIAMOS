package com.variamos.defectAnalyzer.model.defects;

import com.cfm.hlcl.Expression;
import com.variamos.defectAnalyzer.model.VariabilityElementDefAna;
import com.variamos.defectAnalyzer.model.enums.DefectType;

public class DeadElement extends Defect {

	private VariabilityElementDefAna deadElement;

	public DeadElement(VariabilityElementDefAna deadElement) {
		super();
		this.deadElement = deadElement;
		this.id = deadElement.getName();
		defectType = DefectType.DEAD_FEATURE;

	}

	public DeadElement(VariabilityElementDefAna deadElement,
			Expression verificationExpression) {
		this(deadElement);
		this.verificationExpression=verificationExpression;
	}

	/**
	 * @return the deadElement
	 */
	public VariabilityElementDefAna getDeadElement() {
		return deadElement;
	}

	/**
	 * @param deadElement
	 *            the deadElement to set
	 */
	public void setDeadElement(VariabilityElementDefAna deadElement) {
		this.deadElement = deadElement;
	}

}
