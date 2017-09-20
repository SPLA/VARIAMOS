package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.variamos.gui.core.viewcontrollers.AbstractVariamoGUIAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class ShowSimulationDashBoardAction extends AbstractVariamoGUIAction {

	public ShowSimulationDashBoardAction() {

		// this.putValue(SHORT_DESCRIPTION, mxResources.get("resetSimulation"));
	}

	/**
		 * 
		 */
	@Override
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.updateDashBoard(true, true, false);
		editor.showDashBoard(true);
	}
}