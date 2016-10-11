package com.variamos.gui.pl.editor;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.cfm.common.AbstractModel;
import com.cfm.productline.Constraint;
import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.Variable;
import com.variamos.configurator.Choice;
import com.variamos.configurator.Configurator;
import com.variamos.configurator.DomainAnnotation;
import com.variamos.configurator.io.ConfigurationDTO;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.translation.ModelExpr2HLCL;
import com.variamos.dynsup.types.IntegerType;
import com.variamos.gui.common.jelements.AbstractConfigurationPanel;
import com.variamos.gui.pl.configurator.treetable.ConfigurationNode;
import com.variamos.gui.pl.configurator.treetable.ConfigurationTreeTable;
import com.variamos.gui.treetable.core.TreeTableModelAdapter;
import com.variamos.hlcl.BinaryDomain;
import com.variamos.solver.Configuration;
import com.variamos.solver.ConfigurationTask;

/**
 * @author unknown jcmunoz: commented unused methods
 *
 */
@SuppressWarnings("serial")
public class ConfiguratorPanel extends AbstractConfigurationPanel {
	private ProductLine productLine;

	// Configurator table settings
	private ConfigurationTreeTable table;
	private ConfigurationNode root;
	// private ConfigurationDataModel dataModel;
	private Configurator configurator;

	private ModelExpr2HLCL refas2hlcl;
	// private AbstractModel abstractModel;

	private JLabel lblStatus;
	private JPanel controlPanel;
	private JTable tblSolutions;

	private JList<String> additionalConstraints;

	// Elements
	JLabel lblAvailableVars;
	JList lstAvailableVars;
	JScrollPane lstAvailableVarsScroll;
	JLabel lblSelectedVar;
	JTextField txtSelectedVar;
	JLabel lblValue;
	JComboBox<String> cmbDomain;
	JButton cmdSet;
	JLabel lblAffectedVarsBySelection;
	JLabel lblAffectedVarsByNonSelection;
	JList lstAffectedVarsBySelection;
	JList lstAffectedVarsByNonSelection;
	JButton cmdBack;
	JButton cmdNextVar;
	JLabel lblConfiguredVars;
	JButton cmdRemoveConfiguredVar;
	JButton cmdEditConfiguredVar;
	JTable tblConfiguredVars;
	JScrollPane tblConfiguredVarsScroll;
	JLabel lblAdditionalConstraints;
	JTextField txtAdditionalConstraint;
	JButton cmdAddConstraint;
	JButton cmdRemoveConstraint;
	JButton cmdEditConstraint;
	JButton cmdCancelActionConstraint;
	JList lstAdditionalConstraints;
	JButton cmdGetSolutions;
	JButton cmdGetNextSolution;
	JScrollPane tblSolutionsScroll;

	public ConfiguratorPanel() {
		// setLayout(new FlowLayout(FlowLayout.LEFT));
		configurator = new Configurator();
		initComponents();
		setConfigPanelVisibility(true);
	}

	public void setRefas2hlcl(ModelExpr2HLCL refas2hlcl) {
		this.refas2hlcl = refas2hlcl;
	}

	private void initComponents() {

		JPanel configurationPanel = new JPanel();
		JPanel solutionPanel = new JPanel();
		// configurationPanel.setLayout(new GridBagLayout());
		initConfigurationPane(configurationPanel);
		initSolutionPane(solutionPanel);
		configurationPanel.setBorder(BorderFactory
				.createTitledBorder("Configure"));
		solutionPanel.setBorder(BorderFactory.createTitledBorder("Solutions"));
		add(configurationPanel);
		add(solutionPanel);
		// initSolutionPanel();

		/*
		 * setLayout(new BorderLayout());
		 * 
		 * root = new ConfigurationNode(); dataModel = new
		 * ConfigurationDataModel(root, this); table = new
		 * ConfigurationTreeTable(dataModel);
		 * 
		 * //Setup the preferred dimensions.
		 * 
		 * TableColumnModel tcm = table.getColumnModel();
		 * tcm.getColumn(ConfigurationDataModel
		 * .COLUMN_NAME).setPreferredWidth(150);
		 * tcm.getColumn(ConfigurationDataModel
		 * .COLUMN_VALUE).setPreferredWidth(20);
		 * tcm.getColumn(ConfigurationDataModel
		 * .COLUMN_STEP).setPreferredWidth(20);
		 * 
		 * table.resizeColumns(); // TableColumnAdjuster tca = new
		 * TableColumnAdjuster(table); // tca.adjustColumns();
		 * 
		 * JTableHeader header = table.getTableHeader();r JPanel panel = new
		 * JPanel(); panel.setLayout(new BorderLayout()); panel.add(header,
		 * BorderLayout.NORTH); panel.add(table, BorderLayout.CENTER);
		 * panel.setBorder(BorderFactory.createTitledBorder("Configuration"));
		 * add(panel, BorderLayout.WEST);
		 * 
		 * 
		 * initControlPanel(); add(controlPanel, BorderLayout.SOUTH);
		 * 
		 * initSolutionPanel();
		 */
	}

	private void initConfigurationPane(JPanel pane) {

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// restricciones comunes a todos los componentes
		c.insets = new Insets(5, 5, 0, 0); // top padding

		// FILA 0
		c.gridy = 0;

		lblAvailableVars = new JLabel("Available Variables:");
		c.gridx = 0;
		c.ipadx = 50;
		pane.add(lblAvailableVars, c);

		lblSelectedVar = new JLabel("Variable:");
		c.gridx = 1;
		c.ipadx = 0;
		pane.add(lblSelectedVar, c);

		txtSelectedVar = new JTextField();
		txtSelectedVar.setEditable(false);
		c.gridx = 2;
		c.ipadx = 150;
		pane.add(txtSelectedVar, c);

		lblValue = new JLabel("Value:");
		c.gridx = 3;
		c.ipadx = 0;
		pane.add(lblValue, c);

		cmbDomain = new JComboBox<String>();
		c.gridx = 4;
		c.ipadx = 100;
		pane.add(cmbDomain, c);

		cmdSet = new JButton("Set");
		c.gridx = 5;
		c.ipadx = 0;
		pane.add(cmdSet, c);

		lblConfiguredVars = new JLabel("Configured Variables:");
		c.gridx = 6;
		c.ipadx = 0;
		pane.add(lblConfiguredVars, c);

		cmdRemoveConfiguredVar = new JButton("Remove");
		cmdRemoveConfiguredVar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initConfigurationProcess();

			}
		});
		c.gridx = 7;
		pane.add(cmdRemoveConfiguredVar, c);

		cmdEditConfiguredVar = new JButton("Edit");
		c.gridx = 8;
		pane.add(cmdEditConfiguredVar, c);

		lblAdditionalConstraints = new JLabel("Additional Constraints:");
		c.gridx = 10;
		c.gridwidth = 2;
		pane.add(lblAdditionalConstraints, c);

		// FILA 1
		c.gridy = 1;

		lstAvailableVars = new JList();
		lstAvailableVars.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				processAvailableVarSelection(e);

			}
		});
		lstAvailableVarsScroll = new JScrollPane();
		lstAvailableVars.setModel(new DefaultListModel());
		lstAvailableVars
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		lstAvailableVarsScroll.setViewportView(lstAvailableVars);

		c.ipadx = 0;
		c.ipady = 275; // make this component tall
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridheight = 10;
		c.gridwidth = 1;
		pane.add(lstAvailableVarsScroll, c);

		lblAffectedVarsBySelection = new JLabel(
				"Affected variables by selection");
		c.ipady = 0;
		c.weightx = 0.0;
		c.gridx = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		pane.add(lblAffectedVarsBySelection, c);

		lblAffectedVarsByNonSelection = new JLabel(
				"Affected variables by non selection");
		c.ipady = 0;
		c.weightx = 0.0;
		c.gridx = 3;
		c.gridwidth = 2;
		pane.add(lblAffectedVarsByNonSelection, c);

		tblConfiguredVars = new JTable();
		tblConfiguredVars.setModel(new DefaultTableModel(new Object[][] { {
				null, null, null } }, new String[] { "Variable", "Value",
				"Step" }));
		tblConfiguredVarsScroll = new JScrollPane();
		tblConfiguredVarsScroll.setPreferredSize(new Dimension(300, 400));
		tblConfiguredVarsScroll.setViewportView(tblConfiguredVars);
		c.ipady = 0;
		c.gridx = 6;
		c.gridheight = 10;
		c.gridwidth = 4;
		pane.add(tblConfiguredVarsScroll, c);

		txtAdditionalConstraint = new JTextField();
		c.ipadx = 200;
		c.gridx = 10;
		c.gridheight = 1;
		c.gridwidth = 2;
		pane.add(txtAdditionalConstraint, c);

		cmdEditConstraint = new JButton("Edit");
		c.gridx = 10;
		c.ipadx = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		// pane.add(cmdEditConstraint,c);

		cmdRemoveConstraint = new JButton("Remove");
		c.gridx = 11;
		c.ipadx = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		// pane.add(cmdRemoveConstraint,c);

		cmdCancelActionConstraint = new JButton("Cancel");
		c.gridx = 12;
		c.ipadx = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		// pane.add(cmdCancelActionConstraint,c);

		cmdAddConstraint = new JButton("Add");
		c.ipady = 0;
		c.ipadx = 0;
		c.gridx = 13;
		c.gridheight = 1;
		c.gridwidth = 1;
		pane.add(cmdAddConstraint, c);

		// FILA 2
		c.gridy = 2;

		lstAffectedVarsBySelection = new JList();
		c.ipady = 350; // make this component tall
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		pane.add(lstAffectedVarsBySelection, c);

		lstAffectedVarsByNonSelection = new JList();
		c.ipady = 350;
		c.weightx = 0.0;
		c.gridx = 3;
		c.gridwidth = 2;
		pane.add(lstAffectedVarsByNonSelection, c);

		lstAdditionalConstraints = new JList();
		c.ipady = 350;
		c.gridx = 10;
		c.gridwidth = 9;
		pane.add(lstAdditionalConstraints, c);

		// FILA 3
		c.gridy = 3;

		cmdBack = new JButton("Back");
		c.ipady = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridwidth = 1;
		pane.add(cmdBack, c);

		cmdNextVar = new JButton("Next Variable");
		c.ipady = 0;
		c.weightx = 0.0;
		c.gridx = 2;
		c.gridwidth = 3;
		pane.add(cmdNextVar, c);

	}

	private void initSolutionPane(JPanel pane) {
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// restricciones comunes a todos los componentes
		c.insets = new Insets(5, 5, 0, 0); // top padding

		// FILA 0
		c.gridy = 0;

		cmdGetSolutions = new JButton("Get Solutions");
		c.gridx = 0;
		pane.add(cmdGetSolutions, c);

		cmdGetNextSolution = new JButton("Get Next Solution");
		c.gridx = 1;
		pane.add(cmdGetNextSolution, c);

		// FILA 1
		c.gridy = 1;

		tblSolutions = new JTable();
		tblSolutions.setModel(new DefaultTableModel(new Object[][] { { null,
				null } }, new String[] { "Solution", "Variables" }));
		tblSolutionsScroll = new JScrollPane();
		tblSolutionsScroll.setPreferredSize(new Dimension(300, 400));
		tblSolutionsScroll.setViewportView(tblSolutions);
		c.ipady = 0;
		c.gridx = 0;
		c.gridheight = 10;
		c.gridwidth = 4;
		pane.add(tblSolutionsScroll, c);

	}

	private void processAvailableVarSelection(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			txtSelectedVar
					.setText((String) lstAvailableVars.getSelectedValue());
			setConfigPanelVisibility(false);
			;

		}
	}

	protected void processSelectionOnTable() {
		DefaultTableModel model = (DefaultTableModel) tblSolutions.getModel();
		JOptionPane.showMessageDialog(
				null,
				model.getValueAt(tblSolutions.getSelectedRow(), 1),
				"Solution Number: "
						+ model.getValueAt(tblSolutions.getSelectedRow(), 0),
				JOptionPane.INFORMATION_MESSAGE);

	}

	private boolean processConfiguration(Configuration configuration) {
		TreeMap<String, Number> configSet = configuration.getConfiguration();
		StringBuilder sb = new StringBuilder();
		for (String identifier : configSet.keySet()) {
			String[] split = identifier.split("_");
			String vertexId = split[0];
			String attribute = split[1];
			if ("Sel".equals(attribute)) {
				if (configSet.get(identifier).floatValue() == 1) {// variable
																	// seleccionada
					String var = refas2hlcl.getRefas().getVertex(vertexId)
							.toString();
					if (!var.contains("mutex") && !var.contains("and")
							&& !var.contains("or"))
						sb.append(" " + var + " "); // create object Solution
													// and
													// save all the info of the
													// solution
				}
			}
		}

		// sb.deleteCharAt(sb.length()-1);

		DefaultTableModel model = (DefaultTableModel) tblSolutions.getModel();// must
																				// configure
																				// without
																				// the
																				// attributes
		if (model.getRowCount() == 0) {
			model.addRow(new Object[] { model.getRowCount() + 1, sb.toString() });
			return true;
		} else {
			boolean existe = false;
			for (int i = 0; i < model.getRowCount(); i++) {
				String lastConf = (String) model.getValueAt(i, 1);
				if (lastConf.equals(sb.toString())) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				model.addRow(new Object[] { model.getRowCount() + 1,
						sb.toString() });
				return true;
			}
			return false;
		}

	}

	private void initConfigurationProcess() {
		initValues();
		setConfigPanelVisibility(true);

	}

	private void initValues() {
		Map<String, InstElement> variables = refas2hlcl.getRefas()
				.getVariabilityVertex();
		DefaultListModel listModel = (DefaultListModel) lstAvailableVars
				.getModel();
		DefaultTableModel tableModel = (DefaultTableModel) tblConfiguredVars
				.getModel();
		listModel.clear();
		tableModel.setRowCount(0);
		for (String identifier : variables.keySet()) {
			// se evalúa si la variable está libre
			InstElement var = variables.get(identifier);
			System.out.println("hace parte del core "
					+ var.getInstAttribute("Core").getAsBoolean());
			if (var.getInstAttribute("Core").getAsBoolean()) {
				tableModel.addRow(new Object[] { var.toString(),
						var.getInstAttribute("Sel").getAsBoolean(), 0 });
				continue;
			}

			if (!var.getInstAttribute("Sel").isEnabled()
					&& !var.getInstAttribute("NNotSel").isEnabled()) {
				listModel.add(0, variables.get(identifier).toString());
			}
		}
	}

	private void setConfigPanelVisibility(boolean availablesVars) {
		lblAvailableVars.setVisible(availablesVars);
		lstAvailableVarsScroll.setVisible(availablesVars);
		lblSelectedVar.setVisible(!availablesVars);
		txtSelectedVar.setVisible(!availablesVars);
		lblValue.setVisible(!availablesVars);
		cmbDomain.setVisible(!availablesVars);
		cmdSet.setVisible(!availablesVars);
		lblAffectedVarsBySelection.setVisible(!availablesVars);
		lblAffectedVarsByNonSelection.setVisible(!availablesVars);
		lstAffectedVarsBySelection.setVisible(!availablesVars);
		lstAffectedVarsByNonSelection.setVisible(!availablesVars);
		cmdBack.setVisible(!availablesVars);
		cmdNextVar.setVisible(!availablesVars);
	}

	public Configuration getCurrentConfiguration() {
		Configuration config = new Configuration();
		addToConfiguration(root, config);
		return config;
	}

	protected static void addToConfiguration(ConfigurationNode node,
			Configuration conf) {
		if (node.getVariable() != null) {
			conf.set(node.getVariable().getName(), (Integer) node.getVariable()
					.getValue());
		}

		for (ConfigurationNode n : node.getChildren())
			addToConfiguration(n, conf);
	}

	@Override
	public void addSolution(Configuration solution) {
		configurator.addSolution(solution);
	}

	@Override
	public void taskCompleted(ConfigurationTask task, long timeMillis) {
		float secs = timeMillis / 1000f;
		// productsTree.expandRow(0);
		// productsTree.updateUI();
		setStatus("Task Completed in " + secs + " seconds. "
				+ task.getSolutionsFound() + " solutions found.");

		// if( getOperationMode() == OperationMode.AUTOMATIC ){
		// disableInvalid();
		// }
	}

	/*
	 * private void disableInvalid() { boolean used=
	 * configurator.validateInvalid();
	 * 
	 * if( !used ){ // table.disableNode(e.getIdentifier()); }
	 * 
	 * // table.updateUI(); }
	 */

	public ProductLine getProductLine() {
		return this.productLine;
	}

	@Override
	public void setStatus(String string) {
		lblStatus.setText(string);
	}

	@Override
	public void configure(AbstractModel am) {
		ProductLine pl = (ProductLine) am;
		this.removeAll();
		initComponents();
		this.productLine = pl;
		configurator.setSolverProductLine(pl);

		List<VariabilityElement> ordered = new ArrayList<VariabilityElement>(
				pl.getVariabilityElements());

		Collections.sort(ordered, new Comparator<VariabilityElement>() {
			@Override
			public int compare(VariabilityElement ve1, VariabilityElement ve2) {
				return ve1.getName().compareTo(ve2.getName());
			}
		});

		for (VariabilityElement el : ordered) {
			Variable var = new Variable();

			var.setType(IntegerType.IDENTIFIER);
			var.setName(el.getName());
			// var.setName(el.getIdentifier());
			var.setDomain(BinaryDomain.INSTANCE);
			var.setValue(null);
			// GARA
			// System.out.println(el.getIdentifier());
			ConfigurationNode node = new ConfigurationNode();
			node.setVariable(var);

			// Add Attributes
			for (Variable v : el.getVarAttributes()) {
				ConfigurationNode attNode = new ConfigurationNode();
				attNode.setVariable(v);
				node.getChildren().add(attNode);
			}

			root.getChildren().add(node);
		}
		table.expandRow(0);

		resizeColumns();
	}

	public void populateNode(ProductLine pl, VariabilityElement ve,
			ConfigurationNode parent, Set<String> visited) {

		if (visited.contains(ve.getIdentifier()))
			return;

		visited.add(ve.getIdentifier());

		Variable var = new Variable();
		var.setType(IntegerType.IDENTIFIER);
		var.setName(ve.getIdentifier());
		var.setDomain(BinaryDomain.INSTANCE);
		var.setValue(null);

		ConfigurationNode node = new ConfigurationNode();
		node.setVariable(var);

		for (Constraint c : pl.getConstraintsStartingFrom(ve)) {
			List<String> rel = c.getRelatedIds();
			for (String childId : rel) {
				VariabilityElement child = pl.getVariabilityElement(childId);
				populateNode(pl, child, node, visited);
			}
		}

		parent.getChildren().add(node);
	}

	public void changedConfiguration() {
		// if( getOperationMode() == OperationMode.AUTOMATIC )
		// performConfiguration();
	}

	@Override
	public void setValueToVariable(Variable variable, Integer value, int index) {
		ConfigurationNode node = findConfigurationNodeFor(variable.getName());

		node.getVariable().setValue(value);
		node.setStepEdited(index);
		// resizeColumns();
		this.repaint();
	}

	@Override
	public void resizeColumns() {
		// table.resizeColumns();
	}

	// public void setChoiceToVariable(Variable variable, Choice choice, int
	// index){
	// int row = findRowFor(variable);
	//
	// TreeTableModelAdapter model = (TreeTableModelAdapter)table.getModel();
	// model.setValueAt(choice, row, column)
	// }
	/*
	 * private int findRowFor(Variable variable){ for(int i = 0; i <
	 * table.getRowCount(); i++){ String varId = (String)table.getValueAt(i, 0);
	 * if(variable.getName().equals(varId)) return i; } return -1; }
	 */
	private ConfigurationNode findConfigurationNodeFor(String name) {
		for (int i = 0; i < table.getRowCount(); i++) {
			String varId = (String) table.getValueAt(i, 0);
			if (name.equals(varId))
				return getConfigurationNode(i);
		}
		return null;
	}

	public ConfigurationNode getConfigurationNode(int row) {
		TreeTableModelAdapter model = (TreeTableModelAdapter) table.getModel();
		return (ConfigurationNode) model.nodeForRow(row);
	}

	public ConfigurationNode getConfigurationNode(String varName) {
		TreeTableModelAdapter model = (TreeTableModelAdapter) table.getModel();
		for (int i = 1; i < model.getRowCount(); i++) {
			ConfigurationNode node = (ConfigurationNode) model.nodeForRow(i);
			if (varName.equalsIgnoreCase(node.getName()))
				return node;
		}
		return null;
	}

	public List<ConfigurationNode> getAllConfigurationNodes() {
		TreeTableModelAdapter model = (TreeTableModelAdapter) table.getModel();
		ArrayList<ConfigurationNode> nodes = new ArrayList<>();
		for (int i = 1; i < model.getRowCount(); i++) {
			nodes.add((ConfigurationNode) model.nodeForRow(i));
		}
		return nodes;
	}

	public ConfigurationTreeTable getTable() {
		return table;
	}

	private void addDomainAnnotations(ConfigurationNode c,
			List<Integer> newValues, int step) {
		List<Integer> oldValues = c.getVariable().getDomain()
				.getPossibleValues();

		List<DomainAnnotation> dm = c.getDomainAnnotations();

		for (Integer i : oldValues) {

			DomainAnnotation existing = c.getAnnotationFor(i);
			if (existing != null) {
				if (existing.getChoice() == Choice.CROSS)
					continue;
			}

			if (!newValues.contains(i) && !dm.contains(i))
				dm.add(new DomainAnnotation(i, Choice.CROSS, step));
		}

	}

	public void setConfiguration(ConfigurationDTO dto) {
		TreeMap<String, Number> values = dto.getValues().getConfiguration();
		for (String varName : values.keySet()) {
			ConfigurationNode node = getConfigurationNode(varName);
			if (node == null)
				continue;

			node.setValue(values.get(varName));
		}

		List<String> cons = dto.getOptions().getAdditionalConstraints();
		for (String str : cons)
			((DefaultListModel<String>) additionalConstraints.getModel())
					.addElement(str);

		table.expandRow(0);
		table.repaint();
	}

	@Override
	public void clearProducts() {
		// TODO Auto-generated method stub

	}
}
