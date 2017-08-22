package com.variamos.gui.pl.configurator.treetable;

import java.util.LinkedList;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import com.cfm.productline.Variable;
import com.variamos.gui.common.jelements.AbstractConfigurationPanel;
import com.variamos.gui.pl.configurator.treetable.actions.SetValueAction;
import com.variamos.gui.treetable.core.AbstractTreeTableModel;
import com.variamos.gui.treetable.core.TreeTableModel;

public class ConfigurationDataModel extends AbstractTreeTableModel {

	// static protected String[] captions = { "Variable", "Step", "Choice",
	// "Value", "Extra" };
	static protected String[] captions = { "Variable", "Value", "Step", "" };
	static protected Class<?>[] types = { TreeTableModel.class, Variable.class,
			Integer.class, String.class };

	private int steps;
	private AbstractConfigurationPanel configurator;

	public static final int COLUMN_NAME = 0, COLUMN_VALUE = 1, COLUMN_STEP = 2;

	private LinkedList<ConfigurationAction> actions = new LinkedList<>();

	public ConfigurationDataModel(Object root,
			AbstractConfigurationPanel configurator) {
		super(root);
		steps = 0;
		this.configurator = configurator;

		addTreeModelListener(new TreeModelListener() {

			@Override
			public void treeStructureChanged(TreeModelEvent arg0) {
				ConfigurationDataModel.this.configurator.resizeColumns();
			}

			@Override
			public void treeNodesRemoved(TreeModelEvent arg0) {
				ConfigurationDataModel.this.configurator.resizeColumns();

			}

			@Override
			public void treeNodesInserted(TreeModelEvent arg0) {
				ConfigurationDataModel.this.configurator.resizeColumns();

			}

			@Override
			public void treeNodesChanged(TreeModelEvent arg0) {
				ConfigurationDataModel.this.configurator.resizeColumns();
			}
		});
	}

	@Override
	public int getColumnCount() {
		return types.length;
	}

	@Override
	public String getColumnName(int column) {
		return captions[column];
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return types[column];
	}

	@Override
	public Object getValueAt(Object node, int column) {

		if (!(node instanceof ConfigurationNode))
			return "";

		ConfigurationNode n = (ConfigurationNode) node;

		switch (column) {
		case COLUMN_NAME:
			return n.getName();

		case COLUMN_VALUE:
			return n.getVariable();

		case COLUMN_STEP:
			if (n.getVariable() == null)
				return "";

			return n.getStepEdited();
		}

		return "";
	}

	@Override
	public boolean isCellEditable(Object node, int column) {
		switch (column) {
		case COLUMN_NAME:
		case COLUMN_VALUE:
			return true;
		}
		return false;
	}

	@Override
	public void setValueAt(Object aValue, Object node, int column) {
		ConfigurationNode n = (ConfigurationNode) node;

		switch (column) {
		case COLUMN_VALUE:
			// n.getVariable().setValue((Integer) aValue);
			ConfigurationAction ca = newSetAction(n.getVariable(),
					(Integer) aValue);
			ca.execute(configurator, null);

			break;
		case COLUMN_STEP:
			n.setStepEdited((Integer) aValue);
			break;
		}
	}

	@Override
	public Object getChild(Object node, int childNo) {
		if (!(node instanceof ConfigurationNode))
			return null;

		ConfigurationNode n = (ConfigurationNode) node;

		return n.getChildren().get(childNo);
	}

	@Override
	public int getChildCount(Object node) {
		if (!(node instanceof ConfigurationNode))
			return 0;

		ConfigurationNode n = (ConfigurationNode) node;

		return n.getChildren().size();
	}

	public ConfigurationAction newSetAction(Variable var, Integer newValue) {
		steps++;
		SetValueAction sva = new SetValueAction(var, steps, newValue);
		actions.addLast(sva);
		return sva;
	}

}
