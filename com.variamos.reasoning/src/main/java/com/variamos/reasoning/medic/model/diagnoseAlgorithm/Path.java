package com.variamos.reasoning.medic.model.diagnoseAlgorithm;

import java.util.LinkedList;
import java.util.List;

import com.variamos.hlcl.core.HlclProgram;



public class Path<G,V> {


	private G subset;
	private LinkedList <V> path;
	private  HlclProgram subProblem;
	

	


	public Path(){
		
	}
	public Path(G inNet, LinkedList <V> inPath){
		subset= inNet;
		path= inPath;
	}
	
	public Path(G inNet, LinkedList <V> inPath, HlclProgram sub){
		subset= inNet;
		path= inPath;
		subProblem= sub;
	}
	
	public HlclProgram getSubProblem() {
		return subProblem;
	}

	public void setSubProblem(HlclProgram subProblem) {
		this.subProblem = subProblem;
	}
	
	public G getSubset() {
		return subset;
	}

	public void setSubset(G subset) {
		this.subset = subset;
	}

	public LinkedList<V> getPath() {
		return path;
	}

	public void setPath(LinkedList<V> path) {
		this.path = path;
	}
	
//	public List<String> getIdsFromPath(){
//		LinkedList<String> ids = new LinkedList<String>();
//		for (V v : path) {
//			ids.addLast(v);
//			
//		}
//		
//		return ids;
//		
//	}


}
