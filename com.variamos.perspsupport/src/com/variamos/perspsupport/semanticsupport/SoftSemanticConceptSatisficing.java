package com.variamos.perspsupport.semanticsupport;

import java.util.List;

import com.variamos.perspsupport.semanticinterface.IntSemanticRelationType;

/**
 * A class to represent concepts for Soft concepts satisficing. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 */
public class SoftSemanticConceptSatisficing extends SemanticOverTwoRelation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 134266317319543125L;

	public SoftSemanticConceptSatisficing() {
		super();
	}
	
	public SoftSemanticConceptSatisficing(
			AbstractSemanticVertex semanticConcept, String name,
			boolean exclusive, List<IntSemanticRelationType> semanticRelationTypes) {
		super(semanticConcept, name, exclusive, semanticRelationTypes);
	//	if (condExpression)
	//		conditionalExpression = new ConditionalExpression();
		//addPanelSpacersAttribute("#" + this.VAR_RELATIONTYPE_IDEN + "#\n\n");
	}

	public SoftSemanticConceptSatisficing(String name, boolean exclusive,
			List<IntSemanticRelationType> semanticRelationTypes) {
		super(name, exclusive, semanticRelationTypes, true);
	//	if (condExpression)
	//		conditionalExpression = new ConditionalExpression();
	}

	public String toString() {

		return "SSCS: " + super.toString();
	}
}
