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

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.types.AttributeType;

/**
 * A class to represented elements of the meta model. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24
 */
public class SyntaxElement implements Serializable {

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
	VAR_USERIDENTIFIER = "userId",
	/**
			 * 
			 */
	VAR_DESCRIPTION = "description";
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

	// For PWRel
	private String palette = "";

	// For View
	protected String paletteName = "";
	protected int index;

	public int getIndex() {
		return index;
	}

	public String getPaletteName() {
		return paletteName;
	}

	public void setPaletteName(String paletteName) {
		this.paletteName = paletteName;
	}

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

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

	private boolean topConcept;
	private String backgroundColor;
	private boolean resizable;

	public static final String
	/**
	 * Name for the enumeration name
	 */
	VAR_METAENUMNAME = "name",
	/**
	 * Display name for the enumeration name
	 */
	VAR_METAENUMNAMENAME = "Name",
	/**
	 * Name for the enumeration value
	 */
	VAR_METAENUMVALUE = "value",
	/**
	 * Display name for the enumeration value
	 */
	VAR_METAENUMVALUENAME = "Values",
	/**
	 * Canonical name of the InstAttributeClass
	 */
	VAR_METAENUMVALUECLASS = InstAttribute.class.getCanonicalName();

	public SyntaxElement(String identifier, boolean visible, boolean editable,
			String name, String style, String description, int width,
			int height, String image, int borderStroke,
			InstElement instSemanticElement, boolean topConcept,
			String backgroundColor, boolean resizable) {
		this(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable, new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new HashMap<String, ElemAttribute>(),
				new ArrayList<SyntaxElement>(), new ArrayList<SyntaxElement>());
	}

	public SyntaxElement(String identifier, boolean visible, boolean editable,
			String name, String style, String description, int width,
			int height, String image, int borderStroke,
			InstElement instSemanticElement, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, ElemAttribute> modelingAttributes) {
		this(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable, disPropVisibleAttributes,
				disPropEditableAttributes, disPanelVisibleAttributes,
				disPanelSpacersAttributes, modelingAttributes,
				new ArrayList<SyntaxElement>(), new ArrayList<SyntaxElement>());
	}

	public SyntaxElement(char type, String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke,
			InstElement instSemanticElement) {
		this(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement);
		this.type = type;
	}

	public SyntaxElement(char type, String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke) {
		this(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, null);
		this.type = type;
	}

	public SyntaxElement(char type, String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke,
			String paletteName, int index, InstElement instSemanticElement) {
		this(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement);
		this.type = type;
		this.index = index;
		this.paletteName = paletteName;
	}

	public SyntaxElement(char type, String shortName, String name,
			String paletteName, int index, InstElement instSemanticElement) {
		this(shortName, true, true, name, "", "", 100, 30, "", 1,
				instSemanticElement);
		this.type = type;
		this.index = index;
		this.paletteName = paletteName;
	}

	public SyntaxElement(String identifier, boolean visible, boolean editable,
			String name, String style, String description, int width,
			int height, String image, int borderStroke,
			InstElement instSemanticElement, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, ElemAttribute> modelingAttributes,
			List<SyntaxElement> asOriginRelations,
			List<SyntaxElement> asDestinationRelations) {
		this(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement,
				disPropVisibleAttributes, disPropEditableAttributes,
				disPanelVisibleAttributes, disPanelSpacersAttributes,
				modelingAttributes);

		this.backgroundColor = backgroundColor;
		this.topConcept = topConcept;
		this.resizable = resizable;
	}

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
		this.modelingAttributes.put(VAR_AUTOIDENTIFIER, new ElemAttribute(
				VAR_AUTOIDENTIFIER, "String", AttributeType.SYNTAX, false,
				"Auto Identifier", "", null, 0, 1, "false", "", -1, "", ""));
		this.modelingAttributes.put(VAR_USERIDENTIFIER, new ElemAttribute(
				VAR_USERIDENTIFIER, "String", AttributeType.SYNTAX, false,
				"UserIdentifier", "", null, 0, 1, "", "", -1, "", ""));
		/*
		 * this.syntaxAttributes.put(VAR_DESCRIPTION, new ElemAttribute(
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

	public OpersElement getTransSemanticConcept() {
		if (instSemanticElement != null)
			return this.instSemanticElement.getEdOperEle();
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
				SyntaxElement parentConcept = parent.getEdSyntaxEle();
				if (parentConcept != null
						&& parentConcept.getTransInstSemanticElement() != null) {
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

	public void setModelingAttributes(
			Map<String, ElemAttribute> modelingAttributes) {
		this.modelingAttributes = modelingAttributes;
	}

	public void setModelingAttributes(HashSet<ElemAttribute> modelingAttributes) {
		this.modelingAttributes = new TreeMap<String, ElemAttribute>();
		Iterator<ElemAttribute> iter = modelingAttributes.iterator();
		while (iter.hasNext()) {
			ElemAttribute att = iter.next();
			this.modelingAttributes.put(att.getName(), att);
		}

	}

	@Deprecated
	public void addPropVisibleAttribute(String visibleAttribute) {
		propVisibleAttributes.add(visibleAttribute);
	}

	@Deprecated
	public void addPropEditableAttribute(String editableAttribute) {
		propEditableAttributes.add(editableAttribute);
	}

	public Set<String> getDeclaredModelingAttributesNames() {
		return modelingAttributes.keySet();
	}

	public Set<String> getModelingAttributesNames(List<InstElement> parents) {
		Set<String> modelingAttributesNames = new HashSet<String>();
		modelingAttributesNames.addAll(getModelingAttributesNames());
		if (parents != null)
			for (InstElement parent : parents) {
				SyntaxElement parentConcept = parent.getEdSyntaxEle();
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
					SyntaxElement parentConcept = parent.getEdSyntaxEle();

					if (parentConcept != null
					// && parentConcept.getModelingAttribute(name) != null
					) {
						return parentConcept.getModelingAttribute(name);
					}
				}
		}
		return null;
	}

	public ElemAttribute getAbstractAttribute(String attributeName,
			List<InstElement> syntaxParents, List<InstElement> opersParents) {
		ElemAttribute out = getModelingAttribute(attributeName, syntaxParents);
		if (out == null)
			return getSemanticAttribute(attributeName);
		else
			return out;
	}

	public boolean equals(SyntaxElement obj) {
		return getAutoIdentifier().equals(obj.getAutoIdentifier());
	}

	public void addModelingAttribute(String name, String type,
			boolean affectProperties, String displayName, String toolTipText,
			Object defaultValue, int defaultGroup, int propTabPosition,
			String propTabEditionCondition, String propTabVisualCondition,
			int elementDisplayPosition, String elementDisplaySpacers,
			String elementDisplayCondition) {
		if (modelingAttributes.get(name) != null)
			modelingAttributes.remove(modelingAttributes.get(name));
		modelingAttributes.put(name, new ElemAttribute(name, type,
				AttributeType.SYNTAX, affectProperties, displayName,
				toolTipText, defaultValue, defaultGroup, propTabPosition,
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
			boolean affectProperties, String displayName, String toolTipText,
			String enumType, Object defaultValue, int defaultGroup,
			int propTabPosition, String propTabEditionCondition,
			String propTabVisualCondition, int elementDisplayPosition,
			String elementDisplaySpacers, String elementDisplayCondition) {
		if (!name.equals(VAR_AUTOIDENTIFIER)
				&& modelingAttributes.get(name) == null)
			modelingAttributes.put(name, new ElemAttribute(name, type,
					AttributeType.SYNTAX, affectProperties, displayName,
					toolTipText, enumType, defaultValue, defaultGroup,
					propTabPosition, propTabEditionCondition,
					propTabVisualCondition, elementDisplayPosition,
					elementDisplaySpacers, elementDisplayCondition));
	}

	public void removeModelingAttribute(String value) {
		modelingAttributes.remove(value);
	}

	public boolean getVisible() {
		return visible;
	}

	public boolean setVisible(boolean visible) {
		return this.visible = visible;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<InstAttribute> getOpersRelationTypes() {
		if (getTransInstSemanticElement() == null)
			return null;
		InstAttribute ia = getTransInstSemanticElement().getInstAttribute(
				"relTypesAttr");
		if (ia == null)
			return null;
		List<InstAttribute> ias = (List<InstAttribute>) ia.getValue();

		return ias;
	}

	public void setTopConcept(boolean topConcept) {
		this.topConcept = topConcept;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setResizable(boolean resizable) {
		this.resizable = resizable;
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

	private char type;

	public SyntaxElement(char type) {
		this();
		this.type = type;
	}

	public SyntaxElement(char type, String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke,
			InstElement instSemanticElement, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, ElemAttribute> attributes) {
		this(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable, propVisibleAttributes,
				propEditableAttributes, panelVisibleAttributes,
				panelSparerAttributes, attributes);
		this.type = type;
	}

	public SyntaxElement(char type, String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke,
			InstElement instSemanticElement, boolean resizable) {
		this(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable);
		this.type = type;
	}

	public char getType() {
		return type;
	}
}
