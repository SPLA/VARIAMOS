package com.cfm.productline.editor.widgets;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import com.cfm.productline.Variable;

@SuppressWarnings("serial")
public class BooleanWidget extends Widget {
	
	private JCheckBox chkValue;
	
	public BooleanWidget(){
		super();
		setLayout(new BorderLayout());
		
		chkValue = new JCheckBox();
		
		chkValue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chkValue.firePropertyChange(Widget.PROPERTY_VALUE, !chkValue.isSelected(),chkValue.isSelected() );
			}
		});
		
		add(chkValue, BorderLayout.CENTER);
		revalidate();
	}
	
	@Override
	protected void pushValue(Variable v) {
		chkValue.setText(String.valueOf(v.getName()));
		chkValue.setSelected( v.getAsBoolean() );
		
		revalidate();
		repaint();
	}

	@Override
	protected void pullValue(Variable v) {
		v.setValue(chkValue.isSelected());
	}

	@Override
	public JComponent getEditor() {
		return chkValue;
	}
}
