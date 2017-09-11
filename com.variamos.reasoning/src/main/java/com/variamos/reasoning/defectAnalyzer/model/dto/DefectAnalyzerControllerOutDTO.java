package com.variamos.reasoning.defectAnalyzer.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.variamos.reasoning.defectAnalyzer.model.diagnosis.ClassifiedElement;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.Diagnosis;
import com.variamos.reasoning.defectAnalyzer.model.transformation.VariabilityModel;

public class DefectAnalyzerControllerOutDTO {

	private VariabilityModel variabilityModel;
	private List<Diagnosis> allDiagnostics; 
	private Long time;
	private ClassifiedElement classifiedCauses;
	private ClassifiedElement classifiedCorrections;

	/**
	 * @return the variabilityModel
	 */
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

	/**
	 * @return the allDiagnostics
	 */
	public List<Diagnosis> getAllDiagnostics() {
		return allDiagnostics;
	}

	/**
	 * @param allDiagnostics
	 *            the allDiagnostics to set
	 */
	public void setAllDiagnostics(List<Diagnosis> allDiagnostics) {
		this.allDiagnostics = allDiagnostics;
	}

	public DefectAnalyzerControllerOutDTO() {
		super();
		classifiedCauses = new ClassifiedElement();
		allDiagnostics= new ArrayList<Diagnosis>();
		classifiedCorrections = new ClassifiedElement();
		// TODO Auto-generated constructor stub
	}

	public DefectAnalyzerControllerOutDTO(VariabilityModel variabilityModel,
			List<Diagnosis> allDiagnostics) {
		super();
		this.variabilityModel = variabilityModel;
		this.allDiagnostics = allDiagnostics;
	}

	/**
	 * @return the time
	 */
	public Long getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Long time) {
		this.time = time;
	}

	/**
	 * @return the classifiedCorrections
	 */
	public ClassifiedElement getClassifiedCorrections() {
		return classifiedCorrections;
	}

	/**
	 * @param classifiedCorrections
	 *            the classifiedCorrections to set
	 */
	public void setClassifiedCorrections(
			ClassifiedElement classifiedCorrections) {
		this.classifiedCorrections = classifiedCorrections;
	}

	/**
	 * @return the classifiedCauses
	 */
	public ClassifiedElement getClassifiedCauses() {
		return classifiedCauses;
	}

	/**
	 * @param classifiedCauses
	 *            the classifiedCauses to set
	 */
	public void setClassifiedCauses(ClassifiedElement classifiedCauses) {
		this.classifiedCauses = classifiedCauses;
	}

}