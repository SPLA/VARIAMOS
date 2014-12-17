package com.variamos.defectAnalyzer.diagnostic;

import java.util.ArrayList;
import java.util.List;

public class ClassifiedDiagnosis {

	List<DefectsByMCSMUSes> commonDiagnosis;
	List<DefectsByMCSMUSes> noCommonDiagnosis;

	public ClassifiedDiagnosis() {
		super();
		commonDiagnosis = new ArrayList<DefectsByMCSMUSes>();
		noCommonDiagnosis = new ArrayList<DefectsByMCSMUSes>();
	}

	public ClassifiedDiagnosis(List<DefectsByMCSMUSes> commonDiagnosis,
			List<DefectsByMCSMUSes> noCommonDiagnosis) {
		super();
		this.commonDiagnosis = commonDiagnosis;
		this.noCommonDiagnosis = noCommonDiagnosis;
	}

	/**
	 * @return the commonDiagnosis
	 */
	public List<DefectsByMCSMUSes> getCommonDiagnosis() {
		return commonDiagnosis;
	}

	/**
	 * @param commonDiagnosis
	 *            the commonDiagnosis to set
	 */
	public void setCommonDiagnosis(List<DefectsByMCSMUSes> commonDiagnosis) {
		this.commonDiagnosis = commonDiagnosis;
	}

	/**
	 * @return the noCommonDiagnosis
	 */
	public List<DefectsByMCSMUSes> getNoCommonDiagnosis() {
		return noCommonDiagnosis;
	}

	/**
	 * @param noCommonDiagnosis
	 *            the noCommonDiagnosis to set
	 */
	public void setNoCommonDiagnosis(List<DefectsByMCSMUSes> noCommonDiagnosis) {
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
