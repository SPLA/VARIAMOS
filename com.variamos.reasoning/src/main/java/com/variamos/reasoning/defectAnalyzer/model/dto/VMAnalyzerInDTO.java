package com.variamos.reasoning.defectAnalyzer.model.dto;

import com.variamos.common.model.enums.SolverEditorType;
import com.variamos.reasoning.defectAnalyzer.model.transformation.VariabilityModel;


public class VMAnalyzerInDTO {
 
	protected SolverEditorType solverEditorType;
	protected VariabilityModel variabilityModel;

	/**
	 * @return the prologEditorType
	 */
	public SolverEditorType getSolverEditorType() {
		return solverEditorType;
	}
	/**
	 * @param solverEditorType the prologEditorType to set
	 */
	public void setSolverEditorType(SolverEditorType solverEditorType) {
		this.solverEditorType = solverEditorType;
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
