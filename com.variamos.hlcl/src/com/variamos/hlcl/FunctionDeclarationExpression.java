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
	@Override
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof FunctionDeclarationExpression) {
			FunctionDeclarationExpression bE = (FunctionDeclarationExpression) obj;
			if (header.equals(bE.getHeader()))
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public int hashCode() {
		return header.hashCode();
	}

}
