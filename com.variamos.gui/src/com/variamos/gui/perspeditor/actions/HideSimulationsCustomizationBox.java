package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class HideSimulationsCustomizationBox extends AbstractEditorAction {

	public HideSimulationsCustomizationBox() {

	//	this.putValue(SHORT_DESCRIPTION, mxResources.get("resetSimulation"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.setShowSimulationCustomizationBox(false);
	}
}