package com.variamos.refas.core.sematicsmetamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.variamos.syntaxsupport.metametamodel.MetaAttribute;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
 */
public abstract class AbstractSemanticRelation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8651835673666769706L;
	private boolean toSoftSemanticConcept;
	private SemanticAttribute satisficingType;
	private SemanticAttribute level;
	private List<AbstractSemanticConcept> conflicts;
	private List<AbstractSemanticConcept> alwaysAllows;
	
	public AbstractSemanticRelation() {
		this(false, new ArrayList<AbstractSemanticConcept>(),
				new ArrayList<AbstractSemanticConcept>());
	}	

	public AbstractSemanticRelation(boolean toSoftSemanticConcept, List<AbstractSemanticConcept> conflicts) {
		this(toSoftSemanticConcept, conflicts, new ArrayList<AbstractSemanticConcept>());
	}
	
	public AbstractSemanticRelation( boolean toSoftSemanticConcept) {
		this(toSoftSemanticConcept, new ArrayList<AbstractSemanticConcept>(),
				new ArrayList<AbstractSemanticConcept>());
			}

	public AbstractSemanticRelation(boolean toSoftSemanticConcept ,List<AbstractSemanticConcept> conflicts,
			List<AbstractSemanticConcept> alwaysAllows) {
		if 	(toSoftSemanticConcept)
		{
			this.satisficingType = new SemanticAttribute("satisficingType","Enumeration", "com.variamos.refas.core.sematicsmetamodel.SatisficingType","","Type of satisficing");
			this.level = new SemanticAttribute("level","Enumeration", "com.variamos.refas.core.sematicsmetamodel.LevelType","","Level of satisficing");
			
		}
		this.toSoftSemanticConcept=toSoftSemanticConcept;
		this.conflicts = conflicts;
		this.alwaysAllows = alwaysAllows;
	}
	


	public String getSatisficingType() {
		return satisficingType.toString();
	}

	public String getLevel() {
		return level.getType();
	}

	public List<AbstractSemanticConcept> getConflicts() {
		return conflicts;
	}

	public void setConflicts(List<AbstractSemanticConcept> conflicts) {
		this.conflicts = conflicts;
	}

	public List<AbstractSemanticConcept> getAlwaysAllows() {
		return alwaysAllows;
	}

	public void setAlwaysAllows(List<AbstractSemanticConcept> alwaysAllows) {
		this.alwaysAllows = alwaysAllows;
	}

	public void addConflicts(AbstractSemanticConcept conflict) {
		conflicts.add(conflict);
	}

	public String toString() {

		
		String levelOut = "";
		if (level != null)
			levelOut = getLevel();
		String satisficingOut = "";
		if (satisficingType != null)
			satisficingOut = getSatisficingType();
			String conflictsOut = "";	
		for (int i = 0; i < conflicts.size(); i++)
			conflictsOut += conflicts.get(i).getIdentifier() + ", ";
		String alwaysAllowsOut = "";
		for (int i = 0; i < alwaysAllows.size(); i++)
			alwaysAllowsOut += alwaysAllows.get(i).getIdentifier() + ", ";
		return " Level: (" + levelOut +
				" satisficing: (" + satisficingOut +
				" conflicts: (" + conflictsOut + ") alwaysAllows: ("
				+ alwaysAllowsOut + ")";
	}
}
