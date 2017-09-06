package com.variamos.gui.pl.configurator.treetable;

import com.cfm.productline.Variable;
import com.variamos.gui.common.jelements.AbstractConfigurationPanel;

public abstract class ConfigurationAction {
	protected Variable variable;
	protected int index;

	public ConfigurationAction(Variable variable, int index) {
		super();
		this.variable = variable;
		this.index = index;
	}

	public Variable getVariable() {
		return variable;
	}

	public int getIndex() {
		return index;
	}

	public abstract void undo(AbstractConfigurationPanel configurator,
			Object source);

	public abstract void execute(AbstractConfigurationPanel configurator,
			Object source);

}