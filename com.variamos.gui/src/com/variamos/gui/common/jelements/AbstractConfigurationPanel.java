package com.variamos.gui.common.jelements;

import javax.swing.JPanel;

import com.variamos.dynsup.types.Variable;
import com.variamos.solver.core.ConfigurationTask;
import com.variamos.solver.model.SolverSolution;

public abstract class AbstractConfigurationPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4389330587062666145L;

	// public abstract void configure(AbstractModel am);

	public abstract void addSolution(SolverSolution solution);

	public abstract void taskCompleted(ConfigurationTask task, long timeMillis);

	public abstract void setStatus(String string);

	public abstract void clearProducts();

	public abstract void resizeColumns();

	public abstract void setValueToVariable(Variable variable, Integer value,
			int index, Object source);
}
