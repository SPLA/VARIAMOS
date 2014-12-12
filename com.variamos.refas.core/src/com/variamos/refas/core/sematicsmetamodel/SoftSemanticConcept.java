package com.variamos.refas.core.sematicsmetamodel;

import com.variamos.refas.core.types.LevelType;
import com.variamos.refas.core.types.SatisficingType;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
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
	//	putSemanticAttribute(VAR_LEVELTYPE, new SemanticAttribute(
	//			VAR_LEVELTYPE, "Enumeration", false, VAR_LEVELTYPENAME, VAR_LEVELTYPECLASS, "", ""));

		this.addDisPropEditableAttribute("10#" + VAR_SATISFICINGTYPE);
	//	this.addDisPropEditableAttribute("15#" + VAR_LEVELTYPE);
		
		this.addDisPropVisibleAttribute("10#" + VAR_SATISFICINGTYPE);
	//	this.addDisPropVisibleAttribute("15#" + VAR_LEVELTYPE);

	}

	public String toString() {

		return "SSC: " + super.toString();
	}
}