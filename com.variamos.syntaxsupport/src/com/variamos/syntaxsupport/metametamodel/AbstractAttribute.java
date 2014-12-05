package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class AbstractAttribute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8137579010457747236L;
	private String name;
	private String type;
	private String hint;
	private String enumType;
	private String object;
	private Object defaultValue;
	private Domain domain;

	public AbstractAttribute() {

	}

	public AbstractAttribute(String name, String type, Object defaultValue) {
		this(name, type, null, null, defaultValue, null, null);
	}

	public AbstractAttribute(String name, String type, Object defaultValue,
			String hint) {
		this(name, type, null, null, defaultValue, null, hint);
	}

	public AbstractAttribute(String name, String type, String enumType,
			Object defaultValue) {
		this(name, type, enumType, null, defaultValue, null, null);
	}
	
	public AbstractAttribute(String name, String type, String enumType, String object,
			Object defaultValue) {
		this(name, type, enumType, object, defaultValue, null, null);
	}

	public AbstractAttribute(String name, String type, String enumType,
			Object defaultValue, String hint) {
		this(name, type, enumType, null, defaultValue, null, hint);
	}
	
	public AbstractAttribute(String name, String type, String enumType, String object,
			Object defaultValue, String hint) {
		this(name, type, enumType, object, defaultValue, null, hint);
	}

	public AbstractAttribute(String name, String type, Object defaultValue,
			Domain domain) {
		this(name, type, null, null, defaultValue, domain, null);
	}

	public AbstractAttribute(String name, String type, Object defaultValue,
			Domain domain, String hint) {
		this(name, type, null, null, defaultValue, domain, hint);
	}

	public AbstractAttribute(String name, String type, String enumType, String object,
			Object defaultValue, Domain domain, String hint) {
		super();
		this.name = name;
		this.type = type;
		this.hint = hint;
		this.enumType = enumType;
		this.object = object;
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

	public String getObject() {
		return object;
	}

	public Domain getDomain() {
		return domain;
	}

	public String getEnumType() {
		return enumType;
	}

	public String getHint() {
		return hint;
	}
}
