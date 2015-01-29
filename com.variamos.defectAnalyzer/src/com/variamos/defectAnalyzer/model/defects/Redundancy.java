package com.variamos.defectAnalyzer.model.defects;

import java.util.List;

import com.variamos.defectAnalyzer.model.enums.DefectType;
import com.variamos.hlcl.BooleanExpression;

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
