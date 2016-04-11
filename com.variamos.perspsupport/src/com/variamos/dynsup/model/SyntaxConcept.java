package com.variamos.dynsup.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstElement;

/**
 * A class to represented concepts of the meta model. Extends from MetaVertex
 * adding the SemanticConcept and extend relations. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.dynsup.model.SyntaxElement
 */
public class SyntaxConcept extends SyntaxElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8416609016211900965L;
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

	public SyntaxConcept(String identifier, boolean visible, boolean editable,
			String name, String style, String description, int width,
			int height, String image, int borderStroke,
			InstElement instSemanticElement, boolean topConcept,
			String backgroundColor, boolean resizable) {
		this(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable, new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), new HashMap<String, ElemAttribute>(),
				new ArrayList<SyntaxPairwiseRel>(),
				new ArrayList<SyntaxPairwiseRel>());
	}

	public SyntaxConcept(String identifier, boolean visible, boolean editable,
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
				new ArrayList<SyntaxPairwiseRel>(),
				new ArrayList<SyntaxPairwiseRel>());
	}

	public SyntaxConcept(String identifier, boolean visible, boolean editable,
			String name, String style, String description, int width,
			int height, String image, int borderStroke,
			InstElement instSemanticElement, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> disPropVisibleAttributes,
			List<String> disPropEditableAttributes,
			List<String> disPanelVisibleAttributes,
			List<String> disPanelSpacersAttributes,
			Map<String, ElemAttribute> modelingAttributes,
			List<SyntaxPairwiseRel> asOriginRelations,
			List<SyntaxPairwiseRel> asDestinationRelations) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement,
				disPropVisibleAttributes, disPropEditableAttributes,
				disPanelVisibleAttributes, disPanelSpacersAttributes,
				modelingAttributes);

		this.backgroundColor = backgroundColor;
		this.topConcept = topConcept;
		this.resizable = resizable;
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

	public SyntaxConcept() {
		super();

	}

	public SyntaxConcept(char type) {
		super();
		this.type = type;
	}

	public SyntaxConcept(char type, String identifier, boolean visible,
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

	public SyntaxConcept(char type, String identifier, boolean visible,
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
