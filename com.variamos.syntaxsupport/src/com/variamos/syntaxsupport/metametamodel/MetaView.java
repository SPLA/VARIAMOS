package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.variamos.syntaxsupport.semanticinterface.IntSemanticConcept;

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
	private String shortName;
	private String name;
	private String paletteName;
	private int index;
	private List<MetaView> childViews;
	private Set<MetaElement> elements;

	public MetaView(String identifier, boolean visible, String name,
			String style, String description, int width, int height,
			String image, int borderStroke, String paletteName, int index) {
		super(identifier, visible, name, style, description, width, height,
				image, borderStroke);
		this.childViews = new ArrayList<MetaView>();
		this.shortName = identifier;
		this.name = name;
		this.paletteName = paletteName;
		this.index = index;
		this.elements = new HashSet<MetaElement>();
	}

	public MetaView(String shortName, String name, String paletteName, int index) {
		super();
		this.childViews = new ArrayList<MetaView>();
		this.shortName = shortName;
		this.name = name;
		this.paletteName = paletteName;
		this.index = index;
		this.elements = new HashSet<MetaElement>();
	}

	public void addConcept(MetaElement sTopGoal) {
		elements.add(sTopGoal);

	}

	public List<MetaView> getChildViews() {
		return childViews;
	}

	public void setChildViews(List<MetaView> childViews) {
		this.childViews = childViews;
	}

	public String getShortName() {
		return shortName;
	}

	public String getName() {
		return name;
	}

	public String getPaletteName() {
		return paletteName;
	}

	public int getIndex() {
		return index;
	}

	public Set<MetaElement> getElements() {
		return elements;
	}

	public void addChildView(MetaView metaChildView) {
		// if (childViews.size()==0)
		// childViews.add(this);
		childViews.add(metaChildView);
	}

}
