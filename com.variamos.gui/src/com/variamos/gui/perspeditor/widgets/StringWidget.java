package com.variamos.gui.perspeditor.widgets;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.variamos.dynsup.interfaces.IntInstAttribute;

/**
 * A class to support string widgets on the interface. Copied from StringWidget
 * from ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-10
 * @see com.variamos.gui.pl.editor.widgets.StringWidget
 */
@SuppressWarnings("serial")
public class StringWidget extends WidgetR {

	private JTextField txtValue;

	public StringWidget() {
		super();
		setLayout(new BorderLayout());
		txtValue = new JTextField();
		txtValue.setMaximumSize(new Dimension(200, 30));
		add(txtValue, BorderLayout.CENTER);
	}

	@Override
	protected boolean pushValue(IntInstAttribute v) {
		if (v.getValue() instanceof String)
			txtValue.setText((String) v.getValue());
		group.setText((String) v.getGroup());
		revalidate();
		repaint();
		return false;
	}

	@Override
	protected void pullValue(IntInstAttribute v) {
		if (v != null) {
			v.setValue(txtValue.getText());
			v.setGroup(group.getText());
		}
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
