package com.variamos.syntaxsupport.metametamodel;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticDirectRelation;


/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class MetaExtends extends MetaDirectRelation{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6817562542934654936L;
	private boolean completeDisjoint;
	
	public MetaExtends()
	{
		
	}

	public MetaExtends(IntSemanticDirectRelation semanticRelation,
			MetaElement origin, MetaElement destination, boolean completeDisjoint) {
		super(semanticRelation, origin, destination);
		this.completeDisjoint = completeDisjoint;
	}

	public void setCompleteDisjoint(boolean completeDisjoint) {
		this.completeDisjoint = completeDisjoint;
	}

	public boolean isCompleteDisjoint() {
		return completeDisjoint;
	}
	
}
