package com.variamos.refas.core.sematicsmetamodel;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupRelation;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public enum GroupRelationType implements IntSemanticGroupRelation {
	means_ends,
	required,	
	conflict,
	alternative,
	mutex,
	implementation,
	implication
	}
