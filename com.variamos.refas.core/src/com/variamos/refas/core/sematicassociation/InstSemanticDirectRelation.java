package com.variamos.refas.core.sematicassociation;

import com.variamos.refas.core.sematicsmetamodel.DirectRelation;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticDirectRelation;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class InstSemanticDirectRelation implements IntSemanticDirectRelation{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1019344702633607179L;
	private String id;
	public String getId() {
		return id;
	}
	public DirectRelation getDirectRelation() {
		return directRelation;
	}
	private DirectRelation directRelation;
	
}
