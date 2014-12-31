package com.variamos.refas.core.expressions;

import java.util.List;
import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.cfm.hlcl.NumericIdentifier;
import com.variamos.refas.core.simulationmodel.AbstractBooleanExpression;
import com.variamos.refas.core.simulationmodel.AbstractExpression;
import com.variamos.syntaxsupport.metamodel.InstElement;

/**
 * Class to create the Assign expression. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-14
 */
public class AssignBooleanExpression extends AbstractBooleanExpression {
	public static final String TRANSFORMATION = "#>=#";

	public AssignBooleanExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public AssignBooleanExpression(InstElement left, String attributeName,
			AbstractExpression subExpression) {
		super(left, attributeName, true, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public AssignBooleanExpression(InstElement left, String attributeName,
			BooleanExpression booleanExpression) {
		super(left, attributeName, true, booleanExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public AssignBooleanExpression(InstElement left, String attributeName,
			NumericExpression numericExpression) {
		super(left, attributeName, true, numericExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public AssignBooleanExpression(InstElement toRelation, String string,
			NumericIdentifier number) {
		super(toRelation, string, true, number);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	@Override
	public BooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<Expression> expressionTerms = expressionTerms(f, idMap);
		return f.assign((Identifier) expressionTerms.get(0),
				(Expression) expressionTerms.get(1));
	}

	@Override
	public BooleanExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap, boolean noAssign,
			boolean valueNegation) {
		List<Expression> expressionTerms = expressionTermsNegation(f, idMap,
				false, false);
		return f.notEquals((Identifier) expressionTerms.get(0),
				(Expression) expressionTerms.get(1));
	}

}
