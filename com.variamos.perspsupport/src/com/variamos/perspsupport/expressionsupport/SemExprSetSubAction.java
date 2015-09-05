package com.variamos.perspsupport.expressionsupport;

import java.util.List;

import com.variamos.perspsupport.types.ExpressionExecType;

public class SemExprSetSubAction {
	private ExpressionSubAction expressionSubAction;
	public ExpressionSubAction getExpressionSubAction() {
		return expressionSubAction;
	}
	public void setExpressionSubAction(ExpressionSubAction expressionSubAction) {
		this.expressionSubAction = expressionSubAction;
	}
	public List<SemanticExpression> getSemanticExpressions() {
		return semanticExpressions;
	}
	public void setSemanticExpressions(List<SemanticExpression> semanticExpressions) {
		this.semanticExpressions = semanticExpressions;
	}
	public ExpressionExecType getExpressionType() {
		return expressionType;
	}
	public void setExpressionType(ExpressionExecType expressionType) {
		this.expressionType = expressionType;
	}
	private List<SemanticExpression> semanticExpressions;
	private ExpressionExecType expressionType;
}
