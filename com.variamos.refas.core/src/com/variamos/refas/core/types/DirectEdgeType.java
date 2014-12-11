package com.variamos.refas.core.types;

import com.variamos.syntaxsupport.semanticinterface.IntDirectEdgeType;

/**
 * An enumeration to represent possible direct relations types. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 */
public enum DirectEdgeType implements IntDirectEdgeType {
	means_ends, preferred, required, conflict, alternative, implication, normal, implementation
}
