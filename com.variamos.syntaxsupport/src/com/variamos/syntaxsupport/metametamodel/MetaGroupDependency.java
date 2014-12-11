package com.variamos.syntaxsupport.metametamodel;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupDependency;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupRelationType;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class MetaGroupDependency extends MetaVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2544731646206260777L;
	private List<IntSemanticGroupDependency> semanticRelations;
	private List<IntSemanticGroupRelationType> semanticTypes;
	public static final String VAR_SEMANTICGROUPDEPENDENCY = "semGroupDep",
			VAR_SEMANTICGROUPDEPENDENCYNAME = "Semantic Group Dependency",
			VAR_SEMANTICGROUPDEPENDENCYIDE = "semGroupDepIde",
			VAR_SEMANTICGROUPDEPENDENCYCLASS = "com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency";

	public MetaGroupDependency() {
		super();
		addModelingAttribute(VAR_SEMANTICGROUPDEPENDENCY,
				new SemanticAttribute(VAR_SEMANTICGROUPDEPENDENCY, "Class",
						true, VAR_SEMANTICGROUPDEPENDENCYNAME,
						VAR_SEMANTICGROUPDEPENDENCYCLASS, null, ""));
		this.addDisPropEditableAttribute("01#" + VAR_SEMANTICGROUPDEPENDENCY);
	}

	public MetaGroupDependency(String identifier, boolean visible, String name,
			String style, String description, int width, int height, String image,
			int borderStroke, boolean topConcept, String backgroundColor,
			boolean resizable,
			List<IntSemanticGroupDependency> semanticRelations,
			List<IntSemanticGroupRelationType> semanticTypes,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> modelingAttributes) {
		super(identifier, visible, name, style, description, width, height, image,
				borderStroke, topConcept, backgroundColor, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes,
				modelingAttributes);
		this.semanticRelations = semanticRelations;
		this.semanticTypes = semanticTypes;
		addModelingAttribute(VAR_SEMANTICGROUPDEPENDENCY,
				new SemanticAttribute(VAR_SEMANTICGROUPDEPENDENCY, "Class",
						true, VAR_SEMANTICGROUPDEPENDENCYNAME,
						VAR_SEMANTICGROUPDEPENDENCYCLASS, null, ""));
		this.addDisPropEditableAttribute("01#" + VAR_SEMANTICGROUPDEPENDENCY);
		
		this.addDisPropVisibleAttribute("01#" + VAR_SEMANTICGROUPDEPENDENCY);
	}

	public MetaGroupDependency(String identifier, boolean visible, String name,
			String style, String description, int width, int height, String image,
			int borderStroke, boolean topConcept, String backgroundColor,
			boolean resizable,
			List<IntSemanticGroupDependency> semanticRelations,
			List<IntSemanticGroupRelationType> semanticTypes,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> modelingAttributes,
			List<MetaEdge> asOriginRelations,
			List<MetaEdge> asDestinationRelations) {
		super(identifier, visible, name, style, description, width, height, image,
				borderStroke, topConcept, backgroundColor, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes,
				modelingAttributes, asOriginRelations, asOriginRelations);
		this.semanticRelations = semanticRelations;
		this.semanticTypes = semanticTypes;
		addModelingAttribute(VAR_SEMANTICGROUPDEPENDENCY,
				new SemanticAttribute(VAR_SEMANTICGROUPDEPENDENCY, "Class",
						true, VAR_SEMANTICGROUPDEPENDENCYNAME,
						VAR_SEMANTICGROUPDEPENDENCYCLASS, null, ""));
		this.addDisPropEditableAttribute("01#" + VAR_SEMANTICGROUPDEPENDENCY);
		this.addDisPropVisibleAttribute("01#" + VAR_SEMANTICGROUPDEPENDENCY);
	}

	public MetaGroupDependency(String identifier, boolean visible, String name,
			String style, String description, int width, int height, String image,
			boolean topConcept, String backgroundColor, int borderStroke,
			boolean resizable,
			List<IntSemanticGroupDependency> semanticRelations) {
		super(identifier, visible, name, style, description, width, height, image,
				borderStroke, topConcept, backgroundColor, resizable);
		this.semanticRelations = semanticRelations;
		addModelingAttribute(VAR_SEMANTICGROUPDEPENDENCY,
				new SemanticAttribute(VAR_SEMANTICGROUPDEPENDENCY, "Class",
						true, VAR_SEMANTICGROUPDEPENDENCYNAME,
						VAR_SEMANTICGROUPDEPENDENCYCLASS, null, ""));
		this.addDisPropEditableAttribute("01#" + VAR_SEMANTICGROUPDEPENDENCY);
		this.addDisPropVisibleAttribute("01#" + VAR_SEMANTICGROUPDEPENDENCY);
	}

	public List<IntSemanticGroupDependency> getSemanticRelations() {
		return semanticRelations;
	}

	public Set<String> getDisPropVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		// if (semanticRelations != null)
		// for (IntSemanticGroupDependency semanticRelation : semanticRelations)
		// modelingAttributesNames.addAll(semanticRelation
		// .getDisPropVisibleAttributes());

		modelingAttributesNames.addAll(super.getDisPropVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getDisPropEditableAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		// if (semanticRelations != null)
		// for (IntSemanticGroupDependency semanticRelation : semanticRelations)
		// modelingAttributesNames.addAll(semanticRelation
		// .getDisPropEditableAttributes());

		modelingAttributesNames.addAll(super.getDisPropEditableAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getDisPanelVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		// if (semanticRelations != null)
		// for (IntSemanticGroupDependency semanticRelation : semanticRelations)
		// modelingAttributesNames.addAll(semanticRelation
		// .getDisPanelVisibleAttributes());

		modelingAttributesNames.addAll(super.getDisPanelVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getDisPanelSpacersAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		// if (semanticRelations != null)
		// for (IntSemanticGroupDependency semanticRelation : semanticRelations)
		// modelingAttributesNames.addAll(semanticRelation
		// .getDisPanelSpacersAttributes());

		modelingAttributesNames.addAll(super.getDisPanelSpacersAttributes());
		return modelingAttributesNames;
	}

}
