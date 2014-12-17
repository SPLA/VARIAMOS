package com.variamos.refas.core.sematicsmetamodel;

/**
 * A class to represent values at the semantic level. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 * @see com.cfm.productline.
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
