package com.cfm.productline.configurator.treetable;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.cfm.productline.Variable;
import com.cfm.productline.configurator.Choice;
import com.cfm.productline.solver.Configuration;
import com.cfm.treetable.core.AbstractTreeTableModel;
import com.cfm.treetable.core.TreeTableCellEditor;
import com.cfm.treetable.core.TreeTableCellRenderer;
import com.cfm.treetable.core.TreeTableModel;
import com.cfm.treetable.core.TreeTableModelAdapter;
import com.cfm.treetable.core.TreeTableSelectionModel;

 
@SuppressWarnings("serial")
public class ConfigurationTreeTable extends JTable {
 
    private TreeTableCellRenderer tree;
    
     
    public ConfigurationTreeTable(AbstractTreeTableModel treeTableModel) {
        super();

        ConfigurationVariableCellRenderer choiceRenderer = new ConfigurationVariableCellRenderer(this, treeTableModel);
        tree = new TreeTableCellRenderer(this, treeTableModel);
        super.setModel(new TreeTableModelAdapter(treeTableModel, tree));
         
        TreeTableSelectionModel selectionModel = new TreeTableSelectionModel();
        tree.setSelectionModel(selectionModel);
        setSelectionModel(selectionModel.getListSelectionModel());
         
        setDefaultRenderer(TreeTableModel.class, tree);
        setDefaultRenderer(Variable.class, choiceRenderer);
        
        
        setDefaultEditor(TreeTableModel.class, new TreeTableCellEditor(tree, this));
        //setDefaultEditor(Choice.class, getChoiceEditor(choiceRenderer));
        setDefaultEditor(Variable.class, getIntegerEditor());

        
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        getColumnModel().getColumn(0).setMinWidth(100);
        getColumnModel().getColumn(1).setMinWidth(40);
        getColumnModel().getColumn(2).setMinWidth(35);
        
        setShowGrid(false);
        
        setIntercellSpacing(new Dimension(0, 0));
    }
    
    private TableCellEditor getIntegerEditor() {
		return new DomainCellEditor();
	}

    public void resizeColumns(){
		for (int i = 0; i < getColumnCount(); i++) {
			DefaultTableColumnModel colModel = (DefaultTableColumnModel) getColumnModel();
			TableColumn col = colModel.getColumn(i);
			int width = 0;

			TableCellRenderer renderer = col.getCellRenderer();//getHeaderRenderer();
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
    
	public TableCellEditor getChoiceEditor(ConfigurationVariableCellRenderer renderer){
    	JComboBox<Choice> choiceEditor = new JComboBox<>(Choice.values());
//    	choiceEditor.setRenderer(new ListCellRenderer<Choice>() {
//    		private JLabel lbl = new JLabel();
//    		
//			@Override
//			public Component getListCellRendererComponent(
//					JList<? extends Choice> list, Choice value, int index,
//					boolean isSelected, boolean cellHasFocus) {
//				lbl.setIcon(new I)
//				return lbl;
//			}
//		});
    	choiceEditor.setRenderer(renderer);
    	return new DefaultCellEditor(choiceEditor);
    }
    
	public void expandRow(int row){
		((TreeTableModelAdapter)getModel()).expandRow(0);
	}
}