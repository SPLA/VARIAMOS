package com.variamos.refas.core.simulationmodel;

import java.util.Map;

import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.variamos.syntaxsupport.metamodel.InstVertex;

public abstract class AbstractNumericTransformation extends AbstractTransformation {

	public AbstractNumericTransformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AbstractNumericTransformation(
			AbstractTransformation leftSubExpression,
			AbstractTransformation rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
	}

	public AbstractNumericTransformation(InstVertex left, InstVertex right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
	}

	public AbstractNumericTransformation(InstVertex vertex,
			String attributeName, boolean replaceTarget,
			AbstractTransformation subExpression) {
		super(vertex, attributeName, replaceTarget, subExpression);
	}

	public AbstractNumericTransformation(InstVertex vertex,
			String attributeName, boolean replaceTarget,
			Expression comparativeExpression) {
		super(vertex, attributeName, replaceTarget, comparativeExpression);
	}

	public abstract NumericExpression transform(HlclFactory f,
			Map<String, Identifier> idMap);

}
