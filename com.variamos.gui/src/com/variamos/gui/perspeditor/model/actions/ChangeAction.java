package com.variamos.gui.perspeditor.model.actions;

import com.variamos.dynsup.types.Variable;
import com.variamos.gui.perspeditor.model.AssociationAction;
import com.variamos.gui.perspeditor.panels.ElementsOperationAssociationPanel;

/**
 * A class to support the change action for the visual representation of the
 * association between semantic expressions and operations. Part of PhD work at
 * University of Paris 1. Initially copied from
 * com.variamos.gui.pl.configuration.treetable.actions.ChangeAction
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-11-06
 * @see com.variamos.gui.pl.configuration.treetable.actions.ChangeAction
 */
public abstract class ChangeAction<T> extends AssociationAction {
	protected T oldValue, newValue;

	public ChangeAction(Variable variable, T value, int column) {
		super(variable, column);
		newValue = value;
	}

	@Override
	public void undo(ElementsOperationAssociationPanel configurator,
			Object source) {
		executeSetValue(configurator, oldValue, column, source);
	}

	protected abstract void executeSetValue(
			ElementsOperationAssociationPanel configurator, T value,
			int column, Object source);

	@Override
	public void execute(ElementsOperationAssociationPanel configurator,
			Object source) {
		// oldValue = (T)variable.getValue();
		executeSetValue(configurator, newValue, column, source);
	}

}
