package com.variamos.syntaxsupport.metametamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticConcept;

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
 * @see com.variamos.syntaxsupport.metametamodel.MetaVertex
 */
public class MetaEnumeration extends MetaVertex {
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

	public MetaEnumeration(String identifier, boolean visible, String name,
			String style, String description, int width, int height, String image,
			int borderStroke, boolean topConcept, String backgroundColor,
			boolean resizable, List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> attributes) {
		super(identifier, visible, name, style, description, width, height, image,
				borderStroke, topConcept, backgroundColor, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes, attributes);
		createEnumModelingAttributes();
	}

	public MetaEnumeration(String identifier, boolean visible, String name,
			String style, String description, int width, int height, String image,
			boolean topConcept, String backgroundColor, int borderStroke,
			boolean resizable) {
		super(identifier, visible, name, style, description, width, height, image,
				borderStroke, topConcept, backgroundColor, resizable);
		createEnumModelingAttributes();
	}

	private void createEnumModelingAttributes() {
		addModelingAttribute(VAR_METAENUMNAME, "String", false,
				VAR_METAENUMNAMENAME, "");
		addModelingAttribute(VAR_METAENUMVALUE, "Set", false,
				VAR_METAENUMVALUENAME, VAR_METAENUMVALUECLASS,
				new HashSet<InstAttribute>());

		this.addDisPropEditableAttribute("01#" + VAR_METAENUMNAME);
		this.addDisPropEditableAttribute("02#" + VAR_METAENUMVALUE);

		this.addDisPropVisibleAttribute("01#" + VAR_METAENUMNAME);
		// this.addDisPropVisibleAttribute("02#" + VAR_METAENUMVALUE); //TODO
		// enable when the attribute JPanel gets loaded dynamically

		this.addDisPanelVisibleAttribute("01#" + VAR_METAENUMNAME);
		this.addDisPanelVisibleAttribute("02#" + VAR_METAENUMVALUE);

		this.addDisPanelSpacersAttribute("#" + VAR_METAENUMNAME + "#\n\n");
		this.addDisPanelSpacersAttribute("#" + VAR_METAENUMVALUE + "#\n");
	}

}
