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

public class ImplicationBooleanTransformation extends AbstractBooleanTransformation {
	private static final String TRANSFORMATION = "#==>";
	
	public ImplicationBooleanTransformation(InstVertex left,
			InstVertex right, String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
	}
	
	public ImplicationBooleanTransformation(InstVertex vertex,
			String attributeName, boolean replaceTarget,
			AbstractTransformation subExpression) {
		super(vertex, attributeName, replaceTarget, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}

	public ImplicationBooleanTransformation(InstVertex vertex,
			String attributeName, boolean replaceTarget,
			Expression comparativeExpression) {
		super(vertex, attributeName, replaceTarget, comparativeExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}

	public ImplicationBooleanTransformation(
			AbstractTransformation leftSubExpression,
			AbstractTransformation rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}

	public ImplicationBooleanTransformation() {
		super();
	}

	@Override
	public BooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<Expression> expressionTerms = expressionTerms(f, idMap);
		return f.implies((BooleanExpression) expressionTerms.get(0),
				(BooleanExpression) expressionTerms.get(1));
	}
}
