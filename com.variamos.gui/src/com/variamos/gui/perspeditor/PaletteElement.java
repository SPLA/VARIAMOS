package com.variamos.gui.perspeditor;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.model.SyntaxElement;

public class PaletteElement {

	private String id;
	private String elementTitle;
	private String icon;
	private String style;
	private int height;
	private int width;
	private String className;
	// jcmunoz
	private SyntaxElement metaElement;
	private InstElement instElement;

	public SyntaxElement getMetaElement() {
		return metaElement;
	}

	public InstElement getInstElement() {
		return instElement;
	}

	public PaletteElement(String id, String elementTitle, String icon,
			String style, int width, int height, String className) {
		this(id, elementTitle, icon, style, height, width, className, null,
				null);
	}

	public PaletteElement(String id, String elementTitle, String icon,
			String style, int width, int height, String className,
			SyntaxElement metaElement, InstElement instElement) {
		this.id = id;
		this.elementTitle = elementTitle;
		this.icon = icon;
		this.style = style;
		this.height = height;
		this.width = width;
		this.className = className;
		this.metaElement = metaElement;
		this.instElement = instElement;
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
