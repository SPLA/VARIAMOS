package com.variamos.reasoning.medic.model.graph;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.variamos.hlcl.core.HlclUtil;
import com.variamos.hlcl.model.expressions.Identifier;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;




/**
 * Esta es la clase que implementa una constraint network
 * using an adjacencies list.
 * Un vértice en esta clase es una variable o una constraint
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
		//graph= new UndirectedSparseGraph<Vertex,String>();
	}
	

	
	/**
	 * For include a vertex in the network
	 * first the type of the vertex is determined, to include it in the collection
	 * @param v
	 */
	public void addVertex(VertexHLCL v){
		
		//FIXME acomodar aqu� los llamados al constructor de cada uno de los v�rtices
		if (v instanceof NodeConstraintHLCL){
			//Constraint c = 
			//addConstraint(((NodeConstraintHLCL) v).getConstraint());
			//System.out.println();
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
	 * @param expression, es una expresion con la restricción.  En la versión 0 es un string.
	 * @param vars debe ser una lista o arreglo de expresiones de variables.  
	 * En la versión 0 las expresiones variable son objetos de la clase Variable
	 */
	public void addConstraint(IntBooleanExpression cons){
		constraintsCount++; //number of constraints in the problem, could be different than the number of nodes 
		String id="C"+constraintsCount ;  //the id of the node constraint is a consecutive
		NodeConstraintHLCL newNode =null; //the new constraint node
		
		Set<Identifier> vars=  HlclUtil.getUsedIdentifiers(cons);
		
//		String expression = cons.getExpression();

//		//The id is formed by concatening the names of vars
//		for (int i = 0; i < vars.size(); i++) {
//			id+=vars.get(i).getId();
//		}
//		// Th id is sorted to avoid anagrams
//		char[] array= id.toCharArray();
//		Arrays.sort(array);
//		String sortedId= new String(array);
		NodeConstraintHLCL node= constraints.get(id);
		
		
		//if there is a constraint node with same set of variables, 
		//then add the constraint expression into the list of constraints
		//also, if there is a constraint node it means that the nodes corresponding the variables in the constraint exist.
//		
		if (node!=null){
			//FIXME revisar que pasa si hay mas de una cons con el mismo id  
//			node.addConstraint(cons);
			}
		/*
		 * Si el nodo constraint no existe, entonces 
		 */
		else{
			newNode= new NodeConstraintHLCL(id, cons);  // new node
			// for each variable expression
			//TODO old code
//			for (int i = 0; i < vars.size(); i++) {
//			
//				NodeVariableHLCL var= variables.get(vars.get(i).getId());
//				// if the variable does not exits, then create the variable.
//				if (var==null){
//					var= new NodeVariableHLCL(vars.get(i));
//				}
//				// in all cases, add the new constraint to the variables neighbors
//				// add all variables to the constraint neighbors
//				var.addNeighbor(newNode);
//				newNode.addNeighbor(var);
//				edges++;
//			}
			// for each variable ( identifier expression)
			for (Identifier identifier : vars) {
				//search the variable in the map
				NodeVariableHLCL var= variables.get(identifier.getId());
				// if the variable does not exits, then create the variable, and add the variable to the map
				if (var==null){
					var= new NodeVariableHLCL(identifier);
					variables.put(identifier.getId(), var);
					variablesCount++;
					System.out.println("adding variable: "+var.toString());

					
				}
				// in all cases, add the new constraint to the variables neighbors
				// add all variables to the constraint neighbors
				var.addNeighbor(newNode);
				newNode.addNeighbor(var);
				edges++;
			}
			constraints.put(id, newNode);
			System.out.println("adding constraint: "+newNode.toString());
		}
		
	}
	
//	public boolean addVersionConstraint(String expression, String id){
//		boolean exit=false;
//		
//		NodeVariableHLCL var=variables.get(id);
//		
//		if (var!=null)
//			exit= var.addConstraint(new Constraint(expression));
//		return exit;
//	}
	
	public boolean addUnaryConstraint(IntBooleanExpression cons, Identifier var){
		boolean exit=false;
		String id= var.getId();
		NodeVariableHLCL varN=variables.get(id);
		// case 1: the variable exists
		if (varN!=null){
			exit= varN.addConstraint(cons);
			constraintsCount++;
			edges++;
			System.out.println("adding unary constraint to existent var: "+varN.toString());

		}
		//case 2: the variable does not exists
		else{
			varN= addVariable(var);
			exit= varN.addConstraint(cons);
			constraintsCount++;
			edges++;
			System.out.println("adding unary constraint to a new var: "+varN.toString());

			
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
		System.out.println("adding variable " + varNode.toString());
	
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
	
	public TreeSet<VertexHLCL> getNeighbors(String id, boolean type){
		TreeSet<VertexHLCL> tmp= null;
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
}
