package com.variamos.gui.pl.configurator.treetable;

import java.util.ArrayList;
import java.util.List;

import com.cfm.productline.Variable;
import com.variamos.pl.configurator.DomainAnnotation;

public class ConfigurationNode {
	
	private Variable variable;
	private int stepEdited;
	private List<DomainAnnotation> domainAnnotations = new ArrayList<>();
	
	protected List<ConfigurationNode> children;
	
	public ConfigurationNode() {
		children = new ArrayList<>();
		//DomainAnnotation def = new DomainAnnotation(0, Choice.CROSS, 5);
		//domainAnnotations.add(def);
	}
	
	public String getName(){
		if( variable == null )
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

//	public Integer getValue() {
//		if( variable == null )
//			return 0;
//		
//		return (Integer)variable.getValue();
//	}
//
	
	public void setValue(Integer value) {
		if( variable != null )
			variable.setValue(value);
	}
	
	@Override
	public String toString() {
		if( variable == null )
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
	
	public DomainAnnotation getAnnotationFor(Integer i){
		for (DomainAnnotation dm : domainAnnotations) {
			if( dm.getValue() == i )
				return dm;
		}
		return null;
	}
	
//	public Domain getDomain(){
//		return variable.
//	}
	
	
}
