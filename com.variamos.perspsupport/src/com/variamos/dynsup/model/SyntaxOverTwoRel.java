package com.variamos.dynsup.model;

import java.util.List;
import java.util.Map;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.interfaces.IntOpersOverTwoRel;
import com.variamos.dynsup.interfaces.IntOpersRelType;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class SyntaxOverTwoRel extends SyntaxVertex {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2544731646206260777L;
	// new
	// private IntSemanticOverTwoRelation semanticOverTwoRelation;

	@Deprecated
	public static final String VAR_SEMANTICPAIRWISEREL = "semPairwiseRel";

	public SyntaxOverTwoRel() {
		super();
	}

	public SyntaxOverTwoRel(String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke,
			InstElement instSemanticElement, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, ElemAttribute> modelingAttributes) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable, propVisibleAttributes,
				propEditableAttributes, panelVisibleAttributes,
				panelSparerAttributes, modelingAttributes);
	}

	public SyntaxOverTwoRel(String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, int borderStroke,
			InstElement instSemanticElement, boolean topConcept,
			String backgroundColor, boolean resizable,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes,
			Map<String, ElemAttribute> modelingAttributes,
			List<SyntaxPairwiseRel> asOriginRelations,
			List<SyntaxPairwiseRel> asDestinationRelations) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable, propVisibleAttributes,
				propEditableAttributes, panelVisibleAttributes,
				panelSparerAttributes, modelingAttributes, asOriginRelations,
				asOriginRelations);
	}

	public SyntaxOverTwoRel(String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke,
			InstElement instSemanticElement, boolean resizable) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable);
	}

	// new
	public SyntaxOverTwoRel(String identifier, boolean visible,
			boolean editable, String name, String style, String description,
			int width, int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke,
			InstElement instSemanticElement, boolean resizable,
			IntOpersOverTwoRel intSemanticOverTwoRelation) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement, topConcept,
				backgroundColor, resizable);
	}

	public List<IntOpersRelType> getSemanticRelationTypes() {
		return ((OpersOverTwoRel) getTransInstSemanticElement()
				.getEditableSemanticElement()).getSemanticRelationTypes();
	}

	public char getType() {
		return 'O';
	};
}
