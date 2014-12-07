package com.variamos.refas.core.sematicsmetamodel;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of semantics for REFAS
 */
public class HardSemanticConcept extends AbstractSemanticVertex {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9024843014882087367L;
	
	public static final String VAR_SATISFACTIONTYPE= "satisfactionType",
			VAR_SATISFACTIONTYPECLASS= "com.variamos.refas.core.types.SatisfactionType";

	public HardSemanticConcept()
	{
		this (null, null);
		}
	
	public HardSemanticConcept(String name) {
		this(null, name);
		}

	public HardSemanticConcept(AbstractSemanticVertex parentConcept,
			String name) {
		super(parentConcept, name,  true);
		defineSemanticAttributes();
	}
	
	private void defineSemanticAttributes()
	{
		putSemanticAttribute(VAR_SATISFACTIONTYPE, new SemanticAttribute(VAR_SATISFACTIONTYPE,"Enumeration",VAR_SATISFACTIONTYPECLASS,"achieve",""));
		this.addDisPropEditableAttribute("01#"+VAR_SATISFACTIONTYPE);
	}
	
	public AbstractAttribute getSatisfactionType() {
		return getSemanticAttribute(VAR_SATISFACTIONTYPE);
	}

	public void setSatisfactionType(SemanticAttribute satisfactionType) {
		setSemanticAttribute(VAR_SATISFACTIONTYPE,satisfactionType);
	}
	
	public String toString()
	{

		return " HSC: " + super.toString();
	}
}
