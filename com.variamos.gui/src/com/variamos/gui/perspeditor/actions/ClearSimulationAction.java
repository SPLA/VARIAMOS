package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphComponent;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.perspsupport.translation.ModelExpr2HLCL;

@SuppressWarnings("serial")
public class ClearSimulationAction extends AbstractEditorAction {

	public ClearSimulationAction() {

		this.putValue(SHORT_DESCRIPTION, mxResources.get("resetSimulation"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.clearQueryMonitor();
		editor.clearElementState(ModelExpr2HLCL.SIMUL_EXEC);
		((VariamosGraphComponent) editor.getGraphComponent())
				.setSimulationStarted(false);
	}
}