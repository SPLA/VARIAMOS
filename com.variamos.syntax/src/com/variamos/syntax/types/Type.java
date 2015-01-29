package com.variamos.syntax.types;

import java.io.Serializable;

/**
 * Abstract class for dynamic types. Copied from ProductLine. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-12-01
 * @see com.cfm.productline.type.Type
 */

@SuppressWarnings("serial")
public abstract class Type implements Serializable {

	protected String identifier;

	protected Type(String identifier) {
		this.identifier = identifier;
	}

	protected Type() {
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	// public abstract boolean contains(Object obj);
/*	public abstract AbstractAttribute makeAttribute(String name,
			boolean affectProperties, String displayName);
			*/
}
