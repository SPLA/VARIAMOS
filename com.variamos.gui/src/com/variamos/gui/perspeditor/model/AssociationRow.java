package com.variamos.gui.perspeditor.model;

import java.util.ArrayList;
import java.util.List;

import com.cfm.productline.Variable;
import com.variamos.configurator.DomainAnnotation;
import com.variamos.hlcl.BinaryDomain;

/**
 * A class to support the creation of nodes for the visual representation of the
 * association between semantic expressions and operations. Part of PhD work at
 * University of Paris 1. Initially copied from
 * com.variamos.gui.pl.configuration.treetable.ConfigurationNode
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-11-06
 * @see com.variamos.gui.pl.AssociationRow.treetable.ConfigurationNode
 */
public class AssociationRow {

	// private Variable variable;
	private List<Variable> values;
	private String name;
	private int stepEdited;
	private List<DomainAnnotation> domainAnnotations = new ArrayList<>();

	protected List<AssociationRow> children;

	public AssociationRow(String name) {
		children = new ArrayList<>();
		values = new ArrayList<Variable>();
		Variable var = new Variable(name, 0, Integer.class.getTypeName());
		var.setDomain(BinaryDomain.INSTANCE);
		values.add(var);
		var = new Variable(name, 0, Integer.class.getTypeName());
		var.setDomain(BinaryDomain.INSTANCE);
		values.add(var);
		var = new Variable(name, 0, Integer.class.getTypeName());
		var.setDomain(BinaryDomain.INSTANCE);
		values.add(var);
		var = new Variable(name, 0, Integer.class.getTypeName());
		var.setDomain(BinaryDomain.INSTANCE);
		values.add(var);
		var = new Variable(name, 0, Integer.class.getTypeName());
		var.setDomain(BinaryDomain.INSTANCE);
		values.add(var);
		this.name = name;
		// DomainAnnotation def = new DomainAnnotation(0, Choice.CROSS, 5);
		// domainAnnotations.add(def);
	}

	public String getName() {
		return name;
	}

	public Variable getValue(int column) {
		return values.get(column);
	}

	public List<Variable> getValues() {
		return values;
	}

	/*
	 * public Variable getVariable() { return variable; }
	 * 
	 * public void setVariable(Variable variable) { this.variable = variable; }
	 */
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

	public void setValue(Integer value, int column) {
		values.get(column).setValue(value);
	}

	@Override
	public String toString() {
		return getName();
	}

	public List<AssociationRow> getChildren() {
		return children;
	}

	public void setChildren(List<AssociationRow> children) {
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
