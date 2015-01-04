package com.variamos.refas.core.sematicsmetamodel;

import java.util.ArrayList;
import java.util.List;

import com.variamos.refas.core.types.LevelType;
import com.variamos.refas.core.types.SatisficingType;
import com.variamos.syntaxsupport.metamodelsupport.SemanticAttribute;

/**
 * A class to represent the edges at semantic level. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 */
public abstract class AbstractSemanticEdge extends AbstractSemanticElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8651835673666769706L;
		/**
	 * 
	 */
	private boolean toSoftSemanticConcept;
	/**
	 * 
	 */
	private List<AbstractSemanticVertex> conflicts;
	/**
	 * 
	 */
	private List<AbstractSemanticVertex> alwaysAllows;

	public AbstractSemanticEdge() {
		this("", false, new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>());
	}

	public AbstractSemanticEdge(String identifier,
			boolean toSoftSemanticConcept,
			List<AbstractSemanticVertex> conflicts) {
		this(identifier, toSoftSemanticConcept, conflicts,
				new ArrayList<AbstractSemanticVertex>());
	}

	public AbstractSemanticEdge(String identifier, boolean toSoftSemanticConcept) {
		this(identifier, toSoftSemanticConcept,
				new ArrayList<AbstractSemanticVertex>(),
				new ArrayList<AbstractSemanticVertex>());
	}

	public AbstractSemanticEdge(String identifier,
			boolean toSoftSemanticConcept,
			List<AbstractSemanticVertex> conflicts,
			List<AbstractSemanticVertex> alwaysAllows) {
		super(identifier);
		this.toSoftSemanticConcept = toSoftSemanticConcept;
		this.conflicts = conflicts;
		this.alwaysAllows = alwaysAllows;
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


		String conflictsOut = "";
		for (int i = 0; i < conflicts.size(); i++)
			conflictsOut += conflicts.get(i).getIdentifier() + ", ";
		String alwaysAllowsOut = "";
		for (int i = 0; i < alwaysAllows.size(); i++)
			alwaysAllowsOut += alwaysAllows.get(i).getIdentifier() + ", ";
		return  " conflicts: (" + conflictsOut + ") alwaysAllows: ("
				+ alwaysAllowsOut + ")";
	}
}
