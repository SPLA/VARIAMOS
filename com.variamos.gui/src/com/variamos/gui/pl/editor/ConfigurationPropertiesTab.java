package com.variamos.gui.pl.editor;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

/**
 * 
 * @author Jose
 *
 */
@SuppressWarnings("serial")
public class ConfigurationPropertiesTab extends JPanel {

	public ConfigurationPropertiesTab() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		initComponents();
	}

	public JScrollPane getScrollPane() {
		return new JScrollPane(this);
	}

	private void initComponents() {
		// In initialization code:
		// Create the radio buttons.
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;

		JPanel orderCriteriaPane = new JPanel();
		orderCriteriaPane.setLayout(new GridLayout(orderCriteriaPane
				.getComponentCount(), 1));

		JPanel variablesToConfigurePane = new JPanel();
		variablesToConfigurePane.setLayout(new GridLayout(
				variablesToConfigurePane.getComponentCount(), 1));

		initOrderCriteriaPane(orderCriteriaPane);
		initVariablesToConfiguraPane(variablesToConfigurePane);

		// all elements are added to the options panel
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 9;
		container.add(orderCriteriaPane, constraints);

		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		container.add(variablesToConfigurePane, constraints);

		add(container);

	}

	private void initOrderCriteriaPane(JPanel panel) {
		JLabel lblOrderBy = new JLabel("Order by");

		JRadioButton smallestDomain = new JRadioButton(
				"Variables with the smallest domain first");
		smallestDomain.setActionCommand("smallestDomain");
		smallestDomain.setSelected(true);

		JRadioButton mostConstrained = new JRadioButton(
				"The most constrained variables first");
		mostConstrained.setActionCommand("mostConstrained");

		JRadioButton mostProducts = new JRadioButton(
				"Variables appearing in most products first");
		mostProducts.setActionCommand("mostProducts");

		JRadioButton automaticCompletion = new JRadioButton(
				"Automatic completion when there is no choice");
		automaticCompletion.setActionCommand("automaticCompletion");

		JRadioButton requiredByLatest = new JRadioButton(
				"Variables required by the latest configured variable first");
		requiredByLatest.setActionCommand("requiredByLatest");

		JRadioButton splitProblemSpace = new JRadioButton(
				"Variables that split the problem space in two first");
		splitProblemSpace.setActionCommand("splitProblemSpace");

		JRadioButton alphabetically = new JRadioButton("Alphabetically");
		splitProblemSpace.setActionCommand("splitProblemSpace");

		JRadioButton category = new JRadioButton("Category");
		category.setActionCommand("category");

		// Group the radio buttons.
		ButtonGroup orderByGroup = new ButtonGroup();
		orderByGroup.add(smallestDomain);
		orderByGroup.add(mostConstrained);
		orderByGroup.add(mostProducts);
		orderByGroup.add(automaticCompletion);
		orderByGroup.add(requiredByLatest);
		orderByGroup.add(splitProblemSpace);
		orderByGroup.add(alphabetically);
		orderByGroup.add(category);

		panel.add(lblOrderBy);
		panel.add(smallestDomain);
		panel.add(mostConstrained);
		panel.add(mostProducts);
		panel.add(automaticCompletion);
		panel.add(requiredByLatest);
		panel.add(splitProblemSpace);
		panel.add(alphabetically);
		panel.add(category);

	}

	private void initVariablesToConfiguraPane(JPanel panel) {
		JLabel lblVariables = new JLabel("Which variables");

		JRadioButton allVariables = new JRadioButton("All");
		allVariables.setActionCommand("allVariables");
		allVariables.setSelected(true);

		JRadioButton modelSelected = new JRadioButton("Selected in the model");
		modelSelected.setActionCommand("modelSelected");

		ButtonGroup variablesGroup = new ButtonGroup();
		variablesGroup.add(allVariables);
		variablesGroup.add(modelSelected);

		panel.add(lblVariables);
		panel.add(allVariables);
		panel.add(modelSelected);

	}

}
