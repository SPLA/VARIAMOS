package com.variamos.refas.core.sematicsmetamodel;

import com.variamos.refas.core.types.LevelType;
import com.variamos.refas.core.types.SatisficingType;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

/**
 * A class to represent soft semantic concepts. Part of PhD work at
 * University of Paris 1
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
			VAR_SATISFICINGTYPECLASS = SatisficingType.class.getCanonicalName(),
			VAR_LEVELTYPE = "level",
			VAR_LEVELTYPENAME = "Satisficing Level",
			VAR_LEVELTYPECLASS = LevelType.class.getCanonicalName();

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
	
	private void defineSemanticAttributes()
	{
		putSemanticAttribute(VAR_SATISFICINGTYPE, new SemanticAttribute(
				VAR_SATISFICINGTYPE, "Enumeration", false, VAR_LEVELTYPENAME, VAR_SATISFICINGTYPECLASS,
				"achieve", ""));
		putSemanticAttribute(VAR_LEVELTYPE, new SemanticAttribute(
				VAR_LEVELTYPE, "Enumeration", false, VAR_LEVELTYPENAME, VAR_LEVELTYPECLASS, "", ""));

		this.addDisPropEditableAttribute("10#" + VAR_SATISFICINGTYPE);
	//	this.addDisPropEditableAttribute("15#" + VAR_LEVELTYPE);
		
		this.addDisPropVisibleAttribute("10#" + VAR_SATISFICINGTYPE);
	//	this.addDisPropVisibleAttribute("15#" + VAR_LEVELTYPE);

	}

	public String toString() {

		return "SSC: " + super.toString();
	}
}