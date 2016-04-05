package com.variamos.dynsup.model;

import java.util.List;
import java.util.Map;

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
 * @see com.variamos.dynsup.model.SyntaxVertex
 */
public class SyntaxConcept extends SyntaxVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8416609016211900965L;
	private char type;

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
		super(identifier, visible, editable, name, style, description, width,
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
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable);
		this.type = type;
	}

	public char getType() {
		return type;
	}

}
