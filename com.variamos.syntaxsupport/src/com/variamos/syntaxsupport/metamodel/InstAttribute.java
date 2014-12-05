package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;
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
								VAR_DISPLAYVALUE = "DispValue";
	protected Map<String, Object> vars = new HashMap<>();
	private Object object;
	
	/*private String identifier;
	private Object value;
	*/
	private AbstractAttribute attribute;
	
	public InstAttribute()
	{
		
	}
	
	
	public InstAttribute(String identifier, AbstractAttribute metaAttribute,
			Object value) {
		super();
		this.attribute = metaAttribute;
		vars.put(VAR_IDENTIFIER, identifier);
		vars.put(VAR_ATTRIBUTE, metaAttribute.getName());
		vars.put(VAR_VALUE, value);
		vars.put(VAR_DISPLAYVALUE, null);
		//vars.put(VAR_DISOBJECT, null);
//		this.identifier = identifier;
		
//		this.value = value;
	}
	
	public InstAttribute(String identifier, AbstractAttribute metaAttribute,
			Object value, Object object) {
		super();
		this.attribute = metaAttribute;
		vars.put(VAR_IDENTIFIER, identifier);
		vars.put(VAR_ATTRIBUTE, metaAttribute.getName());
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

	public void setAttribute(AbstractAttribute metaAttribute) {
		this.attribute = metaAttribute;
		setVariable(VAR_ATTRIBUTE, metaAttribute.getName());
	}

	public String getIdentifier() {
		return (String)getVariable(VAR_IDENTIFIER);
		//return identifier;
	}
	public AbstractAttribute getAttribute() {
		//return (MetaAttribute)getVariable(VAR_METAATTRIBUTE);
		return attribute;
	}
	
	public String getAttributeName() {
		return (String)getVariable(VAR_ATTRIBUTE);
		//return metaAttribute;
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
	
	public String getMetaAttributeType(){
		//return ((MetaAttribute)getVariable(VAR_METAATTRIBUTE)).getType();
		return attribute.getType();
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


	public void clearMetaAttribute() {
		attribute = null;		
		object = null;
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
