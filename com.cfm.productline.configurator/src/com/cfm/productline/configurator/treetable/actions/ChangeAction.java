package com.cfm.productline.configurator.treetable.actions;

import com.cfm.productline.Variable;
import com.cfm.productline.configurator.Configurator;
import com.cfm.productline.configurator.treetable.ConfigurationAction;

public abstract class ChangeAction<T> extends ConfigurationAction{
	protected T oldValue, newValue;
	
	public ChangeAction(Variable variable, int index, T value) {
		super(variable, index);
		newValue = value;
	}

	@Override
	public void undo(Configurator configurator) {
		executeSetValue(configurator, oldValue);
	}

	protected abstract void executeSetValue(Configurator configurator, T value);

	@Override
	public void execute(Configurator configurator) {
		oldValue = (T)variable.getValue();
		executeSetValue(configurator, newValue);
	}

}
