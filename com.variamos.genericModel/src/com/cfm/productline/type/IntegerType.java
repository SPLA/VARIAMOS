package com.cfm.productline.type;

import com.cfm.productline.Variable;


@SuppressWarnings("serial")
public class IntegerType extends Type{
	public static final String IDENTIFIER = "Integer";
	protected IntegerType(){
		super(IDENTIFIER);
	}
	
//	@Override
//	public boolean contains(Object obj) {
//		return obj instanceof Integer;
//	}

	@Override
	public Variable makeVariable(String name) {
		return new Variable(name, 0, getIdentifier());
	}
	
	public static Variable newVariable(String name) {
		return new Variable(name, 0, IDENTIFIER);
	}
}
