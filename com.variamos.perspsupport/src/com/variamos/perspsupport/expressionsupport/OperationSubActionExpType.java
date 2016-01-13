package com.variamos.perspsupport.expressionsupport;

import java.util.ArrayList;
import java.util.List;

import com.variamos.perspsupport.semanticinterface.IntSemanticExpression;
import com.variamos.perspsupport.semanticsupport.AbstractSemanticElement;
import com.variamos.perspsupport.types.OperationSubActionExecType;

public class OperationSubActionExpType extends AbstractSemanticElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4179971760312524058L;
	private List<IntSemanticExpression> semanticExpressions;
	private OperationSubActionExecType expressionType;

	public OperationSubActionExpType() {
		super(null);
		semanticExpressions = new ArrayList<IntSemanticExpression>();
	}

	public OperationSubActionExpType(OperationSubActionExecType expressionType) {
		super(null);
		this.expressionType = expressionType;
		semanticExpressions = new ArrayList<IntSemanticExpression>();
	}

	public List<IntSemanticExpression> getSemanticExpressions() {
		return semanticExpressions;
	}

	public void setSemanticExpressions(
			List<IntSemanticExpression> semanticExpressions) {
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
		for (IntSemanticExpression s : semanticExpressions)
			if (s.getIdentifier().equals(identifier))
				return true;
		return false;
	}

}
