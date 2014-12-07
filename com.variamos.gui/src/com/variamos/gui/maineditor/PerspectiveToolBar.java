package com.variamos.gui.maineditor;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import com.mxgraph.util.mxResources;
/*
 * import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxConstants;
*/
//import com.mxgraph.view.mxGraph;

public class PerspectiveToolBar extends JToolBar
{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8015443128436394471L;
	private JButton buttons[] = new JButton[5];
	private BasicGraphEditor editor;

	/**
	 * 
	 */
	public PerspectiveToolBar(final BasicGraphEditor editor, int orientation, int perspective)
	{
		super(orientation);
		this.editor = editor;
		setBackground(new Color(220,220,220));
		setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createEmptyBorder(3, 3, 3, 3), getBorder()));
		setFloatable(false);

		addSeparator();

		JLabel lb ;
		lb = new JLabel("Perspectives:  ");
		add (lb);
		buttons[3] = new JButton(mxResources.get("metamodelingPerspButton"));
		//buttons[2].setEnabled(false);
		add (buttons[3]);
		buttons[3].addActionListener(new PerspectiveAction(this));
		
		buttons[2] = new JButton(mxResources.get("modelingPerspButton"));
		//buttons[2].setEnabled(false);
		add (buttons[2]);
		buttons[2].addActionListener(new PerspectiveAction(this));
		
		buttons[4] = new JButton(mxResources.get("simulationPerspButton"));
		//buttons[2].setEnabled(false);
		add (buttons[4]);
		buttons[4].addActionListener(new PerspectiveAction(this));
		
		buttons[0] = new JButton(mxResources.get("plPerspButton"));
		add (buttons[0]);
		buttons[0].addActionListener(new PerspectiveAction(this));
		
		buttons[1] = new JButton(mxResources.get("defectAnalyzerPerspButton"));
		//buttons[1].setEnabled(false);		
		add (buttons[1]);
		buttons[1].addActionListener(new PerspectiveAction(this));
		
		buttons[perspective].setSelected(true);
		
	}
	public void updateButtons()
	{
		for (int i = 0; i<4; i++)
		{
			if (i == editor.getPerspective())
				buttons[i].setSelected(true);
			else
				buttons[i].setSelected(false);
			editor.setLayout(editor.getPerspective());

		}

	}
}
