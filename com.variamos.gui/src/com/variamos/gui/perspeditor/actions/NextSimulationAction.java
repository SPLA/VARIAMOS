package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.util.mxResources;
import com.variamos.dynsup.translation.ModelExpr2HLCL;
import com.variamos.gui.core.viewcontrollers.AbstractVariamoGUIAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
@Deprecated
public class NextSimulationAction extends AbstractVariamoGUIAction {


	public NextSimulationAction() {
		this.putValue(SHORT_DESCRIPTION, mxResources.get("nextSimulation"));
	}

	/**
		 * 
		 */
	@Override
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.clearNotificationBar();
		editor.executeSimulation(false, false, ModelExpr2HLCL.SIMUL_EXEC, true,
				"Simul");
		// boolean wasFirst = editor.executeSimulation(false,
		// Refas2Hlcl.SIMUL_EXEC, true, "Simul");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		editor.updateDashBoard(true, false, true);
		editor.editPropertiesRefas();
		editor.updateSimulResults();
	}

}
