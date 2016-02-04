package com.variamos.hlcl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SymbolicExpression implements BooleanExpression {

	protected String name;
	protected List<Identifier> args;

	protected SymbolicExpression() {
		super();
		args = new LinkedList<>();
	}

	protected SymbolicExpression(String name, Identifier... args) {
		super();
		this.name = name;
		this.args = Arrays.asList(args);
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	public boolean isValidExpression() {
		if (name == null)
			return false;
		// TODO how to define a valid SymbolicExpression?
		return true;
	};

	public String getName() {
		return name;
	}

	public List<Identifier> getArgs() {
		return args;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setArgs(List<Identifier> args) {
		this.args = args;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SymbolicExpression [name=" + name + ", args=" + args + "]";
	}

}
