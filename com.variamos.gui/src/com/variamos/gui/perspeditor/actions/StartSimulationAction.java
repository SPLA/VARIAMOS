package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perpseditor.panels.VariamosDashBoardFrame;
import com.variamos.perspsupport.perspmodel.Refas2Hlcl;
import com.variamos.perspsupport.perspmodel.RefasModel;

@SuppressWarnings("serial")
public class StartSimulationAction extends AbstractEditorAction {

	public StartSimulationAction() {
		this.putValue(SHORT_DESCRIPTION, mxResources.get("startSimulation"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.clearNotificationBar();
		editor.clearElementState(Refas2Hlcl.SIMUL_EXEC);
		editor.executeSimulation(true, Refas2Hlcl.SIMUL_EXEC, true, "Simul");
		editor.updateDashBoard(true, true);
		
	}
}
