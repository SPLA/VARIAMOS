package com.variamos.perspsupport.opers;

import java.util.List;

import com.variamos.perspsupport.opersint.IntOpersRelType;

/**
 * A class to represent concepts for resoning. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 */
public class OpersReasoningConcept extends OpersOverTwoRel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 134266317319543125L;

	public OpersReasoningConcept() {
		super();
	}

	/*
	 * public OpersReasoningConcept( String name, boolean exclusive,
	 * List<IntOpersRelType> semanticRelationTypes) { super( name, exclusive,
	 * semanticRelationTypes); }
	 */

	public OpersReasoningConcept(String name, boolean exclusive,
			List<IntOpersRelType> semanticRelationTypes) {
		super(name, exclusive, semanticRelationTypes, true);
	}

	public String toString() {

		return "SSCS: " + super.toString();
	}
}
