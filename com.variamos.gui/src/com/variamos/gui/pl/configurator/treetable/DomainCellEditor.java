package com.variamos.gui.pl.configurator.treetable;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

import com.variamos.gui.treetable.core.TreeTableModelAdapter;

@SuppressWarnings("serial")
public class DomainCellEditor extends AbstractCellEditor implements TableCellEditor{
	
	private Integer editedValue;
	
	private ListDomainEditor listEditor;
	
	public DomainCellEditor(){

	}
	
	@Override
	public Integer getCellEditorValue() {
		return editedValue;
	}

	@Override
	public Component getTableCellEditorComponent(final JTable table, Object value, boolean isSelected, int row, int column) {
		
		//final ConfigurationNode node = getConfigurationNode(table, row);
		listEditor = new ListDomainEditor(getConfigurationNode(table, row));
		if(isSelected){
			
			SwingUtilities.invokeLater(new Runnable(){
	            public void run(){
//	            	JOptionPane opPane = new JOptionPane();
					int option = JOptionPane.showConfirmDialog(table, listEditor, "Select Value", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
					
					if(option == JOptionPane.OK_OPTION ){
						editedValue = listEditor.getSelectedValue();
						fireEditingStopped();
					}else{
						cancelCellEditing();
					}
	            }
	        });
			
			return new JLabel(String.valueOf(value));
		}
		
		return null;
	}
	
	public static ConfigurationNode getConfigurationNode(JTable table, int row){
		TreeTableModelAdapter model = (TreeTableModelAdapter)table.getModel();
		return (ConfigurationNode)model.nodeForRow(row);
	}
}
