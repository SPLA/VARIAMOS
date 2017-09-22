package com.variamos.reasoning.medic.model.graph;


import java.util.ArrayList;
import java.util.TreeSet;

import com.variamos.hlcl.model.expressions.Identifier;





public class NodeVariableHLCL extends VertexHLCL {
	
	private TreeSet <NodeConstraintHLCL> neighbors;
	private Identifier var;
	//private HlclProgram constraints;
	
	
	public NodeVariableHLCL (Identifier v){
		var=v;
		//constraints= new HlclProgram();
		initialize(var.getId());
	}
	
	public Identifier getVar(){
		return var;
	}
	

	
	public NodeVariableHLCL clone(){
		//NodeConstraint clon= new NodeConstraint(this.getId(), this.getConstraints());
		NodeVariableHLCL clon= new NodeVariableHLCL(this.var);
		clon.setConstraints(this.getConstraints());
		
		return clon;
	}
	
	public String toString(){
		return getId();
	}

	




}
