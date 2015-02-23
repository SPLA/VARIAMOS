package com.variamos.perspsupport.semanticinterface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;

/**
 * An interface for SemanticElement class, required to avoid cyclic
 * references in projects. Part of PhD work at University of Paris 1
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
	
	public Map<String,AbstractAttribute> getSemanticAttributes();

	IntSemanticElement getParent();

	public Set<String> getSemanticAttributesNames();
	
	public List<IntSemanticExpression> getSemanticExpresions();
}
