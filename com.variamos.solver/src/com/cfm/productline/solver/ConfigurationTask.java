package com.cfm.productline.solver;


public class ConfigurationTask implements Runnable{
	private Solver solver;
	private Configuration configuration;
	private ConfigurationTaskListener listener;
	private boolean stopCall;
	private int solFound = 0;
	private ConfigurationOptions options;
	
	public ConfigurationTask(Solver solver, Configuration configuration, ConfigurationOptions options, ConfigurationTaskListener listener) {
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
		
		if( configuration != null && solver.getProductLine() != null){
		
			solver.solve(configuration, options);
			
			while(!stopCall && solver.hasNextSolution()){
				solFound++;
				final Configuration sol = solver.getSolution();
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
