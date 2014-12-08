package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticEdge;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupDependency;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class InstAttribute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6097914483168975659L;
	public static final String 	VAR_IDENTIFIER = "Identifier",
								VAR_ATTRIBUTE = "attributeName",
								VAR_VALUE = "Value",
								VAR_DISPLAYVALUE = "DispValue", //For JList with indexes for VAR_VALUE (MClass and MEnumeration)
								VAR_VALIDATIONGROUPDEPLIST = "ValidGDList", //Initially for List IntSemanticGroupDependency
								VAR_VALIDATIONDIRECTRELLIST = "ValidDRList", //Initially for List IntSemanticDirectRelation
								VAR_VALIDATIONMETAEDGELIST = "ValidMEList";
	protected Map<String, Object> vars = new HashMap<>();
	private Object object;
	
	/*private String identifier;
	private Object value;
	*/
	private AbstractAttribute attribute;
	
	public InstAttribute()
	{
		
	}
	
	
	public InstAttribute(String identifier, AbstractAttribute modelingAttribute,
			Object value) {
		super();
		this.attribute = modelingAttribute;
		vars.put(VAR_IDENTIFIER, identifier);
		vars.put(VAR_ATTRIBUTE, modelingAttribute.getName());
		vars.put(VAR_VALUE, value);
		vars.put(VAR_DISPLAYVALUE, null);
		//vars.put(VAR_DISOBJECT, null);
//		this.identifier = identifier;
		
//		this.value = value;
	}
	
	public InstAttribute(String identifier, AbstractAttribute modelingAttribute,
			Object value, Object object) {
		super();
		this.attribute = modelingAttribute;
		vars.put(VAR_IDENTIFIER, identifier);
		vars.put(VAR_ATTRIBUTE, modelingAttribute.getName());
		vars.put(VAR_VALUE, value);
		vars.put(VAR_DISPLAYVALUE, null);
		//vars.put(VAR_DISOBJECT, object);
		this.object=object;
//		this.identifier = identifier;
		
//		this.value = value;
	}
	
	public Object getVariable(String name){
		return vars.get(name);
	}
	
	public void setVariable(String name, Object value){
		vars.put(name, value);
	}	
	
	public void setIdentifier(String identifier) {
		//this.identifier = identifier;
		setVariable(VAR_IDENTIFIER, identifier);
	}
	
	
	public void setValidationGDList(List<IntSemanticGroupDependency> semGD) {
		//this.identifier = identifier;
		setVariable(VAR_VALIDATIONGROUPDEPLIST, semGD);
	}
	public void setValidationMEList(List<MetaEdge> metaEdge) {
		//this.identifier = identifier;
		setVariable(VAR_VALIDATIONMETAEDGELIST, metaEdge);
	}

	@SuppressWarnings("unchecked")
	public List<MetaEdge>  getValidationMEList() {
		return (List<MetaEdge> )getVariable(VAR_VALIDATIONMETAEDGELIST);
		//return identifier;
	}
	
	@SuppressWarnings("unchecked")
	public List<IntSemanticGroupDependency>  getValidationGDList() {
		return (List<IntSemanticGroupDependency> )getVariable(VAR_VALIDATIONGROUPDEPLIST);
		//return identifier;
	}
	
	public void setValidationDRList(List<IntDirectSemanticEdge> semGD) {
		//this.identifier = identifier;
		setVariable(VAR_VALIDATIONDIRECTRELLIST, semGD);
	}
	
	@SuppressWarnings("unchecked")
	public List<IntDirectSemanticEdge>  getValidationDRList() {
		return (List<IntDirectSemanticEdge> )getVariable(VAR_VALIDATIONDIRECTRELLIST);
		//return identifier;
	}

	public void setAttribute(AbstractAttribute modelingAttribute) {
		this.attribute = modelingAttribute;
		if (modelingAttribute !=null )
		setVariable(VAR_ATTRIBUTE, modelingAttribute.getName());
	}

	public String getIdentifier() {
		return (String)getVariable(VAR_IDENTIFIER);
		//return identifier;
	}
	public AbstractAttribute getAttribute() {
		return attribute;
	}
	
	public String getAttributeName() {
		return (String)getVariable(VAR_ATTRIBUTE);
	}
	
	public Object getValue() {
		return getVariable(VAR_VALUE);
		//return value;
	}
	
	public Object getDisplayValue() {
		if (getVariable(VAR_DISPLAYVALUE) == null)
		return getVariable(VAR_VALUE);
		else
			return getVariable(VAR_DISPLAYVALUE);
		//return value;
	}
	public void setValue(Object value) {
		setVariable(VAR_VALUE, value);
		//this.value = value;
	}
	
	public String getModelingAttributeType(){
		return attribute.getType();
	}
	

	public Object getEnumType() {
		// TODO Auto-generated method stub
		return attribute.getEnumType();
	}
	
	public void setType(String selectedItem) {
		// TODO Auto-generated method stub
		
	}
	//Method from com.cfm.productline.Variable class 
	public Float getAsFloat() {
		Object val = getValue();
		if( val == null )
			return null;
		
		if( val instanceof Float)
			return (Float) val;
		
		if( val instanceof String )
			return Float.parseFloat( (String) val );
		
		return 0f;
	}
	//Method from com.cfm.productline.Variable class 
	public Integer getAsInteger() {
		Object val = getValue();
		if( val == null )
			return null;
		
		if( val instanceof Integer)
			return (Integer) val;
		
		if( val instanceof String )
			return Integer.parseInt( (String) val );
		
		return 0;
	}

	//Method from com.cfm.productline.Variable class 
	public Boolean getAsBoolean() {
		Object val = getValue();
		if( val == null )
			return null;
		
		if( val instanceof Boolean )
			return (Boolean)val;
		
		if( val instanceof String ){
			//Check if it's 0
			if( "0".equals(val) )
				return false;
			
			//Check if it's "false"
			if( "false".equalsIgnoreCase( (String) val ) )
				return false;
		}
		return true;
	}
	
	public String toString()
	{
		Object val = getDisplayValue();
		if( val == null )
			return "";
		return val.toString();
	}
	
	public Map<String, Object> getVars() {
		return vars;
	}
	
	public void setVars(Map<String, Object> vars) {
		this.vars = vars;
	}


	public void clearModelingAttribute() {
		attribute = null;		
		object = null;
		setVariable(VAR_VALIDATIONGROUPDEPLIST, null);
		setVariable(VAR_VALIDATIONDIRECTRELLIST, null);
		setVariable(VAR_VALIDATIONMETAEDGELIST, null);
	}


	public void displayValue(String out) {
		setVariable(VAR_DISPLAYVALUE, out);
		
	}
	public void setObject(Object object) {
		//setVariable(VAR_DISOBJECT, object);
		this.object = object;
	}

	public Object getObject() {
		//return getVariable(VAR_DISOBJECT);
		return object;
	}


}
