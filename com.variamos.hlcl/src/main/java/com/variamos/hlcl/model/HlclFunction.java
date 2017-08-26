package com.variamos.hlcl.model;

import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.model.expressions.FunctionDeclarationExpression;

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
