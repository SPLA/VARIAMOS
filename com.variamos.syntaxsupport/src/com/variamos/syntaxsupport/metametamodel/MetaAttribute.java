package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class MetaAttribute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8137579010457747236L;
	private String name;
	private String type;
	private Object defaultValue;
	private Domain domain;
	
	public MetaAttribute()
	{
		
	}
	
	public MetaAttribute(String name, String type, Object defaultValue) {
		this( name, type, defaultValue, null);
	}
	public MetaAttribute(String name, String type,Object defaultValue, Domain domain) {
		super();
		this.name = name;
		this.type = type;
		this.defaultValue = defaultValue;
		this.domain = domain;
	}
	public Object getDefaultValue() {
		return defaultValue;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public Domain getDomain() {
		return domain;
	}

}
