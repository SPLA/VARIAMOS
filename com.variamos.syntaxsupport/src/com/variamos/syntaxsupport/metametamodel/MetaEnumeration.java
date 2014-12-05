package com.variamos.syntaxsupport.metametamodel;

import java.io.Serializable;
import java.util.List;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of syntax for VariaMos
 */
public class MetaEnumeration implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4695459882699621321L;
	private String name;
	private String type;
	private List<MetaValue> values;

	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public List<MetaValue> getValues() {
		return values;
	}
	
}
