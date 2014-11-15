package com.cfm.productline.constraints;

import java.util.ArrayList;
import java.util.List;

import com.cfm.productline.Constraint;
import com.cfm.productline.Variable;
import com.cfm.productline.type.StringType;

public class GenericConstraint extends Constraint {
	
	private Variable varText = StringType.newVariable("Text");
	//protected String text;
	
	public GenericConstraint() {
		super();
	}

	public GenericConstraint(String formula) {
		//this.text = formula;
		varText.setValue(formula);
	}

	public String getText() {
		return (String) varText.getValue();
	}

	public void setText(String text) {
		varText.setValue(text);
	}
	
	@Override
	public List<String> getRelatedIds() {
		return new ArrayList<String>();
	}
	@Override
	public Variable[] getEditableVariables() {
		return new Variable[]{ varText };
	}
}
