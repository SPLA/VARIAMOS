package com.variamos.hlcl;

import java.util.List;

public class ListDefinitionExpression implements Expression {
	private List<Identifier> values;

	public ListDefinitionExpression(List<Identifier> values) {
		this.values = values;
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	public boolean isValidExpression() {
		if (values == null)
			return false;
		// TODO how to validate a list definition expression?
		return true;
	};

	public List<Identifier> getValues() {
		return values;
	}

	public void setValues(List<Identifier> values) {
		this.values = values;
	}
}
