package com.variamos.reasoning.defectAnalyzer.model.defects;

import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.reasoning.defectAnalyzer.model.transformation.VariabilityModel;

public class FalseProductLine extends Defect {

	private VariabilityModel variabilityModel;
	private boolean isFalsePL;

	public FalseProductLine() {
		super();
		this.isFalsePL = Boolean.TRUE;
		defectType = DefectTypeEnum.FALSE_PRODUCT_LINE;
		id = "FalsePL: ";
	}

	// jcmunoz: added to support false product lines analysis for additional
	// expressions
	// to support the homogeneity analysis operation and other similar
	public FalseProductLine(IntBooleanExpression additionalExpression) {
		super(additionalExpression);
		this.isFalsePL = Boolean.TRUE;

		defectType = DefectTypeEnum.FALSE_PRODUCT_LINE;
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
		defectType = DefectTypeEnum.FALSE_PRODUCT_LINE;

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
