package com.variamos.perspsupport.syntaxsupport;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.semanticinterface.IntSemanticConcept;
import com.variamos.perspsupport.semanticinterface.IntSemanticOverTwoRelation;
import com.variamos.perspsupport.semanticinterface.IntSemanticPairwiseRelation;
import com.variamos.perspsupport.semanticinterface.IntSemanticRelationType;
import com.variamos.perspsupport.semanticsupport.SemanticOverTwoRelation;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class MetaOverTwoRelation extends MetaVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2544731646206260777L;
	// new
//	private IntSemanticOverTwoRelation semanticOverTwoRelation;

	public static final String
	/**
	 * 
	 */
	VAR_SEMANTICPAIRWISEREL = "semPairwiseRel",
	/**
			 * 
			 */
	VAR_SEMANTICPAIRWISEREL_NAME = "Semantic Group Dependency",
	/**
					 * 
					 */
	VAR_SEMANTICGROUPDEPENDENCYIDE = "semGroupDepIde",
	/**
					 * 
					 */
	VAR_SEMANTICPAIRWISEREL_CLASS = IntSemanticPairwiseRelation.class
			.getCanonicalName();

	public MetaOverTwoRelation() {
		super();
		createGroupDepModelingAttributes();
	}

	public MetaOverTwoRelation(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, InstElement instSemanticElement, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> modelingAttributes) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, instSemanticElement, topConcept, backgroundColor, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes,
				modelingAttributes);
		createGroupDepModelingAttributes();
	}

	public MetaOverTwoRelation(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, InstElement instSemanticElement, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> modelingAttributes,
			List<MetaPairwiseRelation> asOriginRelations,
			List<MetaPairwiseRelation> asDestinationRelations) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, instSemanticElement, topConcept, backgroundColor, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes,
				modelingAttributes, asOriginRelations, asOriginRelations);
		createGroupDepModelingAttributes();
	}

	public MetaOverTwoRelation(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, boolean topConcept, String backgroundColor,
			int borderStroke, InstElement instSemanticElement, boolean resizable) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, instSemanticElement, topConcept, backgroundColor, resizable);
		createGroupDepModelingAttributes();
	}

	// new
	public MetaOverTwoRelation(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, boolean topConcept, String backgroundColor,
			int borderStroke, InstElement instSemanticElement, boolean resizable,
			IntSemanticOverTwoRelation intSemanticOverTwoRelation) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, instSemanticElement, topConcept, backgroundColor, resizable);
//		this.semanticOverTwoRelation = intSemanticOverTwoRelation;
		createGroupDepModelingAttributes();
	}

	private void createGroupDepModelingAttributes() {
		/*
		 * addModelingAttribute(VAR_SEMANTICPAIRWISEREL, new
		 * SemanticAttribute(VAR_SEMANTICPAIRWISEREL, "Class", true,
		 * VAR_SEMANTICPAIRWISEREL_NAME, VAR_SEMANTICPAIRWISEREL_CLASS,
		 * "OperGoalOverTwoRel", ""));
		 */
		// TODO include attribute based on other object values, cardinalityType
		// from semanticTypes
		// this.addPropEditableAttribute("01#" + VAR_SEMANTICPAIRWISEREL);
		// this.addPropVisibleAttribute("01#" + VAR_SEMANTICPAIRWISEREL);
	}

	public Set<String> getPropVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getInstSemanticElement().getEditableSemanticElement()
					.getPropVisibleAttributes());

		modelingAttributesNames.addAll(super.getPropVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPropEditableAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getInstSemanticElement().getEditableSemanticElement()
					.getPropEditableAttributes());

		modelingAttributesNames.addAll(super.getPropEditableAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPanelVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getInstSemanticElement().getEditableSemanticElement()
					.getPanelVisibleAttributes());

		modelingAttributesNames.addAll(super.getPanelVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPanelSpacersAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getInstSemanticElement().getEditableSemanticElement()
					.getPanelSpacersAttributes());

		modelingAttributesNames.addAll(super.getPanelSpacersAttributes());
		return modelingAttributesNames;
	}

	@Override
	public Set<String> getAllAttributesNames(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();
		if (getInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getInstSemanticElement().getEditableSemanticElement()
					.getSemanticAttributesNames());
		modelingAttributesNames.addAll(this.getModelingAttributesNames());
		return modelingAttributesNames;
	}

	public AbstractAttribute getSemanticAttribute(String name) {
		return getInstSemanticElement().getEditableSemanticElement().getSemanticAttribute(name);
	}

	public List<IntSemanticRelationType> getSemanticRelationTypes() {
		return ((SemanticOverTwoRelation)getInstSemanticElement().getEditableSemanticElement()).getSemanticRelationTypes();
	}
	/**
	 * Name changed from standard to avoid graph serialization of the object
	 * Emulates the transient attribute modifier
	 * @return
	 */
	public IntSemanticConcept getTransSemanticConcept() {
		return (IntSemanticConcept) getInstSemanticElement().getEditableSemanticElement();
	}
}
