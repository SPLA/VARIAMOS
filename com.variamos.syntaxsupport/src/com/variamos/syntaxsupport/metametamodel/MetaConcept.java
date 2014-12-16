package com.variamos.syntaxsupport.metametamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticConcept;

/**
 * A class to represented concepts of the meta model. Extends from MetaVertex
 * adding the SemanticConcept and extend relations. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.syntaxsupport.metametamodel.MetaElement
 * @see com.variamos.syntaxsupport.metametamodel.MetaVertex
 */
public class MetaConcept extends MetaVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8416609016211900965L;
	private IntSemanticConcept semanticConcept;
	private List<MetaExtends> metaExtendsRelations;

	public MetaConcept() {
		super();

	}

	public MetaConcept(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable,
			IntSemanticConcept semanticConcept,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> attributes,
			List<MetaExtends> metaExtendsRelations) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes, attributes);
		this.semanticConcept = semanticConcept;
		this.metaExtendsRelations = metaExtendsRelations;
	}

	public MetaConcept(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable,
			IntSemanticConcept semanticConcept,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> attributes) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes, attributes);
		this.semanticConcept = semanticConcept;
		this.metaExtendsRelations = new ArrayList<MetaExtends>();
	}

	public MetaConcept(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, boolean topConcept, String backgroundColor,
			int borderStroke, boolean resizable,
			IntSemanticConcept semanticConcept) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable);
		this.semanticConcept = semanticConcept;
		this.metaExtendsRelations = new ArrayList<MetaExtends>();
	}

	public IntSemanticConcept getSemanticConcept() {
		return semanticConcept;
	}

	public void setSemanticConcept(IntSemanticConcept semanticConcept) {
		this.semanticConcept = semanticConcept;
	}

	public Set<String> getDisPropVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticConcept != null)
			modelingAttributesNames.addAll(semanticConcept
					.getDisPropVisibleAttributes());
		for (int i = 0; i < metaExtendsRelations.size(); i++) {
			MetaExtends metaExtends = metaExtendsRelations.get(i);
			modelingAttributesNames.addAll(metaExtends.getDestination()
					.getDisPropVisibleAttributes());

		}
		modelingAttributesNames.addAll(super.getDisPropVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getDisPropEditableAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticConcept != null)
			modelingAttributesNames.addAll(semanticConcept
					.getDisPropEditableAttributes());

		for (int i = 0; i < metaExtendsRelations.size(); i++) {
			MetaExtends metaExtends = metaExtendsRelations.get(i);
			modelingAttributesNames.addAll(metaExtends.getDestination()
					.getDisPropEditableAttributes());

		}
		modelingAttributesNames.addAll(super.getDisPropEditableAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getDisPanelVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticConcept != null)
			modelingAttributesNames.addAll(semanticConcept
					.getDisPanelVisibleAttributes());

		for (int i = 0; i < metaExtendsRelations.size(); i++) {
			MetaExtends metaExtends = metaExtendsRelations.get(i);
			modelingAttributesNames.addAll(metaExtends.getDestination()
					.getDisPanelVisibleAttributes());

		}
		modelingAttributesNames.addAll(super.getDisPanelVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getDisPanelSpacersAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticConcept != null)
			modelingAttributesNames.addAll(semanticConcept
					.getDisPanelSpacersAttributes());

		for (int i = 0; i < metaExtendsRelations.size(); i++) {
			MetaExtends metaExtends = metaExtendsRelations.get(i);
			modelingAttributesNames.addAll(metaExtends.getDestination()
					.getDisPanelSpacersAttributes());
		}

		modelingAttributesNames.addAll(super.getDisPanelSpacersAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getModelingAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(super.getModelingAttributes());
		for (int i = 0; i < metaExtendsRelations.size(); i++) {
			MetaExtends metaDirectRelation = metaExtendsRelations.get(i);
			modelingAttributesNames.addAll(metaDirectRelation.getDestination()
					.getModelingAttributes());

		}
		return modelingAttributesNames;
	}

	public Set<String> getSemanticAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(semanticConcept.getSemanticAttributes());
		for (int i = 0; i < metaExtendsRelations.size(); i++) {
			MetaExtends metaDirectRelation = metaExtendsRelations.get(i);
			modelingAttributesNames.addAll(metaDirectRelation.getDestination()
					.getSemanticAttributes());

		}
		return modelingAttributesNames;
	}

	public AbstractAttribute getModelingAttribute(String name) {
		if (super.getModelingAttribute(name) != null)
			return super.getModelingAttribute(name);
		else {
			for (int i = 0; i < metaExtendsRelations.size(); i++) {
				MetaExtends metaDirectRelation = metaExtendsRelations.get(i);
				if (metaDirectRelation.getDestination().getModelingAttribute(
						name) != null)
					return metaDirectRelation.getDestination()
							.getModelingAttribute(name);

			}
		}
		return null;
	}

	public AbstractAttribute getSemanticAttribute(String name) {
		return semanticConcept.getSemanticAttribute(name);
	}

	public void addMetaExtendRelation(MetaConcept destination,
			boolean completeDisjoint) {
		// TODO validate
		metaExtendsRelations
				.add(new MetaExtends(destination, completeDisjoint));
	}

	public AbstractAttribute getAbstractAttribute(String attributeName) {
		AbstractAttribute out = getSemanticAttribute(attributeName);
		if (out == null)
			return getModelingAttribute(attributeName);
		else
			return out;
	}
}
