package com.variamos.dynsup.types;

/**
 * A class to represent an string dynamically loaded attribute. Based on
 * StringType of ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2017-06-23
 * @see com.cfm.productline.type.StringType
 */
@SuppressWarnings("serial")
public class DomainType extends Type {
	public static final String IDENTIFIER = "Domain";

	protected DomainType() {
		super(IDENTIFIER);
	}

	// @Override
	/*
	 * public AbstractAttribute makeAttribute(String name, boolean
	 * affectProperties, String displayName) { return new
	 * AbstractAttribute(name, getIdentifier(), affectProperties, displayName,
	 * ""); }
	 * 
	 * /* public static AbstractAttribute newModelingAttribute(String name,
	 * boolean affectProperties, String displayName) { return new
	 * ModelingAttribute(name, IDENTIFIER, affectProperties, name, ""); } public
	 * static AbstractAttribute newSemanticAttribute(String name, boolean
	 * affectProperties, String displayName) { return new
	 * SemanticAttribute(name, IDENTIFIER, affectProperties, displayName, ""); }
	 * public static AbstractAttribute newSimulationAttribute(String name,
	 * boolean affectProperties, String displayName) { return new
	 * SimulationStateAttribute(name, IDENTIFIER, affectProperties, displayName,
	 * ""); }
	 */
}
