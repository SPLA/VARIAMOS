package com.variamos.gui.perspeditor.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.SpringUtilities;

/**
 * A class to create the dialog to associate element's expressions to
 * operations. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-11-05
 */
public class ElementsOperationAssociationDialog extends JDialog implements
		PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1478873242908074197L;
	private DialogButtonAction onClose;
	private JPanel generalPanel = null;
	private JPanel panel = null;
	private int width = 1350;
	private int height = 760;

	static interface DialogButtonAction {
		public boolean onAction();
	}

	public ElementsOperationAssociationDialog(final VariamosGraphEditor editor,
			int dialog) {
		super();
		this.setMinimumSize(new Dimension(width, height));
		setPreferredSize(new Dimension(width, height));
		switch (dialog) {
		case 0:
			setTitle("Association of Meta-Concept�s Meta-Expressions to Suboperations");
			break;
		case 1:
			setTitle("Association of Meta-Concept�s Meta-Attributes to Subperations (Input/Output)");
			break;
		case 2:
			setTitle("Association of Meta-Concept�s Meta-Attributes to Labelings");
			break;
		}
		setVisible(true);

		generalPanel = new JPanel();

		generalPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		generalPanel.add(new ElementsOperationAssociationPanel(editor, dialog),
				gbc);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new SpringLayout());

		final JButton btnStart = new JButton();
		btnStart.setText("Close");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if( onAccept == null )

				dispose();

				revalidate();
				repaint();
			}
		});

		buttonsPanel.add(btnStart);
		// buttonsPanel.add(btnCancel);

		SpringUtilities.makeCompactGrid(buttonsPanel, 1, 1, 4, 4, 4, 4);

		gbc.weighty = 0;
		gbc.gridy = 1;
		generalPanel.add(buttonsPanel, gbc);

		setLayout(new BorderLayout());
		add(generalPanel, BorderLayout.CENTER);

		pack();
		revalidate();
		repaint();
	}

	public void setOnClose(DialogButtonAction onClose) {
		this.onClose = onClose;
	}

	public void center() {
		// setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// if (evt.getPropertyName().equals("progress"))
		{
			revalidate();
			repaint();
		}

	}

}
