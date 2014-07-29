package com.cfm.productline.model.diagnostic;

import java.util.List;

import com.cfm.productline.model.defect.Defect;
import com.cfm.productline.model.defectAnalyzerModel.Dependency;

/**
 * Sive para saber cada MCS o cada causa a que defecto pertenece
 * @author LuFe
 *
 */
public class DiagnosedCauseCorrection {

	private List<List<Dependency>> causes;
	private Defect defect;
	/**
	 * @return the causes
	 */
	public List<List<Dependency>> getCauses() {
		return causes;
	}
	/**
	 * @param causes the causes to set
	 */
	public void setCauses(List<List<Dependency>> causes) {
		this.causes = causes;
	}
	/**
	 * @return the defect
	 */
	public Defect getDefect() {
		return defect;
	}
	/**
	 * @param defect the defect to set
	 */
	public void setDefect(Defect defect) {
		this.defect = defect;
	}
	
}
