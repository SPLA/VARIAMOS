package com.variamos.refas.core.sematicsmetamodel;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
 */
public class HardSemanticConcept extends AbstractSemanticConcept {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9024843014882087367L;

	public HardSemanticConcept()
	{
		putSemanticAttribute("satisfactionType", new SemanticAttribute("satisfactionType","Enumeration","com.variamos.refas.core.types.SatisfactionType","achieve",""));
		this.addDisPropEditableAttribute("01#"+"satisfactionType");
	}
	
	public HardSemanticConcept(String name) {
		super(name, true);
		putSemanticAttribute("satisfactionType", new SemanticAttribute("satisfactionType","Enumeration","com.variamos.refas.core.types.SatisfactionType","achieve",""));
		this.addDisPropEditableAttribute("01#"+"satisfactionType");
	}

	public HardSemanticConcept(AbstractSemanticConcept parentConcept,
			String name) {
		super(parentConcept, name,  true);
		putSemanticAttribute("satisfactionType", new SemanticAttribute("satisfactionType","Enumeration","com.variamos.refas.core.types.SatisfactionType","achieve",""));
		this.addDisPropEditableAttribute("01#"+"satisfactionType");
		
	}
	
	public AbstractAttribute getSatisfactionType() {
		return getSemanticAttribute("satisfactionType");
	}

	public void setSatisfactionType(SemanticAttribute satisfactionType) {
		setSemanticAttribute("satisfactionType",satisfactionType);
	}
	
	public String toString()
	{

		return " HSC: " + super.toString();
	}
}
