package com.variamos.perspsupport.semanticsupport;

import com.variamos.hlcl.RangeDomain;
import com.variamos.perspsupport.syntaxsupport.SemanticAttribute;
import com.variamos.semantic.types.LevelType;
import com.variamos.semantic.types.SatisficingType;

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
			VAR_CONFREQLEVELTYPE = "ConfigReqLevel",
			VAR_CONFREQLEVELTYPENAME = "Satisficing Level",
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
	
	private void defineSemanticAttributes()
	{
		putSemanticAttribute(VAR_SATISFICINGTYPE, new SemanticAttribute(
				VAR_SATISFICINGTYPE, "Enumeration", false, VAR_CONFREQLEVELTYPENAME, VAR_SATISFICINGTYPECLASS,
				"Achieve as close as possible", "",0));
		putSemanticAttribute(VAR_CONFREQLEVELTYPE, new SemanticAttribute(
				VAR_CONFREQLEVELTYPE, "Integer", false, VAR_CONFREQLEVELTYPENAME, 0, new RangeDomain(0, 5),0));

		this.addPropEditableAttribute("10#" + VAR_SATISFICINGTYPE);
		this.addPropEditableAttribute("05#" + VAR_CONFREQLEVELTYPE
				+ "#" + "Required" + "#==#" + "true" + "#" + "0");
		
		this.addPropVisibleAttribute("10#" + VAR_SATISFICINGTYPE);
		this.addPropVisibleAttribute("05#" + VAR_CONFREQLEVELTYPE);

	}

	public String toString() {

		return "SSC: " + super.toString();
	}
}