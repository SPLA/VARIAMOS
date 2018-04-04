package com.variamos.hlcl.model.expressions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SymbolicExpression implements IntBooleanExpression {

	private static final String RELATION_OP="tuples_in"; //Name of the RELATION operation, included by avillota
	public static final int TYPE_REGULAR=1;
	public static final int TYPE_RELATION=0;
	
	protected int type;  //Type of the symbolic expression 0 is a relation, 1 is a regular one
	protected String name;
	protected List<Identifier> args;
	protected NumericIdentifier[][] tuples; // for Relations, included by avillota


	SymbolicExpression() {
		super();
		this.type = TYPE_REGULAR;
		args = new LinkedList<>();
	}

	SymbolicExpression(String name, Identifier... args) {
		super();
		this.name = name;
		this.type = TYPE_REGULAR;
		this.args = Arrays.asList(args);
	}
	
	/**
	 * method represents the global constraint Relation it includes a matrix of 
	 * NumericIdentifiers with the values in the table (matrix)
	 * @author Angela Villota
	 * @param tuples
	 * @param args
	 */
	SymbolicExpression(NumericIdentifier[][] tuples, Identifier... args) {
		super();
		this.name = RELATION_OP;
		this.type = TYPE_RELATION;
		this.tuples= tuples;
		this.args = Arrays.asList(args);
	}

	/**
	 * jcmunoz: added to validate expressions in editor
	 * 
	 * @return true if the expression has all the components
	 */
	@Override
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
	
	public NumericIdentifier[][] getTuples() {
		return tuples;
	}

	public void setTuples(NumericIdentifier[][] tuples) {
		this.tuples = tuples;
	}
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof SymbolicExpression) {
			SymbolicExpression bE = (SymbolicExpression) obj;
			// FIXME include identifiers??
			if (name.equals(bE.getName()))
				return true;
			else
				return false;
		} else
			return false;
	}

}
