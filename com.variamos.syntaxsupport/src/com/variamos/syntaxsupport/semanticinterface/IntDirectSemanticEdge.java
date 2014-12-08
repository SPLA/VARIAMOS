package com.variamos.syntaxsupport.semanticinterface;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.variamos.syntaxsupport.metamodel.InstAttribute;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public interface IntDirectSemanticEdge  extends IntSemanticElement {
	public List<IntDirectEdgeType> getSemanticRelationTypes();
	public String getIdentifier();
	public Set<String> getSemanticAttributes();
	public List<String> getDisPropEditableAttributes();
	public List<String> getDisPanelVisibleAttributes();
	public List<String> getDisPropVisibleAttributes();
	public List<String> getDisPanelSpacersAttributes();
	
}
