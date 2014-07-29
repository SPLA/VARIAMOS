package com.cfm.productline.dto;

import com.cfm.productline.model.defectAnalyzerModel.VariabilityModel;
import com.cfm.productline.model.enums.SolverEditorType;


public class VMAnalyzerInDTO {
 
	protected SolverEditorType prologEditorType;
	protected VariabilityModel variabilityModel;

	/**
	 * @return the prologEditorType
	 */
	public SolverEditorType getPrologEditorType() {
		return prologEditorType;
	}
	/**
	 * @param prologEditorType the prologEditorType to set
	 */
	public void setPrologEditorType(SolverEditorType prologEditorType) {
		this.prologEditorType = prologEditorType;
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
