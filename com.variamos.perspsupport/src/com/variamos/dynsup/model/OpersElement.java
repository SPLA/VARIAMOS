package com.variamos.dynsup.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.interfaces.IntMetaExpression;
import com.variamos.dynsup.interfaces.IntOpersElement;

/**
 * A class to represent all elements at semantic level. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-07
 */
public class OpersElement implements Serializable, IntOpersElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2700689903495112611L;
	private String identifier;

	private List<String> propVisibleAttributes; // position(01-99)#variable#conditionalvariable#operator#value
	private List<String> propEditableAttributes; // position(01-99)#variable#conditionalvariable#operator#value
	private List<String> panelVisibleAttributes; // position(01-99)#variable#conditionalvariable#operator#value
	private List<String> panelSpacersAttributes; // preSpacer#variable#1Spacer#2Spacer#3Spacer#...
	private Map<String, ElemAttribute> semanticAttributes = new HashMap<String, ElemAttribute>();
	private List<IntMetaExpression> semanticExpressions;

	public OpersElement(String identifier) {
		this(identifier, new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>());
	}

	/*
	 * public OpersAbstractElement(OpersAbstractElement parentElement, String
	 * identifier) { this(parentElement, identifier, new ArrayList<String>(),
	 * new ArrayList<String>(), new ArrayList<String>(), new
	 * ArrayList<String>()); }
	 */

	public OpersElement(String identifier,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSpacersAttributes) {
		this.identifier = identifier;
		this.propVisibleAttributes = propVisibleAttributes;
		this.propEditableAttributes = propEditableAttributes;
		this.panelVisibleAttributes = panelVisibleAttributes;
		this.panelSpacersAttributes = panelSpacersAttributes;
		this.semanticExpressions = new ArrayList<IntMetaExpression>();
	}

	public void setPropVisibleAttributes(List<String> disPropVisibleAttributes) {
		this.propVisibleAttributes = disPropVisibleAttributes;
	}

	public void setPropEditableAttributes(List<String> disPropEditableAttributes) {
		this.propEditableAttributes = disPropEditableAttributes;
	}

	public void setPanelVisibleAttributes(List<String> disPanelVisibleAttributes) {
		this.panelVisibleAttributes = disPanelVisibleAttributes;
	}

	public void setPanelSpacersAttributes(List<String> disPanelSpacersAttributes) {
		this.panelSpacersAttributes = disPanelSpacersAttributes;
	}

	public List<String> getDeclaredPropVisibleAttributes() {
		List<String> syntaxAttributesNames = new ArrayList<String>();
		syntaxAttributesNames.addAll(propVisibleAttributes);
		return syntaxAttributesNames;
	}

	@Override
	public List<String> getPropVisibleAttributes(List<InstElement> opersParents) {
		List<String> modelingAttributesNames = new ArrayList<String>();
		if (opersParents != null)
			for (InstElement parent : opersParents)
				modelingAttributesNames.addAll(parent
						.getEditableSemanticElement()
						.getDeclaredPropVisibleAttributes());
		modelingAttributesNames.addAll(getDeclaredPropVisibleAttributes());
		return modelingAttributesNames;
	}

	@Override
	public Set<String> getPropVisibleAttributesSet(
			List<InstElement> opersParents) {
		Set<String> modelingAttributesNames = new HashSet<String>();
		if (opersParents != null)
			for (InstElement parent : opersParents)
				modelingAttributesNames.addAll(parent
						.getEditableSemanticElement()
						.getDeclaredPropVisibleAttributes());
		modelingAttributesNames.addAll(getDeclaredPropVisibleAttributes());
		return modelingAttributesNames;
	}

	public void addPropVisibleAttribute(String visibleAttribute) {
		propVisibleAttributes.add(visibleAttribute);
	}

	@Override
	public List<String> getDeclaredPropEditableAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		modelingAttributesNames.addAll(propEditableAttributes);
		return modelingAttributesNames;
	}

	@Override
	public HashMap<String, ElemAttribute> getDeclaredSemanticAttributes() {
		HashMap<String, ElemAttribute> properties = new HashMap<String, ElemAttribute>();
		properties.putAll(semanticAttributes);
		return properties;
	}

	@Override
	public List<String> getPropEditableAttributes(
			List<InstElement> opersDirectParents) {
		List<String> modelingAttributesNames = new ArrayList<String>();
		if (opersDirectParents != null)
			for (InstElement parent : opersDirectParents)
				modelingAttributesNames.addAll(parent
						.getEditableSemanticElement()
						.getDeclaredPropEditableAttributes());
		modelingAttributesNames.addAll(getDeclaredPropEditableAttributes());
		return modelingAttributesNames;
	}

	@Override
	public Set<String> getPropEditableAttributesSet(
			List<InstElement> opersDirectParents) {
		Set<String> modelingAttributesNames = new HashSet<String>();
		if (opersDirectParents != null)
			for (InstElement parent : opersDirectParents)
				modelingAttributesNames.addAll(parent
						.getEditableSemanticElement()
						.getDeclaredPropEditableAttributes());
		modelingAttributesNames.addAll(getDeclaredPropEditableAttributes());
		return modelingAttributesNames;
	}

	public void addPropEditableAttribute(String editableAttribute) {
		propEditableAttributes.add(editableAttribute);
	}

	@Override
	public List<String> getDeclaredPanelVisibleAttributes() {
		List<String> modelingAttributesNames = new ArrayList<String>();
		modelingAttributesNames.addAll(panelVisibleAttributes);
		return modelingAttributesNames;
	}

	@Override
	public List<String> getPanelVisibleAttributes(
			List<InstElement> opersDirectParents) {
		List<String> modelingAttributesNames = new ArrayList<String>();
		if (opersDirectParents != null)
			for (InstElement parent : opersDirectParents)
				modelingAttributesNames.addAll(parent
						.getEditableSemanticElement()
						.getDeclaredPanelVisibleAttributes());
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

	public List<String> getPanelSpacersAttributes(List<InstElement> opersParents) {
		List<String> modelingAttributesNames = new ArrayList<String>();
		if (opersParents != null)
			for (InstElement parent : opersParents)
				modelingAttributesNames.addAll(parent
						.getEditableSemanticElement()
						.getDeclaredPanelSpacersAttributes());
		modelingAttributesNames.addAll(getDeclaredPanelSpacersAttributes());
		return modelingAttributesNames;
	}

	public void addPanelSpacersAttribute(String spacerAttribute) {
		panelSpacersAttributes.add(spacerAttribute);
	}

	public Set<String> getAllSemanticAttributesNames(
			List<InstElement> opersParents) {
		Set<String> properties = new HashSet<String>();
		properties.addAll(getDeclaredSemanticAttributesNames());
		if (opersParents != null)
			for (InstElement parent : opersParents)
				properties.addAll(parent.getEditableSemanticElement()
						.getAllSemanticAttributesNames(null));
		return properties;
	}

	public Map<String, ElemAttribute> getAllSemanticAttributes(
			List<InstElement> opersParents) {

		Map<String, ElemAttribute> abstractAttributes = new HashMap<String, ElemAttribute>();
		abstractAttributes.putAll(semanticAttributes);
		if (opersParents != null)
			for (InstElement parent : opersParents)
				abstractAttributes.putAll(parent.getEditableSemanticElement()
						.getAllSemanticAttributes(null));
		return abstractAttributes;
	}

	public Set<String> getDeclaredSemanticAttributesNames() {
		Set<String> abstractAttributesNames = new HashSet<String>();
		abstractAttributesNames.addAll(semanticAttributes.keySet());
		return abstractAttributesNames;
	}

	public Map<String, ElemAttribute> getSemanticAttributes() {
		return semanticAttributes;
	}

	public ElemAttribute getSemanticAttribute(String name,
			List<InstElement> opersParents) {
		ElemAttribute semAtt = semanticAttributes.get(name);
		if (semAtt == null && opersParents != null) {
			ElemAttribute out = null;
			for (InstElement parent : opersParents) {
				out = (parent.getEditableSemanticElement()
						.getSemanticAttribute(name, null));
				if (out != null)
					return out;
			}
		}
		return semAtt;
	}

	public void setSemanticAttributes(
			Map<String, ElemAttribute> semanticProperties) {
		this.semanticAttributes = semanticProperties;
	}

	@Override
	public void setSemanticAttribute(String name,
			ElemAttribute semanticAttribute, List<InstElement> opersParents) {
		ElemAttribute semAtt = semanticAttributes.get(name);
		if (semAtt != null)
			semanticAttributes.put(name, semanticAttribute);
		else if (opersParents != null)
			for (InstElement parent : opersParents)
				parent.getEditableSemanticElement().setSemanticAttribute(name,
						semanticAttribute, null);
	}

	public void putSemanticAttribute(String name,
			ElemAttribute abstractAttribute) {
		this.semanticAttributes.put(name, abstractAttribute);
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public List<IntMetaExpression> getDeclaredSemanticExpressions() {
		List<IntMetaExpression> out = new ArrayList<IntMetaExpression>();
		if (semanticExpressions != null)
			out.addAll(semanticExpressions);
		return out;
	}

	public List<IntMetaExpression> getAllSemanticExpressions(
			List<InstElement> opersParents) {
		List<IntMetaExpression> out = new ArrayList<IntMetaExpression>();
		if (semanticExpressions != null)
			out.addAll(semanticExpressions);
		if (opersParents != null)
			for (InstElement parent : opersParents)
				out.addAll(parent.getEditableSemanticElement()
						.getAllSemanticExpressions(null));
		return out;
	}

	public List<IntMetaExpression> getSemanticExpressions() {
		return semanticExpressions;
	}

	public void setSemanticExpressions(
			List<IntMetaExpression> semanticExpressions) {
		this.semanticExpressions = semanticExpressions;
	}

	public Set<String> getAllAttributesNames(List<InstElement> syntaxParents,
			List<InstElement> opersParents) {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames
				.addAll(getAllSemanticAttributesNames(opersParents));
		if (syntaxParents != null)
			for (InstElement parent : syntaxParents) {
				SyntaxConcept parentConcept = (SyntaxConcept) parent
						.getEditableMetaElement();
				modelingAttributesNames.addAll(parentConcept
						.getTransInstSemanticElement()
						.getEditableSemanticElement()
						.getAllSemanticAttributesNames(opersParents));
				modelingAttributesNames.addAll(parentConcept
						.getModelingAttributesNames(syntaxParents));
			}
		return modelingAttributesNames;
	}

}
