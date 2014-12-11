package com.variamos.syntaxsupport.semanticinterface;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.ModelingAttribute;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

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
	public Set<String> getSemanticAttributes();
	public Set<String> getSimulationAttributes();
	public List<String> getDisPropVisibleAttributes();
	public List<String> getDisPropEditableAttributes();
	public List<String> getDisPanelSpacersAttributes() ;
	public List<String> getDisPanelVisibleAttributes();
	public AbstractAttribute getSemanticAttribute(String name);
	
}

