package com.cfm.productline.type;

import java.io.Serializable;

import com.cfm.productline.Variable;

@SuppressWarnings("serial")
public abstract class Type implements Serializable{
	
	protected String identifier;

	protected Type(String identifier) {
		this.identifier = identifier;
	}

	protected Type() {
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	//public abstract boolean contains(Object obj);
	public abstract Variable makeVariable(String name);
}
