package com.variamos.defectAnalyzer.model.defects;

import com.variamos.defectAnalyzer.model.enums.DefectType;
import com.variamos.hlcl.BooleanExpression;
import com.variamos.hlcl.Identifier;

public class FalseOptionalElement extends Defect {

	
	private Identifier falseOptional;

	public FalseOptionalElement(Identifier falseOptional) {
		super();
		this.falseOptional = falseOptional;
		defectType = DefectType.FALSE_OPTIONAL_FEATURE;
		id = falseOptional.getId();
	}

	
	public FalseOptionalElement(Identifier falseOptional,
			BooleanExpression verificationExpression) {
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
