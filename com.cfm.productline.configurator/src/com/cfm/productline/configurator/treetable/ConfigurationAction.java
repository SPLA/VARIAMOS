package com.cfm.productline.configurator.treetable;

import com.cfm.productline.Variable;
import com.cfm.productline.configurator.Configurator;

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

	public abstract void undo(Configurator configurator);
	public abstract void execute(Configurator configurator);
	
}
