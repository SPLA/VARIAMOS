package com.variamos.perspsupport.semanticsupport;

import java.util.List;

/**
 * A class to represent enumerations at semantic level. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-23
 * @see com.cfm.productline.
 */
public class SemanticEnumeration {
	private String name;
	private String type;
	private List<SemanticValue> values;

	public SemanticEnumeration(String name, String type,
			List<SemanticValue> values) {
		this.name = name;
		this.type = type;
		this.values = values;

	}

	public SemanticEnumeration() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public List<SemanticValue> getValues() {
		return values;
	}

}
