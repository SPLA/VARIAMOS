package com.cfm.hlcl;

public class FunctionDeclarationExpression implements BooleanExpression{
	private SymbolicExpression header;
	
	public FunctionDeclarationExpression(SymbolicExpression header) {
		super();
		this.header = header;
	}

	public SymbolicExpression getHeader() {
		return header;
	}

	public void setHeader(SymbolicExpression header) {
		this.header = header;
	}
	
	
}
