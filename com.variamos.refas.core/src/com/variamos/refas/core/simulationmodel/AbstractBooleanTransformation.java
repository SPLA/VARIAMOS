package com.variamos.refas.core.simulationmodel;

import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.variamos.syntaxsupport.metamodel.InstVertex;

public abstract class AbstractBooleanTransformation extends AbstractTransformation {

	public AbstractBooleanTransformation() {
		super();
	}

	public AbstractBooleanTransformation(
			AbstractTransformation leftSubExpression,
			AbstractTransformation rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
	}

	public AbstractBooleanTransformation(InstVertex left, InstVertex right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
	}

	public AbstractBooleanTransformation(InstVertex vertex,
			String attributeName, boolean replaceTarget,
			AbstractTransformation subExpression) {
		super(vertex, attributeName, replaceTarget, subExpression);
	}

	public AbstractBooleanTransformation(InstVertex vertex,
			String attributeName, boolean replaceTarget,
			Expression comparativeExpression) {
		super(vertex, attributeName, replaceTarget, comparativeExpression);
	}

	public abstract BooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap);

}
