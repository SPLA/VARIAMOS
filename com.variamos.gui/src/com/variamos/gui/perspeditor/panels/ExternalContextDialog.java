package com.variamos.gui.perspeditor.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.SpringUtilities;

public class ExternalContextDialog extends JDialog implements
		PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1478873242908074197L;
	private DialogButtonAction onStart, onStop, onStopAndClose;
	private JPanel generalPanel = null;
	private JPanel panel = null;
	private JTextField initialConfigFile;
	private JTextField monitoringDirectory;
	private JTextField outputDirectory;
	private JTextField waitBetweenExecs;
	private JTextField waitAfterNoSolution;
	private JCheckBox monitorVariables;
	private JCheckBox mapeAP;
	private JCheckBox fileIteration;
	private JCheckBox firstSolutionOnly;
	private int width = 480;
	private int height = 500;
	private MonitoringWorker monitoringWorker;
	private JTextArea results;
	private JTextField state;
	private JCheckBox adaptoOnInvalid;
	private JCheckBox monitorOpers;

	static interface DialogButtonAction {
		public boolean onAction();
	}

	public ExternalContextDialog(final VariamosGraphEditor editor) {
		super();
		setPreferredSize(new Dimension(width, height));
		setTitle("MAPE control");
		// setVisible(true);

		generalPanel = new JPanel();
		generalPanel.setLayout(new BorderLayout());

		panel = new JPanel();
		panel.setLayout(new SpringLayout());
		panel.add(new JLabel(
				"Initial Config File (Empty to use current values): "));
		initialConfigFile = new JTextField("Z:/monitor/ini.conf");
		panel.add(initialConfigFile);
		panel.add(new JLabel("Monitoring Directory (read context & config): "));
		monitoringDirectory = new JTextField("Z:/monitor/systemtoobj/");
		panel.add(monitoringDirectory);
		panel.add(new JLabel("Executing Directory (write context & config): "));
		outputDirectory = new JTextField("Z:/monitor/objtosystem/");
		panel.add(outputDirectory);
		panel.add(new JLabel("Monitoring speed (seconds): "));
		waitBetweenExecs = new JTextField("5");
		panel.add(waitBetweenExecs);
		panel.add(new JLabel("Delay after no solution (seconds): "));
		waitAfterNoSolution = new JTextField("5");
		panel.add(waitAfterNoSolution);
		panel.add(new JLabel("Include external variables: "));
		monitorVariables = new JCheckBox("monVariables", true);
		panel.add(monitorVariables);
		panel.add(new JLabel("Include external operationalizations: "));
		monitorOpers = new JCheckBox("monOpers", true);
		panel.add(monitorOpers);
		panel.add(new JLabel("Execute Analysis and Planning: "));
		mapeAP = new JCheckBox("mAPe", true);
		panel.add(mapeAP);
		mapeAP.setEnabled(false);
		panel.add(new JLabel("Loop iteration over existing files: "));
		fileIteration = new JCheckBox("fileIteration", true);
		panel.add(fileIteration);
		panel.add(new JLabel("Auto-selecting first solution: "));
		firstSolutionOnly = new JCheckBox("FirstSolOnly", true);
		panel.add(firstSolutionOnly);
		firstSolutionOnly.setEnabled(false);
		panel.add(new JLabel("Adapt only if configuration is invalid: "));
		adaptoOnInvalid = new JCheckBox("AdaptOnInvalid", true);
		panel.add(adaptoOnInvalid);
		adaptoOnInvalid.setEnabled(false);
		panel.add(new JLabel("Monitoring state: "));
		state = new JTextField(("Not running"));
		state.setEnabled(false);
		panel.add(state);
		SpringUtilities.makeCompactGrid(panel, 12, 2, 4, 4, 4, 4);
		generalPanel.add(panel, BorderLayout.NORTH);
		JPanel notificationPanel = new JPanel();
		notificationPanel.setLayout(new SpringLayout());
		notificationPanel.add(new JLabel("MAPE log: "));
		results = new JTextArea("");
		results.setEditable(false);
		notificationPanel.add(new JScrollPane(results));
		SpringUtilities.makeCompactGrid(notificationPanel, 1, 2, 4, 4, 4, 4);
		generalPanel.add(notificationPanel, BorderLayout.CENTER);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new SpringLayout());

		final JButton btnStart = new JButton();
		btnStart.setText("Start Monitoring");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if( onAccept == null )
				// if (onStart.onAction()) {
				if (state.getText().equals("Not running")) {
					monitoringWorker = new MonitoringWorker(editor,
							initialConfigFile.getText(), monitoringDirectory
									.getText(), outputDirectory.getText(),
							Integer.parseInt(waitBetweenExecs.getText()),
							Integer.parseInt(waitAfterNoSolution.getText()),
							monitorVariables.isSelected(), monitorOpers
									.isSelected(), mapeAP.isSelected(),
							fileIteration.isSelected(), firstSolutionOnly
									.isSelected());
					monitoringWorker.execute();
					monitoringWorker
							.addPropertyChangeListener(ExternalContextDialog.this);
					state.setText("Running");

				}

				// }
				revalidate();
				repaint();
			}
		});

		buttonsPanel.add(btnStart);

		final JButton btnStop = new JButton();
		btnStop.setText("Stop Monitoring");
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if( onAccept == null )
				if (monitoringWorker != null)
					monitoringWorker.setCanceled(true);
				state.setText("Not running");
				revalidate();
				repaint();
			}
		});

		buttonsPanel.add(btnStop);
		final JButton btnCancel = new JButton();
		btnCancel.setText("Stop and Close");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (onStopAndClose == null) {
					if (monitoringWorker != null)
						monitoringWorker.setCanceled(true);
					state.setText("Not Running");
					dispose();
					return;
				}
				// if( onCancel.onAction() )
				// dispose();
			}
		});

		buttonsPanel.add(btnCancel);

		SpringUtilities.makeCompactGrid(buttonsPanel, 1, 3, 4, 4, 4, 4);

		generalPanel.add(buttonsPanel, BorderLayout.SOUTH);
		add(generalPanel);

		pack();
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
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// if (evt.getPropertyName().equals("progress"))
		{
			results.setText(monitoringWorker.getResults());
			revalidate();
			repaint();
		}

	}
}
