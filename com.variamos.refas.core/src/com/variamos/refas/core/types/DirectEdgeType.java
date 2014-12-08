package com.variamos.refas.core.types;

import com.variamos.syntaxsupport.semanticinterface.IntDirectEdgeType;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public enum DirectEdgeType implements IntDirectEdgeType {
	means_ends,
	preferred,
	required,
	conflict,
	alternative,
	implication,
	normal,
	implementation
	}
