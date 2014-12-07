package com.variamos.syntaxsupport.type;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.ModelingAttribute;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;
import com.variamos.syntaxsupport.metametamodel.SimulationAttribute;


@SuppressWarnings("serial")
public class StringType extends Type{
	public static final String IDENTIFIER = "String";
	protected StringType(){
		super(IDENTIFIER);
	}
	
//	@Override
//	public boolean contains(Object obj) {
//		return obj instanceof String;
//	}

	@Override
	public AbstractAttribute makeAttribute(String name) {
		return new AbstractAttribute(name, getIdentifier(),"");
	}

	public static AbstractAttribute newModelingAttribute(String name) {
		return new ModelingAttribute(name, IDENTIFIER, "");
	}
	public static AbstractAttribute newSemanticAttribute(String name) {
		return new SemanticAttribute(name, IDENTIFIER, "");
	}
	public static AbstractAttribute newSimulationAttribute(String name) {
		return new SimulationAttribute(name, IDENTIFIER, "");
	}
}
