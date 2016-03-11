package com.variamos.perspsupport.semanticsupport;

import com.variamos.semantic.types.LevelType;
import com.variamos.semantic.types.SatisficingType;

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
public class SoftSemanticConcept extends AbstractSemanticVertex {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2755844763829079610L;
	public static final String VAR_SATISFICINGTYPE = "satisficingType",
			VAR_SATISFICINGTYPENAME = "satisficing Type",
			VAR_SATISFICINGTYPECLASS = SatisficingType.class.getCanonicalName(),
			VAR_CONFREQLEVELTYPE = "ConfigReqLevel",
			VAR_CONFREQLEVELTYPENAME = "Satisficing Req. Level",
			VAR_CONFREQLEVELTYPECLASS = LevelType.class.getCanonicalName();

	public SoftSemanticConcept() {
		super();
		defineSemanticAttributes();
	}

	public SoftSemanticConcept(String name) {
		super(name, false);
		defineSemanticAttributes();
	}

	public SoftSemanticConcept(AbstractSemanticVertex semanticConcept,
			String name) {
		super(semanticConcept, name, false);
		defineSemanticAttributes();
	}

	private void defineSemanticAttributes() {

	}

	public String toString() {

		return "SSC: " + super.toString();
	}
}