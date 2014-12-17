package com.cfm.productline;

import java.io.Serializable;

import com.cfm.productline.type.IntegerType;
import com.cfm.productline.type.StringType;

public class Asset implements Serializable, Editable{

	private static final long serialVersionUID = -6535463614831756558L;
	
	private String identifier;
	
	private Variable nameVar = StringType.newVariable("Name");
	private Variable cardVar = IntegerType.newVariable("Cardinality");
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public Variable getNameVar() {
		return nameVar;
	}
	public void setNameVar(Variable nameVar) {
		this.nameVar = nameVar;
	}
	public void setName(String name){
		nameVar.setValue(name);
	}
	public String getName(){
		return (String)nameVar.getValue();
	}
	@Override
	public Variable[] getEditableVariables() {
		return new Variable[]{ nameVar, cardVar };
	}
	@Override
	public String toString() {
		return getName();
	}
	public Variable getCardVar() {
		return cardVar;
	}
	public void setCardVar(Variable cardVar) {
		this.cardVar = cardVar;
	}
	
	
}
