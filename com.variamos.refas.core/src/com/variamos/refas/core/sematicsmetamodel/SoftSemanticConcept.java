package com.variamos.refas.core.sematicsmetamodel;

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

	public SoftSemanticConcept(String name, boolean topConcept, boolean multipleGroupRelations)
	{
		super (name, topConcept, "SemanticEnumeration", multipleGroupRelations);
	}
	
	public String toString()
	{

		return "SSC: " + super.toString();
	}
}
