package com.variamos.reasoning.medic.model.graph;


import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.model.expressions.Identifier;





public class NodeVariableHLCL extends VertexHLCL {
	
	private Collection <NodeConstraintHLCL> neighbors;
	private ArrayList<NodeConstraintHLCL> unary;
	private Identifier var;
		
	
	public NodeVariableHLCL (Identifier v){
		var=v;
		initialize(var.getId());
		unary= new ArrayList<NodeConstraintHLCL>();
	}
	
	public Identifier getVar(){
		return var;
	}
	

	
	public NodeVariableHLCL clone(){
		NodeVariableHLCL clon= new NodeVariableHLCL(this.var);
		clon.setConstraints(this.getConstraints());
		clon.setUnary(this.unary);
		
		
		return clon;
	}
	
	public String toString(){
		return getId();
	}
	
	@Override
	public HlclProgram getConstraints(){
		HlclProgram constraints= new HlclProgram();
		
		for (NodeConstraintHLCL un : unary) {
			constraints.add(un.getConstraint());
		}
		return constraints;
	}
	
	public Boolean addUnary(NodeConstraintHLCL cons){
		return unary.add(cons);
	}
	
	public ArrayList<NodeConstraintHLCL> getUnary(){
		return unary;
	}
	public void setUnary(ArrayList<NodeConstraintHLCL> unary){
		this.unary=unary;
	}




}
