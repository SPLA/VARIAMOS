package com.variamos.gui.perspeditor.model;

import com.cfm.productline.Variable;
import com.variamos.gui.common.jelements.AbstractConfigurationPanel;

/**
 * A class to support the configuration actions for the visual representation of
 * the association between semantic expressions and operations. Part of PhD work
 * at University of Paris 1. Initially copied from
 * com.variamos.gui.pl.configuration.treetable.ConfigurationAction
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-11-06
 * @see com.variamos.gui.pl.configuration.treetable.ConfigurationAction
 */
public abstract class ConfigurationAction {
	protected Variable variable;
	protected int index;

	public ConfigurationAction(Variable variable, int index) {
		super();
		this.variable = variable;
		this.index = index;
	}

	public Variable getVariable() {
		return variable;
	}

	public int getIndex() {
		return index;
	}

	public abstract void undo(AbstractConfigurationPanel configurator);

	public abstract void execute(AbstractConfigurationPanel configurator);

}
