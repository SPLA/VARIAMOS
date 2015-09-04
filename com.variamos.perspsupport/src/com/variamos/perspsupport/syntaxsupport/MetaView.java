package com.variamos.perspsupport.syntaxsupport;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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

	public String getPaletteName() {
		return paletteName;
	}

	private int index;

	public MetaView() {
	}

	public MetaView(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, String paletteName, int index,
			InstElement instSemanticElement) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke, instSemanticElement);
		this.index = index;
		this.paletteName = paletteName;
	}

	public MetaView(String shortName, String name, String paletteName,
			int index, InstElement instSemanticElement) {
		super(shortName, true, name, "", "", 100, 30, "", 1,
				instSemanticElement);
		this.index = index;
		this.paletteName = paletteName;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public Set<String> getAllAttributesNames(List<InstElement> parents) {
		return null;
	}

	@Override
	public AbstractAttribute getSemanticAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
