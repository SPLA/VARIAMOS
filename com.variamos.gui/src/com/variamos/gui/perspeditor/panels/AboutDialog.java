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
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import com.variamos.dynsup.interfaces.IntInstAttribute;
import com.variamos.dynsup.model.ElemAttribute;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.SpringUtilities;
import com.variamos.gui.perspeditor.widgets.WidgetR;

/**
 * A class to draw the About Dialog. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * @version 1.0
 * @since 2014-03-15
 */
@SuppressWarnings("serial")
public class AboutDialog extends JDialog {
	private HashMap<String, WidgetR> widgets;
	@SuppressWarnings("unused")
	private DialogButtonAction onAccept;

	static interface DialogButtonAction {
		public boolean onAction();
	}

	private static void open(URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) { /* TODO: error handling */
			}
		}
	}

	public AboutDialog(VariamosGraphEditor editor, ElemAttribute... arguments)
			throws URISyntaxException {
		super(editor.getFrame(), "About VariaMos", true);

		setBounds(300, 300, 300, 400);
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());

		setPreferredSize(new Dimension(330, 400));

		panel.add(newButton("                 VariaMos Tool - ",
				"http://variamos.com/", "http://variamos.com/"));
		panel.add(new JLabel(""));
		MainFrame mainFrame = editor.getMainFrame();
		panel.add(new JLabel("Version: VariaMos-"
				+ mainFrame.getVariamosVersionNumber() + " ("
				+ mainFrame.getVariamosVersionName() + ")"));
		panel.add(new JLabel("Built time: " + mainFrame.getVariamosBuild()));

		panel.add(newButton("Changelog: ",
				"http://variamos.com/home/category/changelog/",
				"http://variamos.com/home/category/changelog/"));
		panel.add(new JLabel("Used Libraries: "));

		JPanel panel2 = new JPanel();
		panel2.setLayout(new SpringLayout());

		panel2.add(newButton("", "Gluegen",
				"http://jogamp.org/git/?p=gluegen.git;a=blob;f=LICENSE.txt"));
		panel2.add(newButton("", "JGraphX",
				"https://github.com/jgraph/jgraphx/blob/master/license.txt"));
		panel2.add(newButton("", "Junit", "http://junit.org/license.html"));
		panel2.add(newButton("", "JPL",
				"http://www.swi-prolog.org/packages/jpl/java_api/"));

		panel2.add(newButton("", "sxfm",
				"http://gsd.uwaterloo.ca:8088/SPLOT/sxfm.html#"));
		panel2.add(newButton("", "jgprolog",
				"https://code.google.com/p/jgprolog/"));
		panel2.add(newButton("", "poi", "https://poi.apache.org/"));
		panel2.add(newButton("", "Gson",
				"https://code.google.com/p/google-gson/"));

		SpringUtilities.makeCompactGrid(panel2, 2, 4, 4, 4, 4, 4);

		panel.add(panel2);
		panel.add(new JLabel("Some icons art from:"));
		panel.add(newButton("(Highlightmarker) ", "Icon Archive",
				"https://iconarchive.com"));
		panel.add(newButton("(player, dead, false and refresh) ",
				"Icon Finder", "https://www.iconfinder.com"));
		panel.add(newButton("(hand for required) ", "Flat Icon",
				"http://www.flaticon.com/free-icon/pointer_108787#term=hand&page=1&position=66"));
		panel.add(newButton(
				"(Pinion for config) ",
				"Flat Icon",
				"http://www.flaticon.com/free-icon/settings_23408#term=configuration&page=1&position=3"));
		panel.add(new JLabel(
				"Organization of notification for elements and some icons:"));
		panel.add(new JLabel("Jose Andrï¿½s Moncada"));

		SpringUtilities.makeCompactGrid(panel, 14, 1, 4, 4, 4, 4);

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

	private JButton newButton(String label, String name, String link)
			throws URISyntaxException {
		final URI uri = new URI(link);

		class OpenUrlAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				open(uri);
			}
		}
		JButton button = new JButton();
		button.setText("<HTML>" + label + "<FONT color=\"#000099\"><U>" + name
				+ "</U></FONT>" + "</HTML>");
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setOpaque(false);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setBackground(Color.WHITE);
		button.setToolTipText(link);
		button.addActionListener(new OpenUrlAction());
		return button;
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