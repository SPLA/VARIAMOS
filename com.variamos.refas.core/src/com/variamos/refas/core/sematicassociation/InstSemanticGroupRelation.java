package com.variamos.refas.core.sematicassociation;

import java.util.List;

import com.variamos.refas.core.sematicsmetamodel.ConditionalExpression;
import com.variamos.refas.core.sematicsmetamodel.SemanticAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupRelation;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class InstSemanticGroupRelation implements IntSemanticGroupRelation{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5869455522842659136L;
	private String id;
	public String getId() {
		return id;
	}
	public ConditionalExpression getCondExpress() {
		return condExpress;
	}
	public List<SemanticAttribute> getMetaAttributes() {
		return metaAttributes;
	}
	private ConditionalExpression condExpress;
	private List<SemanticAttribute> metaAttributes;

	
}
