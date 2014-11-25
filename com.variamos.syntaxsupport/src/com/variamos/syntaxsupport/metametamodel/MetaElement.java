package com.variamos.syntaxsupport.metametamodel;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cfm.productline.Prototype;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticDirectRelation;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class MetaElement implements Serializable {
	public void setIdentified(String identified) {
		this.identified = identified;
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

	public void setPropVisibleAttributes(List<String> propVisibleAttributes) {
		this.propVisibleAttributes = propVisibleAttributes;
	}

	public void setPropEditableAttributes(List<String> propEditableAttributes) {
		this.propEditableAttributes = propEditableAttributes;
	}

	public void setPanelVisibleAttributes(List<String> panelVisibleAttributes) {
		this.panelVisibleAttributes = panelVisibleAttributes;
	}

	public void setPanelSpacersAttributes(List<String> panelSpacersAttributes) {
		this.panelSpacersAttributes = panelSpacersAttributes;
	}

	public void setMetaAttributes(Map<String, MetaAttribute> metaAttributes) {
		this.metaAttributes = metaAttributes;
	}

	public void setMetaDirectRelations(List<MetaDirectRelation> metaDirectRelations) {
		this.metaDirectRelations = metaDirectRelations;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3348811616807875183L;
	private String identified;
	private String name;
	private String style;
	private int width;
	private int height;
	private String image;
	private boolean topConcept;
	private String backgroundColor;
	private int borderStroke;

	private boolean resizable;
	private List<String> propVisibleAttributes;
	private List<String> propEditableAttributes;
	private List<String> panelVisibleAttributes;
	private List<String> panelSpacersAttributes;
	private Map<String, MetaAttribute> metaAttributes;
	private List<MetaDirectRelation> metaDirectRelations;

	public MetaElement()
	{
		
	}
	
	public MetaElement(String identified, String name, String style, int width,
			int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable) {
		this(identified, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable,
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new HashMap<String, MetaAttribute>(),
				new ArrayList<MetaDirectRelation>());

	}

	public MetaElement(String identified, String name, String style, int width,
			int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSpacersAttributes,
			Map<String, MetaAttribute> metaAttributes) {
		this(identified, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSpacersAttributes, metaAttributes,
				new ArrayList<MetaDirectRelation>());

	}

	public MetaElement(String identified, String name, String style, int width,
			int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSpacersAttributes,
			Map<String, MetaAttribute> metaAttributes,
			List<MetaDirectRelation> metaRelations) {
		super();
		this.identified = identified;
		this.name = name;
		this.style = style;
		this.width = width;
		this.height = height;
		this.image = image;
		this.topConcept = topConcept;
		this.backgroundColor = backgroundColor;
		this.borderStroke = borderStroke;
		this.resizable = resizable;
		this.propVisibleAttributes = propVisibleAttributes;
		this.propEditableAttributes = propEditableAttributes;
		this.panelVisibleAttributes = panelVisibleAttributes;
		this.panelSpacersAttributes = panelSpacersAttributes;
		this.metaAttributes = metaAttributes;
		this.metaAttributes.put("identifier", new MetaAttribute("identifier",
				"String", null));
		this.metaDirectRelations = metaRelations;
	}

	public List<MetaDirectRelation> getMetaDirectRelations() {
		return metaDirectRelations;
	}

	public int getBorderStroke() {
		return borderStroke;
	}

	public String getIdentified() {
		return identified;
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

	public List<String> getPropVisibleAttributes() {
		List<String> metaAttributesNames = new ArrayList<String>();
		for (int i = 0; i < metaDirectRelations.size(); i++) {
			MetaDirectRelation metaDirectRelation = metaDirectRelations.get(i);
			if (metaDirectRelation instanceof MetaExtends)
			{
				metaAttributesNames.addAll(metaDirectRelation.getDestination().getPropVisibleAttributes());
			}
		}
		metaAttributesNames.addAll(propVisibleAttributes);
		return metaAttributesNames;
	}

	public void addPropVisibleAttributes(String visibleAttribute) {
		propVisibleAttributes.add(visibleAttribute);
	}

	public List<String> getPropEditableAttributes() {
		List<String> metaAttributesNames = new ArrayList<String>();
		for (int i = 0; i < metaDirectRelations.size(); i++) {
			MetaDirectRelation metaDirectRelation = metaDirectRelations.get(i);
			if (metaDirectRelation instanceof MetaExtends)
			{
				metaAttributesNames.addAll(metaDirectRelation.getDestination().getPropEditableAttributes());
			}
		}
		metaAttributesNames.addAll(propEditableAttributes);
		return metaAttributesNames;
	}

	public void addPropEditableAttributes(String editableAttribute) {
		propEditableAttributes.add(editableAttribute);
	}

	public List<String> getPanelVisibleAttributes() {
		List<String> metaAttributesNames = new ArrayList<String>();
		for (int i = 0; i < metaDirectRelations.size(); i++) {
			MetaDirectRelation metaDirectRelation = metaDirectRelations.get(i);
			if (metaDirectRelation instanceof MetaExtends)
			{
				metaAttributesNames.addAll(metaDirectRelation.getDestination().getPanelVisibleAttributes());
			}
		}
		metaAttributesNames.addAll(panelVisibleAttributes);
		return metaAttributesNames;
	}

	public void addPanelVisibleAttributes(String visibleAttribute) {
		panelVisibleAttributes.add(visibleAttribute);
	}

	public List<String> getPanelSpacersAttributes() {
		List<String> metaAttributesNames = new ArrayList<String>();
		for (int i = 0; i < metaDirectRelations.size(); i++) {
			MetaDirectRelation metaDirectRelation = metaDirectRelations.get(i);
			if (metaDirectRelation instanceof MetaExtends)
			{
				metaAttributesNames.addAll(metaDirectRelation.getDestination().getPanelSpacersAttributes());
			}
		}

		metaAttributesNames.addAll(panelSpacersAttributes);
		return metaAttributesNames;
	}

	public void addPanelSpacersAttributes(String spacerAttribute) {
		panelSpacersAttributes.add(spacerAttribute);
	}

	public Set<String> getDeclaredMetaAttributes() {
		return metaAttributes.keySet();
	}

	public Set<String> getMetaAttributes() {
		Set<String> metaAttributesNames = new HashSet<String>();
		metaAttributesNames.addAll(metaAttributes.keySet());
		for (int i = 0; i < metaDirectRelations.size(); i++) {
			MetaDirectRelation metaDirectRelation = metaDirectRelations.get(i);
			if (metaDirectRelation instanceof MetaExtends)
			{
				metaAttributesNames.addAll(metaDirectRelation.getDestination().getMetaAttributes());
			}
		}
		return metaAttributesNames;
	}

	public MetaAttribute getDeclaredMetaAttribute(String name) {
		return metaAttributes.get(name);
	}
	
	public MetaAttribute getMetaAttribute(String name) {
	if (metaAttributes.get(name)!=null)
		return metaAttributes.get(name);
	else
	{
		for (int i = 0; i < metaDirectRelations.size(); i++) {
			MetaDirectRelation metaDirectRelation = metaDirectRelations.get(i);
			if (metaDirectRelation instanceof MetaExtends)
			{
				if(metaDirectRelation.getDestination().getMetaAttribute(name)!=null)
					return metaDirectRelation.getDestination().getMetaAttribute(name);
			}
		}
	}
	return null;
	}
	public String getMetaAttributeName(String name) {
	if (metaAttributes.get(name)!=null)
		return metaAttributes.get(name).getName();
	else
	{
		for (int i = 0; i < metaDirectRelations.size(); i++) {
			MetaDirectRelation metaDirectRelation = metaDirectRelations.get(i);
			if (metaDirectRelation instanceof MetaExtends)
			{
				if(metaDirectRelation.getDestination().getMetaAttribute(name)!=null)
					return metaDirectRelation.getDestination().getMetaAttribute(name).getName();
			}
		}
	}
	return null;
	}

	public void addMetaAttribute(String name, String type, Object defaultValue) {
		if (!name.equals("identifier") && metaAttributes.get(name) == null)
			metaAttributes.put(name,
					new MetaAttribute(name, type, defaultValue));
	}

	public void addExtendMetaDirectRelation(
			IntSemanticDirectRelation directRelationType, MetaConcept origin,
			MetaConcept destination, boolean completeDisjoint) {
		// TODO validate
		metaDirectRelations.add(new MetaExtends(directRelationType, origin,
				destination, completeDisjoint));
	}

	public MetaAssociation addAssociationMetaDirectRelation(
			IntSemanticDirectRelation directRelationType, MetaConcept origin,
			MetaConcept destination) {
		// TODO add the parameters for the association
		MetaAssociation metaAssociation = new MetaAssociation(
				directRelationType, origin, destination);
		metaDirectRelations.add(metaAssociation);
		return metaAssociation;
	}
}
