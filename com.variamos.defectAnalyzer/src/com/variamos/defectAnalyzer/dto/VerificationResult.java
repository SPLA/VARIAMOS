package com.variamos.defectAnalyzer.dto;

import java.util.ArrayList;
import java.util.List;

import com.variamos.defectAnalyzer.model.defects.Defect;

public class VerificationResult {

	private List<Defect> deadFeaturesList;
	private List<Defect> falseOptionalFeaturesList;
	private List<Defect> domainNotAttainableList;
	private List<Defect> redundanciesList;
	private Defect voidModel;
	private Defect falseProductLineModel;

	public VerificationResult() {
		super();
		deadFeaturesList = new ArrayList<Defect>();
		falseOptionalFeaturesList = new ArrayList<Defect>();
		domainNotAttainableList = new ArrayList<Defect>();
		redundanciesList = new ArrayList<Defect>();
	}

	/**
	 * @return the deadFeaturesList
	 */
	public List<Defect> getDeadFeaturesList() {
		return deadFeaturesList;
	}

	/**
	 * @param deadFeaturesList
	 *            the deadFeaturesList to set
	 */
	public void setDeadFeaturesList(List<Defect> deadFeaturesList) {
		this.deadFeaturesList = deadFeaturesList;
	}

	/**
	 * @return the falseOptionalFeaturesList
	 */
	public List<Defect> getFalseOptionalFeaturesList() {
		return falseOptionalFeaturesList;
	}

	/**
	 * @param falseOptionalFeaturesList
	 *            the falseOptionalFeaturesList to set
	 */
	public void setFalseOptionalFeaturesList(
			List<Defect> falseOptionalFeaturesList) {
		this.falseOptionalFeaturesList = falseOptionalFeaturesList;
	}


	/**
	 * @return the redundanciesList
	 */
	public List<Defect> getRedundanciesList() {
		return redundanciesList;
	}

	/**
	 * @param redundanciesList
	 *            the redundanciesList to set
	 */
	public void setRedundanciesList(List<Defect> redundanciesList) {
		this.redundanciesList = redundanciesList;
	}

	/**
	 * @return the voidModel
	 */
	public Defect getVoidModel() {
		return voidModel;
	}

	/**
	 * @param voidModel
	 *            the voidModel to set
	 */
	public void setVoidModel(Defect voidModel) {
		this.voidModel = voidModel;
	}

	/**
	 * @return the falseProductLineModel
	 */
	public Defect getFalseProductLineModel() {
		return falseProductLineModel;
	}

	/**
	 * @param falseProductLineModel
	 *            the falseProductLineModel to set
	 */
	public void setFalseProductLineModel(Defect falseProductLineModel) {
		this.falseProductLineModel = falseProductLineModel;
	}

	public boolean isVoidModel() {
		if (voidModel != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFalseProductLineModel() {
		if (falseProductLineModel != null) {
			return true;
		} else {
			return false;
		}
	}

}
