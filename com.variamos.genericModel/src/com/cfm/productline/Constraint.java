package com.cfm.productline;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public abstract class Constraint implements Serializable, Editable{
	protected String identifier;
	protected String text;
	
	public Constraint() {
		super();
	}

	public Constraint(String text){
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public abstract List<String> getRelatedIds();

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String id) {
		this.identifier = id;
	}

	public void printDebug(PrintStream out) {
		out.println(":c " + identifier + " {" + getText() + "}");
	}
	
	protected String firstUpperCaseString(String name) {
		if( name == null )
			return null;
		
		String changedName = String.valueOf(name.charAt(0)).toUpperCase()
				+ name.trim().substring(1);

		return changedName;
	}
	
	public String getConstraintSource(){
		return getRelatedIds().get(0);
	}
}
