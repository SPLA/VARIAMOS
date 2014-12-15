package com.variamos.refas.core.transformations;

import java.util.List;
import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.variamos.refas.core.simulationmodel.AbstractBooleanTransformation;
import com.variamos.refas.core.simulationmodel.AbstractTransformation;
import com.variamos.syntaxsupport.metamodel.InstVertex;

public class NotBooleanTransformation extends AbstractBooleanTransformation {
	private static final String TRANSFORMATION = "-";
	
	public NotBooleanTransformation(InstVertex left,String leftAttributeName)
	{
		super(left, null, leftAttributeName, null);
		this.expressionConnectors.add(TRANSFORMATION);
	}
	
	public NotBooleanTransformation(AbstractTransformation subExpression)
	{
		super(null, null, false, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}
	
	@Override
	public BooleanExpression transform(HlclFactory f, Map<String, Identifier> idMap) {
		List<Expression> expressionTerms = expressionTerms(f, idMap);
		return f.not((Identifier) expressionTerms.get(0));
	}

}
