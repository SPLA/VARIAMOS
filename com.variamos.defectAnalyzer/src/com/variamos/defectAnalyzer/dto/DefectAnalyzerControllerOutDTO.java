package com.variamos.defectAnalyzer.dto;

import java.util.ArrayList;
import java.util.List;

import com.variamos.defectAnalyzer.model.ClassifiedDiagnosis;
import com.variamos.defectAnalyzer.model.Diagnosis;
import com.variamos.defectAnalyzer.model.VariabilityModel;

public class DefectAnalyzerControllerOutDTO {

	private VariabilityModel variabilityModel;
	private List<Diagnosis> allDiagnostics; 
	private Long time;
	private ClassifiedDiagnosis classifiedCauses;
	private ClassifiedDiagnosis classifiedCorrections;

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
		classifiedCauses = new ClassifiedDiagnosis();
		allDiagnostics= new ArrayList<Diagnosis>();
		classifiedCorrections = new ClassifiedDiagnosis();
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
	public ClassifiedDiagnosis getClassifiedCorrections() {
		return classifiedCorrections;
	}

	/**
	 * @param classifiedCorrections
	 *            the classifiedCorrections to set
	 */
	public void setClassifiedCorrections(
			ClassifiedDiagnosis classifiedCorrections) {
		this.classifiedCorrections = classifiedCorrections;
	}

	/**
	 * @return the classifiedCauses
	 */
	public ClassifiedDiagnosis getClassifiedCauses() {
		return classifiedCauses;
	}

	/**
	 * @param classifiedCauses
	 *            the classifiedCauses to set
	 */
	public void setClassifiedCauses(ClassifiedDiagnosis classifiedCauses) {
		this.classifiedCauses = classifiedCauses;
	}

}