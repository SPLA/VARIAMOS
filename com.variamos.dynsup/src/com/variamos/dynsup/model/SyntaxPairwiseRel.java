package com.variamos.dynsup.model;

import com.variamos.dynsup.instance.InstElement;

/**
 * A class to represented edges of the meta model. Extends from MetaElement
 * adding the allowed semantic concepts and edge types. An dynamic attribute for
 * selected semantic concept Part of PhD work at University of Paris 1 Refactor:
 * Merged with MetaEdge
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-10
 * @see com.variamos.dynsup.model.SyntaxElement * @see
 *      com.variamos.dynsup.model.OpersPairwiseRel
 */
public class SyntaxPairwiseRel extends SyntaxElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2665541567934067387L;
	private String palette = "";

	/**
	 * 
	 */
	// private IntSemanticPairwiseRelation semanticPairwiseRelation;

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	public SyntaxPairwiseRel() {

	}

	public SyntaxPairwiseRel(String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke,
			InstElement instSemanticElement) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement);
	}

	public SyntaxPairwiseRel(String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, null);
	}

	public String toString() {
		return "MetaGroupRelation";
	}

	public static String getClassId() {
		return "E";
	}

	public char getType() {
		return 'P';
	};
}