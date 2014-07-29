package com.cfm.productline.configurator.solution;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JTable;

import com.cfm.productline.solver.Configuration;
import com.cfm.treetable.core.TreeTableCellRenderer;
import com.cfm.treetable.core.TreeTableModel;
import com.cfm.treetable.core.TreeTableModelAdapter;
import com.cfm.treetable.core.TreeTableSelectionModel;

@SuppressWarnings("serial")
public class SolutionTreeTable extends JTable{
	private TreeTableModelAdapter modelAdapter;
	private SolutionDataModel dataModel;
	public SolutionTreeTable(SolutionDataModel model){
		super();
		this.dataModel = model;
		TreeTableCellRenderer tree = new TreeTableCellRenderer(this, model);
		modelAdapter = new TreeTableModelAdapter(model, tree);
		setModel(modelAdapter);
		
		TreeTableSelectionModel selModel = new TreeTableSelectionModel();
		tree.setSelectionModel(selModel);
		setSelectionModel(selModel.getListSelectionModel());
		
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		getColumnModel().getColumn(0).setMinWidth(100);
		
		setDefaultRenderer(TreeTableModel.class, tree);
		
		setShowGrid(false);
		setIntercellSpacing(new Dimension(0, 0));
		setMinimumSize(new Dimension(400, 180));
	}
	
	public void addSolution(Configuration conf){
		dataModel.addSolution(conf);
		modelAdapter.fireTableStructureChanged();
		modelAdapter.fireTableDataChanged();
		revalidate();
		this.getParent().repaint();
	}
	
	public void expandRow(int row){
		modelAdapter.expandRow(0);
	}
	public List<Configuration> getAllSolutions(){
		return dataModel.getSolutions();
	}
	
	public void clearSolutions(){
		dataModel.clearSolutions();
		modelAdapter.fireTableDataChanged();
		revalidate();
	}
}
