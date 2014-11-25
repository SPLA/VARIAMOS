package com.variamos.refas.core.sematicsmetamodel;

import java.io.Serializable;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class SemanticAttribute implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 2582154145921483304L;
	private String name;
	 private String type;
	 private boolean mandatory;
	 
	 public SemanticAttribute(String name, String type, boolean mandatory)
	 {
		 this.name = name;
		 this.type = type;
		 this.mandatory = mandatory;
		 
	 }
		
	public String getType() {
		return type;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public String getName()
		{

			return name;
		}
}
