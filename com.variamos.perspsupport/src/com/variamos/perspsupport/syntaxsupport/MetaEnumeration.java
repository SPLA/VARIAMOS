package com.variamos.perspsupport.syntaxsupport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.variamos.perspsupport.instancesupport.InstAttribute;

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
 * @see com.variamos.perspsupport.syntaxsupport.MetaVertex
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
				borderStroke, null, topConcept, backgroundColor, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes, attributes);
		createEnumModelingAttributes();
	}

	public MetaEnumeration(String identifier, boolean visible, String name,
			String style, String description, int width, int height, String image,
			boolean topConcept, String backgroundColor, int borderStroke,
			boolean resizable) {
		super(identifier, visible, name, style, description, width, height, image,
				borderStroke, null, topConcept, backgroundColor, resizable);
		createEnumModelingAttributes();
	}

	public MetaEnumeration() {
		super();
		createEnumModelingAttributes();
	}

	private void createEnumModelingAttributes() {
		addModelingAttribute(VAR_METAENUMNAME, "String", false,
				VAR_METAENUMNAMENAME, "");
		addModelingAttribute(VAR_METAENUMVALUE, "Set", false,
				VAR_METAENUMVALUENAME, VAR_METAENUMVALUECLASS,
				new ArrayList<InstAttribute>());	

		this.addPropEditableAttribute("01#" + VAR_METAENUMNAME);
		this.addPropEditableAttribute("02#" + VAR_METAENUMVALUE);

		this.addPropVisibleAttribute("01#" + VAR_METAENUMNAME);
		// this.addDisPropVisibleAttribute("02#" + VAR_METAENUMVALUE);
		//TODO enable when the attribute JPanel gets loaded dynamically

		this.addPanelVisibleAttribute("01#" + VAR_METAENUMNAME);
		this.addPanelVisibleAttribute("02#" + VAR_METAENUMVALUE);

		this.addPanelSpacersAttribute("#" + VAR_METAENUMNAME + "#\n\n");
		this.addPanelSpacersAttribute("#" + VAR_METAENUMVALUE + "#\n");
	}
	public Set<String> getAllAttributesNames() 
	{
		return this.getModelingAttributesNames();
	}
	public AbstractAttribute getSemanticAttribute(String name) 
	{
		return null;
	}
}
