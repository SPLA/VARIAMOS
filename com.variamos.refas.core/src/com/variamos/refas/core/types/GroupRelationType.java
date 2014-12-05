package com.variamos.refas.core.types;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupRelationType;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public enum GroupRelationType implements IntSemanticGroupRelationType {
	means_ends,
	required,	
	conflict,
	alternative,
	implementation,
	implication
	}
