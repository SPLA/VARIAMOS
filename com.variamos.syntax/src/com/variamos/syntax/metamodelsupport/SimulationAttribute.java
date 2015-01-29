package com.variamos.syntax.metamodelsupport;

import com.cfm.hlcl.Domain;

public class SimulationAttribute extends AbstractAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2574853604112183883L;

	
	public SimulationAttribute()
	{
		super();
	}
	
	public SimulationAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue) {
		super(name, type, affectProperties, displayName, defaultValue);
	}
	
	public SimulationAttribute(String name, String type,
			boolean affectProperties, String displayName, 
			Object defaultValue, Domain domain) {
		super(name, type, affectProperties, displayName, defaultValue, domain);
	}

	public SimulationAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue) {
		super(name, type, affectProperties, displayName, defaultValue);
	}

}
