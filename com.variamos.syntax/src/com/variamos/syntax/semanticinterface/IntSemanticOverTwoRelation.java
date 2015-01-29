package com.variamos.syntax.semanticinterface;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.variamos.syntax.metamodelsupport.AbstractAttribute;


/**
 * An interface for SemanticGroupDependency class, required to avoid cyclic
 * references in projects. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.semantic.semanticsupport.SemanticGroupDependency
 */
public interface IntSemanticOverTwoRelation   extends Serializable{
	public Set<String> getSemanticAttributesNames();
	public List<String> getPropVisibleAttributes();
	public List<String> getPropEditableAttributes();
	public List<String> getPanelSpacersAttributes() ;
	public List<String> getPanelVisibleAttributes();
	public AbstractAttribute getSemanticAttribute(String name);
	public String getIdentifier();
	public List<IntSemanticRelationType> getSemanticRelationTypes();
}
