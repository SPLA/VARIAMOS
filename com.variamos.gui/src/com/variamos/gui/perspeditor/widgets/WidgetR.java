package com.variamos.gui.perspeditor.widgets;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.mxgraph.view.mxGraph;
import com.variamos.perspsupport.syntaxsupport.EditableElementAttribute;

@SuppressWarnings("serial")
public abstract class WidgetR extends JPanel{
	
	public static final String PROPERTY_VALUE = "WidgetR.Value";
	private boolean affectProperties = false;
	
	protected EditableElementAttribute edited;
	
	public boolean editVariable(EditableElementAttribute v){
		edited = v;
		return pushValue(v);
	}
	
	protected abstract boolean pushValue(EditableElementAttribute v);
	protected abstract void pullValue(EditableElementAttribute v);
	
	public abstract JComponent getEditor();

	public boolean isAffectedProperties()
	{
		return affectProperties;
	}

	public void configure(EditableElementAttribute v, mxGraph graph) {
		affectProperties = v.isAffectProperties();
		
	}

	
	public EditableElementAttribute getInstAttribute() {
		pullValue(edited);
		return edited;
	}
	
	public boolean isAffectProperties()
	{
		return affectProperties;
	}
	
	public void setAffectProperties(boolean affectProperties)
	{
		this.affectProperties = affectProperties;
	}
}