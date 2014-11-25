package com.variamos.syntaxsupport.metametamodel;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupRelation;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class MetaGroupRelation extends MetaElement{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2544731646206260777L;
	private IntSemanticGroupRelation semanticRelation;

	public IntSemanticGroupRelation getSemanticRelation() {
		return semanticRelation;
	}

	public MetaGroupRelation(String identified, String name, String style,
			int width, int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable,IntSemanticGroupRelation semanticRelation,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes, 
			List<String> panelSparerAttributes, Map<String,MetaAttribute> metaAttributes) {
		super(identified, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable, propVisibleAttributes,
				propEditableAttributes, panelVisibleAttributes, panelSparerAttributes,
				metaAttributes);
		this.semanticRelation = semanticRelation;
	}
	
	public MetaGroupRelation(String identified, String name, String style,
			int width, int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable,IntSemanticGroupRelation semanticRelation,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes, 
			List<String> panelSparerAttributes, Map<String,MetaAttribute> metaAttributes,
			List<MetaDirectRelation> metaDirectRelations) {
		super(identified, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable, propVisibleAttributes,
				propEditableAttributes, panelVisibleAttributes, panelSparerAttributes,
				metaAttributes, metaDirectRelations);
		this.semanticRelation = semanticRelation;
	}

	public MetaGroupRelation(String identified, String name, String style,
			int width, int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable, IntSemanticGroupRelation semanticRelation) {
		super(identified, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable);
		this.semanticRelation = semanticRelation;
	}
}
