package com.variamos.syntaxsupport.metametamodel;

public class MetaAttribute extends AbstractAttribute {
	/**
	 * 
	 */
	private static final long serialVersionUID = 552704923296673747L;

	public MetaAttribute(String name, String type, Object defaultValue) {
		super(name, type, defaultValue);
	}


	public MetaAttribute(String name, String type, String enumType, Object defaultValue) {
		super(name, type, enumType, defaultValue);
	}
	
	public MetaAttribute(String name, String type,Object defaultValue, Domain domain) 
	{
		super(name, type, defaultValue, domain);
	}
}
