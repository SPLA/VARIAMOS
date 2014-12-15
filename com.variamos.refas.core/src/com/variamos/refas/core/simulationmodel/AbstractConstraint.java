package com.variamos.refas.core.simulationmodel;

/**
 * A class to represent the constraints. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public abstract class AbstractConstraint {
	
	/**
	 * Identifier of the contraint: InstEdge Identifier
	 */
	private String identifier;
	/**
	 * Semantic description of the relation
	 */
	private String description;
	/**
	 * Assign the parameters on the abstract class
	 * @param identifier
	 * @param description
	 */
	public AbstractConstraint(String identifier, String description) {
		super();
		this.identifier = identifier;
		this.description = description;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public String getDescription() {
		return description;
	}
	
}
