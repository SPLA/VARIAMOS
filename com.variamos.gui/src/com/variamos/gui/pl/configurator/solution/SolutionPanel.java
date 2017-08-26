package com.variamos.gui.pl.configurator.solution;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.variamos.gui.pl.configurator.treetable.ConfigurationNode;
import com.variamos.solver.model.SolverSolution;

/**
 * @author unknown
 *
 */
@SuppressWarnings("serial")
public class SolutionPanel extends JPanel{

	private SolutionTreeTable table;
	private SolutionDataModel model;
	
	public SolutionPanel(ConfigurationNode root){
		setBorder(BorderFactory.createTitledBorder("Solutions"));
		setLayout(new BorderLayout());
		model = new SolutionDataModel(root);
		table = new SolutionTreeTable(model);
		
		JPanel container = new JPanel(new BorderLayout());
		container.add(table.getTableHeader(), BorderLayout.NORTH);
		container.add(table, BorderLayout.CENTER);
		add(new JScrollPane(container), BorderLayout.CENTER);
	}
	
	public void addSolution(SolverSolution solution){
		table.addSolution(solution);
		
	}
	
	public List<SolverSolution> getAllSolutions() {
		return table.getAllSolutions();
	}

	public void clearSolutions() {
		table.clearSolutions();
	}

	public void expand(){
		table.expandRow(0);
	}

	public SolutionDataModel getModel() {
		return model;
	}
}
