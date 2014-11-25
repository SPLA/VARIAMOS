package com.variamos.syntaxsupport.type;

import com.variamos.syntaxsupport.metametamodel.MetaAttribute;


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
	public MetaAttribute makeVariable(String name) {
		return new MetaAttribute(name, getIdentifier(), false);
	}

	public static MetaAttribute newVariable(String name) {
		return new MetaAttribute(name, IDENTIFIER, false);
	}

}
