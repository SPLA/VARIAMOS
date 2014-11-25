package com.variamos.refas.core.sematicsmetamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
 */
public abstract class AbstractSemanticConcept implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3569174300072453550L;
	private String name;
	private boolean topConcept;
	private String satisfactionType;
	private boolean multipleGroupRelations;
	private List<GroupRelation> groupRelations = new ArrayList<GroupRelation>();
	private List<DirectRelation> directRelations = new ArrayList<DirectRelation>();

	public AbstractSemanticConcept()
	{
		
	}
	
	public AbstractSemanticConcept(AbstractSemanticConcept parentConcept,
			String name, boolean topConcept, String satisfactionType,
			boolean multipleGroupRelations) {
		this(name, topConcept, satisfactionType, multipleGroupRelations);
		groupRelations.addAll(parentConcept.getgroupRelations());
		directRelations.addAll(parentConcept.getDirectRelations());
	}

	public AbstractSemanticConcept(String name, boolean topConcept,
			String satisfactionType, boolean multipleGroupRelations) {
		this.name = name;
		this.topConcept = topConcept;
		this.satisfactionType = satisfactionType;
		this.multipleGroupRelations = multipleGroupRelations;
		groupRelations = new ArrayList<GroupRelation>();
		directRelations = new ArrayList<DirectRelation>();
	}

	public String getName() {
		return name;
	}

	public boolean isTopConcept() {
		return topConcept;
	}

	public List<GroupRelation> getgroupRelations() {
		return groupRelations;
	}

	public List<DirectRelation> getDirectRelations() {
		return directRelations;
	}

	public void addGroupRelation(GroupRelation groupRelation) {
		groupRelations.add(groupRelation);
	}

	public void addDirectRelation(DirectRelation directRelation) {
		directRelations.add(directRelation);
	}
	public String toString()
	{
		String directRelationsOut = "";
		for (int i = 0; i< directRelations.size();i++)
			directRelationsOut += directRelations.get(i).getType()+", ";
		String groupRelationsOut = "";
		for (int i = 0; i< groupRelations.size();i++)
			groupRelationsOut += groupRelations.get(i).toString()+", ";
		return "name : " +name + " top: " + topConcept + " satisf: " + satisfactionType +
				" multirel: " + multipleGroupRelations + " dirRel: " 
					+ directRelationsOut + " dirRel: " + groupRelationsOut;
	}
}
