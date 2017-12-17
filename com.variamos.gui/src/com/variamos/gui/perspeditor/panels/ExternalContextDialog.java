package com.variamos.gui.perspeditor.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URISyntaxException;

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

/**
 * A class to support the dialog to control simulation of configurations. Part
 * of PhD work at University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-03-20
 * @see com.variamos.gui.pl.editor.VariabilityAttributeList
 */
public class ExternalContextDialog extends JDialog implements
		PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1478873242908074197L;
	@SuppressWarnings("unused")
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
	private int width = 680;
	private int height = 500;
	private MonitoringWorker monitoringWorker;
	private JTextArea results;
	private JTextField state;
	private JCheckBox adaptoOnInvalid;
	private JCheckBox monitorOpers;
	private JCheckBox monitorAssets;

	static interface DialogButtonAction {
		public boolean onAction();
	}

	public ExternalContextDialog(final VariamosGraphEditor editor) {
		super();
		setPreferredSize(new Dimension(width, height));
		setTitle("Simulation Control Dialog");
		// setVisible(true);
		String filesUrl = null;
		try {
			filesUrl = new File(ExternalContextDialog.class
					.getProtectionDomain().getCodeSource().getLocation()
					.toURI().getPath()).getParentFile().getPath()
					.replace('\\', '/');
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		generalPanel = new JPanel();
		generalPanel.setLayout(new BorderLayout());

		panel = new JPanel();
		panel.setLayout(new SpringLayout());
		JLabel lab = new JLabel(
				"Initial (Partial) Configuration File (Optional): ");
		lab.setToolTipText("Initial JSON config file. If empty, the simulation uses the current model configuration");
		panel.add(lab);
		initialConfigFile = new JTextField(filesUrl + "/monitor/ini.conf");
		panel.add(initialConfigFile);
		lab = new JLabel("Path of (partial) configuration files: ");
		lab.setToolTipText("Input context (variables values) and configuration (operationalizations, assets and features)");
		panel.add(lab);
		monitoringDirectory = new JTextField(filesUrl
				+ "/monitor/inputconfiguration/");
		panel.add(monitoringDirectory);
		lab = new JLabel("Path for the complete configurations: ");
		lab.setToolTipText("Output with the complete configuration after each execution");
		panel.add(lab);
		outputDirectory = new JTextField(filesUrl
				+ "/monitor/outoutconfiguration/");
		panel.add(outputDirectory);
		lab = new JLabel("Delay (seconds) between configurations: ");
		lab.setToolTipText("Waiting time (seconds) before executing the next configuration. Only applies if the option iterate over input files is selected");
		panel.add(lab);
		waitBetweenExecs = new JTextField("5");
		panel.add(waitBetweenExecs);
		lab = new JLabel("Delay (seconds) before a reconfiguration: ");
		lab.setToolTipText("Delay time (seconds) to visualize a non-valid configuration. After the delay, the simulation will find a valid configuration");
		panel.add(lab);
		waitAfterNoSolution = new JTextField("5");
		panel.add(waitAfterNoSolution);
		lab = new JLabel("Obtain variables from configuration files: ");
		lab.setToolTipText("Defines whether the variables values defined in the configuration files are considered for the partial configuration input or not");
		panel.add(lab);
		monitorVariables = new JCheckBox("monVariables", true);
		panel.add(monitorVariables);
		lab = new JLabel(
				"Obtain operationalizations from configuration files: ");
		lab.setToolTipText("Defines whether the operationalizations/leaf features in the configuration files are considered for the partial configuration input or not");
		panel.add(lab);
		monitorOpers = new JCheckBox("monOpers", true);
		panel.add(monitorOpers);
		lab = new JLabel("Obtain assets from configuration files: ");
		lab.setToolTipText("Defines whether the assets in the configuration files are considered for the partial configuration input or not");
		panel.add(lab);
		monitorAssets = new JCheckBox("monAssets", true);
		panel.add(monitorAssets);
		lab = new JLabel("Loop over configuration files: ");
		lab.setToolTipText("Performs simulation iterating over existing files. If not, the simulation waits for a new file created in the folder");
		panel.add(lab);
		fileIteration = new JCheckBox("fileIteration", true);
		panel.add(fileIteration);

		lab = new JLabel("Execute Analysis and Planning: ");
		lab.setToolTipText("Defines if the adaptation is perfomed. If not, the configuration is accepted as it is");
		panel.add(lab);
		mapeAP = new JCheckBox("mAPe", true);
		panel.add(mapeAP);
		mapeAP.setEnabled(false);

		panel.add(new JLabel("Auto-selecting first solution: "));
		firstSolutionOnly = new JCheckBox("FirstSolOnly", true);
		panel.add(firstSolutionOnly);
		firstSolutionOnly.setEnabled(false);
		// panel.add(new JLabel("Adapt only if configuration is invalid: "));
		// adaptoOnInvalid = new JCheckBox("AdaptOnInvalid", true);
		// panel.add(adaptoOnInvalid);
		// adaptoOnInvalid.setEnabled(false);
		panel.add(new JLabel("Monitoring state: "));
		state = new JTextField(("Not running"));
		state.setEnabled(false);
		panel.add(state);
		SpringUtilities.makeCompactGrid(panel, 12, 2, 4, 4, 4, 4);
		generalPanel.add(panel, BorderLayout.NORTH);
		JPanel notificationPanel = new JPanel();
		notificationPanel.setLayout(new SpringLayout());
		notificationPanel.add(new JLabel("Execution log: "));
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
							Float.parseFloat(waitBetweenExecs.getText()), Float
									.parseFloat(waitAfterNoSolution.getText()),
							monitorVariables.isSelected(), monitorOpers
									.isSelected(), monitorAssets.isSelected(),
							mapeAP.isSelected(), fileIteration.isSelected(),
							firstSolutionOnly.isSelected());
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
		btnCancel.setText("Stop and Close Dialog");
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
