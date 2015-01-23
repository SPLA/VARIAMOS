package com.variamos.gui.refas.editor.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class RootElementAction extends AbstractEditorAction {
	
	public RootElementAction() {
		this.putValue(SHORT_DESCRIPTION, mxResources.get("verifyRoot"));
	}
	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		List<String> defect = new ArrayList<String>();
		defect.add("Root");
		editor.verify(defect);
	}
}