package com.variamos.reasoning.medic.model.graph;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import com.variamos.hlcl.core.HlclUtil;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;




/**
 * Esta es la clase que implementa una constraint network
 * using an adjacencies list.
 * Un vÃ©rtice en esta clase es una variable o una constraint
 * Edges in this class represent the aparition of a constraint containing a variable
 * @author Angela Villota <Angela Villota>
 *
 */
public class ConstraintGraphHLCL {
	
	
	//private UndirectedSparseGraph<Vertex,String> graph;
	private HashMap<String,NodeConstraintHLCL> constraints;
	private HashMap<String,NodeVariableHLCL> variables;
	/**
	 * ConstraintsCount is the Amount of constraints in the problem, this may be different than the amount
	 *  of vertices in the network.
	 */
	private int constraintsCount;
	/**
	 * variablesCount is the Amount of variables in the problem, this may be different than the amount
	 *  of vertices in the network.
	 */
	private int variablesCount;
	
	private int constraintVertex;
	
	private int edges;
	private int vertices;
	
	public ConstraintGraphHLCL(){
		constraintVertex= 0;
		constraintsCount=0;
		variablesCount=0;
		variables=new HashMap<String,NodeVariableHLCL>();
		constraints=new HashMap<String,NodeConstraintHLCL>();
		//amount of vertices and edges in a graph
		edges=0;
		vertices=0;
		
	}
	

	
	/**
	 * For include a vertex in the network
	 * first the type of the vertex is determined, to include it in the collection
	 * @param v
	 */
	public void addVertex(VertexHLCL v){
		
		if (v instanceof NodeConstraintHLCL){
			constraints.put(v.getId(), (NodeConstraintHLCL) v);
			constraintsCount++;
		}

        else if (v instanceof NodeVariableHLCL){
        	variables.put(v.getId(), (NodeVariableHLCL) v);
        	variablesCount++;
        }
	}
	
	/**
	 * v1 y v2 are vertices, they can't be the same type
	 * v1 and v2 are vertices in the graph
	 * @param v1
	 * @param v2
	 * @throws Exception when the two vertices ae the same type
	 */
	public void addEdge(VertexHLCL v1, VertexHLCL v2) throws Exception{
		if ((v1 instanceof NodeConstraintHLCL && v2 instanceof NodeVariableHLCL) ||
				(v1 instanceof NodeVariableHLCL && v2 instanceof NodeConstraintHLCL)  ){
			
			v1.addNeighbor(v2);
			v2.addNeighbor(v1);
			edges++;
		}else{
			throw new Exception("An error occured when adding an edge containing two vertices of the same type, vertices: " 
		                         + v1.getId() + " , "+ v2.getId());
		}
		
		
	}
	
	
	
	
	/**
	 * 
	 * @param expression, es una expresion con la restricciÃ³n.  En la versiÃ³n 0 es un string.
	 * @param vars debe ser una lista o arreglo de expresiones de variables.  
	 * En la versiÃ³n 0 las expresiones variable son objetos de la clase Variable
	 */
	public void addConstraint(IntBooleanExpression cons){
		constraintsCount++; //number of constraints in the problem, could be different than the number of nodes 
		String id="C"+constraintsCount ;  //the id of the node constraint is a consecutive
		NodeConstraintHLCL newNode =null; //the new constraint node
		
		Set<Identifier> vars=  HlclUtil.getUsedIdentifiers(cons);
		

		NodeConstraintHLCL node= constraints.get(id);
	
		if (node!=null){
			//FIXME revisar que pasa si hay mas de una cons con el mismo id  

			}
		/*
		 * Si el nodo constraint no existe, entonces 
		 */
		else{
			newNode= new NodeConstraintHLCL(id, cons);  // new node
			// for each variable ( identifier expression)
			for (Identifier identifier : vars) {
				//search the variable in the map
				NodeVariableHLCL var= variables.get(identifier.getId());
				// if the variable does not exits, then create the variable, and add the variable to the map
				if (var==null){
					var= new NodeVariableHLCL(identifier);
					variables.put(identifier.getId(), var);
					variablesCount++;
					//System.out.println("adding variable: "+var.toString());					
				}
				// in all cases, add the new constraint to the variables neighbors
				// add all variables to the constraint neighbors
				var.addNeighbor(newNode);
				newNode.addNeighbor(var);
				edges++;
			}
			constraints.put(id, newNode);
			//System.out.println("adding constraint: "+newNode.toString());
		}
		
	}
	
	
	public boolean addUnaryConstraint(IntBooleanExpression cons, Identifier var){
		constraintsCount++;
		boolean exit=false;
		String id="C"+constraintsCount+"_Unary_"+ var.getId() ;  //the id of the node constraint is a consecutive
		NodeVariableHLCL varN=variables.get(var.getId());
		NodeConstraintHLCL newC = new NodeConstraintHLCL(id, cons);
		// case 1: the variable exists
		if (varN!=null){
			exit= varN.addUnary(newC);
			
			edges++;
			//System.out.println("adding unary constraint to existent var: "+varN.toString());

		}
		//case 2: the variable does not exists
		else{
			varN= addVariable(var);
			exit= varN.addUnary(newC);
			//constraintsCount++;
			edges++;
			//System.out.println("adding unary constraint to a new var: "+varN.toString());

			
		}
		return exit;
		
	}
	
	/**
	 * 
	 * @param id variable's identifier 
	 * @param domain name of the domain
	 */
	public NodeVariableHLCL addVariable(Identifier var){
		
		NodeVariableHLCL varNode = null;
		//System.out.println(var.getId());
		if (!variables.containsKey(var.getId())){
			varNode = new NodeVariableHLCL(var);
			variables.put(var.getId(), varNode);
			variablesCount++;
		}
		//System.out.println("adding variable " + varNode.toString());
	
		return varNode;
	}
	
	public int getConstraintsCount(){
		return constraintsCount;
	}
	
	public int getVariablesCount(){
		return variablesCount;
	}
	
	public int constraintsSize(){
		return constraints.size();
	}
	public int variablesSize(){
		return variables.size();
	}
	
	
	//Cambios aquÃ­-------------------------------------------------------
	public Collection<VertexHLCL> getNeighbors(String id, boolean type){
		Collection<VertexHLCL> tmp= null;
		if (type==VertexHLCL.CONSTRAINT_TYPE)
			tmp= constraints.get(id).getNeighbors();
		
		else
			tmp= variables.get(id).getNeighbors();
		
		return tmp;
	}
	
	public HashMap<String,NodeVariableHLCL> getVariables(){
		return variables;
	}  
	public HashMap<String,NodeConstraintHLCL> getConstraints(){
		return constraints;
	} 
	
	public VertexHLCL getVertex(String id){
		//TODO fix here
		VertexHLCL ver= variables.get(id);
		if(ver==null){
			ver=constraints.get(id);
		}
		 
		return ver;
	}
	
	public void printNetwork(ConstraintGraphHLCL net){
		System.out.print("\nConstraint network: \n");
		System.out.print("Total vertices: "+net.numVertices()+
				           " vars: "+net.getVariablesCount()+
				           " cons: "+net.getConstraintsCount()+
				           " Total edges: "+net.numEdges()+  "\n");
		
		System.out.print("Problem variables\n");
		 
		 for (String id : variables.keySet()) {
			 System.out.print("adjacent nodes of variable "+ id + ": " );
			 NodeVariableHLCL var = variables.get(id);
			 for (VertexHLCL v: var.getNeighbors()){
				 System.out.print(v.getId()+",  ");	 
			 }
			 System.out.print("\n");
			 System.out.print("unary constraints: \n" );
			 for (IntBooleanExpression unary :  var.getConstraints()) {
				 System.out.print(unary.toString()+"\n");	 
			}
		 }
			 
			 
		
			
	
		 
		//neighbors for constraint nodes
		 System.out.print("Problem constraints\n");
		 
		 for (String id : constraints.keySet()) {
			 System.out.print("adjacent nodes of constraint "+ id + ": " );
			 for (VertexHLCL v: net.getNeighbors(id, VertexHLCL.CONSTRAINT_TYPE)){
				 System.out.print(v.getId()+",  ");	 
			 }
			 System.out.print("\n");
		}
	}

	public int numEdges(){
		return edges;
	}
	
	public int numVertices(){
		return constraintsCount+variablesCount;
	}
	
	public String getMaxRoot(){
		String answer ="";
		int max= -1;
		NodeVariableHLCL maxVar=null;
		
		for (String varId : variables.keySet()) {
			NodeVariableHLCL var= variables.get(varId);
			int numAdjacents = var.getNeighbors().size()+ var.getUnary().size();
			if( numAdjacents >max){
				max = numAdjacents;
				maxVar= var;
			}
		}
		answer= maxVar.getId();
		return answer;
	}
	
	public String getRandRoot(){
		String answer ="";
		Object[] keys = variables.keySet().toArray();
		int min=0;
		int max= keys.length;
		Random random = new Random();
		int rand= random.nextInt(max - min + 1) + min;
		answer= (String) keys[rand];
		return answer;
	}
	
	public String getMinRoot(){
		String answer ="";
		int min= 2000000;
		NodeVariableHLCL minVar=null;
		
		for (String varId : variables.keySet()) {
			NodeVariableHLCL var= variables.get(varId);
			int numAdjacents = var.getNeighbors().size()+ var.getUnary().size();
			if( numAdjacents <min){
				min = numAdjacents;
				minVar= var;
			}
		}
		answer= minVar.getId();
		return answer;
	}
	
}
