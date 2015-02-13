package com.variamos.gui.refas.editor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.refas.Refas2Hlcl;

@SuppressWarnings("serial")
public class HideSimulationDashBoardAction extends AbstractEditorAction {

	public HideSimulationDashBoardAction() {

		this.putValue(SHORT_DESCRIPTION, mxResources.get("resetSimulation"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.showDashBoard(false);
	}
}