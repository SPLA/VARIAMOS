package com.variamos.reasoning.defectAnalyzer.model.defects;

import com.variamos.hlcl.BooleanExpression;
import com.variamos.reasoning.defectAnalyzer.model.VariabilityModel;
import com.variamos.reasoning.defectAnalyzer.model.enums.DefectType;

public class FalseProductLine extends Defect {

	private VariabilityModel variabilityModel;
	private boolean isFalsePL;

	public FalseProductLine() {
		super();
		this.isFalsePL = Boolean.TRUE;
		defectType = DefectType.FALSE_PRODUCT_LINE;
		id = "FalsePL: ";
	}

	// jcmunoz: added to support false product lines analysis for additional
	// expressions
	// to support the homogeneity analysis operation and other similar
	public FalseProductLine(BooleanExpression additionalExpression) {
		super(additionalExpression);
		this.isFalsePL = Boolean.TRUE;

		defectType = DefectType.FALSE_PRODUCT_LINE;
		id = "FalsePL: " + additionalExpression.toString();
	}

	public boolean isFalsePL() {
		return isFalsePL;
	}

	public void setFalsePL(boolean isFalsePL) {
		this.isFalsePL = isFalsePL;
	}

	public FalseProductLine(VariabilityModel variabilityModel) {
		super();
		this.variabilityModel = variabilityModel;
		defectType = DefectType.FALSE_PRODUCT_LINE;

	}

	/**
	 * @return the variabilityModel
	 */
	@Deprecated
	public VariabilityModel getVariabilityModel() {
		return variabilityModel;
	}

	/**
	 * @param variabilityModel
	 *            the variabilityModel to set
	 */
	public void setVariabilityModel(VariabilityModel variabilityModel) {
		this.variabilityModel = variabilityModel;
	}
}
