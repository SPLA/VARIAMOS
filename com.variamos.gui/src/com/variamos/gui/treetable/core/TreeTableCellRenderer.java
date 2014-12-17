package com.variamos.gui.treetable.core;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreeModel;

 
 
@SuppressWarnings("serial")
public class TreeTableCellRenderer extends JTree implements TableCellRenderer {
    protected int visibleRow;
     
    protected JTable treeTable;
     
    public TreeTableCellRenderer(JTable treeTable, TreeModel model) {
        super(model);
        this.treeTable = treeTable;
        setRowHeight(getRowHeight());
    }
 
    public void setRowHeight(int rowHeight) {
        if (rowHeight > 0) {
            super.setRowHeight(rowHeight);
            if (treeTable != null && treeTable.getRowHeight() != rowHeight) {
                treeTable.setRowHeight(getRowHeight());
            }
        }
    }
 
    public void setBounds(int x, int y, int w, int h) {
        super.setBounds(x, 0, w, treeTable.getHeight());
    }
 
    public void paint(Graphics g) {
        g.translate(0, -visibleRow * getRowHeight());
        super.paint(g);
    }
     
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected)
            setBackground(table.getSelectionBackground());
        else
            setBackground(table.getBackground());

        visibleRow = row;
        return this;
    }
}