package com.variamos.syntax.metamodelsupport;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntax.semanticinterface.IntSemanticPairwiseRelation;
import com.variamos.syntax.semanticinterface.IntSemanticRelationType;

/**
 * A class to represented edges of the meta model. Extends from MetaElement
 * adding the allowed semantic concepts and edge types. An dynamic attribute for
 * selected semantic concept Part of PhD work at University of Paris 1 Refactor:
 * Merged with MetaEdge
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-10
 * @see com.variamos.syntax.metamodelsupport.MetaElement * @see
 *      com.variamos.semantic.semanticsupport.SemanticPairwiseRelation
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
	private IntSemanticPairwiseRelation semanticPairwiseRelation;

	public static final/**
						 * CanonicalName of DirectSemanticEdge - no direct reference allowed
						 */
	String VAR_SEMANTICPAIRWISEREL_CLASS = IntSemanticPairwiseRelation.class
			.getCanonicalName(),
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
			VAR_METAGENERALCONSTRAINT = "generalConstraint",
			/**
			 * 
			 */
			VAR_METAGENERALCONSTRAINTNAME = "Constraint Expression";

	public MetaPairwiseRelation() {

		createPWModelingAttributes();
	}

	public MetaPairwiseRelation(String identifier, boolean visible,
			String name, String style, String description, int width,
			int height, String image, int borderStroke, MetaVertex origin,
			MetaVertex destination, IntSemanticPairwiseRelation semanticRelation) {
		this(identifier, visible, name, style, description, width, height,
				image, borderStroke, origin, destination, 1, 1, 1, 1, "", "",
				false, TypeOfLine.solid);
		this.semanticPairwiseRelation = semanticRelation;
		createPWModelingAttributes();
	}

	public MetaPairwiseRelation(String identifier, boolean visible,
			String name, String style, String description, int width,
			int height, String image, int borderStroke, MetaVertex origin,
			MetaVertex destination) {
		this(identifier, visible, name, style, description, width, height,
				image, borderStroke, origin, destination, 1, 1, 1, 1, "", "",
				false, TypeOfLine.solid);
		createPWModelingAttributes();
	}

	public MetaPairwiseRelation(String identifier, boolean visible,
			String name, String style, String description, int width,
			int height, String image, int borderStroke, MetaVertex origin,
			MetaVertex destination, int iniLowCardinality,
			int iniHighCardinality, int endLowCardinality,
			int endHighCardinality, String iniDescription,
			String endDescription, boolean arrowDirection, TypeOfLine typeOfLine) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke);
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
		createPWModelingAttributes();
	}

	public MetaPairwiseRelation(String identifier, boolean visible,
			String name, String style, String description, int width,
			int height, String image, int borderStroke,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> modelingAttributes,
			MetaVertex origin, MetaVertex destination, int iniLowCardinality,
			int iniHighCardinality, int endLowCardinality,
			int endHighCardinality, String iniDescription,
			String endDescription, boolean arrowDirection, TypeOfLine typeOfLine) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, disPropVisibleAttributes,
				disPropEditableAttributes, disPanelVisibleAttributes,
				disPanelSpacersAttributes, modelingAttributes);
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
		createPWModelingAttributes();
	}

	public void createPWModelingAttributes() {
		// TODO include attribute based on other object values, cardinalityType
		// from semanticTypes
		// addModelingAttribute(VAR_SEMANTICPAIRWISEREL_IDEN, new
		// SemanticAttribute(
		// VAR_SEMANTICPAIRWISEREL_IDEN, "Class", true,
		// VAR_SEMANTICPAIRWISEREL_NAME, VAR_SEMANTICPAIRWISEREL_CLASS,
		// "OperGoalOverTwoRel", ""));
/*		addModelingAttribute(VAR_METAPAIRWISERELTYPE, new SemanticAttribute(
				VAR_METAPAIRWISERELTYPE, "Enumeration", true,
				VAR_METAPAIRWISERELTYPE_NAME, VAR_METAPAIRWISERELTYPE_CLASS,
				"mandatory", ""));
*/
		addModelingAttribute(VAR_METAGENERALCONSTRAINT, new SemanticAttribute(
				VAR_METAGENERALCONSTRAINT, "String", false,
				VAR_METAGENERALCONSTRAINTNAME, ""));

		// this.addPropEditableAttribute("03#" + VAR_SEMANTICPAIRWISEREL_IDEN);
		// this.addPropVisibleAttribute("03#" + VAR_SEMANTICPAIRWISEREL_IDEN);

//		this.addPropEditableAttribute("04#" + VAR_METAPAIRWISERELTYPE);
//		this.addPropVisibleAttribute("04#" + VAR_METAPAIRWISERELTYPE);

//		this.addPropEditableAttribute("05#" + VAR_METAGENERALCONSTRAINT + "#"
//				+ VAR_METAPAIRWISERELTYPE + "#==#" + "generalConstraint");
//		this.addPropVisibleAttribute("05#" + VAR_METAGENERALCONSTRAINT + "#"
//				+ VAR_METAPAIRWISERELTYPE + "#==#" + "generalConstraint");
	}

	public Set<String> getPropVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticPairwiseRelation != null)
			modelingAttributesNames.addAll(semanticPairwiseRelation
					.getPropVisibleAttributes());

		modelingAttributesNames.addAll(super.getPropVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPropEditableAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticPairwiseRelation != null)
			modelingAttributesNames.addAll(semanticPairwiseRelation
					.getPropEditableAttributes());

		modelingAttributesNames.addAll(super.getPropEditableAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPanelVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticPairwiseRelation != null)
			modelingAttributesNames.addAll(semanticPairwiseRelation
					.getPanelVisibleAttributes());

		modelingAttributesNames.addAll(super.getPanelVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPanelSpacersAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (semanticPairwiseRelation != null)
			modelingAttributesNames.addAll(semanticPairwiseRelation
					.getPanelSpacersAttributes());

		modelingAttributesNames.addAll(super.getPanelSpacersAttributes());
		return modelingAttributesNames;
	}

	public AbstractAttribute getAbstractAttribute(String attributeName) {
		AbstractAttribute out = getSemanticAttribute(attributeName);
		if (out == null)
			return getModelingAttribute(attributeName);
		else
			return out;
	}

	public Set<String> getAllAttributesNames() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		if (semanticPairwiseRelation != null)
			modelingAttributesNames.addAll(semanticPairwiseRelation
					.getSemanticAttributesNames());
		return modelingAttributesNames;
	}

	public AbstractAttribute getSemanticAttribute(String name) {
		return semanticPairwiseRelation.getSemanticAttribute(name);
	}

	public List<IntSemanticRelationType> getSemanticRelationTypes() {
		return semanticPairwiseRelation.getSemanticRelationTypes();
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

	public SemanticAttribute getSemanticRelation() {
		return (SemanticAttribute) getModelingAttribute(VAR_SEMANTICPAIRWISEREL_IDEN);
	}
}