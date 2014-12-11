package com.variamos.gui.refas.editor.widgets;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.mxgraph.view.mxGraph;
import com.variamos.refas.core.staticconcepts.SemanticPlusSyntax;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstVertex;

/**
 * A class to support set widgets on the interface. Copied from SetWidget
 * from ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-10
 * @see com.variamos.gui.pl.editor.widgets.SetWidget
 */
@SuppressWarnings("serial")
public class SetWidget extends WidgetR{
	
	private JComboBox<String> comboBox;
	private Map<String, InstAttribute> instAttributes;
	
	public SetWidget(){
		
		comboBox = new JComboBox<>();
//		comboBox.setPreferredSize(new Dimension(100, 50));
		setLayout(new BorderLayout());
		add(comboBox, BorderLayout.CENTER);
		revalidate();

		//comboBox.setEditable(false);
	}
	
	@Override
	protected void pushValue(InstAttribute v) {
		comboBox.setSelectedItem( v.getValue() );
	}

	@Override
	protected void pullValue(InstAttribute v) {
		v.setType( (String)comboBox.getSelectedItem() );
	}

	@Override
	public JComponent getEditor() {
		return comboBox;
	}

	@Override
	public void configure(InstAttribute v, SemanticPlusSyntax semanticSyntaxObject, mxGraph graph) {}
}
