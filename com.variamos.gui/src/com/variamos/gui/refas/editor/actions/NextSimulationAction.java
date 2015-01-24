package com.variamos.gui.refas.editor.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.refas.editor.RefasGraph;
import com.variamos.refas.core.simulationmodel.Refas2Hlcl;

@SuppressWarnings("serial")
public class NextSimulationAction extends AbstractEditorAction {

	public NextSimulationAction() {
		this.putValue(SHORT_DESCRIPTION, mxResources.get("nextSimulation"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.clearNotificationBar();
		editor.executeSimulation(false, Refas2Hlcl.SIMUL_EXEC, true, "Simul");
	}

}
