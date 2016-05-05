package com.variamos.gui.perspeditor.model;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.cfm.productline.Variable;
import com.variamos.dynsup.types.ElementVariable;
import com.variamos.dynsup.types.IntegerVariable;
import com.variamos.gui.treetable.core.AbstractTreeTableModel;
import com.variamos.gui.treetable.core.TreeTableCellEditor;
import com.variamos.gui.treetable.core.TreeTableCellRenderer;
import com.variamos.gui.treetable.core.TreeTableModel;
import com.variamos.gui.treetable.core.TreeTableModelAdapter;
import com.variamos.gui.treetable.core.TreeTableSelectionModel;

/**
 * A class to support the tree table for the visual representation of the
 * association between semantic expressions and operations. Part of PhD work at
 * University of Paris 1. Initially copied from
 * com.variamos.gui.pl.configuration.treetable.ConfigurationTreeTable
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-11-06
 * @see com.variamos.gui.pl.configuration.treetable.ConfigurationTreeTable
 */
@SuppressWarnings("serial")
public class AssociationTreeTable extends JTable {

	private TreeTableCellRenderer tree;

	public AssociationTreeTable(AbstractTreeTableModel treeTableModel) {
		super();

		AssociationCellRenderer choiceRenderer = new AssociationCellRenderer(
				this, treeTableModel);
		tree = new TreeTableCellRenderer(this, treeTableModel);
		super.setModel(new TreeTableModelAdapter(treeTableModel, tree));

		TreeTableSelectionModel selectionModel = new TreeTableSelectionModel();
		tree.setSelectionModel(selectionModel);
		setSelectionModel(selectionModel.getListSelectionModel());

		setDefaultRenderer(TreeTableModel.class, tree);
		setDefaultRenderer(Variable.class, choiceRenderer);

		setDefaultRenderer(IntegerVariable.class, choiceRenderer);
		setDefaultRenderer(ElementVariable.class, null);

		setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor(tree,
				this));
		// setDefaultEditor(Choice.class, getChoiceEditor(choiceRenderer));
		setDefaultEditor(Variable.class, this.getChoiceEditor(choiceRenderer));
		setDefaultEditor(IntegerVariable.class,
				this.getChoiceEditor(choiceRenderer));
		setDefaultEditor(ElementVariable.class, getIntegerEditor());

		setDefaultEditor(Boolean.class, this.getChoiceEditor(choiceRenderer));

		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getColumnModel().getColumn(0).setMinWidth(100);
		for (int i = 1; i < getColumnModel().getColumnCount(); i++) {
			getColumnModel().getColumn(i).setMinWidth(40);
		}
		this.setAutoscrolls(true);

		setShowGrid(false);
		JTableHeader tableHeader = getTableHeader();
		tableHeader.setName("name");
		tableHeader.setVisible(true);

		setIntercellSpacing(new Dimension(0, 0));
	}

	private TableCellEditor getIntegerEditor() {
		return new DomainCellEditor();
	}

	public void resizeColumns() {
		for (int i = 0; i < getColumnCount(); i++) {
			DefaultTableColumnModel colModel = (DefaultTableColumnModel) getColumnModel();
			TableColumn col = colModel.getColumn(i);
			int width = 0;

			TableCellRenderer renderer = col.getHeaderRenderer();
			for (int r = 0; r < getRowCount(); r++) {
				renderer = getCellRenderer(r, i);
				Component comp = renderer.getTableCellRendererComponent(this,
						getValueAt(r, i), false, false, r, i);
				width = Math.max(width, comp.getPreferredSize().width);
			}

			renderer = col.getHeaderRenderer();
			for (int r = 0; r < getRowCount(); r++) {
				renderer = getCellRenderer(r, i);
				Component comp = renderer.getTableCellRendererComponent(this,
						getValueAt(r, i), false, false, r, i);
				width = Math.max(width, comp.getPreferredSize().width);
			}
			col.setPreferredWidth(width + 2);
		}
	}

	@SuppressWarnings("unchecked")
	public TableCellEditor getChoiceEditor(AssociationCellRenderer renderer) {
		JComboBox<ChoiceBoolean> choiceEditor = new JComboBox<>(
				ChoiceBoolean.values());
		// choiceEditor.setRenderer(new ListCellRenderer<Choice>() {
		// private JLabel lbl = new JLabel();
		//
		// @Override
		// public Component getListCellRendererComponent(
		// JList<? extends Choice> list, Choice value, int index,
		// boolean isSelected, boolean cellHasFocus) {
		// lbl.setIcon(new I)
		// return lbl;
		// }
		// });
		choiceEditor.setRenderer(renderer);
		return new DefaultCellEditor(choiceEditor);
	}

	public void expandRow(int row) {
		((TreeTableModelAdapter) getModel()).expandRow(0);
	}
}