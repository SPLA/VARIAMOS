package com.variamos.reasoning.defectAnalyzer.model.diagnosis;

import java.util.List;

import com.variamos.reasoning.defectAnalyzer.model.defects.Defect;

public class ClassifiableDiagnosis {

	// Puede ser una causa o una corrección según como se use

	private CauCos cauCos;
	private Long id;
	private List<Defect> defects;

	public ClassifiableDiagnosis() {
		super();

	}

	public ClassifiableDiagnosis(CauCos cauCos, Long id, List<Defect> defects) {
		super();
		this.cauCos = cauCos;
		this.id = id;
		this.defects = defects;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the defectsList
	 */
	public List<Defect> getDefects() {
		return defects;
	}

	/**
	 * @param defects
	 *            the defectsList to set
	 */
	public void setDefects(List<Defect> defects) {
		this.defects = defects;
	}

	public CauCos getCauCos() {
		return cauCos;
	}

	public void setCauCos(CauCos cauCos) {
		this.cauCos = cauCos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Common defects [diagnosticElements=" + cauCos
				+ ", defectsList=" + defects + "]";
	}

}
