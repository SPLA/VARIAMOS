package com.variamos.perspsupport.semanticsupport;

import com.variamos.perspsupport.semanticinterface.IntSemanticConcept;

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
public class SemanticConcept extends AbstractSemanticVertex implements
		IntSemanticConcept {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9024843014882087367L;

	public SemanticConcept() {
		this(null, null);
	}

	public SemanticConcept(String identifier) {
		this(null, identifier);
	}

	public SemanticConcept(AbstractSemanticVertex parentConcept, String name) {
		super(parentConcept, name, true);
		defineSemanticAttributes();
	}

	private void defineSemanticAttributes() {
	}

	public String toString() {

		return " HSC: " + super.toString();
	}
}
