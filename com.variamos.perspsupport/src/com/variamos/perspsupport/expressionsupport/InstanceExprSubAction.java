package com.variamos.perspsupport.expressionsupport;

import java.util.List;

import com.variamos.perspsupport.types.ExpressionExecType;

/**
 * TODO
 * A class to support instance sub actions to generalize the semantic operationalization 
 * with GUI edition. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-02-05
 */
/**
 * @author jcmunoz
 *
 */
public class InstanceExprSubAction {
	private ExpressionSubAction expressionSubAction;
	public ExpressionSubAction getExpressionSubAction() {
		return expressionSubAction;
	}
	public void setExpressionSubAction(ExpressionSubAction expressionSubAction) {
		this.expressionSubAction = expressionSubAction;
	}
	public List<InstanceExpression> getInstanceExpressions() {
		return instanceExpressions;
	}
	public void setInstanceExpressions(List<InstanceExpression> instanceExpressions) {
		this.instanceExpressions = instanceExpressions;
	}
	public ExpressionExecType getExpressionType() {
		return expressionType;
	}
	public void setExpressionType(ExpressionExecType expressionType) {
		this.expressionType = expressionType;
	}
	private List<InstanceExpression> instanceExpressions;
	private ExpressionExecType expressionType;
}
