package com.variamos.refas.core.sematicassociation;

import java.util.ArrayList;
import java.util.List;

import com.variamos.refas.core.sematicsmetamodel.AbstractSemanticConcept;
import com.variamos.refas.core.sematicsmetamodel.SemanticAttribute;
import com.variamos.refas.core.sematicsmetamodel.SemanticService;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticConcept;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class InstSemanticConcept implements IntSemanticConcept{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1290717362880437391L;
	private String identifier;
	private AbstractSemanticConcept metaConcept;
	private List<SemanticAttribute> metaAttributes;

	public AbstractSemanticConcept getMetaConcept() {
		return metaConcept;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setMetaConcept(AbstractSemanticConcept metaConcept) {
		this.metaConcept = metaConcept;
	}

	public void setMetaAttributes(List<SemanticAttribute> metaAttributes) {
		this.metaAttributes = metaAttributes;
	}

	public void setMetaServices(List<SemanticService> metaServices) {
		this.metaServices = metaServices;
	}

	public List<SemanticAttribute> getMetaAttributes() {
		return metaAttributes;
	}

	public List<SemanticService> getMetaServices() {
		return metaServices;
	}

	private List<SemanticService> metaServices;
	
	public InstSemanticConcept(String identifier, AbstractSemanticConcept metaConcept) {
		this( identifier, metaConcept, new ArrayList<SemanticAttribute>(), new ArrayList<SemanticService>()) ;

	}
	
	public InstSemanticConcept()
	{
		
	}
	
	public InstSemanticConcept(String identifier, AbstractSemanticConcept metaConcept,
			List<SemanticAttribute> metaAttributes,
			List<SemanticService> metaServices) {
		super();
		this.identifier = identifier;
		this.metaConcept = metaConcept;
		this.metaAttributes = metaAttributes;
		this.metaServices = metaServices;
	}

	public String getIdentifier() {
		return identifier;
	}
	
}
