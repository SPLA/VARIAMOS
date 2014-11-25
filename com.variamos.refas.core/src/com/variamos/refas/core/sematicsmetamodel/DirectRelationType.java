package com.variamos.refas.core.sematicsmetamodel;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticDirectRelation;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public enum DirectRelationType implements IntSemanticDirectRelation {
	means_ends,
	preferred,
	required,
	conflict,
	alternative,
	mutex,
	implication,
	normal
	}
