package com.variamos.gui.pl.configurator.treetable.actions;

import com.cfm.productline.Variable;
import com.variamos.gui.pl.configurator.treetable.ConfigurationAction;
import com.variamos.gui.pl.editor.ConfiguratorPanel;

public abstract class ChangeAction<T> extends ConfigurationAction{
	protected T oldValue, newValue;
	
	public ChangeAction(Variable variable, int index, T value) {
		super(variable, index);
		newValue = value;
	}

	@Override
	public void undo(ConfiguratorPanel configurator) {
		executeSetValue(configurator, oldValue);
	}

	protected abstract void executeSetValue(ConfiguratorPanel configurator, T value);

	@Override
	public void execute(ConfiguratorPanel configurator) {
		//oldValue = (T)variable.getValue();
		executeSetValue(configurator, newValue);
	}

}
