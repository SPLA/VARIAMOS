package com.variamos.gui.refas.editor;

import com.variamos.syntaxsupport.metametamodel.MetaConcept;
import com.variamos.syntaxsupport.metametamodel.MetaElement;
import com.variamos.syntaxsupport.metametamodel.MetaVertex;

public class PaletteElement {

	private String id;
	private String elementTitle;
	private String icon;
	private String style;
	private int height;
	private int width;
	private String className;
	//jcmunoz
	private MetaElement metaElement;

	public MetaElement getMetaElement() {
		return metaElement;
	}

	public PaletteElement(String id, String elementTitle, String icon,
			String style, int width, int height, String className) {		
		this(id, elementTitle, icon, style, height, width, className, null);
	}
	
	public PaletteElement(String id, String elementTitle, String icon,
			String style, int width, int height, String className, MetaElement metaElement) {		
		this.id = id;
		this.elementTitle = elementTitle;
		this.icon = icon;
		this.style = style;
		this.height = height;
		this.width = width;
		this.className = className;
		this.metaElement = metaElement;
	}

	public String getId() {
		return id;
	}

	public String getElementTitle() {
		return elementTitle;
	}

	public String getIcon() {
		return icon;
	}

	public String getStyle() {
		return style;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public String getClassName() {
		return className;
	}

	public boolean equals(Object other) {
		return ((PaletteElement) other).getIcon().equals(id);

	}

}
