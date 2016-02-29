package com.variamos.perspsupport.syntaxsupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.semanticinterface.IntSemanticElement;
import com.variamos.semantic.types.AttributeType;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public abstract class MetaElement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5336725046708613241L;
	public static final String
	/**
	 * 
	 */
	VAR_AUTOIDENTIFIER = "identifier",
	/**
			 * 
			 */
	VAR_USERIDENTIFIER = "userIdentifier",
	/**
			 * 
			 */
	VAR_DESCRIPTION = "Description";
	/**
	 * 
	 */
	private String autoIdentifier;
	/**
	 * 
	 */
	private String userIdentifier;
	/**
	 * 
	 */
	private boolean visible;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String style;
	/**
	 * 
	 */
	private String description;
	/**
	 * 
	 */
	private int width;
	/**
	 * 
	 */
	private int height;
	/**
	 * 
	 */
	private String image;
	/**
	 * 
	 */
	private int borderStroke;
	/**
	 * 
	 */
	private Map<String, AbstractAttribute> modelingAttributes;
	/**
	 * 
	 */
	private List<String> propVisibleAttributes;
	/**
	 * 
	 */
	private List<String> propEditableAttributes;
	/**
	 * 
	 */
	private List<String> panelVisibleAttributes;
	/**
	 * 
	 */
	private List<String> panelSpacersAttributes;

	private InstElement instSemanticElement;

	private String instSemanticElementId;

	/**
	 * 
	 */
	public MetaElement() {

		this("", true, "", "", "", 100, 40, "", 1, null,
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new HashMap<String, AbstractAttribute>());
		createSyntaxAttributes();
	}

	public MetaElement(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, InstElement instSemanticElement)

	{
		this(identifier, visible, name, style, description, width, height,
				image, borderStroke, instSemanticElement,
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new HashMap<String, AbstractAttribute>());
	}

	public MetaElement(String autoIdentifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, InstElement instSemanticElement,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> modelingAttributes)

	{
		this.autoIdentifier = autoIdentifier;
		this.userIdentifier = autoIdentifier;
		this.visible = visible;
		this.name = name;
		this.style = style;
		this.description = description; // TODO include in parameters
		this.width = width;
		this.height = height;
		this.image = image;
		this.borderStroke = borderStroke;
		this.instSemanticElement = instSemanticElement;
		if (instSemanticElement != null)
			this.instSemanticElementId = instSemanticElement.getIdentifier();
		this.propVisibleAttributes = disPropVisibleAttributes;
		this.propEditableAttributes = disPropEditableAttributes;
		this.panelVisibleAttributes = disPanelVisibleAttributes;
		this.panelSpacersAttributes = disPanelSpacersAttributes;
		this.modelingAttributes = modelingAttributes;
		createSyntaxAttributes();
	}

	public void createSyntaxAttributes() {
		this.modelingAttributes.put(VAR_AUTOIDENTIFIER, new SyntaxAttribute(
				VAR_AUTOIDENTIFIER, "String", AttributeType.SYNTAX, false,
				"Auto Identifier", null, 0, 1, "", "", -1, "", ""));
		this.modelingAttributes.put(VAR_USERIDENTIFIER, new SyntaxAttribute(
				VAR_USERIDENTIFIER, "String", AttributeType.SYNTAX, false,
				"UserIdentifier", null, 0, 1, "", "", -1, "", ""));
		/*
		 * this.syntaxAttributes.put(VAR_DESCRIPTION, new SyntaxAttribute(
		 * VAR_DESCRIPTION, "String", false, "description", null));
		 */
		this.propVisibleAttributes.add("01#" + VAR_AUTOIDENTIFIER);
		this.propVisibleAttributes.add("02#" + VAR_USERIDENTIFIER);
		this.propEditableAttributes.add("02#" + VAR_USERIDENTIFIER);

	}

	public InstElement getTransInstSemanticElement() {
		return instSemanticElement;
	}

	public void setTransInstSemanticElement(InstElement instSemanticElement) {
		instSemanticElementId = instSemanticElement.getIdentifier();
		this.instSemanticElement = instSemanticElement;
	}

	public String getInstSemanticElementId() {
		return instSemanticElementId;
	}

	public void setInstSemanticElementId(String instSemanticElementId) {
		this.instSemanticElementId = instSemanticElementId;
	}

	public void setAutoIdentifier(String autoIdentifier) {
		this.autoIdentifier = autoIdentifier;
	}

	public String getAutoIdentifier() {
		return autoIdentifier;
	}

	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	public String getUserIdentifier() {
		return userIdentifier;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setBorderStroke(int borderStroke) {
		this.borderStroke = borderStroke;
	}

	public IntSemanticElement getTransSemanticConcept() {
		if (instSemanticElement != null)
			return this.instSemanticElement.getEditableSemanticElement();
		else
			return null;
	}

	public int getBorderStroke() {
		return borderStroke;
	}

	public String getName() {
		return name;
	}

	public String getStyle() {
		return style;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getImage() {
		return image;
	}

	public abstract Set<String> getAllAttributesNames(List<InstElement> parents);

	public abstract AbstractAttribute getSemanticAttribute(String name);

	public void setPropVisibleAttributes(List<String> disPropVisibleAttributes) {
		this.propVisibleAttributes = disPropVisibleAttributes;
	}

	public void setPropEditableAttributes(List<String> disPropEditableAttributes) {
		this.propEditableAttributes = disPropEditableAttributes;
	}

	public void setPanelVisibleAttributes(List<String> disPanelVisibleAttributes) {
		this.panelVisibleAttributes = disPanelVisibleAttributes;
	}

	public void setPanelSpacersAttributes(List<String> disPanelSpacersAttributes) {
		this.panelSpacersAttributes = disPanelSpacersAttributes;
	}

	public void setModelingAttributes(
			Map<String, AbstractAttribute> modelingAttributes) {
		this.modelingAttributes = modelingAttributes;
	}

	public void setModelingAttributes(
			HashSet<AbstractAttribute> modelingAttributes) {
		this.modelingAttributes = new TreeMap<String, AbstractAttribute>();
		Iterator<AbstractAttribute> iter = modelingAttributes.iterator();
		while (iter.hasNext()) {
			AbstractAttribute att = iter.next();
			this.modelingAttributes.put(att.getName(), att);
		}

	}

	public List<String> getPropVisibleAttributes() {
		return propVisibleAttributes;
	}

	protected Set<String> getPropVisibleAttributesSet() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(propVisibleAttributes);
		return modelingAttributesNames;
	}

	public Set<String> getPropVisibleAttributesSet(List<InstElement> parents) {
		return getPropVisibleAttributesSet();
	}

	public void addPropVisibleAttribute(String visibleAttribute) {
		propVisibleAttributes.add(visibleAttribute);
	}

	public List<String> getPropEditableAttributes() {
		return propEditableAttributes;
	}

	protected Set<String> getPropEditableAttributesSet() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(propEditableAttributes);
		return modelingAttributesNames;
	}

	public Set<String> getPropEditableAttributesSet(List<InstElement> parents) {
		return getPropEditableAttributesSet();
	}

	public void addPropEditableAttribute(String editableAttribute) {
		propEditableAttributes.add(editableAttribute);
	}

	public Set<String> getPanelVisibleAttributesSet(List<InstElement> parents) {
		return getPanelVisibleAttributesSet();
	}

	public List<String> getPanelVisibleAttributes() {
		return panelVisibleAttributes;
	}

	protected Set<String> getPanelVisibleAttributesSet() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(panelVisibleAttributes);
		return modelingAttributesNames;
	}

	public void addPanelVisibleAttribute(String visibleAttribute) {
		panelVisibleAttributes.add(visibleAttribute);
	}

	public Set<String> getPanelSpacersAttributesSet(List<InstElement> parents) {
		return getPanelSpacersAttributesSet();
	}

	public List<String> getPanelSpacersAttributes() {
		return panelSpacersAttributes;
	}

	protected Set<String> getPanelSpacersAttributesSet() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(panelSpacersAttributes);
		return modelingAttributesNames;
	}

	public void addPanelSpacersAttribute(String spacerAttribute) {
		panelSpacersAttributes.add(spacerAttribute);
	}

	public Set<String> getDeclaredModelingAttributesNames() {
		return modelingAttributes.keySet();
	}

	public Set<String> getModelingAttributesNames(List<InstElement> parents) {
		return getModelingAttributesNames();
	}

	protected Set<String> getModelingAttributesNames() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(modelingAttributes.keySet());
		return modelingAttributesNames;
	}

	public Map<String, AbstractAttribute> getModelingAttributes() {
		return modelingAttributes;
	}

	public AbstractAttribute getDeclaredModelingAttribute(String name) {
		return modelingAttributes.get(name);
	}

	public AbstractAttribute getModelingAttribute(String name,
			List<InstElement> parents) {
		return modelingAttributes.get(name);
	}

	protected AbstractAttribute getModelingAttribute(String name) {
		return modelingAttributes.get(name);
	}

	public String getModelingAttributeName(String name) {
		if (modelingAttributes.get(name) != null)
			return modelingAttributes.get(name).getName();
		return null;
	}

	public AbstractAttribute getAbstractAttribute(String attributeName,
			List<InstElement> parents) {
		AbstractAttribute out = getSemanticAttribute(attributeName);
		if (out == null)
			return getModelingAttribute(attributeName, parents);
		else
			return out;
	}

	public void addModelingAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		if (!name.equals(VAR_AUTOIDENTIFIER)
				&& modelingAttributes.get(name) == null)
			modelingAttributes.put(name, new SyntaxAttribute(name, type,
					AttributeType.SYNTAX, affectProperties, displayName,
					defaultValue, defaultGroup, propTabPosition,
					propTabEditionCondition, propTabVisualCondition,
					elementDisplayPosition, elementDisplaySpacers,
					elementDisplayCondition));
	}

	public void addModelingAttribute(String name,
			AbstractAttribute abstractAttribute) {
		if (!name.equals(VAR_AUTOIDENTIFIER)
				&& modelingAttributes.get(name) == null)
			modelingAttributes.put(name, abstractAttribute);
	}

	public void addModelingAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue, int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		if (!name.equals(VAR_AUTOIDENTIFIER)
				&& modelingAttributes.get(name) == null)
			modelingAttributes.put(name, new SyntaxAttribute(name, type,
					AttributeType.SYNTAX, affectProperties, displayName,
					enumType, defaultValue, defaultGroup, propTabPosition,
					propTabEditionCondition, propTabVisualCondition,
					elementDisplayPosition, elementDisplaySpacers,
					elementDisplayCondition));
	}

	public boolean getVisible() {
		return visible;
	}

	public abstract char getType();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPalette() {
		return "";
	}
}
