package com.cfm.productline.configurator.treetable.actions;

import com.cfm.productline.Variable;
import com.cfm.productline.configurator.Configurator;

public class SetValueAction extends ChangeAction<Integer> {

	public SetValueAction(Variable variable, int index, Integer value) {
		super(variable, index, value);
	}

	@Override
	protected void executeSetValue(Configurator configurator, Integer value) {
		configurator.setValueToVariable(variable, value, getIndex());
	}
	
	
}
