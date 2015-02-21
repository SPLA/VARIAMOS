package com.variamos.gui.refas.editor.panels;

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
import com.variamos.gui.pl.editor.SpringUtilities;
import com.variamos.refas.RefasModel;
import com.variamos.semantic.expressionsupport.SemanticExpression;
import com.variamos.semantic.expressionsupport.SemanticExpressionType;
import com.variamos.semantic.types.ExpressionVertexType;
import com.variamos.syntax.instancesupport.InstConcept;
import com.variamos.syntax.instancesupport.InstElement;
import com.variamos.syntax.instancesupport.InstOverTwoRelation;
import com.variamos.syntax.instancesupport.InstPairwiseRelation;

/**
 * @author unknown
 *
 */
@SuppressWarnings("serial")
public class SemanticExpressionDialog extends JDialog {
	private List<SemanticExpression> semanticExpressions;
	private SemanticExpressionButtonAction onAccept, onCancel;
	private SemanticExpression selectedExpression;
	private JPanel solutionPanel;
	private RefasModel refasModel;

	static interface SemanticExpressionButtonAction {
		public boolean onAction();
	}

	public SemanticExpressionDialog(VariamosGraphEditor editor,
			InstElement instElement, boolean multiExpression,
			List<SemanticExpression> semanticExpressions) {
		super(editor.getFrame(), "Semantic Expressions Editor");
		refasModel = (RefasModel) editor.getEditedModel();
		this.semanticExpressions = new ArrayList<SemanticExpression>();
		this.semanticExpressions.add(new SemanticExpression(instElement));
		this.initialize(instElement, semanticExpressions);

		setPreferredSize(new Dimension(950, 400));
		setMaximumSize(new Dimension(1650, 1080));
	}

	public void initialize(final InstElement element,
			final List<SemanticExpression> semanticExpressions) {
		this.getContentPane().removeAll();
		// removeAll();
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());

		if (semanticExpressions != null)
			this.semanticExpressions = semanticExpressions;
		final List<SemanticExpression> finalSemanticExpressions = this.semanticExpressions;
		for (SemanticExpression semanticExpression : this.semanticExpressions) {

			if (semanticExpressions != null)
				selectedExpression = semanticExpression;

			solutionPanel = new JPanel();
			solutionPanel.setAutoscrolls(true);
			solutionPanel.setMaximumSize(new Dimension(900, 200));
			showExpression(semanticExpression, element, solutionPanel,
					SemanticExpressionType.BOOLEXP, 255);

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
		JButton addButton = new JButton("Add new Semantic Expression");
		addButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				finalSemanticExpressions.add(new SemanticExpression(element));

				new Thread() {
					public void run() {
						initialize(element, null);
					}
				}.start();
				pack();
				revalidate();
				repaint();
			}});
		panel.add(addButton);

		SpringUtilities.makeCompactGrid(panel, this.semanticExpressions.size()+1,
				1, 4, 4, 4, 4);

		add(panel, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new SpringLayout());

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
	}

	private void showExpression(final SemanticExpression semanticExpression,
			final InstElement element, JPanel parentPanel,
			int topExpressionType, int color) {
		final InstElement ele = element;
		final SemanticExpression exp = semanticExpression;

		JPanel basePanel = new JPanel();
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		// if (selectedExpression == instanceExpression)
		// basePanel.setBorder(blackline);
		// else
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
				element, true);
		JComboBox<String> rightSide = createSidesCombo(semanticExpression,
				element, false);
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
		if (selectedExpression == semanticExpression) {
			leftPanel.setBorder(raisedbevel);
			basePanel.add(leftSide);
		}
		if (semanticExpression.getLeftExpressionType() != null)
			switch (semanticExpression.getLeftExpressionType()) {
			case LEFTSUBEXPRESSION:
				leftSide.setSelectedItem("SubExpression");
				break;
			case LEFTCONCEPTVARIABLE:
				leftSide.setSelectedItem("Variable");
				break;
			case LEFTNUMERICEXPRESSIONVALUE:
				leftSide.setSelectedItem("Number");
				break;
			case LEFTCONCEPTTYPEVARIABLE:
				leftSide.setSelectedItem("A Concept Type Variable");
				break;
			case LEFTANYCONCEPTVARIABLE:
				leftSide.setSelectedItem("Any Concept Variable");
				break;
			case LEFTINCOMRELVARIABLE:
				leftSide.setSelectedItem("Any Incomming Relation Variable");
				break;
			case LEFTOUTGRELVARIABLE:
				leftSide.setSelectedItem("Any Outgoing Relation Variable");
				break;
			case LEFTANYRELVARIABLE:
				leftSide.setSelectedItem("Any Relation Variable");
				break;
			default:
			}

		if (semanticExpression.getRightExpressionType() != null)
			switch (semanticExpression.getRightExpressionType()) {
			case RIGHTSUBEXPRESSION:
				rightSide.setSelectedItem("SubExpression");
				break;
			case RIGHTCONCEPTVARIABLE:
				rightSide.setSelectedItem("Variable");
				break;
			case RIGHTNUMERICEXPRESSIONVALUE:
				rightSide.setSelectedItem("Number");
				break;
			default:
			}
		if (leftSide.getSelectedItem().equals("SubExpression")) {
			if (semanticExpression.getSemanticExpressionType() != null) {
				semanticExpression.setLeftSemanticExpression(
						ExpressionVertexType.LEFTSUBEXPRESSION, null, "id");
				showExpression(semanticExpression.getLeftSemanticExpression(),
						element, leftPanel,
						semanticExpression.getLeftValidExpressions(),
						color > 20 ? color - 20 : color > 5 ? color - 5 : color);
			}
		}
		if (leftSide.getSelectedItem().equals("Number")) {
			if (semanticExpression.getLeftExpressionType() != null)
				if (semanticExpression.getLeftExpressionType().equals(
						ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE)) {
					basePanel.add(createTextField(semanticExpression, element,
							ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE));
				}
		}
		if (leftSide.getSelectedItem().equals("This Concept Variable")) {
			{
				if (semanticExpression.getSemanticExpressionType() != null) {
					leftPanel
							.add(createCombo(semanticExpression, element,
									ExpressionVertexType.LEFTCONCEPTVARIABLE,
									semanticExpression
											.getLeftValidExpressions(), false));
					semanticExpression
							.setLeftExpressionType(ExpressionVertexType.LEFTCONCEPTVARIABLE);
				}
			}
		}
		if (leftSide.getSelectedItem().equals("A Concept Type Variable")) {
			{
				if (semanticExpression.getSemanticExpressionType() != null) {
					leftPanel
							.add(createCombo(
									semanticExpression,
									element,
									ExpressionVertexType.LEFTANYCONCEPTVARIABLE,
									semanticExpression
											.getLeftValidExpressions(), true));
					leftPanel
							.add(createCombo(
									semanticExpression,
									element,
									ExpressionVertexType.LEFTCONCEPTTYPEVARIABLE,
									semanticExpression
											.getLeftValidExpressions(), false));
					semanticExpression
							.setLeftExpressionType(ExpressionVertexType.LEFTCONCEPTTYPEVARIABLE);
				}
			}
		}
		if (leftSide.getSelectedItem().equals("Any Concept Variable")) {
			{
				if (semanticExpression.getSemanticExpressionType() != null) {
					leftPanel
							.add(createCombo(
									semanticExpression,
									element,
									ExpressionVertexType.LEFTANYCONCEPTVARIABLE,
									semanticExpression
											.getLeftValidExpressions(), true));
					leftPanel
							.add(createCombo(
									semanticExpression,
									element,
									ExpressionVertexType.LEFTANYCONCEPTVARIABLE,
									semanticExpression
											.getLeftValidExpressions(), false));
					semanticExpression
							.setLeftExpressionType(ExpressionVertexType.LEFTANYCONCEPTVARIABLE);
				}
			}
		}
		if (leftSide.getSelectedItem()
				.equals("Any Incomming Relation Variable")) {
			{
				if (semanticExpression.getSemanticExpressionType() != null) {
					leftPanel
							.add(createCombo(semanticExpression, element,
									ExpressionVertexType.LEFTINCOMRELVARIABLE,
									semanticExpression
											.getLeftValidExpressions(), true));
					leftPanel
							.add(createCombo(semanticExpression, element,
									ExpressionVertexType.LEFTINCOMRELVARIABLE,
									semanticExpression
											.getLeftValidExpressions(), false));
					semanticExpression
							.setLeftExpressionType(ExpressionVertexType.LEFTINCOMRELVARIABLE);
				}
			}
		}
		if (leftSide.getSelectedItem().equals("Any Outgoing Relation Variable")) {
			{
				if (semanticExpression.getSemanticExpressionType() != null) {
					leftPanel
							.add(createCombo(semanticExpression, element,
									ExpressionVertexType.LEFTOUTGRELVARIABLE,
									semanticExpression
											.getLeftValidExpressions(), true));
					leftPanel
							.add(createCombo(semanticExpression, element,
									ExpressionVertexType.LEFTOUTGRELVARIABLE,
									semanticExpression
											.getLeftValidExpressions(), false));
					semanticExpression
							.setLeftExpressionType(ExpressionVertexType.LEFTOUTGRELVARIABLE);
				}
			}
		}
		if (leftSide.getSelectedItem().equals("Any Relation Variable")) {
			{
				if (semanticExpression.getSemanticExpressionType() != null) {
					leftPanel
							.add(createCombo(semanticExpression, element,
									ExpressionVertexType.LEFTANYRELVARIABLE,
									semanticExpression
											.getLeftValidExpressions(), true));
					leftPanel
							.add(createCombo(semanticExpression, element,
									ExpressionVertexType.LEFTANYRELVARIABLE,
									semanticExpression
											.getLeftValidExpressions(), false));
					semanticExpression
							.setLeftExpressionType(ExpressionVertexType.LEFTANYRELVARIABLE);
				}
			}
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
							ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE)) {
						rightPanel
								.add(createTextField(
										semanticExpression,
										element,
										ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE));
					}
			}
			if (rightSide.getSelectedItem().equals("This Concept Variable")) {
				if (semanticExpression.getSemanticExpressionType() != null) {
					rightPanel.add(createCombo(semanticExpression, element,
							ExpressionVertexType.RIGHTCONCEPTVARIABLE,
							semanticExpression.getRightValidExpressions(),
							false));
					semanticExpression
							.setRightExpressionType(ExpressionVertexType.RIGHTCONCEPTVARIABLE);
				}
			}
		}

		basePanel.add(rightPanel);
		if (color == 255)
		{
			JButton delButton = new JButton("Delete");
			delButton.addActionListener(new ActionListener(){

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
					
				}});
			basePanel.add(delButton);
		}
		parentPanel.add(basePanel);
	}

	private JTextField createTextField(
			final SemanticExpression instanceExpression,
			final InstElement element,
			final ExpressionVertexType expressionVertexType) {
		JTextField textField = new JTextField(""
				+ (instanceExpression).getNumber());
		textField.addFocusListener(new FocusListener() {

			public void actionPerformed(ActionEvent event) {

			}

			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent event) {
				String item = (String) ((JTextField) event.getSource())
						.getText();
				if (item != null) {
					instanceExpression.setNumber(Integer.parseInt(item));
				}
			}
		});
		return textField;
	}

	private JComboBox<String> createCombo(
			final SemanticExpression semanticExpression,
			final InstElement element,
			final ExpressionVertexType expressionVertexType, int validType,
			final boolean isConcept) {

		JComboBox<String> identifiers = null;
		String selectedElement = null;
		selectedElement = semanticExpression.getSelectedElement(
				expressionVertexType, isConcept);

		identifiers = fillCombo(expressionVertexType, element, selectedElement,
				isConcept);
		identifiers.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if (item != null) {
						if (isConcept) {
							semanticExpression.setInstElement(
									refasModel.getVertex(item),
									expressionVertexType);
						} else {
							if (expressionVertexType == ExpressionVertexType.LEFTVARIABLEVALUE)
								semanticExpression.setLeftSemanticElement();
							if (expressionVertexType == ExpressionVertexType.RIGHTVARIABLEVALUE)
								semanticExpression.setRightSemanticElement();
							semanticExpression.setAttributeName(item,
									expressionVertexType);
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

	/*
	 * private JComboBox<String> createIdentifiersValueCombo(InstElement
	 * element, String selectedElement) { JComboBox<String> combo = new
	 * JComboBox<String>(); for (InstVertex instVertex : refasModel
	 * .getVariabilityVertexCollection()) { IntSemanticElement semElement2 =
	 * ((MetaVertex) instVertex
	 * .getTransSupportMetaElement()).getTransSemanticConcept(); if (semElement2
	 * != null && semElement2.getIdentifier().equals("Variable")) { String
	 * variableType = (String) instVertex.getInstAttribute(
	 * SemanticVariable.VAR_VARIABLETYPE).getValue(); switch (variableType) {
	 * case "Boolean": combo.addItem(instVertex.getIdentifier() + "_" + "true");
	 * combo.addItem(instVertex.getIdentifier() + "_" + "false"); break; case
	 * "Integer": String domain = (String) instVertex.getInstAttribute(
	 * SemanticVariable.VAR_VARIABLEDOMAIN).getValue(); String split[] =
	 * domain.split(","); for (String dom : split) {
	 * combo.addItem(instVertex.getIdentifier() + "_" + dom);
	 * 
	 * } break; case "Enumeration": Object object = instVertex.getInstAttribute(
	 * "enumerationType").getValueObject(); if (object != null) {
	 * Set<InstAttribute> values = (Set<InstAttribute>) ((InstAttribute)
	 * ((InstEnumeration) object) .getInstAttribute("value")).getValue(); for
	 * (InstAttribute value : values) combo.addItem(instVertex.getIdentifier() +
	 * "_" + value.getValue()); } break;
	 * 
	 * } } } combo.setSelectedItem(selectedElement); return combo; }
	 */
	private JComboBox<String> fillCombo(ExpressionVertexType type,
			InstElement element, String selectedElement, boolean isConcept) {
		JComboBox<String> combo = new JComboBox<String>();
		Collection<InstElement> instElements = null;
		instElements = new ArrayList<InstElement>();

		switch (type) {
		case LEFTCONCEPTVARIABLE:
		case RIGHTCONCEPTVARIABLE:
			if (element instanceof InstConcept)
				instElements.add(element);
			if (element instanceof InstPairwiseRelation) {
				instElements.add(((InstPairwiseRelation) element)
						.getSourceRelations().get(0));
				instElements.add(((InstPairwiseRelation) element)
						.getTargetRelations().get(0));
				instElements.add(element);
			}

			if (element instanceof InstOverTwoRelation) {
				if (((InstOverTwoRelation) element).getTargetRelations().size() > 0) {
					instElements
							.add(((InstPairwiseRelation) ((InstOverTwoRelation) element)
									.getTargetRelations().get(0)));
					instElements
							.add(((InstPairwiseRelation) ((InstOverTwoRelation) element)
									.getTargetRelations().get(0))
									.getTargetRelations().get(0));
				}
				for (InstElement sourceRelation : ((InstOverTwoRelation) element)
						.getSourceRelations())
					instElements.add(((InstPairwiseRelation) sourceRelation)
							.getSourceRelations().get(0));
				instElements.add(element);
			}

			break;
		case LEFTCONCEPTTYPEVARIABLE:
		case LEFTANYCONCEPTVARIABLE:
			instElements = refasModel.getVariabilityVertexCollection();
			break;
		case LEFTINCOMRELVARIABLE:
			instElements = element.getSourceRelations();
			break;
		case LEFTOUTGRELVARIABLE:
			instElements = element.getTargetRelations();
			break;
		case LEFTANYRELVARIABLE:
			instElements.addAll(element.getSourceRelations());
			instElements.addAll(element.getTargetRelations());
			break;

		}
		if (isConcept) {
			for (InstElement instVertex : instElements) {
				combo.addItem(instVertex.getIdentifier());
			}
		} else {
			for (String attributeName : element.getInstAttributes().keySet())
				combo.addItem(attributeName);

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
			final InstElement element, final boolean left) {
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("This Concept Variable");
		combo.addItem("SubExpression");
		combo.addItem("Number");
		if (left) {
			combo.addItem("A Concept Type Variable");
			combo.addItem("Any Concept Variable");
			combo.addItem("Any Incomming Relation Variable");
			combo.addItem("Any Outgoing Relation Variable");
			combo.addItem("Any Relation Variable");
		}
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
										.setLeftExpressionType(ExpressionVertexType.LEFT);
							else
								semanticExpression
										.setRightExpressionType(ExpressionVertexType.RIGHT);
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
										.setLeftExpressionType(ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE);
							else
								semanticExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE);
							break;
						case "A Concept Type Variable":
							semanticExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTCONCEPTTYPEVARIABLE);
							break;
						case "Any Concept Variable":
							semanticExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTANYCONCEPTVARIABLE);
							break;
						case "Any Incomming Relation Variable":
							semanticExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTINCOMRELVARIABLE);
							break;
						case "Any Outgoing Relation Variable":
							semanticExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTOUTGRELVARIABLE);
							break;
						case "Any Relation Variable":
							semanticExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTANYRELVARIABLE);
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
	public List<SemanticExpression> getExpressions() {
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
