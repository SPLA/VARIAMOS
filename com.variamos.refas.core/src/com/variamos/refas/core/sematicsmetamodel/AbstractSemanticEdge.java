package com.variamos.refas.core.sematicsmetamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.variamos.syntaxsupport.metametamodel.ModelingAttribute;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
 */
public abstract class AbstractSemanticEdge extends AbstractSemanticElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8651835673666769706L;
	public static final String VAR_LEVEL = "Level",
								VAR_LEVELCLASS = "com.variamos.refas.core.types.LevelType";
	private boolean toSoftSemanticConcept;
	private SemanticAttribute satisficingType;
	private SemanticAttribute level;
	private List<AbstractSemanticVertex> conflicts;
	private List<AbstractSemanticVertex> alwaysAllows;
	
	public AbstractSemanticEdge() {
		this("",false, new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>());
	}	

	public AbstractSemanticEdge(String identifier, boolean toSoftSemanticConcept, List<AbstractSemanticVertex> conflicts) {
		this(identifier, toSoftSemanticConcept, conflicts, new ArrayList<AbstractSemanticVertex>());
	}
	
	public AbstractSemanticEdge(String identifier, boolean toSoftSemanticConcept) {
		this(identifier, toSoftSemanticConcept, new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>());
			}

	public AbstractSemanticEdge(String identifier, boolean toSoftSemanticConcept ,List<AbstractSemanticVertex> conflicts,
			List<AbstractSemanticVertex> alwaysAllows) {
		super(identifier);
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

	public List<AbstractSemanticVertex> getConflicts() {
		return conflicts;
	}

	public void setConflicts(List<AbstractSemanticVertex> conflicts) {
		this.conflicts = conflicts;
	}

	public List<AbstractSemanticVertex> getAlwaysAllows() {
		return alwaysAllows;
	}

	public void setAlwaysAllows(List<AbstractSemanticVertex> alwaysAllows) {
		this.alwaysAllows = alwaysAllows;
	}

	public void addConflicts(AbstractSemanticVertex conflict) {
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
