package com.variamos.refas.core.expressions;

import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.variamos.refas.core.simulationmodel.AbstractBooleanExpression;
/**
 * Class to create the Literal expression. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public class LiteralBooleanExpression extends AbstractBooleanExpression {
	public static final String TRANSFORMATION = "";
	private String expression;
	
	public LiteralBooleanExpression(String expression)
	{
		super(null, null, null, null);
		this.expressionConnectors.add(TRANSFORMATION);

		this.expression = expression;
	}
	
	
	@Override
	public BooleanExpression transform(HlclFactory f, Map<String, Identifier> idMap) {
		return f.literalBooleanExpression(expression);
	}

	@Override
	public BooleanExpression transformNegation(HlclFactory f, Map<String, Identifier> idMap, boolean negateLeft, boolean negateRight) {
		return f.not(f.literalBooleanExpression(expression));
	}

}
