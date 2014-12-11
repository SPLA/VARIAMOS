package com.variamos.gui.refas.editor.widgets;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.variamos.syntaxsupport.metamodel.InstAttribute;

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
	protected void pushValue(InstAttribute v) {
		txtValue.setText(String.valueOf( v.getAsFloat() ));
		revalidate();
		repaint();
	}

	@Override
	protected void pullValue(InstAttribute v) {
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
