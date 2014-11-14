package com.cfm.productline;

import java.io.Serializable;

import com.cfm.productline.type.IntegerType;
import com.cfm.productline.type.StringType;

public class Asset extends AbstractElement implements Serializable, Editable{
	public String getClassId()
	{
		return "Asset_";
		
	}
	public Asset()
	{
		super();
	}
	public Asset(String alias)
	{
		super();
		if (alias != null)
			this.alias = alias;
	}
	
	public Asset(String alias, String id)
	{
		this(alias);
		identifier = id;
		
	}
	
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
