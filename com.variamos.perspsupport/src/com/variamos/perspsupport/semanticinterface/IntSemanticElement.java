package com.variamos.perspsupport.semanticinterface;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;

/**
 * An interface for SemanticElement class, required to avoid cyclic references
 * in projects. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.semantic.semanticsupport.SemanticElement
 */
public interface IntSemanticElement extends Serializable {

	String getIdentifier();

	AbstractAttribute getSemanticAttribute(String attributeName);

	public Set<String> getDeclaredSemanticAttributes();

	void setIdentifier(String value);

	public Map<String, AbstractAttribute> getAllSemanticAttributes();

	IntSemanticElement getParent();

	public Set<String> getSemanticAttributesNames();

	public Set<String> getAllAttributesNames(List<InstElement> parents);

	public List<IntSemanticExpression> getDeclaredSemanticExpressions();

	public List<IntSemanticExpression> getSemanticExpressions();

	public List<IntSemanticExpression> getAllSemanticExpressions();

	public void setSemanticExpressions(
			List<IntSemanticExpression> semanticExpressions);

	Collection<? extends String> getPanelSpacersAttributes();

	Collection<? extends String> getPropVisibleAttributes();

	Collection<? extends String> getPropEditableAttributes();

	Collection<? extends String> getPanelVisibleAttributes();
}
