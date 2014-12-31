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
 * Abstract  Class to group the NUmericTranformation. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public abstract class AbstractNumericExpression extends AbstractExpression {

	public AbstractNumericExpression() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AbstractNumericExpression(
			AbstractExpression leftSubExpression,
			AbstractExpression rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
	}

	public AbstractNumericExpression(InstElement left, InstElement right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
	}

	public AbstractNumericExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			AbstractExpression subExpression) {
		super(vertex, attributeName, replaceTarget, subExpression);
	}

	public AbstractNumericExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			BooleanExpression booleanExpression) {
		super(vertex, attributeName, replaceTarget, booleanExpression);
	}
	
	public AbstractNumericExpression(InstElement vertex,
			String attributeName, boolean replaceTarget,
			NumericExpression numericExpression) {
		super(vertex, attributeName, replaceTarget, numericExpression);
	}

	public abstract NumericExpression transform(HlclFactory f,
			Map<String, Identifier> idMap);

}
