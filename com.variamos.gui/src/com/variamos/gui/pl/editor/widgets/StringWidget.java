package com.variamos.gui.pl.editor.widgets;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.cfm.productline.Variable;

@SuppressWarnings("serial")
public class StringWidget extends WidgetPL {

	private JTextField txtValue;
	
	public StringWidget() {
		super();
		setLayout(new BorderLayout());
		txtValue = new JTextField();
		txtValue.setMaximumSize(new Dimension(200, 30));
		add(txtValue, BorderLayout.CENTER);
		revalidate();
	}

	@Override
	protected void pushValue(Variable v) {
		txtValue.setText( (String) v.getValue() );
		revalidate();
		repaint();
	}

	@Override
	protected void pullValue(Variable v) {
		v.setValue(txtValue.getText());
	}

	@Override
	public JComponent getEditor() {
		return txtValue;
	}
	
}
