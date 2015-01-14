package com.variamos.defectAnalyzer.model.defects;

import com.cfm.hlcl.BooleanExpression;
import com.variamos.defectAnalyzer.model.Dependency;
import com.variamos.defectAnalyzer.model.enums.DefectType;

public class Redundancy extends Defect {


	private Dependency redundantDependency;
	private BooleanExpression redundantExpression;
	private BooleanExpression negation;
	
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
			BooleanExpression negation) {
		super();
		this.redundantExpression = redundantExpression;
		this.negation = negation;
		this.id = redundantExpression.toString();
		defectType = DefectType.REDUNDANCY;

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

	public BooleanExpression getNegation() {
		return negation;
	}

	public void setNegation(BooleanExpression negation) {
		this.negation = negation;
	}

}
