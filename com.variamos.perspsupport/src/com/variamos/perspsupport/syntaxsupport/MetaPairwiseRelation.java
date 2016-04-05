package com.variamos.perspsupport.syntaxsupport;

import java.util.List;

import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.opers.OpersPairwiseRel;
import com.variamos.perspsupport.opersint.IntOpersRelType;

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
 * @see com.variamos.perspsupport.syntaxsupport.MetaElement * @see
 *      com.variamos.semantic.semanticsupport.SemanticPairwiseRelation
 */
public class MetaPairwiseRelation extends MetaElement {
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

	public MetaPairwiseRelation() {

	}

	public MetaPairwiseRelation(String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke,
			InstElement instSemanticElement) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement);
	}

	public MetaPairwiseRelation(String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, null);
	}

	public List<IntOpersRelType> getSemanticRelationTypes() {
		if (getTransInstSemanticElement() == null)
			return null;
		return ((OpersPairwiseRel) getTransInstSemanticElement()
				.getEditableSemanticElement()).getSemanticRelationTypes();
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