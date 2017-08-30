package com.variamos.gui.perspeditor.model.actions;

import com.variamos.dynsup.types.Variable;
import com.variamos.gui.perspeditor.panels.ElementsOperationAssociationPanel;

/**
 * A class to support the change action for the visual representation of the
 * association between semantic expressions and operations. Part of PhD work at
 * University of Paris 1. Initially copied from
 * com.variamos.gui.pl.configuration.treetable.actions.SetValueAction
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-11-06
 * @see com.variamos.gui.pl.SetIntegerValueAction.treetable.actions.SetValueAction
 */
public class SetStringValueAction extends ChangeAction<String> {

	public SetStringValueAction(Variable variable, String value, int column) {
		super(variable, value, column);
	}

	@Override
	protected void executeSetValue(
			ElementsOperationAssociationPanel configurator, String value,
			int column, Object source) {
		configurator.setValueToVariable(variable, value, column, source);
	}
}
