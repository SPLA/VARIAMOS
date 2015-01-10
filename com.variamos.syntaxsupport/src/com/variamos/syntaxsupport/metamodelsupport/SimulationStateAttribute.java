package com.variamos.syntaxsupport.metamodelsupport;

import com.cfm.hlcl.Domain;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
 */
public class SimulationStateAttribute extends SimulationAttribute {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7430454253717334119L;


	public SimulationStateAttribute()
	{
		super();
	}
	/**
	 * 
	 */
	public SimulationStateAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue) {
		super(name, type, affectProperties, displayName, defaultValue);
	}
	public SimulationStateAttribute(String name, String type,
			boolean affectProperties, String displayName,
			Object defaultValue, Domain domain) {
		super(name, type, affectProperties, displayName,
				defaultValue, domain);
	}

	public SimulationStateAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue) {
		super(name, type, affectProperties, displayName, enumType, defaultValue);
	}

	public SimulationStateAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue, Domain domain) {
		super(name, type, affectProperties, displayName, 
				defaultValue, domain);
	}

}
