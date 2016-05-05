package com.variamos.reasoning.defectAnalyzer.model.defects;

import java.util.List;

import com.variamos.hlcl.BooleanExpression;
import com.variamos.reasoning.defectAnalyzer.model.enums.DefectType;

public class Redundancy extends Defect {

	private List<BooleanExpression> negationList;

	public Redundancy(List<BooleanExpression> negationList,BooleanExpression verificationExpression) {
		super(verificationExpression);
		this.negationList = negationList;
		defectType = DefectType.REDUNDANCY;
		id="Redundancy: "+verificationExpression.toString();
	}

	public List<BooleanExpression> getNegationList() {
		return negationList;
	}

	public void setNegationList(List<BooleanExpression> negationList) {
		this.negationList = negationList;
	}

}
