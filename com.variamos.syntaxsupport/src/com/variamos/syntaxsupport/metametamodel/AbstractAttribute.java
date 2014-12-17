package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;

/**
 * A class to represent semantic and syntax concepts attributes. The
 * attributes are dynamically loaded on modeling perspective. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24
 */
public class AbstractAttribute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8137579010457747236L;
	/**
	 * Unique name of the attribute within the concept
	 */
	private String name;
	/**
	 * Attribute type (String, Integer, Boolean, Set, Class, MClass,
	 * Enumeration, MEnumeration)
	 */
	private String type;
	/**
	 * Identify if this attribute affects other attributes in the properties
	 * view (to update view after change)
	 */
	private boolean affectProperties;
	/**
	 * Name to display on properties JLabel
	 */
	private String displayName;
	/**
	 * Complete class in referenced attributes (Class, MClass, Enumeration,
	 * MEnumeration)
	 */
	private String classCanonicalName;
	/**
	 * Identifier of the type of instance concept. Examples: OPER
	 */
	private String metaConceptInstanceType;
	/**
	 * Default value for the attribute
	 */
	private Object defaultValue;
	/**
	 * Domain of the attribute (not tested)
	 */
	private Domain domain;
	/**
	 * Hint to display on the property tab (not currently implemented)
	 */
	// TODO use on properties
	private String hint;

	/**
	 * 
	 */
	public AbstractAttribute() {

	}

	/**
	 * set not received local attributes with null
	 * 
	 * @param name
	 *            local identifier of the attribute
	 * @param type
	 *            Attribute type (String, Integer, Boolean, Set, Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param affectProperties
	 *            Name to display on properties JLabel
	 * @param displayName
	 *            Name to display on properties JLabel
	 * @param metaConceptInstanceType
	 *            Identifier of the type of instance concept. Examples: OPER
	 * @param defaultValue
	 *            Default value for the attribute
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue) {
		this(name, type, affectProperties, displayName, null, null,
				defaultValue, null, null);
	}

	/**
	 * set not received local attributes with null
	 * 
	 * @param name
	 *            local identifier of the attribute
	 * @param type
	 *            Attribute type (String, Integer, Boolean, Set, Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param affectProperties
	 *            Name to display on properties JLabel
	 * @param displayName
	 *            Name to display on properties JLabel
	 * @param defaultValue
	 *            Default value for the attribute
	 * @param hint
	 *            Hint to display on the property tab (not currently
	 *            implemented)
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			String hint) {
		this(name, type, affectProperties, displayName, null, null,
				defaultValue, null, hint);
	}

	/**
	 * set not received local attributes with null
	 * 
	 * @param name
	 *            local identifier of the attribute
	 * @param type
	 *            Attribute type (String, Integer, Boolean, Set, Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param affectProperties
	 *            Name to display on properties JLabel
	 * @param displayName
	 *            Name to display on properties JLabel
	 * @param enumType
	 *            Complete class in referenced attributes (Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param defaultValue
	 *            Default value for the attribute
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue) {
		this(name, type, affectProperties, displayName, enumType, null,
				defaultValue, null, null);
	}

	/**
	 * set not received local attributes with null
	 * 
	 * @param name
	 *            local identifier of the attribute
	 * @param type
	 *            Attribute type (String, Integer, Boolean, Set, Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param affectProperties
	 *            Name to display on properties JLabel
	 * @param displayName
	 *            Name to display on properties JLabel
	 * @param enumType
	 *            Complete class in referenced attributes (Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param metaConceptInstanceType
	 *            Identifier of the type of instance concept. Examples: OPER
	 * @param defaultValue
	 *            Default value for the attribute
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			String metaConceptInstanceType, Object defaultValue) {
		this(name, type, affectProperties, displayName, enumType, metaConceptInstanceType,
				defaultValue, null, null);
	}

	/**
	 * set not received local attributes with null
	 * 
	 * @param name
	 *            local identifier of the attribute
	 * @param type
	 *            Attribute type (String, Integer, Boolean, Set, Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param affectProperties
	 *            Name to display on properties JLabel
	 * @param displayName
	 *            Name to display on properties JLabel
	 * @param enumType
	 *            Complete class in referenced attributes (Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param metaConceptInstanceType
	 *            Identifier of the type of instance concept. Examples: OPER
	 * @param defaultValue
	 *            Default value for the attribute
	 * @param hint
	 *            Hint to display on the property tab (not currently
	 *            implemented)
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue, String hint) {
		this(name, type, affectProperties, displayName, enumType, null,
				defaultValue, null, hint);
	}
	/**
	 * set not received local attributes with null
	 * 
	 * @param name
	 *            local identifier of the attribute
	 * @param type
	 *            Attribute type (String, Integer, Boolean, Set, Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param affectProperties
	 *            Name to display on properties JLabel
	 * @param displayName
	 *            Name to display on properties JLabel
	 * @param enumType
	 *            Complete class in referenced attributes (Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param metaConceptInstanceType
	 *            Identifier of the type of instance concept. Examples: OPER
	 * @param defaultValue
	 *            Default value for the attribute
	 * @param hint
	 *            Hint to display on the property tab (not currently
	 *            implemented)
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			String metaConceptInstanceType, Object defaultValue, String hint) {
		this(name, type, affectProperties, displayName, enumType, metaConceptInstanceType,
				defaultValue, null, hint);
	}
	
	/**
	 * set not received local attributes with null
	 * 
	 * @param name
	 *            local identifier of the attribute
	 * @param type
	 *            Attribute type (String, Integer, Boolean, Set, Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param affectProperties
	 *            Name to display on properties JLabel
	 * @param displayName
	 *            Name to display on properties JLabel
	 * @param defaultValue
	 *            Default value for the attribute
	 * @param domain
	 *            Domain of the attribute (not tested)
	 */

	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			Domain domain) {
		this(name, type, affectProperties, displayName, null, null,
				defaultValue, domain, null);
	}

	/**
	 * set not received local attributes with null
	 * 
	 * @param name
	 *            local identifier of the attribute
	 * @param type
	 *            Attribute type (String, Integer, Boolean, Set, Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param affectProperties
	 *            Name to display on properties JLabel
	 * @param displayName
	 *            Name to display on properties JLabel
	 * @param defaultValue
	 *            Default value for the attribute
	 * @param domain
	 *            Domain of the attribute (not tested)
	 * @param hint
	 *            Hint to display on the property tab (not currently
	 *            implemented)
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			Domain domain, String hint) {
		this(name, type, affectProperties, displayName, null, null,
				defaultValue, domain, hint);
	}

	/**
	 * Principal constructor, set all local attributes
	 * 
	 * @param name
	 *            local identifier of the attribute
	 * @param type
	 *            Attribute type (String, Integer, Boolean, Set, Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param affectProperties
	 *            Name to display on properties JLabel
	 * @param displayName
	 *            Name to display on properties JLabel
	 * @param enumType
	 *            Complete class in referenced attributes (Class, MClass,
	 *            Enumeration, MEnumeration)
	 * @param metaConceptInstanceType
	 *            Identifier of the type of instance concept. Examples: OPER
	 * @param defaultValue
	 *            Default value for the attribute
	 * @param domain
	 *            Domain of the attribute (not tested)
	 * @param hint
	 *            Hint to display on the property tab (not currently
	 *            implemented)
	 */

	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			String metaConceptInstanceType, Object defaultValue, Domain domain, String hint) {
		super();
		this.name = name;
		this.type = type;
		this.affectProperties = affectProperties;
		this.displayName = displayName;
		this.classCanonicalName = enumType;
		this.metaConceptInstanceType = metaConceptInstanceType;
		this.defaultValue = defaultValue;
		this.domain = domain;
		this.hint = hint;

	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public String getMetaConceptInstanceType() {
		return metaConceptInstanceType;
	}

	public Domain getDomain() {
		return domain;
	}

	public String getClassCanonicalName() {
		return classCanonicalName;
	}

	public String getHint() {
		return hint;
	}

	public boolean isAffectProperties() {
		return affectProperties;
	}

	public void setAffectProperties(boolean affectProperties) {
		this.affectProperties = affectProperties;
	}
}
