package com.variamos.syntaxsupport.type;

import java.util.HashSet;
import java.util.Set;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;

/**
 * A class to represent a set dynamically loaded attribute. Based on SetType
 * of ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-12-01
 * @see com.cfm.productline.type.SetType
 */
@SuppressWarnings("serial")
public class SetType extends Type {
	public static final String IDENTIFIER = "Set";

	protected SetType() {
		super(IDENTIFIER);
	}

	private Set<String> elements = new HashSet<>();

	public boolean add(String element) {
		return elements.add(element);
	}

	public int size() {
		return elements.size();
	}

	public Set<String> getElements() {
		return elements;
	}

	public void setElements(Set<String> elements) {
		this.elements = elements;
	}

	// @Override
	// public boolean contains(Object obj) {
	// return elements.contains(obj);
	// }

	@Override
	public AbstractAttribute makeAttribute(String name,
			boolean affectProperties, String displayName) {
		return new AbstractAttribute(name, elements.iterator().next(),
				affectProperties, displayName, getIdentifier());
	}
}
