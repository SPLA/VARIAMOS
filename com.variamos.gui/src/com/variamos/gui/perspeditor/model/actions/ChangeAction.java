package com.variamos.gui.perspeditor.model.actions;

import com.cfm.productline.Variable;
import com.variamos.gui.common.jelements.AbstractConfigurationPanel;
import com.variamos.gui.perspeditor.model.ConfigurationAction;

/**
 * A class to support the change action for the visual representation of the
 * association between semantic expressions and operations. Part of PhD work at
 * University of Paris 1. Initially copied from
 * com.variamos.gui.pl.configuration.treetable.actions.ChangeAction
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-11-06
 * @see com.variamos.gui.pl.configuration.treetable.actions.ChangeAction
 */
public abstract class ChangeAction<T> extends ConfigurationAction {
	protected T oldValue, newValue;

	public ChangeAction(Variable variable, int index, T value) {
		super(variable, index);
		newValue = value;
	}

	@Override
	public void undo(AbstractConfigurationPanel configurator) {
		executeSetValue(configurator, oldValue);
	}

	protected abstract void executeSetValue(
			AbstractConfigurationPanel configurator, T value);

	@Override
	public void execute(AbstractConfigurationPanel configurator) {
		// oldValue = (T)variable.getValue();
		executeSetValue(configurator, newValue);
	}

}
