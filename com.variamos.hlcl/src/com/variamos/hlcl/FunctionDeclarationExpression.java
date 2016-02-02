package com.variamos.hlcl;

public class FunctionDeclarationExpression implements BooleanExpression {
	private SymbolicExpression header;

	public FunctionDeclarationExpression(SymbolicExpression header) {
		super();
		this.header = header;
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	public boolean isValidExpression() {
		if (header == null)
			return false;
		return true;
	};

	public SymbolicExpression getHeader() {
		return header;
	}

	public void setHeader(SymbolicExpression header) {
		this.header = header;
	}

}
