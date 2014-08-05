package com.variamos.gui.maineditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.GraphicsEnvironment;
*/











import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.TransferHandler;







import javax.swing.border.Border;

import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.mxgraph.examples.swing.editor.EditorToolBar;
/*import com.mxgraph.examples.swing.editor.EditorActions.ColorAction;
import com.mxgraph.examples.swing.editor.EditorActions.FontStyleAction;

import com.mxgraph.examples.swing.editor.EditorActions.KeyValueAction;
import com.mxgraph.examples.swing.editor.EditorActions.PrintAction;
*/
import com.mxgraph.examples.swing.editor.EditorActions.NewAction;
import com.mxgraph.examples.swing.editor.EditorActions.OpenAction;
import com.mxgraph.examples.swing.editor.EditorActions.SaveAction;
import com.mxgraph.examples.swing.editor.EditorActions.HistoryAction;

/*
 * import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxConstants;
*/
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxResources;
//import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxGraphView;

public class PerspectiveToolBar extends JToolBar
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8015443128436394471L;

	/**
	 * 
	 */
	public PerspectiveToolBar(final BasicGraphEditor editor, int orientation)
	{
		super(orientation);
		setBackground(new Color(220,220,220));
		setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createEmptyBorder(3, 3, 3, 3), getBorder()));
		setFloatable(false);

		addSeparator();

		JLabel lb ;
		lb = new JLabel("Perspectives:  ");
		add (lb);
		JButton bt = new JButton(mxResources.get("productLineButton"));
		add (bt);
		bt.addActionListener(new PerspectiveAction());
		bt.setBackground(new Color(200,200,200));
		bt = new JButton(mxResources.get("defectAnalyzerButton"));
		bt.setEnabled(false);
		add (bt);
		bt.addActionListener(new PerspectiveAction());
		bt = new JButton(mxResources.get("requirementsButton"));
		bt.setEnabled(false);
		add (bt);
		bt.addActionListener(new PerspectiveAction());
		// Sets the zoom in the zoom combo the current value
		
	}
}
