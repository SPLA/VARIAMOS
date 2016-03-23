package com.variamos.perspsupport.syntaxsupport;

import java.io.Serializable;

import com.variamos.perspsupport.instancesupport.InstElement;

/**
 * @author Juan Carlos Muñoz 2014 part of the PhD work at CRI - Universite Paris
 *         1
 *
 *         Definition of syntax for VariaMos
 */
public class MetaView extends MetaElement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3876870066100742969L;
	private String paletteName = "";
	private int index;

	public MetaView() {
	}

	public MetaView(String identifier, boolean visible, boolean editable,
			String name, String style, String description, int width,
			int height, String image, int borderStroke, String paletteName,
			int index, InstElement instSemanticElement) {
		super(identifier, visible, editable, name, style, description, width,
				height, image, borderStroke, instSemanticElement);
		this.index = index;
		this.paletteName = paletteName;
	}

	public MetaView(String shortName, String name, String paletteName,
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
