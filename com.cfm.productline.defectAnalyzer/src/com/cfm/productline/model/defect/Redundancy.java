package com.cfm.productline.model.defect;

import com.cfm.hlcl.Expression;
import com.cfm.productline.model.defectAnalyzerModel.Dependency;
import com.cfm.productline.model.enums.DefectType;

public class Redundancy extends Defect {

	private Dependency redundantDependency;

	public Redundancy(Dependency redundantDependency) {
		super();
		this.redundantDependency = redundantDependency;
		this.id = redundantDependency.toString();
		defectType = DefectType.REDUNDANCY;

	}

	public Redundancy(Dependency redundantDependency,
			Expression verificationExpression) {
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
