package com.variamos.syntaxsupport.semanticinterface;

import java.util.List;
import java.util.Set;

/**
 * An interface for DirectSemanticEdge class, required to avoid cyclic
 * references in projects. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.refas.core.sematicsmetamodel.DirectSemanticEdge
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
