package com.variamos.gui.refas.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
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
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import com.cfm.common.AbstractModel;
import com.cfm.hlcl.BinaryDomain;
import com.cfm.hlcl.BooleanExpression;
import com.cfm.hlcl.Expression;
import com.cfm.hlcl.HlclProgram;
import com.cfm.hlcl.NumericExpression;
import com.cfm.hlcl.NumericIdentifier;
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
import com.variamos.gui.pl.editor.SpringUtilities;
import com.variamos.gui.treetable.core.TreeTableModelAdapter;
import com.variamos.pl.configurator.Choice;
import com.variamos.pl.configurator.Configurator;
import com.variamos.pl.configurator.DomainAnnotation;
import com.variamos.pl.configurator.io.ConfigurationDTO;
import com.variamos.refas.core.simulationmodel.AbstractConstraintGroup;
import com.variamos.refas.core.simulationmodel.AbstractTransformation;
import com.variamos.refas.core.transformations.NumberNumericTransformation;
import com.variamos.refas.core.types.ExpressionClassType;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstEdge;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;
import com.variamos.syntaxsupport.refas.Refas;
import com.variamos.syntaxsupport.type.IntegerType;

/**
 * A class to support the properties panel to display and edit (future) the
 * expression associated to concepts and relations. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-21
 */
@SuppressWarnings("serial")
public class RefasExpressionPanel extends JPanel {
	private Refas refas;

	private JPanel solutionPanel;

	private AbstractConstraintGroup expressionSet;

	public RefasExpressionPanel() {
		// initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

	}

	private void initSolutionPanel(int expressionCount) {
		solutionPanel.setPreferredSize(new Dimension(600, 200));

		solutionPanel
				.setPreferredSize(new Dimension(600, 50 * expressionCount));
		add(new JScrollPane(solutionPanel));
		this.setAutoscrolls(true);
		SpringUtilities.makeCompactGrid(solutionPanel, expressionCount, 1, 4,
				4, 4, 4);

	}

	public Refas getRefas() {
		return this.refas;
	}

	public void configure(AbstractModel am,
			AbstractConstraintGroup expressionSet, InstElement element) {
		removeAll();
		solutionPanel = new JPanel(new SpringLayout());
		this.expressionSet = expressionSet;
		Refas pl = (Refas) am;
		initComponents();
		this.refas = pl;
		List<AbstractTransformation> expressions = expressionSet
				.getTransformations();
		for (AbstractTransformation expression : expressions) {
			showExpression(expression, element, solutionPanel, 255);

		}

		initSolutionPanel(expressions.size());
	}

	private void showExpression(AbstractTransformation expression,
			InstElement element, JPanel parentPanel, int color) {
		if (expression instanceof NumberNumericTransformation) {
			parentPanel.add(new JTextField(""
					+ ((NumberNumericTransformation) expression).getNumber()));
			return;
		}
		JPanel childPanel = new JPanel();
		childPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		childPanel.setBackground(new Color(color, color, color));

		if (expression.getLeftSubExpression() != null)
			showExpression(expression.getLeftSubExpression(), element,
					childPanel, color - 20);
		if (expression.getLeftComparativeExpression() != null)
			showComparativeExpression(
					expression.getLeftComparativeExpression(), childPanel);
		if (expression.getLeftNumericExpression() != null)
			showComparativeExpression(expression.getLeftNumericExpression(),
					childPanel);
		if (expression.getLeft() != null) {
			{
				childPanel.add(createCombo(
						element,
						expression.getLeft().getIdentifier() + "_"
								+ expression.getLeftAttributeName()));
			}
		}
		childPanel.add(createOperators(expression.getOperation()));

		if (expression.getRightSubExpression() != null)
			showExpression(expression.getRightSubExpression(), element,
					childPanel, color - 20);
		if (expression.getRightComparativeExpression() != null)
			showComparativeExpression(
					expression.getRightComparativeExpression(), childPanel);
		if (expression.getRightNumericExpression() != null)
			showComparativeExpression(expression.getRightNumericExpression(),
					childPanel);

		if (expression.getRight() != null) {
			childPanel
					.add(createCombo(element,
							expression.getRight().getIdentifier() + "_"
									+ expression.getRightAttributeName()));
		}

		parentPanel.add(childPanel);
	}

	private void showComparativeExpression(Expression expression,
			JPanel parentPanel) {
		if (expression instanceof NumericIdentifier)
			parentPanel.add(new JTextField(""
					+ ((NumericIdentifier) expression).getValue()));
		else
			parentPanel.add(new JTextField(expression.toString()));

		return;
	}

	private JComboBox createCombo(InstElement element, String selectedElement) {
		JComboBox<String> combo = new JComboBox<String>();
		if (element instanceof InstConcept)
			for (String attributeName : element.getInstAttributes().keySet())
				combo.addItem(element.getIdentifier() + "_" + attributeName);
		if (element instanceof InstEdge) {
			for (String attributeName : ((InstEdge) element)
					.getSourceRelation().getInstAttributes().keySet())
				combo.addItem(((InstEdge) element).getSourceRelation()
						.getIdentifier() + "_" + attributeName);
			for (String attributeName : ((InstEdge) element)
					.getTargetRelation().getInstAttributes().keySet())
				combo.addItem(((InstEdge) element).getTargetRelation()
						.getIdentifier() + "_" + attributeName);
			for (String attributeName : element.getInstAttributes().keySet())
				combo.addItem(element.getIdentifier() + "_" + attributeName);
		}

		if (element instanceof InstGroupDependency) {
			if (((InstGroupDependency) element).getTargetRelations().size() > 0)
				for (String attributeName : ((InstGroupDependency) element)
						.getTargetRelations().get(0).getTargetRelation()
						.getInstAttributes().keySet())
					combo.addItem(((InstGroupDependency) element)
							.getTargetRelations().get(0).getTargetRelation()
							.getIdentifier()
							+ "_" + attributeName);
			for (InstEdge sourceRelation : ((InstGroupDependency) element)
					.getSourceRelations())
				for (String attributeName : sourceRelation.getSourceRelation()
						.getInstAttributes().keySet())
					combo.addItem(sourceRelation.getSourceRelation()
							.getIdentifier() + "_" + attributeName);
			for (String attributeName : element.getInstAttributes().keySet())
				combo.addItem(element.getIdentifier() + "_" + attributeName);
		}

		combo.setSelectedItem(selectedElement);
		return combo;
	}
	
	private JComboBox createOperators (String selectedOperator) 
	{
		JComboBox<String> combo = new JComboBox<String>();
		for (ExpressionClassType operatorType :  ExpressionClassType.values())
		{
			Class<AbstractTransformation> expressionClass = null;
			try {
				expressionClass = (Class<AbstractTransformation>) Class.forName("com.variamos.refas.core.transformations."+operatorType.name());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Field f = null;
			try {
				f = expressionClass.getDeclaredField("TRANSFORMATION");
			} catch (NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				combo.addItem((String)f.get(null));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		combo.setSelectedItem(selectedOperator);
		return combo;
	}
}
