package com.cfm.productline.constraints;


@SuppressWarnings("serial")
public class ExcludesConstraint extends TwoOperandConstraint {
	
	
	public ExcludesConstraint() {
		super();
	}

	public ExcludesConstraint(String f1, String f2) {
		super(f1, f2);
	}

	@Override
	public String getText() {
		return getFeature1Id() + " excludes " + getFeature2Id();
	}
		
	
}
