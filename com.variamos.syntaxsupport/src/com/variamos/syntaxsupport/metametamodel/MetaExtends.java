package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;

import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticRelation;


/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class MetaExtends implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6817562542934654936L;
	private boolean completeDisjoint;
	private MetaConcept parent;
	
	public MetaExtends()
	{
		
	}

	public MetaExtends(	MetaConcept parent, boolean completeDisjoint) {
		this.completeDisjoint = completeDisjoint;
		this.parent = parent;
	}

	public void setCompleteDisjoint(boolean completeDisjoint) {
		this.completeDisjoint = completeDisjoint;
	}

	public boolean isCompleteDisjoint() {
		return completeDisjoint;
	}

	public MetaConcept getDestination() {
		return parent;
	}
	
}
