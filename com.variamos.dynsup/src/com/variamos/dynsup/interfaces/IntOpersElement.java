package com.variamos.dynsup.interfaces;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.model.ElemAttribute;

/**
 * An interface for SemanticElement class, required to avoid cyclic references
 * in projects. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.semantic.semanticsupport.SemanticElement
 */
public interface IntOpersElement extends Serializable {

	String getIdentifier();

	ElemAttribute getSemanticAttribute(String attributeName,
			List<InstElement> opersParents);

	public Set<String> getDeclaredSemanticAttributesNames();

	public HashMap<String, ElemAttribute> getDeclaredSemanticAttributes();

	void setIdentifier(String value);

	public void setSemanticAttribute(String name,
			ElemAttribute semanticAttribute, List<InstElement> opersParents);

	public Map<String, ElemAttribute> getAllSemanticAttributes(
			List<InstElement> opersParents);

	public Set<String> getAllSemanticAttributesNames(
			List<InstElement> opersPparents);

	public Set<String> getAllAttributesNames(List<InstElement> syntaxParents,
			List<InstElement> opersParents);

	public List<IntMetaExpression> getDeclaredSemanticExpressions();

	public List<IntMetaExpression> getSemanticExpressions();

	public List<IntMetaExpression> getAllSemanticExpressions(
			List<InstElement> parents);

	public void setSemanticExpressions(
			List<IntMetaExpression> semanticExpressions);

	Collection<String> getPanelSpacersAttributes(List<InstElement> parents);

	Collection<String> getPropVisibleAttributes(List<InstElement> parents);

	Set<String> getPropVisibleAttributesSet(List<InstElement> parents);

	Collection<String> getPropEditableAttributes(List<InstElement> parents);

	Set<String> getPropEditableAttributesSet(List<InstElement> parents);

	Collection<String> getPanelVisibleAttributes(List<InstElement> parents);

	Collection<String> getDeclaredPanelSpacersAttributes();

	Collection<String> getDeclaredPropVisibleAttributes();

	Collection<String> getDeclaredPropEditableAttributes();

	Collection<String> getDeclaredPanelVisibleAttributes();
}
