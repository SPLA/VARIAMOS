package com.variamos.gui.pl.editor.widgets;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.cfm.productline.Variable;

@SuppressWarnings("serial")
public class SetWidget extends WidgetPL{
	
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
	protected void pushValue(Variable v) {
		comboBox.setSelectedItem( v.getValue() );
	}

	@Override
	protected void pullValue(Variable v) {
		v.setType( (String)comboBox.getSelectedItem() );
	}

	@Override
	public JComponent getEditor() {
		return comboBox;
	}

	@Override
	public void configure(Variable v) {
//		SetDomain set = (SetDomain)d;
//		
//		for( String elm : set.getElements() )
//			comboBox.addItem(elm);
//		
//		if( edited != null )
//			comboBox.setSelectedItem( edited.getValue() );
	}

}