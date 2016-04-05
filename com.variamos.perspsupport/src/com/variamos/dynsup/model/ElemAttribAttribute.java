package com.variamos.dynsup.model;

import java.io.Serializable;

import com.variamos.dynsup.interfaces.IntElemAttribute;

/**
 * A class to represented the component of each AbstractAttribute on VariaMos.
 * Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-29
 * @see com.variamos.syntaxsupport.metamodelsupport.AbtractAttribute
 */
public class ElemAttribAttribute implements Serializable, IntElemAttribute {
	/**
	 * 
	 */
	private static final long serialVersionUID = -609791468975659L;
	/**
	 * Element identifier
	 */
	private String identifier;

	private String type;

	private String name;
	/**
	 * Object associated to an existing metaElement, syntaxElement or
	 * InstElement - from JList or JComboBox
	 */
	private Object value;

	private Object group;

	public ElemAttribAttribute() {

	}

	public ElemAttribAttribute(String identifier, String type, String name,
			Object valueObject) {
		this.identifier = identifier;
		this.type = type;
		this.name = name;
		this.value = valueObject;
	}

	public ElemAttribAttribute(String identifier, String type, String name) {
		this.identifier = identifier;
		this.type = type;
		this.name = name;
		this.value = null;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Method from com.cfm.productline.Variable class
	public Float getAsFloat() {
		Object val = value;
		if (val == null)
			return null;

		if (val instanceof Float)
			return (Float) val;

		if (val instanceof String)
			return Float.parseFloat((String) val);

		return 0f;
	}

	// Method from com.cfm.productline.Variable class
	public Integer getAsInteger() {
		Object val = value;
		if (val == null)
			return null;

		if (val instanceof Integer)
			return (Integer) val;

		if (val instanceof String)
			return Integer.parseInt((String) val);

		return 0;
	}

	// Method from com.cfm.productline.Variable class
	public Boolean getAsBoolean() {
		Object val = value;
		if (val == null)
			return null;

		if (val instanceof Boolean)
			return (Boolean) val;

		if (val instanceof String) {
			// Check if it's 0
			if ("0".equals(val))
				return false;

			// Check if it's "false"
			if ("false".equalsIgnoreCase((String) val))
				return false;
		}
		return true;
	}

	public String toString() {
		Object val = value;
		if (val == null)
			return "";
		return val.toString();
	}

	public void setGroup(Object valueObject) {
		this.group = valueObject;
	}

	public Object getGroup() {
		return group;
	}

	public void setValue(Object valueObject) {
		this.value = valueObject;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof ElemAttribAttribute)
			return getIdentifier().equals(
					((ElemAttribAttribute) o).getIdentifier());
		else
			return false;
	}

	@Override
	public int hashCode() {
		return getIdentifier().hashCode();
	}

	@Override
	public boolean isAffectProperties() {
		return false;
	}

	@Override
	public String getDisplayName() {
		return name;
	}
}
