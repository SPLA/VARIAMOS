package com.variamos.refas.core.sematicsmetamodel;

/**
 * A class to represent concepts for Soft concepts satisficing. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 */
public class SoftSemanticConceptSatisficing extends AbstractSemanticVertex {

	/**
	 * 
	 */
	private static final long serialVersionUID = 134266317319543125L;
	private ConditionalExpression conditionalExpression;

	public SoftSemanticConceptSatisficing(
			AbstractSemanticVertex semanticConcept, String name,
			boolean condExpression) {
		super(semanticConcept, name, false);
		if (condExpression)
			conditionalExpression = new ConditionalExpression();
	}

	public SoftSemanticConceptSatisficing(String name, boolean condExpression) {
		super(name, false);
		if (condExpression)
			conditionalExpression = new ConditionalExpression();
	}

	public String toString() {

		return "SSCS: " + super.toString() + "condit: "
				+ conditionalExpression.getExpression();
	}
}
