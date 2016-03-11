package com.variamos.perspsupport.opers;

import java.util.ArrayList;
import java.util.List;

import com.variamos.perspsupport.opersint.IntOpersConcept;

/**
 * A class to represent the vertex at semantic level. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 */
public abstract class OpersAbstractVertex extends OpersAbstractElement
		implements IntOpersConcept {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3569174300072453550L;

	private boolean booleanSatisfaction;
	public static final String
	/**
	* 
	*/
	VAR_SELECTED_IDEN = "Selected";

	public OpersAbstractVertex() {
		this(null, "", false, new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>());
	}

	public OpersAbstractVertex(String identifier) {
		super(identifier);
		createModelingAttributes();
	}

	public OpersAbstractVertex(OpersAbstractVertex parentConcept,
			String identifier) {
		super(parentConcept, identifier);
		createModelingAttributes();
	}

	public OpersAbstractVertex(OpersAbstractVertex parentConcept,
			String identifier, boolean satisfactionType) {
		this(parentConcept, identifier, satisfactionType,
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>());

	}

	public OpersAbstractVertex(String name, boolean satisfactionType) {
		this(null, name, satisfactionType, new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>());
	}

	public OpersAbstractVertex(OpersAbstractVertex parentConcept,
			String identifier, boolean booleanSatisfaction,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes) {
		super(parentConcept, identifier, disPropVisibleAttributes,
				disPropEditableAttributes, disPanelVisibleAttributes,
				disPanelSpacersAttributes);
		this.booleanSatisfaction = booleanSatisfaction;
		createModelingAttributes();
	}

	private void createModelingAttributes() {
	}

	public boolean isBooleanSatisfaction() {
		return booleanSatisfaction;
	}

	public void setBooleanSatisfaction(boolean booleanSatisfaction) {
		this.booleanSatisfaction = booleanSatisfaction;
	}

	public String toString() {
		/*
		 * String directRelationsOut = ""; for (int i = 0; i<
		 * directRelations.size();i++) directRelationsOut +=
		 * directRelations.get(i).getType()+", "; String groupRelationsOut = "";
		 * for (int i = 0; i< groupRelations.size();i++) groupRelationsOut +=
		 * groupRelations.get(i).toString()+", ";
		 */
		return "name : " + getIdentifier() + " booleanSatisf: "
				+ booleanSatisfaction + " multirel: " /*
													 * + multipleGroupRelations
													 * + " dirRel: " +
													 * directRelationsOut +
													 * " dirRel: " +
													 * groupRelationsOut
													 */;
	}
}
