package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class OperationAction extends AbstractEditorAction {

	public OperationAction() {
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.executeOperation((String) (e.getActionCommand()));
	}
}