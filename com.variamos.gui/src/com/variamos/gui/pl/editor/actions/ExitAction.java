package com.variamos.gui.pl.editor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.variamos.gui.maineditor.AbstractEditorAction;

@SuppressWarnings("serial")
public class ExitAction extends AbstractEditorAction {
	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		BasicGraphEditor editor = getEditor(e);

		if (editor != null) {
			editor.exit();
		}
	}
}