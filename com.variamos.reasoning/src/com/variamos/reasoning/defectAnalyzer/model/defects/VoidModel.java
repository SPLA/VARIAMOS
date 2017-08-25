package com.variamos.reasoning.defectAnalyzer.model.defects;

import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.reasoning.defectAnalyzer.model.enums.DefectTypeEnum;

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
		defectType = DefectTypeEnum.VOID_MODEL;
		isVoidModel=Boolean.TRUE;
		id="Void model";
	}
	
	public VoidModel(
	IntBooleanExpression additionalExpression) {
		super(additionalExpression);
		defectType = DefectTypeEnum.VOID_MODEL;
		isVoidModel=Boolean.TRUE;
		id="Void model";
	}

}
