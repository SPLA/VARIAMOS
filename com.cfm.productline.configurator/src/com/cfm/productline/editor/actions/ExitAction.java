package com.cfm.productline.editor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.examples.swing.editor.BasicGraphEditor;

@SuppressWarnings("serial")
public class ExitAction extends ConfiguratorEditorAction {
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