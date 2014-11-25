package com.variamos.syntaxsupport.type;

import com.variamos.syntaxsupport.metametamodel.MetaAttribute;


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
	public MetaAttribute makeVariable(String name) {
		return new MetaAttribute(name, getIdentifier(),"");
	}

	public static MetaAttribute newVariable(String name) {
		return new MetaAttribute(name, IDENTIFIER, "");
	}
}
