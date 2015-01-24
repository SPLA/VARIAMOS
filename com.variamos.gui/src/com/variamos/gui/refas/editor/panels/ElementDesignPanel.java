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
import com.variamos.gui.refas.editor.widgets.MClassWidget;
import com.variamos.gui.refas.editor.widgets.MEnumerationWidget;
import com.variamos.gui.refas.editor.widgets.RefasWidgetFactory;
import com.variamos.gui.refas.editor.widgets.WidgetR;
import com.variamos.refas.core.refas.Refas;
import com.variamos.syntaxsupport.metamodel.EditableElement;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstElement;
import com.variamos.syntaxsupport.metamodel.InstEnumeration;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodelsupport.AbstractAttribute;
import com.variamos.syntaxsupport.metamodelsupport.EditableElementAttribute;
import com.variamos.syntaxsupport.metamodelsupport.MetaConcept;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.ModelingAttribute;
import com.variamos.syntaxsupport.metamodelsupport.SemanticAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;

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

	public void editorProperties(VariamosGraphEditor editor, EditableElement elm) {
		mainPanel.removeAll();
		rootPanels = 1;
		mainPanelWidth = 350;
		JPanel elementDesPropSubPanel = null;
		final VariamosGraphEditor finalEditor = editor;
		final EditableElement finalElm = elm;

		// updateVisibleProperties(elm);

		contentPanel1.removeAll();
		contentPanel2.removeAll();
		contentPanel3.removeAll();

		if (elm == null) {
			return;
		} else {
			elm.getInstAttributes();

			RefasWidgetFactory factory = new RefasWidgetFactory(editor);
			int designPanelElements = 0;
			String description = null;

			String type = null;
			if (elm instanceof InstConcept) {
				type = "vertex";
			}
			if (elm instanceof InstPairwiseRelation) {
				if (((InstPairwiseRelation) elm).getSourceRelations().size() == 0)
					// TODO workaround for non supported relations - delete
					// after fix
					return;
				type = "edge";
			}
			if (elm instanceof InstOverTwoRelation) {
				type = "groupdep";
			}
			if (elm instanceof InstElement) {
				if (((InstElement) elm).getEditableMetaElement() != null)
					description = ((InstElement) elm)
							.getTransSupportMetaElement().getDescription();
			}
			int count = 0;
			while (count < 2) {
				designPanelElements = 0;
				
				//Warning: Fix for Mac, do not delete it
				if (elm instanceof InstPairwiseRelation)
					designPanelElements++;
				
				elementDesPropSubPanel = new JPanel(new SpringLayout());
				Collection<InstAttribute> visible = elm.getVisibleVariables();
				for (InstAttribute v : visible) {
					if (v != null
							&& (v.getAttribute() instanceof ModelingAttribute || v
									.getAttribute() instanceof SemanticAttribute)) {
						Map<String, MetaElement> mapElements = null;
						if (elm instanceof InstPairwiseRelation) {
							InstPairwiseRelation instPairwise = (InstPairwiseRelation) elm;
							mapElements = ((Refas) editor.getEditedModel())
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
						v.updateValidationList(((InstElement) elm), mapElements);

						final WidgetR w = factory.getWidgetFor(v);

						if (w == null) {
							System.err.print("No Widget found for " + v);
							return;
						}
						// TODO: Add listeners to w.

						w.getEditor().addFocusListener(new FocusListener() {
							@Override
							public void focusLost(FocusEvent arg0) {
								// Makes it pull the values.
								EditableElementAttribute elementAttribute = w
										.getInstAttribute();
								if (elementAttribute.getAttributeType().equals(
										"String")
										&& !elementAttribute.getIdentifier()
												.equals("Description"))
									elementAttribute.setValue(AbstractElement.multiLine(
											elementAttribute.toString(), 15));
								// Divide lines every 15 characters (aprox.)
								onVariableEdited(finalEditor, finalElm,
										elementAttribute);
							}

							@Override
							public void focusGained(FocusEvent arg0) {
							}
						});

						w.getEditor().addPropertyChangeListener(
								new PropertyChangeListener() {

									@Override
									public void propertyChange(
											PropertyChangeEvent evt) {
										if (WidgetPL.PROPERTY_VALUE.equals(evt
												.getPropertyName())) {
											w.getInstAttribute();
											onVariableEdited(finalEditor,
													finalElm,
													w.getInstAttribute());

											editorProperties(finalEditor,
													finalElm);
										}
									}
								});
						if (w.getEditor() instanceof JCheckBox)
							((JCheckBox) w.getEditor())
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent e) {
											finalEditor.clearNotificationBar();
											// finalEditor.identifyCoreConcepts();
											// finalEditor.executeSimulation(true,
											// Refas2Hlcl.DESIGN_EXEC);
											new Thread() {
												public void run() {
													editorProperties(
															finalEditor,
															finalElm);
												}
											}.start();
										}
									});
						/*
						 * if (w.getEditor() instanceof JComboBox) ((JComboBox)
						 * w.getEditor()) .addItemListener(new ItemListener() {
						 * 
						 * @Override public void itemStateChanged(ItemEvent e) {
						 * finalEditor.cleanNotificationBar(); //
						 * finalEditor.identifyCoreConcepts(); //
						 * finalEditor.executeSimulation(true, //
						 * Refas2Hlcl.DESIGN_EXEC); new Thread() { public void
						 * run() { editorProperties( finalEditor, finalElm); }
						 * }.start(); }
						 * 
						 * });
						 */
						if (w instanceof MClassWidget
								|| w instanceof MEnumerationWidget) {
							w.getEditor().setPreferredSize(
									new Dimension(200, 100));
						} else {
							w.getEditor().setPreferredSize(
									new Dimension(200, 20));
							w.getEditor()
									.setMaximumSize(new Dimension(200, 20));
						}
						if (w.editVariable(v))
							count = 0;

						List<InstAttribute> editables = elm
								.getEditableVariables();

						if (!editables.contains(v)
								|| editor.getPerspective() == 4)

						{
							w.getEditor().setEnabled(false);

						}

						// GARA
						// variablesPanel.add(new JLabel(v.getName() + ":: "));
						{

							elementDesPropSubPanel.add(new JLabel(v
									.getDisplayName() + ": "));
							elementDesPropSubPanel.add(w);

							if (v.isAffectProperties()
									&& editor.getPerspective() != 4 &&  !(w.getEditor() instanceof JCheckBox)) {
								JButton button = new JButton("Validate");
								button.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										finalEditor.clearNotificationBar();
										editorProperties(finalEditor, finalElm);
									}
								});
								elementDesPropSubPanel.add(button);
							} else

								elementDesPropSubPanel.add(new JPanel());

							designPanelElements++;

						}

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

			if (elm instanceof InstEnumeration) {
				rootPanels++;
				mainPanelWidth += 200;
				attPanel.addFocusListener(new FocusListener() {
					@Override
					public void focusLost(FocusEvent arg0) {
						editorProperties(finalEditor, finalElm);
					}

					@Override
					public void focusGained(FocusEvent arg0) {
						editorProperties(finalEditor, finalElm);
					}
				});
				attPanel.setPreferredSize(new Dimension(150, 80));
				attPanel.setMaximumSize(new Dimension(150, 80));
				attPanel.add(new JLabel(mxResources.get("attributesPanel")));

				EnumerationAttributeList attList = new EnumerationAttributeList(
						editor, (InstElement) elm);
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
						editorProperties(finalEditor, finalElm);
					}

					@Override
					public void focusGained(FocusEvent arg0) {
						editorProperties(finalEditor, finalElm);
					}
				});

				attPanel.add(new JLabel(mxResources.get("elementAttributes")));
				attPanel.add(new JLabel(mxResources.get("attributeEdition")));
				AttributeEditionPanel attributeEdition = new AttributeEditionPanel();
				PropertyAttributeList attList = null;
				if (((InstElement) elm).getEditableMetaElement() != null)
					attList = new PropertyAttributeList(editor,
							((InstElement) elm).getEditableMetaElement()
									.getModelingAttributes(), attributeEdition);
				if (((InstElement) elm).getEditableSemanticElement() != null)
					attList = new PropertyAttributeList(editor,
							((InstElement) elm).getEditableSemanticElement()
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

		SpringUtilities.makeCompactGrid(mainPanel, 1, rootPanels, 4, 4, 4, 4);
		this.revalidate();
		this.repaint();
	}

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
