package com.variamos.refas.core.simulationmodel;

import java.util.ArrayList;
import java.util.List;

import com.variamos.refas.core.types.DirectEdgeType;
import com.variamos.syntaxsupport.metamodel.InstVertex;

/**
 * A class to represent the constraints for direct relations. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-13
 */
public class DirectEdgeConstraint extends AbstractConstraint {
	/**
	 * Type of direct Edge from DirectEdgeType enum: Example means_ends
	 */
	private DirectEdgeType directEdgeType;
	/**
	 * The source vertex for the constraint
	 */
	private InstVertex source;
	/**
	 * The target vertex for the constraint
	 */
	private InstVertex target;

	private List<MetaExpression> expressions;
	
	/**
	 * Create the Constraint with all required parameters
	 * @param identifier
	 * @param description
	 * @param directEdgeType
	 * @param source
	 * @param target
	 */
	public DirectEdgeConstraint(String identifier, String description,
			DirectEdgeType directEdgeType, InstVertex source,
			InstVertex target) {
		super(identifier, description);
		this.directEdgeType = directEdgeType;
		this.source = source;
		this.target = target;
		expressions = new ArrayList<MetaExpression>();
	}

	public DirectEdgeType getDirectEdgeType() {
		return directEdgeType;
	}

	public InstVertex getSource() {
		return source;
	}

	public InstVertex getTarget() {
		return target;
	}
}
