package com.variamos.gui.pl.editor.widgets;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.cfm.productline.Variable;

@SuppressWarnings("serial")
public class RealWidget extends WidgetPL {

	private JTextField txtValue;
	
	public RealWidget() {
		super();

		setLayout(new BorderLayout());
		txtValue = new JTextField();		
		add(txtValue, BorderLayout.CENTER);
		revalidate();
	}

	@Override
	protected void pushValue(Variable v) {
		txtValue.setText(String.valueOf( v.getAsFloat() ));
		revalidate();
		repaint();
	}

	@Override
	protected void pullValue(Variable v) {
		float val = 0;
		
		if( !txtValue.getText().isEmpty() )
			val = Float.parseFloat(txtValue.getText());
		
		v.setValue(val);
	}

	@Override
	public JComponent getEditor() {
		return txtValue;
	}
}
