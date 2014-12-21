package com.variamos.gui.refas.editor;

import java.awt.BorderLayout;
import java.awt.Color;
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

		solutionPanel.setPreferredSize(new Dimension(600, 50*expressionCount));
		add(new JScrollPane(solutionPanel));
		this.setAutoscrolls(true);
		SpringUtilities.makeCompactGrid(solutionPanel, expressionCount, 1, 4, 4, 4, 4);

		}
	public Refas getRefas() {
		return this.refas;
	}


	public void configure(AbstractModel am, AbstractConstraintGroup expressionSet) {
		removeAll();		
		solutionPanel = new JPanel(new SpringLayout());
		this.expressionSet = expressionSet;
		Refas pl = (Refas) am;
		initComponents();
		this.refas = pl;
		List<AbstractTransformation> expressions = expressionSet.getTransformations();
		for (AbstractTransformation expression : expressions)
		{
			showExpression(expression, solutionPanel, 255);
		
		}

		initSolutionPanel(expressions.size());
	}
	


	private void showExpression (AbstractTransformation expression, JPanel parentPanel, int color)
	{
		if (expression instanceof NumberNumericTransformation)
		{
			parentPanel.add(new JTextField(""+((NumberNumericTransformation)expression).getNumber()));
			return;
		}
		JPanel childPanel = new JPanel();
		childPanel.setBorder(new EmptyBorder(1,1,1,1));
		childPanel.setBackground(new Color(color, color, color));

		
		if (expression.getLeftSubExpression() != null)
			showExpression(expression.getLeftSubExpression(), childPanel, color-20);
		if (expression.getLeftComparativeExpression() != null)
			showComparativeExpression(expression.getLeftComparativeExpression(), childPanel);
		if (expression.getLeftNumericExpression() != null)
			showComparativeExpression(expression.getLeftNumericExpression(), childPanel);
		if (expression.getLeft()!= null){
			{
				childPanel.add(new JTextField(expression.getLeft().getIdentifier()+"_"+expression.getLeftAttributeName()));
			}
		}
		childPanel.add(new JTextField(expression.getOperation()));
		
		if (expression.getRightSubExpression() != null)
			showExpression(expression.getRightSubExpression(), childPanel, color-20);
		if (expression.getRightComparativeExpression() != null)
			showComparativeExpression(expression.getRightComparativeExpression(), childPanel);
		if (expression.getRightNumericExpression() != null)
			showComparativeExpression(expression.getRightNumericExpression(), childPanel);

		if (expression.getRight()!= null)
		{
			childPanel.add(new JTextField(expression.getRight().getIdentifier()+"_"+expression.getRightAttributeName()));
		}

		parentPanel.add(childPanel);
	}
	
	private void showComparativeExpression (Expression expression, JPanel parentPanel)
	{
		if (expression instanceof NumericIdentifier)
			parentPanel.add(new JTextField(""+((NumericIdentifier)expression).getValue()));
		else
		parentPanel.add(new JTextField(expression.toString()));
		
		return;
	}	
}
