package com.variamos.refas.core.sematicsmetamodel;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class SoftSemanticConceptSatisficing extends AbstractSemanticConcept {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 134266317319543125L;
	private ConditionalExpression condition;
	public SoftSemanticConceptSatisficing(String name, ConditionalExpression condition)
	{
		super (name, false, "SemanticEnumeration", false);
		this.condition = condition;
	}
	
	public String toString()
	{

		return "SSCS: " + super.toString() + "condit: " + condition.getExpression();
	}
}
