package com.variamos.defectAnalyzer.model.defects;

import com.variamos.defectAnalyzer.model.VariabilityModel;
import com.variamos.defectAnalyzer.model.enums.DefectType;


public class FalseProductLine extends Defect {

	private VariabilityModel variabilityModel;
	
	

	public FalseProductLine(VariabilityModel variabilityModel) {
		super();
		this.variabilityModel = variabilityModel;
		defectType=DefectType.FALSE_PRODUCT_LINE;
		this.id=variabilityModel.getModelName();
	}

	/**
	 * @return the variabilityModel
	 */
	public VariabilityModel getVariabilityModel() {
		return variabilityModel;
	}

	/**
	 * @param variabilityModel the variabilityModel to set
	 */
	public void setVariabilityModel(VariabilityModel variabilityModel) {
		this.variabilityModel = variabilityModel;
	}
}
