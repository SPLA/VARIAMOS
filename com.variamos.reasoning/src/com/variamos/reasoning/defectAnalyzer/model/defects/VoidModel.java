package com.variamos.reasoning.defectAnalyzer.model.defects;

import com.variamos.hlcl.BooleanExpression;
import com.variamos.reasoning.defectAnalyzer.model.enums.DefectType;

public class VoidModel extends Defect {

	private boolean isVoidModel;
	public boolean isVoidModel() {
		return isVoidModel;
	}
	public void setVoidModel(boolean isVoidModel) {
		this.isVoidModel = isVoidModel;
	}
	
	
	public VoidModel() {
		super();
		defectType = DefectType.VOID_MODEL;
		isVoidModel=Boolean.TRUE;
		id="Void model";
	}
	
	public VoidModel(
	BooleanExpression additionalExpression) {
		super(additionalExpression);
		defectType = DefectType.VOID_MODEL;
		isVoidModel=Boolean.TRUE;
		id="Void model";
	}

}
