package com.variamos.dynsup.model;

import java.util.List;

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
public class OpersOverTwoRel extends OpersElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6309224856276191013L;
	private List<OpersRelType> semanticRelationTypes;

	public OpersOverTwoRel() {
		super(null);
	}

	public OpersOverTwoRel(String identifier,
			List<OpersRelType> semanticRelationTypes) {
		super(identifier);
		this.semanticRelationTypes = semanticRelationTypes;
	}

	public List<OpersRelType> getSemanticRelationTypes() {
		return semanticRelationTypes;
	}

}
