package com.variamos.defectAnalyzer.dto;

import com.variamos.core.enums.SolverEditorType;
import com.variamos.defectAnalyzer.model.VariabilityModel;


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
