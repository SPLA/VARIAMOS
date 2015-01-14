package com.variamos.defectAnalyzer.model.defects;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Identifier;
import com.variamos.defectAnalyzer.model.enums.DefectType;

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
		this.verificationExpression = verificationExpression;
	}


	public Identifier getFalseOptional() {
		return falseOptional;
	}

	public void setFalseOptional(Identifier falseOptional) {
		this.falseOptional = falseOptional;
	}

}
