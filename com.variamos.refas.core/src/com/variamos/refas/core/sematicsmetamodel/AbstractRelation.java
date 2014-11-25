package com.variamos.refas.core.sematicsmetamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public abstract class AbstractRelation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8651835673666769706L;
	private AbstractSemanticConcept toConcept;
	private List<AbstractSemanticConcept> conflicts;

	public AbstractRelation()
	{
		
	}
	
	public AbstractRelation (AbstractSemanticConcept toConcept)
	{
		this ( toConcept,  new ArrayList<AbstractSemanticConcept>());
	}
	
	public AbstractRelation (AbstractSemanticConcept toConcept, List<AbstractSemanticConcept> conflicts)
	{
		this.toConcept = toConcept;
		this.conflicts = conflicts;
	}
	

	public void addConflicts(AbstractSemanticConcept conflict)
	{
		conflicts.add(conflict);
	}
	
	public String toString()
	{
		String conflictsOut = "";
		for (int i = 0; i< conflicts.size();i++)
			conflictsOut += conflicts.get(i).getName();
		return "to: " + toConcept.getName() + " conflicts: " + conflictsOut;
	}
}
