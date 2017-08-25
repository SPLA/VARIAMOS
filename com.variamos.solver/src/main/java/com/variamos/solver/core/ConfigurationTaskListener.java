package com.variamos.solver.core;

import com.variamos.solver.model.SolverSolution;

/*
 * TODO: Check if this class is useful or not
 */
public interface ConfigurationTaskListener {
	public void onSolutionFound(SolverSolution solution);
	public void onTaskCompleted(ConfigurationTask task);
	public void onTaskStarted(ConfigurationTask configurationTask);
}
