package com.variamos.gui.pl.configurator.solution;

import java.util.ArrayList;
import java.util.List;

import com.variamos.gui.pl.configurator.treetable.ConfigurationNode;
import com.variamos.gui.treetable.core.AbstractTreeTableModel;
import com.variamos.gui.treetable.core.TreeTableModel;
import com.variamos.solver.Configuration;

public class SolutionDataModel extends AbstractTreeTableModel{
	
	static protected String[] captions = { "Variable"};
	static protected Class<?>[] types = { TreeTableModel.class};
	
	private List<Configuration> solutions;
	
	public static final int COLUMN_NAME = 0;
	
	public SolutionDataModel(Object root) {
		super(root);
		solutions = new ArrayList<>();
	}

	@Override
	public int getColumnCount() {
		return solutions.size() + 1;
	}

	@Override
	public String getColumnName(int column) {
		if( column == COLUMN_NAME )
			return "Variable";
		
		return "Solution " + column;
	}

	@Override
	public Class<?> getColumnClass(int column) {
		if( column == COLUMN_NAME )
			return TreeTableModel.class;
		
		return Integer.class;
	}

	@Override
	public Object getValueAt(Object node, int column) {
		
		if( !(node instanceof ConfigurationNode) )
			return "";
		
		ConfigurationNode n = (ConfigurationNode) node;
		
		
		if( column == COLUMN_NAME )
			return n.getName();
			
		if(n.getVariable() == null)
			return "";
			
		int index = column - 1;
		Configuration conf = solutions.get(index);
		return conf.stateOf(n.getName());
		
	}

	@Override
	public boolean isCellEditable(Object node, int column) {
		if(column == 0)
			return true;
		return false;
	}

	@Override
	public Object getChild(Object node, int childNo) {
		if( !(node instanceof ConfigurationNode) )
			return null;
		
		ConfigurationNode n = (ConfigurationNode) node;
		
		return n.getChildren().get(childNo);
	}

	@Override
	public int getChildCount(Object node) {
		if( !(node instanceof ConfigurationNode) )
			return 0;
		
		ConfigurationNode n = (ConfigurationNode) node;
		
		return n.getChildren().size();
	}

	@Override
	public void setValueAt(Object aValue, Object node, int column) {
		
	}

	public void addSolution(Configuration solution) {
		solutions.add(solution);
	}
	
	public List<Configuration> getSolutions(){
		return solutions;
	}

	public void clearSolutions() {
		solutions.clear();
	}
}