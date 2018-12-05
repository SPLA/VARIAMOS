package com.variamos.reasoning.medic.model.diagnoseAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.reasoning.medic.model.graph.ConstraintGraphHLCL;
import com.variamos.reasoning.medic.model.graph.HLCL2Graph;
import com.variamos.reasoning.medic.model.graph.NodeConstraintHLCL;
import com.variamos.reasoning.medic.model.graph.NodeVariableHLCL;
import com.variamos.reasoning.medic.model.graph.VertexHLCL;
import com.variamos.reasoning.util.LogParameters;
import com.variamos.solver.core.SWIPrologSolver;

/**
 * This class implements the algorithm for find a minimal set of inconsistent constraints
 * when a CSP is inconsistent.
 * 
 * This version of the algorithm uses the HLCL and compiler from the variamos core.
 * The HLCL is used to represent a ccp and the compiler for using the solver.
 * 
 * General characteristics of the algorithm in this version:
 * 1. The main loop does  a number of  DFS determined by an input or until the path reaches a fixed point: there is no shorter path.
 * 2. The size of the graph changes, the next DFS uses only the previous visited graph
 * 3. The output is an object of the class Path that represents the path, the last vertex in the path is the last visited vertex 
 * Specific changes:
 * for the DFS
 * 1. we included an attribute in the node for determine if it's visited or not
 * 2. the isVisited subroutine will not search in a list
 * 
 * 
 * @author Angela Villota <Angela Villota>
 * @version 1.3
 * @since 0
 *
 */
public class MinimalSetsDFSIterationsHLCL {
	private HlclProgram cspIn; // input
	private ConstraintGraphHLCL graph; // csp transformed

	
	private LogManager logMan;// log manager
	
	//varibles used for statistics
	private int iterations; // number of iterations in the main loop
	private LogParameters logParameters;
	private long time; // execution time
	private int iterationsPath; // number of iterations in the intern loop (Search path)
	private int total=0; // ciclos de la busqueda, dentro del while
	private int numPath; //total de vertices en el camino
	int numVars=0; // variables en una salida

	/**
	 * Method for using the diagnosis algorithm from variamos
	 * @param csp
	 * @param net
	 */
	public MinimalSetsDFSIterationsHLCL(HlclProgram csp, LogParameters log){
		cspIn= csp;
		logParameters = log;
		HLCL2Graph transformer= new HLCL2Graph(cspIn);
		graph= transformer.transform();
		iterations=0;
	}

	/**
	 * Diagnosis algorithms main loop. This loop performs a defined number of searches, or until an answer can't be 
	 * improved (the length of the path can't be reduced)
	 * @param source string with the id of the starting vertex
	 * @param maxIterations integer with the max amount of iteratiuons
	 * @return
	 */

	public LinkedList<VertexHLCL> sourceOfInconsistentConstraints(String source, int maxIterations) {
		/*
		 * Each search returns a subset of the graph and the sequence of visited vetrices, all wrapped
		 * in a Path object
		 */
		Path<ConstraintGraphHLCL,VertexHLCL> path = new Path<ConstraintGraphHLCL,VertexHLCL>();

		if(source.equals("MAX")){
			source=graph.getMaxRoot();
		}

		if(source.equals("RAND")){
			source=graph.getRandRoot();
		}
		if(source.equals("MIN")){
			source=graph.getMinRoot();
		}
		switch(logParameters.getLogType()){

		//start the diagnosis algorithm using a log file using an logMan object
		case LogParameters.LOG:
			//first we setup the logManager
			//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String testName=logParameters.getProblemName()+"_"+ date.toString();
			logMan= new LogManager(logParameters.getPath(), testName);
			logMan.initLog();
			logMan.writeInFile("Starting test of the MEDIC diagnosis algorithm, file: "+testName + "\n"+
					"The strategy of the search is "+ source +"\n"+
					"path lenght"+getPathLenght()+"main loop: "+ getIter()+ "total vertices: "+getTotal()+ "size"+ sizePath()+" \n" );


			
			try{
				iterations=1; //number of iterations of a call to the search algorithm
				long startTime = System.currentTimeMillis();
				path = searchPathLog(source, graph, cspIn);
				sizes+=path.getPath().size() +", ";
				source= path.getPath().getLast().getId(); // source for the following iterations
				int previousLength; //Length of the path
				ConstraintGraphHLCL subGraph= path.getSubset(); //subgraph
				HlclProgram csp = path.getSubProblem();

				do{
					System.gc();
					previousLength= path.getPath().size();
					path = searchPathLog(source, subGraph, csp);
					source= path.getPath().getLast().getId();
					subGraph= path.getSubset();
					maxIterations--;
					iterations++;
					//sizes+=path.getPath().size() +", ";
					
					int totalVertices=0;
					for (VertexHLCL vertex : path.getPath()) {
						totalVertices++;
						if(vertex instanceof NodeVariableHLCL){
							numVars++;
							for (NodeConstraintHLCL cons : ((NodeVariableHLCL) vertex).getUnary()) {
								totalVertices++;
							}
						}
					}
					sizes+=totalVertices +", ";

				}
				while(maxIterations>0 && previousLength> path.getPath().size());
				long endTime = System.currentTimeMillis();
				time=endTime - startTime;

				//TODO comentar estas lineas para pruebas de performance
				//logMan.writeInFile((endTime - startTime) + "\t");
				//printNetwork(graph);
				logMan.writeInFile("Total execution: "+time + "\n");

				if (! (maxIterations>0)){
					logMan.writeInFile("Execution termined for reaching a the maximun of iterations"+ "\n");
				}else{
					logMan.writeInFile("Execution termided for reaching a fixed point after " +  iterations+ " iterations" +"\n");
				}
				logMan.writeInFile("Size of path at iteration No."+ iterations+": " + path.getPath().size() + "\n");
				int totalVertices=0;
				
				for (VertexHLCL vertex : path.getPath()) {
					totalVertices++;
					if(vertex instanceof NodeVariableHLCL){
						numVars++;
						logMan.writeInFile(vertex.getId()+ " ");
						logMan.writeInFile( "(");
						for (NodeConstraintHLCL cons : ((NodeVariableHLCL) vertex).getUnary()) {
							totalVertices++;
							logMan.writeInFile(cons.getId()+ " -");
						}
						logMan.writeInFile( ") - ");
					}else 
						logMan.writeInFile(vertex.getId()+ " -");
				}
				logMan.writeInFile("Total vertices : " + totalVertices  + "\n");
				numPath=totalVertices;
				logMan.writeInFile("\n");
			}catch (Exception e){
				logMan.writeInFile("Execution suspended at iteration No."+ iterations+", iterations in search path "+iterationsPath+ ", for a runTime error \n"+ e.toString());
				e.printStackTrace();
			}
			logMan.closeLog();
			break;
		case LogParameters.NO_LOG:

			try{
				iterations=1; //number of iterations of a call in the diagnosis algorithm
				long startTime = System.currentTimeMillis();
				path = searchPathLog(source, graph, cspIn);
				sizes+=path.getPath().size() +", ";
				source= path.getPath().getLast().getId(); // source for the following iterations
				int previousLength; //Length of the path
				ConstraintGraphHLCL subGraph= path.getSubset(); //subgraph
				HlclProgram csp = path.getSubProblem();

				do{
					System.gc();
					previousLength= path.getPath().size();
					path = searchPathLog(source, subGraph, csp);
					source= path.getPath().getLast().getId();
					subGraph= path.getSubset();
					maxIterations--;
					iterations++;
					//sizes+=path.getPath().size() +", ";
					sizes+=iterationsPath +", ";
				}
				while(maxIterations>0 && previousLength> path.getPath().size());
				long endTime = System.currentTimeMillis();
				time=endTime - startTime;
				
			}catch (Exception e){
				e.printStackTrace();
			}
			break;
		}
		return path.getPath();
	}

	
	/**
	 * This method performs a DFS until an insatisfiable constraint is found
	 * @param source is a string with the id of the vertex source of the search
	 * @param csp is a copy of the inconsistent CSP with the variables and domains of the problem, 
	 * no se esta usando por ahora, sirve cuando se haga la ejecuciÃ³n incremental 
	 * @param cc is the list of inconsistent constraints 
	 * @return path is a linked-list containing the sequence of visited vertices.  
	 * The last vertex in path is the inconsistent contains the inconsistent constraint
	 * @throws FunctionalException 
	 */
	public Path<ConstraintGraphHLCL,VertexHLCL> 
	searchPathLog(String source, ConstraintGraphHLCL graphIn, HlclProgram csp) throws Exception{
		iterationsPath=0;
		ConstraintGraphHLCL subGraph = new ConstraintGraphHLCL();
		Path<ConstraintGraphHLCL,VertexHLCL> output= null; // the output
		HlclProgram subProblem = new HlclProgram();
		LinkedList<VertexHLCL> path= new LinkedList<VertexHLCL>(); // the path of vertices
		Stack<VertexHLCL> stack= new Stack<VertexHLCL>();  //data structure used to perform the Depth First Search
		stack.push(graphIn.getVertex(source)); // the data structure starts with the source vertex

		boolean satisfiable=true; // all empty csp is satisfiable
		VertexHLCL actual=stack.pop(); //initializing the loop with a vertex
		if (actual==null){
			throw new Exception(" Error while examining the vertex with id "+ source+" the vertex is not in the graph,"
					+ "either:  the id is wrong, or there are problems in the translation of the constraint graph" );
		}
		else{
			VertexHLCL clon= actual.clone(); // esto es null cuando el hlcl es vacio
			subGraph.addVertex(clon);
			int count=1;// number of nodes to visit 
			HlclProgram newConstraints= null ; // new set of constraints

			//While the CSP is satisfiable
			while(satisfiable){
				path.addLast(actual);
				iterationsPath++;
				total++;
				actual.setOrder(count);
				boolean empty=true;  //if the new set of constraints is empty, then there is no call to the solver

				if (actual instanceof NodeVariableHLCL){
					newConstraints= ((NodeVariableHLCL)actual).getConstraints();
					if (!newConstraints.isEmpty()){
						empty=false;
					}
				}else if (actual instanceof NodeConstraintHLCL){
					newConstraints=  actual.getConstraints();
					if (!newConstraints.isEmpty()){
						empty=false;
					}	
				}
				//if the set of constraints is different to empty, then the satisfiability of the SCP is evaluated
				if (!empty){
					subProblem.addAll(newConstraints);
					satisfiable= evaluateSatisfatibility(subProblem);
				}
				// if the csp is satisfiable, then the traverse of the constraint network continues.
				if(satisfiable){
					actual=getNextNode(stack, actual,subGraph);
					count++;
				}
			}
		}
		// creating the output 
		output= new Path<ConstraintGraphHLCL,VertexHLCL>(subGraph, path, subProblem);
		return output;
	}

	
	/**
	 * To obtain the next node, first the set of adjacent vertices are added to the structure
	 * @param structure structure is the data structure whit the nodes to perform a graph traverse
	 * @param actual actual is the actual vertex
	 * @param newG is the subgraph in construction
	 * @return the next vertex
	 * @throws Exception when the stack is empty, this happens when the graph contains 
	 * an unconnected region or when the problem is consistent.
	 */
	public VertexHLCL getNextNode(Stack<VertexHLCL> structure, VertexHLCL actual, ConstraintGraphHLCL newG )throws Exception{

		//Mark the current vertex as visited
		actual.setSearchState(VertexHLCL.VISITED);
		VertexHLCL next= null;
		
		// including the adjacent vertices in the stack only if it's not visited  
		for(VertexHLCL v: actual.getNeighbors()){
			if (v.getSearchState()!= VertexHLCL.VISITED){
				v.setParent(actual);
				structure.push(v);
				v.setSearchState(VertexHLCL.INSTACK);
			}	
		}
		
		if (structure.isEmpty()){
			throw new Exception("The stack is empty, this means that the problem is consistent");
		}
		else{ // if everything is all right (not empty stack)

			//Find the next vertex, a non visited vertex.
			do{
				next= structure.pop();
			}while(next.getSearchState()== VertexHLCL.NOT_VISITED);

			// Including the new vertex into the subgraph
			VertexHLCL nV= next.clone();
			VertexHLCL parent = newG.getVertex(next.getParent().getId()); 
			newG.addVertex(nV);
			newG.addEdge(nV, parent);
		}
		
		return next;
	}

	/**
	 * Method to evaluate the satisfiability of a CSP represented as a HLCL program
	 * @param constraints is the hlcl program
	 * @return
	 */
	public boolean evaluateSatisfatibility(HlclProgram constraints){

		// the output
		boolean evaluation;

		// to transform the hlcl program into a prolog  file, 
		// these lines are commented because they are useful for debugging 
		//Hlcl2SWIProlog swiPrologTransformer = new Hlcl2SWIProlog(); 
		//String prologProgram = swiPrologTransformer.transform(constraints);
		
		// an instance of the solver for Swiprolog 
		SWIPrologSolver swiSolver= new SWIPrologSolver();
		swiSolver.setHLCLProgram(constraints); //passing the hlcl program to the solver
		swiSolver.solve(); // This method prepares the solver 
		evaluation = swiSolver.hasSolution(); // Consulting if the solver has one solution
		System.gc(); //calling the Java's garbage collector 
		
		//usar el llamado con parametros
		
		return evaluation;
	}
	

	
	/**
	 * Method to print a constraint graph in the log file
	 * This method is used just for debugging
	 * @param net is the constraint graph
	 */
	public void printNetwork(ConstraintGraphHLCL net){
		logMan.writeInFile("\nConstraint network: \n");
		logMan.writeInFile("Total vertices: "+net.numVertices()+
				           " vars: "+net.getVariablesCount()+
				           " cons: "+net.getConstraintsCount()+
				           " Total edges: "+net.numEdges()+  "\n");
		
		StringBuilder vecinos= new StringBuilder();
		
		logMan.writeInFile("Problem variables\n");
		 HashMap<String,NodeVariableHLCL> vars= net.getVariables();
		 for (String id : vars.keySet()) {
			 logMan.writeInFile(id+"\n");
			 vecinos.append("adjacent nodes of variable "+ id + ": " );
			 for (VertexHLCL v: net.getNeighbors(id, VertexHLCL.VARIABLE_TYPE)){
				 vecinos.append(v.getId()+",  ");	 
			 }
			 vecinos.append("\n");
		}
		 
		//neighbors for constraint nodes
		 logMan.writeInFile("Problem constraints\n");
		 HashMap<String,NodeConstraintHLCL> cons= net.getConstraints();
		 for (String id : cons.keySet()) {
			 logMan.writeInFile(id+"\n");
			 vecinos.append("adjacent nodes of constraint "+ id + ": " );
			 for (VertexHLCL v: net.getNeighbors(id, VertexHLCL.CONSTRAINT_TYPE)){
				 vecinos.append(v.getId()+",  ");	 
			 }
			 vecinos.append("\n");
		}
		 for (String id : vars.keySet()) {
			 NodeVariableHLCL var1= vars.get(id);
			 //logMan.writeInFile(id+"\n");
			 //vecinos.append("adjacent nodes of variable "+ id + ": " );
			 for (NodeConstraintHLCL v: var1.getUnary()){
				 logMan.writeInFile(v.getId()+ " , "+ v.getConstraint()+"\n"); 
			 }
			 vecinos.append("\n");
		}
		 logMan.writeInFile(vecinos.toString());
	}
	
	/**
	 * 
	 * @return EXECUTION TIME
	 */
	public long getTime(){
		return time;
	}
	/**	
	 * 
	 * @return returns the total amount of iterations
	 * 
	 */

	public int getTotal(){
		return total;
	}

	public int getIter(){
		return iterations;
	}
	public int getPathLenght(){
		return numPath;
	}
	
	public int getNumVars(){
		return numVars;
	}

	public String graphInfo(){
		String salida="";
		salida += graph.getVariablesCount() + ", "+ graph.getConstraintsCount() +", "+(graph.getVariablesCount()+ graph.getConstraintsCount())+ ","+ graph.numEdges();

		return salida;
	}

	String sizes="";
	public String sizePath(){
		return sizes;
	}
	public ConstraintGraphHLCL getGraph() {
		return graph;
	}


}
