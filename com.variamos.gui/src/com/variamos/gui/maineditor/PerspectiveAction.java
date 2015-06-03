package com.variamos.gui.maineditor;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import com.mxgraph.util.mxResources;

/**
 * A class to support perspectives button actions on mainframe. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-02-03
 */

@SuppressWarnings("serial")
public class PerspectiveAction extends AbstractEditorAction {

	private PerspectiveToolBar perspective;


	public PerspectiveAction(PerspectiveToolBar perspective) {
		this.perspective = perspective;
	}

	/**
		 * 
		 */

	public void actionPerformed(ActionEvent e) {
		newActionPerformed(e);
	}

	private void newActionPerformed(ActionEvent e) {

		VariamosGraphEditor editor = getEditor(e);
		MainFrame mainFrame = editor.getMainFrame();
		int perspectiveInd = mainFrame.getPerspective();
		perspective = editor.installToolBar(mainFrame, perspectiveInd);
		 
		JButton jb = (JButton) e.getSource();
		if (perspectiveInd != 1
				&& jb.getText().equals(mxResources.get("semanticPerspButton"))) {
			System.out.println("semanticPerspButton");
			mainFrame.setPerspective(1);
		}
		if (perspectiveInd != 2
				&& jb.getText().equals(mxResources.get("modelingPerspButton"))) {
			mainFrame.setPerspective(2);

			System.out.println("modelingPerspButton");
		}
		if (perspectiveInd != 3
				&& jb.getText().equals(mxResources.get("syntaxPerspButton"))) {
			mainFrame.setPerspective(3);
			System.out.println("syntaxPerspButton");
		}

		if (perspectiveInd != 4
				&& jb.getText()
						.equals(mxResources.get("simulationPerspButton"))) {
			mainFrame.setPerspective(4);
			System.out.println("simulationPerspButton");
		}	
		perspective.updatePerspective(mainFrame.getPerspective());
		mainFrame.validate();
		mainFrame.repaint();
	}
}
