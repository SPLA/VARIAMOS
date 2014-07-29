package com.cfm.productline.configurator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.cfm.hlcl.BinaryDomain;
import com.cfm.jgprolog.gnuprolog.GNUPrologContext;
import com.cfm.productline.Constraint;
import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.Variable;
import com.cfm.productline.configurator.gui.actions.ConfigurationTask;
import com.cfm.productline.configurator.gui.actions.DefaultConfigurationTaskListener;
import com.cfm.productline.configurator.io.ConfigurationDTO;
import com.cfm.productline.configurator.solution.SolutionPanel;
import com.cfm.productline.configurator.treetable.ConfigurationDataModel;
import com.cfm.productline.configurator.treetable.ConfigurationNode;
import com.cfm.productline.configurator.treetable.ConfigurationTreeTable;
import com.cfm.productline.editor.SpringUtilities;
import com.cfm.productline.solver.Configuration;
import com.cfm.productline.solver.ConfigurationOptions;
import com.cfm.productline.solver.PrologSolver;
import com.cfm.productline.solver.Solver;
import com.cfm.productline.type.IntegerType;
import com.cfm.treetable.core.TreeTableModelAdapter;
import com.mxgraph.swing.util.mxGraphActions.DeleteAction;

@SuppressWarnings("serial")
public class Configurator extends JPanel {
	private ProductLine productLine;
	
	//Configurator table settings
	private ConfigurationTreeTable table;
	private ConfigurationNode root;
	private ConfigurationDataModel dataModel;
	private Solver solver;
	
	private SolutionPanel solutionPanel;
	
	private JLabel lblStatus;
	private List<Configuration> products;
	private JPanel controlPanel;
	
	private JList<String> additionalConstraints;
	
	public Configurator(){
		solver = new PrologSolver(new GNUPrologContext());
		
		//initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		
		root = new ConfigurationNode();
		dataModel = new ConfigurationDataModel(root, this);
		table = new ConfigurationTreeTable(dataModel);
		
		//Setup the preferred dimensions.
		
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(ConfigurationDataModel.COLUMN_NAME).setPreferredWidth(150);
		tcm.getColumn(ConfigurationDataModel.COLUMN_VALUE).setPreferredWidth(20);
		tcm.getColumn(ConfigurationDataModel.COLUMN_STEP).setPreferredWidth(20);
		
		table.resizeColumns();
		// TableColumnAdjuster tca = new TableColumnAdjuster(table);
	    //    tca.adjustColumns();
	        
		JTableHeader header = table.getTableHeader();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(header, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createTitledBorder("Configuration"));
		add(panel, BorderLayout.WEST);
		
		
		initControlPanel();
		add(controlPanel, BorderLayout.SOUTH);
		
		initSolutionPanel();
	}
	
	private void initSolutionPanel(){
		products = new ArrayList<>();
		solutionPanel = new SolutionPanel(root);
		solutionPanel.setPreferredSize(new Dimension(600, 200));
		add(solutionPanel, BorderLayout.CENTER);
	}
	
	private void initControlPanel() {
		controlPanel = new JPanel(new BorderLayout());
		
		JPanel buttonPanels = new JPanel();
		buttonPanels.setLayout(new SpringLayout());
		
		final JTextField txtNumConf = new JTextField(3);
		txtNumConf.setMaximumSize(new Dimension(60, 9));
		JButton getSolution = new JButton("Get Solutions");
		getSolution.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer num = Integer.parseInt(txtNumConf.getText());
				
				if(num == null){
					JOptionPane.showMessageDialog(Configurator.this, "Invalid Number", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				clearProducts();
				ConfigurationOptions options = getCurrentOptions();
				options.setStartFromZero(true);
				solve(num, options);
			}
		});
		
		JButton getNextSolution = new JButton("Get Next");
		getNextSolution.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer num = Integer.parseInt(txtNumConf.getText());
				
				if(num == null){
					JOptionPane.showMessageDialog(Configurator.this, "Invalid Number", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				ConfigurationOptions options = getCurrentOptions();
				options.setStartFromZero(false);
				solve(num, options);
				
			}
		});
		
		JButton undo = new JButton("Undo");
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		JButton reduceDomain = new JButton("Reduce Domain");
		reduceDomain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reduceDomain();
			}
		});
		
		buttonPanels.add(getSolution);
		buttonPanels.add(txtNumConf);
		buttonPanels.add(getNextSolution);
		buttonPanels.add(reduceDomain);
		
		SpringUtilities.makeCompactGrid(buttonPanels,
				2, 2,
				4, 4,
				4, 4);
		
		controlPanel.add(buttonPanels, BorderLayout.WEST);
		
		JPanel additionalPanel = new JPanel(new SpringLayout());
		additionalPanel.setBorder(BorderFactory.createTitledBorder("Additional Constraints"));
		
		additionalConstraints = new JList<String>(new DefaultListModel<String>());
		additionalConstraints.setPreferredSize(new Dimension(200, 100));
		
		
		JPanel constraintButtons = new JPanel(new SpringLayout());
		JButton btnAddConstraint = new JButton("Add Constraint");
		btnAddConstraint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String cons = JOptionPane.showInputDialog("New Constraint");
				((DefaultListModel<String>)additionalConstraints.getModel()).addElement(cons);
			}
		});
		
		JButton btnRemoveConstraint = new JButton("Remove Constraint");
		btnRemoveConstraint.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int sel = additionalConstraints.getSelectedIndex();
				if( sel < 0 )
					return;
				
				((DefaultListModel<String>)additionalConstraints.getModel()).remove(sel);
			}
		});
		
		constraintButtons.add(btnAddConstraint);
		constraintButtons.add(btnRemoveConstraint);
		SpringUtilities.makeCompactGrid(constraintButtons,
				2, 1,
				4, 4,
				4, 4);
		
		additionalPanel.add(additionalConstraints);
		additionalPanel.add(constraintButtons);
		SpringUtilities.makeCompactGrid(additionalPanel,
				1, 2,
				4, 4,
				4, 4);
		
		JPanel center = new JPanel();
		center.add(additionalPanel);
		
		controlPanel.add(center);
	}

	public void solve(int numSol, ConfigurationOptions options){
		Configuration config = getCurrentConfiguration();
		config.debugPrint();
		
		solver.solve(config, options);
		int i = 0;
		
		if( !solver.hasNextSolution() ){
			System.out.println("No solutions found !!");
			return;
		}
		while( solver.hasNextSolution() && i < numSol){
			Configuration sol = solver.getSolution();
			solutionPanel.addSolution(sol);
			sol.debugPrint();
			i++;
		}
		solutionPanel.expand();
		//
	}
	
	public ConfigurationOptions getCurrentOptions(){
		ConfigurationOptions op = new ConfigurationOptions();
		for(int i = 0; i < ((DefaultListModel<String>)additionalConstraints.getModel()).size();i++)
			op.getAdditionalConstraints().add(((DefaultListModel<String>)additionalConstraints.getModel()).elementAt(i));
		return op;
	}
	
	public Configuration getCurrentConfiguration(){
		Configuration config = new Configuration();
		addToConfiguration(root, config);
		return config;
	}
	
	protected static void addToConfiguration(ConfigurationNode node, Configuration conf){
		if( node.getVariable() != null ){
			conf.set(node.getVariable().getName(), (Integer)node.getVariable().getValue());
		}
		
		for(ConfigurationNode n : node.getChildren())
			addToConfiguration(n, conf);
	}
	
	public void clearProducts(){
		solutionPanel.clearSolutions();
	}
	
	private DefaultMutableTreeNode makeNodeFor(Configuration solution){
		DefaultMutableTreeNode solNode = new DefaultMutableTreeNode();

		for( String id : solution.getNotIgnored() ){
			if( solution.stateOf(id) == Configuration.BANNED )
				continue;
			
			VariabilityElement ve = productLine.getVariabilityElement(id);
			solNode.add(new DefaultMutableTreeNode(ve.getName()));
		}
		return solNode;
	}
	
	public void addSolution(Configuration solution) {
		products.add(solution);
	}

	public void taskCompleted(ConfigurationTask task, long timeMillis) {
		float secs = timeMillis / 1000f;
//		productsTree.expandRow(0);
//		productsTree.updateUI();
		setStatus("Task Completed in " + secs + " seconds. " + task.getSolutionsFound() + " solutions found.");
		
//		if( getOperationMode() == OperationMode.AUTOMATIC ){
//			disableInvalid();
//		}
	}

	private void disableInvalid() {
		for(VariabilityElement e : productLine.getVariabilityElements()){
			boolean used = false;
			for(Configuration conf : products ){
				if( conf.stateOf(e.getIdentifier()) == Configuration.ENFORCED ){
					used = true;
					break;
				}
			}
			if( !used ){
//				table.disableNode(e.getIdentifier());
			}
		}
//		table.updateUI();
	}
	
	
	public ProductLine getProductLine() {
		return this.productLine;
	}

	public void setStatus(String string) {
		lblStatus.setText(string);
	}

	public void configure(ProductLine pl) {
		this.removeAll();
		initComponents();
		this.productLine = pl;
		this.solver.setProductLine(pl);
		
		List<VariabilityElement> ordered = new ArrayList<VariabilityElement>(pl.getVariabilityElements());
		
		Collections.sort(ordered, new Comparator<VariabilityElement>(){
			@Override
			public int compare(VariabilityElement ve1, VariabilityElement ve2) {
				return ve1.getName().compareTo(ve2.getName());
			}
		});
		
		for(VariabilityElement el : ordered){
			Variable var = new Variable();
			
			var.setType(IntegerType.IDENTIFIER);
			var.setName(el.getName());
			//var.setName(el.getIdentifier());
			var.setDomain(BinaryDomain.INSTANCE);
			var.setValue(null);
			//GARA
			//System.out.println(el.getIdentifier());
			ConfigurationNode node = new ConfigurationNode();
			node.setVariable(var);
			
			//Add Attributes
			for(Variable v : el.getVarAttributes() ){
				ConfigurationNode attNode = new ConfigurationNode();
				attNode.setVariable(v);
				node.getChildren().add(attNode);
			}
			
			root.getChildren().add(node);
		}
		table.expandRow(0);
		solutionPanel.expand();
		
		resizeColumns();
	}
	
	public void populateNode(ProductLine pl, VariabilityElement ve, ConfigurationNode parent, Set<String> visited){
		
		if( visited.contains(ve.getIdentifier() ))
			return;
		
		visited.add(ve.getIdentifier());
		
		Variable var = new Variable();
		var.setType(IntegerType.IDENTIFIER);
		var.setName(ve.getIdentifier());
		var.setDomain(BinaryDomain.INSTANCE);
		var.setValue(null);
		
		ConfigurationNode node = new ConfigurationNode();
		node.setVariable(var);
		
		for( Constraint c : pl.getConstraintsStartingFrom(ve) ){
			List<String> rel = c.getRelatedIds();
			for(String childId : rel){
				VariabilityElement child = pl.getVariabilityElement(childId);
				populateNode(pl, child, node, visited);
			}
		}
		
		parent.getChildren().add(node);
	}
	
	public void changedConfiguration() {
//		if( getOperationMode() == OperationMode.AUTOMATIC )
//			performConfiguration();
	}

	public void performConfiguration() {
		GNUPrologContext ctx = new GNUPrologContext();
		PrologSolver solver = new PrologSolver(ctx);
		solver.setProductLine(getProductLine());
		Configuration configuration = getCurrentConfiguration();
		configuration.debugPrint();
		
		DefaultConfigurationTaskListener listener = new DefaultConfigurationTaskListener(this);
		
		ConfigurationTask task = new ConfigurationTask(solver, configuration, getCurrentOptions(), listener);
		new Thread(task).start();
	}

	public void setValueToVariable(Variable variable, Integer value, int index) {
		ConfigurationNode node = findConfigurationNodeFor(variable.getName());
		
		node.getVariable().setValue(value);
		node.setStepEdited(index);
		//resizeColumns();
		this.repaint();
	}
	
	public void resizeColumns(){
		//table.resizeColumns();
	}
//	public void setChoiceToVariable(Variable variable, Choice choice, int index){
//		int row = findRowFor(variable);
//		
//		TreeTableModelAdapter model = (TreeTableModelAdapter)table.getModel();
//		model.setValueAt(choice, row, column)
//	}
	
	private int findRowFor(Variable variable){
		for(int i = 0; i < table.getRowCount(); i++){
			String varId = (String)table.getValueAt(i, 0);
			if(variable.getName().equals(varId))
				return i;
		}
		return -1;
	}
	
	private ConfigurationNode findConfigurationNodeFor(String name){
		for(int i = 0; i < table.getRowCount(); i++){
			String varId = (String)table.getValueAt(i, 0);
			if(name.equals(varId))
				return getConfigurationNode(i);
		}
		return null;
	}
	
	public ConfigurationNode getConfigurationNode(int row){
		TreeTableModelAdapter model = (TreeTableModelAdapter)table.getModel();
		return (ConfigurationNode)model.nodeForRow(row);
	}
	
	public ConfigurationNode getConfigurationNode(String varName){
		TreeTableModelAdapter model = (TreeTableModelAdapter)table.getModel();
		for(int i = 1; i < model.getRowCount(); i++){
			ConfigurationNode node = (ConfigurationNode)model.nodeForRow(i);
			if( varName.equalsIgnoreCase(node.getName()))
				return node;
		}
		return null;
	}
	
	public List<ConfigurationNode> getAllConfigurationNodes(){
		TreeTableModelAdapter model = (TreeTableModelAdapter)table.getModel();
		ArrayList<ConfigurationNode> nodes = new ArrayList<>();
		for(int i = 1; i < model.getRowCount(); i++){
			nodes.add( (ConfigurationNode) model.nodeForRow(i) );
		}
		return nodes;
	}
	
	public ConfigurationTreeTable getTable(){
		return table;
	}
	
	public void reduceDomain(){
		Configuration config = getCurrentConfiguration();
		//config.debugPrint();
		Map<String, List<Integer>> values = solver.reduceDomain(config, getCurrentOptions());
		
		List<ConfigurationNode> allNodes = getAllConfigurationNodes();
		for( ConfigurationNode c : allNodes ){
			List<Integer> newValues = values.get( c.getName() );
			if( newValues == null )
				continue;
			
			addDomainAnnotations(c, newValues, 0);
		}
	}

	private void addDomainAnnotations(ConfigurationNode c, List<Integer> newValues, int step) {
		List<Integer> oldValues = c.getVariable().getDomain().getPossibleValues();
		
		List<DomainAnnotation> dm = c.getDomainAnnotations();
		
		for(Integer i : oldValues ){
			
			DomainAnnotation existing = c.getAnnotationFor(i);
			if( existing != null ){
				if( existing.getChoice() == Choice.CROSS )
					continue;
			}
			
			if( !newValues.contains(i) && !dm.contains(i))
				dm.add(new DomainAnnotation(i, Choice.CROSS, step));
		}
		
	}

	public void setConfiguration(ConfigurationDTO dto) {
		TreeMap<String, Integer> values = dto.getValues().getConfiguration();
		for(String varName : values.keySet()){
			ConfigurationNode node = getConfigurationNode(varName);
			if( node == null )
				continue;
			
			node.setValue(values.get(varName));
		}
		
		List<String> cons = dto.getOptions().getAdditionalConstraints();
		for(String str : cons)
			((DefaultListModel<String>)additionalConstraints.getModel()).addElement(str);
		
		table.expandRow(0);
		table.repaint();
	}

	public ConfigurationDTO getConfigurationDTO() {
		ConfigurationDTO dto = new ConfigurationDTO();
		dto.setValues( getCurrentConfiguration() );
		dto.setOptions( getCurrentOptions() );
		return dto;
	}
	
	public List<Configuration> getSolutions(){
		return solutionPanel.getAllSolutions();
	}
	
//	public OperationMode getOperationMode() {
//		return operationMode;
//	}
//
//	public void setOperationMode(OperationMode operationMode) {
//		this.operationMode = operationMode;
//	}
	
}
