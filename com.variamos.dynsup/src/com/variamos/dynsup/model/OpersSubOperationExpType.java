package com.variamos.dynsup.model;

import java.util.ArrayList;
import java.util.List;

import com.variamos.dynsup.interfaces.IntMetaExpression;

public class OpersSubOperationExpType extends OpersElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4179971760312524058L;
	private List<IntMetaExpression> semanticExpressions;

	public OpersSubOperationExpType() {
		super(null);
		semanticExpressions = new ArrayList<IntMetaExpression>();
	}

	public List<IntMetaExpression> getSemanticExpressions() {
		return semanticExpressions;
	}

	public void setSemanticExpressions(
			List<IntMetaExpression> semanticExpressions) {
		this.semanticExpressions = semanticExpressions;
	}

	public void addSemanticExpression(OpersExpr semanticExpression) {
		semanticExpressions.add(semanticExpression);
	}

	public boolean hasSemanticExpression(String identifier) {
		for (IntMetaExpression s : semanticExpressions)
			if (s.getIdentifier().equals(identifier))
				return true;
		return false;
	}

}
