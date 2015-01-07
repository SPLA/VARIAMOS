package com.variamos.defectAnalyzer.model.defects;

import com.variamos.defectAnalyzer.model.enums.DefectType;

public class VoidModel extends Defect {

	private boolean isVoidModel;
	public boolean isVoidModel() {
		return isVoidModel;
	}
	public void setVoidModel(boolean isVoidModel) {
		this.isVoidModel = isVoidModel;
	}
	
	
	public VoidModel(boolean isVoidModel) {
		super();
		this.isVoidModel = isVoidModel;
		defectType = DefectType.VOID_MODEL;
	}
	public VoidModel(String variabilityModelName) {
		super();
		this.id = variabilityModelName;
		defectType = DefectType.VOID_MODEL;
	}

}
