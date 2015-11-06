package com.variamos.gui.perspeditor.model.actions;

import com.cfm.productline.Variable;
import com.variamos.gui.common.jelements.AbstractConfigurationPanel;

/**
 * A class to support the change action for the visual representation of the
 * association between semantic expressions and operations. Part of PhD work at
 * University of Paris 1. Initially copied from
 * com.variamos.gui.pl.configuration.treetable.actions.SetValueAction
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-11-06
 * @see com.variamos.gui.pl.configuration.treetable.actions.SetValueAction
 */
public class SetValueAction extends ChangeAction<Integer> {

	public SetValueAction(Variable variable, int index, Integer value) {
		super(variable, index, value);
	}

	@Override
	protected void executeSetValue(AbstractConfigurationPanel configurator,
			Integer value) {
		configurator.setValueToVariable(variable, value, getIndex());
	}

}
