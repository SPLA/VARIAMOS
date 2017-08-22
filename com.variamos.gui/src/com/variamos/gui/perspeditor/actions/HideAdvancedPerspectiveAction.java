package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class HideAdvancedPerspectiveAction extends AbstractEditorAction {

	public HideAdvancedPerspectiveAction() {

	//	this.putValue(SHORT_DESCRIPTION, mxResources.get("resetSimulation"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.setAdvancedPerspective(false);
	}
}