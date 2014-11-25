package com.variamos.refas.core.sematicsmetamodel;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class ImplementationDependency extends Dependency {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6399164076807457621L;
	private ConditionalExpression condition;
	
	public ConditionalExpression getCondition() {
		return condition;
	}

	public ImplementationDependency (ConditionalExpression condition)
	{
		super (GroupRelationType.implementation);
		this.condition = condition;
	}

}
