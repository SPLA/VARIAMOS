package com.variamos.gui.perspeditor.model;

import java.util.LinkedList;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import com.cfm.productline.Variable;
import com.variamos.gui.perspeditor.model.actions.SetValueAction;
import com.variamos.gui.perspeditor.panels.ElementsOperationAssociationPanel;
import com.variamos.gui.treetable.core.AbstractTreeTableModel;
import com.variamos.gui.treetable.core.TreeTableModel;

/**
 * A class to support the data model for the visual representation of the
 * association between semantic expressions and operations. Part of PhD work at
 * University of Paris 1. Initially copied from
 * com.variamos.gui.pl.configuration.treetable.ConfigurationDataModel
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-11-06
 * @see com.variamos.gui.pl.AssociationDataModel.treetable.ConfigurationDataModel
 */
public class AssociationDataModel extends AbstractTreeTableModel {

	// static protected String[] captions = { "Variable", "Step", "Choice",
	// "Value", "Extra" };
	static protected String[] captions = { "Concept/RelationType/Expresssion",
			"Compulsory", "Relaxable", "Verification", "oth" };
	static protected Class<?>[] types = { TreeTableModel.class, Variable.class,
			Variable.class, Variable.class, Variable.class };

	private int steps;
	private ElementsOperationAssociationPanel configurator;

	public static final int COLUMN_NAME = 0, COLUMN_VALUE = 1, COLUMN_STEP = 2;

	private LinkedList<AssociationAction> actions = new LinkedList<>();

	public AssociationDataModel(Object root,
			ElementsOperationAssociationPanel configurator) {
		super(root);
		steps = 0;
		this.configurator = configurator;

		addTreeModelListener(new TreeModelListener() {

			@Override
			public void treeStructureChanged(TreeModelEvent arg0) {
				AssociationDataModel.this.configurator.resizeColumns();
			}

			@Override
			public void treeNodesRemoved(TreeModelEvent arg0) {
				AssociationDataModel.this.configurator.resizeColumns();

			}

			@Override
			public void treeNodesInserted(TreeModelEvent arg0) {
				AssociationDataModel.this.configurator.resizeColumns();

			}

			@Override
			public void treeNodesChanged(TreeModelEvent arg0) {
				AssociationDataModel.this.configurator.resizeColumns();
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

		if (!(node instanceof AssociationRow))
			return "";

		AssociationRow n = (AssociationRow) node;

		if (column == 0)
			return n.getName();
		else
			return n.getValue(column);

		/*
		 * switch (column) { case COLUMN_NAME: return n.getName();
		 * 
		 * case COLUMN_VALUE: return n.getVariable();
		 * 
		 * case COLUMN_STEP: if (n.getVariable() == null) return "";
		 * 
		 * return n.getStepEdited(); }
		 */
		// return "";
	}

	@Override
	public boolean isCellEditable(Object node, int column) {
		switch (column) {
		case COLUMN_NAME:
		case COLUMN_VALUE:
			return true;
		}
		return true;
	}

	@Override
	public void setValueAt(Object aValue, Object node, int column) {
		AssociationRow n = (AssociationRow) node;

		// n.getVariable().setValue((Integer) aValue);
		AssociationAction ca = null;
		if (aValue instanceof Integer)
			ca = newSetAction(n.getValue(column), (Integer) aValue, column);
		if (aValue instanceof ChoiceBoolean)
			ca = newSetAction(n.getValue(column),
					((ChoiceBoolean) aValue).ordinal(), column);
		ca.execute(configurator);

	}

	@Override
	public Object getChild(Object node, int childNo) {
		if (!(node instanceof AssociationRow))
			return null;

		AssociationRow n = (AssociationRow) node;

		return n.getChildren().get(childNo);
	}

	@Override
	public int getChildCount(Object node) {
		if (!(node instanceof AssociationRow))
			return 0;

		AssociationRow n = (AssociationRow) node;

		return n.getChildren().size();
	}

	public AssociationAction newSetAction(Variable var, Integer newValue,
			int column) {
		// steps++;
		SetValueAction sva = new SetValueAction(var, newValue, column);
		// actions.addLast(sva);
		return sva;
	}

}
