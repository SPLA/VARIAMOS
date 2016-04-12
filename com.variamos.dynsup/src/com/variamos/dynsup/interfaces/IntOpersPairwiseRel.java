package com.variamos.dynsup.interfaces;

import java.util.List;

/**
 * An interface for DirectSemanticEdge class, required to avoid cyclic
 * references in projects. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.semantic.semanticsupport.DirectSemanticEdge
 */
public interface IntOpersPairwiseRel extends IntOpersElement {

	public List<IntOpersRelType> getSemanticRelationTypes();
}
