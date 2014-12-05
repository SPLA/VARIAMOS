package com.variamos.syntaxsupport.metametamodel;

import java.awt.Color;
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
public class MetaGroupDependency extends MetaElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2544731646206260777L;
	private List<IntSemanticGroupDependency> semanticRelations;
	private List<IntSemanticGroupRelationType> semanticTypes;


	public MetaGroupDependency() {
		super();
		addMetaAttribute("semanticRelation", new SemanticAttribute("semanticRelation",
				"Class","com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency",null,""));
		this.addDisPropEditableAttribute("01#semanticRelation");
	}

	public MetaGroupDependency(String identified, String name, String style,
			int width, int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable,
			List<IntSemanticGroupDependency> semanticRelations,
			List<IntSemanticGroupRelationType> semanticTypes,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> metaAttributes) {
		super(identified, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes, metaAttributes);
		this.semanticRelations = semanticRelations;
		this.semanticTypes = semanticTypes;
		addMetaAttribute("semanticRelation", new SemanticAttribute("semanticRelation",
				"Class","com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency",null,""));
		this.addDisPropEditableAttribute("01#semanticRelation");
	}

	public MetaGroupDependency(String identified, String name, String style,
			int width, int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable,
			List<IntSemanticGroupDependency> semanticRelations,
			List<IntSemanticGroupRelationType> semanticTypes,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> metaAttributes,
			List<MetaAssociation> asOriginRelations,
			List<MetaAssociation> asDestinationRelations) {
		super(identified, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes, metaAttributes,
				asOriginRelations, asOriginRelations);
		this.semanticRelations = semanticRelations;
		this.semanticTypes = semanticTypes;
		addMetaAttribute("semanticRelation", new SemanticAttribute("semanticRelation",
				"Class","com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency",null,""));
		this.addDisPropEditableAttribute("01#semanticRelation");
	}

	public MetaGroupDependency(String identified, String name, String style,
			int width, int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable,
			List<IntSemanticGroupDependency> semanticRelations) {
		super(identified, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable);
		this.semanticRelations = semanticRelations;
		addMetaAttribute("semanticRelation", new SemanticAttribute("semanticRelation",
				"Class","com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency",null,""));
		this.addDisPropEditableAttribute("01#semanticRelation");
	}

	public List<IntSemanticGroupDependency> getSemanticRelations() {
		return semanticRelations;
	}

	public Set<String> getDisPropVisibleAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();

//		if (semanticRelations != null)
//			for (IntSemanticGroupDependency semanticRelation : semanticRelations)
//				metaAttributesNames.addAll(semanticRelation
//						.getDisPropVisibleAttributes());

		metaAttributesNames.addAll(super.getDisPropVisibleAttributes());
		return metaAttributesNames;
	}

	public Set<String> getDisPropEditableAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();

//		if (semanticRelations != null)
//			for (IntSemanticGroupDependency semanticRelation : semanticRelations)
//				metaAttributesNames.addAll(semanticRelation
//						.getDisPropEditableAttributes());

		metaAttributesNames.addAll(super.getDisPropEditableAttributes());
		return metaAttributesNames;
	}

	public Set<String> getDisPanelVisibleAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();

//		if (semanticRelations != null)
//			for (IntSemanticGroupDependency semanticRelation : semanticRelations)
//				metaAttributesNames.addAll(semanticRelation
//						.getDisPanelVisibleAttributes());

		metaAttributesNames.addAll(super.getDisPanelVisibleAttributes());
		return metaAttributesNames;
	}

	public Set<String> getDisPanelSpacersAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();

//		if (semanticRelations != null)
//			for (IntSemanticGroupDependency semanticRelation : semanticRelations)
//				metaAttributesNames.addAll(semanticRelation
//						.getDisPanelSpacersAttributes());

		metaAttributesNames.addAll(super.getDisPanelSpacersAttributes());
		return metaAttributesNames;
	}

}
