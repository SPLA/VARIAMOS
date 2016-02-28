package com.variamos.gui.perspeditor.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumnModel;

import com.cfm.common.AbstractModel;
import com.cfm.productline.Variable;
import com.variamos.gui.common.jelements.AbstractConfigurationPanel;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.model.AssociationDataModel;
import com.variamos.gui.perspeditor.model.AssociationRow;
import com.variamos.gui.perspeditor.model.AssociationTreeTable;
import com.variamos.gui.treetable.core.TreeTableModelAdapter;
import com.variamos.hlcl.BinaryDomain;
import com.variamos.hlcl.Domain;
import com.variamos.perspsupport.expressionsupport.OperationLabeling;
import com.variamos.perspsupport.expressionsupport.OperationSubActionExpType;
import com.variamos.perspsupport.expressionsupport.SemanticOperationSubAction;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.perspmodel.ModelInstance;
import com.variamos.perspsupport.semanticinterface.IntSemanticExpression;
import com.variamos.solver.Configuration;
import com.variamos.solver.ConfigurationTask;

/**
 * A class to create the dialog to associate element's expressions to
 * operations. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-11-05
 */
public class ElementsOperationAssociationPanel extends
		AbstractConfigurationPanel implements PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1478873242908074197L;
	private JPanel generalPanel = null;
	private JPanel panel = null;
	private int dialog = 0;
	private int width = 880;
	private int height = 600;
	List<InstElement> operActions = null;
	private AssociationTreeTable table = null;

	static interface DialogButtonAction {
		public boolean onAction();
	}

	public ElementsOperationAssociationPanel(final VariamosGraphEditor editor,
			int dialog) {
		super();
		this.dialog = dialog;
		setMaximumSize(new Dimension(width, height));
		// setTitle("Simulation Control Dialog");
		// setVisible(true);

		generalPanel = new JPanel();
		generalPanel.setLayout(new BorderLayout());

		final JComboBox<String> combo = new JComboBox<String>();
		operActions = editor.getEditedModel().getVariabilityVertex(
				"OMMOperation");

		for (InstElement operAction : operActions) {
			combo.addItem(operAction.getEditableSemanticElement()
					.getIdentifier());
		}
		combo.setSelectedItem("SimulationOper");
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Operation"));
		topPanel.add(combo);

		generalPanel.add(topPanel, BorderLayout.NORTH);

		combo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				InstElement operAction = operActions.get(combo
						.getSelectedIndex());
				AssociationTreeTable tableN = createTable(
						editor.getEditedModel(), operAction);
				panel.removeAll();
				table = tableN;
				table.setPreferredSize(new Dimension(width, height + 400));
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setPreferredSize(new Dimension(width, height));
				panel.add(scrollPane);
				validate();
				repaint();
			}
		});

		table = createTable(editor.getEditedModel(), operActions.get(0));
		table.setPreferredSize(new Dimension(width, height + 400));
		panel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(width, height));
		panel.add(scrollPane);
		// panel.add(new JLabel("test"));

		generalPanel.add(panel, BorderLayout.CENTER);

		add(generalPanel);

		// pack();
		revalidate();
		repaint();
	}

	private AssociationTreeTable createTable(ModelInstance refasModel,
			InstElement operAction) {

		SemanticOperationSubAction operSubAction = null;
		List<String> subOperTypesColumnsNames = new ArrayList<String>();
		List<OperationSubActionExpType> subOperTypesColumns = new ArrayList<OperationSubActionExpType>();
		List<String> subOperColumnsNames = new ArrayList<String>();
		List<SemanticOperationSubAction> subOperColumns = new ArrayList<SemanticOperationSubAction>();
		List<String> operLabelNames = new ArrayList<String>();
		List<OperationLabeling> operLabels = new ArrayList<OperationLabeling>();
		for (InstElement rel : operAction.getTargetRelations()) {
			InstElement subOper = rel.getTargetRelations().get(0);

			operSubAction = (SemanticOperationSubAction) subOper
					.getEditableSemanticElement();
			subOperTypesColumnsNames.addAll(operSubAction
					.getOperationSubActionExpTypesNames());
			subOperTypesColumns.addAll(operSubAction
					.getOperationSubActionExpTypes());
			subOperColumnsNames.add(operSubAction.getIdentifier());
			subOperColumns.add(operSubAction);
			operLabelNames.addAll(operSubAction.getOperLabelNames());
			operLabels.addAll(operSubAction.getOperLabels());
		}

		List<Domain> domainOperColumns = new ArrayList<Domain>();
		for (String s : subOperTypesColumnsNames)
			domainOperColumns.add(BinaryDomain.INSTANCE);

		List<Domain> domainOperLabels = new ArrayList<Domain>();
		List<Boolean> valuesOperLabels = new ArrayList<Boolean>();
		for (String s : operLabelNames)
			domainOperLabels.add(BinaryDomain.INSTANCE);

		List<String> operIO = new ArrayList<String>();
		List<Domain> domainOperIO = new ArrayList<Domain>();
		List<Boolean> valuesOperIO = new ArrayList<Boolean>();
		for (String s : subOperColumnsNames) {
			domainOperIO.add(BinaryDomain.INSTANCE);
			domainOperIO.add(BinaryDomain.INSTANCE);
			operIO.add(s + "ModelValue/\nFreeValue");
			operIO.add(s + "UpdateModelValue");
		}

		AssociationRow root = null;
		AssociationDataModel dataModel = null;

		if (dialog == 0)
			root = new AssociationRow("", subOperTypesColumns.size(), false,
					domainOperColumns, null);
		if (dialog == 1)
			root = new AssociationRow("", operIO.size(), false, domainOperIO,
					null);
		if (dialog == 2)
			root = new AssociationRow("", operLabelNames.size(), false,
					domainOperLabels, null);

		for (InstElement el : refasModel.getVariabilityVertexCollection()) {
			AssociationRow node = null;
			if (dialog == 0)
				node = new AssociationRow(el.getIdentifier(),
						subOperTypesColumns.size(), false, domainOperColumns,
						null);
			if (dialog == 1)
				node = new AssociationRow(el.getIdentifier(), operIO.size(),
						false, domainOperIO, null);
			if (dialog == 2)
				node = new AssociationRow(el.getIdentifier(),
						operLabelNames.size(), false, domainOperLabels, null);
			// node.setVariable(var);

			// Add Attributes
			if (dialog == 0
					&& el.getEditableSemanticElement() != null
					&& el.getEditableSemanticElement()
							.getAllSemanticExpressions() != null)
				for (IntSemanticExpression v : el.getEditableSemanticElement()
						.getSemanticExpressions()) {
					List<Integer> valuesOperColumns = new ArrayList<Integer>();
					for (OperationSubActionExpType operColumn : subOperTypesColumns)
						if (operColumn.hasSemanticExpression(v.getIdentifier()))
							valuesOperColumns.add(1);
						else
							valuesOperColumns.add(0);

					AssociationRow attNode = new AssociationRow(
							v.getIdentifier(), subOperTypesColumns.size(),
							true, domainOperColumns, valuesOperColumns);

					node.getChildren().add(attNode);

				}

			if (dialog == 0
					&& el.getInstAttribute("operationsExpressions") != null)
				for (InstAttribute v : (List<InstAttribute>) el
						.getInstAttribute("operationsExpressions").getValue()) {
					List<Integer> valuesOperColumns = new ArrayList<Integer>();

					AssociationRow attNode = new AssociationRow(
							v.getIdentifier(), subOperTypesColumns.size(),
							false, domainOperColumns, null);

					node.getChildren().add(attNode);
					for (IntSemanticExpression e : (List<IntSemanticExpression>) v
							.getValue()) {
						for (OperationSubActionExpType operColumn : subOperTypesColumns)
							if (operColumn.hasSemanticExpression(e
									.getIdentifier()))
								valuesOperColumns.add(1);
							else
								valuesOperColumns.add(0);

						AssociationRow att2Node = new AssociationRow(
								e.getIdentifier(), subOperTypesColumns.size(),
								true, domainOperColumns, valuesOperColumns);

						attNode.getChildren().add(att2Node);
					}
				}
			if (dialog == 1 && el.getEditableSemanticElement() != null)
				for (String v : el.getEditableSemanticElement()
						.getDeclaredSemanticAttributes()) {

					List<Integer> valuesVarColumns = new ArrayList<Integer>();
					for (SemanticOperationSubAction operColumn : subOperColumns) {
						if (operColumn.hasInVariable(v))
							valuesVarColumns.add(1);
						else
							valuesVarColumns.add(0);
						if (operColumn.hasOutVariable(v))
							valuesVarColumns.add(1);
						else
							valuesVarColumns.add(0);
					}
					AssociationRow attNode = new AssociationRow(v,
							operIO.size(), true, domainOperIO, valuesVarColumns);
					node.getChildren().add(attNode);

				}

			if (dialog == 2 && el.getEditableSemanticElement() != null)
				for (String v : el.getEditableSemanticElement()
						.getDeclaredSemanticAttributes()) {
					List<Integer> valuesVarColums = new ArrayList<Integer>();

					for (OperationLabeling operColumn : operLabels)
						if (operColumn.hasAttribute(v))
							valuesVarColums.add(1);
						else
							valuesVarColums.add(0);
					AssociationRow attNode = new AssociationRow(v,
							operLabelNames.size(), true, domainOperLabels,
							valuesVarColums);
					node.getChildren().add(attNode);
				}

			root.getChildren().add(node);
		}

		AssociationTreeTable table;
		if (dialog == 0)
			dataModel = new AssociationDataModel(root, this,
					subOperTypesColumnsNames, domainOperColumns);
		if (dialog == 1)
			dataModel = new AssociationDataModel(root, this, operIO,
					domainOperIO);
		if (dialog == 2)
			dataModel = new AssociationDataModel(root, this, operLabelNames,
					domainOperLabels);
		table = new AssociationTreeTable(dataModel);

		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setMinWidth(100);
		for (int i = 1; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setMinWidth(60);
		}

		table.resizeColumns();
		table.expandRow(0);
		return table;
	}

	public void center() {
		// setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// if (evt.getPropertyName().equals("progress"))
		{
			revalidate();
			repaint();
		}

	}

	@Override
	public void configure(AbstractModel am) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSolution(Configuration solution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void taskCompleted(ConfigurationTask task, long timeMillis) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatus(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearProducts() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resizeColumns() {
		// TODO Auto-generated method stub

	}

	public void setValueToVariable(Variable variable, Integer value, int column) {
		AssociationRow node = findConfigurationNodeFor(variable.getName());

		node.setValue(value, column);
		// node.setStepEdited(index);
		// resizeColumns();
		this.repaint();
	}

	public void setValueToVariable(Variable variable, String value, int column) {
		AssociationRow node = findConfigurationNodeFor(variable.getName());

		node.setValue(value, column);
		// node.setStepEdited(index);
		// resizeColumns();
		this.repaint();
	}

	private AssociationRow findConfigurationNodeFor(String name) {
		for (int i = 0; i < table.getRowCount(); i++) {
			String varId = (String) table.getValueAt(i, 0);
			if (name.equals(varId))
				return getConfigurationNode(i);
		}
		return null;
	}

	public AssociationRow getConfigurationNode(int row) {
		TreeTableModelAdapter model = (TreeTableModelAdapter) table.getModel();
		return (AssociationRow) model.nodeForRow(row);
	}
}
