package com.variamos.perspsupport.syntaxsupport;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.semanticinterface.IntSemanticPairwiseRelation;
import com.variamos.perspsupport.semanticinterface.IntSemanticRelationType;
import com.variamos.perspsupport.semanticsupport.SemanticPairwiseRelation;

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
 * @see com.variamos.perspsupport.syntaxsupport.MetaElement * @see
 *      com.variamos.semantic.semanticsupport.SemanticPairwiseRelation
 */
public class MetaPairwiseRelation extends MetaElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2665541567934067387L;
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
	// private IntSemanticPairwiseRelation semanticPairwiseRelation;

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
			int height, String image, int borderStroke,
			InstElement instSemanticElement) {
		this(identifier, visible, name, style, description, width, height,
				image, borderStroke, instSemanticElement, 1, 1, 1, 1, "", "",
				false, TypeOfLine.solid);
		// this.semanticPairwiseRelation = semanticRelation;
		createPWModelingAttributes();
	}

	public MetaPairwiseRelation(String identifier, boolean visible,
			String name, String style, String description, int width,
			int height, String image, int borderStroke) {
		this(identifier, visible, name, style, description, width, height,
				image, borderStroke, null, 1, 1, 1, 1, "", "",
				false, TypeOfLine.solid);
		createPWModelingAttributes();
	}

	public MetaPairwiseRelation(String identifier, boolean visible,
			String name, String style, String description, int width,
			int height, String image, int borderStroke,
			InstElement instSemanticElement, int iniLowCardinality,
			int iniHighCardinality, int endLowCardinality,
			int endHighCardinality, String iniDescription,
			String endDescription, boolean arrowDirection, TypeOfLine typeOfLine) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, instSemanticElement);

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
			InstElement instSemanticElement,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> modelingAttributes,
			int iniLowCardinality, int iniHighCardinality,
			int endLowCardinality, int endHighCardinality,
			String iniDescription, String endDescription,
			boolean arrowDirection, TypeOfLine typeOfLine) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, instSemanticElement,
				disPropVisibleAttributes, disPropEditableAttributes,
				disPanelVisibleAttributes, disPanelSpacersAttributes,
				modelingAttributes);
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
		/*
		 * addModelingAttribute(VAR_METAPAIRWISERELTYPE, new SemanticAttribute(
		 * VAR_METAPAIRWISERELTYPE, "Enumeration", true,
		 * VAR_METAPAIRWISERELTYPE_NAME, VAR_METAPAIRWISERELTYPE_CLASS,
		 * "mandatory", ""));
		 */
//		addModelingAttribute(VAR_METAGENERALCONSTRAINT, new SemanticAttribute(
//				VAR_METAGENERALCONSTRAINT, "String", false,
//				VAR_METAGENERALCONSTRAINTNAME, ""));

		// this.addPropEditableAttribute("03#" + VAR_SEMANTICPAIRWISEREL_IDEN);
		// this.addPropVisibleAttribute("03#" + VAR_SEMANTICPAIRWISEREL_IDEN);

		// this.addPropEditableAttribute("04#" + VAR_METAPAIRWISERELTYPE);
		// this.addPropVisibleAttribute("04#" + VAR_METAPAIRWISERELTYPE);

		// this.addPropEditableAttribute("05#" + VAR_METAGENERALCONSTRAINT + "#"
		// + VAR_METAPAIRWISERELTYPE + "#==#" + "generalConstraint");
		// this.addPropVisibleAttribute("05#" + VAR_METAGENERALCONSTRAINT + "#"
		// + VAR_METAPAIRWISERELTYPE + "#==#" + "generalConstraint");
	}

	public Set<String> getPropVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getInstSemanticElement()
					.getEditableSemanticElement().getPropVisibleAttributes());

		modelingAttributesNames.addAll(super.getPropVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPropEditableAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getInstSemanticElement()
					.getEditableSemanticElement().getPropEditableAttributes());

		modelingAttributesNames.addAll(super.getPropEditableAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPanelVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstSemanticElement() != null)
			modelingAttributesNames.addAll(getInstSemanticElement()
					.getEditableSemanticElement().getPanelVisibleAttributes());

		modelingAttributesNames.addAll(super.getPanelVisibleAttributes());
		return modelingAttributesNames;
	}

	public Set<String> getPanelSpacersAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getInstSemanticElement()!= null)
			modelingAttributesNames.addAll(getInstSemanticElement()
					.getEditableSemanticElement().getPanelSpacersAttributes());

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
		if (getInstSemanticElement() != null)
			modelingAttributesNames.addAll(getInstSemanticElement()
					.getEditableSemanticElement().getSemanticAttributesNames());
		return modelingAttributesNames;
	}

	public AbstractAttribute getSemanticAttribute(String name) {
		return getInstSemanticElement().getEditableSemanticElement()
				.getSemanticAttribute(name);
	}

	public List<IntSemanticRelationType> getSemanticRelationTypes() {
		return ((SemanticPairwiseRelation) getInstSemanticElement()
				.getEditableSemanticElement()).getSemanticRelationTypes();
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
		return "MetaGroupRelation";
	}

	public static String getClassId() {
		return "E";
	}

	public SemanticAttribute getSemanticRelation() {
		return (SemanticAttribute) getModelingAttribute(VAR_SEMANTICPAIRWISEREL_IDEN);
	}
}