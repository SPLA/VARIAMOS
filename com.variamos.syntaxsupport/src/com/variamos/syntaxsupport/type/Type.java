package com.variamos.syntaxsupport.type;

import java.io.Serializable;

import com.cfm.productline.Variable;
import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;

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
	public abstract AbstractAttribute makeAttribute(String name);
}
