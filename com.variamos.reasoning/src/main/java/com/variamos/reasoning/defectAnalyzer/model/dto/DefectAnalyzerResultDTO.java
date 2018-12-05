package com.variamos.reasoning.defectAnalyzer.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.variamos.hlcl.core.HlclProgram;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.ClassifiedElement;
import com.variamos.reasoning.defectAnalyzer.model.diagnosis.Diagnosis;

/**
 * Entrega los resultados del análisis del defecto causas y correcciones
 * 
 * @author LuFe
 * 
 */
public class DefectAnalyzerResultDTO  {

	
	
	private List<Diagnosis> allDiagnosis = new ArrayList<Diagnosis>();
	private HlclProgram model= new HlclProgram();
	private HlclProgram fixedConstraints= new HlclProgram();
	private ClassifiedElement classifiedCauses;
	private ClassifiedElement classifiedCorrections;
	private Long causesClassificationTime;
	private Long correctionsClassificationTime;
	private Long totalTime;

	

	public HlclProgram getModel() {
		return model;
	}

	public void setModel(HlclProgram model) {
		this.model = model;
	}

	public ClassifiedElement getClassifiedCauses() {
		return classifiedCauses;
	}

	public void setClassifiedCauses(ClassifiedElement classifiedCauses) {
		this.classifiedCauses = classifiedCauses;
	}

	public ClassifiedElement getClassifiedCorrections() {
		return classifiedCorrections;
	}

	public void setClassifiedCorrections(ClassifiedElement classifiedCorrections) {
		this.classifiedCorrections = classifiedCorrections;
	}

	/**
	 * @return the allDiagnostics
	 */
	public List<Diagnosis> getAllDiagnosis() {
		return allDiagnosis;
	}

	/**
	 * @param allDiagnosis the allDiagnostics to set
	 */
	public void setAllDiagnosis(List<Diagnosis> allDiagnosis) {
		this.allDiagnosis = allDiagnosis;
	}

	public HlclProgram getFixedConstraints() {
		return fixedConstraints;
	}

	public void setFixedConstraints(HlclProgram fixedConstraints) {
		this.fixedConstraints = fixedConstraints;
	}

	public Long getCorrectionsClassificationTime() {
		return correctionsClassificationTime;
	}

	public void setCorrectionsClassificationTime(Long correctionsClassificationTime) {
		this.correctionsClassificationTime = correctionsClassificationTime;
	}

	public Long getCausesClassificationTime() {
		return causesClassificationTime;
	}

	public void setCausesClassificationTime(Long causesClassificationTime) {
		this.causesClassificationTime = causesClassificationTime;
	}

	public Long getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}

}
