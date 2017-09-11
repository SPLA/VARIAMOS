package com.variamos.dynsup.types;

import java.io.Serializable;

import com.variamos.hlcl.model.domains.IntDomain;

public class Variable implements Serializable{
	private static final long serialVersionUID = 118894641885363593L;
	
	protected String name;
	protected Object value;
	protected String type;
	protected IntDomain domain;
	
	public Variable(String name, Object value, String type) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
	}

	//Required by the serialization codec but its use is discouraged
	public Variable(){
		name = "";
		type = StringType.IDENTIFIER;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		//HACK careful here ... there is not type checking !
		this.value = value;
	}
	
	public Boolean getAsBoolean(){
		if( value == null )
			return null;
		
		if( value instanceof Boolean )
			return (Boolean)value;
		
		if( value instanceof String ){
			//Check if it's 0
			if( "0".equals(value) )
				return false;
			
			//Check if it's "false"
			if( "false".equalsIgnoreCase( (String)value) )
				return false;
		}
		return true;
	}
	
	public Integer getAsInteger(){
		if( value == null )
			return null;
		
		if( value instanceof Integer)
			return (Integer) value;
		
		if( value instanceof String )
			return Integer.parseInt( (String) value );
		
		return 0;
	}
	
	public Float getAsFloat(){
		if( value == null )
			return null;
		
		if( value instanceof Float)
			return (Float) value;
		
		if( value instanceof String )
			return Float.parseFloat( (String) value );
		
		return 0f;
	}
	
	@Override
	public String toString() {
		if( value == null )
			return "";
		return value.toString();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public IntDomain getDomain() {
		return domain;
	}

	public void setDomain(IntDomain domain) {
		this.domain = domain;
	}
	
}
