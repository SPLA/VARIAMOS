package com.variamos.dynsup.model;

import java.util.List;

import com.variamos.dynsup.interfaces.IntOpersOverTwoRel;
import com.variamos.dynsup.interfaces.IntOpersRelType;

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
public class OpersOverTwoRel extends OpersVertex implements
		IntOpersOverTwoRel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6309224856276191013L;
	private List<IntOpersRelType> semanticRelationTypes;

	public OpersOverTwoRel() {
	}

	public OpersOverTwoRel(String identifier,
			List<IntOpersRelType> semanticRelationTypes) {
		super(identifier);
		this.semanticRelationTypes = semanticRelationTypes;
	}

	@Override
	public List<IntOpersRelType> getSemanticRelationTypes() {
		return semanticRelationTypes;
	}

}
