package com.variamos.gui.refas.editor.widgets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.mxgraph.view.mxGraph;
import com.variamos.gui.refas.editor.SemanticPlusSyntax;
import com.variamos.syntax.instancesupport.InstAttribute;
import com.variamos.syntax.metamodelsupport.EditableElementAttribute;
import com.variamos.syntax.types.EnumerationMultiSelectionType;

/**
 * A class to support enumeration widgets on the interface with multi-selection.
 * Inspired on other widgets from ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-04
 * @see com.variamos.gui.pl.editor.widgets
 */
@SuppressWarnings("serial")
public class MEnumerationWidget extends WidgetR {
	
	private JList<String> txtValue;
	private Object[] enumeration;
	
	public MEnumerationWidget(){
		super();
		
		setLayout(new BorderLayout());
		
	}
	
	@Override
	public void configure(EditableElementAttribute v, SemanticPlusSyntax semanticSyntaxObject, mxGraph graph) {
		super.configure(v, semanticSyntaxObject, graph);
		ClassLoader classLoader = EnumerationMultiSelectionType.class.getClassLoader();
		@SuppressWarnings("rawtypes")
		Class aClass = null;
		InstAttribute instAttribute = (InstAttribute)v;
	    try {
	    	aClass = classLoader.loadClass(instAttribute.getAttribute().getClassCanonicalName());
	        //System.out.println("aClass.getName() = " + aClass.getName());
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
		enumeration= aClass.getEnumConstants();
		String[] out = new String[enumeration.length];
		for (int i = 0; i< enumeration.length; i++)
		{
			out[i] = enumeration[i].toString();	
		}
		txtValue = new JList<String>(out);
		JScrollPane panel = new JScrollPane(txtValue);
		panel.setPreferredSize(new Dimension(150, 60));
		panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(panel, BorderLayout.CENTER);
		revalidate();
	}
	
	@Override
	protected boolean pushValue(EditableElementAttribute v) {
		if (v.getValue() instanceof int[])
			txtValue.setSelectedIndices((int[])v.getValue() );
		revalidate();
		repaint();
		return false;
	}

	@Override
	protected void pullValue(EditableElementAttribute v) {
		v.setValue(txtValue.getSelectedIndices());
		String out = "";
		List<String> tmp = txtValue.getSelectedValuesList();
		for (String str : tmp)
			out += str+";";

		InstAttribute instAttribute = (InstAttribute)v;
		instAttribute.displayValue(out);
	}

	@Override
	public JComponent getEditor() {
		return txtValue;
	}


}
