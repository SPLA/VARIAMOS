package com.variamos.gui.core.viewcontrollers;

import javax.swing.JOptionPane;

import com.mxgraph.util.mxResources;
import com.variamos.dynsup.interfaces.APIDynsup;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.gui.maineditor.VariamosGraphComponent;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.PerspEditorGraph;

public class VariamosGUIEditorActionsController {

	
	public static void newAction(VariamosGraphEditor editor) {
		if (editor != null) {

			// When the perspective is the one of configuration, then the action for new is
			// not supported.
			if (editor.getPerspective() == 4) {
				JOptionPane.showMessageDialog(editor, mxResources.get("saveloadnewerror"), "Operation not supported",
						JOptionPane.INFORMATION_MESSAGE, null);
				return;
			}

			// When there are not changes or the user accepts to loose changes, then the
			// editor is reseted
			if (!editor.isModified() || JOptionPane.showConfirmDialog(editor,
					mxResources.get("loseChanges")) == JOptionPane.YES_OPTION) {
				((VariamosGraphEditor) editor).resetView();
				System.runFinalization();

				if (editor.getPerspective() == 1) {

					// Call the action that defines the default elements in the semantic model. True
					// means the model is empty
					InstanceModel instanceModel = editor.getEditedModel();
					APIDynsup.createOperationsSuperstructure(instanceModel, Boolean.TRUE);

					// The instance model under edition is updated into the editor
					((PerspEditorGraph) ((VariamosGraphComponent) editor.getGraphComponent()).getGraph())
							.setModelInstance(instanceModel);

				}
			}
		}
	}
}
