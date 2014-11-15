package com.cfm.productline.constraints;

@SuppressWarnings("serial")
public class OptionalConstraint extends TwoOperandConstraint{

	public OptionalConstraint() {
		super();
	}

	public OptionalConstraint(String f1, String f2) {
		super(f1, f2);
	}

	@Override
	public String getText() {
		return getFeature1Id() + " might have " + getFeature2Id();
	}

}
