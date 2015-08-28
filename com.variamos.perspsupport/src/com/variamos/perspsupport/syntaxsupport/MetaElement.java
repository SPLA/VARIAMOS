package com.variamos.perspsupport.syntaxsupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.TreeMap;

import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.semanticinterface.IntSemanticConcept;
import com.variamos.perspsupport.semanticinterface.IntSemanticElement;

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
	VAR_IDENTIFIER = "identifier",
	/**
			 * 
			 */
	VAR_DESCRIPTION = "Description";
	/**
	 * 
	 */
	private String identifier;
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

	public MetaElement(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, InstElement instSemanticElement,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> modelingAttributes)

	{
		this.identifier = identifier;
		this.visible = visible;
		this.name = name;
		this.style = style;
		this.description = description; // TODO include in parameters
		this.width = width;
		this.height = height;
		this.image = image;
		this.borderStroke = borderStroke;
		this.instSemanticElement = instSemanticElement;
		this.propVisibleAttributes = disPropVisibleAttributes;
		this.propEditableAttributes = disPropEditableAttributes;
		this.panelVisibleAttributes = disPanelVisibleAttributes;
		this.panelSpacersAttributes = disPanelSpacersAttributes;
		this.modelingAttributes = modelingAttributes;
		createSyntaxAttributes();
	}

	public void createSyntaxAttributes() {
		this.modelingAttributes.put(VAR_IDENTIFIER, new SyntaxAttribute(
				VAR_IDENTIFIER, "String", false, "Identifier", null, 0));
		/*
		 * this.syntaxAttributes.put(VAR_DESCRIPTION, new SyntaxAttribute(
		 * VAR_DESCRIPTION, "String", false, "description", null));
		 */
		this.propVisibleAttributes.add("01#" + VAR_IDENTIFIER);
		// this.disPropVisibleAttributes.add("91#" + VAR_DESCRIPTION);

	}

	public InstElement getInstSemanticElement() {
		return instSemanticElement;
	}

	public void setInstSemanticElement(InstElement instSemanticElement) {
		this.instSemanticElement = instSemanticElement;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
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

	public abstract Set<String> getAllAttributesNames();

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

	public Set<String> getPropVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(propVisibleAttributes);
		return modelingAttributesNames;
	}

	public void addPropVisibleAttribute(String visibleAttribute) {
		propVisibleAttributes.add(visibleAttribute);
	}

	public Set<String> getPropEditableAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(propEditableAttributes);
		return modelingAttributesNames;
	}

	public void addPropEditableAttribute(String editableAttribute) {
		propEditableAttributes.add(editableAttribute);
	}

	public Set<String> getPanelVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(panelVisibleAttributes);
		return modelingAttributesNames;
	}

	public void addPanelVisibleAttribute(String visibleAttribute) {
		panelVisibleAttributes.add(visibleAttribute);
	}

	public Set<String> getPanelSpacersAttributes() {
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

	public Set<String> getModelingAttributesNames() {
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

	public AbstractAttribute getModelingAttribute(String name) {
		return modelingAttributes.get(name);
	}

	public String getModelingAttributeName(String name) {
		if (modelingAttributes.get(name) != null)
			return modelingAttributes.get(name).getName();
		return null;
	}

	public AbstractAttribute getAbstractAttribute(String attributeName) {
		AbstractAttribute out = getSemanticAttribute(attributeName);
		if (out == null)
			return getModelingAttribute(attributeName);
		else
			return out;
	}

	public void addModelingAttribute(String name, String type,
			boolean affectProperties, String displayName, Object defaultValue,
			int defaultGroup) {
		if (!name.equals("identifier") && modelingAttributes.get(name) == null)
			modelingAttributes.put(name, new SyntaxAttribute(name, type,
					affectProperties, displayName, defaultValue, defaultGroup));
	}

	public void addModelingAttribute(String name,
			AbstractAttribute abstractAttribute) {
		if (!name.equals("identifier") && modelingAttributes.get(name) == null)
			modelingAttributes.put(name, abstractAttribute);
	}

	public void addModelingAttribute(String name, String type,
			boolean affectProperties, String displayName, String enumType,
			Object defaultValue, int defaultGroup) {
		if (!name.equals("identifier") && modelingAttributes.get(name) == null)
			modelingAttributes.put(name, new SyntaxAttribute(name, type,
					affectProperties, displayName, enumType, defaultValue,
					defaultGroup));
	}

	public boolean getVisible() {
		return visible;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;

	}

	public Object getParent() {
		return null;
	}
}
