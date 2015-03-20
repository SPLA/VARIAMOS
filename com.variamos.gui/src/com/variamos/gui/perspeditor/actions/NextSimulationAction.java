package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.perspsupport.perspmodel.Refas2Hlcl;

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
		//boolean wasFirst = editor.executeSimulation(false, Refas2Hlcl.SIMUL_EXEC, true, "Simul");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		editor.updateDashBoard(false, true);
		editor.editPropertiesRefas();
		editor.updateSimulResults();
	}

}
