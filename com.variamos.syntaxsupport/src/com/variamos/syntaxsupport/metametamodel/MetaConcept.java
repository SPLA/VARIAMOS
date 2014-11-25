package com.variamos.syntaxsupport.metametamodel;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticConcept;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class MetaConcept extends MetaElement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8416609016211900965L;
	private IntSemanticConcept semanticConcept;

	public IntSemanticConcept getSemanticConcept() {
		return semanticConcept;
	}
	
	public MetaConcept()
	{
		super();
		
	}
	
	public void setSemanticConcept(IntSemanticConcept semanticConcept) {
		this.semanticConcept = semanticConcept;
	}

	public MetaConcept(String identified, String name, String style, int width,
			int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable, 
			IntSemanticConcept semanticConcept,
			List<String> propVisibleAttributes,
			List<String> propEditableAttributes,
			List<String> panelVisibleAttributes,
			List<String> panelSparerAttributes, Map<String,MetaAttribute> attributes) {
		super(identified, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable,
				propVisibleAttributes, propEditableAttributes,
				panelVisibleAttributes, panelSparerAttributes, attributes);
		this.semanticConcept = semanticConcept;
	}

	public MetaConcept(String identified, String name, String style, int width,
			int height, String image, boolean topConcept,
			String backgroundColor, int borderStroke, boolean resizable,
			IntSemanticConcept semanticConcept) {
		super(identified, name, style, width, height, image, topConcept,
				backgroundColor, borderStroke, resizable);
		this.semanticConcept = semanticConcept;
	}

}
