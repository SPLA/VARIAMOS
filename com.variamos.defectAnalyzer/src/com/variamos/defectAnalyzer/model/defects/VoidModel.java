package com.variamos.defectAnalyzer.model.defects;

import com.variamos.defectAnalyzer.model.enums.DefectType;

public class VoidModel extends Defect {

	public VoidModel(String variabilityModelName) {
		super();
		this.id = variabilityModelName;
		defectType = DefectType.VOID_MODEL;
	}

}
