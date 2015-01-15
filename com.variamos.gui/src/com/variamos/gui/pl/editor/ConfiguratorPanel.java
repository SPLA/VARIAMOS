package com.variamos.gui.pl.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.util.Vector;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.cfm.common.AbstractModel;
import com.cfm.hlcl.BinaryDomain;
import com.cfm.productline.Constraint;
import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.Variable;
import com.cfm.productline.solver.Configuration;
import com.cfm.productline.solver.ConfigurationOptions;
import com.cfm.productline.solver.ConfigurationTask;
import com.variamos.gui.common.jelements.AbstractConfigurationPanel;
import com.variamos.gui.pl.configurator.guiactions.DefaultConfigurationTaskListener;
import com.variamos.gui.pl.configurator.solution.SolutionPanel;
import com.variamos.gui.pl.configurator.treetable.ConfigurationDataModel;
import com.variamos.gui.pl.configurator.treetable.ConfigurationNode;
import com.variamos.gui.pl.configurator.treetable.ConfigurationTreeTable;
import com.variamos.gui.treetable.core.TreeTableModelAdapter;
import com.variamos.pl.configurator.Choice;
import com.variamos.pl.configurator.Configurator;
import com.variamos.pl.configurator.DomainAnnotation;
import com.variamos.pl.configurator.io.ConfigurationDTO;
import com.variamos.refas.core.simulationmodel.Refas2Hlcl;
import com.variamos.syntaxsupport.type.IntegerType;

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
	private ConfigurationDataModel dataModel;
	private Configurator configurator;

	private SolutionPanel solutionPanel;
	private Refas2Hlcl refas2hlcl;
	private AbstractModel abstractModel;

	private JLabel lblStatus;
	private JPanel controlPanel;
	private JTable tblSolutions;

	private JList<String> additionalConstraints;

	public ConfiguratorPanel() {
		// setLayout(new FlowLayout(FlowLayout.LEFT));
		configurator = new Configurator();
		initComponents();
	}

	public void setRefas2hlcl(Refas2Hlcl refas2hlcl) {
		this.refas2hlcl = refas2hlcl;
	}

	private void initComponents() {

		JPanel configurationPanel = new JPanel();
		// configurationPanel.setLayout(new GridBagLayout());
		initConfiturationPaneNB();
		configurationPanel.setBorder(BorderFactory
				.createTitledBorder("Configure"));
		add(configurationPanel);

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
		 * JTableHeader header = table.getTableHeader();
		 * 
		 * JPanel panel = new JPanel(); panel.setLayout(new BorderLayout());
		 * panel.add(header, BorderLayout.NORTH); panel.add(table,
		 * BorderLayout.CENTER);
		 * panel.setBorder(BorderFactory.createTitledBorder("Configuration"));
		 * add(panel, BorderLayout.WEST);
		 * 
		 * 
		 * initControlPanel(); add(controlPanel, BorderLayout.SOUTH);
		 * 
		 * initSolutionPanel();
		 */
	}

	private void initConfiturationPaneNB() {
		JLabel lblVariable = new javax.swing.JLabel();
		JTextField txtVariable = new javax.swing.JTextField();
		JLabel lblVarDescription = new javax.swing.JLabel();
		JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
		JTextArea txtVarDescription = new javax.swing.JTextArea();
		JLabel lblAvailableVariables = new javax.swing.JLabel();
		JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
		JList lstAvailableVariables = new javax.swing.JList();
		JLabel lblValue = new javax.swing.JLabel();
		JLabel lblAffectedVariables = new javax.swing.JLabel();
		JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
		JTable tblAffectedVariables = new javax.swing.JTable();
		JButton cmdSet = new javax.swing.JButton();
		JScrollPane jScrollPane4 = new javax.swing.JScrollPane();
		JTable tblConfiguredVariables = new javax.swing.JTable();
		JLabel lblConfiguredVariables = new javax.swing.JLabel();
		JButton cmdNext = new javax.swing.JButton();
		JButton cmdClear = new javax.swing.JButton();
		JButton cmdRemoveConfiguredVar = new javax.swing.JButton();
		JButton cmdUndo = new javax.swing.JButton();
		JLabel lblAdditionalConstraints = new javax.swing.JLabel();
		JTextField txtAdditionalConstraint = new javax.swing.JTextField();
		JButton cmdAddConstraint = new javax.swing.JButton();
		JScrollPane jScrollPane5 = new javax.swing.JScrollPane();
		JList lstAdditionalConstraints = new javax.swing.JList();
		JButton cmdRemoveConstraint = new javax.swing.JButton();
		JButton cmdEditConstraint = new javax.swing.JButton();
		JButton cmdEditConfiguredVar = new javax.swing.JButton();
		JScrollPane jScrollPane6 = new javax.swing.JScrollPane();
		tblSolutions = new javax.swing.JTable();
		final JButton cmdGetNextSolution = new javax.swing.JButton();
		JButton cmdGetSolutions = new javax.swing.JButton();
		JComboBox cmbOperators = new javax.swing.JComboBox();
		JComboBox cmbVarDomain = new javax.swing.JComboBox();

		lblVariable.setText("Variable:");

		txtVariable.setEditable(false);
		lblVarDescription.setText("Description:");

		txtVarDescription.setEditable(false);
		txtVarDescription.setColumns(20);
		txtVarDescription.setRows(5);
		jScrollPane1.setViewportView(txtVarDescription);

		lblAvailableVariables.setText("Available variables");

		lstAvailableVariables.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4",
					"Item 5" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		lstAvailableVariables
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		jScrollPane2.setViewportView(lstAvailableVariables);

		lblValue.setText("Value:");
		lblAffectedVariables.setText("Affected variables:");

		tblAffectedVariables.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, { null, null },
						{ null, null }, { null, null } }, new String[] {
						"Variable", "With value" }));
		jScrollPane3.setViewportView(tblAffectedVariables);

		cmdSet.setText("Set");
		tblConfiguredVariables
				.setModel(new javax.swing.table.DefaultTableModel(
						new Object[][] { { null, null, null },
								{ null, null, null }, { null, null, null },
								{ null, null, null } }, new String[] {
								"Variable", "Value", "Step" }));
		jScrollPane4.setViewportView(tblConfiguredVariables);

		lblConfiguredVariables.setText("Configured variables");

		cmdNext.setText("Next");

		cmdClear.setText("Clear");

		cmdRemoveConfiguredVar.setText("Remove");

		cmdUndo.setText("Undo");

		lblAdditionalConstraints.setText("Additional constraints:");

		cmdAddConstraint.setText("Add");

		lstAdditionalConstraints.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4",
					"Item 5" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane5.setViewportView(lstAdditionalConstraints);

		cmdRemoveConstraint.setText("Remove");

		cmdEditConstraint.setText("Edit");

		cmdEditConfiguredVar.setText("Edit");
		cmdEditConfiguredVar.setName("cmdEditConfiguredVariable"); // NOI18N

		tblSolutions.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {}, new String[] { "Solution", "Variables" }));
		
		tblSolutions.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				processSelectionOnTable();
			
			}
		});
		
		jScrollPane6.setViewportView(tblSolutions);
		tblSolutions.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblSolutions.getColumnModel().getColumn(1).setPreferredWidth(40);

		cmdGetNextSolution.setText("Get next solution");
		cmdGetNextSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (refas2hlcl.execute(Refas2Hlcl.NEXT_SOLUTION, Refas2Hlcl.CONF_EXEC))
					if (processConfiguration(refas2hlcl.getConfiguration()))
						break;
				
			}

		});
		cmdGetNextSolution.setVisible(false);

		cmdGetSolutions.setText("Get solutions");
		cmdGetSolutions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = refas2hlcl.execute(Refas2Hlcl.ONE_SOLUTION, Refas2Hlcl.CONF_EXEC);
				if (result) {
					processConfiguration(refas2hlcl.getConfiguration());
					cmdGetNextSolution.setVisible(true);
				}
			}

		});

		cmbOperators.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "=", ">", "<", ">=", "<=", "<>" }));
		cmbOperators.setName("cmbOperator"); // NOI18N

		cmbVarDomain.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		cmbVarDomain.setName("cmbDomain"); // NOI18N

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(55, 55, 55)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jScrollPane2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														174,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														174,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														lblAvailableVariables)
												.addComponent(lblVarDescription)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		lblVariable)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		txtVariable,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		128,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										lblValue)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										cmbOperators,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										cmbVarDomain,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										90,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										cmdSet))
																				.addComponent(
																						lblAffectedVariables))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						cmdClear)
																				.addComponent(
																						cmdNext)))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																				.addComponent(
																						jScrollPane4,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						0,
																						Short.MAX_VALUE)
																				.addComponent(
																						jScrollPane3,
																						javax.swing.GroupLayout.Alignment.LEADING,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						0,
																						Short.MAX_VALUE)
																				.addComponent(
																						lblConfiguredVariables,
																						javax.swing.GroupLayout.Alignment.LEADING,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						230,
																						Short.MAX_VALUE))
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										cmdUndo))
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addGap(4,
																										4,
																										4)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																												.addComponent(
																														cmdRemoveConfiguredVar,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														Short.MAX_VALUE)
																												.addComponent(
																														cmdEditConfiguredVar,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														Short.MAX_VALUE))))))
								.addGap(32, 32, 32)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jScrollPane5,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														216,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														lblAdditionalConstraints)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		txtAdditionalConstraint,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		152,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		cmdAddConstraint))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		cmdRemoveConstraint)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		cmdEditConstraint)))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										22, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		cmdGetSolutions)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		cmdGetNextSolution))
												.addComponent(
														jScrollPane6,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(69, 69, 69)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(35, 35, 35)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						cmdGetNextSolution)
																				.addComponent(
																						cmdGetSolutions))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jScrollPane6,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		286,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						lblAdditionalConstraints)
																				.addComponent(
																						lblVariable)
																				.addComponent(
																						txtVariable,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						lblValue)
																				.addComponent(
																						cmbOperators,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						cmbVarDomain,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						cmdSet)
																				.addComponent(
																						cmdNext))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						txtAdditionalConstraint,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						cmdAddConstraint,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						23,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						cmdClear)
																				.addComponent(
																						lblAffectedVariables)
																				.addComponent(
																						lblVarDescription))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addComponent(
																										jScrollPane5)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.BASELINE)
																												.addComponent(
																														cmdRemoveConstraint)
																												.addComponent(
																														cmdEditConstraint)))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(
																																		jScrollPane1,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addPreferredGap(
																																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																.addComponent(
																																		lblAvailableVariables))
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(
																																		jScrollPane3,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		96,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addPreferredGap(
																																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																																.addComponent(
																																		lblConfiguredVariables)))
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														jScrollPane4,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														130,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(
																														jScrollPane2,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										cmdUndo)
																								.addGap(104,
																										104,
																										104)
																								.addComponent(
																										cmdRemoveConfiguredVar)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										cmdEditConfiguredVar)))))
								.addGap(117, 117, 117)));
	}

	protected void processSelectionOnTable() {
		DefaultTableModel model=(DefaultTableModel)tblSolutions.getModel();
		JOptionPane.showMessageDialog(null, model.getValueAt(tblSolutions.getSelectedRow(), 1),"Solution Number: "+model.getValueAt(tblSolutions.getSelectedRow(), 0),JOptionPane.INFORMATION_MESSAGE);
		
	}

	private boolean processConfiguration(Configuration configuration) {
		TreeMap<String, Integer> configSet = configuration.getConfiguration();
		StringBuilder sb = new StringBuilder();
		for (String identifier : configSet.keySet()) {
			String[] split = identifier.split("_");
			String vertexId = split[0];
			String attribute = split[1];
			if ("Selected".equals(attribute)) {
				if (configSet.get(identifier) == 1) {// variable seleccionada
					String var=refas2hlcl.getRefas().getVertex(vertexId).toString();
					if(!var.contains("mutex") && !var.contains("and") && !var.contains("or"))
					sb.append(" "+ var + " "); // create object Solution and
												// save all the info of the
												// solution
				}
			}
		}
		
		//sb.deleteCharAt(sb.length()-1);
		

		DefaultTableModel model = (DefaultTableModel) tblSolutions.getModel();// must
																				// configure
																				// without
																				// the
																				// attributes
		if (model.getRowCount() == 0) {
			model.addRow(new Object[] { model.getRowCount() + 1, sb.toString() });
			return true;
		} else {
			boolean existe=false;
			for (int i = 0; i < model.getRowCount(); i++) {
				String lastConf = (String) model.getValueAt(
						i, 1);				
				if (lastConf.equals(sb.toString())) {
					existe=true;
					break;
				} 
			}
			if(!existe){
				model.addRow(new Object[] { model.getRowCount() + 1,	sb.toString() });
				return true;
			}
			return false;
		}
		

	}

	private void initConfigurationPane(JPanel panel) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(3, 3, 3, 3);

		// first row
		JLabel variableName = new JLabel("Variable:");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel.add(variableName, constraints);

		JTextField txtVariableName = new JTextField();
		txtVariableName.setEditable(false);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;

		panel.add(txtVariableName, constraints);

		JLabel variableValue = new JLabel("Value:");
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel.add(variableValue, constraints);

		JTextField txtVariableValue = new JTextField();
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel.add(txtVariableValue, constraints);

		JButton cmdSet = new JButton("Set");
		constraints.gridx = 4;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel.add(cmdSet, constraints);

		JButton cmdNext = new JButton("Next");
		constraints.gridx = 5;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel.add(cmdNext, constraints);
		cmdNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refas2hlcl.execute(Refas2Hlcl.ONE_SOLUTION, Refas2Hlcl.CONF_EXEC);
				solutionPanel.addSolution(refas2hlcl.getConfiguration());
			}
		});

		// second row
		JLabel variableDescription = new JLabel("Description:");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel.add(variableDescription, constraints);

		JLabel affectedVariables = new JLabel("Affected Variables:");
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel.add(affectedVariables, constraints);

		// third row
		JTextArea txtVariableDescription = new JTextArea();
		txtVariableDescription.setEditable(false);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		panel.add(txtVariableDescription, constraints);

		JList lstAffectedVariables = new JList();
		JScrollPane lstAffectedVariablesScroll = new JScrollPane(
				lstAffectedVariables);
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		panel.add(lstAffectedVariablesScroll, constraints);

		// fourth row
		JLabel lblAvailableVariables = new JLabel("Available Variables");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel.add(lblAvailableVariables, constraints);

		JLabel lblConfiguredVariables = new JLabel("Configured Variables");
		constraints.gridx = 3;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		panel.add(lblConfiguredVariables, constraints);

		// fifth row
		JList<String> lstAvailableVariables = new JList<String>();
		JScrollPane lstAvailableScroll = new JScrollPane(lstAvailableVariables);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 3;
		constraints.gridheight = 3;
		panel.add(lstAvailableScroll, constraints);
		DefaultListModel<String> availableVariablesModel = new DefaultListModel<String>();
		lstAvailableVariables.setModel(availableVariablesModel);
		availableVariablesModel.addElement("VAR 1");
		availableVariablesModel.addElement("VAR 2");

		JList<String> lstConfiguredVariables = new JList<String>();
		JScrollPane lstConfiguredScroll = new JScrollPane(
				lstConfiguredVariables);
		constraints.gridx = 3;
		constraints.gridy = 5;
		constraints.gridwidth = 3;
		constraints.gridheight = 3;
		panel.add(lstConfiguredScroll, constraints);
		DefaultListModel<String> configuredVariablesModel = new DefaultListModel<String>();
		lstConfiguredVariables.setModel(configuredVariablesModel);
		configuredVariablesModel.addElement("VAR3");

		/*
		 * 
		 * 
		 * 
		 * JButton cmdPrevious=new JButton("Previous"); JButton cmdRestart=new
		 * JButton("Restart"); configurationControls.add(cmdNext);
		 * configurationControls.add(cmdPrevious);
		 * configurationControls.add(cmdRestart);
		 * 
		 * 
		 * valuesManager.add(variableInfo);
		 * valuesManager.add(variableConfiguration);
		 * valuesManager.add(configurationControls);
		 * 
		 * 
		 * JPanel variablesManager=new JPanel(); variablesManager.setLayout(new
		 * GridLayout(2,2,10,10));
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * variablesManager.add(lblAvailableVariables);
		 * variablesManager.add(lblConfiguredVariables);
		 * variablesManager.add(lstAvailableScroll);
		 * variablesManager.add(lstConfiguredScroll);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //the panels are added to the configuration panel
		 * panel.add(valuesManager); panel.add(variablesManager);
		 */
	}

	private void initSolutionPanel() {
		solutionPanel = new SolutionPanel(root);
		solutionPanel.setPreferredSize(new Dimension(600, 200));
		add(solutionPanel);
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

				if (num == null) {
					JOptionPane.showMessageDialog(ConfiguratorPanel.this,
							"Invalid Number", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				clearProducts();
				ConfigurationOptions options = getCurrentOptions();
				options.setStartFromZero(true);
				configurator.solve(num, getCurrentConfiguration(), options);
			}
		});

		JButton getNextSolution = new JButton("Get Next");
		getNextSolution.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer num = Integer.parseInt(txtNumConf.getText());

				if (num == null) {
					JOptionPane.showMessageDialog(ConfiguratorPanel.this,
							"Invalid Number", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				ConfigurationOptions options = getCurrentOptions();
				options.setStartFromZero(false);
				configurator.solve(num, getCurrentConfiguration(), options);

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

		SpringUtilities.makeCompactGrid(buttonPanels, 2, 2, 4, 4, 4, 4);

		controlPanel.add(buttonPanels, BorderLayout.WEST);

		JPanel additionalPanel = new JPanel(new SpringLayout());
		additionalPanel.setBorder(BorderFactory
				.createTitledBorder("Additional Constraints"));

		additionalConstraints = new JList<String>(
				new DefaultListModel<String>());
		additionalConstraints.setPreferredSize(new Dimension(200, 100));

		JPanel constraintButtons = new JPanel(new SpringLayout());
		JButton btnAddConstraint = new JButton("Add Constraint");
		btnAddConstraint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String cons = JOptionPane.showInputDialog("New Constraint");
				((DefaultListModel<String>) additionalConstraints.getModel())
						.addElement(cons);
			}
		});

		JButton btnRemoveConstraint = new JButton("Remove Constraint");
		btnRemoveConstraint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int sel = additionalConstraints.getSelectedIndex();
				if (sel < 0)
					return;

				((DefaultListModel<String>) additionalConstraints.getModel())
						.remove(sel);
			}
		});

		constraintButtons.add(btnAddConstraint);
		constraintButtons.add(btnRemoveConstraint);
		SpringUtilities.makeCompactGrid(constraintButtons, 2, 1, 4, 4, 4, 4);

		additionalPanel.add(additionalConstraints);
		additionalPanel.add(constraintButtons);
		SpringUtilities.makeCompactGrid(additionalPanel, 1, 2, 4, 4, 4, 4);

		JPanel center = new JPanel();
		center.add(additionalPanel);

		controlPanel.add(center);
	}

	/*
	 * public void solve(int numSol, ConfigurationOptions options){
	 * Configuration config = getCurrentConfiguration(); config.debugPrint();
	 * 
	 * configurator.solve(config, options); int i = 0;
	 * 
	 * if( !solver.hasNextSolution() ){
	 * System.out.println("No solutions found !!"); return; } while(
	 * solver.hasNextSolution() && i < numSol){ Configuration sol =
	 * solver.getSolution(); solutionPanel.addSolution(sol); sol.debugPrint();
	 * i++; } solutionPanel.expand(); // }
	 */
	public ConfigurationOptions getCurrentOptions() {
		ConfigurationOptions op = new ConfigurationOptions();
		for (int i = 0; i < ((DefaultListModel<String>) additionalConstraints
				.getModel()).size(); i++)
			op.getAdditionalConstraints().add(
					((DefaultListModel<String>) additionalConstraints
							.getModel()).elementAt(i));
		return op;
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

	public void clearProducts() {
		solutionPanel.clearSolutions();
	}

	/*
	 * private DefaultMutableTreeNode makeNodeFor(Configuration solution){
	 * DefaultMutableTreeNode solNode = new DefaultMutableTreeNode();
	 * 
	 * for( String id : solution.getNotIgnored() ){ if( solution.stateOf(id) ==
	 * Configuration.BANNED ) continue;
	 * 
	 * VariabilityElement ve = productLine.getVariabilityElement(id);
	 * solNode.add(new DefaultMutableTreeNode(ve.getName())); } return solNode;
	 * }
	 */
	public void addSolution(Configuration solution) {
		configurator.addSolution(solution);
	}

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

	public void setStatus(String string) {
		lblStatus.setText(string);
	}

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
		solutionPanel.expand();

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

	public void performConfiguration() {
		DefaultConfigurationTaskListener listener = new DefaultConfigurationTaskListener(
				this);
		Configuration configuration = getCurrentConfiguration();
		configurator.performConfiguration(configuration, getCurrentOptions(),
				listener, productLine);
	}

	public void setValueToVariable(Variable variable, Integer value, int index) {
		ConfigurationNode node = findConfigurationNodeFor(variable.getName());

		node.getVariable().setValue(value);
		node.setStepEdited(index);
		// resizeColumns();
		this.repaint();
	}

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

	public void reduceDomain() {
		Configuration config = getCurrentConfiguration();
		// config.debugPrint();
		Map<String, List<Integer>> values = configurator.reduceDomain(config,
				getCurrentOptions());

		List<ConfigurationNode> allNodes = getAllConfigurationNodes();
		for (ConfigurationNode c : allNodes) {
			List<Integer> newValues = values.get(c.getName());
			if (newValues == null)
				continue;

			addDomainAnnotations(c, newValues, 0);
		}
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
		TreeMap<String, Integer> values = dto.getValues().getConfiguration();
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

	public ConfigurationDTO getConfigurationDTO() {
		return configurator.getConfigurationDTO(getCurrentConfiguration(),
				getCurrentOptions());
	}

	public List<Configuration> getSolutions() {
		return solutionPanel.getAllSolutions();
	}

	// public OperationMode getOperationMode() {
	// return operationMode;
	// }
	//
	// public void setOperationMode(OperationMode operationMode) {
	// this.operationMode = operationMode;
	// }
}
