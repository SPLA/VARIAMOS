package com.variamos.syntaxsupport.metamodelsupport;

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
		this.index = index;
		this.elements = new HashSet<MetaElement>();
	}

	public MetaView(String shortName, String name, String paletteName, int index) {
		super(shortName, true, name, "","",100,30,"",1);
		this.childViews = new ArrayList<MetaView>();
		this.index = index;
		this.paletteName = paletteName;
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

	public String getDescription() {
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
