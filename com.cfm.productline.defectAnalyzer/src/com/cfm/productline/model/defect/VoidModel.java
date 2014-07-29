package com.cfm.productline.model.defect;

import com.cfm.productline.model.enums.DefectType;

public class VoidModel extends Defect {

	public VoidModel(String variabilityModelName) {
		super();
		this.id = variabilityModelName;
		defectType = DefectType.VOID_MODEL;
	}

}
