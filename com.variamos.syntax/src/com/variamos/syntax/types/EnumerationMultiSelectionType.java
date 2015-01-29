package com.variamos.syntax.types;

/**
 * A class to represent an enumeration dynamically loaded attribute for
 * multiselection. Inspired on other ProductLine types. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-12-04
 * @see com.cfm.productline.type
 */
@SuppressWarnings("serial")
public class EnumerationMultiSelectionType extends Type {
	public static final String IDENTIFIER = "MEnumeration";

	protected EnumerationMultiSelectionType() {
		super(IDENTIFIER);
	}

	// @Override
	// public boolean contains(Object obj) {
	// return obj instanceof Integer;
	// }

	//@Override
/*	public AbstractAttribute makeAttribute(String name,
			boolean affectProperties, String displayName) {
		return new AbstractAttribute(name, getIdentifier(), affectProperties,
				displayName, 0);
	}
/*
	public static SemanticAttribute newSemanticAttribute(String name,
			boolean affectProperties, String displayName) {
		return new SemanticAttribute(name, IDENTIFIER, affectProperties,
				displayName, 0);
	}

	public static SimulationStateAttribute newSimulationAttribute(String name,
			boolean affectProperties, String displayName) {
		return new SimulationStateAttribute(name, IDENTIFIER, affectProperties,
				displayName, 0);
	}

	public static ModelingAttribute newModelingAttribute(String name,
			boolean affectProperties, String displayName) {
		return new ModelingAttribute(name, IDENTIFIER, affectProperties,
				displayName, 0);
	}
	*/
}
