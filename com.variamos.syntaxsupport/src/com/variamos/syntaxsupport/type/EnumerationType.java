package com.variamos.syntaxsupport.type;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.ModelingAttribute;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;
import com.variamos.syntaxsupport.metametamodel.SimulationAttribute;

/**
 * A class to represent an enumeration dynamically loaded attribute. Inspired on
 * other ProductLine types. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-12-01
 * @see com.cfm.productline.type
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
	public AbstractAttribute makeAttribute(String name,
			boolean affectProperties, String displayName) {
		return new AbstractAttribute(name, getIdentifier(), affectProperties, displayName, 0);
	}
	

	
	public static SemanticAttribute newSemanticAttribute(String name,
			boolean affectProperties, String displayName) {
		return new SemanticAttribute (name, IDENTIFIER, affectProperties, displayName, 0);
	}	
	public static SimulationAttribute newSimulationAttribute(String name,
			boolean affectProperties, String displayName) {
		return new SimulationAttribute (name, IDENTIFIER, affectProperties, displayName, 0);
	}	
	public static ModelingAttribute newModelingAttribute(String name,
			boolean affectProperties, String displayName) {
		return new ModelingAttribute (name, IDENTIFIER, affectProperties, displayName, 0);
	}
}
