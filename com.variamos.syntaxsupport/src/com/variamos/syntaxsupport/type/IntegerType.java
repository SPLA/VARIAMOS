package com.variamos.syntaxsupport.type;

import com.variamos.syntaxsupport.metametamodel.MetaAttribute;


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
	public MetaAttribute makeVariable(String name) {
		return new MetaAttribute(name, getIdentifier(),0);
	}
	
	public static MetaAttribute newVariable(String name) {
		return new MetaAttribute (name, IDENTIFIER, 0);
	}
}
