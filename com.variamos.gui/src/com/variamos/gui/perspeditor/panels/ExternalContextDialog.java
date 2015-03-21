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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.SpringUtilities;

public class ExternalContextDialog extends JDialog implements
PropertyChangeListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1478873242908074197L;
	private DialogButtonAction onStart, onStop, onStopAndClose;
	private JPanel generalPanel = null;
	private JPanel panel = null;
	private JTextField monitoringDirectory;
	private JTextField outputDirectory;
	private JTextField seconds;
	private JCheckBox iterate;
	private JCheckBox firstSolution;
	private int width = 450;
	private int height = 300;
	private MonitoringWorker monitoringWorker;
	private JTextArea results;
	private JTextField state;

	static interface DialogButtonAction {
		public boolean onAction();
	}

	public ExternalContextDialog(final VariamosGraphEditor editor) {
		super();
		setPreferredSize(new Dimension(width, height));
		setTitle("External Monitoring");
		// setVisible(true);

		generalPanel = new JPanel();
		generalPanel.setLayout(new BorderLayout());

		panel = new JPanel();
		panel.setLayout(new SpringLayout());
		panel.add(new JLabel("Monitoring Directory: "));
		monitoringDirectory = new JTextField("Z:/monitor/context/");
		panel.add(monitoringDirectory);
		panel.add(new JLabel("Executing Directory: "));
		outputDirectory = new JTextField("Z:/monitor/output/");
		panel.add(outputDirectory);
		panel.add(new JLabel("Monitoring speed (seconds): "));
		seconds = new JTextField("10");
		panel.add(seconds);
		panel.add(new JLabel("Iterate over files (not newest processed): "));
		iterate = new JCheckBox("FileIter", true);
		panel.add(iterate);
		iterate.setEnabled(false);
		panel.add(new JLabel("Auto-selecting first solution: "));
		firstSolution = new JCheckBox("FirtSol", true);
		panel.add(firstSolution);
		firstSolution.setEnabled(false);
		panel.add(new JLabel("Monitoring state: "));
		state = new JTextField(("Not running"));
		state.setEnabled(false);
		panel.add(state);
		panel.add(new JLabel("Monitoring actions: "));
		results = new JTextArea("");
		results.setEditable(false);
		panel.add(results);
		SpringUtilities.makeCompactGrid(panel, 7, 2, 4, 4, 4, 4);
		generalPanel.add(panel, BorderLayout.CENTER);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new SpringLayout());

		final JButton btnStart = new JButton();
		btnStart.setText("Start Monitoring");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if( onAccept == null )
				// if (onStart.onAction()) {
				if (state.getText().equals("Not running"))
				{
					monitoringWorker = new MonitoringWorker(editor, monitoringDirectory
							.getText(), outputDirectory.getText(), Integer
							.parseInt(seconds.getText()), iterate.isSelected(),
							firstSolution.isSelected());
					monitoringWorker.execute();
					monitoringWorker.addPropertyChangeListener(ExternalContextDialog.this);
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
				if(monitoringWorker !=null)
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
//		if (evt.getPropertyName().equals("progress"))
		{
			results.setText(monitoringWorker.getResults());
			revalidate();
			repaint();
		}
		
	}
}
