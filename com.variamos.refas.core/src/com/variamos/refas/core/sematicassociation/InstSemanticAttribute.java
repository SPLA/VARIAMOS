package com.variamos.refas.core.sematicassociation;

import java.util.List;

import com.variamos.refas.core.sematicsmetamodel.AbstractSemanticConcept;
import com.variamos.refas.core.sematicsmetamodel.SemanticAttribute;
import com.variamos.refas.core.sematicsmetamodel.SemanticService;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticAttribute;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class InstSemanticAttribute implements IntSemanticAttribute{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3102676598542496635L;
	private String id;
	private AbstractSemanticConcept metaConcept;
	public String getId() {
		return id;
	}
	public AbstractSemanticConcept getMetaConcept() {
		return metaConcept;
	}
	public List<SemanticAttribute> getMetaAttributes() {
		return metaAttributes;
	}
	public List<SemanticService> getMetaServices() {
		return metaServices;
	}
	private List<SemanticAttribute> metaAttributes;
	private List<SemanticService> metaServices;
	
}
