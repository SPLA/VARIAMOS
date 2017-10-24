package com.variamos.reasoning.medic.model.graph;



import java.util.TreeSet;


import com.variamos.hlcl.model.expressions.IntBooleanExpression;
//import com.variamos.hlcl.HlclProgram;

//import com.variamos.hlcl.BooleanExpression;



public class NodeConstraintHLCL extends VertexHLCL {
	private IntBooleanExpression constraint;

	/**
	 * Creates a new constraint node.  
	 * The id in a constraint node is formed by concatening variables id's 
	 * @param id String, an id 
	 */
	//private Constraint cons;
	private TreeSet <NodeVariableHLCL> neighbors;
	
	//FIXME el ide debe ser el id de la interfaz de variamos
	
	public IntBooleanExpression getConstraint(){
		return constraint;
	}
	public NodeConstraintHLCL (String id, IntBooleanExpression c){
		initialize(id);
		constraint= c;
		addConstraint(c);
		//cons= c;
	}
	
	
	public NodeConstraintHLCL clone(){
		NodeConstraintHLCL clon= new NodeConstraintHLCL(this.getId(), this.constraint);
		return clon;
				
	}
	
	public String toString(){
		return getId()+ " "+ constraint.toString();
	}
	



}
