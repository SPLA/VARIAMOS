package com.variamos.perspsupport.opers;

import com.variamos.perspsupport.opersint.IntOpersConcept;

/**
 * A class to represent the hard concepts at semantic level. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 * @see com.cfm.productline.
 */
public class OpersConcept extends OpersAbstractVertex implements
		IntOpersConcept {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9024843014882087367L;

	public OpersConcept() {
		this(null, null);
	}

	public OpersConcept(String identifier) {
		this(null, identifier);
	}

	public OpersConcept(OpersAbstractVertex parentConcept, String name) {
		super(parentConcept, name, true);
	}

	public String toString() {

		return " HSC: " + super.toString();
	}
}
