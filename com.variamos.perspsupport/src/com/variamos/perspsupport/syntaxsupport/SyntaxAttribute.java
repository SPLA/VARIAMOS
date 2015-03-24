package com.variamos.perspsupport.syntaxsupport;

import com.variamos.hlcl.Domain;

public class SyntaxAttribute extends AbstractAttribute {
	/**
	 * 
	 */
	private static final long serialVersionUID = 552704923296673747L;
	
	public SyntaxAttribute()
	{
		super();
	}

	public SyntaxAttribute(String name, String type,  boolean affectProperties, String displayName,  Object defaultValue) {
		super(name, type, affectProperties, displayName, defaultValue);
	}


	public SyntaxAttribute(String name, String type,  boolean affectProperties, String displayName,  String enumType, Object defaultValue) {
		super(name, type, affectProperties, displayName, enumType, defaultValue);
	}
	
	public SyntaxAttribute(String name, String type,  boolean affectProperties, String displayName, Object defaultValue, Domain domain) 
	{
		super(name, type, affectProperties, displayName, defaultValue, domain);
	}
}
