package com.variamos.syntaxsupport.metamodelsupport;

import com.cfm.hlcl.Domain;

public class ModelingAttribute extends AbstractAttribute {
	/**
	 * 
	 */
	private static final long serialVersionUID = 552704923296673747L;
	
	public ModelingAttribute()
	{
		super();
	}

	public ModelingAttribute(String name, String type,  boolean affectProperties, String displayName,  Object defaultValue) {
		super(name, type, affectProperties, displayName, defaultValue);
	}


	public ModelingAttribute(String name, String type,  boolean affectProperties, String displayName,  String enumType, Object defaultValue) {
		super(name, type, affectProperties, displayName, enumType, defaultValue);
	}
	
	public ModelingAttribute(String name, String type,  boolean affectProperties, String displayName, Object defaultValue, Domain domain) 
	{
		super(name, type, affectProperties, displayName, defaultValue, domain);
	}
}
