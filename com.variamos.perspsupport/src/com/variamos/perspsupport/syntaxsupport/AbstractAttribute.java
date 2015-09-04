package com.variamos.perspsupport.syntaxsupport;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.variamos.hlcl.Domain;

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

	public void setDefaultGroup(int defaultGroup) {
		this.defaultGroup = defaultGroup;
	}

	public void setPropTabPosition(int propTabPosition) {
		this.propTabPosition = propTabPosition;
	}

	public void setElementDisplayPosition(int elementDisplayPosition) {
		this.elementDisplayPosition = elementDisplayPosition;
	}

	public void setElementDisplaySpacers(String elementDisplaySpacers) {
		this.elementDisplaySpacers = elementDisplaySpacers;
	}

	public void setPropTabEditionCondition(String propTabEditionCondition) {
		this.propTabEditionCondition = propTabEditionCondition;
	}

	public void setPropTabVisualCondition(String propTabVisualCondition) {
		this.propTabVisualCondition = propTabVisualCondition;
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
	/**
	 * defaultGroup for the attribute - for simulation multi-labeling
	 */
	private int defaultGroup;
	/**
	 * Position of the attribute in the property tab panel - -1 to hide
	 */
	private int propTabPosition;
	/**
	 * Position of the attribute in the element representation - -1 to hide
	 */
	private int elementDisplayPosition;
	/**
	 * Spacer for the attribute in the element representation (before and after
	 * separated by #-#
	 */
	private String elementDisplaySpacers;
	/**
	 * Condition to enable edition of the attribute in the property tab - Empty
	 * always edits
	 */
	private String propTabEditionCondition;
	/**
	 * Condition to enable visualization of the attribute in the property tab-
	 * Empty always displays
	 */
	private String propTabVisualCondition;
	/**
	 * Condition to enable visualization of the attribute in the graph Empty
	 * always displays
	 */
	private String elementDisplayCondition;
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
	VAR_HINT = "Hint",
	/**
	 * Name of element defaultGroup
	 */
	VAR_defaultGroup = "Default Group",
	/**
	 * Position of the attribute in the property tab panel - -1 to hide
	 */
	VAR_PROPTABPOSITION = "propTabPosition",
	/**
	 * Name of the position of the attribute in the property tab panel - -1 to
	 * hide
	 */
	VAR_PROPTABPOSITION_NAME = "Prop. Tab Position",
	/**
	 * Position of the attribute in the element representation - -1 to hide
	 */
	VAR_ELEMENTDISPLAYPOSITION = "elementDisplayPosition",
	/**
	 * Name of the position of the attribute in the element representation - -1
	 * to hide
	 */
	VAR_ELEMENTDISPLAYPOSITION_NAME = "Element Disp. Position",
	/**
	 * Spacer for the attribute in the element representation (before and after
	 * separated by #-#
	 */
	VAR_ELEMENTDISPLAYSPACERS = "elementDisplaySpacers",
	/**
	 * Name of the spacer for the attribute in the element representation
	 * (before and after separated by #-#
	 */
	VAR_ELEMENTDISPLAYSPACERS_NAME = "Element Disp. Spacers",
	/**
	 * Condition to enable edition of the attribute in the property tab - Empty
	 * always edits
	 */
	VAR_PROPTABEDITIONCOND = "propTabEditionCondition",
	/**
	 * Name of the Condition to enable edition of the attribute in the property
	 * tab - Empty always edits
	 */
	VAR_PROPTABEDITIONCOND_NAME = "Prop. Tab Edition Cond.",
	/**
	 * Condition to enable visualization of the attribute in the property tab-
	 * Empty always displays
	 */
	VAR_PROPTABVISUALCOND = "propTabVisualCondition",
	/**
	 * Name of the Condition to enable visualization of the attribute in the
	 * property tab- Empty always displays
	 */
	VAR_PROPTABVISUALCOND_NAME = "Prop. Tab Visual Cond.",
	/**
	 * Condition to enable visualization of the attribute in the property tab-
	 * Empty always displays
	 */
	VAR_ELEMENTDISPLAYCONDITION = "elementDisplayCondition",
	/**
	 * Name of the Condition to enable visualization of the attribute in the
	 * property tab- Empty always displays
	 */
	VAR_ELEMENTDISPLAYCONDITION_NAME = "Graph Visual Cond.";

	public String getElementDisplayCondition() {
		return elementDisplayCondition;
	}

	public void setElementDisplayCondition(String elementDisplayCondition) {
		this.elementDisplayCondition = elementDisplayCondition;
	}

	/**
	 * Dynamic storage of attributes
	 */
	protected Map<String, EditableElementAttribute> dynamicAttributeComponentsMap = new HashMap<>();

	public Map<String, EditableElementAttribute> getDynamicAttributeComponentsMap() {
		return dynamicAttributeComponentsMap;
	}

	public void setDynamicAttributeComponentsMap(
			Map<String, EditableElementAttribute> dynamicAttributesMap) {
		this.dynamicAttributeComponentsMap = dynamicAttributesMap;
	}

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
	 * @param defaultGroup
	 *            Default defaultGroup for the attribute
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		this(name, type, affectProperties, displayName, null, null,
				defaultValue, null, null, defaultGroup, propTabPosition,
				propTabEditionCondition, propTabVisualCondition,
				elementDisplayPosition, elementDisplaySpacers,
				elementDisplayCondition);
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
	 * @param defaultGroup
	 *            Default defaultGroup for the attribute
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			String hint, int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		this(name, type, affectProperties, displayName, null, null,
				defaultValue, null, hint, defaultGroup, propTabPosition,
				propTabEditionCondition, propTabVisualCondition,
				elementDisplayPosition, elementDisplaySpacers,
				elementDisplayCondition);
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
	 * @param defaultGroup
	 *            Default defaultGroup for the attribute
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue, int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		this(name, type, affectProperties, displayName, enumType, null,
				defaultValue, null, null, defaultGroup, propTabPosition,
				propTabEditionCondition, propTabVisualCondition,
				elementDisplayPosition, elementDisplaySpacers,
				elementDisplayCondition);
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
	 * @param defaultGroup
	 *            Default defaultGroup for the attribute
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			String metaConceptInstanceType, Object defaultValue,
			int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		this(name, type, affectProperties, displayName, enumType,
				metaConceptInstanceType, defaultValue, null, null,
				defaultGroup, propTabPosition, propTabEditionCondition,
				propTabVisualCondition, elementDisplayPosition,
				elementDisplaySpacers, elementDisplayCondition);
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
	 * @param defaultGroup
	 *            Default defaultGroup for the attribute
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue, String hint, int defaultGroup,
			int propTabPosition, String propTabEditionCondition,
			String propTabVisualCondition, int elementDisplayPosition,
			String elementDisplaySpacers, String elementDisplayCondition) {
		this(name, type, affectProperties, displayName, enumType, null,
				defaultValue, null, hint, defaultGroup, propTabPosition,
				propTabEditionCondition, propTabVisualCondition,
				elementDisplayPosition, elementDisplaySpacers,
				elementDisplayCondition);
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
	 * @param defaultGroup
	 *            Default defaultGroup for the attribute
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			String metaConceptInstanceType, Object defaultValue, String hint,
			int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		this(name, type, affectProperties, displayName, enumType,
				metaConceptInstanceType, defaultValue, null, hint,
				defaultGroup, propTabPosition, propTabEditionCondition,
				propTabVisualCondition, elementDisplayPosition,
				elementDisplaySpacers, elementDisplayCondition);
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
	 * @param defaultGroup
	 *            Default defaultGroup for the attribute
	 */

	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			Domain domain, int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		this(name, type, affectProperties, displayName, null, null,
				defaultValue, domain, null, defaultGroup, propTabPosition,
				propTabEditionCondition, propTabVisualCondition,
				elementDisplayPosition, elementDisplaySpacers,
				elementDisplayCondition);
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
	 * @param defaultGroup
	 *            Default defaultGroup for the attribute
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			Domain domain, String hint, int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		this(name, type, affectProperties, displayName, null, null,
				defaultValue, domain, hint, defaultGroup, propTabPosition,
				propTabEditionCondition, propTabVisualCondition,
				elementDisplayPosition, elementDisplaySpacers,
				elementDisplayCondition);
	}

	/**
	 * constructor, set all local attributes without visual control
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
	 * @param defaultGroup
	 *            Default defaultGroup for the attribute
	 */
	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			String metaConceptInstanceType, Object defaultValue, Domain domain,
			String hint, int defaultGroup) {
		this(name, type, affectProperties, displayName, enumType,
				metaConceptInstanceType, defaultValue, domain, hint,
				defaultGroup, -1, "", "", -1, "", "");
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
	 * @param defaultGroup
	 *            Default defaultGroup for the attribute
	 */

	public AbstractAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			String metaConceptInstanceType, Object defaultValue, Domain domain,
			String hint, int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
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
		this.defaultGroup = defaultGroup;
		this.propTabPosition = propTabPosition;
		this.propTabEditionCondition = propTabEditionCondition;
		this.propTabVisualCondition = propTabVisualCondition;
		this.elementDisplayPosition = elementDisplayPosition;
		this.elementDisplaySpacers = elementDisplaySpacers;
		this.elementDisplayCondition = elementDisplayCondition;

		if (type.equals("Class") || type.equals("MClass")
				|| type.equals("Enum") || type.equals("MEnum"))
			type = "String";
		dynamicAttributeComponentsMap.put(VAR_NAME, new AttributeElement(
				VAR_NAME, "String", VAR_NAME, name));
		dynamicAttributeComponentsMap.put(VAR_TYPE, new AttributeElement(
				VAR_TYPE, "String", VAR_TYPE, type));
		dynamicAttributeComponentsMap.put(VAR_AFFECTPROPS,
				new AttributeElement(VAR_AFFECTPROPS, "Boolean",
						VAR_AFFECTPROPSNAME, affectProperties));
		dynamicAttributeComponentsMap.put(VAR_DISPLAYNAME,
				new AttributeElement(VAR_DISPLAYNAME, "String",
						VAR_DISPLAYNAMENAME, displayName));
		dynamicAttributeComponentsMap.put(VAR_CLASSCANONICALNAME,
				new AttributeElement(VAR_CLASSCANONICALNAME, "String",
						VAR_CLASSCANONICALNAMENAME, enumType));
		dynamicAttributeComponentsMap.put(VAR_METACONCEPTINSTTYPE,
				new AttributeElement(VAR_METACONCEPTINSTTYPE, "String",
						VAR_METACONCEPTINSTTYPENAME, metaConceptInstanceType));
		dynamicAttributeComponentsMap.put(VAR_DEFAULTVALUE,
				new AttributeElement(VAR_DEFAULTVALUE, type,
						VAR_DEFAULTVALUENAME, defaultValue));
		dynamicAttributeComponentsMap.put(VAR_DOMAIN, new AttributeElement(
				VAR_DOMAIN, "String", VAR_DOMAIN, domain)); // TODO Change
															// String to Domain
		dynamicAttributeComponentsMap.put(VAR_HINT, new AttributeElement(
				VAR_HINT, "String", VAR_HINT, hint));
		dynamicAttributeComponentsMap.put(VAR_defaultGroup,
				new AttributeElement(VAR_defaultGroup, "Integer",
						VAR_defaultGroup, defaultGroup));
		dynamicAttributeComponentsMap.put(VAR_PROPTABPOSITION,
				new AttributeElement(VAR_PROPTABPOSITION, "Integer",
						VAR_PROPTABPOSITION_NAME, propTabPosition));
		dynamicAttributeComponentsMap.put(VAR_ELEMENTDISPLAYPOSITION,
				new AttributeElement(VAR_ELEMENTDISPLAYPOSITION, "Integer",
						VAR_ELEMENTDISPLAYPOSITION_NAME, elementDisplayPosition));
		dynamicAttributeComponentsMap.put(VAR_ELEMENTDISPLAYSPACERS,
				new AttributeElement(VAR_ELEMENTDISPLAYSPACERS, "String",
						VAR_ELEMENTDISPLAYSPACERS_NAME, elementDisplaySpacers));
		dynamicAttributeComponentsMap.put(VAR_PROPTABEDITIONCOND,
				new AttributeElement(VAR_PROPTABEDITIONCOND, "String",
						VAR_PROPTABEDITIONCOND_NAME, propTabEditionCondition));
		dynamicAttributeComponentsMap.put(VAR_PROPTABVISUALCOND,
				new AttributeElement(VAR_PROPTABVISUALCOND, "String",
						VAR_PROPTABVISUALCOND_NAME, propTabVisualCondition));
		dynamicAttributeComponentsMap.put(VAR_ELEMENTDISPLAYCONDITION,
				new AttributeElement(VAR_ELEMENTDISPLAYCONDITION, "String",
						VAR_ELEMENTDISPLAYCONDITION_NAME, elementDisplayCondition));
	}

	public int getPropTabPosition() {
		return propTabPosition;
	}

	public int getElementDisplayPosition() {
		return elementDisplayPosition;
	}

	public String getElementDisplaySpacers() {
		return elementDisplaySpacers;
	}

	public String getPropTabEditionCondition() {
		return propTabEditionCondition;
	}

	public String getPropTabVisualCondition() {
		return propTabVisualCondition;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		dynamicAttributeComponentsMap.put(VAR_DISPLAYNAME,
				new AttributeElement(VAR_DISPLAYNAME, "String",
						VAR_DISPLAYNAMENAME, displayName));
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

	public int getDefaultGroup() {
		return defaultGroup;
	}

	public boolean isAffectProperties() {
		return affectProperties;
	}

	public void setAffectProperties(boolean affectProperties) {
		this.affectProperties = affectProperties;
		dynamicAttributeComponentsMap.put(VAR_AFFECTPROPS,
				new AttributeElement(VAR_AFFECTPROPS, "Boolean",
						VAR_AFFECTPROPSNAME, affectProperties));

	}

	public void setName(String name) {
		this.name = name;
		dynamicAttributeComponentsMap.put(VAR_NAME, new AttributeElement(
				VAR_NAME, "String", VAR_NAME, name));

	}
}
