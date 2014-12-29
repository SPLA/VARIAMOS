package com.variamos.refas.core.sematicsmetamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.SimulationStateAttribute;

/**
 * A class to represent all elements at semantic level. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-07
 */
public class AbstractSemanticElement implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2700689903495112611L;
	private String identifier;
	private AbstractSemanticElement parent;
	public AbstractSemanticElement getParent() {
		return parent;
	}
	private List<String> disPropVisibleAttributes; //position(01-99)#variable#conditionalvariable#operator#value 
	private List<String> disPropEditableAttributes; //position(01-99)#variable#conditionalvariable#operator#value
	private List<String> disPanelVisibleAttributes; //position(01-99)#variable#conditionalvariable#operator#value
	private List<String> disPanelSpacersAttributes; //preSpacer#variable#1Spacer#2Spacer#3Spacer#...
	private List<String> simPropVisibleAttributes;
	private List<String> simPropEditableAttributes;
	private List<String> simPanelVisibleAttributes;
	private List<String> simPanelSpacersAttributes;
	private Map<String, AbstractAttribute> semanticAttributes = new HashMap<String, AbstractAttribute>();
	private Map<String, SimulationStateAttribute> simulationAttributes = new HashMap<String, SimulationStateAttribute>();

	
	public AbstractSemanticElement(	String identifier)
	{
		this(null, identifier, new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(), new ArrayList<String>());

	}
	
	public AbstractSemanticElement(AbstractSemanticVertex parentConcept,
			String identifier,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes)
	{
		this.parent = parentConcept;
		this.identifier = identifier;
		this.disPropVisibleAttributes = disPropVisibleAttributes;
		this.disPropEditableAttributes = disPropEditableAttributes;
		this.disPanelVisibleAttributes = disPanelVisibleAttributes;
		this.disPanelSpacersAttributes = disPanelSpacersAttributes;

	}
	
	public void setSimPropVisibleAttributes(
			List<String> simPropVisibleAttributes) {
		this.simPropVisibleAttributes = simPropVisibleAttributes;
	}

	public void setSimPropEditableAttributes(
			List<String> simPropEditableAttributes) {
		this.simPropEditableAttributes = simPropEditableAttributes;
	}

	public void setSimPanelVisibleAttributes(
			List<String> simPanelVisibleAttributes) {
		this.simPanelVisibleAttributes = simPanelVisibleAttributes;
	}

	public void setSimPanelSpacersAttributes(
			List<String> simPanelSpacersAttributes) {
		this.simPanelSpacersAttributes = simPanelSpacersAttributes;
	}

	public void setDisPropVisibleAttributes(
			List<String> disPropVisibleAttributes) {
		this.disPropVisibleAttributes = disPropVisibleAttributes;
	}

	public void setDisPropEditableAttributes(
			List<String> disPropEditableAttributes) {
		this.disPropEditableAttributes = disPropEditableAttributes;
	}

	public void setDisPanelVisibleAttributes(
			List<String> disPanelVisibleAttributes) {
		this.disPanelVisibleAttributes = disPanelVisibleAttributes;
	}

	public void setDisPanelSpacersAttributes(
			List<String> disPanelSpacersAttributes) {
		this.disPanelSpacersAttributes = disPanelSpacersAttributes;
	}

	public List<String> getDeclaredDisPropVisibleAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		modelingAttributesNames.addAll(disPropVisibleAttributes);
		return modelingAttributesNames;
	}

	public List<String> getDisPropVisibleAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		if (parent != null)
			modelingAttributesNames.addAll(parent.getDisPropVisibleAttributes());
		modelingAttributesNames.addAll(getDeclaredDisPropVisibleAttributes());
		return modelingAttributesNames;
	}

	public void addDisPropVisibleAttribute(String visibleAttribute) {
		disPropVisibleAttributes.add(visibleAttribute);
	}

	public List<String> getDeclaredDisPropEditableAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		modelingAttributesNames.addAll(disPropEditableAttributes);
		return modelingAttributesNames;
	}

	public List<String> getDisPropEditableAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		if (parent != null)
			modelingAttributesNames.addAll(parent.getDisPropEditableAttributes());
		modelingAttributesNames.addAll(getDeclaredDisPropEditableAttributes());
		return modelingAttributesNames;
	}

	public void addDisPropEditableAttribute(String editableAttribute) {
		disPropEditableAttributes.add(editableAttribute);
	}

	public List<String> getDeclaredDisPanelVisibleAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		modelingAttributesNames.addAll(disPanelVisibleAttributes);
		return modelingAttributesNames;
	}

	public List<String> getDisPanelVisibleAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		if (parent != null)
			modelingAttributesNames.addAll(parent.getDisPanelVisibleAttributes());
		modelingAttributesNames.addAll(getDeclaredDisPanelVisibleAttributes());
		return modelingAttributesNames;
	}

	public void addDisPanelVisibleAttribute(String visibleAttribute) {
		disPanelVisibleAttributes.add(visibleAttribute);
	}

	public List<String> getDeclaredDisPanelSpacersAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		modelingAttributesNames.addAll(disPanelSpacersAttributes);
		return modelingAttributesNames;
	}

	public List<String> getDisPanelSpacersAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		if (parent != null)
			modelingAttributesNames.addAll(parent.getDisPanelSpacersAttributes());
		modelingAttributesNames.addAll(getDeclaredDisPanelSpacersAttributes());
		return modelingAttributesNames;
	}

	public void addDisPanelSpacersAttribute(String spacerAttribute) {
		disPanelSpacersAttributes.add(spacerAttribute);
	}
	
	public Set<String> getSemanticAttributesNames() {
		Set<String> properties = new HashSet<String>();
		properties.addAll(getDeclaredSemanticAttributes());
		if (parent != null)
			properties.addAll(parent.getSemanticAttributesNames());
		return properties;
	}
	
	public Map<String,AbstractAttribute> getSemanticAttributes() {
		return semanticAttributes;
	}
	public Set<String> getDeclaredSemanticAttributes() {
		Set<String> abstractAttributesNames = new HashSet<String>();
		abstractAttributesNames.addAll(semanticAttributes.keySet());
		return abstractAttributesNames;
	}



	public AbstractAttribute getSemanticAttribute(String name) {
		AbstractAttribute semAtt = semanticAttributes.get(name);
		if (semAtt == null && parent != null)
			return (parent.getSemanticAttribute(name));
		return semAtt;
	}

	public Set<String> getDeclaredSimulationAttributes() {
		Set<String> abstractAttributesNames = new HashSet<String>();
		abstractAttributesNames.addAll(simulationAttributes.keySet());
		return abstractAttributesNames;
	}

	public Set<String> getSimulationAttributes() {
		Set<String> properties = new HashSet<String>();
		properties.addAll(getDeclaredSimulationAttributes());
		if (parent != null)
			properties.addAll(parent.getSimulationAttributes());
		return properties;
	}

	public void setSemanticAttributes(
			Map<String, AbstractAttribute> semanticProperties) {
		this.semanticAttributes = semanticProperties;
	}

	public void setSemanticAttribute(String name,
			AbstractAttribute semanticAttribute) {
		AbstractAttribute semAtt = semanticAttributes.get(name);
		if (semAtt != null)
			semanticAttributes.put(name, semanticAttribute);
		else if (parent != null)
			parent.setSemanticAttribute(name, semanticAttribute);
	}

	public void putSemanticAttribute(String name,
			AbstractAttribute abstractAttribute) {
		this.semanticAttributes.put(name, abstractAttribute);
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getIdentifier() {
		return identifier;
	}

}
