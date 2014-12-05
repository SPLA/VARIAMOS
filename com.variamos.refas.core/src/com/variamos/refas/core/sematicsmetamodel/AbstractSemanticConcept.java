package com.variamos.refas.core.sematicsmetamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaExtends;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;
import com.variamos.syntaxsupport.metametamodel.SimulationAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticConcept;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
 */
public class AbstractSemanticConcept implements IntSemanticConcept,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3569174300072453550L;
	private AbstractSemanticConcept parent;
	private String identifier;
	private boolean booleanSatisfaction;
	private List<String> disPropVisibleAttributes; //position(01-99)#variable#conditionalvariable#operator#value 
	private List<String> disPropEditableAttributes; //position(01-99)#variable#conditionalvariable#operator#value
	private List<String> disPanelVisibleAttributes; //position(01-99)#variable#conditionalvariable#operator#value
	private List<String> disPanelSpacersAttributes; //preSpacer#variable#1Spacer#2Spacer#3Spacer#...
	private List<String> simPropVisibleAttributes;
	private List<String> simPropEditableAttributes;
	private List<String> simPanelVisibleAttributes;
	private List<String> simPanelSpacersAttributes;
	private List<IncomingSemanticRelation> groupRelations = new ArrayList<IncomingSemanticRelation>();
	private List<DirectSemanticRelation> directRelations = new ArrayList<DirectSemanticRelation>();
	private Map<String, AbstractAttribute> semanticAttributes = new HashMap<String, AbstractAttribute>();
	private Map<String, SimulationAttribute> simulationAttributes = new HashMap<String, SimulationAttribute>();

	public AbstractSemanticConcept() {
		this(null, "", false, new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>());
	}

	public AbstractSemanticConcept(AbstractSemanticConcept parentConcept,
			String name,  boolean satisfactionType) {
		this(parentConcept, name, satisfactionType,
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>());
		groupRelations.addAll(parentConcept.getgroupRelations());
		directRelations.addAll(parentConcept.getDirectRelations());
	}

	public AbstractSemanticConcept(String name,
			boolean satisfactionType) {
		this(null, name, satisfactionType, new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>());
	}

	public AbstractSemanticConcept(AbstractSemanticConcept parentConcept,
			String identifier, boolean satisfactionType,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes) {
		this.parent = parentConcept;
		this.identifier = identifier;		
		this.booleanSatisfaction = satisfactionType;
		if (parent != null) {
			groupRelations.addAll(parentConcept.getgroupRelations());
			directRelations.addAll(parentConcept.getDirectRelations());
		} else {
			groupRelations = new ArrayList<IncomingSemanticRelation>();
			directRelations = new ArrayList<DirectSemanticRelation>();
		}
		this.disPropVisibleAttributes = disPropVisibleAttributes;
		this.disPropEditableAttributes = disPropEditableAttributes;
		this.disPanelVisibleAttributes = disPanelVisibleAttributes;
		this.disPanelSpacersAttributes = disPanelSpacersAttributes;
	}

	public boolean isBooleanSatisfaction() {
		return booleanSatisfaction;
	}

	public void setBooleanSatisfaction(boolean booleanSatisfaction) {
		this.booleanSatisfaction = booleanSatisfaction;
	}

	public List<IncomingSemanticRelation> getGroupRelations() {
		return groupRelations;
	}

	public void setGroupRelations(List<IncomingSemanticRelation> groupRelations) {
		this.groupRelations = groupRelations;
	}

	public Set<String> getDeclaredSemanticAttributes() {
		Set<String> abstractAttributesNames = new HashSet<String>();
		abstractAttributesNames.addAll(semanticAttributes.keySet());
		return abstractAttributesNames;
	}

	public Set<String> getSemanticAttributes() {
		Set<String> properties = new HashSet<String>();
		properties.addAll(getDeclaredSemanticAttributes());
		if (parent != null)
			properties.addAll(parent.getSemanticAttributes());
		return properties;
	}

	public AbstractAttribute getSemanticAttribute(String name) {
		AbstractAttribute semAtt = semanticAttributes.get(name);
		if (semAtt == null && parent != null)
			return (parent.getSemanticAttribute(name));
		return semAtt;
	}

	public Set<String> getDeclaredSimulationAttributes() {
		Set<String> abstractAttributesNames = new HashSet<String>();
		abstractAttributesNames.addAll(simulationAttributes.keySet());
		return abstractAttributesNames;
	}

	public Set<String> getSimulationAttributes() {
		Set<String> properties = new HashSet<String>();
		properties.addAll(getDeclaredSimulationAttributes());
		if (parent != null)
			properties.addAll(parent.getSimulationAttributes());
		return properties;
	}

	public void setSemanticAttributes(
			Map<String, AbstractAttribute> semanticProperties) {
		this.semanticAttributes = semanticProperties;
	}

	public void setSemanticAttribute(String name,
			AbstractAttribute semanticAttribute) {
		AbstractAttribute semAtt = semanticAttributes.get(name);
		if (semAtt != null)
			semanticAttributes.put(name, semanticAttribute);
		else if (parent != null)
			parent.setSemanticAttribute(name, semanticAttribute);
	}

	public void putSemanticAttribute(String name,
			AbstractAttribute abstractAttribute) {
		this.semanticAttributes.put(name, abstractAttribute);
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setDirectRelations(List<DirectSemanticRelation> directRelations) {
		this.directRelations = directRelations;
	}

	public String getIdentifier() {
		return identifier;
	}

	public List<IncomingSemanticRelation> getgroupRelations() {
		return groupRelations;
	}

	public List<DirectSemanticRelation> getDirectRelations() {
		return directRelations;
	}

	public void addGroupRelation(IncomingSemanticRelation groupRelation) {
		groupRelations.add(groupRelation);
	}

	public void addDirectRelation(DirectSemanticRelation directRelation) {
		directRelations.add(directRelation);
	}

	public String toString() {
		/*
		 * String directRelationsOut = ""; for (int i = 0; i<
		 * directRelations.size();i++) directRelationsOut +=
		 * directRelations.get(i).getType()+", "; String groupRelationsOut = "";
		 * for (int i = 0; i< groupRelations.size();i++) groupRelationsOut +=
		 * groupRelations.get(i).toString()+", ";
		 */
		return "name : " + identifier + " booleanSatisf: "
				+ booleanSatisfaction + " multirel: " /*
													 * + multipleGroupRelations
													 * + " dirRel: " +
													 * directRelationsOut +
													 * " dirRel: " +
													 * groupRelationsOut
													 */;
	}

	public void setSimPropVisibleAttributes(
			List<String> simPropVisibleAttributes) {
		this.simPropVisibleAttributes = simPropVisibleAttributes;
	}

	public void setSimPropEditableAttributes(
			List<String> simPropEditableAttributes) {
		this.simPropEditableAttributes = simPropEditableAttributes;
	}

	public void setSimPanelVisibleAttributes(
			List<String> simPanelVisibleAttributes) {
		this.simPanelVisibleAttributes = simPanelVisibleAttributes;
	}

	public void setSimPanelSpacersAttributes(
			List<String> simPanelSpacersAttributes) {
		this.simPanelSpacersAttributes = simPanelSpacersAttributes;
	}

	public void setDisPropVisibleAttributes(
			List<String> disPropVisibleAttributes) {
		this.disPropVisibleAttributes = disPropVisibleAttributes;
	}

	public void setDisPropEditableAttributes(
			List<String> disPropEditableAttributes) {
		this.disPropEditableAttributes = disPropEditableAttributes;
	}

	public void setDisPanelVisibleAttributes(
			List<String> disPanelVisibleAttributes) {
		this.disPanelVisibleAttributes = disPanelVisibleAttributes;
	}

	public void setDisPanelSpacersAttributes(
			List<String> disPanelSpacersAttributes) {
		this.disPanelSpacersAttributes = disPanelSpacersAttributes;
	}

	public List<String> getDeclaredDisPropVisibleAttributes() {
		List<String> metaAttributesNames = new ArrayList<String>();
		metaAttributesNames.addAll(disPropVisibleAttributes);
		return metaAttributesNames;
	}

	public List<String> getDisPropVisibleAttributes() {
		List<String> metaAttributesNames = new ArrayList<String>();
		if (parent != null)
			metaAttributesNames.addAll(parent.getDisPropVisibleAttributes());
		metaAttributesNames.addAll(getDeclaredDisPropVisibleAttributes());
		return metaAttributesNames;
	}

	public void addDisPropVisibleAttribute(String visibleAttribute) {
		disPropVisibleAttributes.add(visibleAttribute);
	}

	public List<String> getDeclaredDisPropEditableAttributes() {
		List<String> metaAttributesNames = new ArrayList<String>();
		metaAttributesNames.addAll(disPropEditableAttributes);
		return metaAttributesNames;
	}

	public List<String> getDisPropEditableAttributes() {
		List<String> metaAttributesNames = new ArrayList<String>();
		if (parent != null)
			metaAttributesNames.addAll(parent.getDisPropEditableAttributes());
		metaAttributesNames.addAll(getDeclaredDisPropEditableAttributes());
		return metaAttributesNames;
	}

	public void addDisPropEditableAttribute(String editableAttribute) {
		disPropEditableAttributes.add(editableAttribute);
	}

	public List<String> getDeclaredDisPanelVisibleAttributes() {
		List<String> metaAttributesNames = new ArrayList<String>();
		metaAttributesNames.addAll(disPanelVisibleAttributes);
		return metaAttributesNames;
	}

	public List<String> getDisPanelVisibleAttributes() {
		List<String> metaAttributesNames = new ArrayList<String>();
		if (parent != null)
			metaAttributesNames.addAll(parent.getDisPanelVisibleAttributes());
		metaAttributesNames.addAll(getDeclaredDisPanelVisibleAttributes());
		return metaAttributesNames;
	}

	public void addDisPanelVisibleAttribute(String visibleAttribute) {
		disPanelVisibleAttributes.add(visibleAttribute);
	}

	public List<String> getDeclaredDisPanelSpacersAttributes() {
		List<String> metaAttributesNames = new ArrayList<String>();
		metaAttributesNames.addAll(disPanelSpacersAttributes);
		return metaAttributesNames;
	}

	public List<String> getDisPanelSpacersAttributes() {
		List<String> metaAttributesNames = new ArrayList<String>();
		if (parent != null)
			metaAttributesNames.addAll(parent.getDisPanelSpacersAttributes());
		metaAttributesNames.addAll(getDeclaredDisPanelSpacersAttributes());
		return metaAttributesNames;
	}

	public void addDisPanelSpacersAttribute(String spacerAttribute) {
		disPanelSpacersAttributes.add(spacerAttribute);
	}
}
