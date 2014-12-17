package com.variamos.refas.core.sematicsmetamodel;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

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
public class HardSemanticConcept extends AbstractSemanticVertex {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9024843014882087367L;

	public static final String VAR_SATISFACTIONTYPE = "satisfactionType",
			VAR_SATISFACTIONTYPENAME = "Satisfaction Type",
			VAR_SATISFACTIONTYPECLASS = "com.variamos.refas.core.types.SatisfactionType";

	public HardSemanticConcept() {
		this(null, null);
	}

	public HardSemanticConcept(String name) {
		this(null, name);
	}

	public HardSemanticConcept(AbstractSemanticVertex parentConcept, String name) {
		super(parentConcept, name, true);
		defineSemanticAttributes();
	}

	private void defineSemanticAttributes() {
		putSemanticAttribute(VAR_SATISFACTIONTYPE, new SemanticAttribute(
				VAR_SATISFACTIONTYPE, "Enumeration",false, VAR_SATISFACTIONTYPENAME, VAR_SATISFACTIONTYPECLASS,
				 "achieve", ""));
		this.addDisPropEditableAttribute("01#" + VAR_SATISFACTIONTYPE);
		this.addDisPropVisibleAttribute("01#" + VAR_SATISFACTIONTYPE);
	}

	public AbstractAttribute getSatisfactionType() {
		return getSemanticAttribute(VAR_SATISFACTIONTYPE);
	}

	public void setSatisfactionType(SemanticAttribute satisfactionType) {
		setSemanticAttribute(VAR_SATISFACTIONTYPE, satisfactionType);
	}

	public String toString() {

		return " HSC: " + super.toString();
	}
}
