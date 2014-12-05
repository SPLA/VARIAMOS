package com.variamos.refas.core.sematicsmetamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.variamos.refas.core.types.GroupRelationType;
import com.variamos.syntaxsupport.type.EnumerationType;
import com.variamos.syntaxsupport.type.IntegerType;
import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaAttribute;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupDependency;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
 */
public class SemanticGroupDependency extends AbstractSemanticConcept implements
		IntSemanticGroupDependency {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6309224856276191013L;
	private boolean exclusive;
	public static final String 	VAR_CARDINALITYTYPE = "cardinalityType",
								VAR_LOWCARDINALITY = "lowCardinality",
								VAR_HIGHCARDINALITY = "highCardinality";

	private ConditionalExpression conditionalExpression;
	private List<AbstractSemanticConcept> conflicts;
	private List<GroupRelationType> relationTypes;
	private List<OutgoingSemanticRelation> outgoingRelations;

	public SemanticGroupDependency(String identifier, boolean exclusive,
			List<GroupRelationType> relationTypes) {
		this(identifier, exclusive, null,
				new ArrayList<AbstractSemanticConcept>(), relationTypes,
				new ArrayList<OutgoingSemanticRelation>());
	}

	public SemanticGroupDependency(String identifier, boolean exclusive,
			List<GroupRelationType> relationTypes,
			List<OutgoingSemanticRelation> outgoingRelations) {
		this(identifier, exclusive, null,
				new ArrayList<AbstractSemanticConcept>(), relationTypes,
				outgoingRelations);
	}

	public SemanticGroupDependency(String identifier, boolean exclusive,
			ConditionalExpression conditionalExpression,
			List<GroupRelationType> relationTypes,
			List<OutgoingSemanticRelation> outgoingRelations) {
		this(identifier, exclusive, conditionalExpression,
				new ArrayList<AbstractSemanticConcept>(), relationTypes,
				outgoingRelations);

	}

	public SemanticGroupDependency(String identifier, boolean exclusive,
			ConditionalExpression conditionalExpression,
			List<AbstractSemanticConcept> conflicts,
			List<GroupRelationType> relationTypes,
			List<OutgoingSemanticRelation> outgoingRelations) {
		super(identifier, true);
		this.exclusive = exclusive;

		putSemanticAttribute(VAR_CARDINALITYTYPE, new SemanticAttribute(
				"cardinalityType", "Enumeration",
				"com.variamos.refas.core.types.CardinalityType", "mandatory","")); 
		putSemanticAttribute(VAR_LOWCARDINALITY, new SemanticAttribute(
				"lowCardinality", "Integer", 1,""));
		putSemanticAttribute(VAR_HIGHCARDINALITY, new SemanticAttribute(
				"highCardinality", "Integer", 1,""));

		addDisPropEditableAttribute("06#"+VAR_CARDINALITYTYPE);
		addDisPropEditableAttribute("09#"+VAR_LOWCARDINALITY+"#"+VAR_CARDINALITYTYPE+"#==#"+"range");
		addDisPropEditableAttribute("10#"+VAR_HIGHCARDINALITY+"#"+VAR_CARDINALITYTYPE+"#==#"+"range");
		
		addDisPanelVisibleAttribute("06#"+VAR_CARDINALITYTYPE+"#"+VAR_CARDINALITYTYPE+"#!=#"+"range");
		addDisPanelVisibleAttribute("09#"+VAR_LOWCARDINALITY+"#"+VAR_CARDINALITYTYPE+"#==#"+"range");
		addDisPanelVisibleAttribute("10#"+VAR_HIGHCARDINALITY+"#"+VAR_CARDINALITYTYPE+"#==#"+"range");
		
		addDisPanelSpacersAttribute("#"+VAR_CARDINALITYTYPE+"#");
		addDisPanelSpacersAttribute("[#"+VAR_LOWCARDINALITY+"#-");
		addDisPanelSpacersAttribute("#"+VAR_HIGHCARDINALITY+"#]");
		
		
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

	public List<AbstractSemanticConcept> getConflicts() {
		return conflicts;
	}

	public void setConflicts(List<AbstractSemanticConcept> conflicts) {
		this.conflicts = conflicts;
	}

	public List<GroupRelationType> getRelationTypes() {
		return relationTypes;
	}

	public void setRelationTypes(List<GroupRelationType> relationTypes) {
		this.relationTypes = relationTypes;
	}

	public List<OutgoingSemanticRelation> getOutgoingRelations() {
		return outgoingRelations;
	}

	public void setOutgoingRelations(
			List<OutgoingSemanticRelation> outgoingRelations) {
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
