package com.cfm.productline.constraints;


@SuppressWarnings("serial")
public class RequiresConstraint extends TwoOperandConstraint {
	
	
	public RequiresConstraint() {
		super();
	}

	public RequiresConstraint(String f1, String f2) {
		super(f1, f2);
	}

	@Override
	public String getText() {
		return getFeature1Id() + " requires " + getFeature2Id();
	}
		
	
}
