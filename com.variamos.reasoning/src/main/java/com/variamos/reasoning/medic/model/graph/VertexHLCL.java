package com.variamos.reasoning.medic.model.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;




/**
 * Class representing a vertex for a constraint network
 * @author Angela Villota <angievig@gmail.com>
 * @version 1.0 we  included the attribute color for the depth first search
 * @since 0
 *
 */

public abstract class VertexHLCL implements Comparable<VertexHLCL>{
	public static final boolean CONSTRAINT_TYPE=true;
	public static final boolean VARIABLE_TYPE=false;
	public static final int VISITED=2;
	public static final int INSTACK=1;
	public static final int NOT_VISITED=0;
	private  String id;
	private HlclProgram constraints;


	private List <VertexHLCL> neighbors;
	private int searchState;
	private VertexHLCL parent;
	private int order;
	




	public int getOrder() {
		return order;
	}



	public void setOrder(int order) {
		this.order = order;
	}



	public void initialize(String id){
		this.id=id;
		neighbors= new LinkedList<VertexHLCL>(); 
		constraints= new HlclProgram();
		searchState= NOT_VISITED;
		parent= null;
	}
	

	
	public String getId(){
		return id;
	}
	//FIXME fix the return
	public boolean addNeighbor(VertexHLCL v){
		neighbors.add(v);
		return true;
	}
	
	public Collection<VertexHLCL> getNeighbors(){
		return neighbors;
	}
	
	public int compareTo(VertexHLCL o) {
		 return id.compareTo(o.getId());
	}
	
	public int getSearchState() {
		return searchState;
	}

	public void setSearchState(int searchState) {
		this.searchState = searchState;
	}
	
	public VertexHLCL getParent() {
		return parent;
	}

	public void setParent(VertexHLCL parent) {
		this.parent = parent;
	}
	

	
	public abstract VertexHLCL clone();
	/**
	 * 
	 */
//	public boolean equals(Object o){
//		Vertex v = (Vertex) o;
//		return v.equals(id);
//		
//	}
	
	public HlclProgram getConstraints(){
		return constraints;
	}


	public void setConstraints(HlclProgram constraints) {
		this.constraints = constraints;
	}
	public boolean addConstraint(IntBooleanExpression c){
		return constraints.add(c);
	}
	


	
}
