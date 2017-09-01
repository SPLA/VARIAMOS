package com.variamos.hlcl;

public class HlclFunction extends HlclProgram{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1531551300143002646L;
	private FunctionDeclarationExpression decl;
	
	public HlclFunction(FunctionDeclarationExpression decl) {
		super();
		this.decl = decl;
	}
	
	public FunctionDeclarationExpression getDecl() {
		return decl;
	}
	public void setDecl(FunctionDeclarationExpression decl) {
		this.decl = decl;
	}
}