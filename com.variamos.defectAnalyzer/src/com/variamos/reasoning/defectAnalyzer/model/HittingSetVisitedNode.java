package com.variamos.reasoning.defectAnalyzer.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Almacena un historico del estado del algoritimo recursivo que construye los
 * hittings sets, esto con el fin de evitar recorrer en la recursión estados
 * previamente visitados
 * 
 * @author LuFe
 * 
 */
public class HittingSetVisitedNode<T> {

	private List<T> actualHittingSet = new ArrayList<T>();
	private Collection<T> elementsToAdd = new ArrayList<T>();
	
	

	public HittingSetVisitedNode(List<T> actualHittingSet, Collection<T> elementsToAdd) {
		super();
		this.actualHittingSet = actualHittingSet;
		this.elementsToAdd = elementsToAdd;
	}

	/**
	 * @return the actualHittingSet
	 */
	public List<T> getActualHittingSet() {
		return actualHittingSet;
	}

	/**
	 * @param actualHittingSet
	 *            the actualHittingSet to set
	 */
	public void setActualHittingSet(List<T> actualHittingSet) {
		this.actualHittingSet = actualHittingSet;
	}

	/**
	 * @return the elementsToAdd
	 */
	public Collection<T> getElementsToAdd() {
		return elementsToAdd;
	}

	/**
	 * @param elementsToAdd the elementsToAdd to set
	 */
	public void setElementsToAdd(Collection<T> elementsToAdd) {
		this.elementsToAdd = elementsToAdd;
	}

	

}
