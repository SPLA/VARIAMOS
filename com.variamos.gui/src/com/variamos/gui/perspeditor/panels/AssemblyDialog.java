package com.variamos.gui.perspeditor.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.variamos.dynsup.interfaces.IntInstAttribute;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.SpringUtilities;
import com.variamos.gui.perspeditor.widgets.WidgetR;
import com.variamos.reasoning.fragop.Fragmental;

/**
 * A class to configure the domain implementation assembly
 * 
 * @author Daniel Correa <yo@danielgara.com>
 * @version 1.0
 * @since 2018-01-09
 */
@SuppressWarnings("serial")
public class AssemblyDialog extends JDialog {
	private HashMap<String, WidgetR> widgets;
	@SuppressWarnings("unused")
	private DialogButtonAction onAccept;
	private JTextField new_assets_path;
	private JTextField new_assembled_path;

	static interface DialogButtonAction {
		public boolean onAction();
	}

	public AssemblyDialog(VariamosGraphEditor editor, ElemAttribute... arguments)
			throws URISyntaxException {
		super(editor.getFrame(), "Configure Integration Testing", true);

		setBounds(300, 200, 300, 150);
		setLayout(new BorderLayout());

		setPreferredSize(new Dimension(480, 400));

		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());		
		JLabel lab = new JLabel("Global Assets Folder Path: ");
		panel.add(lab);
		String asset_path= Fragmental.assets_folder;
		String assemble_path= Fragmental.assembled_folder;
		new_assets_path = new JTextField(asset_path);
		panel.add(new_assets_path);
		lab = new JLabel("Global Integration Folder Path: ");
		panel.add(lab);
		new_assembled_path = new JTextField(assemble_path);
		panel.add(new_assembled_path);

		SpringUtilities.makeCompactGrid(panel, 2, 2, 2, 2, 2, 2);
		add(panel, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new SpringLayout());

		final JButton btnAccept = new JButton();
		btnAccept.setText("Save");
		btnAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Fragmental.assets_folder=new_assets_path.getText();
				Fragmental.assembled_folder=new_assembled_path.getText();
				dispose();
			}
		});

		buttonsPanel.add(new JLabel("           "));
		buttonsPanel.add(btnAccept);

		buttonsPanel.add(new JLabel(""));
		SpringUtilities.makeCompactGrid(buttonsPanel, 1, 3, 4, 4, 4, 4);

		add(buttonsPanel, BorderLayout.SOUTH);

		getRootPane().setDefaultButton(btnAccept);
		getRootPane().registerKeyboardAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnAccept.doClick();
			}
		}, KeyStroke.getKeyStroke("ESCAPE"), JComponent.WHEN_IN_FOCUSED_WINDOW);
		setVisible(true);
		pack();
	}

	/**
	 * @return
	 */
	public Map<String, IntInstAttribute> getParameters() {
		Map<String, IntInstAttribute> map = new HashMap<>();

		for (String s : widgets.keySet()) {
			IntInstAttribute v = widgets.get(s).getInstAttribute();
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
}