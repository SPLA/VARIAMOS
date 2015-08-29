package com.variamos.gui.perspeditor.widgets;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mxgraph.view.mxGraph;
import com.variamos.perspsupport.perspmodel.RefasModel;
import com.variamos.perspsupport.syntaxsupport.EditableElementAttribute;

@SuppressWarnings("serial")
public abstract class WidgetR extends JPanel {

	public static final String PROPERTY_VALUE = "WidgetR.Value";
	private boolean affectProperties = false;

	protected EditableElementAttribute edited;
	protected JTextField group;

	public WidgetR() {
		super();
		group = new JTextField();
		group.setPreferredSize(new Dimension(20, 30));
	}

	public boolean editVariable(EditableElementAttribute v) {
		edited = v;
		return pushValue(v);
	}

	protected abstract boolean pushValue(EditableElementAttribute v);

	protected abstract void pullValue(EditableElementAttribute v);

	public abstract JComponent getGroup();

	public abstract JComponent getEditor();

	public boolean isAffectedProperties() {
		return affectProperties;
	}

	public void configure(EditableElementAttribute v, mxGraph graph,
			RefasModel semanticModel, boolean showSimulationCustomizationBox) {
		affectProperties = v.isAffectProperties();
		if (showSimulationCustomizationBox)
			add(group, BorderLayout.EAST);
		revalidate();
	}

	public EditableElementAttribute getInstAttribute() {
		pullValue(edited);
		return edited;
	}

	public boolean isAffectProperties() {
		return affectProperties;
	}

	public void setAffectProperties(boolean affectProperties) {
		this.affectProperties = affectProperties;
	}

}