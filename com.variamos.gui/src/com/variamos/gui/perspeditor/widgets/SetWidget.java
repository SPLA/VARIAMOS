package com.variamos.gui.perspeditor.widgets;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.mxgraph.view.mxGraph;
import com.variamos.perspsupport.perspmodel.RefasModel;
import com.variamos.perspsupport.syntaxsupport.EditableElementAttribute;

/**
 * A class to support set widgets on the interface. Copied from SetWidget from
 * ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-10
 * @see com.variamos.gui.pl.editor.widgets.SetWidget
 */
@SuppressWarnings("serial")
public class SetWidget extends WidgetR {

	private JComboBox<String> comboBox;

	public SetWidget() {

		comboBox = new JComboBox<>();
		// comboBox.setPreferredSize(new Dimension(100, 50));
		setLayout(new BorderLayout());
		add(comboBox, BorderLayout.CENTER);
		revalidate();

		// comboBox.setEditable(false);
	}

	@Override
	protected boolean pushValue(EditableElementAttribute v) {
		comboBox.setSelectedItem(v.getValue());
		return false;
	}

	@Override
	protected void pullValue(EditableElementAttribute v) {
		v.setType((String) comboBox.getSelectedItem());
	}

	@Override
	public JComponent getEditor() {
		return comboBox;
	}

	@Override
	public void configure(EditableElementAttribute v, mxGraph graph,
			RefasModel semanticModel, boolean showSimulationCustomizationBox) {
	}

	@Override
	public JComponent getGroup() {
		return group;
	}
}
