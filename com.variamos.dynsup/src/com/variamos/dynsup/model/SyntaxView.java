package com.variamos.dynsup.model;

import java.io.Serializable;

import com.variamos.dynsup.instance.InstElement;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class SyntaxView extends SyntaxElement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3876870066100742969L;
	private String paletteName = "";
	private int index;

	public SyntaxView() {
	}

	public SyntaxView(String identifier, boolean visible, boolean editable,
			String name, String style, String description, int width,
			int height, String image, int borderStroke, String paletteName,
			int index, InstElement instSemanticElement) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement);
		this.index = index;
		this.paletteName = paletteName;
	}

	public SyntaxView(String shortName, String name, String paletteName,
			int index, InstElement instSemanticElement) {
		super(shortName, true, true, name, "", "", 100, 30, "", 1,
				instSemanticElement);
		this.index = index;
		this.paletteName = paletteName;
	}

	public int getIndex() {
		return index;
	}

	public char getType() {
		return 'V';
	};

	public String getPaletteName() {
		return paletteName;
	}

	public void setPaletteName(String paletteName) {
		this.paletteName = paletteName;
	}

}
