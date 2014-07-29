package com.cfm.productline.model.defect;

import com.cfm.productline.model.defectAnalyzerModel.VariabilityModel;
import com.cfm.productline.model.enums.DefectType;


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
