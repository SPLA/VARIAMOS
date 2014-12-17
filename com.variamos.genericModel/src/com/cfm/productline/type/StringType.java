package com.cfm.productline.type;

import com.cfm.productline.Variable;


@SuppressWarnings("serial")
public class StringType extends Type{
	public static final String IDENTIFIER = "String";
	protected StringType(){
		super(IDENTIFIER);
	}
	
//	@Override
//	public boolean contains(Object obj) {
//		return obj instanceof String;
//	}

	@Override
	public Variable makeVariable(String name) {
		return new Variable(name, "", getIdentifier());
	}

	public static Variable newVariable(String name) {
		return new Variable(name, "", IDENTIFIER);
	}
}
