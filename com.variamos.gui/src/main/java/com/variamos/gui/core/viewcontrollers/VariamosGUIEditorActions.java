package com.variamos.gui.core.viewcontrollers;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.mxgraph.util.mxResources;
import com.variamos.dynsup.interfaces.APIDynsup;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.gui.maineditor.VariamosGraphComponent;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.PerspEditorGraph;

public class VariamosGUIEditorActions {

	@SuppressWarnings("serial")
	public static class NewAction extends AbstractVariamoGUIAction {
		public NewAction() {
			super();
			this.putValue(SHORT_DESCRIPTION, "N");
		}

	
		public void actionPerformed(ActionEvent e) {
			VariamosGraphEditor editor = getEditor(e);
			VariamosGUIEditorActionsController.newAction(editor);
		}
	}

}
