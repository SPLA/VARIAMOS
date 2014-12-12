package com.variamos.syntaxsupport.type;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.ModelingAttribute;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;
import com.variamos.syntaxsupport.metametamodel.SimulationAttribute;

/**
 * A class to represent an string dynamically loaded attribute. Based on
 * StringType of ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-11-24
 * @see com.cfm.productline.type.StringType
 */
@SuppressWarnings("serial")
public class StringType extends Type{
	public static final String IDENTIFIER = "String";
	protected StringType(){
		super(IDENTIFIER);
	}

	@Override
	public AbstractAttribute makeAttribute(String name,
			boolean affectProperties, String displayName) {
		return new AbstractAttribute(name, getIdentifier(), affectProperties, displayName, "");
	}

	public static AbstractAttribute newModelingAttribute(String name,
			boolean affectProperties, String displayName) {
		return new ModelingAttribute(name, IDENTIFIER, affectProperties, name, "");
	}
	public static AbstractAttribute newSemanticAttribute(String name,
			boolean affectProperties, String displayName) {
		return new SemanticAttribute(name, IDENTIFIER, affectProperties, displayName, "");
	}
	public static AbstractAttribute newSimulationAttribute(String name,
			boolean affectProperties, String displayName) {
		return new SimulationAttribute(name, IDENTIFIER, affectProperties, displayName, "");
	}
}
