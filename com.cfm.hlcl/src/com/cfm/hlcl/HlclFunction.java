package com.cfm.hlcl;

public class HlclFunction extends HlclProgram{
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
