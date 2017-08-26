package com.variamos.gui.perspeditor.model;

import com.cfm.productline.Variable;
import com.variamos.gui.perspeditor.panels.ElementsOperationAssociationPanel;

/**
 * A class to support the configuration actions for the visual representation of
 * the association between semantic expressions and operations. Part of PhD work
 * at University of Paris 1. Initially copied from
 * com.variamos.gui.pl.configuration.treetable.ConfigurationAction
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-11-06
 * @see com.variamos.gui.pl.AssociationAction.treetable.ConfigurationAction
 */
public abstract class AssociationAction {
	protected Variable variable;
	protected int column;

	public AssociationAction(Variable variable, int column) {
		super();
		this.variable = variable;
		this.column = column;
	}

	public Variable getVariable() {
		return variable;
	}

	public int getColumn() {
		return column;
	}

	public abstract void undo(ElementsOperationAssociationPanel configurator,
			Object source);

	public abstract void execute(
			ElementsOperationAssociationPanel configurator, Object source);

}
