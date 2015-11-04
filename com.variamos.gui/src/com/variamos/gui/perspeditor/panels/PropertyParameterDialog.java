package com.variamos.gui.perspeditor.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;

import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.SpringUtilities;
import com.variamos.gui.perspeditor.panels.SemanticExpressionDialog.SemanticExpressionButtonAction;
import com.variamos.gui.perspeditor.widgets.RefasWidgetFactory;
import com.variamos.gui.perspeditor.widgets.WidgetR;
import com.variamos.perspsupport.expressionsupport.SemanticExpression;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.semanticinterface.IntSemanticExpression;
import com.variamos.perspsupport.syntaxsupport.EditableElementAttribute;

/**
 * @author unknown
 *
 */
@SuppressWarnings("serial")
public class PropertyParameterDialog extends JDialog {
	private HashMap<String, WidgetR> widgets;
	private DialogButtonAction onAccept, onDelete, onCancel;

	static interface DialogButtonAction {
		public boolean onAction();
	}

	public PropertyParameterDialog(int height, int width,
			final VariamosGraphEditor editor, final InstElement instElement,
			EditableElementAttribute... arguments) {
		super(editor.getFrame(), "Enumeration Value");

		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());

		setPreferredSize(new Dimension(width, height));

		RefasWidgetFactory factory = new RefasWidgetFactory(editor);

		widgets = new HashMap<String, WidgetR>();

		for (final EditableElementAttribute elementAttribute : arguments) {
			panel.add(new JLabel(elementAttribute.getDisplayName() + ": "));
			if (elementAttribute.getAttributeType().equals(
					SemanticExpression.class.getCanonicalName())) {
				JButton jb = new JButton(elementAttribute.getDisplayName());
				panel.add(jb);
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						List<IntSemanticExpression> ie = new ArrayList<IntSemanticExpression>();
						;
						if (elementAttribute.getValue() != null)
							ie.addAll((List<IntSemanticExpression>) elementAttribute
									.getValue());

						final SemanticExpressionDialog dialog = new SemanticExpressionDialog(
								editor, instElement, ie);
						dialog.center();
						dialog.setOnAccept(new SemanticExpressionButtonAction() {
							@Override
							public boolean onAction() {
								// This calls Pull on each
								// parameter
								// attributeEdition.getParameters();
								elementAttribute.setValue(dialog
										.getExpressions().get(0));
								// attributes.put(name.getName(),
								// v);
								try {
									// ((SemanticExpression) elementAttribute
									// .getValue())
									// .createSGSExpression((SemanticExpression)
									// elementAttribute
									// .getValue());

								} catch (Exception e) {
									JOptionPane
											.showMessageDialog(
													editor,
													"Complete/Correct the expression before closing the editor",
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
						/*
						 * dialog.setOnDelete(new
						 * InstanceExpressionButtonAction() {
						 * 
						 * @Override public boolean onAction() {
						 * elementAttribute .setValue(null); return true; } });
						 */
					}
				});
			} else {
				WidgetR w = factory.getWidgetFor(elementAttribute);
				w.editVariable(elementAttribute);

				w.addPropertyChangeListener("value",
						new PropertyChangeListener() {
							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								revalidate();
								doLayout();
								pack();
							}
						});

				widgets.put(elementAttribute.getIdentifier(), w);

				panel.add(w);
			}
		}

		SpringUtilities.makeCompactGrid(panel, arguments.length, 2, 4, 4, 4, 4);

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
				if (onDelete != null) {
					if (onDelete.onAction())
						dispose();
					return;
				}

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
	}

	/**
	 * @return
	 */
	public Map<String, EditableElementAttribute> getParameters() {
		Map<String, EditableElementAttribute> map = new HashMap<>();

		for (String s : widgets.keySet()) {
			EditableElementAttribute v = widgets.get(s).getInstAttribute();
			map.put(v.getIdentifier(), v);
		}

		return map;
	}

	public void center() {
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setOnAccept(DialogButtonAction onAccept) {
		this.onAccept = onAccept;
	}

	public void setOnDelete(DialogButtonAction onDelete) {
		this.onDelete = onDelete;
	}

	public void setOnCancel(DialogButtonAction onCancel) {
		this.onCancel = onCancel;
	}

}