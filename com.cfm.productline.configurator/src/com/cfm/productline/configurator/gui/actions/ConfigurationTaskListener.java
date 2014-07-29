package com.cfm.productline.configurator.gui.actions;

import com.cfm.productline.solver.Configuration;

public interface ConfigurationTaskListener {
	public void onSolutionFound(Configuration solution);
	public void onTaskCompleted(ConfigurationTask task);
	public void onTaskStarted(ConfigurationTask configurationTask);
}
