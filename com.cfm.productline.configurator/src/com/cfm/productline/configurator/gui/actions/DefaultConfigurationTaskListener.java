package com.cfm.productline.configurator.gui.actions;

import javax.swing.SwingUtilities;

import com.cfm.productline.configurator.Configurator;
import com.cfm.productline.solver.Configuration;

public class DefaultConfigurationTaskListener implements
		ConfigurationTaskListener {
	
	private long timeStarted;
	private Configurator configurator;
	
	public DefaultConfigurationTaskListener(Configurator configurator){
		this.configurator = configurator;
	}
	
	@Override
	public void onSolutionFound(final Configuration solution) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				configurator.addSolution(solution);
			}
		});
	}

	@Override
	public void onTaskCompleted(final ConfigurationTask task) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				configurator.taskCompleted(task, System.currentTimeMillis() - timeStarted);
			}
		});
	}

	@Override
	public void onTaskStarted(final ConfigurationTask configurationTask) {
		timeStarted = System.currentTimeMillis();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				configurator.setStatus("Solving ...");
				configurator.clearProducts();
			}
		});
	}
}
