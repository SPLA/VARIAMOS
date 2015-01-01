package com.variamos.gui.refas.editor.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import com.cfm.productline.AbstractElement;
import com.cfm.productline.Constraint;
import com.cfm.productline.ProductLine;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.Variable;
import com.cfm.productline.solver.Configuration;
import com.cfm.productline.solver.ConfigurationOptions;
import com.cfm.productline.solver.ConfigurationTask;
import com.variamos.gui.common.jelements.AbstractConfigurationPanel;
import com.variamos.gui.maineditor.VariamosGraphEditor;
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
import com.variamos.refas.core.expressions.NumberNumericExpression;
import com.variamos.refas.core.refas.Refas;
import com.variamos.refas.core.simulationmodel.MetaExpressionSet;
import com.variamos.refas.core.simulationmodel.AbstractExpression;
import com.variamos.refas.core.types.ExpressionClassType;
import com.variamos.syntaxsupport.metamodel.EditableElement;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
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

	private MetaExpressionSet expressionSet;

	private AbstractExpression selectedExpression;

	private VariamosGraphEditor graphEditor;
	
	private EditableElement elm;

	public RefasExpressionPanel(VariamosGraphEditor graphEditor, EditableElement elm) {
		// initComponents();
		this.graphEditor = graphEditor;
		this.elm = elm;
	}


	private void initSolutionPanel(int expressionCount) {
		solutionPanel.setMaximumSize(new Dimension(600,  50 * expressionCount));

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
			MetaExpressionSet expressionSet, InstElement element) {
		this.expressionSet = expressionSet;
		Refas pl = (Refas) am;
		this.refas = pl;
		initialize(element);
	}
	
	public void initialize(InstElement element)
	{
		removeAll();
		setLayout(new BorderLayout());
		solutionPanel = new JPanel(new SpringLayout());
		List<AbstractExpression> expressions = expressionSet
				.getTransformations();
		for (AbstractExpression expression : expressions) {
			showExpression(expression, element, solutionPanel, 255);
		}

		initSolutionPanel(expressions.size());
		this.repaint();
		this.revalidate();

	}

	private void showExpression(AbstractExpression expression,
			InstElement element, JPanel parentPanel, int color) {
		final InstElement ele = element;
		final AbstractExpression exp = expression;		
		if (expression instanceof NumberNumericExpression) {
			parentPanel.add(new JTextField(""
					+ ((NumberNumericExpression) expression).getNumber()));
			return;
		}
		JPanel childPanel = new JPanel();
		childPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		childPanel.setBackground(new Color(color, color, color));
		childPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				selectedExpression = exp;
				//System.out.println(exp.toString()+" selected");
				initialize(ele);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		JComboBox<String> leftSide = createSidesCombo();

		JComboBox<String> rightSide = createSidesCombo();
		if (selectedExpression == expression) {
			childPanel.add(leftSide);
		}
		if (expression.getLeftSubExpression() != null) {
			leftSide.setSelectedItem("Subexp");
			showExpression(expression.getLeftSubExpression(), element,
					childPanel, color - 20);
		}

		if (expression.getLeftComparativeExpression() != null) {
			leftSide.setSelectedItem("Number");
			showComparativeExpression(
					expression.getLeftComparativeExpression(), childPanel);
		}
		if (expression.getLeftNumericExpression() != null) {
			leftSide.setSelectedItem("Number");
			showComparativeExpression(expression.getLeftNumericExpression(),
					childPanel);
		}
		if (expression.getLeft() != null) {
			{
				leftSide.setSelectedItem("Identif");
				childPanel.add(createIdentifiersCombo(
						element,
						expression.getLeft().getIdentifier() + "_"
								+ expression.getLeftAttributeName()));
			}
		}
		childPanel.add(createOperatorsCombo(expression.getOperation()));

		if (selectedExpression == expression) {
			childPanel.add(rightSide);
		}
		if (expression.getRightSubExpression() != null){
			rightSide.setSelectedItem("SubExp");
			showExpression(expression.getRightSubExpression(), element,
					childPanel, color - 20);
		}
		if (expression.getRightComparativeExpression() != null){
			rightSide.setSelectedItem("Number");
			showComparativeExpression(
					expression.getRightComparativeExpression(), childPanel);
		}
		if (expression.getRightNumericExpression() != null){
			rightSide.setSelectedItem("Number");
			showComparativeExpression(expression.getRightNumericExpression(),
					childPanel);
		}
		if (expression.getRight() != null) {
			rightSide.setSelectedItem("Identif");
			childPanel.add(createIdentifiersCombo(
					element,
					expression.getRight().getIdentifier() + "_"
							+ expression.getRightAttributeName()));
		}
		if (color == 255)
		{
			JLabel lab = new JLabel("");
			lab.setPreferredSize(new Dimension(500,10));
			lab.setMaximumSize(new Dimension(500,10));
			childPanel.add(lab);
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

	private JComboBox<String> createIdentifiersCombo(InstElement element,
			String selectedElement) {
		JComboBox<String> combo = new JComboBox<String>();
		if (element instanceof InstConcept)
			for (String attributeName : element.getInstAttributes().keySet())
				combo.addItem(element.getIdentifier() + "_" + attributeName);
		if (element instanceof InstPairwiseRelation) {
			for (String attributeName : ((InstPairwiseRelation) element)
					.getSourceRelations().get(0).getInstAttributes().keySet())
				combo.addItem(((InstPairwiseRelation) element).getSourceRelations().get(0)
						.getIdentifier() + "_" + attributeName);
			for (String attributeName : ((InstPairwiseRelation) element)
					.getTargetRelations().get(0).getInstAttributes().keySet())
				combo.addItem(((InstPairwiseRelation) element).getTargetRelations().get(0)
						.getIdentifier() + "_" + attributeName);
			for (String attributeName : element.getInstAttributes().keySet())
				combo.addItem(element.getIdentifier() + "_" + attributeName);
		}

		if (element instanceof InstOverTwoRelation) {
			if (((InstOverTwoRelation) element).getTargetRelations().size() > 0)
				for (String attributeName : ((InstPairwiseRelation)((InstOverTwoRelation) element)
						.getTargetRelations().get(0)).getTargetRelations().get(0)
						.getInstAttributes().keySet())
					combo.addItem(((InstPairwiseRelation)((InstOverTwoRelation) element)
							.getTargetRelations().get(0)).getTargetRelations().get(0)
							.getIdentifier()
							+ "_" + attributeName);
			for (InstElement sourceRelation : ((InstOverTwoRelation) element)
					.getSourceRelations())
				for (String attributeName : ((InstPairwiseRelation)sourceRelation).getSourceRelations().get(0)
						.getInstAttributes().keySet())
					combo.addItem(((InstPairwiseRelation)sourceRelation).getSourceRelations().get(0)
							.getIdentifier() + "_" + attributeName);
			for (String attributeName : element.getInstAttributes().keySet())
				combo.addItem(element.getIdentifier() + "_" + attributeName);
		}

		combo.setSelectedItem(selectedElement);
		return combo;
	}

	private JComboBox<String> createSidesCombo() {
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("SubExpr");
		combo.addItem("Number");
		combo.addItem("Identif");
		return combo;
	}

	private JComboBox<String> createOperatorsCombo(String selectedOperator) {
		JComboBox<String> combo = new JComboBox<String>();
		for (ExpressionClassType operatorType : ExpressionClassType.values()) {
			Class<AbstractExpression> expressionClass = null;
			try {
				expressionClass = (Class<AbstractExpression>) Class
						.forName("com.variamos.refas.core.expressions."
								+ operatorType.name());
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
				combo.addItem((String) f.get(null));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		combo.setSelectedItem(selectedOperator);
		return combo;
	}
}
