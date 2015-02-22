package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.perspsupport.perspmodel.Refas2Hlcl;

@SuppressWarnings("serial")
public class ShowSimulationDashBoardAction extends AbstractEditorAction {

	public ShowSimulationDashBoardAction() {

	//	this.putValue(SHORT_DESCRIPTION, mxResources.get("resetSimulation"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);		
		editor.updateDashBoard(true, false);
	}
}