package com.variamos.gui.perspeditor.panels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
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
public class CustomizeDialog extends JDialog {
	private HashMap<String, WidgetR> widgets;
	@SuppressWarnings("unused")
	private DialogButtonAction onAccept;
	private JTextArea current_content;
	private JTextArea new_content;
	private JLabel lab0;
	private JLabel lab1;
	private JLabel lab3;
	private JTextArea lab4;
	private JButton btnAccept;
	private JButton btnNext;
	private JPanel panel;
	private GridBagConstraints c;
	
	//current customization file
	private int current_cf;
	private int current_multi_cf;
	private int max_multi_cf;
	private boolean multi_cp;
	private boolean set_next_cp;
	private int current_folder;
	private int max_folders;
	private String current_id;
	private String current_cpoint;
	private String current_plan;

	static interface DialogButtonAction {
		public boolean onAction();
	}

	public CustomizeDialog(VariamosGraphEditor editor, ElemAttribute... arguments)
			throws URISyntaxException {
		super(editor.getFrame(), "Customize Derivation", true);
		
		current_folder = 0;
		max_folders = Fragmental.component_folders.size();
		current_cf = 1;
		current_multi_cf = 0;
		max_multi_cf = 0;
		multi_cp = false;
		set_next_cp = false;
		
		current_id="";
		current_cpoint="";
		current_plan="";

		setBounds(300, 200, 500, 350);
		setLayout(new BorderLayout());

		setPreferredSize(new Dimension(580, 500));

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		
		lab0 = new JLabel("Start customization process");
		c.insets = new Insets(0,10,10,0);
		c.weightx = 0.2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(lab0, c);
		
		lab1 = new JLabel("!Click start to begin!");
		c.insets = new Insets(0,0,10,10);
		c.weightx = 0.8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(lab1, c);
		
		JLabel lab = new JLabel("Default content");
		c.insets = new Insets(0,10,10,0);
		c.weightx = 0.2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(lab, c);
		
		current_content = new JTextArea(2,2);
		JScrollPane scrollPane = new JScrollPane(current_content); 
		c.insets = new Insets(0,0,10,10);
		current_content.setEnabled(false);
		c.weightx = 0.8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.ipady = 40;
		panel.add(scrollPane, c);
		
		JLabel lab2 = new JLabel("New customized content");
		c.insets = new Insets(0,10,10,0);
		c.weightx = 0.2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.ipady = 0;
		panel.add(lab2, c);
		
		new_content = new JTextArea(2,2);
		JScrollPane scrollPane2 = new JScrollPane(new_content); 
		new_content.setEnabled(false);
		c.insets = new Insets(0,0,10,10);
		c.weightx = 0.8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.ipady = 40;
		panel.add(scrollPane2, c);
		
		lab3 = new JLabel("Notification:");
		lab3.setForeground(Color.red);
		c.insets = new Insets(0,10,10,0);
		c.weightx = 0.2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.ipady = 0;
		panel.add(lab3, c);
		
		lab4 = new JTextArea(2,2);
		lab4.setForeground(Color.red);
		JScrollPane scrollPane3 = new JScrollPane(lab4); 
		lab4.setEnabled(false);
		c.insets = new Insets(0,0,10,10);
		c.weightx = 0.8;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.ipady = 40;
		panel.add(scrollPane3, c);		

		btnAccept = new JButton();
		c.insets = new Insets(0,10,10,10);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 2;
		c.ipady = 0;
		c.gridy = 4;
		btnAccept.setText("Start");
		btnAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Fragmental.component_folders.isEmpty()) {
					lab4.setText("Customization not carried out. " +
					"Please derivate a product first");
				}else {
					folder_iterator();
				}
			}
		});
		
		panel.add(btnAccept, c);
		
		btnNext = new JButton();
		btnNext.setText("Next");
		btnNext.setEnabled(false);
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(set_next_cp) {
					Fragmental.set_customize_one(current_id, current_cpoint, current_plan, new_content.getText());
				}
				folder_iterator();
			}
		});
		c.insets = new Insets(0,10,10,10);
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridwidth = 2;
		c.ipady = 0;
		c.gridy = 5;
		panel.add(btnNext, c);

		add(panel);

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
	
	public void folder_iterator(){
		for (int i = current_folder; i < max_folders; i++) {
			String foldername = Fragmental.component_folders.get(i);
			ArrayList<String> data_file = new ArrayList<String>();
			data_file = Fragmental.check_folder(foldername);

			if(data_file.get(0).equals("no")) {
				current_folder++;
			}else if(data_file.get(0).equals("one")) {
				current_folder++;
				btnAccept.setEnabled(false);
				
				current_id=data_file.get(1);
				current_cpoint=data_file.get(2);
				current_plan=data_file.get(3);
				
				this.customize_one();
				break;
			}else if(data_file.get(0).equals("multiple")){
				if(!multi_cp) {
					multi_cp = true;
					current_multi_cf=1;
					max_multi_cf=Integer.parseInt(data_file.get(1));
				}
				btnAccept.setEnabled(false);
				
				current_id=data_file.get((3*current_multi_cf)-1);
				current_cpoint=data_file.get((3*current_multi_cf));
				current_plan=data_file.get((3*current_multi_cf)+1);
				
				this.customize_one();
				if(current_multi_cf>=max_multi_cf) {
					multi_cp = false;
					current_multi_cf=1;
					max_multi_cf=0;
					current_folder++;					
				}
				current_multi_cf++;
				break;
			}
		}
		if(current_folder>=max_folders-1) {
			btnNext.setEnabled(false); // revisar con multiple
			lab4.setText("Customization finalized!!");
		}
	}
	
	public void customize_one() {
		lab1.setText(current_cf + ") FILE: " + current_id + " - POINT: "
				+ current_cpoint);
		current_cf++;
		String customization_code = Fragmental.customize_one(current_id,current_cpoint,current_plan);
		if(customization_code.isEmpty()) {
			lab4.setText("Error in CPOINT: " + current_cpoint + " - Verify it");
			new_content.setEnabled(false);
			current_content.setText("");
			new_content.setText("");
			btnNext.setEnabled(false);
			set_next_cp = false;
		}else{
			current_content.setText(customization_code);
			new_content.setText(customization_code);
			new_content.setEnabled(true);
			btnNext.setEnabled(true);
			set_next_cp = true;
		}
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