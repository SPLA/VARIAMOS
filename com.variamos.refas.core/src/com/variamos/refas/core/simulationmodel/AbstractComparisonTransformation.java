package com.variamos.refas.core.simulationmodel;

import java.util.Map;

/**
 * Abstract  Class to group the ComparisonTranformation. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.ComparisonExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.Identifier;
import com.cfm.hlcl.NumericExpression;
import com.variamos.syntaxsupport.metamodel.InstVertex;

/**
 * Abstract Class to group at the ComparisonTransformation, currently only for
 * Equals. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-15
 */
public abstract class AbstractComparisonTransformation extends
		AbstractTransformation {

	public AbstractComparisonTransformation() {
		super();
	}

	public AbstractComparisonTransformation(
			AbstractTransformation leftSubExpression,
			AbstractTransformation rightSubExpression) {
		super(leftSubExpression, rightSubExpression);
	}

	public AbstractComparisonTransformation(InstVertex left, InstVertex right,
			String leftAttributeName, String rightAttributeName) {
		super(left, right, leftAttributeName, rightAttributeName);
	}

	public AbstractComparisonTransformation(InstVertex vertex,
			String attributeName, boolean replaceTarget,
			AbstractTransformation subExpression) {
		super(vertex, attributeName, replaceTarget, subExpression);
	}

	public AbstractComparisonTransformation(InstVertex vertex,
			String attributeName, boolean replaceTarget,
			BooleanExpression booleanExpression) {
		super(vertex, attributeName, replaceTarget, booleanExpression);
	}

	public AbstractComparisonTransformation(InstVertex vertex,
			String attributeName, boolean replaceTarget,
			NumericExpression numericExpression) {
		super(vertex, attributeName, replaceTarget, numericExpression);
	}

	public abstract BooleanExpression transform(HlclFactory f,
			Map<String, Identifier> idMap);

	public abstract ComparisonExpression transformNegation(HlclFactory f,
			Map<String, Identifier> idMap);

}
