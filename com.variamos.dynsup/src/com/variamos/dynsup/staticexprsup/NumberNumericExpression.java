package com.variamos.dynsup.staticexprsup;

import java.util.Map;

import com.variamos.hlcl.model.expressions.HlclFactory;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntNumericExpression;

/**
 * Class to create the Number expression. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */

public class NumberNumericExpression extends AbstractNumericExpression {
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
	public IntNumericExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		return f.number(number);
	}

}