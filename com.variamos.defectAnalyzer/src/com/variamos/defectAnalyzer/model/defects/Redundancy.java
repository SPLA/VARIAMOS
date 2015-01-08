package com.variamos.defectAnalyzer.model.defects;

import java.util.List;

import com.cfm.hlcl.BooleanExpression;
import com.variamos.defectAnalyzer.model.Dependency;
import com.variamos.defectAnalyzer.model.enums.DefectType;

public class Redundancy extends Defect {


	private Dependency redundantDependency;
	private BooleanExpression redundantExpression;
	private List<BooleanExpression> negationList;
	
	@Deprecated
	public Redundancy(Dependency redundantDependency) {
		super();
		this.redundantDependency = redundantDependency;
		this.id = redundantDependency.toString();
		defectType = DefectType.REDUNDANCY;

	}
	@Deprecated
	public Redundancy(Dependency redundantDependency,
			BooleanExpression verificationExpression) {
		this(redundantDependency);
		this.verificationExpression = verificationExpression;

	}
	
	

	public Redundancy(BooleanExpression redundantExpression,
			List<BooleanExpression> negationList) {
		super();
		this.redundantExpression = redundantExpression;
		this.negationList = negationList;
	}
	/**
	 * @return the redundantDependency
	 */
	public Dependency getRedundantDependency() {
		return redundantDependency;
	}

	/**
	 * @param redundantDependency
	 *            the redundantDependency to set
	 */
	public void setRedundantDependency(Dependency redundantDependency) {
		this.redundantDependency = redundantDependency;
	}

	public BooleanExpression getRedundantExpression() {
		return redundantExpression;
	}

	public void setRedundantExpression(BooleanExpression redundantExpression) {
		this.redundantExpression = redundantExpression;
	}
	public List<BooleanExpression> getNegationList() {
		return negationList;
	}
	public void setNegationList(List<BooleanExpression> negationList) {
		this.negationList = negationList;
	}



}
