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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstOverTwoRel;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.model.ModelExpr;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersElement;
import com.variamos.dynsup.model.OpersExprType;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.types.ExpressionVertexType;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.PerspEditorGraph;
import com.variamos.gui.perspeditor.SpringUtilities;
import com.variamos.hlcl.Domain;
import com.variamos.hlcl.DomainParser;
import com.variamos.hlcl.Expression;

/**
 * @author unknown
 *
 */
@SuppressWarnings("serial")
public class InstanceExpressionDialog extends JDialog {
	private List<ModelExpr> instanceExpressions;
	private InstanceExpressionButtonAction onAccept, onCancel, onDelete;
	private ModelExpr selectedExpression;
	private JPanel solutionPanel;
	private ModelInstance refasModel;
	private boolean displayVariableName = true;
	private int width = 950;
	private int height = 400;
	private boolean multiExpressions;
	private boolean displayTextExpression = false;
	private boolean editable;
	private InstElement sourceElement;

	static interface InstanceExpressionButtonAction {
		public boolean onAction();
	}

	public InstanceExpressionDialog(VariamosGraphEditor editor,
			InstElement instElement, boolean multiExpression,
			List<ModelExpr> instanceExpressions, boolean editable) {
		super(editor.getFrame(), "Expressions Editor");
		this.multiExpressions = multiExpression;
		this.editable = editable;
		refasModel = editor.getEditedModel();
		setPreferredSize(new Dimension(width, height));
		this.initialize(instElement, instanceExpressions);
		sourceElement = instElement;
	}

	public void initialize(final InstElement element,
			List<ModelExpr> instanceExpressions) {
		this.getContentPane().removeAll();
		// removeAll();
		if (this.getWidth() != 0)
			width = this.getWidth();
		if (this.getHeight() != 0)
			height = this.getHeight();

		this.setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());

		if (instanceExpressions != null)
			this.instanceExpressions = instanceExpressions;

		for (final ModelExpr instanceExpression : this.instanceExpressions) {

			if (instanceExpressions != null)
				selectedExpression = instanceExpression;

			solutionPanel = new JPanel();
			solutionPanel.setAutoscrolls(true);
			solutionPanel.setMaximumSize(new Dimension(900, 200));
			showExpression(instanceExpression, element, solutionPanel,
					OpersExprType.BOOLEXP, 255);

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
			if (displayTextExpression) {
				JPanel textExpression = new JPanel();
				JTextArea textTextualExpression;
				try {
					Expression exp = instanceExpression
							.createSGSExpression(element.getIdentifier());
					textTextualExpression = new JTextArea(exp.toString());

				} catch (Exception e) {

					textTextualExpression = new JTextArea(
							"Expression Incomplete or Invalid");

				}
				textTextualExpression.setWrapStyleWord(true);
				textTextualExpression.setPreferredSize(new Dimension(this
						.getWidth() - 50, this.height / 3));
				textTextualExpression.setEditable(false);
				textTextualExpression.setAutoscrolls(true);
				textTextualExpression.setLineWrap(true);
				textTextualExpression.setRows(4);
				textExpression.add(textTextualExpression);
				// JPanel txtPanel = new JPanel();
				JScrollPane sp = new JScrollPane(textExpression);
				sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				sp.setPreferredSize(new Dimension(this.getWidth() - 50, 100));
				// txtPanel.add(textExpression);
				// txtPanel.setPreferredSize(new Dimension(this
				// .getWidth() - 30, 100));
				panel.add(textExpression);
			} else
				panel.add(new JLabel());

		}
		JPanel options = new JPanel();
		JCheckBox conceptNamesCheck = new JCheckBox("Display Text Expression ");
		if (displayTextExpression)
			conceptNamesCheck.setSelected(true);
		options.add(conceptNamesCheck);
		conceptNamesCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				displayTextExpression = !displayTextExpression;
				new Thread() {
					@Override
					public void run() {
						initialize(element, null);
					}
				}.start();
				revalidate();
				repaint();
			}
		});
		JCheckBox varNamesCheck = new JCheckBox(
				"Display Variable User Identifiers (not Auto Identifiers)");
		// varNamesCheck.setEnabled(false);
		if (displayVariableName)
			varNamesCheck.setSelected(true);
		options.add(varNamesCheck);
		varNamesCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				displayVariableName = !displayVariableName;

				new Thread() {
					@Override
					public void run() {
						initialize(element, null);
					}
				}.start();
				revalidate();
				repaint();
			}
		});
		if (multiExpressions) {
			final List<ModelExpr> finalInstanceExpressions = instanceExpressions;
			JButton addButton = new JButton("Add new Instance Expression");
			addButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					finalInstanceExpressions.add(new ModelExpr());

					new Thread() {
						@Override
						public void run() {
							initialize(element, null);
						}
					}.start();
					revalidate();
					repaint();
				}
			});
			options.add(addButton);
		}
		panel.add(options);
		SpringUtilities.makeCompactGrid(panel,
				this.instanceExpressions.size() * 2 + 1, 1, 4, 4, 4, 4);

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

		final JButton btnDelete = new JButton();
		btnDelete.setText("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (onDelete == null) {
					dispose();
					return;
				}
				if (onDelete.onAction())
					dispose();
			}
		});

		buttonsPanel.add(btnDelete);

		SpringUtilities.makeCompactGrid(buttonsPanel, 1, 3, 4, 4, 4, 4);

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

	public void createRelations(VariamosGraphEditor editor) {

		PerspEditorGraph refasGraph = ((PerspEditorGraph) editor
				.getGraphComponent().getGraph());
		List<InstElement> concepts = instanceExpressions.get(0).getConcepts();

		for (InstPairwiseRel e : refasModel.getConstraintInstEdges().values()) {
			if (e.getSupInstEleId().equals("Test-Cl-Var")
					&& e.getSourceRelations().get(0).equals(sourceElement)) {
				refasGraph.removeEdge(e);
				refasModel.removeElement(e);
			}
		}

		int i = 0;
		for (InstElement concept : concepts) {
			InstPairwiseRel directRelation = new InstPairwiseRel();
			directRelation.setIdentifier(sourceElement.getIdentifier() + i++);
			directRelation.setTargetRelation(concept, true);
			directRelation.setSourceRelation(sourceElement, true);
			InstElement metaPairwiseRelNormal = refasModel.getSyntaxModel()
					.getElement("Test-Cl-Var");
			directRelation
					.setSupportMetaPairwiseRelation(metaPairwiseRelNormal);
			directRelation.setSupSyntaxEleId("Test-Cl-Var");
			directRelation.setSupInstEleId("Test-Cl-Var");
			this.refasModel.addNewConstraintInstEdge(directRelation);
			refasGraph.createNewEdge(directRelation);
		}
	}

	private void showExpression(final ModelExpr instanceExpression,
			final InstElement element, JPanel parentPanel,
			int topExpressionType, int color) {
		final InstElement ele = element;
		final ModelExpr exp = instanceExpression;

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
					@Override
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
					@Override
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
			case LEFTVARIABLE:
				leftSide.setSelectedItem("Variable");
				break;
			case LEFTNUMERICVALUE:
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
			case RIGHTVARIABLE:
				rightSide.setSelectedItem("Variable");
				break;
			case RIGHTNUMERICVALUE:
				rightSide.setSelectedItem("Number");
				break;
			case RIGHTVARIABLEVALUE:
				rightSide.setSelectedItem("VariableValue");
				break;
			default:
			}
		if (leftSide.getSelectedItem().equals("SubExpression")) {
			if (instanceExpression.getSemanticExpression()
					.getSemanticExpressionType() != null) {
				if (instanceExpression.getLeftInstanceExpression() == null)
					instanceExpression.setLeftInstanceExpression(
							ExpressionVertexType.LEFTSUBEXPRESSION, null,
							instanceExpression.getSemanticExpressionId()
									+ "Sub", -1);
				showExpression(instanceExpression.getLeftInstanceExpression(),
						element, leftPanel,
						instanceExpression.getLeftValidExpressions(),
						color > 20 ? color - 20 : color > 5 ? color - 5 : color);
				instanceExpression
						.setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
			}
		}
		if (leftSide.getSelectedItem().equals("Number")) {
			if (instanceExpression.getLeftExpressionType() != null)
				if (instanceExpression.getLeftExpressionType().equals(
						ExpressionVertexType.LEFTNUMERICVALUE)) {
					basePanel.add(createTextField(instanceExpression, element,
							ExpressionVertexType.LEFTNUMERICVALUE));
				}
		}
		if (leftSide.getSelectedItem().equals("Variable")) {
			{
				if (instanceExpression.getSemanticExpression()
						.getSemanticExpressionType() != null) {
					leftPanel.add(createVarCombo(instanceExpression, element,
							ExpressionVertexType.LEFTVARIABLE,
							instanceExpression.getLeftValidExpressions()));
					instanceExpression
							.setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
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
					@Override
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
					@Override
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
								instanceExpression.getSemanticExpressionId()
										+ "Sub", -1);
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
							ExpressionVertexType.RIGHTNUMERICVALUE)) {
						rightPanel
								.add(createTextField(instanceExpression,
										element,
										ExpressionVertexType.RIGHTNUMERICVALUE));
					}
			}
			if (rightSide.getSelectedItem().equals("Variable")) {
				if (instanceExpression.getSemanticExpression()
						.getSemanticExpressionType() != null) {
					rightPanel.add(createVarCombo(instanceExpression, element,
							ExpressionVertexType.RIGHTVARIABLE,
							instanceExpression.getRightValidExpressions()));
					instanceExpression
							.setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
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

	private JTextField createTextField(final ModelExpr instanceExpression,
			final InstElement element,
			final ExpressionVertexType expressionVertexType) {
		JTextField textField;

		if (expressionVertexType.equals(ExpressionVertexType.LEFTNUMERICVALUE))
			textField = new JTextField(""
					+ (instanceExpression).getLeftNumber());
		else
			textField = new JTextField(""
					+ (instanceExpression).getRightNumber());
		if (!editable)
			textField.setEnabled(false);
		textField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent event) {
				selectedExpression = instanceExpression;
				String item = ((JTextField) event.getSource()).getText();
				if (item != null) {
					if (expressionVertexType
							.equals(ExpressionVertexType.LEFTNUMERICVALUE))
						instanceExpression.setLeftNumber(Integer.parseInt(item));
					else
						instanceExpression.setRightNumber(Integer
								.parseInt(item));
				}
			}
		});
		return textField;
	}

	private JComboBox<String> createVarCombo(
			final ModelExpr instanceExpression, final InstElement element,
			final ExpressionVertexType expressionVertexType, int validType) {
		if (instanceExpression.getSideElement(expressionVertexType) == null) {
			String id = instanceExpression.getSideElementIdentifier(
					expressionVertexType, false);
			instanceExpression.setInstElement(refasModel.getVertex(id),
					expressionVertexType);
		}
		JComboBox<String> identifierList = null;
		final Map<String, InstElement> identifierTree = new HashMap<String, InstElement>();
		String varIdentifier = null;
		varIdentifier = instanceExpression.getElementAttributeIdentifier(
				expressionVertexType, displayVariableName);
		if (varIdentifier == null) {
			varIdentifier = instanceExpression.getSideElementIdentifier(
					expressionVertexType, false);
			InstElement instVertex = refasModel.getVertex(varIdentifier);
			instanceExpression.setInstElement(instVertex, expressionVertexType);
		}
		identifierList = createIdentifiersCombo(expressionVertexType, element,
				varIdentifier, identifierTree);
		identifierList.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				selectedExpression = instanceExpression;
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String itemName[] = ((String) event.getItem()).split("_");

					String item = identifierTree.get(itemName[0])
							.getIdentifier();
					if (item != null) {
						// String[] split = item.split("_");
						instanceExpression.setInstElement(
								refasModel.getVertex(item),
								expressionVertexType);
						instanceExpression.setAttributeName(itemName[1],
								expressionVertexType);
						new Thread() {
							@Override
							public void run() {
								initialize(element, null);
							}
						}.start();
					}
				}
			}

		});
		return identifierList;
	}

	private JComboBox<String> createIdentifiersValueCombo(InstElement element,
			String selectedElement, Map<String, InstElement> identifiersList) {
		JComboBox<String> combo = new JComboBox<String>();
		if (!editable)
			combo.setEnabled(false);
		for (InstElement instVertex : refasModel
				.getVariabilityVertexCollection()) {
			String instElementId = null;
			if (displayVariableName) {
				if (instVertex.getInstAttribute("userId") != null)
					instElementId = instVertex.getInstAttribute("userId")
							.toString();
				else
					instElementId = instVertex.getIdentifier();

			} else {
				instElementId = instVertex.getIdentifier();

			}

			OpersElement semElement2 = instVertex.getTransSupportMetaElement()
					.getTransSemanticConcept();
			List<InstElement> parents = instVertex.getTransSupportMetaElement()
					.getTransInstSemanticElement().getParentOpersConcept();
			boolean child = false;
			for (InstElement e : parents)
				if (e.getIdentifier().equals("NmVariable"))
					child = true;
			if (semElement2 != null
					&& ((semElement2.getIdentifier() != null && semElement2
							.getIdentifier().equals("NmVariable")) || child)) {
				String variableType = (String) instVertex.getInstAttribute(
						"variableType").getValue();
				switch (variableType) {
				case "Boolean":
					combo.addItem(instElementId + "_" + "true");
					combo.addItem(instElementId + "_" + "false");
					identifiersList.put(instElementId, instVertex);
					break;
				case "Integer":
					String domain = (String) instVertex.getInstAttribute(
							"varDom").getValue();
					Domain dom = (DomainParser.parseDomain(domain, 0));
					List<Integer> intValues = dom.getPossibleValues();
					for (Integer intValue : intValues) {
						combo.addItem(instElementId + "_" + intValue.intValue());
					}
					identifiersList.put(instElementId, instVertex);
					break;
				case "Float":
					domain = (String) instVertex.getInstAttribute("floatDom")
							.getValue();
					dom = (DomainParser.parseDomain(domain, (int) instVertex
							.getInstAttribute("floatPrec").getValue()));
					List<Float> floatValues = dom.getPossibleFloatValues();
					for (Float intValue : floatValues) {
						combo.addItem(instElementId + "_"
								+ intValue.floatValue());
					}
					identifiersList.put(instElementId, instVertex);
					break;
				case "Enumeration":
					Object object = instVertex.getInstAttribute("enumType")
							.getValueObject();
					if (object != null) {
						@SuppressWarnings("unchecked")
						Collection<InstAttribute> values = (Collection<InstAttribute>) ((InstElement) object)
								.getInstAttribute(
										SyntaxElement.VAR_METAENUMVALUE)
								.getValue();
						for (InstAttribute value : values)
							combo.addItem(instElementId + "_"
									+ value.getValue());
					}
					identifiersList.put(instElementId, instVertex);
					break;

				}
			}
		}
		combo.setSelectedItem(selectedElement);
		return combo;
	}

	private JComboBox<String> createIdentifiersCombo(ExpressionVertexType type,
			InstElement element, String selectedElement,
			Map<String, InstElement> identifiersList) {
		JComboBox<String> combo = new JComboBox<String>();
		String instElementId = null, instRelElementId = null;
		if (displayVariableName) {
			instElementId = element.getInstAttribute("userId").toString();
			if (element instanceof InstPairwiseRel
					|| element.getTransSupportMetaElement().getType() == 'P')
				instRelElementId = ((InstPairwiseRel) element)
						.getSourceRelations().get(0).getInstAttribute("userId")
						.toString();
			if (element instanceof InstOverTwoRel
					|| element.getTransSupportMetaElement().getType() == 'O'
					|| element.getTransSupportMetaElement().getType() == 'T')
				instRelElementId = ((InstPairwiseRel) ((InstOverTwoRel) element)
						.getTargetRelations().get(0)).getTargetRelations()
						.get(0).getInstAttribute("userId").toString();
		} else {
			instElementId = element.getIdentifier();
			if (element instanceof InstPairwiseRel
					|| element.getTransSupportMetaElement().getType() == 'P')
				instRelElementId = ((InstPairwiseRel) element)
						.getSourceRelations().get(0).getIdentifier();
			if (element instanceof InstOverTwoRel
					|| element.getTransSupportMetaElement().getType() == 'O'
					|| element.getTransSupportMetaElement().getType() == 'T')
				instRelElementId = ((InstPairwiseRel) ((InstOverTwoRel) element)
						.getTargetRelations().get(0)).getTargetRelations()
						.get(0).getIdentifier();
		}
		if (!editable)
			combo.setEnabled(false);
		if (type == ExpressionVertexType.LEFTVARIABLE
				|| type == ExpressionVertexType.RIGHTVARIABLE) {
			for (InstElement instVertex : refasModel
					.getVariabilityVertexCollection()) {
				OpersElement semElement2 = instVertex
						.getTransSupportMetaElement().getTransSemanticConcept();
				List<InstElement> parents = instVertex
						.getTransSupportMetaElement()
						.getTransInstSemanticElement().getParentOpersConcept();
				boolean child = false;
				for (InstElement e : parents)
					if (e.getIdentifier().equals("NmVariable"))
						child = true;
				if (semElement2 != null
						&& ((semElement2.getIdentifier() != null && semElement2
								.getIdentifier().equals("NmVariable")) || child)) {
					String instVertexId = null;
					if (displayVariableName)
						instVertexId = instVertex.getInstAttribute("userId")
								.toString();
					else
						instVertexId = instVertex.getIdentifier();
					identifiersList.put(instVertexId, instVertex);
					combo.addItem(instVertexId + "_" + "value");
				}
			}
		} else if (type == ExpressionVertexType.LEFTVARIABLEVALUE
				|| type == ExpressionVertexType.RIGHTVARIABLEVALUE) {
			return createIdentifiersValueCombo(element, selectedElement,
					identifiersList);
		} else

		{
			if (element instanceof InstConcept)
				for (String attributeName : element.getInstAttributes()
						.keySet())
					combo.addItem(instElementId + "_" + attributeName);

			if (element instanceof InstPairwiseRel) {
				for (String attributeName : ((InstPairwiseRel) element)
						.getSourceRelations().get(0).getInstAttributes()
						.keySet())
					combo.addItem(instRelElementId + "_" + attributeName);
				for (String attributeName : ((InstPairwiseRel) element)
						.getTargetRelations().get(0).getInstAttributes()
						.keySet())
					combo.addItem(instRelElementId + "_" + attributeName);
				for (String attributeName : element.getInstAttributes()
						.keySet())
					combo.addItem(instElementId + "_" + attributeName);
			}

			if (element instanceof InstOverTwoRel) {
				if (((InstOverTwoRel) element).getTargetRelations().size() > 0)
					for (String attributeName : ((InstPairwiseRel) ((InstOverTwoRel) element)
							.getTargetRelations().get(0)).getTargetRelations()
							.get(0).getInstAttributes().keySet())
						combo.addItem(instRelElementId + "_" + attributeName);
				for (InstElement sourceRelation : ((InstOverTwoRel) element)
						.getSourceRelations())
					for (String attributeName : ((InstPairwiseRel) sourceRelation)
							.getSourceRelations().get(0).getInstAttributes()
							.keySet())
						if (displayVariableName)
							combo.addItem(((InstPairwiseRel) sourceRelation)
									.getSourceRelations().get(0)
									.getInstAttribute("userId").toString()
									+ "_" + attributeName);
						else
							combo.addItem(((InstPairwiseRel) sourceRelation)
									.getSourceRelations().get(0)
									.getIdentifier()
									+ "_" + attributeName);

				for (String attributeName : element.getInstAttributes()
						.keySet())
					combo.addItem(instElementId + "_" + attributeName);
			}
		}
		if (displayVariableName)
			combo.setSelectedItem(selectedElement);
		else
			combo.setSelectedItem(selectedElement);
		return combo;
	}

	private JComboBox<String> createOperatorsCombo(
			final ModelExpr instanceExpression, final InstElement element,
			String selectedOperator, int topExpressionType) {
		JComboBox<String> combo = new JComboBox<String>();
		if (!editable)
			combo.setEnabled(false);
		List<OpersExprType> semanticExpressionTypes = OpersExprType
				.getValidSemanticExpressionTypes(refasModel
						.getSemanticExpressionTypes().values(),
						topExpressionType);

		for (OpersExprType semanticExpressionType : semanticExpressionTypes) {
			combo.addItem(semanticExpressionType.getTextConnector());
		}
		combo.setSelectedItem(selectedOperator);
		combo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				selectedExpression = instanceExpression;
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if (item != null) {
						instanceExpression.setSemanticExpressionType(refasModel
								.getSemanticExpressionTypes().get(item));
					}
					new Thread() {
						@Override
						public void run() {
							initialize(element, null);
						}
					}.start();
				}
			}
		});
		return combo;
	}

	private JComboBox<String> createSidesCombo(
			final ModelExpr instanceExpression, final InstElement element,
			final boolean left) {
		JComboBox<String> combo = new JComboBox<String>();
		if (!editable)
			combo.setEnabled(false);
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
										.setLeftExpressionType(ExpressionVertexType.LEFTVARIABLE);
							else
								instanceExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTVARIABLE);
							break;
						case "Number":
							if (left)
								instanceExpression
										.setLeftExpressionType(ExpressionVertexType.LEFTNUMERICVALUE);
							else
								instanceExpression
										.setRightExpressionType(ExpressionVertexType.RIGHTNUMERICVALUE);
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
							@Override
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
	public List<ModelExpr> getExpressions() {
		return instanceExpressions;
	}

	public void center() {
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setOnAccept(InstanceExpressionButtonAction onAccept) {
		this.onAccept = onAccept;
	}

	public void setOnDelete(InstanceExpressionButtonAction onDelete) {
		this.onDelete = onDelete;
	}

	public void setOnCancel(InstanceExpressionButtonAction onCancel) {
		this.onCancel = onCancel;
	}
}
