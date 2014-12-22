package com.variamos.refas.core.transformations;

import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.variamos.refas.core.simulationmodel.AbstractBooleanTransformation;
/**
 * Class to create the Literal expression. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public class LiteralBooleanTransformation extends AbstractBooleanTransformation {
	public static final String TRANSFORMATION = "";
	private String expression;
	
	public LiteralBooleanTransformation(String expression)
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
