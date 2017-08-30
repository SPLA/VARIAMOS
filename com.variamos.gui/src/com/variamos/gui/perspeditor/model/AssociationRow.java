package com.variamos.gui.perspeditor.model;

import java.util.ArrayList;
import java.util.List;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.types.ElementVariable;
import com.variamos.dynsup.types.IntegerVariable;
import com.variamos.dynsup.types.Variable;
import com.variamos.hlcl.model.domains.BinaryDomain;
import com.variamos.hlcl.model.domains.IntDomain;
import com.variamos.hlcl.model.domains.IntervalDomain;

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
	private List<Variable> variables;
	private InstElement element;
	private String name;
	private int stepEdited;
	private boolean leaf;
	private List<DomainAnnotation> domainAnnotations = new ArrayList<>();
	private Object source = null;

	protected List<AssociationRow> children;

	public AssociationRow(InstElement element, String name, int size,
			boolean leaf, List<IntDomain> domains, List<Integer> values,
			Object source) {
		this.element = element;
		this.leaf = leaf;
		this.source = source;
		String elemId = "";
		if (element != null)
			elemId = element.getIdentifier() + "-";
		children = new ArrayList<>();
		variables = new ArrayList<Variable>();
		for (int i = 0; i < size; i++) {
			Object defaultValue = null;
			if (leaf)
				defaultValue = 0;
			Variable var = null;
			if (domains.get(i) instanceof BinaryDomain) {
				var = new Variable(elemId + name, defaultValue,
						Integer.class.getTypeName());
				var.setDomain(domains.get(i));
			} else if (domains.get(i) instanceof IntervalDomain) {
				var = new IntegerVariable(elemId + "-" + name, defaultValue,
						Integer.class.getTypeName());
				var.setDomain(domains.get(i));
			} else {
				var = new ElementVariable(elemId + "-" + name, defaultValue,
						String.class.getTypeName());
				var.setDomain(domains.get(i));
			}
			if (values != null)
				var.setValue(values.get(i));
			variables.add(var);
		}

		this.name = name;
		// DomainAnnotation def = new DomainAnnotation(0, Choice.CROSS, 5);
		// domainAnnotations.add(def);
	}

	public String getFullId() {
		if (element != null)
			return element.getIdentifier() + "-" + name;
		else
			return name;
	}

	public String getName() {
		return name;
	}

	public Variable getValue(int column) {
		return variables.get(column - 1);
	}

	public List<Variable> getValues() {
		return variables;
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

	public void setValue(Object value, int column) {
		variables.get(column - 1).setValue(value);
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

	public DomainAnnotation getAnnotationFor(String i) {
		for (DomainAnnotation dm : domainAnnotations) {
			if (i.equals(dm.getValue()))
				return dm;
		}
		return null;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public Object getSource() {
		return source;
	}

	// public Domain getDomain(){
	// return variable.
	// }

}
