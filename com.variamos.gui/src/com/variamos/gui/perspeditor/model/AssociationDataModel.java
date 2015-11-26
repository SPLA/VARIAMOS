package com.variamos.gui.perspeditor.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import com.cfm.productline.Variable;
import com.variamos.gui.perspeditor.model.actions.SetIntegerValueAction;
import com.variamos.gui.perspeditor.model.actions.SetStringValueAction;
import com.variamos.gui.perspeditor.panels.ElementsOperationAssociationPanel;
import com.variamos.gui.treetable.core.AbstractTreeTableModel;
import com.variamos.gui.treetable.core.TreeTableModel;
import com.variamos.hlcl.BinaryDomain;
import com.variamos.hlcl.Domain;
import com.variamos.hlcl.IntervalDomain;
import com.variamos.perspsupport.expressionsupport.ElementVariable;
import com.variamos.perspsupport.expressionsupport.IntegerVariable;

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
	protected List<String> captions = null;
	protected List<Class<?>> types = null;

	private int steps;
	private ElementsOperationAssociationPanel configurator;

	public static final int COLUMN_NAME = 0, COLUMN_VALUE = 1, COLUMN_STEP = 2;

	private LinkedList<AssociationAction> actions = new LinkedList<>();

	public AssociationDataModel(Object root,
			ElementsOperationAssociationPanel configurator, List<String> names,
			List<Domain> domains) {
		super(root);
		captions = new ArrayList<String>();
		captions.add("Concept/Variable/Expression");
		captions.addAll(names);

		types = new ArrayList<Class<?>>();
		types.add(TreeTableModel.class);
		for (int i = 0; i < names.size(); i++) {
			if (domains.get(i) instanceof BinaryDomain) {
				types.add(Variable.class);
			} else if (domains.get(i) instanceof IntervalDomain) {
				types.add(IntegerVariable.class);
			} else {
				types.add(ElementVariable.class);
			}
		}
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
		return types.size();
	}

	@Override
	public String getColumnName(int column) {
		return captions.get(column);
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return types.get(column);
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
		if (((AssociationRow) node).isLeaf() || column == 0)
			return true;
		else
			return false;
	}

	@Override
	public void setValueAt(Object aValue, Object node, int column) {
		AssociationRow n = (AssociationRow) node;

		// n.getVariable().setValue((Integer) aValue);
		AssociationAction ca = null;
		if (aValue instanceof Integer)
			ca = newSetIntegerAction(n.getValue(column), (Integer) aValue,
					column);
		if (aValue instanceof String)
			ca = newSetStringAction(n.getValue(column), (String) aValue, column);
		if (aValue instanceof ChoiceBoolean)
			ca = newSetIntegerAction(n.getValue(column),
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

	public AssociationAction newSetIntegerAction(Variable var,
			Integer newValue, int column) {
		// steps++;
		SetIntegerValueAction sva = new SetIntegerValueAction(var, newValue,
				column);
		// actions.addLast(sva);
		return sva;
	}

	public AssociationAction newSetStringAction(Variable var, String newValue,
			int column) {
		// steps++;
		SetStringValueAction sva = new SetStringValueAction(var, newValue,
				column);
		// actions.addLast(sva);
		return sva;
	}

}
