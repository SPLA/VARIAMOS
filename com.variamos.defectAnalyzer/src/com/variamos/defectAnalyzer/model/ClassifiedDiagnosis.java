package com.variamos.defectAnalyzer.model;

import java.util.ArrayList;
import java.util.List;

public class ClassifiedDiagnosis {

	List<ClassifiableDiagnosis> commonDiagnosis;
	List<ClassifiableDiagnosis> noCommonDiagnosis;

	public ClassifiedDiagnosis() {
		super();
		commonDiagnosis = new ArrayList<ClassifiableDiagnosis>();
		noCommonDiagnosis = new ArrayList<ClassifiableDiagnosis>();
	}

	public ClassifiedDiagnosis(List<ClassifiableDiagnosis> commonDiagnosis,
			List<ClassifiableDiagnosis> noCommonDiagnosis) {
		super();
		this.commonDiagnosis = commonDiagnosis;
		this.noCommonDiagnosis = noCommonDiagnosis;
	}

	/**
	 * @return the commonDiagnosis
	 */
	public List<ClassifiableDiagnosis> getCommonDiagnosis() {
		return commonDiagnosis;
	}

	/**
	 * @param commonDiagnosis
	 *            the commonDiagnosis to set
	 */
	public void setCommonDiagnosis(List<ClassifiableDiagnosis> commonDiagnosis) {
		this.commonDiagnosis = commonDiagnosis;
	}

	/**
	 * @return the noCommonDiagnosis
	 */
	public List<ClassifiableDiagnosis> getNoCommonDiagnosis() {
		return noCommonDiagnosis;
	}

	/**
	 * @param noCommonDiagnosis
	 *            the noCommonDiagnosis to set
	 */
	public void setNoCommonDiagnosis(List<ClassifiableDiagnosis> noCommonDiagnosis) {
		this.noCommonDiagnosis = noCommonDiagnosis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClassifiedDiagnosis [commonDiagnosis=" + commonDiagnosis
				+ ", noCommonDiagnosis=" + noCommonDiagnosis + "]";
	}
}
