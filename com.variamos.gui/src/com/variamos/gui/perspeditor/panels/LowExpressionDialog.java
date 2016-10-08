package com.variamos.gui.perspeditor.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.model.LowExpr;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.SpringUtilities;

/**
 * @author unknown
 *
 */
@SuppressWarnings("serial")
public class LowExpressionDialog extends JDialog {
	private List<LowExpr> lowExpressions;
	private LowExpressionButtonAction onAccept, onCancel, onDelete;
	private JPanel solutionPanel;
	private boolean displayVariableName = true;
	private int width = 950;
	private int height = 500;
	private boolean multiExpressions;
	private boolean displayTextExpression = false;
	private boolean editable;
	private String oldText;
	private boolean cancel = false;

	static interface LowExpressionButtonAction {
		public boolean onAction();
	}

	public LowExpressionDialog(VariamosGraphEditor editor,
			InstElement instElement, boolean multiExpression,
			List<LowExpr> instanceExpressions, boolean editable) {
		super(editor.getFrame(), "Low Expressions Editor");
		this.multiExpressions = multiExpression;
		this.editable = editable;
		setPreferredSize(new Dimension(width, height));
		this.initialize(instElement, instanceExpressions);
	}

	public void initialize(final InstElement element,
			List<LowExpr> instanceExpressions) {
		this.getContentPane().removeAll();
		// removeAll();
		if (this.getWidth() != 0)
			width = this.getWidth();
		if (this.getHeight() != 0)
			height = this.getHeight();

		this.setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout());
		JPanel parentPane = new JPanel();
		parentPane.setLayout(new SpringLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());

		if (instanceExpressions != null)
			this.lowExpressions = instanceExpressions;

		for (final LowExpr instanceExpression : this.lowExpressions) {
			solutionPanel = new JPanel();
			solutionPanel.setAutoscrolls(true);
			solutionPanel.setMaximumSize(new Dimension(900, 200));
			JTextArea textArea = new JTextArea();
			oldText = instanceExpression.getLowExpressions();
			textArea.setText(oldText);
			textArea.setSize(500, 500);
			textArea.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e) {
				}

				@Override
				public void focusLost(FocusEvent event) {
					String item = (String) ((JTextArea) event.getSource())
							.getText();
					if (item != null && !cancel) {
						instanceExpression.setLowExpressions(item);
					}
				}
			});

			solutionPanel.add(textArea);

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
				// JPanel txtPanel = new JPanel();
				JScrollPane sp = new JScrollPane(textExpression);
				sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				sp.setPreferredSize(new Dimension(this.getWidth() - 50, 600));
				// txtPanel.add(textExpression);
				// txtPanel.setPreferredSize(new Dimension(this
				// .getWidth() - 30, 100));
				panel.add(textExpression);
			} else
				panel.add(new JLabel());

		}
		JPanel options = new JPanel();
		panel.add(options);
		SpringUtilities.makeCompactGrid(panel,
				this.lowExpressions.size() * 2 + 1, 1, 4, 4, 4, 4);

		parentPane.add(panel);// , BorderLayout.CENTER);

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

		// final JButton btnCancel = new JButton();
		// btnCancel.setText("Cancel");
		// btnCancel.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// cancel = true;
		// if (onCancel == null) {
		// dispose();
		// return;
		// }
		// if (onCancel.onAction())
		// dispose();
		// }
		// });
		//
		// buttonsPanel.add(btnCancel);

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

		SpringUtilities.makeCompactGrid(buttonsPanel, 1, 2, 4, 4, 4, 4);

		parentPane.add(buttonsPanel);// , BorderLayout.SOUTH);

		SpringUtilities.makeCompactGrid(parentPane, 2, 1, 4, 4, 4, 4);

		add(parentPane);

		getRootPane().setDefaultButton(btnAccept);
		// getRootPane().registerKeyboardAction(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// btnCancel.doClick();
		// }
		//
		// }, KeyStroke.getKeyStroke("ESCAPE"),
		// JComponent.WHEN_IN_FOCUSED_WINDOW);
		pack();
		revalidate();
		repaint();
	}

	/**
	 * @return
	 */
	public List<LowExpr> getExpressions() {
		return lowExpressions;
	}

	public void center() {
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setOnAccept(LowExpressionButtonAction onAccept) {
		this.onAccept = onAccept;
	}

	public void setOnDelete(LowExpressionButtonAction onDelete) {
		this.onDelete = onDelete;
	}

	public void setOnCancel(LowExpressionButtonAction onCancel) {
		this.onCancel = onCancel;
	}
}
