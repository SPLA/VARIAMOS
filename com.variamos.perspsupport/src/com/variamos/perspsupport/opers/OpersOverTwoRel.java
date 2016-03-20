package com.variamos.perspsupport.opers;

import java.util.List;

import com.variamos.perspsupport.opersint.IntOpersOverTwoRel;
import com.variamos.perspsupport.opersint.IntOpersRelType;

/**
 * A class to represent relations of more than two concepts at semantic level.
 * Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 * @see com.cfm.productline.
 */
public class OpersOverTwoRel extends OpersAbstractVertex implements
		IntOpersOverTwoRel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6309224856276191013L;
	private List<IntOpersRelType> semanticRelationTypes;

	public OpersOverTwoRel() {
	}

	public OpersOverTwoRel(OpersAbstractVertex parent, String identifier,
			boolean exclusive, List<IntOpersRelType> semanticRelationTypes) {
		super(parent, identifier);
		this.semanticRelationTypes = semanticRelationTypes;
	}

	public OpersOverTwoRel(OpersAbstractVertex parent, String identifier,
			List<IntOpersRelType> semanticRelationTypes) {
		this(parent, identifier, false, semanticRelationTypes);
	}

	public OpersOverTwoRel(String identifier, boolean exclusive,
			List<IntOpersRelType> semanticRelationTypes,
			boolean conditionalExpression) {
		super(identifier, true);
		this.semanticRelationTypes = semanticRelationTypes;
		// this.conditionalExpression = conditionalExpression;
	}

	@Override
	public List<IntOpersRelType> getSemanticRelationTypes() {
		return semanticRelationTypes;
	}

}
