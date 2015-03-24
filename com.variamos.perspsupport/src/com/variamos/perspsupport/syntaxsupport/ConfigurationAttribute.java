package com.variamos.perspsupport.syntaxsupport;

import com.variamos.hlcl.Domain;

public class ConfigurationAttribute extends AbstractAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2574853604112183883L;

	
	public ConfigurationAttribute()
	{
		super();
	}
	
	public ConfigurationAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue) {
		super(name, type, affectProperties, displayName, defaultValue);
	}
	
	public ConfigurationAttribute(String name, String type,
			boolean affectProperties, String displayName, 
			Object defaultValue, Domain domain) {
		super(name, type, affectProperties, displayName, defaultValue, domain);
	}

	public ConfigurationAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue) {
		super(name, type, affectProperties, displayName, defaultValue);
	}

}
