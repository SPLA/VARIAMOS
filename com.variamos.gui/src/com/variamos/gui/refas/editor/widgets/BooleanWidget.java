package com.variamos.gui.refas.editor.widgets;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import com.variamos.syntaxsupport.metamodelsupport.EditableElementAttribute;

/**
 * A class to support boolean widgets on the interface. Copied on BooleanWidget
 * from ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-10
 * @see com.variamos.gui.pl.editor.widgets.BooleanWidget
 */
@SuppressWarnings("serial")
public class BooleanWidget extends WidgetR {

	private JCheckBox chkValue;

	public BooleanWidget() {
		super();
		setLayout(new BorderLayout());

		chkValue = new JCheckBox();

		chkValue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				chkValue.firePropertyChange(WidgetR.PROPERTY_VALUE,
						!chkValue.isSelected(), chkValue.isSelected());
			}
		});

		add(chkValue, BorderLayout.CENTER);
		revalidate();
	}

	@Override
	protected void pushValue(EditableElementAttribute v) {
		chkValue.setText(String.valueOf(v.getIdentifier()));
		chkValue.setSelected(v.getAsBoolean());

		revalidate();
		repaint();
	}

	@Override
	protected void pullValue(EditableElementAttribute v) {
		v.setValue(chkValue.isSelected());
	}

	@Override
	public JComponent getEditor() {
		return chkValue;
	}
}
