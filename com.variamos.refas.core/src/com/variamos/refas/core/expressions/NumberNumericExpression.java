package com.variamos.refas.core.expressions;

import java.util.Map;

import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.variamos.refas.core.simulationmodel.AbstractNumericTransformation;

/**
 * Class to create the Number expression. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */

public class NumberNumericExpression extends AbstractNumericTransformation {
	public int getNumber() {
		return number;
	}

	public static final String TRANSFORMATION = "";
	private int number;

	public NumberNumericExpression(int number) {
		super();
		this.expressionConnectors.add(TRANSFORMATION);
		this.number = number;
	}

	@Override
	public NumericExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		return f.number(number);
	}

}