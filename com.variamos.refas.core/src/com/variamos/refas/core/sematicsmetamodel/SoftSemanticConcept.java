package com.variamos.refas.core.sematicsmetamodel;

import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class SoftSemanticConcept extends AbstractSemanticConcept{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2755844763829079610L;
	
	public SoftSemanticConcept(AbstractSemanticConcept semanticConcept, String name)
	{
		super (semanticConcept, name,  false);
		putSemanticAttribute("satisficingType", new SemanticAttribute("satisficingType","Enumeration","com.variamos.refas.core.types.SatisficingType","",""));
		putSemanticAttribute("level", new SemanticAttribute("level","Enumeration","com.variamos.refas.core.types.LevelType","",""));
		
		this.addDisPropEditableAttribute("15#"+"satisficingType");		
		this.addDisPropEditableAttribute("15#"+"level");

	}
	
	public SoftSemanticConcept(String name)
	{
		super (name, false);
		putSemanticAttribute("satisficingType", new SemanticAttribute("satisficingType","Enumeration","com.variamos.refas.core.types.SatisficingType","achieve",""));
		putSemanticAttribute("level", new SemanticAttribute("level","Enumeration","com.variamos.refas.core.types.LevelType","",""));
		
		this.addDisPropEditableAttribute("15#"+"satisficingType");
				this.addDisPropEditableAttribute("15#"+"level");
	}
	
	public String toString()
	{

		return "SSC: " + super.toString();
	}
}