package com.variamos.dynsup.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.interfaces.IntOpersElement;
import com.variamos.semantic.types.AttributeType;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public abstract class SyntaxElement implements Serializable {

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
	private boolean editable = true;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * 
	 */
	private Map<String, ElemAttribute> modelingAttributes;
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
	public SyntaxElement() {

		this("", true, true, "", "", "", 100, 40, "", 1, null,
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new HashMap<String, ElemAttribute>());
		createSyntaxAttributes();
	}

	public SyntaxElement(String identifier, boolean visible, boolean editable,
			String name, String style, String description, int width,
			int height, String image, int borderStroke,
			InstElement instSemanticElement)

	{
		this(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement,
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new HashMap<String, ElemAttribute>());
	}

	public SyntaxElement(String autoIdentifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke,
			InstElement instSemanticElement,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, ElemAttribute> modelingAttributes)

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
		this.editable = editable;
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

	public IntOpersElement getTransSemanticConcept() {
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

	public Set<String> getAllSemanticAttributesNames(
			List<InstElement> syntaxParents) {
		Set<String> semanticAttributesNames = new HashSet<String>();
		if (getTransInstSemanticElement() != null)
			semanticAttributesNames.addAll(getTransInstSemanticElement()
					.getAllSemanticAttributesNames(syntaxParents));
		return semanticAttributesNames;
	}

	public Set<String> getAllAttributesNames(List<InstElement> syntaxParents) {
		Set<String> modelingAttributesNames = new HashSet<String>();
		if (getTransInstSemanticElement() != null)
			modelingAttributesNames.addAll(getTransInstSemanticElement()
					.getAllSemanticAttributesNames(syntaxParents));
		if (syntaxParents != null)
			for (InstElement parent : syntaxParents) {
				SyntaxConcept parentConcept = (SyntaxConcept) parent
						.getEditableMetaElement();
				if (parentConcept != null) {
					modelingAttributesNames.addAll(parentConcept
							.getTransInstSemanticElement()
							.getAllSemanticAttributesNames(syntaxParents));
					modelingAttributesNames.addAll(parentConcept
							.getModelingAttributesNames(syntaxParents));
				}
			}
		modelingAttributesNames.addAll(this
				.getModelingAttributesNames(syntaxParents));
		return modelingAttributesNames;
	}

	public ElemAttribute getSemanticAttribute(String name) {
		if (getTransInstSemanticElement() != null)
			return getTransInstSemanticElement().getSemanticAttribute(name);
		return null;
	}

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
			Map<String, ElemAttribute> modelingAttributes) {
		this.modelingAttributes = modelingAttributes;
	}

	public void setModelingAttributes(
			HashSet<ElemAttribute> modelingAttributes) {
		this.modelingAttributes = new TreeMap<String, ElemAttribute>();
		Iterator<ElemAttribute> iter = modelingAttributes.iterator();
		while (iter.hasNext()) {
			ElemAttribute att = iter.next();
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

	public Set<String> getPropVisibleAttributesSet(
			List<InstElement> syntaParents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getTransInstSemanticElement() != null
				&& getTransInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getTransInstSemanticElement()
					.getPropVisibleAttributes());
		if (syntaParents != null)
			for (InstElement parent : syntaParents) {
				SyntaxConcept parentConcept = (SyntaxConcept) parent
						.getEditableMetaElement();
				if (parentConcept != null) {
					if (parentConcept.getTransInstSemanticElement()
							.getEditableSemanticElement() != null)
						modelingAttributesNames.addAll(parentConcept
								.getTransInstSemanticElement()
								.getPropVisibleAttributes());
					modelingAttributesNames.addAll(parentConcept
							.getPropVisibleAttributesSet());
				}

			}
		modelingAttributesNames.addAll(getPropVisibleAttributesSet());
		return modelingAttributesNames;
	}

	public Set<String> getPropEditableAttributesSet(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getTransInstSemanticElement() != null
				&& getTransInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getTransInstSemanticElement()
					.getPropEditableAttributes());

		if (parents != null)
			for (InstElement parent : parents) {
				SyntaxConcept parentConcept = (SyntaxConcept) parent
						.getEditableMetaElement();
				if (parentConcept != null) {
					if (parentConcept.getTransInstSemanticElement()
							.getEditableSemanticElement() != null)
						modelingAttributesNames.addAll(parentConcept
								.getTransInstSemanticElement()
								.getPropEditableAttributes());
					modelingAttributesNames.addAll(parentConcept
							.getPropEditableAttributesSet());
				}
			}
		modelingAttributesNames.addAll(getPropEditableAttributesSet());
		return modelingAttributesNames;
	}

	public Set<String> getPanelVisibleAttributesSet(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getTransInstSemanticElement() != null
				&& getTransInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getTransInstSemanticElement()
					.getPanelVisibleAttributes());

		if (parents != null)
			for (InstElement parent : parents) {
				SyntaxConcept parentConcept = (SyntaxConcept) parent
						.getEditableMetaElement();
				if (parentConcept != null) {
					if (parentConcept.getTransInstSemanticElement()
							.getEditableSemanticElement() != null)
						modelingAttributesNames.addAll(parentConcept
								.getTransInstSemanticElement()
								.getPanelVisibleAttributes());
					modelingAttributesNames.addAll(parentConcept
							.getPanelVisibleAttributesSet());
				}
			}
		modelingAttributesNames.addAll(getPanelVisibleAttributesSet());
		return modelingAttributesNames;
	}

	public Set<String> getPanelSpacersAttributesSet(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();

		if (getTransInstSemanticElement() != null
				&& getTransInstSemanticElement().getEditableSemanticElement() != null)
			modelingAttributesNames.addAll(getTransInstSemanticElement()
					.getPanelSpacersAttributes());

		if (parents != null)
			for (InstElement parent : parents) {
				SyntaxConcept parentConcept = (SyntaxConcept) parent
						.getEditableMetaElement();
				if (parentConcept != null) {
					if (parentConcept.getTransInstSemanticElement()
							.getEditableSemanticElement() != null)
						modelingAttributesNames.addAll(parentConcept
								.getTransInstSemanticElement()
								.getPanelSpacersAttributes());

					modelingAttributesNames.addAll(parentConcept
							.getPanelSpacersAttributesSet());
				}

			}
		modelingAttributesNames.addAll(getPanelSpacersAttributesSet());
		return modelingAttributesNames;
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

	public void addPropEditableAttribute(String editableAttribute) {
		propEditableAttributes.add(editableAttribute);
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
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(getModelingAttributesNames());
		if (parents != null)
			for (InstElement parent : parents) {
				SyntaxConcept parentConcept = (SyntaxConcept) parent
						.getEditableMetaElement();
				if (parentConcept != null)
					modelingAttributesNames.addAll(parentConcept
							.getModelingAttributesNames());
			}
		return modelingAttributesNames;
	}

	protected Set<String> getModelingAttributesNames() {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(modelingAttributes.keySet());
		return modelingAttributesNames;
	}

	public Map<String, ElemAttribute> getModelingAttributes() {
		return modelingAttributes;
	}

	public ElemAttribute getDeclaredModelingAttribute(String name) {
		return modelingAttributes.get(name);
	}

	protected ElemAttribute getModelingAttribute(String name) {
		return modelingAttributes.get(name);
	}

	public String getModelingAttributeName(String name) {
		if (modelingAttributes.get(name) != null)
			return modelingAttributes.get(name).getName();
		return null;
	}

	public ElemAttribute getModelingAttribute(String name,
			List<InstElement> parents) {
		if (getModelingAttribute(name) != null)
			return getModelingAttribute(name);
		else {
			if (parents != null)
				for (InstElement parent : parents) {
					SyntaxConcept parentConcept = (SyntaxConcept) parent
							.getEditableMetaElement();

					if (parentConcept != null
							&& parentConcept.getModelingAttribute(name) != null) {
						return parentConcept.getModelingAttribute(name);
					}
				}
		}
		return null;
	}

	public ElemAttribute getAbstractAttribute(String attributeName,
			List<InstElement> syntaxParents, List<InstElement> opersParents) {
		ElemAttribute out = getSemanticAttribute(attributeName);
		if (out == null)
			return getModelingAttribute(attributeName, syntaxParents);
		else
			return out;
	}

	public boolean equals(SyntaxElement obj) {
		return getAutoIdentifier().equals(obj.getAutoIdentifier());
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
			ElemAttribute abstractAttribute) {
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
