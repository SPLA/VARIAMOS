package com.variamos.syntaxsupport.type;

import com.cfm.productline.Variable;
import com.cfm.productline.type.Type;


@SuppressWarnings("serial")
@Deprecated
public class RealType extends Type{
	public static final String IDENTIFIER = "Real";
	protected RealType(){
		super(IDENTIFIER);
	}
	
//	@Override
//	public boolean contains(Object obj) {
//		return obj instanceof Float;
//	}

	@Override
	public Variable makeVariable(String name) {
		return new Variable(name, 0.0f, getIdentifier());
	}
}
