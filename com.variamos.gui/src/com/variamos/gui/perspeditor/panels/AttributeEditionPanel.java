package com.variamos.gui.perspeditor.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.variamos.dynsup.interfaces.IntInstAttribute;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.SpringUtilities;
import com.variamos.gui.perspeditor.widgets.RefasWidgetFactory;
import com.variamos.gui.perspeditor.widgets.WidgetR;

public class AttributeEditionPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1478873242908064197L;
	private HashMap<String, WidgetR> widgets;
	private DialogButtonAction onAccept, onCancel, onDelete;
	private JList<ElemAttribute> propertyAttributeList;
	JPanel panel = null;

	static interface DialogButtonAction {
		public boolean onAction();
	}

	public AttributeEditionPanel() {
		super();
		setLayout(new BorderLayout());

		panel = new JPanel();
		panel.setLayout(new SpringLayout());

		JPanel dummy = new JPanel();
		dummy.setMinimumSize(new Dimension(20, 0));
		dummy.setPreferredSize(new Dimension(20, 40));
		dummy.setMaximumSize(new Dimension(20, 40));
		panel.add(dummy);
		dummy = new JPanel();
		dummy.setMinimumSize(new Dimension(100, 0));
		dummy.setPreferredSize(new Dimension(100, 40));
		dummy.setMaximumSize(new Dimension(100, 40));
		panel.add(dummy);
		dummy = new JPanel();
		dummy.setMinimumSize(new Dimension(20, 0));
		dummy.setPreferredSize(new Dimension(20, 40));
		dummy.setMaximumSize(new Dimension(20, 40));
		panel.add(dummy);
		this.setEnabled(false);
		SpringUtilities.makeCompactGrid(panel, 1, 3, 4, 4, 4, 4);
		add(panel, BorderLayout.CENTER);

		this.setMinimumSize(new Dimension(200, 400));
		this.setPreferredSize(new Dimension(200, 400));
		this.setMaximumSize(new Dimension(200, 400));
		this.setAutoscrolls(true);
	}

	public Map<String, IntInstAttribute> getParameters() {
		Map<String, IntInstAttribute> map = new HashMap<>();

		for (String s : widgets.keySet()) {
			IntInstAttribute v = widgets.get(s).getInstAttribute();
			map.put(v.getIdentifier(), v);
		}

		return map;
	}

	public void setPropertyAttributeList(
			JList<ElemAttribute> propertyAttributeList) {
		this.propertyAttributeList = propertyAttributeList;

	}

	public void loadElementAttributes(VariamosGraphEditor editor,
			boolean editable, IntInstAttribute... editableElementAttributes) {
		RefasWidgetFactory factory = new RefasWidgetFactory(editor);

		widgets = new HashMap<String, WidgetR>();
		panel.removeAll();
		int contEd = 0;
		for (IntInstAttribute p : editableElementAttributes) {
			if (p != null) {
				WidgetR w = factory.getWidgetFor(p);
				if (w == null) {
					System.out.println("Error on AttributeEditorPanel" + p);
					continue;
				}

				w.getEditor().setEnabled(editable);
				contEd++;
				w.editVariable(p);

				w.addPropertyChangeListener("value",
						new PropertyChangeListener() {
							@Override
							public void propertyChange(PropertyChangeEvent evt) {
								revalidate();
								doLayout();
								// pack();
							}
						});

				widgets.put(p.getName(), w);
				w.setPreferredSize(new Dimension(150, 15));

				panel.add(new JLabel(p.getDisplayName() + ": "));
				panel.add(w);
				panel.add(new JLabel("          "));
			}
		}
		this.setEnabled(false);
		SpringUtilities.makeCompactGrid(panel, contEd, 3, 4, 4, 4, 4);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new SpringLayout());

		final JButton btnCancel = new JButton();
		btnCancel.setText("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (onCancel == null) {
					// dispose();

					propertyAttributeList.setEnabled(true);
					panel.removeAll();
					repaint();
					return;
				}
				// if( onCancel.onAction() )
				// dispose();
			}
		});

		buttonsPanel.add(btnCancel);

		final JButton btnAccept = new JButton();
		btnAccept.setText("Accept");
		btnAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if( onAccept == null )
				if (onAccept.onAction())
					// dispose();
					propertyAttributeList.setEnabled(true);
				panel.removeAll();
				panel.revalidate();
				repaint();
			}
		});

		buttonsPanel.add(btnAccept);

		final JButton btnDelete = new JButton();
		btnDelete.setText("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if( onAccept == null )
				if (onDelete.onAction())
					// dispose();
					propertyAttributeList.setEnabled(true);
				panel.removeAll();
				panel.revalidate();
				repaint();
			}
		});

		buttonsPanel.add(btnDelete);

		SpringUtilities.makeCompactGrid(buttonsPanel, 1, 3, 4, 4, 4, 4);

		add(buttonsPanel, BorderLayout.SOUTH);

	}

	public void setOnAccept(DialogButtonAction onAccept) {
		this.onAccept = onAccept;
	}

	public void setOnCancel(DialogButtonAction onCancel) {
		this.onCancel = onCancel;
	}

	public void setOnDelete(DialogButtonAction onDelete) {
		this.onDelete = onDelete;
	}
}
