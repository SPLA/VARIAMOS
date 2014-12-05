package com.variamos.syntaxsupport.type;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;


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
	public AbstractAttribute makeAttribute(String name) {
		return new AbstractAttribute(name, getIdentifier(), false);
	}

	public static AbstractAttribute newVariable(String name) {
		return new AbstractAttribute(name, IDENTIFIER, false);
	}

}
