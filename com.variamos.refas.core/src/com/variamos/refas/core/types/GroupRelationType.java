package com.variamos.refas.core.types;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticOverTwoRelType;

/**
 * An enumeration to represent possible group relations types. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 */
public enum GroupRelationType implements IntSemanticOverTwoRelType {
	means_ends,
	required,	
	conflict,
	alternative,
	implementation,
	implication
	}
