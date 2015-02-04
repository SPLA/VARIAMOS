package com.variamos.refas.core.sematicsmetamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.refas.core.simulationmodel.MetaExpressionSet;
import com.variamos.syntaxsupport.metamodelsupport.AbstractAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SimulationStateAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;

/**
 * A class to represent all elements at semantic level. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-07
 */
public class AbstractSemanticElement implements Serializable, IntSemanticElement{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2700689903495112611L;
	private String identifier;
	private AbstractSemanticElement parent;
	public AbstractSemanticElement getParent() {
		return parent;
	}
	private List<String> propVisibleAttributes; //position(01-99)#variable#conditionalvariable#operator#value 
	private List<String> propEditableAttributes; //position(01-99)#variable#conditionalvariable#operator#value
	private List<String> panelVisibleAttributes; //position(01-99)#variable#conditionalvariable#operator#value
	private List<String> panelSpacersAttributes; //preSpacer#variable#1Spacer#2Spacer#3Spacer#...
	private Map<String, AbstractAttribute> semanticAttributes = new HashMap<String, AbstractAttribute>();
	
	private List<MetaExpressionSet> metaExpressionSets;

	public AbstractSemanticElement(	String identifier)
	{
		this(null, identifier, new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(), new ArrayList<String>());
	}
	
	public AbstractSemanticElement(AbstractSemanticElement parentElement,	String identifier)
	{
		this(parentElement, identifier, new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(), new ArrayList<String>());
	}
	
	public AbstractSemanticElement(AbstractSemanticElement parentElement,
			String identifier,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSpacersAttributes)
	{
		this.parent = parentElement;
		this.identifier = identifier;
		this.propVisibleAttributes = propVisibleAttributes;
		this.propEditableAttributes = propEditableAttributes;
		this.panelVisibleAttributes = panelVisibleAttributes;
		this.panelSpacersAttributes = panelSpacersAttributes;

	}
	
	public void setPropVisibleAttributes(
			List<String> disPropVisibleAttributes) {
		this.propVisibleAttributes = disPropVisibleAttributes;
	}

	public void setPropEditableAttributes(
			List<String> disPropEditableAttributes) {
		this.propEditableAttributes = disPropEditableAttributes;
	}

	public void setPanelVisibleAttributes(
			List<String> disPanelVisibleAttributes) {
		this.panelVisibleAttributes = disPanelVisibleAttributes;
	}

	public void setPanelSpacersAttributes(
			List<String> disPanelSpacersAttributes) {
		this.panelSpacersAttributes = disPanelSpacersAttributes;
	}

	public List<String> getDeclaredPropVisibleAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		modelingAttributesNames.addAll(propVisibleAttributes);
		return modelingAttributesNames;
	}

	public List<String> getPropVisibleAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		if (parent != null)
			modelingAttributesNames.addAll(parent.getPropVisibleAttributes());
		modelingAttributesNames.addAll(getDeclaredPropVisibleAttributes());
		return modelingAttributesNames;
	}

	public void addPropVisibleAttribute(String visibleAttribute) {
		propVisibleAttributes.add(visibleAttribute);
	}

	public List<String> getDeclaredPropEditableAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		modelingAttributesNames.addAll(propEditableAttributes);
		return modelingAttributesNames;
	}

	public List<String> getPropEditableAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		if (parent != null)
			modelingAttributesNames.addAll(parent.getPropEditableAttributes());
		modelingAttributesNames.addAll(getDeclaredPropEditableAttributes());
		return modelingAttributesNames;
	}

	public void addPropEditableAttribute(String editableAttribute) {
		propEditableAttributes.add(editableAttribute);
	}

	public List<String> getDeclaredPanelVisibleAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		modelingAttributesNames.addAll(panelVisibleAttributes);
		return modelingAttributesNames;
	}

	public List<String> getPanelVisibleAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		if (parent != null)
			modelingAttributesNames.addAll(parent.getPanelVisibleAttributes());
		modelingAttributesNames.addAll(getDeclaredPanelVisibleAttributes());
		return modelingAttributesNames;
	}

	public void addPanelVisibleAttribute(String visibleAttribute) {
		panelVisibleAttributes.add(visibleAttribute);
	}

	public List<String> getDeclaredPanelSpacersAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		modelingAttributesNames.addAll(panelSpacersAttributes);
		return modelingAttributesNames;
	}

	public List<String> getPanelSpacersAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		if (parent != null)
			modelingAttributesNames.addAll(parent.getPanelSpacersAttributes());
		modelingAttributesNames.addAll(getDeclaredPanelSpacersAttributes());
		return modelingAttributesNames;
	}

	public void addPanelSpacersAttribute(String spacerAttribute) {
		panelSpacersAttributes.add(spacerAttribute);
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
