package com.variamos.syntaxsupport.metamodelsupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticConcept;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public abstract class MetaVertex extends MetaElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3348811616807875183L;
	private boolean topConcept;
	private String backgroundColor;
	private boolean resizable;
	private List<MetaPairwiseRelation> asSourceEdges;
	private List<MetaPairwiseRelation> asTargetEdges;

	public MetaVertex() {
		super();
	}

	public MetaVertex(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable) {
		this(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable,
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new HashMap<String, AbstractAttribute>(),
				new ArrayList<MetaPairwiseRelation>(), new ArrayList<MetaPairwiseRelation>());

	}

	public MetaVertex(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> modelingAttributes) {
		this(identifier, visible, name, style, description, width, height,
				image, borderStroke, topConcept, backgroundColor, resizable,
				disPropVisibleAttributes, disPropEditableAttributes,
				disPanelVisibleAttributes, disPanelSpacersAttributes,
				modelingAttributes, new ArrayList<MetaPairwiseRelation>(),
				new ArrayList<MetaPairwiseRelation>());

	}

	public MetaVertex(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> modelingAttributes,
			List<MetaPairwiseRelation> asOriginRelations,
			List<MetaPairwiseRelation> asDestinationRelations) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, disPropVisibleAttributes,
				disPropEditableAttributes, disPanelVisibleAttributes,
				disPanelSpacersAttributes, modelingAttributes);

		this.backgroundColor = backgroundColor;
		this.topConcept = topConcept;
		this.resizable = resizable;
		this.asSourceEdges = asOriginRelations;
		this.asTargetEdges = asDestinationRelations;
	}

	public void setTopConcept(boolean topConcept) {
		this.topConcept = topConcept;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public void setAsOriginEdge(List<MetaPairwiseRelation> asOriginEdge) {
		this.asSourceEdges = asOriginEdge;
	}

	public void setAsDestinationEdge(List<MetaPairwiseRelation> asDestinationEdge) {
		this.asTargetEdges = asDestinationEdge;
	}

	public List<MetaPairwiseRelation> getAsOriginRelations() {
		return asSourceEdges;
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
	
	public IntSemanticConcept getTransSemanticConcept() {
		return null;
	}

	public boolean equals(MetaVertex obj) {
		return getIdentifier().equals(obj.getIdentifier());
	}

	public void addMetaPairwiseRelAsOrigin(MetaConcept metaConcept, MetaPairwiseRelation metaEdge) {
		metaConcept.addMetaPairwiseRelAsDestination(metaEdge);
		asSourceEdges.add(metaEdge);

	}

	public void addMetaPairwiseRelAsDestination(MetaPairwiseRelation metaEdge) {
		asTargetEdges.add(metaEdge);

	}

	public AbstractAttribute getAbstractAttribute(String attributeName) {
		return this.getModelingAttribute(attributeName);
	}
	public abstract Set<String> getSemanticAttributes() ;
	public abstract AbstractAttribute getSemanticAttribute(String name); 
}
