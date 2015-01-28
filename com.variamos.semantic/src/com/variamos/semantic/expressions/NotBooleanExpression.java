package com.variamos.semantic.expressions;

import java.util.List;
import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.variamos.syntax.instancesupport.InstElement;

/**
 * Class to create the Not expression. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public class NotBooleanExpression extends AbstractBooleanExpression {
	public static final String TRANSFORMATION = "-";

	public NotBooleanExpression(InstElement left, String leftAttributeName) {
		super(left, leftAttributeName);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	public NotBooleanExpression(AbstractExpression subExpression) {
		super(null, null, false, subExpression);
		this.expressionConnectors.add(TRANSFORMATION);
		operation = TRANSFORMATION;
	}

	@Override
	public BooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap) {
		List<Expression> expressionTerms = expressionTerms(f, idMap);
		return f.not((Identifier) expressionTerms.get(0));
	}

	@Override
	public BooleanExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap, boolean negateLeft,
			boolean negateRight) {
		List<Expression> expressionTerms = expressionTerms(f, idMap);
		return (Identifier) expressionTerms.get(0);
	}

}
