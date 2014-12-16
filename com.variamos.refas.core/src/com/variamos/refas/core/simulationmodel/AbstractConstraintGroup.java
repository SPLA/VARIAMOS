package com.variamos.refas.core.simulationmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cfm.hlcl.HlclFactory;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.Identifier;

/**
 * A class to represent the constraints. Part of PhD work at University of Paris
 * 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public abstract class AbstractConstraintGroup {

	/**
	 * Identifier of the contraint: InstEdge Identifier
	 */
	private String identifier;
	/**
	 * Semantic description of the relation
	 */
	private String description;
	/**
	 * 
	 */
	private List<AbstractTransformation> transformations;
	/**
	 * 
	 */
	private Map<String, Identifier> idMap;
	/**
	 * 
	 */
	private HlclFactory hlclFactory;	
	/**
	 * 
	 */
	private boolean optional = false;

	/**
	 * Assign the parameters on the abstract class
	 * 
	 * @param identifier
	 * @param description
	 */
	public AbstractConstraintGroup(String identifier, String description,
			Map<String, Identifier> idMap, HlclFactory hlclFactory) {
		super();
		transformations = new ArrayList<AbstractTransformation>();
		this.idMap = idMap;
		this.hlclFactory = hlclFactory;
		this.identifier = identifier;
		this.description = description;
	}

	protected void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	protected void setOptional(boolean optional) {
		this.optional = optional;
	}

	protected Map<String, Identifier> getIdMap() {
		return idMap;
	}

	protected HlclFactory getHlclFactory() {
		return hlclFactory;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getDescription() {
		return description;
	}
	
	public boolean isOptional() {
		return optional;
	}


	public List<AbstractTransformation> getTransformations() {
		return transformations;
	}

	public List<AbstractTransformation> getTransformationNegations()
	{
		List<AbstractTransformation> out = new ArrayList<AbstractTransformation>();
		for (AbstractTransformation transformation : transformations) {
			idMap.putAll(transformation.getIndentifiers(hlclFactory));
				out.add(transformation.transformationNegation());
		}
		return out;
	}

	public HlclProgram getHlclExpressions() {
		HlclProgram prog = new HlclProgram();
		for (AbstractTransformation transformation : transformations) {
			idMap.putAll(transformation.getIndentifiers(hlclFactory));
			if (transformation instanceof AbstractBooleanTransformation)
				prog.add(((AbstractBooleanTransformation) transformation)
						.transform(hlclFactory, idMap));
			else
				prog.add(((AbstractComparisonTransformation) transformation)
						.transform(hlclFactory, idMap));
		}
		return prog;
	}

}
