package com.variamos.syntaxsupport.type;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.ModelingAttribute;
import com.variamos.syntaxsupport.metametamodel.SemanticAttribute;
import com.variamos.syntaxsupport.metametamodel.SimulationAttribute;


@SuppressWarnings("serial")
public class IntegerType extends Type{
	public static final String IDENTIFIER = "Integer";
	protected IntegerType(){
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
	
	public static ModelingAttribute newVariableModelingAttribute(String name) {
		return new ModelingAttribute (name, IDENTIFIER, 0);
	}
	public static SemanticAttribute newSemanticAttribute(String name) {
		return new SemanticAttribute (name, IDENTIFIER, 0);
	}
	public static SimulationAttribute newSimulationAttribute(String name) {
		return new SimulationAttribute (name, IDENTIFIER, 0);
	}
}
