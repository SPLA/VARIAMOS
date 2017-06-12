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
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersExpr;
import com.variamos.dynsup.model.OpersIOAttribute;
import com.variamos.dynsup.model.OpersLabeling;
import com.variamos.dynsup.model.OpersSubOperation;
import com.variamos.dynsup.model.OpersSubOperationExpType;
import com.variamos.gui.common.jelements.AbstractConfigurationPanel;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.model.AssociationDataModel;
import com.variamos.gui.perspeditor.model.AssociationRow;
import com.variamos.gui.perspeditor.model.AssociationTreeTable;
import com.variamos.gui.treetable.core.TreeTableModelAdapter;
import com.variamos.hlcl.BinaryDomain;
import com.variamos.hlcl.Domain;
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
	private int width = 980;
	private int height = 500;
	List<InstElement> operActions = null;
	private AssociationTreeTable table = null;
	private List<OpersSubOperationExpType> subOpersTypesColumns = null;
	private List<OpersLabeling> operLabels = null;
	private List<OpersSubOperation> subOpers = null;

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
				"OMOperation");

		for (InstElement operAction : operActions) {
			combo.addItem(operAction.getIdentifier());
		}
		combo.setSelectedItem(combo.getItemAt(0));
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
				table.setPreferredSize(new Dimension(width, height + 1000));
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
		OpersSubOperation operSubAction = null;
		List<String> subOperTypesColumnsNames = new ArrayList<String>();
		subOpersTypesColumns = new ArrayList<OpersSubOperationExpType>();
		List<String> subOperColumnsNames = new ArrayList<String>();
		subOpers = new ArrayList<OpersSubOperation>();
		List<String> operLabelNames = new ArrayList<String>();
		operLabels = new ArrayList<OpersLabeling>();
		for (InstElement rel : operAction.getTargetRelations()) {
			InstElement subOper = rel.getTargetRelations().get(0);

			operSubAction = (OpersSubOperation) subOper.getEdOperEle();
			// FIXME complete: include names and objects from suboper
			List<InstAttribute> atttypes = ((List<InstAttribute>) subOper
					.getInstAttributeValue("exptype"));
			// .getDynamicAttribute("exptype"));
			List<String> names = new ArrayList<String>();
			for (InstAttribute instatt : atttypes) {
				subOperTypesColumnsNames.add(subOper.getIdentifier()
						+ "-"
						+ ((InstElement) instatt.getValue())
								.getInstAttributeValue("suboperexptype")
								.toString());
				subOpersTypesColumns
						.add((OpersSubOperationExpType) ((InstElement) instatt
								.getValue()).getEdOperEle());
			}

			subOperColumnsNames.add(subOper.getIdentifier());
			subOpers.add(operSubAction);
			// operLabelNames.addAll(operSubAction.getOperLabelNames());
			// operLabels.addAll(operSubAction.getOperLabels());
			List<OpersLabeling> operLabs = new ArrayList<OpersLabeling>();
			List<String> operLabsNames = new ArrayList<String>();
			for (InstElement rel2 : subOper.getTargetRelations()) {
				InstElement instOperLab = rel2.getTargetRelations().get(0);
				OpersLabeling operLab = (OpersLabeling) instOperLab
						.getEdOperEle();
				operLabs.add(operLab);
				operLabsNames.add(subOper.getIdentifier() + "-"
						+ instOperLab.getIdentifier());

			}

			operLabelNames.addAll(operLabsNames);
			operLabels.addAll(operLabs);
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
			root = new AssociationRow("", subOpersTypesColumns.size(), false,
					domainOperColumns, null, null);
		if (dialog == 1)
			root = new AssociationRow("", operIO.size(), false, domainOperIO,
					null, null);
		if (dialog == 2)
			root = new AssociationRow("", operLabelNames.size(), false,
					domainOperLabels, null, null);

		for (InstElement el : refasModel.getVariabilityVertexCollection()) {
			InstElement et = el.getTransSupInstElement();
			if (et.getIdentifier().equals("OMSubOper")
					|| et.getIdentifier().equals("OMLabeling")
					|| et.getIdentifier().equals("OMOperation")
					|| et.getIdentifier().equals("OMOperGroup"))
				continue;
			AssociationRow node = null;

			if (dialog == 0)
				node = new AssociationRow(el.getIdentifier(),
						subOpersTypesColumns.size(), false, domainOperColumns,
						null, el);
			if (dialog == 1)
				node = new AssociationRow(el.getIdentifier(), operIO.size(),
						false, domainOperIO, null, el);
			if (dialog == 2)
				node = new AssociationRow(el.getIdentifier(),
						operLabelNames.size(), false, domainOperLabels, null,
						el);
			// node.setVariable(var);

			List<InstElement> opersParent = null;
			if (el.getTransSupportMetaElement().getTransInstSemanticElement() != null)
				opersParent = el.getTransSupportMetaElement()
						.getTransInstSemanticElement().getParentOpersConcept();
			// Add Semantic Expressions - normal concepts
			if (dialog == 0
					&& el.getEdOperEle() != null
					&& el.getEdOperEle().getAllSemanticExpressions(opersParent) != null)
				for (OpersExpr v : el.getEdOperEle().getSemanticExpressions()) {
					List<Integer> valuesOperColumns = new ArrayList<Integer>();
					for (OpersSubOperationExpType subOperExpType : subOpersTypesColumns)
						if (subOperExpType.hasSemanticExpression(v
								.getIdentifier()))
							valuesOperColumns.add(1);
						else
							valuesOperColumns.add(0);

					AssociationRow attNode = new AssociationRow(
							v.getIdentifier(), subOpersTypesColumns.size(),
							true, domainOperColumns, valuesOperColumns, v);

					node.getChildren().add(attNode);

				}
			// Add Semantic Expressions - operExprs of OT and PW
			if (dialog == 0 && el.getInstAttribute("opersExprs") != null)
				for (InstAttribute v : (List<InstAttribute>) el
						.getInstAttribute("opersExprs").getValue()) {

					// Relation Type
					AssociationRow attNode = new AssociationRow(
							v.getIdentifier(), subOpersTypesColumns.size(),
							false, domainOperColumns, null, v);

					node.getChildren().add(attNode);
					for (OpersExpr e : (List<OpersExpr>) v.getValue()) {
						List<Integer> valuesOperColumns = new ArrayList<Integer>();
						for (OpersSubOperationExpType operColumn : subOpersTypesColumns)
							if (operColumn.hasSemanticExpression(e
									.getIdentifier()))
								valuesOperColumns.add(1);
							else
								valuesOperColumns.add(0);

						// Expression row
						AssociationRow att2Node = new AssociationRow(
								e.getIdentifier(), subOpersTypesColumns.size(),
								true, domainOperColumns, valuesOperColumns, e);

						attNode.getChildren().add(att2Node);
					}
				}
			// Add attributes
			if (dialog == 1 && el.getEdOperEle() != null)
				for (ElemAttribute v : el.getEdOperEle()
						.getDeclaredSemanticAttributes().values()) {

					List<Integer> valuesVarColumns = new ArrayList<Integer>();
					for (OpersSubOperation operColumn : subOpers) {
						if (operColumn.hasInVariable(el.getIdentifier(),
								v.getName()))
							valuesVarColumns.add(1);
						else
							valuesVarColumns.add(0);
						if (operColumn.hasOutVariable(el.getIdentifier(),
								v.getName()))
							valuesVarColumns.add(1);
						else
							valuesVarColumns.add(0);
					}
					AssociationRow attNode = new AssociationRow(v.getName(),
							operIO.size(), true, domainOperIO,
							valuesVarColumns, el);
					node.getChildren().add(attNode);

				}

			// Add labels
			if (dialog == 2 && el.getEdOperEle() != null)
				for (ElemAttribute v : el.getEdOperEle()
						.getDeclaredSemanticAttributes().values()) {
					List<Integer> valuesVarColums = new ArrayList<Integer>();

					for (OpersLabeling operColumn : operLabels)
						if (operColumn.hasAttribute(el.getIdentifier(),
								v.getName()))
							valuesVarColums.add(1);
						else
							valuesVarColums.add(0);
					AssociationRow attNode = new AssociationRow(v.getName(),
							operLabelNames.size(), true, domainOperLabels,
							valuesVarColums, el);
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

	@Override
	public void setValueToVariable(Variable variable, Integer value,
			int column, Object source) {
		AssociationRow node = findConfigurationNodeFor(variable.getName());

		node.setValue(value, column);
		if (dialog == 0) {
			OpersSubOperationExpType subOper = subOpersTypesColumns
					.get(column - 1);
			if (value == 1)
				subOper.addSemanticExpression((OpersExpr) source);
			else
				subOper.removeSemanticExpression((OpersExpr) source);
		}

		if (dialog == 1) {
			OpersSubOperation subOper = subOpers.get((column - 1) / 2);
			if (value == 1)
				if (column % 2 != 0)
					subOper.addInAttribute(new OpersIOAttribute(
							((InstElement) source).getIdentifier(), variable
									.getName(), true));
				else
					subOper.addOutAttribute(new OpersIOAttribute(
							((InstElement) source).getIdentifier(), variable
									.getName(), true));
			else if (column % 2 != 0)
				subOper.removeInAttribute(new OpersIOAttribute(
						((InstElement) source).getIdentifier(), variable
								.getName(), true));
			else
				subOper.removeOutAttribute(new OpersIOAttribute(
						((InstElement) source).getIdentifier(), variable
								.getName(), true));
		}
		if (dialog == 2) {
			OpersLabeling operLabeling = operLabels.get(column - 1);
			if (value == 1)
				operLabeling.addAttribute(new OpersIOAttribute(
						((InstElement) source).getIdentifier(), variable
								.getName(), true));
			else
				operLabeling.removeAttribute(new OpersIOAttribute(
						((InstElement) source).getIdentifier(), variable
								.getName(), true));
		}

		// node.setStepEdited(index);
		// resizeColumns();
		this.repaint();
	}

	public void setValueToVariable(Variable variable, String value, int column,
			Object source) {
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
