package com.variamos.defectAnalyzer.model.defects;

import com.variamos.defectAnalyzer.model.enums.DefectType;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.Identifier;

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
