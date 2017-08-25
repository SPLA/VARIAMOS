package com.variamos.reasoning.defectAnalyzer.model.defects;

import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.reasoning.defectAnalyzer.model.enums.DefectTypeEnum;

public class DeadElement extends Defect {

	private Identifier deadIdentifier;

	public DeadElement(Identifier deadIdentifier,
			IntBooleanExpression verificationExpression) {
		super();
		this.deadIdentifier = deadIdentifier;
		this.id = deadIdentifier.getId();
		defectType = DefectTypeEnum.DEAD_FEATURE;
		setVerificationExpression(verificationExpression);
	}

	public Identifier getDeadIdentifier() {
		return deadIdentifier;
	}

	public void setDeadIdentifier(Identifier deadIdentifier) {
		this.deadIdentifier = deadIdentifier;
	}

}
