package com.variamos.reasoning.defectAnalyzer.model.dto;

import com.variamos.common.model.enums.NotationType;
import com.variamos.common.model.enums.SolverEditorType;

public class VMTransformerInDTO {
	//Para transformar desde formato splot o Prolog
	private String pathToTransform;
	
		
	//Para todos los tipos
	private NotationType notationType;
	
	
	//Para transformar desde Prolog Indica en que notación esta el modelo a transformar
	private SolverEditorType prologEditorTypeFileToTransform;
		
	/**
	 * @return the pathToTransform
	 */
	public String getPathToTransform() {
		return pathToTransform;
	}
	/**
	 * @param pathToTransform the pathToTransform to set
	 */
	public void setPathToTransform(String pathToTransform) {
		this.pathToTransform = pathToTransform;
	}
	/**
	 * @return the notationType
	 */
	public NotationType getNotationType() {
		return notationType;
	}
	/**
	 * @param notationType the notationType to set
	 */
	public void setNotationType(NotationType notationType) {
		this.notationType = notationType;
	}
	
	
	
	/**
	 * @return the prologEditorTypeFileToTransform
	 */
	public SolverEditorType getPrologEditorTypeFileToTransform() {
		return prologEditorTypeFileToTransform;
	}
	/**
	 * @param prologEditorTypeFileToTransform the prologEditorTypeFileToTransform to set
	 */
	public void setPrologEditorTypeFileToTransform(
			SolverEditorType prologEditorTypeFileToTransform) {
		this.prologEditorTypeFileToTransform = prologEditorTypeFileToTransform;
	}
	
}
