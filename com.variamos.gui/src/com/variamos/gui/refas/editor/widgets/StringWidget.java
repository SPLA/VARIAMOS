package com.variamos.gui.refas.editor.widgets;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.variamos.syntaxsupport.metamodel.InstAttribute;

@SuppressWarnings("serial")
public class StringWidget extends WidgetR {

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
	protected void pushValue(InstAttribute v) {
		txtValue.setText( (String) v.getValue() );
		revalidate();
		repaint();
	}

	@Override
	protected void pullValue(InstAttribute v) {
		v.setValue(txtValue.getText());
	}

	@Override
	public JComponent getEditor() {
		return txtValue;
	}
	
}
