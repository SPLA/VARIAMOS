package com.variamos.gui.perspeditor.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
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
	@SuppressWarnings("unused")
	private DialogButtonAction onStart, onStop, onStopAndClose;
	private JPanel generalPanel = null;
	private JPanel panel = null;
	private int width = 900;
	private int height = 650;

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
			setTitle("Association of Concept’s Expressions to Operations");
			break;
		case 1:
			setTitle("Association of Concept’s Variables to Operations In/Out");
			break;
		case 2:
			setTitle("Association of Concept’s Variables to Labelings");
			break;
		}
		setVisible(true);

		generalPanel = new JPanel();
		generalPanel.setLayout(new BorderLayout());

		panel = new JPanel();
		panel.setLayout(new SpringLayout());
		panel.add(new ElementsOperationAssociationPanel(editor, dialog));

		generalPanel.add(panel, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new SpringLayout());

		final JButton btnStart = new JButton();
		btnStart.setText("Close");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if( onAccept == null )
				// if (onStart.onAction()) {

				// }
				revalidate();
				repaint();
			}
		});

		buttonsPanel.add(btnStart);

		final JButton btnStop = new JButton();
		btnStop.setText("Cancel");
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if( onAccept == null )
				revalidate();
				repaint();
			}
		});

		// buttonsPanel.add(btnStop);
		final JButton btnCancel = new JButton();
		btnCancel.setText("Refresh");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// if( onCancel.onAction() )
				// dispose();
			}
		});

		// buttonsPanel.add(btnCancel);

		SpringUtilities.makeCompactGrid(buttonsPanel, 1, 1, 4, 4, 4, 4);

		generalPanel.add(buttonsPanel, BorderLayout.SOUTH);
		add(generalPanel);

		// pack();
		revalidate();
		repaint();
	}

	public void setOnStart(DialogButtonAction onStart) {
		this.onStart = onStart;
	}

	public void setOnStop(DialogButtonAction onStop) {
		this.onStop = onStop;
	}

	public void setOnStopAndClose(DialogButtonAction onStopAndClose) {
		this.onStopAndClose = onStopAndClose;
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
