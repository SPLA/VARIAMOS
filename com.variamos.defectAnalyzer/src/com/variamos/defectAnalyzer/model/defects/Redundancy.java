package com.variamos.defectAnalyzer.model.defects;

import com.cfm.hlcl.BooleanExpression;
import com.variamos.defectAnalyzer.model.Dependency;
import com.variamos.defectAnalyzer.model.VariabilityModel;
import com.variamos.defectAnalyzer.model.enums.DefectType;

public class Redundancy extends Defect {


	private Dependency redundantDependency;

	public Redundancy(Dependency redundantDependency) {
		super();
		this.redundantDependency = redundantDependency;
		this.id = redundantDependency.toString();
		defectType = DefectType.REDUNDANCY;

	}

	public Redundancy(Dependency redundantDependency,
			BooleanExpression verificationExpression) {
		this(redundantDependency);
		this.verificationExpression = verificationExpression;

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

}
