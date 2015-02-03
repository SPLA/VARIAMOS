package com.variamos.semantic.semanticsupport;

import java.util.List;

import com.variamos.syntax.metamodelsupport.AbstractAttribute;
import com.variamos.syntax.metamodelsupport.SemanticAttribute;
import com.variamos.syntax.semanticinterface.IntSemanticOverTwoRelation;
import com.variamos.syntax.semanticinterface.IntSemanticRelationType;

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
	private boolean conditionalExpression;
	public static final String VAR_RELATIONTYPE_IDEN = "relationType",
			VAR_RELATIONTYPE_NAME = "Relation Type",
			VAR_RELATIONTYPE_CLASS = SemanticRelationType.class
					.getCanonicalName();

//	private ConditionalExpression conditionalExpression;
	private List<IntSemanticRelationType> semanticRelationTypes;

	public SemanticOverTwoRelation() {
	}

	public SemanticOverTwoRelation(String identifier,
			List<IntSemanticRelationType> list) {
		super(identifier);
		this.semanticRelationTypes = list;
		defineSemanticAttributes();
	}

	public SemanticOverTwoRelation(AbstractSemanticVertex parent, String identifier, boolean exclusive,
			List<IntSemanticRelationType> semanticRelationTypes,
			boolean conditionalExpression) {
		super(parent, identifier);
		this.conditionalExpression= conditionalExpression;
		this.semanticRelationTypes = semanticRelationTypes;
		this.exclusive = exclusive;
		defineSemanticAttributes();
	}
	
	public SemanticOverTwoRelation(AbstractSemanticVertex parent,
			String identifier, boolean exclusive,
			List<IntSemanticRelationType> semanticRelationTypes) {
		this(parent, identifier, exclusive, semanticRelationTypes, false);
	}
	
	public SemanticOverTwoRelation(AbstractSemanticVertex parent,
			String identifier, List<IntSemanticRelationType> semanticRelationTypes) {
		this(parent, identifier, false, semanticRelationTypes, false);
	}

	public SemanticOverTwoRelation(String identifier, boolean exclusive,
			List<IntSemanticRelationType> semanticRelationTypes) {
		this(identifier, exclusive, semanticRelationTypes, false);
		defineSemanticAttributes();
	}
	
	public SemanticOverTwoRelation(String identifier, boolean exclusive,
			boolean conditionalExpression) {
		this(identifier, exclusive, null, conditionalExpression);
		defineSemanticAttributes();
	}

	public SemanticOverTwoRelation(String identifier, boolean exclusive,
			List<IntSemanticRelationType> semanticRelationTypes,
			boolean conditionalExpression) {
		super(identifier, true);
		this.exclusive = exclusive;
		this.semanticRelationTypes = semanticRelationTypes;
	//	this.conditionalExpression = conditionalExpression;
		defineSemanticAttributes();
	}

	private void defineSemanticAttributes() {
		putSemanticAttribute(VAR_RELATIONTYPE_IDEN, new SemanticAttribute(
				VAR_RELATIONTYPE_IDEN, "Class", true, VAR_RELATIONTYPE_NAME,
				VAR_RELATIONTYPE_CLASS, null, null));
		addPropEditableAttribute("06#" + VAR_RELATIONTYPE_IDEN);
		addPropVisibleAttribute("06#" + VAR_RELATIONTYPE_IDEN);
		addPanelVisibleAttribute("06#" + VAR_RELATIONTYPE_IDEN);
		addPanelSpacersAttribute("#" + VAR_RELATIONTYPE_IDEN + "#");
	}

	public boolean isExclusive() {
		return exclusive;
	}

	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}

//	public ConditionalExpression getConditionalExpression() {
//		return conditionalExpression;
//	}

//	public void setConditionalExpression(
//			ConditionalExpression conditionalExpression) {
//		this.conditionalExpression = conditionalExpression;
//	}

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
