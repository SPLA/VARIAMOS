package com.variamos.perspsupport.expressionsupport;

import java.util.ArrayList;
import java.util.List;

import com.variamos.perspsupport.types.OperationSubActionExecType;

public class OperationSubActionExpType {
	public OperationSubActionExpType(OperationSubActionExecType expressionType) {
		super();
		this.expressionType = expressionType;
		semanticExpressions = new ArrayList<SemanticExpression>();
	}

	private List<SemanticExpression> semanticExpressions;
	private OperationSubActionExecType expressionType;

	public List<SemanticExpression> getSemanticExpressions() {
		return semanticExpressions;
	}

	public void setSemanticExpressions(
			List<SemanticExpression> semanticExpressions) {
		this.semanticExpressions = semanticExpressions;
	}

	public OperationSubActionExecType getExpressionType() {
		return expressionType;
	}

	public void setExpressionType(OperationSubActionExecType expressionType) {
		this.expressionType = expressionType;
	}

}
