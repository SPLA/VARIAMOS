package com.variamos.gui.perspeditor.widgets;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JTextField;

import com.variamos.perspsupport.syntaxsupport.EditableElementAttribute;

/**
 * Not a real need founded jet. Copied from RealWidget
 * from ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-10
 * @see com.variamos.gui.pl.editor.widgets.RealWidget
 */
@SuppressWarnings("serial")
public class RealWidget extends WidgetR {

	private JTextField txtValue;
	
	public RealWidget() {
		super();

		setLayout(new BorderLayout());
		txtValue = new JTextField();		
		add(txtValue, BorderLayout.CENTER);
		revalidate();
	}

	@Override
	protected boolean pushValue(EditableElementAttribute v) {
		txtValue.setText(String.valueOf( v.getAsFloat() ));
		revalidate();
		repaint();
		return false;
	}

	@Override
	protected void pullValue(EditableElementAttribute v) {
		float val = 0;
		
		if( !txtValue.getText().isEmpty() )
			val = Float.parseFloat(txtValue.getText());
		
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
