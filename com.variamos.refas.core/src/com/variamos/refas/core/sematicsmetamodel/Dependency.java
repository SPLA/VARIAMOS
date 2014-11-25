package com.variamos.refas.core.sematicsmetamodel;

import java.io.Serializable;

/**
 * @author Juan Carlos Muñoz 2014
 *  part of the PhD work at CRI - Universite Paris 1
 *
 * Definition of semantics for REFAS
 */
public class Dependency implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6451557207632365209L;
	protected GroupRelationType type;

	public Dependency (GroupRelationType type)
	{
		this.type = type;
	}

	public String getType() {
		return type.name();
	}
}
