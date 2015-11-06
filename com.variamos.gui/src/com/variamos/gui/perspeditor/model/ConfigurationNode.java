package com.variamos.gui.perspeditor.model;

import java.util.ArrayList;
import java.util.List;

import com.cfm.productline.Variable;
import com.variamos.configurator.DomainAnnotation;

/**
 * A class to support the creation of nodes for the visual representation of the
 * association between semantic expressions and operations. Part of PhD work at
 * University of Paris 1. Initially copied from
 * com.variamos.gui.pl.configuration.treetable.ConfigurationNode
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-11-06
 * @see com.variamos.gui.pl.configuration.treetable.ConfigurationNode
 */
public class ConfigurationNode {

	private Variable variable;
	private int stepEdited;
	private List<DomainAnnotation> domainAnnotations = new ArrayList<>();

	protected List<ConfigurationNode> children;

	public ConfigurationNode() {
		children = new ArrayList<>();
		// DomainAnnotation def = new DomainAnnotation(0, Choice.CROSS, 5);
		// domainAnnotations.add(def);
	}

	public String getName() {
		if (variable == null)
			return "";

		return variable.getName();
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	public int getStepEdited() {
		return stepEdited;
	}

	public void setStepEdited(int stepEdited) {
		this.stepEdited = stepEdited;
	}

	// public Integer getValue() {
	// if( variable == null )
	// return 0;
	//
	// return (Integer)variable.getValue();
	// }
	//

	public void setValue(Integer value) {
		if (variable != null)
			variable.setValue(value);
	}

	@Override
	public String toString() {
		if (variable == null)
			return "";
		return variable.getName();
	}

	public List<ConfigurationNode> getChildren() {
		return children;
	}

	public void setChildren(List<ConfigurationNode> children) {
		this.children = children;
	}

	public List<DomainAnnotation> getDomainAnnotations() {
		return domainAnnotations;
	}

	public DomainAnnotation getAnnotationFor(Integer i) {
		for (DomainAnnotation dm : domainAnnotations) {
			if (dm.getValue() == i)
				return dm;
		}
		return null;
	}

	// public Domain getDomain(){
	// return variable.
	// }

}
