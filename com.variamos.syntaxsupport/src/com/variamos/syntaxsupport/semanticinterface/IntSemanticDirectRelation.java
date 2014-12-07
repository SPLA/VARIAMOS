package com.variamos.syntaxsupport.semanticinterface;

import java.io.Serializable;
import java.util.List;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public interface IntSemanticDirectRelation  extends Serializable {
	public List<IntDirectRelationType> getSemanticRelationTypes();
	public String getIdentifier();
	
}
