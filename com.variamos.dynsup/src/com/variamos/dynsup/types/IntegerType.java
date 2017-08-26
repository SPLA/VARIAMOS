package com.variamos.dynsup.types;

/**
 * A class to represent an int dynamically loaded attribute. Based on
 * IntegerType of ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-11-24
 * @see com.cfm.productline.type.IntegerType
 */
@SuppressWarnings("serial")
public class IntegerType extends Type{
	public static final String IDENTIFIER = "Integer";
	protected IntegerType(){
		super(IDENTIFIER);
	}

	//@Override
/*	public AbstractAttribute makeAttribute(String name,
			boolean affectProperties, String displayName) {
		return new AbstractAttribute(name, getIdentifier(), affectProperties, displayName, 0);
	}
	
	/*
	public static ModelingAttribute newVariableModelingAttribute(String name,
			boolean affectProperties, String displayName) {
		return new ModelingAttribute (name, IDENTIFIER, affectProperties, displayName, 0);
	}
	public static SemanticAttribute newSemanticAttribute(String name,
			boolean affectProperties, String displayName) {
		return new SemanticAttribute (name, IDENTIFIER, affectProperties, displayName, 0);
	}
	public static SimulationStateAttribute newSimulationAttribute(String name,
			boolean affectProperties, String displayName) {
		return new SimulationStateAttribute (name, IDENTIFIER, affectProperties, displayName, 0);
	}
	*/
}
