package com.variamos.refas.core.sematicsmetamodel;

import java.util.ArrayList;
import java.util.List;

import com.variamos.refas.core.types.GroupRelationType;
import com.variamos.syntaxsupport.metamodelsupport.AbstractAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SemanticAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticOverTwoRelation;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticRelationType;

/**
 * A class to represent relations of more than two concepts at semantic level.
 * Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 * @see com.cfm.productline.
 */
public class SemanticOverTwoRelation extends AbstractSemanticVertex implements
		IntSemanticOverTwoRelation {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6309224856276191013L;
	private boolean exclusive;
	public static final String VAR_RELATIONTYPE_IDEN = "relationType",
			VAR_RELATIONTYPE_NAME = "Relation Type",
			VAR_RELATIONTYPE_CLASS = SemanticRelationType.class
					.getCanonicalName();

	private ConditionalExpression conditionalExpression;
	private List<AbstractSemanticVertex> conflicts;
	private List<GroupRelationType> relationTypes;
	private List<OutgoingSemanticEdge> outgoingRelations;

	private List<IntSemanticRelationType> semanticRelationTypes;

	public SemanticOverTwoRelation() {
	}

	public SemanticOverTwoRelation(String identifier,
			List<IntSemanticRelationType> list) {
		super(identifier);
		this.semanticRelationTypes = list;
		defineSemanticAttributes();
	}

	public SemanticOverTwoRelation(AbstractSemanticElement parent, String identifier,
			List<IntSemanticRelationType> list) {
		super(parent, identifier);
		this.semanticRelationTypes = list;
		defineSemanticAttributes();
	}
	
	public SemanticOverTwoRelation(String identifier, boolean exclusive,
			List<GroupRelationType> relationTypes) {
		this(identifier, exclusive, null,
				new ArrayList<AbstractSemanticVertex>(), relationTypes,
				new ArrayList<OutgoingSemanticEdge>());
		defineSemanticAttributes();
	}

	public SemanticOverTwoRelation(String identifier, boolean exclusive,
			List<GroupRelationType> relationTypes,
			List<OutgoingSemanticEdge> outgoingRelations) {
		this(identifier, exclusive, null,
				new ArrayList<AbstractSemanticVertex>(), relationTypes,
				outgoingRelations);
		defineSemanticAttributes();
	}

	public SemanticOverTwoRelation(String identifier, boolean exclusive,
			ConditionalExpression conditionalExpression,
			List<GroupRelationType> relationTypes,
			List<OutgoingSemanticEdge> outgoingRelations) {
		this(identifier, exclusive, conditionalExpression,
				new ArrayList<AbstractSemanticVertex>(), relationTypes,
				outgoingRelations);
		defineSemanticAttributes();
	}

	public SemanticOverTwoRelation(String identifier, boolean exclusive,
			ConditionalExpression conditionalExpression,
			List<AbstractSemanticVertex> conflicts,
			List<GroupRelationType> relationTypes,
			List<OutgoingSemanticEdge> outgoingRelations) {
		super(identifier, true);
		this.exclusive = exclusive;

		this.conditionalExpression = conditionalExpression;
		this.conflicts = conflicts;
		this.relationTypes = relationTypes;
		this.outgoingRelations = outgoingRelations;
		defineSemanticAttributes();
	}

	private void defineSemanticAttributes() {
		putSemanticAttribute(VAR_RELATIONTYPE_IDEN, new SemanticAttribute(
				VAR_RELATIONTYPE_IDEN, "Class", true, VAR_RELATIONTYPE_NAME,
				VAR_RELATIONTYPE_CLASS, "", ""));
		addPropEditableAttribute("06#" + VAR_RELATIONTYPE_IDEN);
		addPropVisibleAttribute("06#" + VAR_RELATIONTYPE_IDEN);
		addPanelSpacersAttribute("#" + VAR_RELATIONTYPE_IDEN + "#");
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
		return " Exc:" + exclusive + "throughDep: "/* + throughDep.getType() */;
	}

	@Override
	public List<IntSemanticRelationType> getSemanticRelationTypes() {
		return semanticRelationTypes;
	}
}
