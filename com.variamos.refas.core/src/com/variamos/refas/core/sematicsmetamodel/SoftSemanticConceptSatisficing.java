package com.variamos.refas.core.sematicsmetamodel;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class SoftSemanticConceptSatisficing extends AbstractSemanticVertex {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 134266317319543125L;
	private ConditionalExpression conditionalExpression;
	
	
	public SoftSemanticConceptSatisficing(AbstractSemanticVertex semanticConcept, String name, boolean condExpression)
	{
		super (semanticConcept, name, false);
		if (condExpression)
			conditionalExpression = new ConditionalExpression();
	}
	
	public SoftSemanticConceptSatisficing(String name,  boolean condExpression)
	{
		super (name, false);
		if (condExpression)
			conditionalExpression = new ConditionalExpression();
	}
	
	public String toString()
	{

		return "SSCS: " + super.toString() + "condit: " + conditionalExpression.getExpression();
	}
}
