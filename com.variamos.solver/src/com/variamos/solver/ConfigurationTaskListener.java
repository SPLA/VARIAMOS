package com.variamos.solver;


public interface ConfigurationTaskListener {
	public void onSolutionFound(Configuration solution);
	public void onTaskCompleted(ConfigurationTask task);
	public void onTaskStarted(ConfigurationTask configurationTask);
}
