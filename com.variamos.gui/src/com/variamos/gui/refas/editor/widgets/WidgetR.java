package com.variamos.gui.refas.editor.widgets;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.mxgraph.view.mxGraph;
import com.variamos.gui.refas.editor.SemanticPlusSyntax;
import com.variamos.syntaxsupport.metamodelsupport.EditableElementAttribute;

@SuppressWarnings("serial")
public abstract class WidgetR extends JPanel{
	
	public static final String PROPERTY_VALUE = "WidgetR.Value";
	private boolean affectProperties = false;
	
	protected EditableElementAttribute edited;
	
	public void editVariable(EditableElementAttribute v){
		edited = v;
		pushValue(v);
	}
	
	protected abstract void pushValue(EditableElementAttribute v);
	protected abstract void pullValue(EditableElementAttribute v);
	
	public abstract JComponent getEditor();

	public boolean isAffectedProperties()
	{
		return affectProperties;
	}

	public void configure(EditableElementAttribute v, SemanticPlusSyntax semanticSyntaxObject, mxGraph graph) {
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