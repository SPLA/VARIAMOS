package com.variamos.syntaxsupport.metamodelsupport;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A class to represent semantic and syntax concepts attributes. The attributes
 * are dynamically loaded on modeling perspective. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24
 */
public class AbstractAttribute implements Serializable {
	public void setType(String type) {
		this.type = type;
	}

	public void setClassCanonicalName(String classCanonicalName) {
		this.classCanonicalName = classCanonicalName;
	}

	public void setMetaConceptInstanceType(String metaConceptInstanceType) {
		this.metaConceptInstanceType = metaConceptInstanceType;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

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

	public static final String
	/**
	 * Name of element name
	 */
	VAR_NAME = "Name",
	/**
	 * Name of element type
	 */
	VAR_TYPE = "Type",
	/**
	 * Name of the element affect properties
	 */
	VAR_AFFECTPROPS = "AffectProps",
	/**
	 * DyplaynName of the element affect properties
	 */
	VAR_AFFECTPROPSNAME = "Affects other elements",
	/**
	 * Name of element display name
	 */
	VAR_DISPLAYNAME = "DispName",
	/**
	 * Display Name of element display name
	 */
	VAR_DISPLAYNAMENAME = "Display Name",
	/**
	 * Name of the element associated class
	 */
	VAR_CLASSCANONICALNAME = "ClassCanName",
	/**
	 * Display name of the element associated class
	 */
	VAR_CLASSCANONICALNAMENAME = "Enumeration Type",
	/**
	 * Name of element associated meta instance type
	 */
	VAR_METACONCEPTINSTTYPE = "MetaCInstType",
	/**
	 * Display name of element associated meta instance type
	 */
	VAR_METACONCEPTINSTTYPENAME = "Instance Type",
	/**
	 * Name of the element default value
	 */
	VAR_DEFAULTVALUE = "DefaultValue",
	/**
	 * Display Name of the element default value
	 */
	VAR_DEFAULTVALUENAME = "Default Value",
	/**
	 * Name of element domain
	 */
	VAR_DOMAIN = "Domain",
	/**
	 * Name of element hint
	 */
	VAR_HINT = "Hint";
	public Map<String, EditableElementAttribute> getEditableElementAttributes() {
		return vars;
	}

	/**
	 * Dynamic storage of attributes
	 */
	protected Map<String, EditableElementAttribute> vars = new HashMap<>();

	/**
	 * 
	 */
	public AbstractAttribute() {

	}

	/**
	 * set local attributes none received with null
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
	 * set local attributes not received with null
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
	 * set local attributes not received with null
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
	 * set local attributes not received with null
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
		this(name, type, affectProperties, displayName, enumType,
				metaConceptInstanceType, defaultValue, null, null);
	}

	/**
	 * set local attributes not received with null
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
	 * set local attributes not received with null
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
		this(name, type, affectProperties, displayName, enumType,
				metaConceptInstanceType, defaultValue, null, hint);
	}

	/**
	 * set local attributes not received with null
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
	 * set local attributes not received with null
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
			String metaConceptInstanceType, Object defaultValue, Domain domain,
			String hint) {
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

		vars.put(VAR_NAME, new AttributeElement(VAR_NAME, "String", VAR_NAME,
				name));
		vars.put(VAR_TYPE, new AttributeElement(VAR_TYPE, "String", VAR_TYPE,
				type));
		vars.put(VAR_AFFECTPROPS, new AttributeElement(VAR_AFFECTPROPS,
				"Boolean", VAR_AFFECTPROPSNAME, affectProperties));
		vars.put(VAR_DISPLAYNAME, new AttributeElement(VAR_DISPLAYNAME,
				"String", VAR_DISPLAYNAMENAME, displayName));
		vars.put(VAR_CLASSCANONICALNAME, new AttributeElement(
				VAR_CLASSCANONICALNAME, "String", VAR_CLASSCANONICALNAMENAME,
				enumType));
		vars.put(VAR_METACONCEPTINSTTYPE, new AttributeElement(
				VAR_METACONCEPTINSTTYPE, "String", VAR_METACONCEPTINSTTYPENAME,
				metaConceptInstanceType));
		vars.put(VAR_DEFAULTVALUE, new AttributeElement(VAR_DEFAULTVALUE,
				type, VAR_DEFAULTVALUENAME, defaultValue));
		vars.put(VAR_DOMAIN, new AttributeElement(VAR_DOMAIN, "String",
				VAR_DOMAIN, domain)); //TODO Change to Domain
		vars.put(VAR_HINT, new AttributeElement(VAR_HINT, "String", VAR_HINT,
				hint));
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		vars.put(VAR_DISPLAYNAME, new AttributeElement(VAR_DISPLAYNAME,
				"String", VAR_DISPLAYNAMENAME, displayName));

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
		vars.put(VAR_AFFECTPROPS, new AttributeElement(VAR_AFFECTPROPS,
				"Boolean", VAR_AFFECTPROPSNAME, affectProperties));

	}

	public void setName(String name) {
	this.name = name;	
	vars.put(VAR_NAME, new AttributeElement(VAR_NAME, "String", VAR_NAME,
			name));

	}
}
