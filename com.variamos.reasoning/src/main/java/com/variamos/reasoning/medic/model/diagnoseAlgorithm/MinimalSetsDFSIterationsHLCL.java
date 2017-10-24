package com.variamos.reasoning.medic.model.diagnoseAlgorithm;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.hlcl.core.HlclProgram;
import com.variamos.hlcl.model.expressions.IntBooleanExpression;
import com.variamos.reasoning.medic.model.graph.ConstraintGraphHLCL;
import com.variamos.reasoning.medic.model.graph.HLCL2Graph;
import com.variamos.reasoning.medic.model.graph.NodeConstraintHLCL;
import com.variamos.reasoning.medic.model.graph.NodeVariableHLCL;
import com.variamos.reasoning.medic.model.graph.VertexHLCL;
import com.variamos.solver.core.IntSolver;
import com.variamos.solver.core.SWIPrologSolver;
import com.variamos.solver.core.compiler.Hlcl2SWIProlog;
import com.variamos.solver.model.ConfigurationOptionsDTO;
import com.variamos.solver.model.SolverSolution;








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
 * @version 1.2
 * @since 0
 *
 */
public class MinimalSetsDFSIterationsHLCL {
//	public static final String LOGNAME="logFiles/ExecutionLog";
//	public static final String LOGEXT=".log";
	
	
	
	
	//StringBuilder 
	
	
	//Objeto que se encarga de convertir un CSP en un archivo desde cero, o de usar un stringBuilder
	// para escribirlo en un archivo
//	private CSP2File csp2file;
	
	// Objects for the translating into the solver representation 
	private StringBuilder sb;
	private Hlcl2SWIProlog parser;
	//EvaluateSatisfiability eval;
	
	private HlclProgram cspIn;
	private ConstraintGraphHLCL graph;

	
	private LogManager logMan;
	
	//TODO comentar estas lineas para pruebas de performance
	private ArrayList<String> times;

	private int iterations;
	
	/**
	 * Method for using the diagnosis algorithm from variamos
	 * @param csp
	 * @param net
	 */
	public MinimalSetsDFSIterationsHLCL(HlclProgram csp){
		cspIn= csp;
		HLCL2Graph transformer= new HLCL2Graph(cspIn);
		graph= transformer.transform();
		iterations=0;
		//revisar si es necesario inicializar más atributos o elementos necesarios para el algoritmo
		times = new ArrayList<String>();
	}

	/**
	 * This method is useful for test and trace the diagnosis algorithm  
	 * @param csp the inconsistent CSP
	 * @param path the path of the problem for tests and etc
	 * @param man is the object used to make the logfile to report the execution of the algorithm
	 * @param net is the graph generated from the csp
	 */
	public MinimalSetsDFSIterationsHLCL(HlclProgram csp, String path,  LogManager man, ConstraintGraphHLCL net){
		cspIn= csp;
		logMan= man;
		graph= net;
		//problemPath= path;
		//TODO imprime la red de restricciones
		//printNetwork(constraintNetwork);
		//sb= new StringBuilder();
		
		//TODO comentar estas lineas para pruebas de performance
		times = new ArrayList<String>();
		
	}

/**
 * Diagnosis algorithms main loop. This loop performs a defined number of searches, or until an answer can't be 
 * improved (the length of the path can't be reduced) 
 * @param source
 * @param maxIterations
 * @return
 */

	/**
	 *  Diagnosis algorithms main loop. This loop performs a defined number of searches, or until an answer can't be 
	 *  improved (the length of the path can't be reduced)
	 *  This method includes a parameter to create a log File to create a trace of the execution of the algorithm.   
	 * @param source string with the id of the starting vertex
	 * @param maxIterations, integer with the max amount of iteratiuons 
	 * @param man log manager
	 * @return
	 */
	
	public LinkedList<VertexHLCL> sourceOfInconsistentConstraintsLog(String source, int maxIterations){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String testName=date.toString();
		logMan= new LogManager("/Users/Angela/Test/", testName);
		logMan.initLog();
		
		logMan.writeInFile("Starting test of the MEDIC diagnosis algorithm, file: "+testName + "\n");

		/*
		 * Each search returns a subset of the graph and the sequence of visited vetrices, all wrapped
		 * in a Path object
		 */
		Path<ConstraintGraphHLCL,VertexHLCL> path = new Path<ConstraintGraphHLCL,VertexHLCL>();

		iterations=0; //number of iterations of a call in the diagnosis algorithm

		
		try{
			
			long startTime = System.currentTimeMillis();
			path = searchPathLog(source, graph, cspIn);
			// one search is already done
			
			/*
			 * Lines to debug, (un)comment till the beginning of the do-while
			 */
			logMan.writeInFile("Size of path iteration No.1: " + path.getPath().size());
			logMan.writeInFile("\n");
			for (VertexHLCL vertex : path.getPath()) {
				logMan.writeInFile(vertex.getId()+ " ");
			}
			logMan.writeInFile("\n");
			
			iterations++;
			source= path.getPath().getLast().getId(); // source for the following iterations
			int previousLength; //Length of the path
			//list = recoverPath( path0.getPath().getLast(), list);
			ConstraintGraphHLCL subGraph= path.getSubset(); //subgraph
			HlclProgram csp = path.getSubProblem();

			do{
				logMan.writeInFile("Iteration "+ iterations);
				logMan.writeInFile("\n");
				previousLength= path.getPath().size();
				path = searchPathLog(source, subGraph, csp);
				source= path.getPath().getLast().getId();
				subGraph= path.getSubset();
				maxIterations--;
				iterations++;

				logMan.writeInFile("Size of path iteration No.: " + path.getPath().size() + "\n");
				for (VertexHLCL vertex : path.getPath()) {
					logMan.writeInFile(vertex.getId()+ " ");
				}
				logMan.writeInFile("\n");
				
			}
			while(maxIterations>0 && previousLength> path.getPath().size());
			
			if (! (maxIterations>0)){
				logMan.writeInFile("Execution termined for reaching a the maximun of iterations"+ "\n");
			}else{
				logMan.writeInFile("Execution termided for reaching a fixed point after " +  iterations+ " iterations" +"\n");
			}
			
			
			long endTime = System.currentTimeMillis();

			
			//TODO comentar estas lineas para pruebas de performance
			//logMan.writeInFile((endTime - startTime) + "\t");
			logMan.writeInFile("Total execution: "+(endTime - startTime) + "\n");
		}catch (Exception e){
			logMan.writeInFile("Execution suspended, runTime error \n"+ e.toString());
			//writeInFile(e.getMessage());
			e.printStackTrace();
		}
		logMan.closeLog();
		return path.getPath();
		
	}
	public LinkedList<VertexHLCL> recoverPath(VertexHLCL v, LinkedList<VertexHLCL> path){
		
		
		if (v.getParent() == null){
			path.add(v);
		}
		else{
			path.add(v);
			recoverPath(v.getParent(), path);
		}
		return path;
	}
	

	/**
	 * This method performs a DFS until an insatisfiable constraint is found
	 * @param source is a string with the id of the vertex source of the search
	 * @param csp is a copy of the inconsistent CSP with the variables and domains of the problem
	 * @param cc is the list of inconsistent constraints 
	 * @return path is a linked-list containing the sequence of visited vertices.  
	 * The last vertex in path is the inconsistent contains the inconsistent constraint
	 */

	
	/**
	 * This method performs a DFS until an insatisfiable constraint is found
	 * @param source is a string with the id of the vertex source of the search
	 * @param csp is a copy of the inconsistent CSP with the variables and domains of the problem, 
	 * no se está usando por ahora, sirve cuando se haga la ejecución incremental 
	 * @param cc is the list of inconsistent constraints 
	 * @return path is a linked-list containing the sequence of visited vertices.  
	 * The last vertex in path is the inconsistent contains the inconsistent constraint
	 * @throws FunctionalException 
	 */
	public Path<ConstraintGraphHLCL,VertexHLCL> 
	searchPathLog(String source, ConstraintGraphHLCL graphIn, HlclProgram csp) throws FunctionalException,Exception{
		
		logMan.writeInFile("\nConstraint graph in iteration "+ iterations+ "\n");
		printNetwork(graphIn);
		ConstraintGraphHLCL subGraph = new ConstraintGraphHLCL();
		Path<ConstraintGraphHLCL,VertexHLCL> output= null;
		HlclProgram subProblem = new HlclProgram();

		
		
		LinkedList<VertexHLCL> path= new LinkedList<VertexHLCL>(); // the output
		Stack<VertexHLCL> stack= new Stack<VertexHLCL>();  //data structure used to perform the Depth First Search

		stack.push(graphIn.getVertex(source)); // the data structure starts with the source vertex
		

		boolean satisfiable=true; // all empty csp is satisfiable
		VertexHLCL actual=stack.pop(); //initializing the loop with a vertex
		if (actual==null){
			throw new FunctionalException(" Error while examining the vertex with id "+ source+" the vertex is not in the graph,"
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
			boolean empty=true;  //if the new set of constraints is empty, then there is no call to the solver
			
			if (actual instanceof NodeVariableHLCL){
				newConstraints= actual.getConstraints();
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
				satisfiable= evaluateSatisfatibility(subProblem, actual);
			}
			//writeLog(actual, count, satisfiable, newConstraints, stack);

			// if the csp is satisfiable, then the traverse of the constraint network continues.
			if(satisfiable){
				actual=getNextNode(stack, actual,subGraph);

				count++;
			}
		}
		
//		logMan.writeInFile("Initial graph"+"\n");
//		printNetwork(graph);
//		
		//logMan.writeInFile("Graph built in iteration"+ iterations+ "\n");
		//printNetwork(subGraph);
		}
	
		output= new Path<ConstraintGraphHLCL,VertexHLCL>(subGraph, path, subProblem);
		return output;



	}
	/**
	 * To obtain the next node, first the set of adjacent vertices are added to the structure
	 * then the first node is obtained.
	 * @param structure is the data structure whit the nodes to perform a graph traverse
	 * @param actual is the actual vertex
	 * @return return the next vertex to be examined
	 */
	public VertexHLCL getNextNode(Stack<VertexHLCL> structure, VertexHLCL actual, ConstraintGraphHLCL newG )throws Exception{
		
		actual.setSearchState(VertexHLCL.VISITED);
		VertexHLCL next= null;
		

		for(VertexHLCL v: actual.getNeighbors()){
			
			if (v.getSearchState()== VertexHLCL.NOT_VISITED){
				//System.out.print(v.getId()+ ", ");
				v.setParent(actual);
				structure.push(v);
				v.setSearchState(VertexHLCL.INSTACK);
			}	
		}
		if (structure.isEmpty()){
			throw new FunctionalException("The stack is empty, this means that the problem is consistent");
			
		}else{

		next= structure.pop();
		//System.out.println();
		//System.out.println("Vertex examined: " + next.getId()+", search state: "+ next.getSearchState()+", parent: "+ next.getParent().getId());
		VertexHLCL nV= next.clone();
		VertexHLCL parent = newG.getVertex(next.getParent().getId());
		newG.addVertex(nV);
		newG.addEdge(nV, parent);
		//System.out.println("New Vertex: " + nV.getId()+", search state: "+ nV.getSearchState()+", parent: "+ nV.getParent());
		//System.out.println("New Edge: "+ nV.getId() + ", "+parent.getId());
		}
		return next;
	}
	/**
	 * Modificado para a�adir las restricciones y crear el archivo a partir del stringBuilder
	 * @param csp
	 * @return
	 */
	public boolean evaluateSatisfatibility(HlclProgram constraints, VertexHLCL actual){
		//Old version
//		CSP2FileRandom csp2file= new CSP2FileRandom(csp);  //transformimng re csp object into a prolog program
//		String programName= csp2file.transform(problemPath,count, direction);
//		EvaluateSatisfiability eval= new EvaluateSatisfiability();
//		return eval.consult(programName);
		
		boolean evaluation;
		
		//TODO quitar la medicion de tiempos
		String time= "Iteration No. "+ iterations + " Node: "+ actual.getId();
		long startTime = System.currentTimeMillis();
		
		/*
		 * En esta parte hay que:
		 * Organizar el programa para hacer la consulta:
		 * - tengo el encabezado en el string builder
		 * - las restricciones que hay que añadir están en la colección
		 * - la parte final se agrega con el footter 
		 */
		
//		PrologTransformParameters params = new PrologTransformParameters();
//		params.setStage(StageInTransformation.Partial);
//		sb.append(parser.transform(constraints, params));
//		
//		csp2file.appendConstraints(sb, constraints);
//		String programName= csp2file.createFile(sb, problemPath);
		
		IntSolver swiSolver= new SWIPrologSolver();
		swiSolver.setHLCLProgram(constraints);
		swiSolver.solve(new SolverSolution(), new ConfigurationOptionsDTO());
		evaluation = swiSolver.hasSolution();
		
		long endTime = System.currentTimeMillis();
		time+= "; " + (endTime - startTime); 
	

		//times.add(time);
		return evaluation;
	}
	/**
	 * 
	 * @param csp
	 * @return
	 */
	public ConstraintGraphHLCL csp2network(HlclProgram csp){
		
		HLCL2Graph o= new HLCL2Graph(csp);
		ConstraintGraphHLCL net=  o.transform();
		
		return net;
	}
	
	
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
		 logMan.writeInFile(vecinos.toString());
	}
	public void writeLog(VertexHLCL actual, int count, boolean satisfiable, HlclProgram newConstraints){

		String sentences= "Iteration No."+ count +" Vertex id= "+ actual.getId()+"\n" + "vertex type: "+
				          ((actual instanceof NodeConstraintHLCL)?"Constraint ": "Variable ");
		sentences += "the CSP in this iteration is "+ ((satisfiable)?"satisfiable\n": "not satisfiable\n");
		if (!satisfiable){
			sentences+= "The constraints that made inconsistent the csp are \n";
			for (IntBooleanExpression constraint : actual.getConstraints()) {
				sentences+= "Constraint: "+ constraint.toString()+ "\n";
			}
			
		}
		logMan.writeInFile(sentences);
	}
//	
//	public void initLog(){
//		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		 String time =timestamp.getTime()+"";
//		String fileName= LOGNAME+"_"+ time+LOGEXT;
//		try {
//			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
//			out.write("Log File : " + timestamp+ "File name " + fileName+ " \n");
//		} catch (Exception e) {
//			// 
//			
//			e.printStackTrace();
//		}
//
//	}
//	/**
//	 * 
//	 * @param sentences is a string with the sentences in prolog 
//	 */
//	public void writeInFile(String sentences){
//		try {
//			out.write(sentences);
//			//System.out.println(sentences);
//		} catch (IOException e) {
//			// 
//			e.printStackTrace();
//		}
//		
//		
//	}
//	
//	public void closeLog(){
//	try{
//		out.flush();
//		out.close();
//	}catch (Exception e){
//		e.printStackTrace();
//	}
//	}

}
