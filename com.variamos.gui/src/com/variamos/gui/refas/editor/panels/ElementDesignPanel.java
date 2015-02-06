package com.variamos.gui.refas.editor.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import com.cfm.productline.AbstractElement;
import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.pl.editor.SpringUtilities;
import com.variamos.gui.pl.editor.widgets.WidgetPL;
import com.variamos.gui.refas.editor.panels.AttributeEditionPanel.DialogButtonAction;
import com.variamos.gui.refas.editor.widgets.MClassWidget;
import com.variamos.gui.refas.editor.widgets.MEnumerationWidget;
import com.variamos.gui.refas.editor.widgets.RefasWidgetFactory;
import com.variamos.gui.refas.editor.widgets.WidgetR;
import com.variamos.refas.RefasModel;
import com.variamos.semantic.expressionsupport.InstanceExpression;
import com.variamos.syntax.instancesupport.EditableElement;
import com.variamos.syntax.instancesupport.InstAttribute;
import com.variamos.syntax.instancesupport.InstCell;
import com.variamos.syntax.instancesupport.InstConcept;
import com.variamos.syntax.instancesupport.InstElement;
import com.variamos.syntax.instancesupport.InstEnumeration;
import com.variamos.syntax.instancesupport.InstPairwiseRelation;
import com.variamos.syntax.metamodelsupport.AbstractAttribute;
import com.variamos.syntax.metamodelsupport.EditableElementAttribute;
import com.variamos.syntax.metamodelsupport.MetaConcept;
import com.variamos.syntax.metamodelsupport.MetaElement;
import com.variamos.syntax.metamodelsupport.ModelingAttribute;
import com.variamos.syntax.metamodelsupport.SemanticAttribute;
import com.variamos.syntax.semanticinterface.IntSemanticElement;
import com.variamos.gui.refas.editor.panels.ExpressionDialog.ExpressionButtonAction;

public class ElementDesignPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4322732010527019064L;
	private JPanel mainPanel = new JPanel(new SpringLayout());
	private JPanel contentPanel1 = new JPanel(new SpringLayout());
	private JPanel contentPanel2 = new JPanel(new SpringLayout());
	private JPanel contentPanel3 = new JPanel(new SpringLayout());
	private JPanel rootPanel1 = new JPanel(new SpringLayout());
	private JPanel rootPanel2 = new JPanel(new SpringLayout());
	private JPanel rootPanel3 = new JPanel(new SpringLayout());
	private int rootPanels = 1;
	private int mainPanelWidth = 200;

	public ElementDesignPanel() {

		rootPanel1.add(contentPanel1);
		JPanel dummyP = new JPanel();
		dummyP.setMinimumSize(new Dimension(0, 0));
		dummyP.setMaximumSize(new Dimension(500, 300));
		rootPanel1.add(dummyP);
		SpringUtilities.makeCompactGrid(rootPanel1, 2, 1, 4, 4, 4, 4);

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

	public void editorProperties(VariamosGraphEditor editor, InstCell instCell) {
		mainPanel.removeAll();
		rootPanels = 0;
		mainPanelWidth = 350;
		JPanel elementDesPropSubPanel = null;
		final VariamosGraphEditor finalEditor = editor;
		final InstCell finalInstCell = instCell;

		// updateVisibleProperties(elm);

		contentPanel1.removeAll();
		contentPanel2.removeAll();
		contentPanel3.removeAll();

		if (instCell == null || instCell.getInstElement() == null) {
			return;
		} else {
			EditableElement editElm = instCell.getInstElement();
			editElm.getInstAttributes();
			final InstElement finalEditElm = (InstElement) editElm;
			RefasWidgetFactory factory = new RefasWidgetFactory(editor);
			int designPanelElements = 0;
			String description = null;

			if (editElm instanceof InstPairwiseRelation) {
				if (((InstPairwiseRelation) editElm).getSourceRelations()
						.size() == 0)
					// TODO workaround for non supported relations - delete
					// after fix
					return;
			}
			if (editElm instanceof InstElement) {
				if (((InstElement) editElm).getEditableMetaElement() != null)
					description = ((InstElement) editElm)
							.getTransSupportMetaElement().getDescription();
			}
			int count = 0;
			while (count < 2) {
				designPanelElements = 0;

				// Warning: Fix for Mac, do not delete it
				if (editElm instanceof InstPairwiseRelation)
					designPanelElements++;

				elementDesPropSubPanel = new JPanel(new SpringLayout());
				Collection<InstAttribute> visible = editElm
						.getVisibleVariables();
				if (visible != null)
					for (InstAttribute instAttribute : visible) {
						if (instAttribute != null
								&& (instAttribute.getAttribute() instanceof ModelingAttribute || instAttribute
										.getAttribute() instanceof SemanticAttribute)) {
							final InstAttribute finalInstAttribute = instAttribute;
							Map<String, MetaElement> mapElements = null;
							if (editElm instanceof InstPairwiseRelation) {
								InstPairwiseRelation instPairwise = (InstPairwiseRelation) editElm;
								mapElements = ((RefasModel) editor
										.getEditedModel())
										.getSyntaxRefas()
										.getValidPairwiseRelations(
												instPairwise
														.getSourceRelations()
														.get(0)
														.getTransSupportMetaElement(),
												instPairwise
														.getTargetRelations()
														.get(0)
														.getTransSupportMetaElement(),
												true);
							}
							instAttribute.updateValidationList(
									((InstElement) editElm), mapElements);

							if (instAttribute.getIdentifier().equals(
									"ConditionalExpression")) {
								JButton button = new JButton("Edit Expression");
								if (editor.getPerspective() == 4)
									button.setEnabled(false);
								button.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										InstanceExpression ie = (InstanceExpression) finalInstAttribute
												.getValue();
										if (ie == null)
											ie = new InstanceExpression(true,
													"id");
										final ExpressionDialog dialog = new ExpressionDialog(
												finalEditor, finalEditElm,
												false, ie);
										dialog.center();
										dialog.setOnAccept(new ExpressionButtonAction() {
											@Override
											public boolean onAction() {
												// This calls Pull on each
												// parameter
												// attributeEdition.getParameters();
												finalInstAttribute.setValue(dialog
														.getExpressions()[0]);
												// attributes.put(name.getName(),
												// v);

												// afterAction();
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
									System.err.print("No Widget found for "
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
												EditableElementAttribute elementAttribute = widget
														.getInstAttribute();
												if (elementAttribute
														.getAttributeType()
														.equals("String")
														&& !elementAttribute
																.getIdentifier()
																.equals("Description"))
													elementAttribute
															.setValue(AbstractElement.multiLine(
																	elementAttribute
																			.toString(),
																	15));
												// Divide lines every 15
												// characters
												// (aprox.)
												onVariableEdited(
														finalEditor,
														finalInstCell
																.getInstElement(),
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
															finalInstCell
																	.getInstElement(),
															widget.getInstAttribute());

													editorProperties(
															finalEditor,
															finalInstCell);
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
													new Thread() {
														public void run() {
															editorProperties(
																	finalEditor,
																	finalInstCell);
														}
													}.start();
												}
											});
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
										.getEditableVariables();

								if (!editables.contains(instAttribute)
										|| editor.getPerspective() == 4)

								{
									widget.getEditor().setEnabled(false);

								}

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
														finalInstCell);
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
			SpringUtilities.makeCompactGrid(elementDesPropSubPanel,
					designPanelElements, 3, 4, 4, 4, 4);

			contentPanel1.add(elementDesPropSubPanel);
			elementDesPropSubPanel.setPreferredSize(new Dimension(350,
					designPanelElements * 30));
			elementDesPropSubPanel.setMaximumSize(new Dimension(350,
					designPanelElements * 30));

			contentPanel1.setMaximumSize(new Dimension(200, 300));
			rootPanels++;
			mainPanel.add(rootPanel1);

			SpringUtilities.makeCompactGrid(contentPanel1, 1, 1, 4, 4, 4, 4);
			contentPanel1.revalidate();
			JPanel attPanel = new JPanel(new SpringLayout());
			// Fill Attributes Panel (Only for VariabilityElements ) in
			// Properties
			// Panel
			JPanel dummy2 = new JPanel();
			if (description != null) {
				rootPanels++;
				mainPanelWidth += 200;
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
				// contentPanel2.setPreferredSize(new Dimension(300, 150));
				// contentPanel2.setMaximumSize(new Dimension(300, 150));
				mainPanel.add(rootPanel2);

				SpringUtilities
						.makeCompactGrid(contentPanel2, 2, 1, 4, 4, 4, 4);

			}
			dummy2.setMinimumSize(new Dimension(0, 100));
			dummy2.setPreferredSize(new Dimension(200, 100));
			dummy2.setMaximumSize(new Dimension(200, 100));

			if (editElm instanceof InstEnumeration) {
				rootPanels++;
				mainPanelWidth += 200;
				attPanel.addFocusListener(new FocusListener() {
					@Override
					public void focusLost(FocusEvent arg0) {
						editorProperties(finalEditor, finalInstCell);
					}

					@Override
					public void focusGained(FocusEvent arg0) {
						editorProperties(finalEditor, finalInstCell);
					}
				});
				attPanel.setPreferredSize(new Dimension(150, 80));
				attPanel.setMaximumSize(new Dimension(150, 80));
				attPanel.add(new JLabel(mxResources.get("attributesPanel")));

				EnumerationAttributeList attList = new EnumerationAttributeList(
						editor, instCell.getInstElement());
				attPanel.add(new JScrollPane(attList));

				SpringUtilities.makeCompactGrid(attPanel, 2, 1, 4, 4, 4, 4);
				contentPanel3.setPreferredSize(new Dimension(200, 200));
				contentPanel3.add(attPanel);
				mainPanel.add(rootPanel3);

			} else if (editor.getPerspective() % 2 != 0) {
				rootPanels++;
				mainPanelWidth += 350;
				rootPanel3.setPreferredSize(new Dimension(350, 400));
				contentPanel3.setPreferredSize(new Dimension(350, 400));
				attPanel.addFocusListener(new FocusListener() {
					@Override
					public void focusLost(FocusEvent arg0) {
						editorProperties(finalEditor, finalInstCell);
					}

					@Override
					public void focusGained(FocusEvent arg0) {
						editorProperties(finalEditor, finalInstCell);
					}
				});

				attPanel.add(new JLabel(mxResources.get("elementAttributes")));
				attPanel.add(new JLabel(mxResources.get("attributeEdition")));
				AttributeEditionPanel attributeEdition = new AttributeEditionPanel();
				PropertyAttributeList attList = null;
				if (instCell.getInstElement().getEditableMetaElement() != null)
					attList = new PropertyAttributeList(editor, instCell
							.getInstElement().getEditableMetaElement()
							.getModelingAttributes(), attributeEdition);
				if (instCell.getInstElement().getEditableSemanticElement() != null)
					attList = new PropertyAttributeList(editor, instCell
							.getInstElement().getEditableSemanticElement()
							.getSemanticAttributes(), attributeEdition);
				attributeEdition.setPropertyAttributeList(attList);
				attPanel.setPreferredSize(new Dimension(350, 400));
				attPanel.setMaximumSize(new Dimension(350, 400));
				attPanel.add(new JScrollPane(attList));
				attPanel.add(new JScrollPane(attributeEdition));

				SpringUtilities.makeCompactGrid(attPanel, 2, 2, 4, 4, 4, 4);

				contentPanel3.add(attPanel);
				mainPanel.add(rootPanel3);
			}
		}
		mainPanel.setMaximumSize(new Dimension(mainPanelWidth, 400));

		System.out.println(mainPanel.getComponentCount() + " " + rootPanels);
		SpringUtilities.makeCompactGrid(mainPanel, 1,
				mainPanel.getComponentCount(), 4, 4, 4, 4);
		this.revalidate();
		this.repaint();
	}

	@SuppressWarnings("unchecked")
	protected void onVariableEdited(VariamosGraphEditor editor,
			EditableElement editableElement,
			EditableElementAttribute instAttribute) {
		if (editableElement instanceof InstConcept) {
			MetaElement editableMetaElement = ((InstConcept) editableElement)
					.getEditableMetaElement();
			if (editableMetaElement != null) {
				if (instAttribute.getIdentifier().equals("Identifier"))
					editableMetaElement.setIdentifier((String) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Visible"))
					editableMetaElement.setVisible((boolean) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Name"))
					editableMetaElement.setName((String) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Style"))
					editableMetaElement.setStyle((String) instAttribute
							.getValue());
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
					((MetaConcept) editableMetaElement)
							.setTopConcept((boolean) instAttribute.getValue());
				if (instAttribute.getIdentifier().equals("BackgroundColor"))
					((MetaConcept) editableMetaElement)
							.setBackgroundColor((String) instAttribute
									.getValue());
				if (instAttribute.getIdentifier().equals("BorderStroke"))
					editableMetaElement.setBorderStroke((int) instAttribute
							.getValue());
				if (instAttribute.getIdentifier().equals("Resizable"))
					((MetaConcept) editableMetaElement)
							.setResizable((boolean) instAttribute.getValue());
				if (instAttribute.getIdentifier().equals("value"))
					editableMetaElement
							.setModelingAttributes((Map<String, AbstractAttribute>) instAttribute
									.getValue());
			}
			IntSemanticElement editableSemanticElement = ((InstConcept) editableElement)
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
