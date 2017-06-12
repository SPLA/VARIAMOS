package com.variamos.gui.perspeditor.widgets;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.mxgraph.view.mxGraph;
import com.variamos.core.util.StringUtils;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.interfaces.IntInstAttribute;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.types.EnumerationSingleSelectionType;
import com.variamos.io.ConsoleTextArea;

/**
 * A class to support enumeration widgets on the interface. Inspired on other
 * widgets from ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-01
 * @see com.variamos.gui.pl.editor.widgets
 */
@SuppressWarnings("serial")
public class EnumerationWidget extends WidgetR {

	private JComboBox<String> txtValue;
	private Object[] enumeration;

	public EnumerationWidget() {
		super();

		setLayout(new BorderLayout());
		txtValue = new JComboBox<String>();
		add(txtValue, BorderLayout.CENTER);
		revalidate();
	}

	@Override
	public void configure(IntInstAttribute v, mxGraph graph,
			ModelInstance semanticModel,
			boolean showSimulationCustomizationBox, int perspective) {
		super.configure(v, graph, semanticModel,
				showSimulationCustomizationBox, perspective);
		ClassLoader classLoader = EnumerationSingleSelectionType.class
				.getClassLoader();
		@SuppressWarnings("rawtypes")
		Class aClass = null;
		IntInstAttribute instAttribute = v;
		if (instAttribute instanceof InstAttribute) {
			try {
				aClass = classLoader.loadClass(((InstAttribute) instAttribute)
						.getAttribute().getClassCanonicalName());
				// System.out.println("aClass.getName() = " + aClass.getName());
			} catch (ClassNotFoundException e) {
				ConsoleTextArea.addText(((InstAttribute) instAttribute)
						.getAttribute().getClassCanonicalName());
				ConsoleTextArea.addText(e.getStackTrace());
			}
			enumeration = aClass.getEnumConstants();
		}
		if (enumeration != null)
			for (int i = 0; i < enumeration.length; i++) {
				String out = StringUtils.formatEnumValue(enumeration[i]
						.toString());
				txtValue.addItem(out);
				if (instAttribute.getValue() != null
						&& out.equals(instAttribute.getValue()))
					txtValue.setSelectedItem(out);
			}
		if (instAttribute.getValue() == null && txtValue.getItemCount() > 0) {
			txtValue.setSelectedIndex(0);
			instAttribute.setValue(txtValue.getSelectedItem());
		}
		revalidate();
		repaint();
	}

	@Override
	protected boolean pushValue(IntInstAttribute v) {
		txtValue.setSelectedItem(v.getValue());
		group.setText((String) v.getGroup());
		revalidate();
		repaint();
		return false;
	}

	@Override
	protected void pullValue(IntInstAttribute v) {
		v.setValue(txtValue.getSelectedItem());
		v.setGroup(group.getText());
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
