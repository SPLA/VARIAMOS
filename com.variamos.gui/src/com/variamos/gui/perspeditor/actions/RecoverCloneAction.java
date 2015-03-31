package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class RecoverCloneAction extends AbstractEditorAction {

	public RecoverCloneAction() {
		//this.putValue(SHORT_DESCRIPTION, mxResources.get("verifyElements"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.recoverClones();
	}
} 