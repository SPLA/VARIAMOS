package com.variamos.reasoning.defectAnalyzer.model.defects;

import java.util.List;

import com.variamos.hlcl.model.expressions.IntBooleanExpression;

public class Redundancy extends Defect {

	private List<IntBooleanExpression> negationList;

	public Redundancy(List<IntBooleanExpression> negationList,IntBooleanExpression verificationExpression) {
		super(verificationExpression);
		this.negationList = negationList;
		defectType = DefectTypeEnum.REDUNDANCY;
		id="Redundancy: "+verificationExpression.toString();
	}

	public List<IntBooleanExpression> getNegationList() {
		return negationList;
	}

	public void setNegationList(List<IntBooleanExpression> negationList) {
		this.negationList = negationList;
	}

}
