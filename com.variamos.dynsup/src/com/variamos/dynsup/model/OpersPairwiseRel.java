package com.variamos.dynsup.model;

import java.util.List;

/**
 * A class to represent the edges with semantic back object. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 * @see com.cfm.productline.
 */
public class OpersPairwiseRel extends OpersElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7976788205587295216L;
	/**
	 * 
	 */
	private List<OpersRelType> semanticRelationTypes;

	public OpersPairwiseRel() {
		super(null);
	}

	public OpersPairwiseRel(String identifier, boolean toSoftSemanticConcept,
			List<OpersRelType> semanticRelationTypes) {
		super(identifier);
		this.semanticRelationTypes = semanticRelationTypes;
	}

	public List<OpersRelType> getSemanticRelationTypes() {
		return semanticRelationTypes;
	}
}
