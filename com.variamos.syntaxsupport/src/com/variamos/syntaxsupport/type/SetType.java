package com.variamos.syntaxsupport.type;

import java.util.HashSet;
import java.util.Set;

import com.variamos.syntaxsupport.metametamodel.AbstractAttribute;


@SuppressWarnings("serial")
public class SetType extends Type{
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

//	@Override
//	public boolean contains(Object obj) {
//		return elements.contains(obj);
//	}

	@Override
	public AbstractAttribute makeAttribute(String name) {
		return new AbstractAttribute(name, elements.iterator().next(), getIdentifier());
	}
}
