package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.util.mxResources;
import com.variamos.gui.core.viewcontrollers.AbstractVariamoGUIAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class ClearVerificationAction extends AbstractVariamoGUIAction {

	public ClearVerificationAction() {
		this.putValue(SHORT_DESCRIPTION, mxResources.get("clearElements"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.clearElementErrors();
	}

}

