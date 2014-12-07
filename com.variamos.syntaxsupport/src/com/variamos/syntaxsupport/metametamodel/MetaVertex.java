package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticDirectRelation;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class MetaVertex extends MetaElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3348811616807875183L;
	private boolean topConcept;
	private String backgroundColor;
	private boolean resizable;
	private List<MetaEdge> asOriginRelations;
	private List<MetaEdge> asDestinationRelations;


	public void setTopConcept(boolean topConcept) {
		this.topConcept = topConcept;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}


	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public void setAsOriginRelations(List<MetaEdge> asOriginRelations) {
		this.asOriginRelations = asOriginRelations;
	}

	public MetaVertex() {

	}

	public MetaVertex(String identifier, boolean visible, String name, String style, int width,
			int height, String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable) {
		this(identifier, visible, name, style, width, height, image, borderStroke,
				topConcept,	backgroundColor,  resizable,
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new HashMap<String, AbstractAttribute>(),
				new ArrayList<MetaEdge>(), new ArrayList<MetaEdge>());

	}

	public MetaVertex(String identifier, boolean visible, String name, String style, int width,
			int height, String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> modelingAttributes) {
		this(identifier, visible, name, style, width, height, image, 
				borderStroke, topConcept, backgroundColor, resizable,
				disPropVisibleAttributes, disPropEditableAttributes,
				disPanelVisibleAttributes, disPanelSpacersAttributes,
				modelingAttributes, new ArrayList<MetaEdge>(),
				new ArrayList<MetaEdge>());

	}

	public MetaVertex(String identifier, boolean visible, String name, String style, int width,
			int height, String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> modelingAttributes,
			List<MetaEdge> asOriginRelations,
			List<MetaEdge> asDestinationRelations) {
		super(identifier, visible, name, style, width, height, image, 
				borderStroke, disPropVisibleAttributes, disPropEditableAttributes,
				disPanelVisibleAttributes, disPanelSpacersAttributes,
				modelingAttributes);


		this.backgroundColor = backgroundColor;
		this.topConcept = topConcept;
		this.resizable = resizable;
		this.asOriginRelations = asOriginRelations;
		this.asDestinationRelations = asDestinationRelations;
	}

	public List<MetaEdge> getAsOriginRelations() {
		return asOriginRelations;
	}

	public boolean isTopConcept() {
		return topConcept;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public boolean isResizable() {
		return resizable;
	}


	public boolean equals(MetaVertex obj) {
		return getIdentifier().equals(obj.getIdentifier());
	}


	public MetaEdge addAssociationAsOriginRelations(
			IntSemanticDirectRelation directRelation, MetaConcept destination) {
		MetaEdge metaAssociation = new MetaEdge(this, destination);
		asOriginRelations.add(metaAssociation);
		return metaAssociation;
	}

	public AbstractAttribute getAbstractAttribute(String attributeName) {
		return this.getModelingAttribute(attributeName);
	}
}
