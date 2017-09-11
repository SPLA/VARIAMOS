package com.variamos.gui.perspeditor.widgets;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.variamos.dynsup.interfaces.IntInstAttribute;

/**
 * A class to support integer widgets on the interface. Copied on BooleanWidget
 * from ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-10
 * @see com.variamos.gui.pl.editor.widgets.IntegerWidget
 */
@SuppressWarnings("serial")
public class IntegerWidget extends WidgetR {
	
	private JTextField txtValue;
	
	public IntegerWidget(){
		super();
		
		setLayout(new BorderLayout());
		txtValue = new JTextField();
		add(txtValue, BorderLayout.CENTER);
		revalidate();
	}
	
	@Override
	protected boolean pushValue(IntInstAttribute v) {
		txtValue.setText(String.valueOf( v.getAsInteger() ));
		group.setText((String) v.getGroup());
		revalidate();
		repaint();
		return false;
	}

	@Override
	protected void pullValue(IntInstAttribute v) {
		int val = 0;
		
		if( !txtValue.getText().isEmpty() )
			val = Integer.parseInt(txtValue.getText());

		v.setGroup(group.getText());
		v.setValue(val);
	}

	@Override
	public JComponent getEditor() {
		return txtValue;
	}
	
	@Override
	public JComponent getGroup() {
		return group;
	}

}
