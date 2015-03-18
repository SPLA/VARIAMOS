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
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;

import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.SpringUtilities;
import com.variamos.gui.perspeditor.widgets.RefasWidgetFactory;
import com.variamos.gui.perspeditor.widgets.WidgetR;
import com.variamos.perspsupport.syntaxsupport.EditableElementAttribute;

/**
 * A class to draw the About Dialog. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2014-03-15
 */
@SuppressWarnings("serial")
public class AboutDialog extends JDialog {
	private HashMap<String, WidgetR> widgets;
	private DialogButtonAction onAccept;

	static interface DialogButtonAction {
		public boolean onAction();
	}

	public AboutDialog(VariamosGraphEditor editor,
			EditableElementAttribute... arguments) {
		super(editor.getFrame(), "About VariaMos");

		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());

		setPreferredSize(new Dimension(330, 200));


		panel.add(new JLabel("               VariaMos Tool"));
		panel.add(new JLabel(""));
		MainFrame mainFrame = editor.getMainFrame();
		panel.add(new JLabel("Version: VariaMos-"+mainFrame.getVariamosVersionNumber()+" (" +mainFrame.getVariamosVersionName()+")"));
		panel.add(new JLabel("Built time: "+mainFrame.getVariamosBuild()));

		panel.add(new JLabel("Changelog: http://variamos.com/home/category/changelog/"));
		panel.add(new JLabel("Libraries: mxgraph, gluegen, gson, interprolog, jpl, "));
		panel.add(new JLabel("               splot_prolog, jgprolog, poi, sxmf, junit"));

		panel.add(new JLabel("Some icons art from ..."));
		

		SpringUtilities.makeCompactGrid(panel,8, 1, 4, 4, 4, 4);

		add(panel, BorderLayout.CENTER); 

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new SpringLayout());

		final JButton btnAccept = new JButton();
		btnAccept.setText("Accept");
		btnAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
}