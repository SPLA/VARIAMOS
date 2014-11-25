package com.variamos.gui.refas.editor.widgets;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.cfm.productline.Variable;
import com.variamos.syntaxsupport.metamodel.InstAttribute;

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
	protected void pushValue(InstAttribute v) {
		txtValue.setText(String.valueOf( v.getAsInteger() ));
		revalidate();
		repaint();
	}

	@Override
	protected void pullValue(InstAttribute v) {
		int val = 0;
		
		if( !txtValue.getText().isEmpty() )
			val = Integer.parseInt(txtValue.getText());
		
		v.setValue(val);
	}

	@Override
	public JComponent getEditor() {
		return txtValue;
	}


}
