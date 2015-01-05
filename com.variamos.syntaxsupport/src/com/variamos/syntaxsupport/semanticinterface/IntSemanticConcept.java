package com.variamos.syntaxsupport.semanticinterface;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.variamos.syntaxsupport.metamodelsupport.AbstractAttribute;
import com.variamos.syntaxsupport.metamodelsupport.ModelingAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SemanticAttribute;

/**
 * An interface for SemanticConcept class, required to avoid cyclic
 * references in projects. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.refas.core.sematicsmetamodel.SemanticConcept
 */
public interface IntSemanticConcept extends IntSemanticElement{
	public Set<String> getSemanticAttributesNames();	
	public List<String> getPropVisibleAttributes();
	public List<String> getPropEditableAttributes();
	public List<String> getPanelSpacersAttributes() ;
	public List<String> getPanelVisibleAttributes();
	public AbstractAttribute getSemanticAttribute(String name);
	
}

