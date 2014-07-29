package com.cfm.productline.type;

import java.util.HashSet;
import java.util.Set;

import com.cfm.productline.Variable;


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
	public Variable makeVariable(String name) {
		return new Variable(name, elements.iterator().next(), getIdentifier());
	}
}
