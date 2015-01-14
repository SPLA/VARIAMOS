package com.variamos.defectAnalyzer.model.defects;

import com.variamos.defectAnalyzer.model.VariabilityModel;
import com.variamos.defectAnalyzer.model.enums.DefectType;

public class FalseProductLine extends Defect {

	private VariabilityModel variabilityModel;
	private boolean isFalsePL;

	public FalseProductLine(boolean isFalsePL) {
		super();
		this.isFalsePL = isFalsePL;
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
