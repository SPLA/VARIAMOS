package com.variamos.gui.perpseditor.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import java.util.List;
import java.util.Set;

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
import com.variamos.gui.perspeditor.SpringUtilities;
import com.variamos.perspsupport.expressionsupport.InstanceExpression;
import com.variamos.perspsupport.expressionsupport.SemanticExpressionType;
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstEnumeration;
import com.variamos.perspsupport.instancesupport.InstOverTwoRelation;
import com.variamos.perspsupport.instancesupport.InstPairwiseRelation;
import com.variamos.perspsupport.instancesupport.InstVertex;
import com.variamos.perspsupport.perspmodel.RefasModel;
import com.variamos.perspsupport.semanticinterface.IntSemanticElement;
import com.variamos.perspsupport.semanticsupport.SemanticVariable;
import com.variamos.perspsupport.syntaxsupport.MetaVertex;
import com.variamos.perspsupport.types.ExpressionVertexType;

/**
 * @author unknown
 *
 */
@SuppressWarnings("serial")
public class InstanceExpressionDialog extends JDialog {
	private InstanceExpression[] instanceExpressions;
	private InstanceExpressionButtonAction onAccept, onCancel;
	private InstanceExpression selectedExpression;
	private JPanel solutionPanel;
	private RefasModel refasModel;

	static interface InstanceExpressionButtonAction {
		public boolean onAction();
	}

	public InstanceExpressionDialog(VariamosGraphEditor editor,
			InstElement instElement, boolean multiExpression,
			InstanceExpression... instanceExpressions) {
		super(editor.getFrame(), "Expressions Editor");
		refasModel = (RefasModel) editor.getEditedModel();
		this.initialize(instElement, instanceExpressions);

		setPreferredSize(new Dimension(950, 400));
		setMaximumSize(new Dimension(1650, 1080));
	}

	public void initialize(InstElement element,
			InstanceExpression[] instanceExpressions) {
		this.getContentPane().removeAll();
		// removeAll();
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());

		if (instanceExpressions != null)
			this.instanceExpressions = instanceExpressions;

		for (InstanceExpression instanceExpression : this.instanceExpressions) {

			if (instanceExpressions != null)
				selectedExpression = instanceExpression;

			solutionPanel = new JPanel();
			solutionPanel.setAutoscrolls(true);
			solutionPanel.setMaximumSize(new Dimension(900, 200));
			showExpression(instanceExpression, element, solutionPanel,
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

		SpringUtilities.makeCompactGrid(panel, this.instanceExpressions.length,
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

	private void showExpression(final InstanceExpression instanceExpression,
			final InstElement element, JPanel parentPanel,
			int topExpressionType, int color) {
		final InstElement ele = element;
		final InstanceExpression exp = instanceExpression;

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
		JComboBox<String> leftSide = createSidesCombo(instanceExpression,
				element, true);
		JComboBox<String> rightSide = createSidesCombo(instanceExpression,
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
		if (selectedExpression == instanceExpression) {
			leftPanel.setBorder(raisedbevel);
			basePanel.add(leftSide);
		}
		if (instanceExpression.getLeftExpressionType() != null)
			switch (instanceExpression.getLeftExpressionType()) {
			case LEFTSUBEXPRESSION:
				leftSide.setSelectedItem("SubExpression");
				break;
			case LEFT:
				leftSide.setSelectedItem("Variable");
				break;
			case LEFTNUMERICEXPRESSIONVALUE:
				leftSide.setSelectedItem("Number");
				break;
			case LEFTVARIABLEVALUE:
				leftSide.setSelectedItem("VariableValue");
				break;
			default:
			}

		if (instanceExpression.getRightExpressionType() != null)
			switch (instanceExpression.getRightExpressionType()) {
			case RIGHTSUBEXPRESSION:
				rightSide.setSelectedItem("SubExpression");
				break;
			case RIGHT:
				rightSide.setSelectedItem("Variable");
				break;
			case RIGHTNUMERICEXPRESSIONVALUE:
				rightSide.setSelectedItem("Number");
				break;
			case RIGHTVARIABLEVALUE:
				rightSide.setSelectedItem("VariableValue");
				break;
			default:
			}
		if (leftSide.getSelectedItem().equals("SubExpression")) {
			if (instanceExpression.getSemanticExpression().getSemanticExpressionType() != null) {
				if (instanceExpression.getLeftInstanceExpression() == null)
					instanceExpression.setLeftInstanceExpression(
							ExpressionVertexType.LEFTSUBEXPRESSION, null, "id");
				instanceExpression
						.setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
				showExpression(instanceExpression.getLeftInstanceExpression(),
						element, leftPanel,
						instanceExpression.getLeftValidExpressions(),
						color > 20 ? color - 20 : color > 5 ? color - 5 : color);
			}
		}
		if (leftSide.getSelectedItem().equals("Number")) {
			if (instanceExpression.getLeftExpressionType() != null)
				if (instanceExpression.getLeftExpressionType().equals(
						ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE)) {
					basePanel.add(createTextField(instanceExpression, element,
							ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE));
				}
		}
		if (leftSide.getSelectedItem().equals("Variable")) {
			{
				if (instanceExpression.getSemanticExpression()
						.getSemanticExpressionType() != null) {
					leftPanel.add(createVarCombo(instanceExpression, element,
							ExpressionVertexType.LEFT,
							instanceExpression.getLeftValidExpressions()));
					instanceExpression
							.setLeftExpressionType(ExpressionVertexType.LEFT);
				}
			}
		}
		if (leftSide.getSelectedItem().equals("VariableValue")) {
			{
				if (instanceExpression.getSemanticExpression()
						.getSemanticExpressionType() != null) {
					leftPanel.add(createVarCombo(instanceExpression, element,
							ExpressionVertexType.LEFTVARIABLEVALUE,
							instanceExpression.getLeftValidExpressions()));
					instanceExpression
							.setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
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
				instanceExpression, element, instanceExpression.getOperation(),
				topExpressionType);
		if (selectedExpression == instanceExpression) {
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
		if (!instanceExpression.isSingleInExpression()) {
			if (selectedExpression == instanceExpression) {
				rightPanel.setBorder(raisedbevel);
				basePanel.add(rightSide);
			}
			if (rightSide.getSelectedItem().equals("SubExpression")) {
				if (instanceExpression.getSemanticExpression()
						.getSemanticExpressionType() != null) {
					if (instanceExpression.getRightInstanceExpression() == null)
						instanceExpression.setRightInstanceExpression(
								ExpressionVertexType.RIGHTSUBEXPRESSION, null,
								"id");
					showExpression(
							instanceExpression.getRightInstanceExpression(),
							element, rightPanel,
							instanceExpression.getRightValidExpressions(),
							color > 20 ? color - 20 : color > 5 ? color - 5
									: color);
					instanceExpression
							.setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
				}
			}
			if (rightSide.getSelectedItem().equals("Number")) {
				if (instanceExpression.getRightExpressionType() != null)
					if (instanceExpression.getRightExpressionType().equals(
							ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE)) {
						rightPanel
								.add(createTextField(
										instanceExpression,
										element,
										ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE));
					}
			}
			if (rightSide.getSelectedItem().equals("Variable")) {
				if (instanceExpression.getSemanticExpression()
						.getSemanticExpressionType() != null) {
					rightPanel.add(createVarCombo(instanceExpression, element,
							ExpressionVertexType.RIGHT,
							instanceExpression.getRightValidExpressions()));
					instanceExpression
							.setRightExpressionType(ExpressionVertexType.RIGHT);
				}
			}
			if (rightSide.getSelectedItem().equals("VariableValue")) {
				if (instanceExpression.getSemanticExpression()
						.getSemanticExpressionType() != null) {
					rightPanel.add(createVarCombo(instanceExpression, element,
							ExpressionVertexType.RIGHTVARIABLEVALUE,
							instanceExpression.getRightValidExpressions()));
					instanceExpression
							.setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);
				}
			}
		}

		basePanel.add(rightPanel);
		parentPanel.add(basePanel);
	}

	private JTextField createTextField(
			final InstanceExpression instanceExpression,
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

	private JComboBox<String> createVarCombo(
			final InstanceExpression instanceExpression,
			final InstElement element,
			final ExpressionVertexType expressionVertexType, int validType) {
		if (instanceExpression.getSideElement(expressionVertexType) == null)
		{
			String id = instanceExpression.getSideElementIdentifier(expressionVertexType);
			instanceExpression.setInstElement(refasModel.getVertex(id),
					expressionVertexType);
		}
		JComboBox<String> identifiers = null;
		String varIdentifier = null;
		varIdentifier = instanceExpression
				.getElementAttributeIdentifier(expressionVertexType);
		if (varIdentifier == null)
		{
			varIdentifier = instanceExpression.getSideElementIdentifier(expressionVertexType);
			InstElement instVertex = refasModel.getVertex(varIdentifier);
			instanceExpression.setInstElement(instVertex,expressionVertexType);
		}
		identifiers = createIdentifiersCombo(expressionVertexType, element,
				varIdentifier);
		identifiers.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if (item != null) {
						String[] split = item.split("_");
						instanceExpression.setInstElement(
								refasModel.getVertex(split[0]),
								expressionVertexType);
						instanceExpression.setAttributeName(split[1],
								expressionVertexType);
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

	private JComboBox<String> createIdentifiersValueCombo(InstElement element,
			String selectedElement) {
		JComboBox<String> combo = new JComboBox<String>();
		for (InstElement instVertex : refasModel
				.getVariabilityVertexCollection()) {
			IntSemanticElement semElement2 = ((MetaVertex) instVertex
					.getTransSupportMetaElement()).getTransSemanticConcept();
			if (semElement2 != null
					&& semElement2.getIdentifier().equals("Variable")) {
				String variableType = (String) instVertex.getInstAttribute(
						SemanticVariable.VAR_VARIABLETYPE).getValue();
				switch (variableType) {
				case "Boolean":
					combo.addItem(instVertex.getIdentifier() + "_" + "true");
					combo.addItem(instVertex.getIdentifier() + "_" + "false");
					break;
				case "Integer":
					String domain = (String) instVertex.getInstAttribute(
							SemanticVariable.VAR_VARIABLEDOMAIN).getValue();
					String split[] = domain.split(",");
					for (String dom : split) {
						combo.addItem(instVertex.getIdentifier() + "_" + dom);

					}
					break;
				case "Enumeration":
					Object object = instVertex.getInstAttribute(
							"enumerationType").getValueObject();
					if (object != null) {
						Set<InstAttribute> values = (Set<InstAttribute>) ((InstAttribute) ((InstEnumeration) object)
								.getInstAttribute("value")).getValue();
						for (InstAttribute value : values)
							combo.addItem(instVertex.getIdentifier() + "_"
									+ value.getValue());
					}
					break;

				}
			}
		}
		combo.setSelectedItem(selectedElement);
		return combo;
	}

	private JComboBox<String> createIdentifiersCombo(ExpressionVertexType type,
			InstElement element, String selectedElement) {
		JComboBox<String> combo = new JComboBox<String>();

		if (type == ExpressionVertexType.LEFT
				|| type == ExpressionVertexType.RIGHT) {
			for (InstElement instVertex : refasModel
					.getVariabilityVertexCollection()) {
				IntSemanticElement semElement2 = ((MetaVertex) instVertex
						.getTransSupportMetaElement())
						.getTransSemanticConcept();
				if (semElement2 != null
						&& semElement2.getIdentifier().equals("Variable")) {

					combo.addItem(instVertex.getIdentifier() + "_" + "value");
				}
			}
		} else if (type == ExpressionVertexType.LEFTVARIABLEVALUE
				|| type == ExpressionVertexType.RIGHTVARIABLEVALUE) {
			return createIdentifiersValueCombo(element, selectedElement);
		} else

		{
			if (element instanceof InstConcept)
				for (String attributeName : element.getInstAttributes()
						.keySet())
					combo.addItem(element.getIdentifier() + "_" + attributeName);

			if (element instanceof InstPairwiseRelation) {
				for (String attributeName : ((InstPairwiseRelation) element)
						.getSourceRelations().get(0).getInstAttributes()
						.keySet())
					combo.addItem(((InstPairwiseRelation) element)
							.getSourceRelations().get(0).getIdentifier()
							+ "_" + attributeName);
				for (String attributeName : ((InstPairwiseRelation) element)
						.getTargetRelations().get(0).getInstAttributes()
						.keySet())
					combo.addItem(((InstPairwiseRelation) element)
							.getTargetRelations().get(0).getIdentifier()
							+ "_" + attributeName);
				for (String attributeName : element.getInstAttributes()
						.keySet())
					combo.addItem(element.getIdentifier() + "_" + attributeName);
			}

			if (element instanceof InstOverTwoRelation) {
				if (((InstOverTwoRelation) element).getTargetRelations().size() > 0)
					for (String attributeName : ((InstPairwiseRelation) ((InstOverTwoRelation) element)
							.getTargetRelations().get(0)).getTargetRelations()
							.get(0).getInstAttributes().keySet())
						combo.addItem(((InstPairwiseRelation) ((InstOverTwoRelation) element)
								.getTargetRelations().get(0))
								.getTargetRelations().get(0).getIdentifier()
								+ "_" + attributeName);
				for (InstElement sourceRelation : ((InstOverTwoRelation) element)
						.getSourceRelations())
					for (String attributeName : ((InstPairwiseRelation) sourceRelation)
							.getSourceRelations().get(0).getInstAttributes()
							.keySet())
						combo.addItem(((InstPairwiseRelation) sourceRelation)
								.getSourceRelations().get(0).getIdentifier()
								+ "_" + attributeName);
				for (String attributeName : element.getInstAttributes()
						.keySet())
					combo.addItem(element.getIdentifier() + "_" + attributeName);
			}
		}
		combo.setSelectedItem(selectedElement);
		return combo;
	}

	private JComboBox<String> createOperatorsCombo(
			final InstanceExpression instanceExpression,
			final InstElement element, String selectedOperator,
			int topExpressionType) {
		JComboBox<String> combo = new JComboBox<String>();
		List<SemanticExpressionType> semanticExpressionTypes = SemanticExpressionType
				.getValidSemanticExpressionTypes(refasModel
						.getSemanticExpressionTypes().values(), topExpressionType);

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
			final InstanceExpression instanceExpression,
			final InstElement element, final boolean left) {
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("Variable");
		combo.addItem("SubExpression");
		combo.addItem("Number");
		combo.addItem("VariableValue");
		combo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if ((!item.equals(instanceExpression.getLastLeft()) && left)
							|| (!item.equals(instanceExpression.getLastRight()) && !left)) {
						if (left)
							instanceExpression.setLastLeft(item);
						if (!left)
							instanceExpression.setLastRight(item);
						switch (item) {
						case "SubExpression":
							if (left)
								instanceExpression
										.setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
							else
								instanceExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
							break;
						case "Variable":
							if (left)
								instanceExpression
										.setLeftExpressionType(ExpressionVertexType.LEFT);
							else
								instanceExpression
										.setRightExpressionType(ExpressionVertexType.RIGHT);
							break;
						case "Number":
							if (left)
								instanceExpression
										.setLeftExpressionType(ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE);
							else
								instanceExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE);
							break;
						case "VariableValue":
							if (left)
								instanceExpression
										.setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
							else
								instanceExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);
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
	public InstanceExpression[] getExpressions() {
		return instanceExpressions;
	}

	public void center() {
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setOnAccept(InstanceExpressionButtonAction onAccept) {
		this.onAccept = onAccept;
	}

	public void setOnCancel(InstanceExpressionButtonAction onCancel) {
		this.onCancel = onCancel;
	}

}
