package com.cfm.productline;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.cfm.productline.type.BooleanType;
import com.cfm.productline.type.StringType;

@SuppressWarnings("serial")

public class VariabilityElement extends AbstractElement implements Serializable, Prototype, Editable{
	
	public String getClassId()
	{
		return "VP_";
		
	}
//	protected Variable varName = StringType.newVariable("Name");
//	protected Variable varIdentifier = StringType.newVariable("Identifier");
//	protected Variable varDescription = StringType.newVariable("Description");
//	protected Variable varVisible = BooleanType.newVariable("Visibility");
//	protected Variable varValidity = BooleanType.newVariable("Validity");
//	protected Variable varAllocation = StringType.newVariable("Allocation");
	public static final String 	VAR_NAME = "Name",
								VAR_IDENTIFIER = "Identifier",
								VAR_DESCRIPTION = "Description",
								VAR_VISIBILITY = "Visibility",
								VAR_VALIDITY = "Validity",
								VAR_ALLOCATION = "Allocation";
	
	protected Map<String, Variable> vars = new HashMap<>();
	
//	protected Variable value = new IntegerRangeDomain().makeVariable("value");
	protected List<Variable> varAttributes = new Vector<>();
	protected List<String> assets; 
	
	//For Allocating a resource (image, text ...)
	//private String allocation;
	
	public VariabilityElement(){
		super();

		vars.put(VAR_NAME, StringType.newVariable(VAR_NAME));
		vars.put(VAR_IDENTIFIER, StringType.newVariable(VAR_IDENTIFIER));
		vars.put(VAR_DESCRIPTION, StringType.newVariable(VAR_DESCRIPTION));
		vars.put(VAR_VISIBILITY, BooleanType.newVariable(VAR_VISIBILITY));
		vars.put(VAR_VALIDITY, BooleanType.newVariable(VAR_VALIDITY));
		vars.put(VAR_ALLOCATION, StringType.newVariable(VAR_ALLOCATION));
		
		setVariableValue(VAR_VISIBILITY, Boolean.TRUE);
		setVariableValue(VAR_VALIDITY, Boolean.TRUE);
		assets = new ArrayList<>();
	}
	
	public Variable getVariable(String name){
		return vars.get(name);
	}
	
	public void setVariableValue(String name, Object value){
		//GARA
		getVariable(name).setValue(value);
	}
	
	public Object getVariableValue(String name){
		return getVariable(name).getValue();
	}
	public VariabilityElement(String alias) {
		this();
		if (alias != null)
			this.alias = alias;
	}
	public VariabilityElement(String alias, String id) {
		this(alias);
		setVariableValue(VAR_IDENTIFIER, String.valueOf(id.charAt(0)).toUpperCase() + id.trim().substring(1));
		setVariableValue(VAR_NAME, (String.valueOf(id.charAt(0)).toUpperCase() + id.trim().substring(1)));
		
//		varIdentifier.setValue(String.valueOf(id.charAt(0)).toUpperCase()
//				+ id.trim().substring(1));
//		varName.setValue((String.valueOf(id.charAt(0)).toUpperCase()
//				+ id.trim().substring(1)));
		//this.identifier = id;
		//this.name = id;
	}

	public String getIdentifier() {
		//return identifier;
		//StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		//System.out.println(stackTraceElements[2].toString());
		//System.out.println((String)getVariableValue(VAR_IDENTIFIER));
		//System.out.println((String)getVariableValue(VAR_NAME));
		
		return (String)getVariableValue(VAR_IDENTIFIER);
	}

	public void setIdentifier(String id) {
		//this.identifier = id;
		setVariableValue(VAR_IDENTIFIER, id);
		//System.out.println("OE"+(String)getVariableValue(VAR_IDENTIFIER));
	}

	public void printDebug(PrintStream out) {
		StringBuffer buf = new StringBuffer();
		buf.append(":vp ");
		buf.append(getIdentifier());
		buf.append("{\n");
		
		buf.append("\tName : ");
		buf.append(getName());
		buf.append("\n");
		
		/*
		int size = properties.keySet().size();
		int i = 1;
		for(String key : properties.keySet()){
			buf.append("\t");
			buf.append(key);
			buf.append(" : ");
			
			Variable p = properties.get(key);
			buf.append(p.toString());
			
			if( i < size)
				buf.append(",");
			
			buf.append("\n");
			i++;
		}
		*/
		buf.append("\t};");
		out.println(buf.toString());
	}

//	public Object getDefaultValue() {
//		return defaultValue;
//	}
//
//	public void setDefaultValue(Object defaultValue) {
//		this.defaultValue = defaultValue;
//	}

	public String getDescription() {
		return (String)getVariableValue(VAR_DESCRIPTION);
		//return description;
	}

	public void setDescription(String description) {
		//this.description = description;
		//this.varDescription.setValue(description);
		setVariableValue(VAR_DESCRIPTION, description);
	}

	public boolean isVisible() {
		//return visibility;
		try{
		return (Boolean)getVariableValue(VAR_VISIBILITY);}
		catch(Exception e){
			return Boolean.TRUE;
		}
	}

	public void setVisible(boolean visibility) {
//		this.visibility = visibility;
		//this.varVisible.setValue(visibility);
		setVariableValue(VAR_VISIBILITY, visibility);
	}

	public boolean isValidity() {
//		return validity;
		return (Boolean)getVariableValue(VAR_VALIDITY);
	}

	public void setValidity(boolean validity) {
		//this.validity = validity;
		setVariableValue(VAR_VALIDITY, validity);
	}

	public String getAllocation() {
		//return allocation;
		return (String)getVariableValue(VAR_ALLOCATION);
	}

	public void setAllocation(String allocation) {
		//this.allocation = allocation;
		setVariableValue(VAR_ALLOCATION, allocation);
	}

	public String getName() {
		//return name;
		return (String)getVariableValue(VAR_NAME);
	}

	public void setName(String name) {
		setVariableValue(VAR_NAME, name);

	}

	@Override
	public String toString() {
		return getName();
	}
	
	public Variable[] getEditableVariables(){
		return new Variable[]{
			vars.get(VAR_NAME),
			vars.get(VAR_DESCRIPTION),
			vars.get(VAR_VISIBILITY),
			vars.get(VAR_VALIDITY)
		};
	}

//	public Variable getVarName() {
//		return varName;
//	}
//
//	public void setVarName(Variable varName) {
//		this.varName = varName;
//	}
//
//	public Variable getVarIdentifier() {
//		return varIdentifier;
//	}
//
//	public void setVarIdentifier(Variable varIdentifier) {
//		this.varIdentifier = varIdentifier;
//	}
//
//	public Variable getVarDescription() {
//		return varDescription;
//	}
//
//	public void setVarDescription(Variable varDescription) {
//		this.varDescription = varDescription;
//	}
//
//	public Variable getVarVisible() {
//		return varVisible;
//	}
//
//	public void setVarVisible(Variable varVisible) {
//		this.varVisible = varVisible;
//	}
//
//	public Variable getVarValidity() {
//		return varValidity;
//	}
//
//	public void setVarValidity(Variable varValidity) {
//		this.varValidity = varValidity;
//	}
//
//	public Variable getVarAllocation() {
//		return varAllocation;
//	}
//
//	public void setVarAllocation(Variable varAllocation) {
//		this.varAllocation = varAllocation;
//	}

//	public Variable getValue() {
//		return value;
//	}
//
//	public void setValue(Variable value) {
//		this.value = value;
//	}

	public List<Variable> getVarAttributes() {
		return varAttributes;
	}

	public void setVarAttributes(List<Variable> varAttributes) {
		this.varAttributes = varAttributes;
	}

	public List<String> getAssets() {
		return assets;
	}

	public void setAssets(List<String> assets) {
		this.assets = assets;
	}

	public Map<String, Variable> getVars() {
		return vars;
	}

	public void setVars(Map<String, Variable> vars) {
		this.vars = vars;
	}
}
