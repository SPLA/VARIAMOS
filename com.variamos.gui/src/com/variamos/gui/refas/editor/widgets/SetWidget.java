package com.variamos.gui.refas.editor.widgets;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.mxgraph.view.mxGraph;
import com.variamos.refas.core.staticconcepts.SemanticPlusSyntax;
import com.variamos.syntaxsupport.metamodel.InstAttribute;

@SuppressWarnings("serial")
public class SetWidget extends WidgetR{
	
	private JComboBox<String> comboBox;
	
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
	public void configure(InstAttribute v, SemanticPlusSyntax semanticSyntaxObject, mxGraph graph) {
//		SetDomain set = (SetDomain)d;
//		
//		for( String elm : set.getElements() )
//			comboBox.addItem(elm);
//		
//		if( edited != null )
//			comboBox.setSelectedItem( edited.getValue() );
	}

}
