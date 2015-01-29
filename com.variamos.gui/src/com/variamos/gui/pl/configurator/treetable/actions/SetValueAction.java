package com.variamos.gui.pl.configurator.treetable.actions;

import com.cfm.productline.Variable;
import com.variamos.gui.common.jelements.AbstractConfigurationPanel;

public class SetValueAction extends ChangeAction<Integer> {

	public SetValueAction(Variable variable, int index, Integer value) {
		super(variable, index, value);
	}

	@Override
	protected void executeSetValue(AbstractConfigurationPanel configurator, Integer value) {
		configurator.setValueToVariable(variable, value, getIndex());
	}
	
	
}
