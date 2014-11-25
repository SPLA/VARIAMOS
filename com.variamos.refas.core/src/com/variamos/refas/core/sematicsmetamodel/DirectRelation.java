package com.variamos.refas.core.sematicsmetamodel;

import java.util.List;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class DirectRelation extends AbstractRelation {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7976788205587295216L;
	private DirectRelationType type;

	public DirectRelation()
	{
		
	}
	
	public DirectRelation (AbstractSemanticConcept toConcept, DirectRelationType type)
	{
		super (toConcept);
		this.type = type;
	}
	
	public DirectRelation ( AbstractSemanticConcept toConcept, List<AbstractSemanticConcept> conflicts,  DirectRelationType type)
	{
		super ( toConcept, conflicts);
		this.type = type;
	}

	public String getType() {
		return type.name();
	}

}
