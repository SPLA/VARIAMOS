package com.variamos.syntaxsupport.metametamodel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticConcept;
import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticRelation;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class MetaConcept extends MetaElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8416609016211900965L;
	private IntSemanticConcept semanticConcept;
	private List<MetaExtends> metaExtendsRelations;
	
	public MetaConcept()
	{
		super();
		
	}
	
	public MetaConcept(String identified, String name, String style, int width,
			int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable, 
			IntSemanticConcept semanticConcept,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String,	AbstractAttribute> attributes,
			List<MetaExtends> metaExtendsRelations) {
		super(identified, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes, attributes);
		this.semanticConcept = semanticConcept;
		this.metaExtendsRelations = metaExtendsRelations;
	}
	
	public MetaConcept(String identified, String name, String style, int width,
			int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable, 
			IntSemanticConcept semanticConcept,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String,	AbstractAttribute> attributes) {
		super(identified, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes, attributes);
		this.semanticConcept = semanticConcept;
		this.metaExtendsRelations = new ArrayList<MetaExtends>();
	}

	public MetaConcept(String identified, String name, String style, int width,
			int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable,
			IntSemanticConcept semanticConcept) {
		super(identified, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable);
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
		Set<String> metaAttributesNames = new HashSet<String>();
		
		if (semanticConcept != null)
			metaAttributesNames.addAll(semanticConcept.getDisPropVisibleAttributes());
		for (int i = 0; i < metaExtendsRelations.size(); i++) {
			MetaExtends metaExtends = metaExtendsRelations.get(i);
			metaAttributesNames.addAll(metaExtends.getDestination()
						.getDisPropVisibleAttributes());
			
		}
		metaAttributesNames.addAll(super.getDisPropVisibleAttributes());
		return metaAttributesNames;
	}
	
	public Set<String> getDisPropEditableAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();
		
		if (semanticConcept != null)
			metaAttributesNames.addAll(semanticConcept.getDisPropEditableAttributes());
		
		for (int i = 0; i < metaExtendsRelations.size(); i++) {
			MetaExtends metaExtends = metaExtendsRelations.get(i);
			metaAttributesNames.addAll(metaExtends.getDestination()
						.getDisPropEditableAttributes());
			
		}
		metaAttributesNames.addAll(super.getDisPropEditableAttributes());
		return metaAttributesNames;
	}
	
	public Set<String> getDisPanelVisibleAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();
		
		if (semanticConcept != null)
			metaAttributesNames.addAll(semanticConcept.getDisPanelVisibleAttributes());
		
		for (int i = 0; i < metaExtendsRelations.size(); i++) {
			MetaExtends metaExtends = metaExtendsRelations.get(i);
			metaAttributesNames.addAll(metaExtends.getDestination()
						.getDisPanelVisibleAttributes());
			
		}
		metaAttributesNames.addAll(super.getDisPanelVisibleAttributes());
		return metaAttributesNames;
	}
	
	public Set<String> getDisPanelSpacersAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();

		if (semanticConcept != null)
			metaAttributesNames.addAll(semanticConcept.getDisPanelSpacersAttributes());

		for (int i = 0; i < metaExtendsRelations.size(); i++) {
			MetaExtends metaExtends = metaExtendsRelations.get(i);
			metaAttributesNames.addAll(metaExtends.getDestination()
						.getDisPanelSpacersAttributes());			
		}

		metaAttributesNames.addAll(super.getDisPanelSpacersAttributes());
		return metaAttributesNames;
	}
	
	public Set<String> getMetaAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();
		metaAttributesNames.addAll(super.getMetaAttributes());
		for (int i = 0; i < metaExtendsRelations.size(); i++) {
			MetaExtends metaDirectRelation = metaExtendsRelations.get(i);
			metaAttributesNames.addAll(metaDirectRelation.getDestination()
						.getMetaAttributes());
			
		}
		return metaAttributesNames;
	}

	public Set<String> getSemanticAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();
		metaAttributesNames.addAll(semanticConcept.getSemanticAttributes());
		for (int i = 0; i < metaExtendsRelations.size(); i++) {
			MetaExtends metaDirectRelation = metaExtendsRelations.get(i);
			metaAttributesNames.addAll(metaDirectRelation.getDestination()
						.getSemanticAttributes());
			
		}
		return metaAttributesNames;
	}
	
	public AbstractAttribute getMetaAttribute(String name) {
		if (super.getMetaAttribute(name)!= null)
			return super.getMetaAttribute(name);
		else {
			for (int i = 0; i < metaExtendsRelations.size(); i++) {
				MetaExtends metaDirectRelation = metaExtendsRelations
						.get(i);
					if (metaDirectRelation.getDestination().getMetaAttribute(
							name) != null)
						return metaDirectRelation.getDestination()
								.getMetaAttribute(name);
				
			}
		}
		return null;
	}
	
	public AbstractAttribute getSemanticAttribute(String name) {
		return semanticConcept.getSemanticAttribute(name);
	}
	
	public void addMetaExtendRelation(MetaConcept destination, boolean completeDisjoint) {
		// TODO validate
		metaExtendsRelations.add(new MetaExtends(destination,completeDisjoint));
	}
	public AbstractAttribute getAbstractAttribute(String attributeName) {
		AbstractAttribute out =	getSemanticAttribute(attributeName);
		if (out == null)
			return getMetaAttribute(attributeName);
		else 
			return out;
	}
}
