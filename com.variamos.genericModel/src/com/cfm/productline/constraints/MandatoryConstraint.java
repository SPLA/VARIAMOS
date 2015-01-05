package com.cfm.productline.constraints;

@SuppressWarnings("serial")
public class MandatoryConstraint extends TwoOperandConstraint{
	
	public MandatoryConstraint() {
		super();
	}

	public MandatoryConstraint(String f1, String f2) {
		super(f1, f2);
	}

	@Override
	public String getText() {
		return getFeature1Id() + " must have " + getFeature2Id();
	}
}
