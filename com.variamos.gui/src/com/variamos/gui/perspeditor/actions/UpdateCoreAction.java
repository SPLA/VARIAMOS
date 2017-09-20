package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import com.mxgraph.util.mxResources;
import com.variamos.gui.core.viewcontrollers.AbstractVariamoGUIAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class UpdateCoreAction extends AbstractVariamoGUIAction {

	public UpdateCoreAction() {
		this.putValue(SHORT_DESCRIPTION, mxResources.get("updateCore"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.clearNotificationBar();
		//editor.verifyErrors();
		List<String> defects = new ArrayList<String>();
		defects.add("Core");
		editor.verify(defects);
	}
} 