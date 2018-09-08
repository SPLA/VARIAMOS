package com.variamos.reasoning.util;

/**
 * 
 * @author Angela
 *
 */
public class LogParameters {
	public static final int LOG=1;
	public static final int NO_LOG=0;
	


	private int runLog= NO_LOG;
	private String path;
	private String problemName;
	
	public LogParameters(){
		
	}
	
	public LogParameters(String path, String problemName) {
		this.runLog= LOG;
		this.path = path;
		this.problemName = problemName;
	}
	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}
	
	public int getLogType(){
		return runLog;
	}

}
