package com.variamos.syntaxsupport.metamodel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.variamos.syntaxsupport.metametamodel.MetaAttribute;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;

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
								VAR_METAATTRIBUTE = "MetaAttributeName",
								VAR_VALUE = "Value";
	protected Map<String, Object> vars = new HashMap<>();
	
	/*private String identifier;
	private Object value;
	*/
	private MetaAttribute metaAttribute;
	
	public InstAttribute()
	{
		
	}
	
	
	public InstAttribute(String identifier, MetaAttribute metaAttribute,
			Object value) {
		super();
		vars.put(VAR_IDENTIFIER, identifier);
		vars.put(VAR_METAATTRIBUTE, metaAttribute.getName());
		vars.put(VAR_VALUE, value);
//		this.identifier = identifier;
		this.metaAttribute = metaAttribute;
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

	public void setMetaAttribute(MetaAttribute metaAttribute) {
		this.metaAttribute = metaAttribute;
		setVariable(VAR_METAATTRIBUTE, metaAttribute.getName());
	}

	public String getIdentifier() {
		return (String)getVariable(VAR_IDENTIFIER);
		//return identifier;
	}
	public MetaAttribute getMetaAttribute() {
		//return (MetaAttribute)getVariable(VAR_METAATTRIBUTE);
		return metaAttribute;
	}
	
	public String getMetaAttributeName() {
		return (String)getVariable(VAR_METAATTRIBUTE);
		//return metaAttribute;
	}
	
	public Object getValue() {
		return getVariable(VAR_VALUE);
		//return value;
	}
	public void setValue(Object value) {
		setVariable(VAR_VALUE, value);
		//this.value = value;
	}
	
	public String getMetaAttributeType(){
		//return ((MetaAttribute)getVariable(VAR_METAATTRIBUTE)).getType();
		return metaAttribute.getType();
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
		Object val = getValue();
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
		metaAttribute = null;		
	}
}
