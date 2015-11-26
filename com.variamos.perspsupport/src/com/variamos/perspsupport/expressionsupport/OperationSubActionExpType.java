package com.variamos.perspsupport.expressionsupport;

import java.util.ArrayList;
import java.util.List;

import com.variamos.perspsupport.types.OperationSubActionExecType;

public class OperationSubActionExpType {

	private List<SemanticExpression> semanticExpressions;
	private OperationSubActionExecType expressionType;

	public OperationSubActionExpType(OperationSubActionExecType expressionType) {
		super();
		this.expressionType = expressionType;
		semanticExpressions = new ArrayList<SemanticExpression>();
	}

	public List<SemanticExpression> getSemanticExpressions() {
		return semanticExpressions;
	}

	public void setSemanticExpressions(
			List<SemanticExpression> semanticExpressions) {
		this.semanticExpressions = semanticExpressions;
	}

	public void addSemanticExpression(SemanticExpression semanticExpression) {
		semanticExpressions.add(semanticExpression);
	}

	public OperationSubActionExecType getExpressionType() {
		return expressionType;
	}

	public void setExpressionType(OperationSubActionExecType expressionType) {
		this.expressionType = expressionType;
	}

	public boolean hasSemanticExpression(String identifier) {
		for (SemanticExpression s : semanticExpressions)
			if (s.getIdentifier().equals(identifier))
				return true;
		return false;
	}

}
