package com.variamos.refas.core.sematicassociation;

import com.variamos.refas.core.sematicsmetamodel.SemanticService;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticService;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class InstSemanticService implements IntSemanticService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7189211122325478484L;
	private String id;
	public String getId() {
		return id;
	}
	public SemanticService getMetaService() {
		return metaService;
	}
	private SemanticService metaService;

	
}
