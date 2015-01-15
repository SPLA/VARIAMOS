package com.variamos.defectAnalyzer.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.cfm.hlcl.BooleanExpression;

public class CauCos {

	protected List<BooleanExpression> elements = new ArrayList<BooleanExpression>();
	private static Long indx = 1L;
	protected Long id;

	public CauCos() {
		super();
		indx++;
		id = indx;
	}

	
	public CauCos(List<BooleanExpression> elements) {
		this();
		this.elements = elements;
	}

	public int getElementsSize(){
		return elements.size();
	}
	
	public List<BooleanExpression> getElements() {
		return elements;
	}

	public void setElements(List<BooleanExpression> elements) {
		this.elements = elements;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean add(BooleanExpression e) {
		return elements.add(e);
	}

	public void add(int index, BooleanExpression element) {
		elements.add(index, element);
	}

	public boolean addAll(Collection<? extends BooleanExpression> c) {
		return elements.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends BooleanExpression> c) {
		return elements.addAll(index, c);
	}

	@Override
	public String toString() {
		return "CauCos [id=" + id + "] elements size: " + elements.size();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((elements == null) ? 0 : elements.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CauCos other = (CauCos) obj;
		if (elements == null) {
			if (other.elements != null)
				return false;
		} else if (!elements.equals(other.elements))
			return false;
		return true;
	}
}
