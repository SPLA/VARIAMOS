package com.variamos.refas.core.sematicsmetamodel;

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
		
	}
	
	public HardSemanticConcept(String name, boolean topConcept,
			boolean multipleGroupRelations) {
		super(name, topConcept, "boolean", multipleGroupRelations);
	}

	public HardSemanticConcept(AbstractSemanticConcept parentConcept,
			String name, boolean topConcept, 
			boolean multipleGroupRelations) {
		super(parentConcept, name, topConcept, "boolean", multipleGroupRelations);
	}
	
	public String toString()
	{

		return " HSC: " + super.toString();
	}
}
