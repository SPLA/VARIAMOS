package com.variamos.refas.core.simulationmodel;

import java.util.Map;

import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstVertex;

/**
 * Abstract Class to group at the BooleanTransformation. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public abstract class AbstractBooleanExpression extends
		AbstractExpression {

	public AbstractBooleanExpression() {
		super();
	}

	public AbstractBooleanExpression(
			AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
	}

	public AbstractBooleanExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
	}

	public AbstractBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			AbstractExpression subExpression) {
		super(vertex, attributeName, replaceTarget, subExpression);
	}

	public AbstractBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			BooleanExpression booleanExpression) {
		super(vertex, attributeName, replaceTarget, booleanExpression);
	}
	
	public AbstractBooleanExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			NumericExpression numericExpression) {
		super(vertex, attributeName, replaceTarget, numericExpression);
	}

	public AbstractBooleanExpression(InstElement left,
			String leftAttributeName) {
		super(left, leftAttributeName);
	}

	public abstract BooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap);
	
	public abstract BooleanExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap, boolean negateLeft, boolean negateRight);

}
