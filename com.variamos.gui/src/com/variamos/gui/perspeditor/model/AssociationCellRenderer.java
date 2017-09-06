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
public class AssociationCellRenderer extends TreeTableCellRenderer implements
		ListCellRenderer {

	private JLabel lbl;

	private static EnumMap<ChoiceBoolean, ImageIcon> map;

	public AssociationCellRenderer(AssociationTreeTable treeTable,
			TreeModel model) {
		super(treeTable, model);
		lbl = new JLabel();

		map = new EnumMap<>(ChoiceBoolean.class);

		map.put(ChoiceBoolean.CHECK,
				new ImageIcon(
						AssociationCellRenderer.class
								.getResource("/com/variamos/gui/perspeditor/images/checkmark.gif")));
		map.put(ChoiceBoolean.CROSS,
				new ImageIcon(
						AssociationCellRenderer.class
								.getResource("/com/variamos/gui/perspeditor/images/x-red.gif")));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		AssociationRow node = getConfigurationNode(table, row);

		lbl.setIcon(null);
		lbl.setText("");

		if (node == null) {
			return lbl;
		}

		Variable var = node.getValue(column);

		if (var == null) {
			return lbl;
		}
		if (var.getValue() instanceof Integer) {
			Integer intVal = (Integer) var.getValue();
			if (intVal == null) {
				return lbl;
			}

			Domain domain = var.getDomain();
			if (domain == null)
				return lbl;

			if (domain instanceof BinaryDomain) {
				if (intVal == 0)
					lbl.setIcon(map.get(ChoiceBoolean.CROSS));
				else
					lbl.setIcon(map.get(ChoiceBoolean.CHECK));
			} else {
				// It should be a number here.
				lbl.setText(intVal + "");
			}
		}
		return lbl;

	}

	public static AssociationRow getConfigurationNode(JTable table, int row) {
		TreeTableModelAdapter model = (TreeTableModelAdapter) table.getModel();
		return (AssociationRow) model.nodeForRow(row);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {

		if (value != null) {
			if (!(value instanceof ChoiceBoolean))
				return null;

			if (isSelected)
				setBackground(treeTable.getSelectionBackground());
			else
				setBackground(treeTable.getBackground());

			lbl.setIcon(map.get((ChoiceBoolean) value));
		}

		return lbl;
	}

}
