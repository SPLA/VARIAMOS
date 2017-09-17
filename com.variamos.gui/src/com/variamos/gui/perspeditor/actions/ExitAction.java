package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.mxgraph.util.mxResources;
import com.variamos.gui.core.mxgraph.editor.BasicGraphEditor;
import com.variamos.gui.core.viewcontrollers.AbstractVariamoGUIAction;

@SuppressWarnings("serial")
public class ExitAction extends AbstractVariamoGUIAction {
	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		BasicGraphEditor editor = getEditor(e);

		if (editor != null
				&& !editor.isModified()
				|| JOptionPane.showConfirmDialog(editor,
						mxResources.get("loseChanges")) == JOptionPane.YES_OPTION) {
			editor.exit();
		}
	}
}