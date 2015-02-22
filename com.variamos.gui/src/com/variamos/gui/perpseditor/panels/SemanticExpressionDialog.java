package com.variamos.gui.perpseditor.panels;

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
import com.variamos.perspsupport.instancesupport.InstOverTwoRelation;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.perspmodel.RefasModel;
import com.variamos.perspsupport.semanticinterface.IntSemanticExpression;
import com.variamos.perspsupport.syntaxsupport.AbstractAttribute;
import com.variamos.perspsupport.types.ExpressionVertexType;

/**
 * @author unknown
 *
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

	static interface SemanticExpressionButtonAction {
		public boolean onAction();
	}

	public SemanticExpressionDialog(VariamosGraphEditor editor,
			InstElement instElement,
			List<IntSemanticExpression> semanticExpressions) {
		super(editor.getFrame(), "Semantic Expressions Editor");
		refasModel = (RefasModel) editor.getEditedModel();
		this.semanticExpressions = new ArrayList<IntSemanticExpression>();
		this.semanticExpressions.add(new SemanticExpression(instElement));
		setPreferredSize(new Dimension(width, height));
		this.initialize(instElement, semanticExpressions);
	}

	public void initialize(final InstElement element,
			final List<IntSemanticExpression> semanticExpressions) {
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
		for (IntSemanticExpression semanticExpression : this.semanticExpressions) {

			if (semanticExpressions != null)
				selectedExpression = (SemanticExpression) semanticExpression;

			solutionPanel = new JPanel();
			solutionPanel.setAutoscrolls(true);
			solutionPanel.setMaximumSize(new Dimension(900, 200));
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
				leftSide.setSelectedItem("This Concept Variable");
				break;
			case LEFTNUMERICEXPRESSIONVALUE:
				leftSide.setSelectedItem("Number");
				break;
			case LEFTCONCEPTTYPEVARIABLE:
				leftSide.setSelectedItem("A Concept Type Variable");
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
				rightSide.setSelectedItem("This Concept Variable");
				break;
			case RIGHTNUMERICEXPRESSIONVALUE:
				rightSide.setSelectedItem("Number");
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
									ExpressionVertexType.LEFTCONCEPTTYPEVARIABLE,
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
		JTextField textField = new JTextField(""
				+ (instanceExpression).getNumber());
		textField.addFocusListener(new FocusListener() {
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

		identifiers = fillCombo(semanticExpression, expressionVertexType,
				element, selectedElement, isConcept);
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

	private JComboBox<String> fillCombo(
			final SemanticExpression semanticExpression,
			ExpressionVertexType type, InstElement element,
			String selectedElement, boolean isConcept) {
		JComboBox<String> combo = new JComboBox<String>();
		Collection<InstElement> instElements = null;
		instElements = new ArrayList<InstElement>();
		InstElement instElement = semanticExpression.getLeftSemanticElement();
		switch (type) {
		case LEFTCONCEPTVARIABLE:
		case RIGHTCONCEPTVARIABLE:
			instElement = element;
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
			if (instElement != null)
				for (AbstractAttribute attribute : instElement
						.getEditableSemanticElement().getSemanticAttributes()
						.values())

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
			final InstElement element, final boolean left) {
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("This Concept Variable");
		combo.addItem("SubExpression");
		combo.addItem("Number");
		if (left) {
			combo.addItem("A Concept Type Variable");
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
