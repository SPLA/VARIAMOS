package com.variamos.gui.maineditor;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

/**
 * A class to support perspectives button actions on mainframe. Part of PhD work
 * at University of Paris 1
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
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

	@Override
	public void actionPerformed(ActionEvent e) {
		newActionPerformed(e);
	}

	private void newActionPerformed(ActionEvent e) {

		VariamosGraphEditor editor = getEditor(e);
		String buttonText = ((JButton) e.getSource()).getText();
		editor.updatePespectiveMenuTab(buttonText);
	}
}

