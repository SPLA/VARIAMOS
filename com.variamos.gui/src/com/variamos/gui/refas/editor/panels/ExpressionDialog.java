package com.variamos.gui.refas.editor.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.pl.editor.SpringUtilities;
import com.variamos.refas.RefasModel;
import com.variamos.semantic.expressionsupport.InstanceExpression;
import com.variamos.semantic.expressionsupport.MetaExpressionType;
import com.variamos.semantic.semanticsupport.AbstractSemanticElement;
import com.variamos.semantic.semanticsupport.SemanticVariable;
import com.variamos.semantic.types.ExpressionVertexType;
import com.variamos.syntax.instancesupport.InstConcept;
import com.variamos.syntax.instancesupport.InstElement;
import com.variamos.syntax.instancesupport.InstOverTwoRelation;
import com.variamos.syntax.instancesupport.InstPairwiseRelation;
import com.variamos.syntax.instancesupport.InstVertex;
import com.variamos.syntax.metamodelsupport.MetaVertex;
import com.variamos.syntax.semanticinterface.IntSemanticElement;

/**
 * @author unknown
 *
 */
@SuppressWarnings("serial")
public class ExpressionDialog extends JDialog {
	private InstanceExpression[] instanceExpressions;
	private DialogButtonAction onAccept, onCancel;
	private InstanceExpression selectedExpression;
	private JPanel solutionPanel;
	private RefasModel refasModel;
	private String lastLeft = null;
	private String lastRight = null;

	static interface DialogButtonAction {
		public boolean onAction();
	}

	public ExpressionDialog(VariamosGraphEditor editor,
			InstElement instElement, boolean multiExpression,
			InstanceExpression... instanceExpressions) {
		super(editor.getFrame(), "Parameters");
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

			showExpression(instanceExpression, element, solutionPanel,
					MetaExpressionType.BOOL, 255);

			solutionPanel.addPropertyChangeListener("value",
					new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							revalidate();
							doLayout();
							pack();
						}
					});
			panel.add(solutionPanel);
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
		/*
		 * if
		 * (instanceExpression.getMetaExpression().getMetaExpressionType().getMethod
		 * ().equals("number")) { parentPanel.add(new JTextField("" +
		 * (instanceExpression).getNumber())); return; }
		 */
		JPanel childPanel = new JPanel();
		childPanel.setBorder(new EmptyBorder(1, 1, 1, 1));
		childPanel.setMaximumSize(new Dimension(800, 300));
		childPanel.setBackground(new Color(color, color, color));
		childPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				selectedExpression = exp;
				// System.out.println(exp.toString()+" selected");
				new Thread() {
					public void run() {
						initialize(ele, null);
					}
				}.start();
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
		leftSide.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if (!item.equals(lastLeft)) {
						lastLeft = item;
						switch (item) {
						case "SubExpression":
							instanceExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTSUBEXPRESSION);
							break;
						case "Variable":

							instanceExpression
									.setLeftExpressionType(ExpressionVertexType.LEFT);
							break;
						case "Number":
							instanceExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTVARIABLEVALUE);
							break;
						case "VariableValue":
							instanceExpression
									.setLeftExpressionType(ExpressionVertexType.LEFTNUMERICEXPRESSIONVALUE);
							break;
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
			}

		});

		JComboBox<String> rightSide = createSidesCombo();

		rightSide.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if (!item.equals(lastRight)) {
						lastRight = item;
						switch (item) {
						case "SubExpression":
							instanceExpression
									.setRightExpressionType(ExpressionVertexType.RIGHTSUBEXPRESSION);
							break;
						case "Variable":

							instanceExpression
									.setRightExpressionType(ExpressionVertexType.RIGHT);
							break;
						case "Number":
							instanceExpression
									.setRightExpressionType(ExpressionVertexType.RIGHTNUMERICEXPRESSIONVALUE);
							break;
						case "VariableValue":
							instanceExpression
									.setRightExpressionType(ExpressionVertexType.RIGHTVARIABLEVALUE);
							break;
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
			}

		});
		if (selectedExpression == instanceExpression) {
			childPanel.add(leftSide);
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
			}
		if (leftSide.getSelectedItem().equals("SubExpression")) {
			if (instanceExpression.getMetaExpression().getMetaExpressionType() != null) {
				if (instanceExpression.getLeftSubExpression() == null)
					instanceExpression.setLeftSubExpression(
							ExpressionVertexType.LEFTSUBEXPRESSION, null, "id");
				// leftSide.setSelectedItem("SubExpression");
				showExpression(instanceExpression.getLeftSubExpression(),
						element, childPanel,
						instanceExpression.getLeftValidExpressions(),
						color - 20);
			}

		}

		/*
		 * if (instanceExpression.getLeftComparativeExpression() != null) {
		 * leftSide.setSelectedItem("Number"); showComparativeExpression(
		 * instanceExpression.getLeftComparativeExpression(), childPanel); } if
		 * (instanceExpression.getLeftNumericExpression() != null) {
		 * leftSide.setSelectedItem("Number");
		 * showComparativeExpression(instanceExpression
		 * .getLeftNumericExpression(), childPanel); }
		 */
		if (leftSide.getSelectedItem().equals("Variable")) {
			{
				if (instanceExpression.getMetaExpression()
						.getMetaExpressionType() != null) {
					JComboBox<String> identifiers = null;
					if (instanceExpression.getLeftElement() != null)
						identifiers = createIdentifiersCombo(
								1,
								element,
								instanceExpression.getLeftElement()
										.getIdentifier()
										+ "_"
										+ instanceExpression
												.getLeftAttributeName());
					else
						identifiers = createIdentifiersCombo(1, element, null);
					childPanel.add(identifiers);
					identifiers.addItemListener(new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent event) {
							if (event.getStateChange() == ItemEvent.SELECTED) {
								String item = (String) event.getItem();
								if (item != null) {
									String[] split = item.split("_");
									instanceExpression
											.setLeftElement(refasModel
													.getVertex(split[0]));
									instanceExpression
											.setLeftAttributeName(split[1]);
									new Thread() {
										public void run() {
											initialize(element, null);
										}
									}.start();
									// revalidate();
									// pack();
								}
							}
						}

					});

				}
			}
		}
		System.out.println(leftSide.getSelectedItem());
		if (leftSide.getSelectedItem().equals("VariableValue")) {
			{
				if (instanceExpression.getMetaExpression()
						.getMetaExpressionType() != null) {
					JComboBox<String> identifiers = null;
					if (instanceExpression.getLeftElement() != null)
						identifiers = createIdentifiersValueCombo(
								element,
								instanceExpression.getLeftElement()
										.getIdentifier()
										+ "_"
										+ instanceExpression
												.getLeftAttributeName());
					else
						identifiers = createIdentifiersValueCombo(element, null);
					childPanel.add(identifiers);
					identifiers.addItemListener(new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent event) {
							if (event.getStateChange() == ItemEvent.SELECTED) {
								String item = (String) event.getItem();
								if (item != null) {
									String[] split = item.split("_");
									instanceExpression
											.setLeftElement(refasModel
													.getVertex(split[0]));
									instanceExpression
											.setLeftAttributeName(split[1]);
									new Thread() {
										public void run() {
											initialize(element, null);
										}
									}.start();
									// revalidate();
									// pack();
								}
							}
						}

					});

				}
			}
		}
		JComboBox<String> centerCombo = createOperatorsCombo(
				instanceExpression.getOperation(), topExpressionType);
		childPanel.add(centerCombo);
		centerCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if (item != null) {
						instanceExpression.setMetaExpressionType(refasModel
								.getMetaExpressionTypes().get(item));
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

		if (selectedExpression == instanceExpression) {
			childPanel.add(rightSide);
		}
		if (rightSide.getSelectedItem().equals("SubExpression")) {
			// rightSide.setSelectedItem("SubExpression");
			if (instanceExpression.getMetaExpression().getMetaExpressionType() != null) {
				if (instanceExpression.getRightSubExpression() == null)

					instanceExpression
							.setRightSubExpression(
									ExpressionVertexType.RIGHTSUBEXPRESSION,
									null, "id");
				showExpression(instanceExpression.getRightSubExpression(),
						element, childPanel,
						instanceExpression.getRightValidExpressions(),
						color - 20);
			}
		}
		/*
		 * if (instanceExpression.getRightComparativeExpression() != null){
		 * rightSide.setSelectedItem("Number"); showComparativeExpression(
		 * instanceExpression.getRightComparativeExpression(), childPanel); } if
		 * (instanceExpression.getRightNumericExpression() != null){
		 * rightSide.setSelectedItem("Number");
		 * showComparativeExpression(instanceExpression
		 * .getRightNumericExpression(), childPanel); }
		 */
		if (rightSide.getSelectedItem().equals("Variable")) {

			if (instanceExpression.getMetaExpression().getMetaExpressionType() != null) {
				JComboBox<String> identifiers = null;
				if (instanceExpression.getRightElement() != null)
					identifiers = createIdentifiersCombo(
							1,
							element,
							instanceExpression.getRightElement()
									.getIdentifier()
									+ "_"
									+ instanceExpression
											.getRightAttributeName());
				else
					identifiers = createIdentifiersCombo(1, element, null);
				childPanel.add(identifiers);
				identifiers.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent event) {
						if (event.getStateChange() == ItemEvent.SELECTED) {
							String item = (String) event.getItem();
							if (item != null) {
								String[] split = item.split("_");
								instanceExpression.setLeftElement(refasModel
										.getVertex(split[0]));
								instanceExpression
										.setLeftAttributeName(split[1]);
								new Thread() {
									public void run() {
										initialize(element, null);
									}
								}.start();
								// revalidate();
								// pack();
							}
						}
					}

				});
			}
		}
		if (color == 255) {
			JLabel lab = new JLabel("");
			lab.setPreferredSize(new Dimension(200, 10));
			lab.setMaximumSize(new Dimension(200, 10));
			childPanel.add(lab);
		}
		parentPanel.add(childPanel);
	}

	private JComboBox<String> createIdentifiersValueCombo(InstElement element,
			String selectedElement) {
		JComboBox<String> combo = new JComboBox<String>();

		IntSemanticElement semElement = ((MetaVertex) element
				.getTransSupportMetaElement()).getTransSemanticConcept();
		for (InstVertex instVertex : refasModel
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
					combo.addItem(instVertex.getIdentifier() + "_" + "0");
					combo.addItem(instVertex.getIdentifier() + "_" + "1");
					combo.addItem(instVertex.getIdentifier() + "_" + "2");
					combo.addItem(instVertex.getIdentifier() + "_" + "3");
					combo.addItem(instVertex.getIdentifier() + "_" + "4");
					break;
				case "Enumeration":
					combo.addItem(instVertex.getIdentifier() + "_" + "ToDo");

					break;

				}
			}
		}
		return combo;
	}

	private JComboBox<String> createIdentifiersCombo(int type,
			InstElement element, String selectedElement) {
		JComboBox<String> combo = new JComboBox<String>();

		if (type == 1) {
			IntSemanticElement semElement = ((MetaVertex) element
					.getTransSupportMetaElement()).getTransSemanticConcept();
			for (InstVertex instVertex : refasModel
					.getVariabilityVertexCollection()) {
				IntSemanticElement semElement2 = ((MetaVertex) instVertex
						.getTransSupportMetaElement())
						.getTransSemanticConcept();
				if (semElement2 != null
						&& semElement2.getIdentifier().equals("Variable")) {

					combo.addItem(instVertex.getIdentifier() + "_" + "value");
				}
			}
		} else {
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

	@SuppressWarnings("unchecked")
	private JComboBox<String> createOperatorsCombo(String selectedOperator,
			int topExpressionType) {
		JComboBox<String> combo = new JComboBox<String>();
		List<MetaExpressionType> metaExpressionTypes = MetaExpressionType
				.getValidMetaExpressionTypes(refasModel
						.getMetaExpressionTypes().values(), topExpressionType);

		for (MetaExpressionType metaExpressionType : metaExpressionTypes) {
			combo.addItem(metaExpressionType.getTextConnector());
		}
		combo.setSelectedItem(selectedOperator);
		return combo;
	}

	private JComboBox<String> createSidesCombo() {
		JComboBox<String> combo = new JComboBox<String>();

		combo.addItem("SubExpression");
		combo.addItem("Number");
		combo.addItem("Variable");
		combo.addItem("VariableValue");
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

	public void setOnAccept(DialogButtonAction onAccept) {
		this.onAccept = onAccept;
	}

	public void setOnCancel(DialogButtonAction onCancel) {
		this.onCancel = onCancel;
	}

}
