package com.variamos.refas.core.sematicsmetamodel;

import com.variamos.syntaxsupport.metamodelsupport.AbstractAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SemanticAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticConcept;

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

/*	private static final String VAR_SATISFACTIONTYPE = "satisfactionType",
			VAR_SATISFACTIONTYPENAME = "satisfactionType",
			VAR_SATISFACTIONTYPECLASS = "com.variamos.refas.core.types.SatisfactionType";
*/
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
	/*	putSemanticAttribute(VAR_SATISFACTIONTYPE, new SemanticAttribute(
				VAR_SATISFACTIONTYPE, "Enumeration", false,
				VAR_SATISFACTIONTYPENAME, VAR_SATISFACTIONTYPECLASS, "achieve",
				""));
		this.addPropEditableAttribute("01#" + VAR_SATISFACTIONTYPE);
		this.addPropVisibleAttribute("01#" + VAR_SATISFACTIONTYPE);
		*/
	}
/*
	public AbstractAttribute getSatisfactionType() {
		return getSemanticAttribute(VAR_SATISFACTIONTYPE);
	}

	public void setSatisfactionType(SemanticAttribute satisfactionType) {
		setSemanticAttribute(VAR_SATISFACTIONTYPE, satisfactionType);
	}
*/
	public String toString() {

		return " HSC: " + super.toString();
	}
}
