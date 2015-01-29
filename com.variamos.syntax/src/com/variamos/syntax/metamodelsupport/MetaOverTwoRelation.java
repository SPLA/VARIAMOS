package com.variamos.syntax.metamodelsupport;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntax.semanticinterface.IntSemanticOverTwoRelation;
import com.variamos.syntax.semanticinterface.IntSemanticPairwiseRelation;
import com.variamos.syntax.semanticinterface.IntSemanticRelationType;

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
	private IntSemanticOverTwoRelation semanticOverTwoRelation;

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
			String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> modelingAttributes) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes,
				modelingAttributes);
		createGroupDepModelingAttributes();
	}

	public MetaOverTwoRelation(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> modelingAttributes,
			List<MetaPairwiseRelation> asOriginRelations,
			List<MetaPairwiseRelation> asDestinationRelations) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes,
				modelingAttributes, asOriginRelations, asOriginRelations);
		createGroupDepModelingAttributes();
	}

	public MetaOverTwoRelation(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, boolean topConcept, String backgroundColor,
			int borderStroke, boolean resizable) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable);
		createGroupDepModelingAttributes();
	}

	// new
	public MetaOverTwoRelation(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, boolean topConcept, String backgroundColor,
			int borderStroke, boolean resizable,
			IntSemanticOverTwoRelation intSemanticOverTwoRelation) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable);
		this.semanticOverTwoRelation = intSemanticOverTwoRelation;
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

		if (semanticOverTwoRelation != null)
			modelingAttributesNames.addAll(semanticOverTwoRelation
					.getPropVisibleAttributes());

		modelingAttributesNames.addAll(super.getPropVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPropEditableAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticOverTwoRelation != null)
			modelingAttributesNames.addAll(semanticOverTwoRelation
					.getPropEditableAttributes());

		modelingAttributesNames.addAll(super.getPropEditableAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPanelVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticOverTwoRelation != null)
			modelingAttributesNames.addAll(semanticOverTwoRelation
					.getPanelVisibleAttributes());

		modelingAttributesNames.addAll(super.getPanelVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPanelSpacersAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticOverTwoRelation != null)
			modelingAttributesNames.addAll(semanticOverTwoRelation
					.getPanelSpacersAttributes());

		modelingAttributesNames.addAll(super.getPanelSpacersAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getSemanticAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		if (semanticOverTwoRelation != null)
			modelingAttributesNames.addAll(semanticOverTwoRelation
					.getSemanticAttributesNames());
		return modelingAttributesNames;
	}

	public AbstractAttribute getSemanticAttribute(String name) {
		return semanticOverTwoRelation.getSemanticAttribute(name);
	}

	public List<IntSemanticRelationType> getSemanticRelationTypes() {
		return semanticOverTwoRelation.getSemanticRelationTypes();
	}
}
