package com.variamos.solver.core;

import com.variamos.solver.model.ConfigurationOptionsDTO;
import com.variamos.solver.model.SolverSolution;

/*
 * TODO: Check if this class is useful or not
 */
public class ConfigurationTask implements Runnable{
	private IntSolver solver;
	private SolverSolution configuration;
	private ConfigurationTaskListener listener;
	private boolean stopCall;
	private int solFound = 0;
	private ConfigurationOptionsDTO options;
	
	public ConfigurationTask(IntSolver solver, SolverSolution configuration, ConfigurationOptionsDTO options, ConfigurationTaskListener listener) {
		super();
		this.solver = solver;
		this.configuration = configuration;
		this.listener = listener;
		this.options = options;
		stopCall = false;
	}

	@Override
	public void run() {
		listener.onTaskStarted(this);
		
		if( configuration != null){
		
			solver.solve(configuration, options);
			
			while(!stopCall && solver.hasNextSolution()){
				solFound++;
				final SolverSolution sol = solver.getSolution();
				listener.onSolutionFound(sol);
				solver.nextSolution();
			}
		}
		
		listener.onTaskCompleted(this);
	}

	public int getSolutionsFound() {
		return solFound;
	}
}
