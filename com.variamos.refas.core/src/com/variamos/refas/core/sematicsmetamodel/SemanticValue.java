package com.variamos.refas.core.sematicsmetamodel;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class SemanticValue {
	private Object value;
	
	public Object getValue() {
		return value;
	}

	public SemanticValue(Object value)
	{
		this.value = value;
	}
}
