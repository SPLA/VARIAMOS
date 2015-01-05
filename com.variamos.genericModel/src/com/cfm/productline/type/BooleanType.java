package com.cfm.productline.type;

import com.cfm.productline.Variable;


@SuppressWarnings("serial")
public class BooleanType extends Type{
	public static final String IDENTIFIER = "Boolean";
	protected BooleanType(){
		super(IDENTIFIER);
	}
	
//	@Override
//	public boolean contains(Object obj) {
//		return obj instanceof Boolean;
//	}

	@Override
	public Variable makeVariable(String name) {
		return new Variable(name, false, getIdentifier());
	}

	public static Variable newVariable(String name) {
		return new Variable(name, false, IDENTIFIER);
	}

}
