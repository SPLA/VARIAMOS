package com.variamos.gui.perpseditor.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Field;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.cfm.common.AbstractModel;
import com.variamos.gui.perspeditor.SpringUtilities;
import com.variamos.hlcl.Expression;
import com.variamos.hlcl.NumericIdentifier;
import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstOverTwoRelation;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.perspmodel.RefasModel;
import com.variamos.semantic.expressions.AbstractExpression;
import com.variamos.semantic.expressions.NumberNumericExpression;
import com.variamos.semantic.expressionsupport.ElementExpressionSet;
import com.variamos.semantic.types.ExpressionClassType;

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
	private RefasModel refas;

	private JPanel solutionPanel;

	private ElementExpressionSet expressionSet;

	private AbstractExpression selectedExpression;


	public RefasExpressionPanel() {

	}


	private void initSolutionPanel(int expressionCount) {
		solutionPanel.setMaximumSize(new Dimension(600,  55 * expressionCount));

		solutionPanel
				.setPreferredSize(new Dimension(600, 55 * expressionCount));
		add(new JScrollPane(solutionPanel));
		this.setAutoscrolls(true);
		SpringUtilities.makeCompactGrid(solutionPanel, expressionCount, 1, 4,
				4, 4, 4);

	}

	public RefasModel getRefas() {
		return this.refas;
	}

	public void configure(AbstractModel am,
			ElementExpressionSet expressionSet, InstElement element) {
		this.expressionSet = expressionSet;
		this.refas = (RefasModel) am;
		initialize(element);
	}
	
	public void initialize(InstElement element)
	{
		removeAll();
		setLayout(new BorderLayout());
		solutionPanel = new JPanel(new SpringLayout());
		List<AbstractExpression> expressions = expressionSet
				.getElementExpressions();
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

	@SuppressWarnings("unchecked")
	private JComboBox<String> createOperatorsCombo(String selectedOperator) {
		JComboBox<String> combo = new JComboBox<String>();
		for (ExpressionClassType operatorType : ExpressionClassType.values()) {
			Class<AbstractExpression> expressionClass = null;
			try {
				expressionClass = (Class<AbstractExpression>) Class
						.forName("com.variamos.semantic.expressions."
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


	public void setRefas(RefasModel refas) {
		this.refas = refas;
	}
}
