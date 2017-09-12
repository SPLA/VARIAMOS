package com.variamos.gui.perspeditor.model;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

import com.variamos.gui.treetable.core.TreeTableModelAdapter;

/**
 * A class to support the cell domain definition. Part of PhD work at University
 * of Paris 1. Initially copied from
 * com.variamos.gui.pl.configuration.treetable. DomainCellEditor
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-11-06
 * @see com.variamos.gui.pl.configuration.treetable.DomainCellEditor
 */
@SuppressWarnings("serial")
public class DomainCellEditor extends AbstractCellEditor implements
		TableCellEditor {

	private String editedValue;

	private ListDomainEditor listEditor;

	public DomainCellEditor() {

	}

	@Override
	public String getCellEditorValue() {
		return editedValue;
	}

	@Override
	public Component getTableCellEditorComponent(final JTable table,
			Object value, boolean isSelected, int row, int column) {

		// final ConfigurationNode node = getConfigurationNode(table, row);
		listEditor = new ListDomainEditor(getConfigurationNode(table, row));
		if (isSelected) {

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					// JOptionPane opPane = new JOptionPane();
					int option = JOptionPane.showConfirmDialog(table,
							listEditor, "Select Value",
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE, null);

					if (option == JOptionPane.OK_OPTION) {
						editedValue = listEditor.getSelectedValue();
						fireEditingStopped();
					} else {
						cancelCellEditing();
					}
				}
			});

			return new JLabel(String.valueOf(value));
		}

		return null;
	}

	public static AssociationRow getConfigurationNode(JTable table, int row) {
		TreeTableModelAdapter model = (TreeTableModelAdapter) table.getModel();
		return (AssociationRow) model.nodeForRow(row);
	}
}
