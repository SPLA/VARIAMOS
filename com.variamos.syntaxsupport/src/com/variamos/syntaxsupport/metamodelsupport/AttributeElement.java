package com.variamos.syntaxsupport.metamodelsupport;

import java.io.Serializable;

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
public class AttributeElement implements Serializable, EditableElementAttribute {
	/**
	 * 
	 */
	private static final long serialVersionUID = -609791468975659L;
	/**
	 * Element identifier
	 */
	private String identifier;

	private String type;

	public String getAttributeType() {
		return type;
	}

	public String getName() {
		return name;
	}

	private String name;
	/**
	 * Object associated to an existing metaElement, syntaxElement or
	 * InstElement - from JList or JComboBox
	 */
	private Object valueObject;

	public AttributeElement() {

	}

	public AttributeElement(String identifier, String type, String name,
			Object valueObject) {
		this.identifier = identifier;
		this.type = type;
		this.name = name;
		this.valueObject = valueObject;
	}

	public AttributeElement(String identifier, String type, String name) {
		this.identifier = identifier;
		this.type = type;
		this.name = name;
		this.valueObject = null;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}


	public void setType(String type) {
		this.type = type;
	}

	// Method from com.cfm.productline.Variable class
	public Float getAsFloat() {
		Object val = valueObject;
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
		Object val = valueObject;
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
		Object val = valueObject;
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
		Object val = valueObject;
		if (val == null)
			return "";
		return val.toString();
	}

	public void setValue(Object valueObject) {
		this.valueObject = valueObject;
	}

	public Object getValue() {
		return valueObject;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof AttributeElement)
			return getIdentifier().equals(
					((AttributeElement) o).getIdentifier());
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
