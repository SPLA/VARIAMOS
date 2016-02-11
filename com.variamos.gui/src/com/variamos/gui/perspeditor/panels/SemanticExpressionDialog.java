package com.variamos.gui.perspeditor.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.SpringUtilities;
import com.variamos.perspsupport.expressionsupport.SemanticExpression;
import com.variamos.perspsupport.expressionsupport.SemanticExpressionType;
import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.perspmodel.RefasModel;
import com.variamos.perspsupport.semanticinterface.IntSemanticExpression;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.types.ExpressionVertexType;

/**
 * A class to draw the semantic expression editor. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-03-10
 */
@SuppressWarnings("serial")
public class SemanticExpressionDialog extends JDialog {
	private List<IntSemanticExpression> semanticExpressions;
	private SemanticExpressionButtonAction onAccept, onCancel;
	private SemanticExpression selectedExpression;
	private JPanel solutionPanel;
	private RefasModel refasModel;
	private boolean displayConceptName = false;
	private boolean displayVariableName = false;
	private int width = 950;
	private int height = 300;
	boolean initializing = false;

	static interface SemanticExpressionButtonAction {
		public boolean onAction();
	}

	public SemanticExpressionDialog(VariamosGraphEditor editor,
			InstElement instElement,
			List<IntSemanticExpression> semanticExpressions) {
		super(editor.getFrame(), "Semantic Expressions Editor");
		refasModel = (RefasModel) editor.getEditedModel();
		this.semanticExpressions = semanticExpressions;
		setPreferredSize(new Dimension(width, height));
		this.initialize(instElement, semanticExpressions);
	}

	public void initialize(final InstElement element,
			final List<IntSemanticExpression> semanticExpressions) {
		if (initializing)
			return;
		initializing = true;
		if (this.getWidth() != 0)
			width = this.getWidth();
		if (this.getHeight() != 0)
			height = this.getHeight();
		this.setPreferredSize(new Dimension(width, height));
		this.getContentPane().removeAll();
		// removeAll();
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());

		if (semanticExpressions != null)
			this.semanticExpressions = semanticExpressions;
		final List<IntSemanticExpression> finalSemanticExpressions = this.semanticExpressions;
		for (final IntSemanticExpression semanticExpression : this.semanticExpressions) {

			if (semanticExpressions != null)
				selectedExpression = (SemanticExpression) semanticExpression;

			solutionPanel = new JPanel();
			solutionPanel.setAutoscrolls(true);
			solutionPanel.setMaximumSize(new Dimension(900, 200));
			JTextField iden = new JTextField();
			iden.setSize(100, 40);
			iden.setText(semanticExpression.getIdentifier());
			iden.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
				}

				@Override
				public void focusLost(FocusEvent event) {
					String item = (String) ((JTextField) event.getSource())
							.getText();
					if (item != null) {
						semanticExpression.setIdentifier(item);
					}
				}
			});
			solutionPanel.add(iden);
			((SemanticExpression) semanticExpression)
					.loadVolatileElements(refasModel.getVariabilityVertex());
			showExpression((SemanticExpression) semanticExpression, element,
					solutionPanel, SemanticExpressionType.BOOLEXP, 255);

			solutionPanel.addPropertyChangeListener("value",
					new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							revalidate();
							doLayout();
							pack();
						}
					});
			panel.add(new JScrollPane(solutionPanel));
		}
		JPanel options = new JPanel();
		JCheckBox conceptNamesCheck = new JCheckBox(
				"Display Concept Names (not identifiers)");
		if (displayConceptName)
			conceptNamesCheck.setSelected(true);
		options.add(conceptNamesCheck);
		conceptNamesCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				displayConceptName = !displayConceptName;
				new Thread() {
					public void run() {
						initialize(element, null);
					}
				}.start();
				revalidate();
				repaint();
			}
		});
		JCheckBox varNamesCheck = new JCheckBox(
				"Display Variable Names (not identifiers)");
		if (displayVariableName)
			varNamesCheck.setSelected(true);
		options.add(varNamesCheck);
		varNamesCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				displayVariableName = !displayVariableName;

				new Thread() {
					public void run() {
						initialize(element, null);
					}
				}.start();
				revalidate();
				repaint();
			}
		});
		JButton addButton = new JButton("Add new Semantic Expression");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				finalSemanticExpressions.add(new SemanticExpression(element));

				new Thread() {
					public void run() {
						initialize(element, null);
					}
				}.start();
				revalidate();
				repaint();
			}
		});
		options.add(addButton);
		panel.add(options);

		SpringUtilities.makeCompactGrid(panel,
				this.semanticExpressions.size() + 1, 1, 4, 4, 4, 4);

		add(panel, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new SpringLayout());

		final JButton btnAccept = new JButton();
		btnAccept.setText("Accept");
		btnAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (onAccept != null)
					if (onAccept.onAction())
						dispose();
			}
		});

		buttonsPanel.add(btnAccept);

		final JButton btnCancel = new JButton();
		btnCancel.setText("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (onCancel == null) {
					dispose();
					return;
				}
				if (onCancel.onAction())
					dispose();
			}
		});

		buttonsPanel.add(btnCancel);

		SpringUtilities.makeCompactGrid(buttonsPanel, 1, 2, 4, 4, 4, 4);

		add(buttonsPanel, BorderLayout.SOUTH);

		getRootPane().setDefaultButton(btnAccept);
		getRootPane().registerKeyboardAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnCancel.doClick();
			}

		}, KeyStroke.getKeyStroke("ESCAPE"), JComponent.WHEN_IN_FOCUSED_WINDOW);
		pack();
		revalidate();
		repaint();

		initializing = false;
	}

	private void showExpression(final SemanticExpression semanticExpression,
			final InstElement element, JPanel parentPanel,
			int topExpressionType, int color) {
		showExpression(semanticExpression, element, null, false, parentPanel,
				topExpressionType, color);
	}

	private void showExpression(final SemanticExpression semanticExpression,
			final InstElement element, final InstElement recursiveElement,
			boolean fixedType, JPanel parentPanel, int topExpressionType,
			int color) {
		final InstElement ele = element;
		final SemanticExpression exp = semanticExpression;

		JPanel basePanel = new JPanel();
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		basePanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		basePanel.setMaximumSize(new Dimension(1000, 300));
		basePanel.setBackground(new Color(color, color, color));
		basePanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedExpression = exp;
				new Thread() {
					public void run() {
						initialize(ele, null);
					}
				}.start();
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		JComboBox<String> leftSide = createSidesCombo(semanticExpression,
				element, true, recursiveElement != null ? true : false,
				fixedType);
		JComboBox<String> rightSide = createSidesCombo(semanticExpression,
				element, false, recursiveElement != null ? true : false,
				fixedType);
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(color, color, color));
		leftPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedExpression = exp;
				new Thread() {
					public void run() {
						initialize(ele, null);
					}
				}.start();
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		if (selectedExpression == semanticExpression && !fixedType) {
			leftPanel.setBorder(raisedbevel);
			basePanel.add(leftSide);
		}
		if (semanticExpression != null
				&& semanticExpression.getLeftExpressionType() != null)
			switch (semanticExpression.getLeftExpressionType()) {
			case LEFTITERCONFIXEDVARIABLE:
				leftSide.setSelectedItem("An Element Fixed");
				break;
			case LEFTSUBEXPRESSION:
				leftSide.setSelectedItem("SubExpression");
				break;
			case LEFTCONCEPTVARIABLE:
				leftSide.setSelectedItem("This Concept Variable");
				break;
			case LEFTNUMERICVALUE:
				leftSide.setSelectedItem("Number");
				break;
			case LEFTSTRINGVALUE:
				leftSide.setSelectedItem("String");
				break;

			case LEFTITERCONCEPTVARIABLE:
				leftSide.setSelectedItem("A Concept Type Variable");
				break;
			case LEFTITERINCCONVARIABLE:
				leftSide.setSelectedItem("Source Variables - Iterative (Concept)");
				break;
			case LEFTITEROUTCONVARIABLE:
				leftSide.setSelectedItem("Target Variables - Iterative (Concept)");
				break;
			case LEFTITERINCRELVARIABLE:
				leftSide.setSelectedItem("Source Variables - Iterative (Relation)");
				break;
			case LEFTITEROUTRELVARIABLE:
				leftSide.setSelectedItem("Target Variables - Iterative (Relation)");
				break;
			case LEFTITERANYCONVARIABLE:
				leftSide.setSelectedItem("Source/Target Variables (Concept)");
				break;
			case LEFTITERANYRELVARIABLE:
				leftSide.setSelectedItem("Source/Target Variables (Relation)");
				break;
			case LEFTUNIQUEINCCONVARIABLE:
				leftSide.setSelectedItem("Source Variable - Unique Rel. (Concept)");
				break;
			case LEFTUNIQUEOUTCONVARIABLE:
				leftSide.setSelectedItem("Target Variable - Unique Rel. (Concept)");
				break;
			case LEFTUNIQUEINCRELVARIABLE:
				leftSide.setSelectedItem("Source Variable - Unique Rel. (Relation)");
				break;
			case LEFTUNIQUEOUTRELVARIABLE:
				leftSide.setSelectedItem("Target Variable - Unique Rel. (Relation)");
				break;
			default:
			}

		if (semanticExpression != null
				&& semanticExpression.getRightExpressionType() != null)
			switch (semanticExpression.getRightExpressionType()) {
			case RIGHTSUBEXPRESSION:
				rightSide.setSelectedItem("SubExpression");
				break;
			case RIGHTCONCEPTVARIABLE:
				rightSide.setSelectedItem("This Concept Variable");
				break;
			case RIGHTNUMERICVALUE:
				rightSide.setSelectedItem("Number");
				break;
			case RIGHTSTRINGVALUE:
				rightSide.setSelectedItem("String");
				break;
			case RIGHTUNIQUEINCCONVARIABLE:
				rightSide
						.setSelectedItem("Source Variable - Unique Rel. (Concept)");
				break;
			case RIGHTUNIQUEOUTCONVARIABLE:
				rightSide
						.setSelectedItem("Target Variable - Unique Rel. (Concept)");
				break;
			case RIGHTUNIQUEINCRELVARIABLE:
				rightSide
						.setSelectedItem("Source Variable - Unique Rel. (Relation)");
				break;
			case RIGHTUNIQUEOUTRELVARIABLE:
				rightSide
						.setSelectedItem("Target Variable - Unique Rel. (Relation)");
				break;
			default:
			}
		if (leftSide.getSelectedItem().equals("SubExpression")) {
			if (semanticExpression.getSemanticExpressionType() != null) {
				if (semanticExpression.getLeftSemanticExpression() == null)
					semanticExpression.setLeftSemanticExpression(
							ExpressionVertexType.LEFTSUBEXPRESSION, null, "id");
				showExpression(semanticExpression.getLeftSemanticExpression(),
						element, leftPanel,
						semanticExpression.getLeftValidExpressions(),
						color > 20 ? color - 20 : color > 5 ? color - 5 : color);
				semanticExpression
						.setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
			}
		}
		if (leftSide.getSelectedItem().equals("Number")) {
			if (semanticExpression.getLeftExpressionType() != null)
				if (semanticExpression.getLeftExpressionType().equals(
						ExpressionVertexType.LEFTNUMERICVALUE)) {
					basePanel.add(createTextField(semanticExpression, element,
							ExpressionVertexType.LEFTNUMERICVALUE));
				}
		}
		if (leftSide.getSelectedItem().equals("String")) {
			if (semanticExpression.getLeftExpressionType() != null)
				if (semanticExpression.getLeftExpressionType().equals(
						ExpressionVertexType.LEFTSTRINGVALUE)) {
					basePanel.add(createTextField(semanticExpression, element,
							ExpressionVertexType.LEFTSTRINGVALUE));
				}
		}
		if (leftSide.getSelectedItem().equals("This Concept Variable")
				|| leftSide.getSelectedItem().equals("An Element Fixed")) {
			{
				if (semanticExpression.getSemanticExpressionType() != null) {
					leftPanel.add(createCombo(semanticExpression, element,
							ExpressionVertexType.LEFTCONCEPTVARIABLE,
							semanticExpression.getLeftValidExpressions(),
							false, 'C', true));
					semanticExpression
							.setLeftExpressionType(ExpressionVertexType.LEFTCONCEPTVARIABLE);
				}
			}
		}
		/*
		 * if (leftSide.getSelectedItem().equals("A Concept Type Variable")) { {
		 * if (semanticExpression.getSemanticExpressionType() != null) {
		 * leftPanel.add(createCombo(semanticExpression, element,
		 * ExpressionVertexType.LEFTCONCEPTTYPEVARIABLE,
		 * semanticExpression.getLeftValidExpressions(), true, 'C'));
		 * leftPanel.add(createCombo(semanticExpression, element,
		 * ExpressionVertexType.LEFTCONCEPTTYPEVARIABLE,
		 * semanticExpression.getLeftValidExpressions(), false, 'C'));
		 * semanticExpression
		 * .setLeftExpressionType(ExpressionVertexType.LEFTCONCEPTTYPEVARIABLE);
		 * } } }
		 */
		if (leftSide.getSelectedItem().equals(
				"Source Variable - Unique Rel. (Concept)")) {
			{
				if (semanticExpression.getSemanticExpressionType() != null) {
					JComboBox<String> conceptCombo = createCombo(
							semanticExpression, element,
							ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
							semanticExpression.getLeftValidExpressions(), true,
							'C', true);
					leftPanel.add(conceptCombo);
					InstElement recElement = refasModel
							.getVertex((String) conceptCombo.getSelectedItem());
					leftPanel.add(createCombo(semanticExpression, recElement,
							ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE,
							semanticExpression.getLeftValidExpressions(),
							false, 'C', true));
					semanticExpression
							.setLeftExpressionType(ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE);
				}
			}
		}
		if (leftSide.getSelectedItem().equals(
				"Target Variable - Unique Rel. (Concept)")) {
			{
				if (semanticExpression.getSemanticExpressionType() != null) {
					JComboBox<String> conceptCombo = createCombo(
							semanticExpression, element,
							ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
							semanticExpression.getLeftValidExpressions(), true,
							'C', true);
					leftPanel.add(conceptCombo);
					InstElement recElement = refasModel
							.getVertex((String) conceptCombo.getSelectedItem());
					leftPanel.add(createCombo(semanticExpression, recElement,
							ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE,
							semanticExpression.getLeftValidExpressions(),
							false, 'C', true));
					semanticExpression
							.setLeftExpressionType(ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE);
				}
			}
		}
		if (leftSide.getSelectedItem().equals(
				"Source Variable - Unique Rel. (Relation)")) {
			{
				if (semanticExpression.getSemanticExpressionType() != null) {
					JComboBox<String> conceptCombo = createCombo(
							semanticExpression, element,
							ExpressionVertexType.LEFTUNIQUEINCRELVARIABLE,
							semanticExpression.getLeftValidExpressions(), true,
							'P', true);
					leftPanel.add(conceptCombo);
					InstElement recElement = refasModel
							.getVertex((String) conceptCombo.getSelectedItem());
					leftPanel.add(createCombo(semanticExpression, recElement,
							ExpressionVertexType.LEFTUNIQUEINCRELVARIABLE,
							semanticExpression.getLeftValidExpressions(),
							false, 'P', true));
					semanticExpression
							.setLeftExpressionType(ExpressionVertexType.LEFTUNIQUEINCRELVARIABLE);
				}
			}
		}
		if (leftSide.getSelectedItem().equals(
				"Target Variable - Unique Rel. (Relation)")) {
			{
				if (semanticExpression.getSemanticExpressionType() != null) {
					JComboBox<String> conceptCombo = createCombo(
							semanticExpression, element,
							ExpressionVertexType.LEFTUNIQUEOUTRELVARIABLE,
							semanticExpression.getLeftValidExpressions(), true,
							'P', true);
					leftPanel.add(conceptCombo);
					InstElement recElement = refasModel
							.getVertex((String) conceptCombo.getSelectedItem());
					leftPanel.add(createCombo(semanticExpression, recElement,
							ExpressionVertexType.LEFTUNIQUEOUTRELVARIABLE,
							semanticExpression.getLeftValidExpressions(),
							false, 'P', true));
					semanticExpression
							.setLeftExpressionType(ExpressionVertexType.LEFTUNIQUEOUTRELVARIABLE);
				}
			}
		}
		ExpressionVertexType iterativeType = null;
		ExpressionVertexType subIterType = null;
		if (leftSide.getSelectedItem().equals(
				"Source Variables - Iterative (Concept)")) {
			iterativeType = ExpressionVertexType.LEFTITERINCCONVARIABLE;
			subIterType = ExpressionVertexType.LEFTITERINCCONFIXEDVARIABLE;
		}
		if (leftSide.getSelectedItem().equals(
				"Target Variables - Iterative (Concept)")) {
			iterativeType = ExpressionVertexType.LEFTITEROUTCONVARIABLE;
			subIterType = ExpressionVertexType.LEFTITEROUTCONFIXEDVARIABLE;
		}
		if (leftSide.getSelectedItem().equals(
				"Source Variables - Iterative (Relation)")) {
			iterativeType = ExpressionVertexType.LEFTITERINCRELVARIABLE;
			subIterType = ExpressionVertexType.LEFTITERINCRELFIXEDVARIABLE;
		}
		if (leftSide.getSelectedItem().equals(
				"Target Variables - Iterative (Relation)")) {
			iterativeType = ExpressionVertexType.LEFTITEROUTRELVARIABLE;
			subIterType = ExpressionVertexType.LEFTITEROUTRELFIXEDVARIABLE;
		}
		if (leftSide.getSelectedItem().equals(
				"Source/Target Variables (Concept)")) {
			iterativeType = ExpressionVertexType.LEFTITERANYCONVARIABLE;
			subIterType = ExpressionVertexType.LEFTITERANYFIXEDVARIABLE;
		}
		if (leftSide.getSelectedItem().equals(
				"Source/Target Variables (Relation)")) {
			iterativeType = ExpressionVertexType.LEFTITERANYRELVARIABLE;
			subIterType = ExpressionVertexType.LEFTITERANYFIXEDVARIABLE;
		}
		if (leftSide.getSelectedItem().equals("A Concept Type Variable")) {
			iterativeType = ExpressionVertexType.LEFTITERCONCEPTVARIABLE;
			subIterType = ExpressionVertexType.LEFTITERCONFIXEDVARIABLE;
		}
		if (iterativeType != null)
			if (semanticExpression.getSemanticExpressionType() != null) {

				if (semanticExpression.getLeftSemanticExpression() == null)
					semanticExpression.setLeftSemanticExpression(iterativeType,
							null, "id");
				JComboBox<String> conceptCombo = createCombo(
						semanticExpression, element, iterativeType,
						semanticExpression.getLeftValidExpressions(), true,
						'C', true);
				leftPanel.add(conceptCombo);
				InstElement recElement = refasModel
						.getVertex((String) conceptCombo.getSelectedItem());
				showExpression(semanticExpression.getLeftSemanticExpression(),
						recElement, recElement, true, leftPanel,
						semanticExpression.getLeftValidExpressions(),
						color > 20 ? color - 20 : color > 5 ? color - 5 : color);
				semanticExpression.setLeftExpressionType(iterativeType);
			}
		basePanel.add(leftPanel);
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(color, color, color));
		centerPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedExpression = exp;
				new Thread() {
					public void run() {
						initialize(ele, null);
					}
				}.start();
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		JComboBox<String> centerCombo = createOperatorsCombo(
				semanticExpression, element, semanticExpression.getOperation(),
				topExpressionType);
		if (selectedExpression == semanticExpression) {
			centerPanel.setBorder(raisedbevel);
		}
		centerPanel.add(centerCombo);
		basePanel.add(centerPanel);
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(color, color, color));
		rightPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedExpression = exp;
				new Thread() {
					public void run() {
						initialize(ele, null);
					}
				}.start();
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		if (!semanticExpression.isSingleInExpression()) {
			if (selectedExpression == semanticExpression) {
				rightPanel.setBorder(raisedbevel);
				basePanel.add(rightSide);
			}
			if (rightSide.getSelectedItem().equals("SubExpression")) {
				if (semanticExpression.getSemanticExpressionType() != null) {
					if (semanticExpression.getRightSemanticExpression() == null)
						semanticExpression.setRightSemanticExpression(
								ExpressionVertexType.RIGHTSUBEXPRESSION, null,
								"id");
					showExpression(
							semanticExpression.getRightSemanticExpression(),
							element, rightPanel,
							semanticExpression.getRightValidExpressions(),
							color > 20 ? color - 20 : color > 5 ? color - 5
									: color);
					semanticExpression
							.setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
				}
			}
			if (rightSide.getSelectedItem().equals("Number")) {
				if (semanticExpression.getRightExpressionType() != null)
					if (semanticExpression.getRightExpressionType().equals(
							ExpressionVertexType.RIGHTNUMERICVALUE)) {
						rightPanel
								.add(createTextField(semanticExpression,
										element,
										ExpressionVertexType.RIGHTNUMERICVALUE));
					}
			}
			if (rightSide.getSelectedItem().equals("String")) {
				if (semanticExpression.getRightExpressionType() != null)
					if (semanticExpression.getRightExpressionType().equals(
							ExpressionVertexType.RIGHTSTRINGVALUE)) {
						rightPanel
								.add(createTextField(semanticExpression,
										element,
										ExpressionVertexType.RIGHTSTRINGVALUE));
					}
			}
			if (rightSide.getSelectedItem().equals("This Concept Variable")) {
				if (semanticExpression.getSemanticExpressionType() != null) {
					rightPanel.add(createCombo(semanticExpression, element,
							ExpressionVertexType.RIGHTCONCEPTVARIABLE,
							semanticExpression.getRightValidExpressions(),
							false, 'C', false));
					semanticExpression
							.setRightExpressionType(ExpressionVertexType.RIGHTCONCEPTVARIABLE);
				}
			}

			if (rightSide.getSelectedItem().equals(
					"Source Variable - Unique Rel. (Concept)")) {
				{
					if (semanticExpression.getSemanticExpressionType() != null) {
						JComboBox<String> conceptCombo = createCombo(
								semanticExpression, element,
								ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
								semanticExpression.getRightValidExpressions(),
								true, 'C', false);
						rightPanel.add(conceptCombo);
						InstElement recElement = refasModel
								.getVertex((String) conceptCombo
										.getSelectedItem());
						rightPanel.add(createCombo(semanticExpression,
								recElement,
								ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE,
								semanticExpression.getRightValidExpressions(),
								false, 'C', false));
						semanticExpression
								.setRightExpressionType(ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE);
					}
				}
			}
			if (rightSide.getSelectedItem().equals(
					"Target Variable - Unique Rel. (Concept)")) {
				{
					if (semanticExpression.getSemanticExpressionType() != null) {
						JComboBox<String> conceptCombo = createCombo(
								semanticExpression, element,
								ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
								semanticExpression.getRightValidExpressions(),
								true, 'C', false);
						rightPanel.add(conceptCombo);
						InstElement recElement = refasModel
								.getVertex((String) conceptCombo
										.getSelectedItem());
						rightPanel.add(createCombo(semanticExpression,
								recElement,
								ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE,
								semanticExpression.getRightValidExpressions(),
								false, 'C', false));
						semanticExpression
								.setRightExpressionType(ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE);
					}
				}
			}
			if (rightSide.getSelectedItem().equals(
					"Source Variable - Unique Rel. (Relation)")) {
				{
					if (semanticExpression.getSemanticExpressionType() != null) {
						JComboBox<String> conceptCombo = createCombo(
								semanticExpression, element,
								ExpressionVertexType.RIGHTUNIQUEINCRELVARIABLE,
								semanticExpression.getRightValidExpressions(),
								true, 'P', false);
						rightPanel.add(conceptCombo);
						InstElement recElement = refasModel
								.getVertex((String) conceptCombo
										.getSelectedItem());
						rightPanel.add(createCombo(semanticExpression,
								recElement,
								ExpressionVertexType.RIGHTUNIQUEINCRELVARIABLE,
								semanticExpression.getRightValidExpressions(),
								false, 'P', false));
						semanticExpression
								.setRightExpressionType(ExpressionVertexType.RIGHTUNIQUEINCRELVARIABLE);
					}
				}
			}
			if (rightSide.getSelectedItem().equals(
					"Target Variable - Unique Rel. (Relation)")) {
				{
					if (semanticExpression.getSemanticExpressionType() != null) {
						JComboBox<String> conceptCombo = createCombo(
								semanticExpression, element,
								ExpressionVertexType.RIGHTUNIQUEOUTRELVARIABLE,
								semanticExpression.getRightValidExpressions(),
								true, 'P', false);
						rightPanel.add(conceptCombo);
						InstElement recElement = refasModel
								.getVertex((String) conceptCombo
										.getSelectedItem());
						rightPanel.add(createCombo(semanticExpression,
								recElement,
								ExpressionVertexType.RIGHTUNIQUEOUTRELVARIABLE,
								semanticExpression.getRightValidExpressions(),
								false, 'P', false));
						semanticExpression
								.setRightExpressionType(ExpressionVertexType.RIGHTUNIQUEOUTRELVARIABLE);
					}
				}
			}
		}

		basePanel.add(rightPanel);
		if (color == 255) {
			JButton delButton = new JButton("Delete");
			delButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					semanticExpressions.remove(semanticExpression);

					new Thread() {
						public void run() {
							initialize(element, null);
						}
					}.start();
					pack();
					revalidate();
					repaint();

				}
			});
			basePanel.add(delButton);
		}
		parentPanel.add(basePanel);
	}

	private JTextField createTextField(
			final SemanticExpression instanceExpression,
			final InstElement element,
			final ExpressionVertexType expressionVertexType) {
		JTextField textField = null;
		switch (expressionVertexType) {
		case LEFTNUMERICVALUE:

			textField = new JTextField(""
					+ (instanceExpression).getLeftNumber());
			break;
		case RIGHTNUMERICVALUE:
			textField = new JTextField(""
					+ (instanceExpression).getRightNumber());
			break;
		case LEFTSTRINGVALUE:
			textField = new JTextField((instanceExpression).getLeftString());
			break;
		case RIGHTSTRINGVALUE:
			textField = new JTextField((instanceExpression).getRightString());
			break;
		default:
			break;
		}
		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent event) {
				String item = (String) ((JTextField) event.getSource())
						.getText();
				if (item != null) {
					switch (expressionVertexType) {
					case LEFTNUMERICVALUE:
						instanceExpression.setLeftNumber(Integer.parseInt(item));
						break;
					case RIGHTNUMERICVALUE:
						instanceExpression.setRightNumber(Integer
								.parseInt(item));
						break;
					case LEFTSTRINGVALUE:
						instanceExpression.setLeftString(item);
						break;
					case RIGHTSTRINGVALUE:
						instanceExpression.setRightString(item);
						break;
					default:
						break;
					}
				}
			}
		});
		return textField;
	}

	private JComboBox<String> createCombo(
			final SemanticExpression semanticExpression,
			final InstElement element,
			final ExpressionVertexType expressionVertexType, int validType,
			final boolean showConceptName, final char elementType,
			final boolean left) {

		JComboBox<String> identifiers = null;
		String selectedElement = null;
		selectedElement = semanticExpression.getSelectedElement(
				expressionVertexType, showConceptName, elementType);
		InstElement comboElement = null;
		// if (elementType == 'C')
		comboElement = element;
		// if (elementType == 'P')
		// comboElement = semanticExpression.getSelectedElement(
		// expressionVertexType, elementType);
		identifiers = fillCombo(semanticExpression, expressionVertexType,
				comboElement, selectedElement, showConceptName, elementType);

		identifiers.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if (item != null) {
						if (showConceptName) {
							if (left)
								semanticExpression
										.setLeftSemanticElement(refasModel
												.getVertex(item));
							else
								semanticExpression
										.setRightSemanticElement(refasModel
												.getVertex(item));
							/*
							 * semanticExpression.setInstElement(
							 * refasModel.getVertex(item), expressionVertexType,
							 * elementType);
							 */
						} else {
							if (expressionVertexType == ExpressionVertexType.LEFTVARIABLEVALUE)
								semanticExpression.setLeftSemanticElement();
							if (expressionVertexType == ExpressionVertexType.RIGHTVARIABLEVALUE)
								semanticExpression.setRightSemanticElement();
							semanticExpression.setAttributeName(item,
									expressionVertexType, elementType);
						}
						new Thread() {
							public void run() {
								initialize(element, null);
							}
						}.start();
					}
				}
			}

		});
		return identifiers;
	}

	private JComboBox<String> fillCombo(
			final SemanticExpression semanticExpression,
			ExpressionVertexType type, InstElement element,
			String selectedElement, boolean isConcept, char elementType) {
		JComboBox<String> combo = new JComboBox<String>();
		Collection<InstElement> instElements = null;
		instElements = new ArrayList<InstElement>();
		InstElement instElement = semanticExpression.getLeftSemanticElement();
		switch (type) {
		case RIGHTVARIABLE:
			instElement = semanticExpression.getRightSemanticElement();
			instElement = semanticExpression.getRightSemanticElement();
			break;
		case LEFTVARIABLE:
		case LEFTCONCEPTVARIABLE:
			instElement = semanticExpression.getLeftSemanticElement();
			/*
			 * if (element instanceof InstConcept) instElements.add(element); if
			 * (element instanceof InstPairwiseRelation) {
			 * instElements.add(((InstPairwiseRelation) element)
			 * .getSourceRelations().get(0));
			 * instElements.add(((InstPairwiseRelation) element)
			 * .getTargetRelations().get(0)); instElements.add(element); }
			 * 
			 * if (element instanceof InstOverTwoRelation) { if
			 * (((InstOverTwoRelation) element).getTargetRelations().size() > 0)
			 * { instElements .add(((InstPairwiseRelation)
			 * ((InstOverTwoRelation) element) .getTargetRelations().get(0)));
			 * instElements .add(((InstPairwiseRelation) ((InstOverTwoRelation)
			 * element) .getTargetRelations().get(0))
			 * .getTargetRelations().get(0)); } for (InstElement sourceRelation
			 * : ((InstOverTwoRelation) element) .getSourceRelations())
			 * instElements.add(((InstPairwiseRelation) sourceRelation)
			 * .getSourceRelations().get(0)); instElements.add(element); }
			 */

			break;
		case LEFTITERCONCEPTVARIABLE:
			instElements = refasModel.getVariabilityVertexCollection();
			instElement = semanticExpression.getLeftSemanticElement();
			break;
		case LEFTITERINCRELVARIABLE:
		case LEFTITEROUTRELVARIABLE:
		case LEFTITERANYRELVARIABLE:
			for (InstElement sourceRelation : refasModel
					.getVariabilityVertexCollection())
				if (((element instanceof InstConcept && (sourceRelation
						.getSupportMetaElementIden().equals("Concept") || sourceRelation
						.getSupportMetaElementIden()
						.equals("CSOverTwoRelation"))))
						|| (element instanceof InstPairwiseRelation && sourceRelation
								.getSupportMetaElementIden().equals(
										"CSPairWiseRelation")))
					instElements.add(sourceRelation);// .getSourceRelations().get(0));
			break;
		case RIGHTUNIQUEINCCONVARIABLE:
		case RIGHTUNIQUEOUTCONVARIABLE:
		case LEFTUNIQUEINCCONVARIABLE:
		case LEFTUNIQUEOUTCONVARIABLE:
			for (InstElement sourceRelation : refasModel
					.getVariabilityVertexCollection())
				if (sourceRelation.getSupportMetaElementIden()
						.equals("Concept")
						|| sourceRelation.getSupportMetaElementIden().equals(
								"CSOverTwoRelation"))
					instElements.add(sourceRelation);// .getSourceRelations().get(0));
			break;
		case RIGHTUNIQUEINCRELVARIABLE:
		case RIGHTUNIQUEOUTRELVARIABLE:
		case LEFTUNIQUEINCRELVARIABLE:
		case LEFTUNIQUEOUTRELVARIABLE:
			for (InstElement sourceRelation : refasModel
					.getVariabilityVertexCollection())
				if (sourceRelation.getSupportMetaElementIden().equals(
						"CSPairWiseRelation"))
					instElements.add(sourceRelation);// .getSourceRelations().get(0));
			break;
		default:
		}
		if (isConcept) {
			combo.addItem("-- Any Concept --");
			for (InstElement instVertex : instElements) {
				if (displayConceptName
						&& instVertex.getInstAttribute("name") != null)
					combo.addItem((String) instVertex.getInstAttribute("name")
							.getValue());
				else
					combo.addItem(instVertex.getIdentifier());
			}
		} else {
			if (instElement != null) {
				if (instElement.getEditableSemanticElement() != null)
					for (AbstractAttribute attribute : instElement
							.getEditableSemanticElement()
							.getAllSemanticAttributes().values())
						if (displayVariableName)

							combo.addItem(attribute.getDisplayName());
						else
							combo.addItem(attribute.getName());
				for (AbstractAttribute attribute : instElement
						.getTransSupportMetaElement().getModelingAttributes()
						.values())
					if (displayVariableName)

						combo.addItem(attribute.getDisplayName());
					else
						combo.addItem(attribute.getName());
			}
			if (instElements != null)
				for (InstElement instElementT : instElements)
					if (instElementT.getEditableSemanticElement() != null)
						for (AbstractAttribute attribute : instElementT
								.getEditableSemanticElement()
								.getAllSemanticAttributes().values())

							if (displayVariableName)

								combo.addItem(attribute.getDisplayName());
							else
								combo.addItem(attribute.getName());
		}
		combo.setSelectedItem(selectedElement);
		return combo;
	}

	private JComboBox<String> createOperatorsCombo(
			final SemanticExpression instanceExpression,
			final InstElement element, String selectedOperator,
			int topExpressionType) {
		JComboBox<String> combo = new JComboBox<String>();
		List<SemanticExpressionType> semanticExpressionTypes = SemanticExpressionType
				.getValidSemanticExpressionTypes(refasModel
						.getSemanticExpressionTypes().values(),
						topExpressionType);

		for (SemanticExpressionType semanticExpressionType : semanticExpressionTypes) {
			combo.addItem(semanticExpressionType.getTextConnector());
		}
		combo.setSelectedItem(selectedOperator);
		combo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if (item != null) {
						instanceExpression.setSemanticExpressionType(refasModel
								.getSemanticExpressionTypes().get(item));
					}
					new Thread() {
						public void run() {
							initialize(element, null);
						}
					}.start();
					// revalidate();
					// pack();

				}
			}

		});
		return combo;
	}

	private JComboBox<String> createSidesCombo(
			final SemanticExpression semanticExpression,
			final InstElement element, final boolean left, boolean relation,
			boolean fixed) {
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("This Concept Variable");
		combo.addItem("SubExpression");
		combo.addItem("Number");
		combo.addItem("String");
		if (left) {
			combo.addItem("A Concept Type Variable");
			combo.addItem("Source Variables - Iterative (Concept)");
			combo.addItem("Source Variables - Iterative (Relation)");
			combo.addItem("Target Variables - Iterative (Concept)");
			combo.addItem("Target Variables - Iterative (Relation)");
			combo.addItem("Source/Target Variables (Concept)");
			combo.addItem("Source/Target Variables (Relation)");
		}
		if (fixed)
			combo.addItem("An Element Fixed");
		combo.addItem("Source Variable - Unique Rel. (Concept)");
		combo.addItem("Target Variable - Unique Rel. (Concept)");
		combo.addItem("Source Variable - Unique Rel. (Relation)");
		combo.addItem("Target Variable - Unique Rel. (Relation)");
		combo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if ((!item.equals(semanticExpression.getLastLeft()) && left)
							|| (!item.equals(semanticExpression.getLastRight()) && !left)) {
						if (left)
							semanticExpression.setLastLeft(item);
						if (!left)
							semanticExpression.setLastRight(item);
						switch (item) {
						case "This Concept Variable":
							if (left)
								semanticExpression
										.setLeftExpressionType(ExpressionVertexType.LEFTCONCEPTVARIABLE);
							else
								semanticExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTCONCEPTVARIABLE);
							break;
						case "SubExpression":
							if (left)
								semanticExpression
										.setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
							else
								semanticExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
							break;
						case "Number":
							if (left)
								semanticExpression
										.setLeftExpressionType(ExpressionVertexType.LEFTNUMERICVALUE);
							else
								semanticExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
							break;
						case "String":
							if (left)
								semanticExpression
										.setLeftExpressionType(ExpressionVertexType.LEFTSTRINGVALUE);
							else
								semanticExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTSTRINGVALUE);
							break;
						case "A Concept Type Variable":
							semanticExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTITERCONCEPTVARIABLE);
							break;
						case "Source Variables - Iterative (Concept)":
							semanticExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTITERINCCONVARIABLE);
							break;
						case "Source Variables - Iterative (Relation)":
							semanticExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTITERINCRELVARIABLE);
							break;
						case "Target Variables - Iterative (Concept)":
							semanticExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTITEROUTCONVARIABLE);
							break;
						case "Target Variables - Iterative (Relation)":
							semanticExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTITEROUTRELVARIABLE);
							break;
						case "Source/Target Variables (Concept)":
							semanticExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTITERANYCONVARIABLE);
							break;
						case "Source/Target Variables (Relation)":
							semanticExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTITERANYRELVARIABLE);
							break;

						case "Source Variable - Unique Rel. (Concept)":
							if (left)
								semanticExpression
										.setLeftExpressionType(ExpressionVertexType.LEFTUNIQUEINCCONVARIABLE);
							else
								semanticExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTUNIQUEINCCONVARIABLE);
							break;
						case "Target Variable - Unique Rel. (Concept)":
							if (left)
								semanticExpression
										.setLeftExpressionType(ExpressionVertexType.LEFTUNIQUEOUTCONVARIABLE);
							else
								semanticExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTUNIQUEOUTCONVARIABLE);
							break;
						case "Source Variable - Unique Rel. (Relation)":
							if (left)
								semanticExpression
										.setLeftExpressionType(ExpressionVertexType.LEFTUNIQUEINCRELVARIABLE);
							else
								semanticExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTUNIQUEINCRELVARIABLE);
							break;
						case "Target Variable - Unique Rel. (Relation)":
							if (left)
								semanticExpression
										.setLeftExpressionType(ExpressionVertexType.LEFTUNIQUEOUTRELVARIABLE);
							else
								semanticExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTUNIQUEOUTRELVARIABLE);
							break;
						}
						new Thread() {
							public void run() {
								initialize(element, null);
							}
						}.start();
					}
				}
			}
		});
		return combo;
	}

	/**
	 * @return
	 */
	public List<IntSemanticExpression> getExpressions() {
		return semanticExpressions;
	}

	public void center() {
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setOnAccept(SemanticExpressionButtonAction onAccept) {
		this.onAccept = onAccept;
	}

	public void setOnCancel(SemanticExpressionButtonAction onCancel) {
		this.onCancel = onCancel;
	}

}
