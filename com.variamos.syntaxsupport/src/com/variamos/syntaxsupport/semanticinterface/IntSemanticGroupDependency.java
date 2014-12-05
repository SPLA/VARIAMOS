package com.variamos.syntaxsupport.semanticinterface;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public interface IntSemanticGroupDependency   extends Serializable{
	public Set<String> getSemanticAttributes();
	public Set<String> getSimulationAttributes();
	public List<String> getDisPropVisibleAttributes();
	public List<String> getDisPropEditableAttributes();
	public List<String> getDisPanelSpacersAttributes() ;
	public List<String> getDisPanelVisibleAttributes();
	public AbstractAttribute getSemanticAttribute(String name);
	public String getIdentifier();
}
