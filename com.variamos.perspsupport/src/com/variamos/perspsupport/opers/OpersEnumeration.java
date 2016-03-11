package com.variamos.perspsupport.opers;

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
public class OpersEnumeration {
	private String name;
	private String type;
	private List<OpersValue> values;

	public OpersEnumeration(String name, String type,
			List<OpersValue> values) {
		this.name = name;
		this.type = type;
		this.values = values;

	}

	public OpersEnumeration() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public List<OpersValue> getValues() {
		return values;
	}

}
