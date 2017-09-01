package com.variamos.dynsup.model;

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
public class OpersConcept extends OpersElement {
	// FIXME v1.1 Split into to SeSupConcept and OpSupConcept
	/**
	 * 
	 */
	private static final long serialVersionUID = -9024843014882087367L;

	public OpersConcept() {
		this(null);
	}

	public OpersConcept(String identifier) {
		super(identifier);
	}

	/*
	 * public OpersConcept(OpersAbstractVertex parentConcept, String name) {
	 * super(parentConcept, name, true); }
	 */
	@Override
	public String toString() {

		return " HSC: " + super.toString();
	}
}
