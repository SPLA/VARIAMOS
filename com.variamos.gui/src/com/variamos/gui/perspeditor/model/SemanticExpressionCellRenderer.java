package com.variamos.gui.perspeditor.model;

import java.awt.Component;
import java.util.EnumMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.tree.TreeModel;

import com.cfm.productline.Variable;
import com.variamos.configurator.Choice;
import com.variamos.gui.treetable.core.TreeTableCellRenderer;
import com.variamos.gui.treetable.core.TreeTableModelAdapter;
import com.variamos.hlcl.BinaryDomain;
import com.variamos.hlcl.Domain;

/**
 * A class to render the icons of the tree for the Concepts expression
 * associations. Part of PhD work at University of Paris 1. Initially copied
 * from com.variamos.gui.pl.configuration.treetable.
 * ConfigurationVariableCellRenderer
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-11-06
 * @see com.variamos.gui.pl.configuration.treetable.ConfigurationVariableCellRenderer
 */
@SuppressWarnings({ "serial", "rawtypes" })
public class SemanticExpressionCellRenderer extends TreeTableCellRenderer
		implements ListCellRenderer {

	private JLabel lbl;

	private static EnumMap<Choice, ImageIcon> map;

	public SemanticExpressionCellRenderer(ConceptOperTreeTable treeTable,
			TreeModel model) {
		super(treeTable, model);
		lbl = new JLabel();

		map = new EnumMap<>(Choice.class);

		map.put(Choice.CHECK,
				new ImageIcon(
						SemanticExpressionCellRenderer.class
								.getResource("/com/mxgraph/examples/swing/images/checkmark.gif")));
		map.put(Choice.CROSS,
				new ImageIcon(
						SemanticExpressionCellRenderer.class
								.getResource("/com/mxgraph/examples/swing/images/x-red.gif")));
		map.put(Choice.QUESTION_MARK,
				new ImageIcon(
						SemanticExpressionCellRenderer.class
								.getResource("/com/mxgraph/examples/swing/images/question.gif")));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		ConfigurationNode node = getConfigurationNode(table, row);

		lbl.setIcon(null);
		lbl.setText("");

		if (node == null)
			return lbl;

		Variable var = node.getVariable();

		if (var == null)
			return lbl;

		Integer intVal = (Integer) var.getValue();
		if (intVal == null) {
			lbl.setIcon(map.get(Choice.QUESTION_MARK));
			return lbl;
		}

		Domain domain = var.getDomain();
		if (domain == null)
			return lbl;

		if (domain instanceof BinaryDomain) {
			if (intVal == 0)
				lbl.setIcon(map.get(Choice.CROSS));
			else
				lbl.setIcon(map.get(Choice.CHECK));
		} else {
			// It should be a number here.
			lbl.setText(intVal + "");
		}

		return lbl;
	}

	public static ConfigurationNode getConfigurationNode(JTable table, int row) {
		TreeTableModelAdapter model = (TreeTableModelAdapter) table.getModel();
		return (ConfigurationNode) model.nodeForRow(row);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {

		if (value != null) {
			if (!(value instanceof Choice))
				return null;

			if (isSelected)
				setBackground(treeTable.getSelectionBackground());
			else
				setBackground(treeTable.getBackground());

			lbl.setIcon(map.get((Choice) value));
		}

		return lbl;
	}

}
