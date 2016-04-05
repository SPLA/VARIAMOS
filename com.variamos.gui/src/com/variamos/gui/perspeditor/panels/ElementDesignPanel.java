package com.variamos.gui.perspeditor.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.cfm.productline.AbstractElement;
import com.mxgraph.util.mxResources;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstCell;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstEnum;
import com.variamos.dynsup.instance.InstPairwiseRel;
import com.variamos.dynsup.interfaces.IntElemAttribute;
import com.variamos.dynsup.interfaces.IntInstElement;
import com.variamos.dynsup.interfaces.IntMetaExpression;
import com.variamos.dynsup.interfaces.IntOpersElement;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.dynsup.model.ModelExpr;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersAttribute;
import com.variamos.dynsup.model.SyntaxAttribute;
import com.variamos.dynsup.model.SyntaxConcept;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.model.SyntaxPairwiseRel;
import com.variamos.dynsup.model.SyntaxView;
import com.variamos.dynsup.types.OperationSubActionExecType;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.SpringUtilities;
import com.variamos.gui.perspeditor.panels.InstanceExpressionDialog.InstanceExpressionButtonAction;
import com.variamos.gui.perspeditor.panels.SemanticExpressionDialog.SemanticExpressionButtonAction;
import com.variamos.gui.perspeditor.widgets.MClassWidget;
import com.variamos.gui.perspeditor.widgets.MEnumerationWidget;
import com.variamos.gui.perspeditor.widgets.RefasWidgetFactory;
import com.variamos.gui.perspeditor.widgets.WidgetR;
import com.variamos.gui.pl.editor.widgets.WidgetPL;
import com.variamos.hlcl.LabelingOrder;

/**
 * A class to draw the first property tab. Part of PhD work at University of
 * Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2015-02-28*
 */
public class ElementDesignPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4322732010527019064L;
	private JPanel mainPanel = new JPanel(new SpringLayout());
	private JPanel contentPanel1 = new JPanel(new SpringLayout());
	private JPanel contentPanel2 = new JPanel(new SpringLayout());
	private JPanel contentPanel3 = new JPanel(new SpringLayout());
	private JPanel contentPanel4 = new JPanel(new SpringLayout());
	private JPanel rootPanel1 = new JPanel();
	private JPanel rootPanel2 = new JPanel(new SpringLayout());
	private JPanel rootPanel3 = new JPanel(new SpringLayout());
	private JPanel rootPanel4 = new JPanel(new SpringLayout());
	private int mainPanelWidth = 200;

	public ElementDesignPanel() {

		rootPanel1.add(contentPanel1);
		JPanel dummyP = new JPanel();
		dummyP.setMinimumSize(new Dimension(0, 0));
		dummyP.setMaximumSize(new Dimension(500, 300));
		// rootPanel1.add(dummyP);
		// SpringUtilities.makeCompactGrid(rootPanel1, 2, 1, 4, 4, 4, 4);

		rootPanel2.add(contentPanel2);
		dummyP = new JPanel();
		dummyP.setMinimumSize(new Dimension(300, 300));
		dummyP.setMaximumSize(new Dimension(500, 300));
		rootPanel2.add(dummyP);
		SpringUtilities.makeCompactGrid(rootPanel2, 2, 1, 4, 4, 4, 4);

		rootPanel3.add(contentPanel3);
		dummyP = new JPanel();
		dummyP.setMinimumSize(new Dimension(0, 0));
		dummyP.setMaximumSize(new Dimension(500, 300));
		rootPanel3.add(dummyP);
		SpringUtilities.makeCompactGrid(rootPanel3, 2, 1, 4, 4, 4, 4);

		rootPanel4.add(contentPanel4);
		dummyP = new JPanel();
		dummyP.setMinimumSize(new Dimension(0, 0));
		dummyP.setMaximumSize(new Dimension(500, 300));
		rootPanel4.add(dummyP);
		SpringUtilities.makeCompactGrid(rootPanel4, 2, 1, 4, 4, 4, 4);

		mainPanel.setBackground(Color.WHITE);
		setLayout(new SpringLayout());
		add(mainPanel);
		dummyP = new JPanel();
		dummyP.setMinimumSize(new Dimension(0, 0));
		dummyP.setMinimumSize(new Dimension(500, 0));
		add(dummyP);
		/*
		 * dummyP = new JPanel(); dummyP.setMinimumSize(new Dimension(0, 0));
		 * dummyP.setMinimumSize(new Dimension(500, 200)); add(dummyP); dummyP =
		 * new JPanel(); dummyP.setMinimumSize(new Dimension(0, 0));
		 * dummyP.setMinimumSize(new Dimension(500, 200)); add(dummyP);
		 */
		SpringUtilities.makeCompactGrid(this, 2, 1, 4, 4, 4, 4);

	}

	public void editorProperties(final VariamosGraphEditor editor,
			final InstCell instCell) {
		mainPanel.removeAll();
		mainPanelWidth = 350;
		JPanel elementDesPropSubPanel = null;
		final VariamosGraphEditor finalEditor = editor;

		// updateVisibleProperties(elm);

		contentPanel1.removeAll();
		contentPanel2.removeAll();
		contentPanel3.removeAll();
		contentPanel4.removeAll();
		int designPanelElements = 0;

		int extra = 0;
		if (instCell == null || instCell.getInstElement() == null) {
			return;
		} else {
			InstElement editElm = instCell.getInstElement();
			List<InstElement> syntaxParent = ((ModelInstance) editor
					.getEditedModel())
					.getParentSMMSyntaxElement((InstElement) editElm);

			editElm.getInstAttributes();
			final InstElement finalEditElm = (InstElement) editElm;
			RefasWidgetFactory factory = new RefasWidgetFactory(editor);

			String description = null;

			if (editElm instanceof InstPairwiseRel) {
				if (((InstPairwiseRel) editElm).getSourceRelations().size() == 0)
					// TODO workaround for non supported relations - delete
					// after fix
					return;
			}
			if (editElm instanceof InstElement) {
				if (((InstElement) editElm).getTransSupportMetaElement() != null)
					description = ((InstElement) editElm)
							.getTransSupportMetaElement().getDescription();
			}
			int count = 0;
			while (count < 2) {
				designPanelElements = 0;

				// Warning: Fix for Mac, do not delete it
				if (editElm instanceof InstPairwiseRel)
					designPanelElements++;

				elementDesPropSubPanel = new JPanel(new SpringLayout());
				Collection<InstAttribute> visible = editElm
						.getVisibleVariables(syntaxParent);
				if (((InstElement) editElm).getEditableSemanticElement() != null
						&& !((InstElement) editElm)
								.getTransSupportMetaElement().getName()
								.equals("InfraSyntaxOpersM2Labeling")) {
					elementDesPropSubPanel.add(new JLabel(
							"Semantic Expressions"));
					JButton button = new JButton(
							"Open Semantic Expressions Editor");
					if (editor.getPerspective() == 4)
						button.setEnabled(false);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							List<IntMetaExpression> ie = ((InstElement) finalEditElm)
									.getEditableSemanticElement()
									.getDeclaredSemanticExpressions();
							final SemanticExpressionDialog dialog = new SemanticExpressionDialog(
									finalEditor, finalEditElm, ie);
							dialog.center();
							dialog.setOnAccept(new SemanticExpressionButtonAction() {
								@Override
								public boolean onAction() {
									try {
										finalEditElm
												.getEditableSemanticElement()
												.setSemanticExpressions(
														dialog.getExpressions());

									} catch (Exception e) {
										JOptionPane
												.showMessageDialog(
														finalEditor,
														"Complete the expression before closing the editor",
														"Expression Error",
														JOptionPane.INFORMATION_MESSAGE,
														null);
										e.printStackTrace();
										return false;
									}

									// afterAction();
									return true;
								}
							});
						}
					});
					elementDesPropSubPanel.add(button);
					elementDesPropSubPanel.add(new JPanel());
					designPanelElements++;
				}
				if (((InstElement) editElm).getEditableSemanticElement() != null
						&& ((InstElement) editElm).getTransSupportMetaElement()
								.getName().equals("InfraSyntaxOpersM2Labeling")) {
					elementDesPropSubPanel.add(new JLabel(
							"Order Meta-Expressions"));
					JButton button = new JButton(
							"Open Order Meta-Expressions Editor");
					if (editor.getPerspective() == 4)
						button.setEnabled(false);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							List<IntMetaExpression> ie = ((InstElement) finalEditElm)
									.getEditableSemanticElement()
									.getDeclaredSemanticExpressions();
							final SemanticExpressionDialog dialog = new SemanticExpressionDialog(
									finalEditor, finalEditElm, ie);
							dialog.center();
							dialog.setOnAccept(new SemanticExpressionButtonAction() {
								@Override
								public boolean onAction() {
									try {
										finalEditElm
												.getEditableSemanticElement()
												.setSemanticExpressions(
														dialog.getExpressions());

									} catch (Exception e) {
										JOptionPane
												.showMessageDialog(
														finalEditor,
														"Complete the expressions before closing the editor",
														"Expression Error",
														JOptionPane.INFORMATION_MESSAGE,
														null);
										e.printStackTrace();
										return false;
									}

									// afterAction();
									return true;
								}
							});
						}
					});
					elementDesPropSubPanel.add(button);
					elementDesPropSubPanel.add(new JPanel());
					designPanelElements++;
				}
				if (visible != null)
					for (InstAttribute instAttribute : visible) {
						if (instAttribute != null
								&& (instAttribute.getAttribute() instanceof SyntaxAttribute || instAttribute
										.getAttribute() instanceof OpersAttribute)) {
							if (instAttribute.getIdentifier().equals(
									SyntaxConcept.VAR_USERIDENTIFIER)
									&& instAttribute.getValue() == null) {
								if (editElm instanceof InstPairwiseRel)
									instAttribute
											.setValue(editElm
													.getInstAttributes()
													.get(SyntaxConcept.VAR_AUTOIDENTIFIER)
													.getValue());
								else {
									InstAttribute name = editElm
											.getInstAttributes().get("name");
									if (name == null)
										name = editElm.getInstAttributes().get(
												"Name");
									if (name != null)
										instAttribute.setValue(name.getValue());
									else
										System.out.println("null editElm:"
												+ editElm.getIdentifier());
								}
							}
							final InstAttribute finalInstAttribute = instAttribute;
							Map<String, SyntaxElement> mapElements = null;
							if (editElm instanceof InstPairwiseRel) {
								InstPairwiseRel instPairwise = (InstPairwiseRel) editElm;
								mapElements = ((ModelInstance) editor
										.getEditedModel()).getSyntaxModel()
										.getValidPairwiseRelations(
												instPairwise
														.getSourceRelations()
														.get(0),
												instPairwise
														.getTargetRelations()
														.get(0));
							}
							instAttribute.updateValidationList(
									((InstElement) editElm), mapElements);

							if (instAttribute.getIdentifier().equals(
									"ConditionalExpression")) {
								JButton button = new JButton("Edit Expression");

								button.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										boolean editable = true;
										if (editor.getPerspective() == 4)
											editable = false;
										List<ModelExpr> ie = new ArrayList<ModelExpr>();
										;
										if ((ModelExpr) finalInstAttribute
												.getValue() != null)
											ie.add((ModelExpr) finalInstAttribute
													.getValue());
										else
											ie.add(new ModelExpr(true, "id",
													true));
										final InstanceExpressionDialog dialog = new InstanceExpressionDialog(
												finalEditor, finalEditElm,
												false, ie, editable);
										dialog.center();
										dialog.setOnAccept(new InstanceExpressionButtonAction() {
											@Override
											public boolean onAction() {
												finalInstAttribute
														.setValue(dialog
																.getExpressions()
																.get(0));
												try {
													if (!((ModelExpr) finalInstAttribute
															.getValue())
															.createSGSExpression(
																	finalEditElm
																			.getIdentifier())
															.isValidExpression()) {
														JOptionPane
																.showMessageDialog(
																		finalEditor,
																		"Complete/Correct the expression before closing the editor",
																		"Instance Expression Error",
																		JOptionPane.INFORMATION_MESSAGE,
																		null);
														return false;
													}
													// System.out.println(exp);
												} catch (Exception e) {
													JOptionPane
															.showMessageDialog(
																	finalEditor,
																	"Complete/Correct the expression before closing the editor",
																	"Instance Expression Error",
																	JOptionPane.INFORMATION_MESSAGE,
																	null);
													// e.printStackTrace();
													return false;
												}

												// afterAction();
												return true;
											}
										});
										dialog.setOnCancel(new InstanceExpressionButtonAction() {
											@Override
											public boolean onAction() {
												if ((ModelExpr) finalInstAttribute
														.getValue() != null)
													try {
														if (!((ModelExpr) finalInstAttribute
																.getValue())
																.createSGSExpression(
																		finalEditElm
																				.getIdentifier())
																.isValidExpression()) {
															JOptionPane
																	.showMessageDialog(
																			finalEditor,
																			"The expression is imcomplete, this may cause"
																					+ " error for verification and simulation operations",
																			"Instance Expression Error",
																			JOptionPane.INFORMATION_MESSAGE,
																			null);
														}
														// System.out.println(exp);
													} catch (Exception e) {
														JOptionPane
																.showMessageDialog(
																		finalEditor,
																		"The expression is imcomplete, this may cause error"
																				+ " for verification and simulation operations",
																		"Instance Expression Error",
																		JOptionPane.INFORMATION_MESSAGE,
																		null);
													}
												return true;
											}
										});
										dialog.setOnDelete(new InstanceExpressionButtonAction() {
											@Override
											public boolean onAction() {
												finalInstAttribute
														.setValue(null);
												return true;
											}
										});
									}
								});
								elementDesPropSubPanel.add(new JLabel(
										"Conditional Expression"));
								elementDesPropSubPanel.add(button);
								elementDesPropSubPanel.add(new JPanel());
							} else {
								final WidgetR widget = factory
										.getWidgetFor(instAttribute);

								if (widget == null) {
									System.err
											.print("EDP: No Widget found for "
													+ instAttribute);
									return;
								}
								// TODO: Add listeners to w.

								widget.getEditor().addFocusListener(
										new FocusListener() {
											@Override
											public void focusLost(
													FocusEvent arg0) {
												// Makes it pull the values.
												IntElemAttribute elementAttribute = widget
														.getInstAttribute();
												if (elementAttribute.getType()
														.equals("String")
														&& !elementAttribute
																.getIdentifier()
																.equals("Description")) {
													elementAttribute
															.setValue(AbstractElement.multiLine(
																	elementAttribute
																			.toString(),
																	(int) instCell
																			.getWidth() / 8));

												}
												// Divide lines every 15
												// characters
												// (aprox.)
												onVariableEdited(
														finalEditor,
														instCell.getInstElement(),
														elementAttribute);
											}

											@Override
											public void focusGained(
													FocusEvent arg0) {
											}
										});

								widget.getGroup().addFocusListener(
										new FocusListener() {
											@Override
											public void focusLost(
													FocusEvent arg0) {
												// Makes it pull the values.
												IntElemAttribute elementAttribute = widget
														.getInstAttribute();
												if (elementAttribute.getType()
														.equals("String")
														&& !elementAttribute
																.getIdentifier()
																.equals("Description")) {
													elementAttribute
															.setValue(AbstractElement.multiLine(
																	elementAttribute
																			.toString(),
																	(int) instCell
																			.getWidth() / 8));

												}
												// Divide lines every 15
												// characters
												// (aprox.)
												onVariableEdited(
														finalEditor,
														instCell.getInstElement(),
														elementAttribute);
											}

											@Override
											public void focusGained(
													FocusEvent arg0) {
											}
										});

								widget.getEditor().addPropertyChangeListener(
										new PropertyChangeListener() {

											@Override
											public void propertyChange(
													PropertyChangeEvent evt) {
												if (WidgetPL.PROPERTY_VALUE.equals(evt
														.getPropertyName())) {
													widget.getInstAttribute();
													onVariableEdited(
															finalEditor,
															instCell.getInstElement(),
															widget.getInstAttribute());

													editorProperties(
															finalEditor,
															instCell);
												}
											}
										});
								widget.getGroup().addPropertyChangeListener(
										new PropertyChangeListener() {

											@Override
											public void propertyChange(
													PropertyChangeEvent evt) {
												if (WidgetPL.PROPERTY_VALUE.equals(evt
														.getPropertyName())) {
													widget.getInstAttribute();
													onVariableEdited(
															finalEditor,
															instCell.getInstElement(),
															widget.getInstAttribute());

													editorProperties(
															finalEditor,
															instCell);
												}
											}
										});
								if (widget.getEditor() instanceof JCheckBox)
									((JCheckBox) widget.getEditor())
											.addActionListener(new ActionListener() {
												public void actionPerformed(
														ActionEvent e) {
													finalEditor
															.clearNotificationBar();
													// finalEditor.identifyCoreConcepts();
													// finalEditor.executeSimulation(true,
													// Refas2Hlcl.DESIGN_EXEC);
													onVariableEdited(
															finalEditor,
															instCell.getInstElement(),
															widget.getInstAttribute());
													new Thread() {
														public void run() {
															editorProperties(
																	finalEditor,
																	instCell);
														}
													}.start();
												}
											});
								if (widget.getEditor() instanceof JTextField)
									((JTextField) widget.getEditor())
											.addActionListener(new ActionListener() {
												public void actionPerformed(
														ActionEvent e) {
													onVariableEdited(
															finalEditor,
															instCell.getInstElement(),
															widget.getInstAttribute());
													new Thread() {
														public void run() {
															editorProperties(
																	finalEditor,
																	instCell);
														}
													}.start();
												}
											});
								((JTextField) widget.getGroup())
										.addActionListener(new ActionListener() {
											public void actionPerformed(
													ActionEvent e) {
												onVariableEdited(
														finalEditor,
														instCell.getInstElement(),
														widget.getInstAttribute());
												new Thread() {
													public void run() {
														editorProperties(
																finalEditor,
																instCell);
													}
												}.start();
											}
										});
								/*
								 * if (widget.getEditor() instanceof JComboBox)
								 * ((JComboBox) widget.getEditor())
								 * .addActionListener(new ActionListener() {
								 * public void actionPerformed( ActionEvent e) {
								 * 
								 * new Thread() { public void run() {
								 * editorProperties( finalEditor, instCell); }
								 * }.start(); } });
								 */
								/*
								 * if (w.getEditor() instanceof JComboBox)
								 * ((JComboBox) w.getEditor())
								 * .addItemListener(new ItemListener() {
								 * 
								 * @Override public void
								 * itemStateChanged(ItemEvent e) {
								 * finalEditor.cleanNotificationBar(); //
								 * finalEditor.identifyCoreConcepts(); //
								 * finalEditor.executeSimulation(true, //
								 * Refas2Hlcl.DESIGN_EXEC); new Thread() {
								 * public void run() { editorProperties(
								 * finalEditor, finalElm); } }.start(); }
								 * 
								 * });
								 */
								if (widget instanceof MClassWidget
										|| widget instanceof MEnumerationWidget) {
									widget.getEditor().setPreferredSize(
											new Dimension(200, 100));

								} else {
									widget.getEditor().setPreferredSize(
											new Dimension(200, 20));
									widget.getEditor().setMaximumSize(
											new Dimension(200, 20));
								}
								if (widget.editVariable(instAttribute))
									count = 0;
								List<InstAttribute> editables = editElm
										.getEditableVariables(syntaxParent);

								if (!editables.contains(instAttribute)
										|| editor.getPerspective() == 4
										|| (editElm
												.getTransSupportMetaElement() != null && editElm
												.getTransSupportMetaElement()
												.isEditable() == false))

								{
									widget.getEditor().setEnabled(false);

								}

								widget.getInstAttribute();
								// GARA
								// variablesPanel.add(new JLabel(v.getName() +
								// ":: "));
								{

									elementDesPropSubPanel.add(new JLabel(
											instAttribute.getDisplayName()
													+ ": "));
									elementDesPropSubPanel.add(widget);

									if (instAttribute.isAffectProperties()
											&& editor.getPerspective() != 4
											&& !(widget.getEditor() instanceof JCheckBox)) {
										JButton button = new JButton("Validate");
										button.addActionListener(new ActionListener() {
											public void actionPerformed(
													ActionEvent e) {
												finalEditor
														.clearNotificationBar();
												editorProperties(finalEditor,
														instCell);
											}
										});
										elementDesPropSubPanel.add(button);
									} else
										elementDesPropSubPanel
												.add(new JPanel());
								}

							}
							designPanelElements++;
						}
					}
				count++;
			}
			// variablesPanel.setPreferredSize(new Dimension(250, 25 *
			// editables.length));
			JPanel dummy = new JPanel();
			dummy.setMinimumSize(new Dimension(100, 0));
			dummy.setPreferredSize(new Dimension(100, 20));
			dummy.setMaximumSize(new Dimension(100, 200));
			elementDesPropSubPanel.add(dummy);
			dummy = new JPanel();
			dummy.setMinimumSize(new Dimension(100, 0));
			dummy.setPreferredSize(new Dimension(100, 20));
			dummy.setMaximumSize(new Dimension(100, 200));
			elementDesPropSubPanel.add(dummy);
			dummy = new JPanel();
			dummy.setMinimumSize(new Dimension(100, 0));
			dummy.setPreferredSize(new Dimension(100, 20));
			dummy.setMaximumSize(new Dimension(350, 200));
			elementDesPropSubPanel.add(dummy);

			// System.out.println(designPanelElements + "s");
			SpringUtilities.makeCompactGrid(elementDesPropSubPanel,
					designPanelElements, 3, 4, 4, 4, 4);
			// JFrame e = new JFrame();
			// e.add(contentPanel1);
			// e.setVisible(true);
			contentPanel1.add(elementDesPropSubPanel);
			elementDesPropSubPanel.setPreferredSize(new Dimension(350,
					designPanelElements * 30));
			elementDesPropSubPanel.setMaximumSize(new Dimension(350,
					designPanelElements * 30));

			contentPanel1.setMaximumSize(new Dimension(200, 350));
			mainPanel.add(rootPanel1);

			SpringUtilities.makeCompactGrid(contentPanel1, 1, 1, 4, 4, 4, 4);
			contentPanel1.revalidate();
			// Fill Attributes Panel (Only for VariabilityElements ) in
			// Properties Panel
			JPanel dummy2 = new JPanel();
			if (description != null) {
				mainPanelWidth += 100;
				JTextArea ta = new JTextArea();
				ta.setAutoscrolls(true);
				ta.setText(description);
				ta.setEditable(false);
				ta.setLineWrap(true);
				contentPanel2.add(new JLabel("Element semantics"));// TODO add
				// constant
				JScrollPane sca = new JScrollPane(ta);
				sca.setAutoscrolls(true);
				contentPanel2.add(sca);
				ta.setRows(5);
				ta.setColumns(40);
				ta.setWrapStyleWord(true);
				sca.setPreferredSize(new Dimension(350, 150));
				sca.setMaximumSize(new Dimension(350, 150));

				// dummy2.setPreferredSize(new Dimension(300, 150));
				// dummy2.setMaximumSize(new Dimension(300, 150));
				// contentPanel2.add(dummy2);
				//
				contentPanel2.setPreferredSize(new Dimension(300, 150));
				contentPanel2.setMaximumSize(new Dimension(300, 150));
				mainPanel.add(rootPanel2);

				SpringUtilities
						.makeCompactGrid(contentPanel2, 2, 1, 4, 4, 4, 4);

			}
			dummy2.setMinimumSize(new Dimension(0, 100));
			dummy2.setPreferredSize(new Dimension(200, 100));
			dummy2.setMaximumSize(new Dimension(200, 100));

			if (editElm instanceof InstEnum
					|| ((InstElement) editElm).getSupportMetaElementIden() != null
					&& (((InstElement) editElm).getSupportMetaElementIden()
							.equals("OPER"))) {

				JPanel attPanel = new JPanel(new SpringLayout());
				mainPanelWidth += 200;
				attPanel.addFocusListener(new FocusListener() {
					@Override
					public void focusLost(FocusEvent arg0) {
						editorProperties(finalEditor, instCell);
					}

					@Override
					public void focusGained(FocusEvent arg0) {
						editorProperties(finalEditor, instCell);
					}
				});
				attPanel.setPreferredSize(new Dimension(150, 150));
				attPanel.setMaximumSize(new Dimension(150, 180));
				attPanel.add(new JLabel(mxResources.get("attributesPanel")));
				if (((InstElement) editElm).getSupportMetaElementIden().equals(
						"ME")) {
					EnumerationAttributeList attList = new EnumerationAttributeList(
							editor, instCell);
					attPanel.add(new JScrollPane(attList));
				} else if (((InstElement) editElm).getSupportMetaElementIden()
						.equals("OPER")) {
					VariableAttributeList attList = new VariableAttributeList(
							editor, instCell);
					attPanel.add(new JScrollPane(attList));
				} else {
					EnumerationTypeAttributeList attList = new EnumerationTypeAttributeList(
							editor, instCell);
					attPanel.add(new JScrollPane(attList));
				}
				SpringUtilities.makeCompactGrid(attPanel, 2, 1, 4, 4, 4, 4);
				contentPanel3.setPreferredSize(new Dimension(200, 200));
				contentPanel3.add(attPanel);
				mainPanel.add(rootPanel3);

			} else if (editor.getPerspective() % 2 != 0) {

				JPanel attPanel = new JPanel(new SpringLayout());
				mainPanelWidth += 350;
				rootPanel3.setPreferredSize(new Dimension(350, 450));
				contentPanel3.setPreferredSize(new Dimension(350, 450));
				attPanel.addFocusListener(new FocusListener() {
					@Override
					public void focusLost(FocusEvent arg0) {
						editorProperties(finalEditor, instCell);
					}

					@Override
					public void focusGained(FocusEvent arg0) {
						editorProperties(finalEditor, instCell);
					}
				});

				AttributeEditionPanel attributeEdition = new AttributeEditionPanel();
				boolean editable = true;

				if (// editor.getPerspective() == 3
				(editElm.getTransSupportMetaElement() != null && editElm
						.getTransSupportMetaElement().isEditable() == false)) {
					attributeEdition.setEnabled(false);
					editable = false;
				}

				JList attList = null;
				if (editElm.getTransSupportMetaElement().getName()
						.equals("InfraSyntaxOpersM2SubOper")) {
					attList = new MetaEnumTypeAttributeList(
							editor,
							instCell,
							"exptype",
							OperationSubActionExecType.class.getCanonicalName(),
							((ModelInstance) editor.getEditedModel())
									.getSyntaxModel()
									.getVertex("InfraSyntaxOpersM2ExpType")
									.getEditableMetaElement());
					attPanel.add(new JLabel(mxResources.get("suboperExpType")));
					attPanel.add(new JLabel(""));
				} else if (editElm.getTransSupportMetaElement().getName()
						.equals("InfraSyntaxOpersM2Labeling")) {
					attList = new MetaEnumTypeAttributeList(editor, instCell,
							"sortorder",
							LabelingOrder.class.getCanonicalName(), null);

					attPanel.add(new JLabel(mxResources.get("labelingSort")));
					attPanel.add(new JLabel(""));
				} else {

					attPanel.add(new JLabel(mxResources
							.get("elementAttributes")));
					attPanel.add(new JLabel(""));
					if (instCell.getInstElement().getEditableMetaElement() != null)
						attList = new PropertyAttributeList(editor, editable,
								instCell.getInstElement(), instCell
										.getInstElement()
										.getEditableMetaElement()
										.getModelingAttributes(),
								attributeEdition);
					if (instCell.getInstElement().getEditableSemanticElement() != null)
						attList = new PropertyAttributeList(editor, editable,
								instCell.getInstElement(), instCell
										.getInstElement()
										.getEditableSemanticElement()
										.getDeclaredSemanticAttributes(),
								attributeEdition);
				}
				attributeEdition.setPropertyAttributeList(attList);
				attPanel.setPreferredSize(new Dimension(450, 250));
				attPanel.setMaximumSize(new Dimension(550, 250));
				JScrollPane jj = new JScrollPane(attList);
				jj.setAutoscrolls(true);
				attPanel.add(jj);
				jj = new JScrollPane(attributeEdition);
				jj.setAutoscrolls(true);
				attPanel.add(jj);
				attPanel.add(jj);

				SpringUtilities.makeCompactGrid(attPanel, 2, 2, 4, 4, 4, 4);

				contentPanel3.add(attPanel);
				mainPanel.add(rootPanel3);
			} else {
				JPanel dummy3 = new JPanel();
				dummy3.setMinimumSize(new Dimension(300, 250));
				dummy3.setPreferredSize(new Dimension(500, 250));
				dummy3.setMaximumSize(new Dimension(650, 250));
				mainPanel.add(dummy3);

			}
			if (((InstElement) editElm).getSupportMetaElementIden() != null
					&& (((InstElement) editElm).getSupportMetaElementIden()
							.equals("InfraSyntaxOpersM2PWRel") || ((InstElement) editElm)
							.getSupportMetaElementIden().equals(
									"InfraSyntaxOpersM2OTRel"))) {

				JPanel attPanel = new JPanel(new SpringLayout());
				mainPanelWidth += 200;
				attPanel.addFocusListener(new FocusListener() {
					@Override
					public void focusLost(FocusEvent arg0) {
						editorProperties(finalEditor, instCell);
					}

					@Override
					public void focusGained(FocusEvent arg0) {
						editorProperties(finalEditor, instCell);
					}
				});
				attPanel.setPreferredSize(new Dimension(150, 150));
				attPanel.setMaximumSize(new Dimension(150, 180));
				attPanel.add(new JLabel(mxResources.get("relationTypesPanel")));

				RelationTypesList attList = new RelationTypesList(editor,
						instCell);
				attPanel.add(new JScrollPane(attList));
				SpringUtilities.makeCompactGrid(attPanel, 2, 1, 4, 4, 4, 4);
				contentPanel4.setPreferredSize(new Dimension(100, 200));
				contentPanel4.add(attPanel);
				mainPanel.add(rootPanel4);
				extra++;
			} else {
				/*
				 * JPanel dummy3 = new JPanel(); dummy3.setMinimumSize(new
				 * Dimension(0, 0)); dummy3.setPreferredSize(new Dimension(500,
				 * 20)); dummy3.setMaximumSize(new Dimension(600, 200));
				 * mainPanel.add(dummy3);
				 */
			}
		}
		int mainPanelHeight = 350;
		if (designPanelElements > 13)
			mainPanelHeight = designPanelElements * 32;

		mainPanel.setPreferredSize(new Dimension(mainPanelWidth,
				mainPanelHeight));
		mainPanel.setMaximumSize(new Dimension(mainPanelWidth, 600));

		// System.out.println(mainPanel.getComponentCount() + " " );
		SpringUtilities.makeCompactGrid(mainPanel, 1,
				mainPanel.getComponentCount(), 3 + extra, 4, 4, 4);
		this.revalidate();
		this.repaint();
		this.revalidate();
		this.repaint();
	}

	@SuppressWarnings("unchecked")
	protected void onVariableEdited(VariamosGraphEditor editor,
			IntInstElement editableElement, IntElemAttribute instAttribute) {
		if (editableElement instanceof InstConcept) {
			SyntaxElement editableMetaElement = ((InstConcept) editableElement)
					.getEditableMetaElement();
			if (editableMetaElement != null) {
				if (instAttribute.getIdentifier().equals("userIdentifier"))
					editableMetaElement
							.setUserIdentifier((String) instAttribute
									.getValue());
				if (instAttribute.getIdentifier().equals("identifier"))
					editableMetaElement
							.setAutoIdentifier((String) instAttribute
									.getValue());
				if (instAttribute.getIdentifier().equals("OperationsMMType"))
					editableMetaElement
							.setTransInstSemanticElement((InstElement) ((ModelInstance) editor
									.getEditedModel()).getOperationalModel()
									.getElement(
											(String) instAttribute.getValue()));
				// if (instAttribute.getIdentifier().equals("Visible"))
				// editableMetaElement.setVisible((boolean) instAttribute
				// .getValue());
				if (instAttribute.getIdentifier().equals("Name"))
					editableMetaElement.setName((String) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Style"))
					editableMetaElement.setStyle((String) instAttribute
							.getValue());
				if (editableMetaElement instanceof SyntaxView) {
					if (instAttribute.getIdentifier().equals("PaletteNames"))
						((SyntaxView) editableMetaElement)
								.setPaletteName((String) instAttribute
										.getValue());
				}
				if (instAttribute.getIdentifier().equals("Description"))
					editableMetaElement.setDescription((String) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Width"))
					editableMetaElement
							.setWidth((int) instAttribute.getValue());
				if (instAttribute.getIdentifier().equals("Height"))
					editableMetaElement.setHeight((int) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Image"))
					editableMetaElement.setImage((String) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("TopConcept"))
					((SyntaxConcept) editableMetaElement)
							.setTopConcept((boolean) instAttribute.getValue());
				if (instAttribute.getIdentifier().equals("BackgroundColor"))
					((SyntaxConcept) editableMetaElement)
							.setBackgroundColor((String) instAttribute
									.getValue());
				if (instAttribute.getIdentifier().equals("BorderStroke"))
					editableMetaElement.setBorderStroke((int) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Resizable"))
					((SyntaxConcept) editableMetaElement)
							.setResizable((boolean) instAttribute.getValue());
				if (instAttribute.getIdentifier().equals("Palette"))
					((SyntaxPairwiseRel) editableMetaElement)
							.setPalette((String) instAttribute.getValue());
				if (instAttribute.getIdentifier().equals("value"))
					editableMetaElement
							.setModelingAttributes((HashSet<ElemAttribute>) instAttribute
									.getValue());
			}
			IntOpersElement editableSemanticElement = ((InstConcept) editableElement)
					.getEditableSemanticElement();
			if (editableSemanticElement != null) {
				if (instAttribute.getIdentifier().equals("Identifier"))
					editableSemanticElement
							.setIdentifier((String) instAttribute.getValue());
			}
		}
		editor.refresh();

	}
}
