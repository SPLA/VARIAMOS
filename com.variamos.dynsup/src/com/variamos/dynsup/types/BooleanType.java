package com.variamos.dynsup.types;

/**
 * A class to represent a boolean dynamically loaded attribute. Based on
 * BooleanType of ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-11-24
 * @see com.cfm.productline.type.BooleanType
 */
@SuppressWarnings("serial")
public class BooleanType extends Type {
	public static final String IDENTIFIER = "Boolean";

	protected BooleanType() {
		super(IDENTIFIER);
	}

	//@Override
/*	public AbstractAttribute makeAttribute(String name,
			boolean affectProperties, String displayName) {
		return new AbstractAttribute(name, getIdentifier(), affectProperties,
				displayName, false);
	}

	public static AbstractAttribute newVariable(String name,
			boolean affectProperties, String displayName) {
		return new AbstractAttribute(name, IDENTIFIER, affectProperties,
				displayName, false);
	}
*/
}
