package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.util.mxResources;
import com.variamos.dynsup.translation.ModelExpr2HLCL;
import com.variamos.gui.core.viewcontrollers.AbstractVariamoGUIAction;
import com.variamos.gui.maineditor.VariamosGraphComponent;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
@Deprecated
public class StartSimulationAction extends AbstractVariamoGUIAction {

	public StartSimulationAction() {
		this.putValue(SHORT_DESCRIPTION, mxResources.get("startSimulation"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.clearNotificationBar();
		editor.clearElementState(ModelExpr2HLCL.SIMUL_EXEC);
		editor.executeSimulation(true, true, ModelExpr2HLCL.SIMUL_EXEC, true,
				"Simul");
		// editor.updateDashBoard(true, true);
		((VariamosGraphComponent) editor.getGraphComponent())
				.setSimulationStarted(true);
	}
}
