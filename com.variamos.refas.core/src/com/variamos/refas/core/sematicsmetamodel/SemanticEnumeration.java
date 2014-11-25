package com.variamos.refas.core.sematicsmetamodel;

import java.util.List;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class SemanticEnumeration {

	 public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public List<SemanticValue> getValues() {
		return values;
	}

	private String name;
	 private String type;
	 private List<SemanticValue> values;
	 
	 public SemanticEnumeration(String name, String type, List<SemanticValue> values)
	 {
		 this.name = name;
		 this.type = type;
		 this.values = values;
		 
	 }
}
