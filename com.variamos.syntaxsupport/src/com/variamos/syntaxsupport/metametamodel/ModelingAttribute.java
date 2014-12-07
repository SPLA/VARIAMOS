package com.variamos.syntaxsupport.metametamodel;

public class ModelingAttribute extends AbstractAttribute {
	/**
	 * 
	 */
	private static final long serialVersionUID = 552704923296673747L;

	public ModelingAttribute(String name, String type, Object defaultValue) {
		super(name, type, defaultValue);
	}


	public ModelingAttribute(String name, String type, String enumType, Object defaultValue) {
		super(name, type, enumType, defaultValue);
	}
	
	public ModelingAttribute(String name, String type,Object defaultValue, Domain domain) 
	{
		super(name, type, defaultValue, domain);
	}
}
