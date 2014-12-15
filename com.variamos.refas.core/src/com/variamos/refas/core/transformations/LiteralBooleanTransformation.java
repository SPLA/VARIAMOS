package com.variamos.refas.core.transformations;

import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.variamos.refas.core.simulationmodel.AbstractBooleanTransformation;

public class LiteralBooleanTransformation extends AbstractBooleanTransformation {
	private static final String TRANSFORMATION = "";
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

}
