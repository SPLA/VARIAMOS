package com.variamos.dynsup.model;

/**
 * A class to represent soft semantic concepts. Part of PhD work at University
 * of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 * @see com.cfm.productline.
 */
public class OpersSoftConcept extends OpersElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2755844763829079610L;

	public OpersSoftConcept() {
		super(null);
	}

	public OpersSoftConcept(String name) {
		super(name);
	}

	public String toString() {

		return "SSC: " + super.toString();
	}
}