package com.variamos.defectAnalyzer.model.defects;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Identifier;
import com.variamos.defectAnalyzer.model.enums.DefectType;

public class DeadElement extends Defect {

	private Identifier deadIdentifier;

	public DeadElement(Identifier deadIdentifier,
			BooleanExpression verificationExpression) {
		super();
		this.deadIdentifier = deadIdentifier;
		this.id = deadIdentifier.getId();
		defectType = DefectType.DEAD_FEATURE;
		setVerificationExpression(verificationExpression);
	}

	public Identifier getDeadIdentifier() {
		return deadIdentifier;
	}

	public void setDeadIdentifier(Identifier deadIdentifier) {
		this.deadIdentifier = deadIdentifier;
	}

}
