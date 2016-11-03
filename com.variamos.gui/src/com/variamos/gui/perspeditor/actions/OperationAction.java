package com.variamos.gui.perspeditor.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;

import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphComponent;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class OperationAction extends AbstractEditorAction {

	public OperationAction() {
	}

	/**
		 * 
		 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Component editor = getComponentEditor(e);
		VariamosGraphEditor vg = null;
		if (editor instanceof VariamosGraphEditor)
			vg = (VariamosGraphEditor) editor;
		if (editor instanceof MainFrame) {
			int i = ((MainFrame) editor).getPerspective();
			vg = ((MainFrame) editor).getEditor(i);
		}
		String operation = ((JMenuItem) e.getSource()).getName();
		List<String> operations = new ArrayList<String>();

		// FIXME review why this is needed for verification operations
		vg.updateObjects();

		if (operation.startsWith("exec-all-ver-")) {
			// FIXME get all operations to execute
			// for ()
			// operations.add (oper);
		} else
			operations.add(operation);

		// FIXME only for simulation
		((VariamosGraphComponent) vg.getGraphComponent())
				.setSimulationStarted(true);
		vg.callOperations(operations);

		if (operation.startsWith("N:")) {
			vg.editPropertiesRefas();
			vg.updateDashBoard(false, true);
		}

		vg.updateSimulResults();
	}
}