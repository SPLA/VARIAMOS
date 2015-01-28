package com.variamos.gui.pl.configurator.guiactions;

import javax.swing.SwingUtilities;

import com.cfm.productline.solver.Configuration;
import com.cfm.productline.solver.ConfigurationTask;
import com.cfm.productline.solver.ConfigurationTaskListener;
import com.variamos.gui.common.jelements.AbstractConfigurationPanel;

public class DefaultConfigurationTaskListener implements
		ConfigurationTaskListener {
	
	private long timeStarted;
	private AbstractConfigurationPanel configurator;
	
	public DefaultConfigurationTaskListener(AbstractConfigurationPanel configurator){
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
