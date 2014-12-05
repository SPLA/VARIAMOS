package com.variamos.syntaxsupport.type;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaAttribute;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;
import com.variamos.syntaxsupport.metametamodel.SimulationAttribute;


/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */

@SuppressWarnings("serial")
public class EnumerationType extends Type{
	public static final String IDENTIFIER = "Enumeration";	
	protected EnumerationType(){
		super(IDENTIFIER);
	}
	
//	@Override
//	public boolean contains(Object obj) {
//		return obj instanceof Integer;
//	}

	@Override
	public AbstractAttribute makeAttribute(String name) {
		return new AbstractAttribute(name, getIdentifier(),0);
	}
	

	
	public static SemanticAttribute newSemanticAttribute(String name) {
		return new SemanticAttribute (name, IDENTIFIER, 0);
	}	
	public static SimulationAttribute newSimulationAttribute(String name) {
		return new SimulationAttribute (name, IDENTIFIER, 0);
	}	
	public static MetaAttribute newMetaAttribute(String name) {
		return new MetaAttribute (name, IDENTIFIER, 0);
	}
}
