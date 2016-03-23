package com.variamos.perspsupport.syntaxsupport;

import java.util.List;
import java.util.Map;

import com.variamos.perspsupport.instancesupport.InstElement;

/**
 * A class to represented concepts of the meta model. Extends from MetaVertex
 * adding the SemanticConcept and extend relations. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.perspsupport.syntaxsupport.MetaElement
 * @see com.variamos.perspsupport.syntaxsupport.MetaVertex
 */
public class MetaConcept extends MetaVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8416609016211900965L;
	private char type;

	public MetaConcept(char type) {
		super();
		this.type = type;
	}

	public MetaConcept(char type, String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke,
			InstElement instSemanticElement, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, AbstractAttribute> attributes) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable, propVisibleAttributes,
				propEditableAttributes, panelVisibleAttributes,
				panelSparerAttributes, attributes);
		this.type = type;
	}

	public MetaConcept(char type, String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke,
			InstElement instSemanticElement, boolean resizable) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable);
		this.type = type;
	}

	public char getType() {
		return type;
	}

}
