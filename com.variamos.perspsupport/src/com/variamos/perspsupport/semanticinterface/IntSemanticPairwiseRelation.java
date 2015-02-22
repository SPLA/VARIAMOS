package com.variamos.perspsupport.semanticinterface;

import java.util.List;
import java.util.Set;

/**
 * An interface for DirectSemanticEdge class, required to avoid cyclic
 * references in projects. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.semantic.semanticsupport.DirectSemanticEdge
 */
public interface IntSemanticPairwiseRelation  extends IntSemanticElement {
	public List<IntSemanticRelationType> getSemanticRelationTypes();
	public String getIdentifier();
	public Set<String> getSemanticAttributesNames();
	public List<String> getPropEditableAttributes();
	public List<String> getPanelVisibleAttributes();
	public List<String> getPropVisibleAttributes();
	public List<String> getPanelSpacersAttributes();
	
}
