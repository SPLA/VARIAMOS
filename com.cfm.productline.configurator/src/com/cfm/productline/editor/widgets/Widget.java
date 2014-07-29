package com.cfm.productline.editor.widgets;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.cfm.productline.Variable;

@SuppressWarnings("serial")
public abstract class Widget extends JPanel{
	
	public static final String PROPERTY_VALUE = "Widget.Value";
	
	protected Variable edited;
	
	public void editVariable(Variable v){
		edited = v;
		pushValue(v);
	}
	

	public Variable getVariable(){
		pullValue(edited);
		return edited;
	}
	
	protected abstract void pushValue(Variable v);
	protected abstract void pullValue(Variable v);
	
	public abstract JComponent getEditor();


	public void configure(Variable v) {
		
	}
}
