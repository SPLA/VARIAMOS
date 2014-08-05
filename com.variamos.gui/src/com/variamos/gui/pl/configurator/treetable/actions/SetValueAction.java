package com.variamos.gui.pl.configurator.treetable.actions;

import com.cfm.productline.Variable;
import com.variamos.gui.pl.editor.ConfiguratorPanel;

public class SetValueAction extends ChangeAction<Integer> {

	public SetValueAction(Variable variable, int index, Integer value) {
		super(variable, index, value);
	}

	@Override
	protected void executeSetValue(ConfiguratorPanel configurator, Integer value) {
		configurator.setValueToVariable(variable, value, getIndex());
	}
	
	
}
