package com.variamos.syntaxsupport.metamodelsupport;

import java.util.List;
import java.util.Map;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticPairwiseRelType;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticPairwiseRelation;
/**
 * A class to represented edges of the meta model. Extends from MetaElement
 * adding the allowed semantic concepts and edge types. An dynamic attribute for
 * selected semantic concept Part of PhD work at University of Paris 1
 * Refactor: Merged with MetaEdge
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-10
 * @see com.variamos.syntaxsupport.metamodelsupport.MetaElement
 *  * @see com.variamos.refas.core.sematicsmetamodel.SemanticPairwiseRelation
 */
public class MetaPairwiseRelation extends MetaElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2665541567934067387L;
	private MetaVertex origin;
	private MetaVertex destination;

	private int iniLowCardinality;
	private int iniHighCardinality;
	private int endLowCardinality;
	private int endHighCardinality;
	private String iniDescription;
	private String endDescription;
	private boolean arrowDirection;
	private TypeOfLine typeOfLine;
	/**
	 * 
	 */
	private List<IntSemanticPairwiseRelation> semanticRelations;
	/**
	 * 
	 */
	private List<IntSemanticPairwiseRelType> semanticTypes;
	
	public static final
	/**
	 * CanonicalName of DirectSemanticEdge - no direct reference allowed
	 */
	String VAR_SEMANTICPAIRWISEREL_CLASS = IntSemanticPairwiseRelation.class.getCanonicalName(),
	/**
	 * Name of the semantic relation attribute
	 */
	VAR_SEMANTICPAIRWISEREL_IDEN = "semanticRelation",
	/**
	 * Display name of the semantic relation attribute
	 */
	VAR_SEMANTICPAIRWISEREL_NAME = "Semantic Relation",
	/**
	 * 
	 */
	VAR_METAPAIRWISERELTYPE="directEdgeType",
	/**
	 * 
	 * 
	 */
	VAR_METAPAIRWISERELTYPE_NAME = "Relation Type",
	/**
	 * 
	 */
	VAR_METAPAIRWISERELTYPE_CLASS = "com.variamos.refas.core.types.DirectEdgeType",
			/**
			 * 
			 */
			VAR_METAGENERALCONSTRAINT="generalConstraint",
			/**
			 * 
			 */
			VAR_METAGENERALCONSTRAINTNAME = "Constraint Expression";

	public MetaPairwiseRelation() {

		createModelingAttributes();
	}

	public MetaPairwiseRelation(String identifier, boolean visible, String name,
			String style, String description, int width, int height, String image,
			int borderStroke, MetaVertex origin, MetaVertex destination,
			List<IntSemanticPairwiseRelation> semanticRelations,
			List<IntSemanticPairwiseRelType> semanticTypes) {
		this(identifier, visible, name, style, description, width, height, image,
				borderStroke, origin, destination, 1, 1, 1, 1, "", "", false,
				TypeOfLine.solid);

		this.semanticRelations = semanticRelations;
		this.semanticTypes = semanticTypes;
		 createModelingAttributes();
	}
		
	public MetaPairwiseRelation(String identifier, boolean visible, String name, String style, String description, int width,
			int height, String image, int borderStroke,
			MetaVertex origin, MetaVertex destination) {
		this(identifier, visible, name, style, description, width,
				height, image, borderStroke,
				origin, destination, 1, 1, 1, 1, "", "", false,
				TypeOfLine.solid);
		createModelingAttributes();
	}

	public MetaPairwiseRelation(String identifier, boolean visible, String name, String style, String description, int width,
			int height, String image, int borderStroke,
			MetaVertex origin,
			MetaVertex destination,
			List<IntSemanticPairwiseRelation> directSemanticEdges) {
		this(identifier, visible, name, style, description, width,
				height, image, borderStroke,
				 origin, destination, 1, 1, 1, 1, "", "", false,
				TypeOfLine.solid);
		createModelingAttributes();
	}

	public MetaPairwiseRelation(String identifier, boolean visible, String name, String style, String description, int width,
			int height, String image, int borderStroke,			
			MetaVertex origin,
			MetaVertex destination,int iniLowCardinality,
			int iniHighCardinality, int endLowCardinality,
			int endHighCardinality, String iniDescription,
			String endDescription, boolean arrowDirection, TypeOfLine typeOfLine) {
		super( identifier, visible, name, style, description, width,
				height, image, borderStroke);
		this.origin = origin;
		this.destination = destination;

		this.iniLowCardinality = iniLowCardinality;
		this.iniHighCardinality = iniHighCardinality;
		this.endLowCardinality = endLowCardinality;
		this.endHighCardinality = endHighCardinality;
		this.iniDescription = iniDescription;
		this.endDescription = endDescription;
		this.arrowDirection = arrowDirection;
		this.typeOfLine = typeOfLine;
		createModelingAttributes();
	}
	
	public MetaPairwiseRelation(String identifier, boolean visible, String name, String style, String description, int width,
			int height, String image, int borderStroke,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> modelingAttributes,
			MetaVertex origin,
			MetaVertex destination, int iniLowCardinality,
			int iniHighCardinality, int endLowCardinality,
			int endHighCardinality, String iniDescription,
			String endDescription, boolean arrowDirection, TypeOfLine typeOfLine) {
		super( identifier, visible, name, style, description, width,
				height, image, borderStroke,
				disPropVisibleAttributes,
				 disPropEditableAttributes,
			disPanelVisibleAttributes,
				 disPanelSpacersAttributes,
				  modelingAttributes);
		this.origin = origin;
		this.destination = destination;
		this.iniLowCardinality = iniLowCardinality;
		this.iniHighCardinality = iniHighCardinality;
		this.endLowCardinality = endLowCardinality;
		this.endHighCardinality = endHighCardinality;
		this.iniDescription = iniDescription;
		this.endDescription = endDescription;
		this.arrowDirection = arrowDirection;
		this.typeOfLine = typeOfLine;
		createModelingAttributes();
	}
	
	public void createModelingAttributes() {
		//TODO include attribute based on other object values, cardinalityType from semanticTypes
		addModelingAttribute(VAR_SEMANTICPAIRWISEREL_IDEN, new SemanticAttribute(
				VAR_SEMANTICPAIRWISEREL_IDEN, "Class", true,
				VAR_SEMANTICPAIRWISEREL_NAME, VAR_SEMANTICPAIRWISEREL_CLASS,
				"means ends", ""));
		addModelingAttribute(VAR_METAPAIRWISERELTYPE, new SemanticAttribute(
				VAR_METAPAIRWISERELTYPE, "Enumeration", true,
				VAR_METAPAIRWISERELTYPE_NAME, VAR_METAPAIRWISERELTYPE_CLASS,
				"means_ends", ""));

		addModelingAttribute(VAR_METAGENERALCONSTRAINT, new SemanticAttribute(
				VAR_METAGENERALCONSTRAINT, "String", false,
				VAR_METAGENERALCONSTRAINTNAME, ""));

		
		this.addPropEditableAttribute("03#" + VAR_SEMANTICPAIRWISEREL_IDEN);
		this.addPropVisibleAttribute("03#" + VAR_SEMANTICPAIRWISEREL_IDEN);
		
		this.addPropEditableAttribute("04#" + VAR_METAPAIRWISERELTYPE);
		this.addPropVisibleAttribute("04#" + VAR_METAPAIRWISERELTYPE);
		
		this.addPropEditableAttribute("05#" + VAR_METAGENERALCONSTRAINT +"#"+VAR_METAPAIRWISERELTYPE+"#==#"+"generalConstraint");
		this.addPropVisibleAttribute("05#" + VAR_METAGENERALCONSTRAINT +"#"+VAR_METAPAIRWISERELTYPE+"#==#"+"generalConstraint");
	}

	public void setOrigin(MetaVertex origin) {
		this.origin = origin;
	}

	public void setDestination(MetaVertex destination) {
		this.destination = destination;
	}

	public MetaVertex getOrigin() {
		return origin;
	}

	public MetaVertex getDestination() {
		return destination;
	}

	public void setIniLowCardinality(int iniLowCardinality) {
		this.iniLowCardinality = iniLowCardinality;
	}

	public void setIniHighCardinality(int iniHighCardinality) {
		this.iniHighCardinality = iniHighCardinality;
	}

	public void setEndLowCardinality(int endLowCardinality) {
		this.endLowCardinality = endLowCardinality;
	}

	public void setEndHighCardinality(int endHighCardinality) {
		this.endHighCardinality = endHighCardinality;
	}

	public void setIniDescription(String iniDescription) {
		this.iniDescription = iniDescription;
	}

	public void setEndDescription(String endDescription) {
		this.endDescription = endDescription;
	}

	public void setArrowDirection(boolean arrowDirection) {
		this.arrowDirection = arrowDirection;
	}

	public void setTypeOfLine(TypeOfLine typeOfLine) {
		this.typeOfLine = typeOfLine;
	}

	public int getIniLowCardinality() {
		return iniLowCardinality;
	}

	public int getIniHighCardinality() {
		return iniHighCardinality;
	}

	public int getEndLowCardinality() {
		return endLowCardinality;
	}

	public int getEndHighCardinality() {
		return endHighCardinality;
	}

	public String getIniDescription() {
		return iniDescription;
	}

	public String getEndDescription() {
		return endDescription;
	}

	public boolean isArrowDirection() {
		return arrowDirection;
	}

	public TypeOfLine getTypeOfLine() {
		return typeOfLine;
	}

	public String toString() {
		return "MetaGroupRelation [metaConcept=" + origin + ", dependency="
				+ destination + "]";
	}

	public static String getClassId() {
		return "E";
	}
	
	public void setSemanticRelation(String identifier,
			List<IntSemanticPairwiseRelation> semanticRelations) {
		this.semanticRelations = semanticRelations;
	}

	public SemanticAttribute getSemanticRelation() {
		return (SemanticAttribute) getModelingAttribute(VAR_SEMANTICPAIRWISEREL_IDEN);
	}

	public List<IntSemanticPairwiseRelType> getSemanticTypes() {
		return semanticTypes;
	}

	public void setSemanticTypes(List<IntSemanticPairwiseRelType> semanticTypes) {
		this.semanticTypes = semanticTypes;
	}

	public boolean addSemanticType(IntSemanticPairwiseRelType semanticType) {
		this.semanticTypes.add(semanticType);
		return true;
	}

	public List<IntSemanticPairwiseRelation> getSemanticRelations() {
		return semanticRelations;
	}

}
