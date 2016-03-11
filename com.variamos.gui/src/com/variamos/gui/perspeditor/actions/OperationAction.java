package com.variamos.gui.perspeditor.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class OperationAction extends AbstractEditorAction {

	public OperationAction() {
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		Component editor = getComponentEditor(e);
		VariamosGraphEditor vg = null;
		if (editor instanceof VariamosGraphEditor)
			vg = (VariamosGraphEditor) editor;
		if (editor instanceof MainFrame) {
			int i = ((MainFrame) editor).getPerspective();
			vg = ((MainFrame) editor).getEditor(i);
		}
		String operation = (String) ((JMenuItem) e.getSource()).getName();

		vg.executeOperation(operation);

		if (operation.startsWith("N:")) {
			vg.editPropertiesRefas();
			vg.updateDashBoard(false, true);
			vg.updateSimulResults();
		}
	}
}