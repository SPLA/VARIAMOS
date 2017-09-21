package com.variamos.gui.maineditor;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import com.mxgraph.util.mxResources;
import com.variamos.gui.core.mxgraph.editor.BasicGraphEditor;

/**
 * A class to support perspectives buttons on mainframe. Part of PhD work at
 * University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-02-03
 */

public class PerspectiveToolBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8015443128436394471L;
	private JButton buttons[] = new JButton[5];
	private MainFrame mainFrame;

	/**
	 * Constructor for a modeling perspective
	 */
	@Deprecated
	public PerspectiveToolBar(final BasicGraphEditor editor, int orientation,
			int perspective) {
		super(orientation);
		// this.editor = editor;
		setBackground(new Color(220, 220, 220));
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(3, 3, 3, 3), getBorder()));
		setFloatable(false);

		addSeparator();

		JLabel lb;
		lb = new JLabel("Perspectives:  ");
		add(lb);
		buttons[3] = new JButton(mxResources.get("metamodelingPerspButton"));
		// buttons[2].setEnabled(false);
		add(buttons[3]);
		buttons[3].addActionListener(new PerspectiveAction(this));

		buttons[2] = new JButton(mxResources.get("modelingPerspButton"));
		// buttons[2].setEnabled(false);
		add(buttons[2]);
		buttons[2].addActionListener(new PerspectiveAction(this));

		buttons[4] = new JButton(mxResources.get("simulationPerspButton"));
		// buttons[2].setEnabled(false);
		add(buttons[4]);
		buttons[4].addActionListener(new PerspectiveAction(this));

		// buttons[0] = new JButton(mxResources.get("plPerspButton"));
		// add (buttons[0]);
		// buttons[0].addActionListener(new PerspectiveAction(this,false));

		buttons[1] = new JButton(mxResources.get("defectAnalyzerPerspButton"));
		// buttons[1].setEnabled(false);
		add(buttons[1]);
		buttons[1].addActionListener(new PerspectiveAction(this));

		// buttons[perspective].setSelected(true);

	}

	/**
	 * Constructor for a all perspectives
	 */
	public PerspectiveToolBar(final MainFrame mainFrame, int orientation,
			int perspective) {
		super(orientation);
		this.mainFrame = mainFrame;
		setBackground(new Color(220, 220, 220));
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(3, 3, 3, 3), getBorder()));
		setFloatable(false);

		addSeparator();

		JLabel lb;
		lb = new JLabel("Perspectives:  ");
		add(lb);
		if (mainFrame.isAdvancedPerspective()) {
			buttons[1] = new JButton(mxResources.get("semanticPerspButton"));
			buttons[1].setBorder(new EmptyBorder(3, 7, 3, 7));

			add(buttons[1]);

			buttons[1].addActionListener(new PerspectiveAction(this));

			buttons[3] = new JButton(mxResources.get("syntaxPerspButton"));
			buttons[3].setBorder(new EmptyBorder(3, 7, 3, 7));
			add(buttons[3]);

			buttons[3].addActionListener(new PerspectiveAction(this));
		}
		buttons[2] = new JButton(mxResources.get("modelingPerspButton"));
		buttons[2].setBorder(new EmptyBorder(3, 7, 3, 7));
		add(buttons[2]);
		buttons[2].addActionListener(new PerspectiveAction(this));

		buttons[4] = new JButton(mxResources.get("simulationPerspButton"));
		buttons[4]
				.setToolTipText("Remember to execute the Core Update after changing the model");
		buttons[4].setBorder(new EmptyBorder(3, 7, 3, 7));
		add(buttons[4]);
		buttons[4].addActionListener(new PerspectiveAction(this));

		// buttons[0] = new JButton(mxResources.get("plPerspButton"));
		// add (buttons[0]);
		// buttons[0].addActionListener(new PerspectiveAction(this));
		if (buttons[perspective] != null)
			buttons[perspective].setSelected(true);

	}

	public void updatePerspective(int ind) {
		mainFrame.setLayout();
	}
}

