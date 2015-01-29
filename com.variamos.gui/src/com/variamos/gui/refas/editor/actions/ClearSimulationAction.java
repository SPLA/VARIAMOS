package com.variamos.gui.refas.editor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.refas.Refas2Hlcl;

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
		editor.clearElementState(Refas2Hlcl.SIMUL_EXEC);
	}
}