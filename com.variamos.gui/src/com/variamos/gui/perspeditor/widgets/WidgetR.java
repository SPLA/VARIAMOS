package com.variamos.gui.perspeditor.widgets;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mxgraph.view.mxGraph;
import com.variamos.dynsup.interfaces.IntInstAttribute;
import com.variamos.dynsup.model.InstanceModel;

@SuppressWarnings("serial")
public abstract class WidgetR extends JPanel {

	public static final String PROPERTY_VALUE = "WidgetR.Value";
	private boolean affectProperties = false;

	protected IntInstAttribute edited;
	protected JTextField group;

	public WidgetR() {
		super();
		group = new JTextField();
		group.setPreferredSize(new Dimension(20, 30));
	}

	public boolean editVariable(IntInstAttribute v) {
		edited = v;
		return pushValue(v);
	}

	protected abstract boolean pushValue(IntInstAttribute v);

	protected abstract void pullValue(IntInstAttribute v);

	public abstract JComponent getGroup();

	public abstract JComponent getEditor();

	public boolean isAffectedProperties() {
		return affectProperties;
	}

	public void configure(IntInstAttribute v, mxGraph graph,
			InstanceModel semanticModel,
			boolean showSimulationCustomizationBox, int perspective) {
		affectProperties = v.isAffectProperties();
		if (showSimulationCustomizationBox)
			add(group, BorderLayout.EAST);
		revalidate();
	}

	public IntInstAttribute getInstAttribute() {
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