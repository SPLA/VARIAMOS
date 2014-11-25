package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class MetaValue implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -3075458834250102102L;
private Object value;

public Object getValue() {
	return value;
}
}
