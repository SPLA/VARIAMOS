package com.variamos.refas.core.transformations;

import java.util.Map;

import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.variamos.refas.core.simulationmodel.AbstractNumericTransformation;


public class NumberNumericTransformation extends AbstractNumericTransformation {
	private static final String TRANSFORMATION = "";
	private int number;
	public NumberNumericTransformation(int number) {
		super();
		this.expressionConnectors.add(TRANSFORMATION);
	}	
	@Override
	public NumericExpression transform(HlclFactory f, Map<String, Identifier> idMap) {		
		return f.number(number);
	}

}