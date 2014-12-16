package com.variamos.refas.core.transformations;

import java.util.List;
import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.variamos.refas.core.simulationmodel.AbstractBooleanTransformation;
import com.variamos.refas.core.simulationmodel.AbstractTransformation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
/**
 * Class to create the DoubleImplication expression. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public class DoubleImplicationBooleanTransformation extends AbstractBooleanTransformation {
	private static final String TRANSFORMATION = "#<==>";
	
	public DoubleImplicationBooleanTransformation(InstVertex left,
			InstVertex right, String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
	}
	
	public DoubleImplicationBooleanTransformation(InstVertex vertex,
			String attributeName, boolean replaceTarget,
			AbstractTransformation subExpression) {
		super(vertex, attributeName, replaceTarget, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}

	public DoubleImplicationBooleanTransformation(InstVertex vertex,
			String attributeName, boolean replaceTarget,
			BooleanExpression comparativeExpression) {
		super(vertex, attributeName, replaceTarget, comparativeExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}
	
	public DoubleImplicationBooleanTransformation(InstVertex vertex,
			String attributeName, boolean replaceTarget,
			NumericExpression comparativeExpression) {
		super(vertex, attributeName, replaceTarget, comparativeExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}

	public DoubleImplicationBooleanTransformation(
			AbstractTransformation leftSubExpression,
			AbstractTransformation rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
		this.expressionConnectors.add(TRANSFORMATION);
	}

	public DoubleImplicationBooleanTransformation() {
		super();
	}

	@Override
	public BooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<Expression> expressionTerms = expressionTerms(f, idMap);
		return f.doubleImplies((BooleanExpression) expressionTerms.get(0),
				(BooleanExpression) expressionTerms.get(1));
	}
	
	@Override
	public BooleanExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap, boolean negateLeft, boolean negateRight) {
		List<Expression> expressionTerms = expressionTermsNegation(f, idMap, false, false);
		return f.notEquals((BooleanExpression) expressionTerms.get(0),
				(BooleanExpression) expressionTerms.get(1));
	}
}
