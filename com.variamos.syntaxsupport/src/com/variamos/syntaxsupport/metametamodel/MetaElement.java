package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticRelation;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class MetaElement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3348811616807875183L;
	private String identifier;
	private String name;
	private String style;
	private int width;
	private int height;
	private String image;
	private boolean topConcept;
	private String backgroundColor;
	private int borderStroke;
	private boolean resizable;
	private List<String> disPropVisibleAttributes;
	private List<String> disPropEditableAttributes;
	private List<String> disPanelVisibleAttributes;
	private List<String> disPanelSpacersAttributes;
	private Map<String, AbstractAttribute> metaAttributes;
	private List<MetaAssociation> asOriginRelations;
	private List<MetaAssociation> asDestinationRelations;

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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

	public void setTopConcept(boolean topConcept) {
		this.topConcept = topConcept;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setBorderStroke(int borderStroke) {
		this.borderStroke = borderStroke;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
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

	public void setMetaAttributes(Map<String, AbstractAttribute> metaAttributes) {
		this.metaAttributes = metaAttributes;
	}

	public void setAsOriginRelations(	List<MetaAssociation> asOriginRelations) {
		this.asOriginRelations = asOriginRelations;
	}

	public MetaElement() {

	}

	public MetaElement(String identifier, String name, String style, int width,
			int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable) {
		this(identifier, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable,
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new HashMap<String, AbstractAttribute>(),
				new ArrayList<MetaAssociation>(),
				new ArrayList<MetaAssociation>());

	}

	public MetaElement(String identifier, String name, String style, int width,
			int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> metaAttributes) {
		this(identifier, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable,
				disPropVisibleAttributes, disPropEditableAttributes,
				disPanelVisibleAttributes, disPanelSpacersAttributes, metaAttributes,
				new ArrayList<MetaAssociation>(),
				new ArrayList<MetaAssociation>());

	}

	public MetaElement(String identifier, String name, String style, int width,
			int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, AbstractAttribute> metaAttributes,
			List<MetaAssociation> asOriginRelations,
			List<MetaAssociation> asDestinationRelations) {
		super();
		this.identifier = identifier;
		this.name = name;
		this.style = style;
		this.width = width;
		this.height = height;
		this.image = image;
		this.topConcept = topConcept;
		this.backgroundColor = backgroundColor;
		this.borderStroke = borderStroke;
		this.resizable = resizable;
		this.disPropVisibleAttributes = disPropVisibleAttributes;
		this.disPropEditableAttributes = disPropEditableAttributes;
		this.disPanelVisibleAttributes = disPanelVisibleAttributes;
		this.disPanelSpacersAttributes = disPanelSpacersAttributes;
		this.metaAttributes = metaAttributes;
		this.metaAttributes.put("identifier", new MetaAttribute("identifier",
				"String", null));
		this.asOriginRelations = asOriginRelations;
		this.asDestinationRelations = asDestinationRelations;
	}

	public List<MetaAssociation> getAsOriginRelations() {
		return asOriginRelations;
	}

	public int getBorderStroke() {
		return borderStroke;
	}

	public String getIdentifier() {
		return identifier;
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

	public boolean isTopConcept() {
		return topConcept;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public boolean isResizable() {
		return resizable;
	}

	public Set<String> getDisPropVisibleAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();
		metaAttributesNames.addAll(disPropVisibleAttributes);
		return metaAttributesNames;
	}

	public void addDisPropVisibleAttribute(String visibleAttribute) {
		disPropVisibleAttributes.add(visibleAttribute);
	}

	public Set<String> getDisPropEditableAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();
		metaAttributesNames.addAll(disPropEditableAttributes);
		return metaAttributesNames;
	}

	public void addDisPropEditableAttribute(String editableAttribute) {
		disPropEditableAttributes.add(editableAttribute);
	}

	public Set<String> getDisPanelVisibleAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();
		metaAttributesNames.addAll(disPanelVisibleAttributes);
		return metaAttributesNames;
	}

	public void addDisPanelVisibleAttribute(String visibleAttribute) {
		disPanelVisibleAttributes.add(visibleAttribute);
	}

	public Set<String> getDisPanelSpacersAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();
		metaAttributesNames.addAll(disPanelSpacersAttributes);
		return metaAttributesNames;
	}
	
	public void addDisPanelSpacersAttribute(String spacerAttribute) {
		disPanelSpacersAttributes.add(spacerAttribute);
	}

	public Set<String> getDeclaredMetaAttributes() {
		return metaAttributes.keySet();
	}

	public Set<String> getMetaAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();
		metaAttributesNames.addAll(metaAttributes.keySet());
		return metaAttributesNames;
	}

	public AbstractAttribute getDeclaredMetaAttribute(String name) {
		return metaAttributes.get(name);
	}

	public AbstractAttribute getMetaAttribute(String name) {
		return metaAttributes.get(name);
	}

	
	public String getMetaAttributeName(String name) {
		if (metaAttributes.get(name) != null)
			return metaAttributes.get(name).getName();
		return null;
	}

	public boolean equals (MetaElement obj)
	{
		return identifier.equals(obj.getIdentifier());
	}
	
	public void addMetaAttribute(String name, String type, Object defaultValue) {
		if (!name.equals("identifier") && metaAttributes.get(name) == null)
			metaAttributes.put(name,
					new MetaAttribute(name, type, defaultValue));
	}

	public void addMetaAttribute(String name, AbstractAttribute abstractAttribute) {
		if (!name.equals("identifier") && metaAttributes.get(name) == null)
			metaAttributes.put(name,abstractAttribute);
	}
	
	public void addMetaAttribute(String name, String type, String enumType, Object defaultValue) {
		if (!name.equals("identifier") && metaAttributes.get(name) == null)
			metaAttributes.put(name,
					new MetaAttribute(name, type, enumType, defaultValue));
	}
	
	public MetaAssociation addAssociationAsOriginRelations(
			IntDirectSemanticRelation directRelation, MetaConcept destination) {
		MetaAssociation metaAssociation = new MetaAssociation(this, destination);
		asOriginRelations.add(metaAssociation);
		return metaAssociation;
	}

	public AbstractAttribute getAbstractAttribute(String attributeName) {
		return this.getMetaAttribute(attributeName);
	}
}
