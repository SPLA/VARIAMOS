package com.variamos.gui.pl.configurator.treetable;

import com.cfm.productline.Variable;
import com.variamos.gui.pl.editor.ConfiguratorPanel;

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

	public abstract void undo(ConfiguratorPanel configurator);
	public abstract void execute(ConfiguratorPanel configurator);
	
}
