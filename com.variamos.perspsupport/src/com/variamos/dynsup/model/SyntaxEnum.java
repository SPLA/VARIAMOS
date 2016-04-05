package com.variamos.dynsup.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.variamos.dynsup.instance.InstAttribute;

/**
 * A class to support the dynamic enumeration on modeling perspective. Extends
 * basic functionality from MetaVertex. Define two additional dynamic
 * attributes. The set is not fully dynamically loaded (static validation). Part
 * of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.dynsup.model.SyntaxVertex
 */
public class SyntaxEnum extends SyntaxVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4695459882699621321L;

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

	public SyntaxEnum(String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke,
			boolean topConcept, String backgroundColor, boolean resizable,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, ElemAttribute> attributes) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, null, topConcept, backgroundColor,
				resizable, propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes, attributes);
		createEnumModelingAttributes();
	}

	public SyntaxEnum(String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, null, topConcept, backgroundColor,
				resizable);
		createEnumModelingAttributes();
	}

	public SyntaxEnum() {
		super();
		createEnumModelingAttributes();
	}

	private void createEnumModelingAttributes() {
		addModelingAttribute(VAR_METAENUMNAME, "String", false,
				VAR_METAENUMNAMENAME, "", 0, 1, "", "", 1, "#-#\n\n", "");
		addModelingAttribute(VAR_METAENUMVALUE, "Set", false,
				VAR_METAENUMVALUENAME, VAR_METAENUMVALUECLASS,
				new ArrayList<InstAttribute>(), 0, 2, "", "", 2, "#\n", "");

		this.addPropEditableAttribute("01#" + VAR_METAENUMNAME);

		this.addPropVisibleAttribute("01#" + VAR_METAENUMNAME);
		this.addPanelVisibleAttribute("05#" + VAR_METAENUMVALUE);
		this.addPanelSpacersAttribute("#" + VAR_METAENUMVALUE + "#\n");
	}

	public char getType() {
		return 'E';
	};
}
