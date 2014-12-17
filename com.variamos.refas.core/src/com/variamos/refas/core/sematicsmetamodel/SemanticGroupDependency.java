package com.variamos.refas.core.sematicsmetamodel;

import java.util.ArrayList;
import java.util.List;

import com.variamos.refas.core.types.GroupRelationType;
import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupDependency;

/**
 * A class to represent group dependencies at semantic level. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 * @see com.cfm.productline.
 */
public class SemanticGroupDependency extends AbstractSemanticVertex implements
		IntSemanticGroupDependency {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6309224856276191013L;
	private boolean exclusive;
	public static final String VAR_CARDINALITYTYPE = "cardinalityType",
			VAR_CARDINALITYTYPENAME = "Cardinality Type",
			VAR_LOWCARDINALITY = "lowCardinality",
			VAR_LOWCARDINALITYNAME = "Low Cardinality",
			VAR_HIGHCARDINALITY = "highCardinality",
			VAR_HIGHCARDINALITYNAME = "High Cardinality",
			VAR_CARDINALITYTYPECLASS = "com.variamos.refas.core.types.CardinalityType";

	private ConditionalExpression conditionalExpression;
	private List<AbstractSemanticVertex> conflicts;
	private List<GroupRelationType> relationTypes;
	private List<OutgoingSemanticEdge> outgoingRelations;

	public SemanticGroupDependency() {
	}

	public SemanticGroupDependency(String identifier, boolean exclusive,
			List<GroupRelationType> relationTypes) {
		this(identifier, exclusive, null,
				new ArrayList<AbstractSemanticVertex>(), relationTypes,
				new ArrayList<OutgoingSemanticEdge>());
	}

	public SemanticGroupDependency(String identifier, boolean exclusive,
			List<GroupRelationType> relationTypes,
			List<OutgoingSemanticEdge> outgoingRelations) {
		this(identifier, exclusive, null,
				new ArrayList<AbstractSemanticVertex>(), relationTypes,
				outgoingRelations);
	}

	public SemanticGroupDependency(String identifier, boolean exclusive,
			ConditionalExpression conditionalExpression,
			List<GroupRelationType> relationTypes,
			List<OutgoingSemanticEdge> outgoingRelations) {
		this(identifier, exclusive, conditionalExpression,
				new ArrayList<AbstractSemanticVertex>(), relationTypes,
				outgoingRelations);

	}

	public SemanticGroupDependency(String identifier, boolean exclusive,
			ConditionalExpression conditionalExpression,
			List<AbstractSemanticVertex> conflicts,
			List<GroupRelationType> relationTypes,
			List<OutgoingSemanticEdge> outgoingRelations) {
		super(identifier, true);
		this.exclusive = exclusive;

		putSemanticAttribute(VAR_CARDINALITYTYPE, new SemanticAttribute(
				VAR_CARDINALITYTYPE, "Enumeration", true,
				VAR_CARDINALITYTYPENAME, VAR_CARDINALITYTYPECLASS, "and", ""));
		putSemanticAttribute(VAR_LOWCARDINALITY,
				new SemanticAttribute(VAR_LOWCARDINALITY, "Integer", false,
						VAR_LOWCARDINALITY, 1, ""));
		putSemanticAttribute(VAR_HIGHCARDINALITY, new SemanticAttribute(
				VAR_HIGHCARDINALITY, "Integer", false, VAR_HIGHCARDINALITY, 1,
				""));

		addDisPropEditableAttribute("06#" + VAR_CARDINALITYTYPE);
		addDisPropEditableAttribute("09#" + VAR_LOWCARDINALITY + "#"
				+ VAR_CARDINALITYTYPE + "#==#" + "range");
		addDisPropEditableAttribute("10#" + VAR_HIGHCARDINALITY + "#"
				+ VAR_CARDINALITYTYPE + "#==#" + "range");

		addDisPropVisibleAttribute("06#" + VAR_CARDINALITYTYPE);
		addDisPropVisibleAttribute("09#" + VAR_LOWCARDINALITY + "#"
				+ VAR_CARDINALITYTYPE + "#==#" + "range");
		addDisPropVisibleAttribute("10#" + VAR_HIGHCARDINALITY + "#"
				+ VAR_CARDINALITYTYPE + "#==#" + "range");

		addDisPanelVisibleAttribute("06#" + VAR_CARDINALITYTYPE + "#"
				+ VAR_CARDINALITYTYPE + "#!=#" + "range");
		addDisPanelVisibleAttribute("09#" + VAR_LOWCARDINALITY + "#"
				+ VAR_CARDINALITYTYPE + "#==#" + "range");
		addDisPanelVisibleAttribute("10#" + VAR_HIGHCARDINALITY + "#"
				+ VAR_CARDINALITYTYPE + "#==#" + "range");

		addDisPanelSpacersAttribute("#" + VAR_CARDINALITYTYPE + "#");
		addDisPanelSpacersAttribute("[#" + VAR_LOWCARDINALITY + "#-");
		addDisPanelSpacersAttribute("#" + VAR_HIGHCARDINALITY + "#]");

		this.conditionalExpression = conditionalExpression;
		this.conflicts = conflicts;
		this.relationTypes = relationTypes;
		this.outgoingRelations = outgoingRelations;
	}

	public boolean isExclusive() {
		return exclusive;
	}

	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}

	public ConditionalExpression getConditionalExpression() {
		return conditionalExpression;
	}

	public void setConditionalExpression(
			ConditionalExpression conditionalExpression) {
		this.conditionalExpression = conditionalExpression;
	}

	public List<AbstractSemanticVertex> getConflicts() {
		return conflicts;
	}

	public void setConflicts(List<AbstractSemanticVertex> conflicts) {
		this.conflicts = conflicts;
	}

	public List<GroupRelationType> getRelationTypes() {
		return relationTypes;
	}

	public void setRelationTypes(List<GroupRelationType> relationTypes) {
		this.relationTypes = relationTypes;
	}

	public List<OutgoingSemanticEdge> getOutgoingRelations() {
		return outgoingRelations;
	}

	public void setOutgoingRelations(
			List<OutgoingSemanticEdge> outgoingRelations) {
		this.outgoingRelations = outgoingRelations;
	}

	public AbstractAttribute getVariable(String name) {
		return getSemanticAttribute(name);
	}

	public String toString() {
		return " Exc:"
				+ exclusive
				+ "throughDep: "/* + throughDep.getType() */
				+ (getVariable(VAR_LOWCARDINALITY) == null ? ""
						: ("cardin: "
								+ getVariable(VAR_LOWCARDINALITY).getName()
								+ " " + getVariable(VAR_HIGHCARDINALITY)
								.getName())) + " cardType: "
				+ getVariable(VAR_CARDINALITYTYPE).getName();
	}
}
