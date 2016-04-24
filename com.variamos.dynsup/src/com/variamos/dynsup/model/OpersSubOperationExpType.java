package com.variamos.dynsup.model;

import java.util.ArrayList;
import java.util.List;

public class OpersSubOperationExpType extends OpersElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4179971760312524058L;
	private List<OpersExpr> semanticExpressions;

	public OpersSubOperationExpType() {
		super(null);
		semanticExpressions = new ArrayList<OpersExpr>();
	}

	public List<OpersExpr> getSemanticExpressions() {
		return semanticExpressions;
	}

	public void setSemanticExpressions(List<OpersExpr> semanticExpressions) {
		this.semanticExpressions = semanticExpressions;
	}

	public void addSemanticExpression(OpersExpr semanticExpression) {
		semanticExpressions.add(semanticExpression);
	}

	public boolean hasSemanticExpression(String identifier) {
		for (OpersExpr s : semanticExpressions)
			if (s.getIdentifier().equals(identifier))
				return true;
		return false;
	}

}
