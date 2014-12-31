package com.variamos.refas.core.expressions;

import java.util.List;
import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.variamos.refas.core.simulationmodel.AbstractBooleanExpression;
import com.variamos.refas.core.simulationmodel.AbstractExpression;
import com.variamos.syntaxsupport.metamodel.InstElement;

/**
 * Class to create the And expression. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public class AndBooleanExpression extends AbstractBooleanExpression {
	public static final String TRANSFORMATION = "#/\\";

	public AndBooleanExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public AndBooleanExpression(InstElement vertex, String attributeName,
			boolean replaceRight, AbstractExpression subExpression) {
		super(vertex, attributeName, replaceRight, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	/*
	 * public AndBooleanTransformation(InstVertex vertex, String attributeName,
	 * boolean replaceRight, BooleanExpression simpleExpression) { super(vertex,
	 * attributeName, replaceRight, simpleExpression);
	 * this.expressionConnectors.add(TRANSFORMATION); }
	 * 
	 * public AndBooleanTransformation(InstVertex vertex, String attributeName,
	 * boolean replaceRight, NumericIdentifier numericIdentifier) {
	 * super(vertex, attributeName, replaceRight, numericIdentifier);
	 * this.expressionConnectors.add(TRANSFORMATION); }
	 */
	public AndBooleanExpression(AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public AndBooleanExpression() {
		operation = TRANSFORMATION;
	}

	@Override
	public BooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<Expression> expressionTerms = expressionTerms(f, idMap);
		return f.and((BooleanExpression) expressionTerms.get(0),
				(BooleanExpression) expressionTerms.get(1));
	}

	@Override
	public BooleanExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap, boolean negateLeft,
			boolean negateRight) {
		List<Expression> expressionTerms = expressionTermsNegation(f, idMap,
				true, true);
		return f.or((BooleanExpression) expressionTerms.get(0),
				(BooleanExpression) expressionTerms.get(1));
	}

}
