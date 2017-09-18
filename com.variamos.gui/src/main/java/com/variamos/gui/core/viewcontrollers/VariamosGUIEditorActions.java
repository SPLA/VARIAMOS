package com.variamos.gui.core.viewcontrollers;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.mxgraph.util.mxResources;
import com.variamos.gui.core.mxgraph.editor.BasicGraphEditor;
import com.variamos.gui.maineditor.VariamosGraphEditor;

/**
 * This file supports static inner classes with the business logic for basic
 * actions in the graphical user interface of VARIAMOS Menus and menu bars call
 * actions here defined
 * 
 * Each new action that will not be defined dynamically needs a new class inside
 * this file.
 * 
 * @author Luisa Rincon <lufe089@gmail.com>
 * @see com.variamos.gui.core.viewcontrollers.VariamosGUIEditorActionsController
 *
 */
@SuppressWarnings("serial")
public class VariamosGUIEditorActions {

	/*********** NEW *************************/
	public static class NewAction extends AbstractVariamoGUIAction {
		public NewAction() {
			super();
			// this.putValue(SHORT_DESCRIPTION, "N");
		}

		public void actionPerformed(ActionEvent e) {
			VariamosGraphEditor variamosEditor = getEditor(e);
			// Call the controller that call the right business functionalities
			VariamosGUIEditorActionsController.newAction(variamosEditor);
		}
	}

	/*********** LOAD *************************/
	public static class LoadAction extends AbstractVariamoGUIAction {

		public LoadAction() {
			super();
		}

		public void actionPerformed(ActionEvent e) {
			VariamosGraphEditor variamosEditor = getEditor(e);
			// Call the controller that call the right business functionalities
			VariamosGUIEditorActionsController.loadAction(variamosEditor);
		}
	}

	/************* SAVE *****************************/

	public static class SaveAction extends AbstractVariamoGUIAction {

		// This variable controls if a new dialog is open once the save button is
		// pressed.
		// For save as, this variable is initialized as True, whereas for the "save"
		// action
		// this variable is initialized as false
		protected boolean showDialog;

		public SaveAction(boolean showDialog) {
			this.showDialog = showDialog;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			VariamosGraphEditor variamosEditor = getEditor(e);
			// Call the controller that call the right business functionalities
			VariamosGUIEditorActionsController.saveAction(variamosEditor, showDialog);
		}

	}
	
	/********************* EXIT ******************************/
	public static class ExitAction extends AbstractVariamoGUIAction {
	
		public void actionPerformed(ActionEvent e) {
			VariamosGraphEditor variamosEditor = getEditor(e);

			if (variamosEditor != null
					&& !variamosEditor.isModified()
					|| JOptionPane.showConfirmDialog(variamosEditor,
							mxResources.get("loseChanges")) == JOptionPane.YES_OPTION) {
				variamosEditor.exit();
			}
		}
	}

}
