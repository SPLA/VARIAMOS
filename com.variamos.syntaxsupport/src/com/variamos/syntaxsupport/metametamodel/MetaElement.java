	package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class MetaElement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5336725046708613241L;
	private String identifier;
	private boolean visible;
	private String name;
	private String style;
	private int width;
	private int height;
	private String image;
	private int borderStroke;
	private List<String> disPropVisibleAttributes;
	private List<String> disPropEditableAttributes;
	private List<String> disPanelVisibleAttributes;
	private List<String> disPanelSpacersAttributes;
	
	private Map<String, AbstractAttribute> modelingAttributes;
	
	public MetaElement()
	{}
	
	public MetaElement(String identifier, boolean visible, String name, String style, int width,
			int height, String image, int borderStroke,
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
		this.width = width;
		this.height = height;
		this.image = image;
		this.borderStroke = borderStroke;
		this.disPropVisibleAttributes = disPropVisibleAttributes;
		this.disPropEditableAttributes = disPropEditableAttributes;
		this.disPanelVisibleAttributes = disPanelVisibleAttributes;
		this.disPanelSpacersAttributes = disPanelSpacersAttributes;
		this.modelingAttributes = modelingAttributes;
		this.modelingAttributes.put("identifier", new ModelingAttribute("identifier",
				"String", null));

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

	
	public void setDisPropVisibleAttributes(List<String> disPropVisibleAttributes) {
		this.disPropVisibleAttributes = disPropVisibleAttributes;
	}

	public void setDisPropEditableAttributes(List<String> disPropEditableAttributes) {
		this.disPropEditableAttributes = disPropEditableAttributes;
	}

	public void setDisPanelVisibleAttributes(List<String> disPanelVisibleAttributes) {
		this.disPanelVisibleAttributes = disPanelVisibleAttributes;
	}

	public void setDisPanelSpacersAttributes(List<String> disPanelSpacersAttributes) {
		this.disPanelSpacersAttributes = disPanelSpacersAttributes;
	}

	public void setModelingAttributes(Map<String, AbstractAttribute> modelingAttributes) {
		this.modelingAttributes = modelingAttributes;
	}

	public Set<String> getDisPropVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(disPropVisibleAttributes);
		return modelingAttributesNames;
	}

	public void addDisPropVisibleAttribute(String visibleAttribute) {
		disPropVisibleAttributes.add(visibleAttribute);
	}

	public Set<String> getDisPropEditableAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(disPropEditableAttributes);
		return modelingAttributesNames;
	}

	public void addDisPropEditableAttribute(String editableAttribute) {
		disPropEditableAttributes.add(editableAttribute);
	}

	public Set<String> getDisPanelVisibleAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(disPanelVisibleAttributes);
		return modelingAttributesNames;
	}

	public void addDisPanelVisibleAttribute(String visibleAttribute) {
		disPanelVisibleAttributes.add(visibleAttribute);
	}

	public Set<String> getDisPanelSpacersAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(disPanelSpacersAttributes);
		return modelingAttributesNames;
	}

	public void addDisPanelSpacersAttribute(String spacerAttribute) {
		disPanelSpacersAttributes.add(spacerAttribute);
	}

	public Set<String> getDeclaredModelingAttributes() {
		return modelingAttributes.keySet();
	}

	public Set<String> getModelingAttributes() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(modelingAttributes.keySet());
		return modelingAttributesNames;
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

	public void addModelingAttribute(String name, String type, Object defaultValue) {
		if (!name.equals("identifier") && modelingAttributes.get(name) == null)
			modelingAttributes.put(name,
					new ModelingAttribute(name, type, defaultValue));
	}

	public void addModelingAttribute(String name,
			AbstractAttribute abstractAttribute) {
		if (!name.equals("identifier") && modelingAttributes.get(name) == null)
			modelingAttributes.put(name, abstractAttribute);
	}

	public void addModelingAttribute(String name, String type, String enumType,
			Object defaultValue) {
		if (!name.equals("identifier") && modelingAttributes.get(name) == null)
			modelingAttributes.put(name, new ModelingAttribute(name, type, enumType,
					defaultValue));
	}

	public boolean getVisible() {
		return visible;
	}


}
