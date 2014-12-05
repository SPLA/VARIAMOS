package com.variamos.gui.refas.editor.widgets;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.mxgraph.view.mxGraph;
import com.variamos.refas.core.staticconcepts.SemanticPlusSyntax;
import com.variamos.syntaxsupport.metamodel.InstAttribute;

@SuppressWarnings("serial")
public abstract class WidgetR extends JPanel{
	
	public static final String PROPERTY_VALUE = "WidgetR.Value";
	
	protected InstAttribute edited;
	
	public void editVariable(InstAttribute v){
		edited = v;
		pushValue(v);
	}
	
	protected abstract void pushValue(InstAttribute v);
	protected abstract void pullValue(InstAttribute v);
	
	public abstract JComponent getEditor();


	public void configure(InstAttribute v, SemanticPlusSyntax semanticSyntaxObject, mxGraph graph) {
		
	}


	public InstAttribute getInstAttribute() {
		pullValue(edited);
		return edited;
	}
}
