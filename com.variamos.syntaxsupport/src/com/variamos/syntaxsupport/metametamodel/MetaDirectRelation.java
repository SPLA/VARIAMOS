package com.variamos.syntaxsupport.metametamodel;

import java.util.List;
import java.util.Map;

import com.variamos.syntaxsupport.semanticinterface.IntDirectEdgeType;
import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticEdge;

/**
 * A class to represented edges of the meta model. Extends from MetaElement
 * adding the allowed semantic concepts and edge types. An dynamic attribute for
 * selected semantic concept Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-10
 * @see com.variamos.syntaxsupport.metametamodel.MetaElement
 * @see com.variamos.syntaxsupport.metametamodel.MetaEdge
 * @see com.variamos.refas.core.sematicsmetamodel.DirectSemanticEdge
 */
public class MetaDirectRelation extends MetaEdge {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8881492732074325558L;
	/**
	 * 
	 */
	private List<IntDirectSemanticEdge> semanticRelations;
	/**
	 * 
	 */
	private List<IntDirectEdgeType> semanticTypes;

	public static final
	/**
	 * CanonicalName of DirectSemanticEdge - no direct reference allowed
	 */
	String VAR_DIRECTSEMANTICEDGECLASS = "com.variamos.refas.core.sematicsmetamodel.DirectSemanticEdge",
	/**
	 * Name of the semantic relation attribute
	 */
	VAR_SEMANTICDIRECTRELATION = "semanticRelation",
	/**
	 * Display name of the semantic relation attribute
	 */
	VAR_SEMANTICDIRECTRELATIONNAME = "Semantic Relation";

	public MetaDirectRelation() {
		createModelingAttributes();
	}

	public MetaDirectRelation(String identifier, boolean visible, String name,
			String style, String description, int width, int height, String image,
			int borderStroke, MetaVertex origin, MetaVertex destination,
			List<IntDirectSemanticEdge> semanticRelations,
			List<IntDirectEdgeType> semanticTypes) {
		super(identifier, visible, name, style, description, width, height, image,
				borderStroke, origin, destination, 1, 1, 1, 1, "", "", false,
				TypeOfLine.solid);

		this.semanticRelations = semanticRelations;
		this.semanticTypes = semanticTypes;
		 createModelingAttributes();
	}

	public MetaDirectRelation(String identifier, boolean visible, String name,
			String style, String description, int width, int height, String image,
			int borderStroke, List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> modelingAttributes,
			MetaVertex origin, MetaVertex destination, int iniLowCardinality,
			int iniHighCardinality, int endLowCardinality,
			int endHighCardinality, String iniDescription,
			String endDescription, boolean arrowDirection, TypeOfLine typeOfLine) {
		super(identifier, visible, name, style, description, width, height, image,
				borderStroke, disPropVisibleAttributes,
				disPropEditableAttributes, disPanelVisibleAttributes,
				disPanelSpacersAttributes, modelingAttributes, origin,
				destination, iniLowCardinality, iniHighCardinality,
				endLowCardinality, endHighCardinality, iniDescription,
				endDescription, arrowDirection, typeOfLine);
		 createModelingAttributes();
	}

	public void createModelingAttributes() {
		addModelingAttribute(VAR_SEMANTICDIRECTRELATION, new SemanticAttribute(
				VAR_SEMANTICDIRECTRELATION, "Class", true,
				VAR_SEMANTICDIRECTRELATIONNAME, VAR_DIRECTSEMANTICEDGECLASS,
				null, ""));
		this.addDisPropEditableAttribute("03#" + VAR_SEMANTICDIRECTRELATION);
		this.addDisPropVisibleAttribute("03#" + VAR_SEMANTICDIRECTRELATION);
	}

	public void setSemanticRelation(String identifier,
			List<IntDirectSemanticEdge> semanticRelations) {
		this.semanticRelations = semanticRelations;
	}

	public SemanticAttribute getSemanticRelation() {
		return (SemanticAttribute) getModelingAttribute(VAR_SEMANTICDIRECTRELATION);
	}

	public List<IntDirectEdgeType> getSemanticTypes() {
		return semanticTypes;
	}

	public void setSemanticTypes(List<IntDirectEdgeType> semanticTypes) {
		this.semanticTypes = semanticTypes;
	}

	public boolean addSemanticType(IntDirectEdgeType semanticType) {
		this.semanticTypes.add(semanticType);
		return true;
	}

	public List<IntDirectSemanticEdge> getSemanticRelations() {
		return semanticRelations;
	}

}
