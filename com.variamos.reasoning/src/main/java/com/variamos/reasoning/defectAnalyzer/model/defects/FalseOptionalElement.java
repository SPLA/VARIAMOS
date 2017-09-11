package com.variamos.reasoning.defectAnalyzer.model.defects;

import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;

public class FalseOptionalElement extends Defect {

	
	private Identifier falseOptional;

	public FalseOptionalElement(Identifier falseOptional) {
		super();
		this.falseOptional = falseOptional;
		defectType = DefectTypeEnum.FALSE_OPTIONAL_FEATURE;
		id = falseOptional.getId();
	}

	
	public FalseOptionalElement(Identifier falseOptional,
			IntBooleanExpression verificationExpression) {
		this(falseOptional);
		setVerificationExpression(verificationExpression);
	}


	public Identifier getFalseOptional() {
		return falseOptional;
	}

	public void setFalseOptional(Identifier falseOptional) {
		this.falseOptional = falseOptional;
	}

}
