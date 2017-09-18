package com.variamos.gui.core.viewcontrollers;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;
import com.variamos.dynsup.interfaces.APIDynsup;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.gui.core.mxgraph.editor.DefaultFileFilter;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphComponent;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.PerspEditorGraph;
import com.variamos.gui.perspeditor.actions.FileTasks;

/**
 * This class implements logic for opening, saving, printing files, etc inside the GUI. Methods here defined are called by listeners in 
 * the GUI inside the class [VariamosGUIEditorActions]{@link com.variamos.gui.core.viewcontrollers.VariamosGUIEditorActions} 
 * @author Luisa Rincon <lufe089@gmail.com>
 * @see com.variamos.gui.core.viewcontrollers.VariamosGUIEditorActions 
 */
public class VariamosGUIEditorActionsController {

	
	/**
	 * @param variamosEditor: main object for handling the GUI in VariaMos
	 */
	public static void newAction(VariamosGraphEditor variamosEditor) {
		if (variamosEditor != null) {

			// When the perspective is the one of configuration, then the action for new is
			// not supported.
			if (variamosEditor.getPerspective() == 4) {
				JOptionPane.showMessageDialog(variamosEditor, mxResources.get("saveloadnewerror"), "Operation not supported",
						JOptionPane.INFORMATION_MESSAGE, null);
				return;
			}

			// When there are not changes or the user accepts to loose changes, then the
			// editor is reseted
			if (!variamosEditor.isModified() || JOptionPane.showConfirmDialog(variamosEditor,
					mxResources.get("loseChanges")) == JOptionPane.YES_OPTION) {
				((VariamosGraphEditor) variamosEditor).resetView();
				System.runFinalization();

				if (variamosEditor.getPerspective() == 1) {

					// Call the action that defines the default elements in the semantic model. True
					// means the model is empty
					InstanceModel instanceModel = variamosEditor.getEditedModel();
					APIDynsup.createOperationsSuperstructure(instanceModel, Boolean.TRUE);

					// The instance model under edition is updated into the editor
					((PerspEditorGraph) ((VariamosGraphComponent) variamosEditor.getGraphComponent()).getGraph())
							.setModelInstance(instanceModel);

				}
			}
		}
	}
	
	/**
	 * @param variamosEditor: main object for handling the GUI in VariaMos
	 */
	public static void loadAction(VariamosGraphEditor variamosEditor) {
		if (variamosEditor != null) {
			if (variamosEditor.getPerspective() == 4) {
				JOptionPane.showMessageDialog(variamosEditor, mxResources.get("saveloadnewerror"),
						"Operation not supported", JOptionPane.INFORMATION_MESSAGE, null);

				return;
			}
			final VariamosGraphEditor finalEditor = variamosEditor;
			
			//Actives the animation that shows there is a work in progress
			((MainFrame) variamosEditor.getFrame()).waitingCursor(true);
			
			if (!variamosEditor.isModified() || JOptionPane.showConfirmDialog(variamosEditor,
					mxResources.get("loseChanges")) == JOptionPane.YES_OPTION) {
				mxGraph graph = variamosEditor.getGraphComponent().getGraph();

				//File chooser logic to find and select what file will be open.
				if (graph != null) {
					
					String path = (variamosEditor.getLastDir() != null) ? variamosEditor.getLastDir() : System.getProperty("user.dir");
					JFileChooser fileChooser = new JFileChooser(path);
											final String fileExtension = finalEditor.getFileExtension();
					final String fileExtensionName = finalEditor.getExtensionName();
					// Adds file filter for supported file format
					DefaultFileFilter defaultFilter = new DefaultFileFilter("." + fileExtension,
							fileExtensionName + " (." + fileExtension + ")") {
						public boolean accept(File file) {
							String lcase = file.getName().toLowerCase();
							((MainFrame) finalEditor.getFrame()).waitingCursor(false);
							return super.accept(file) || lcase.endsWith("." + fileExtension);
						}
					};

					fileChooser.setFileFilter(defaultFilter);
					int fileChooserAnswer = fileChooser.showDialog(null, mxResources.get("openFile"));
					if (fileChooserAnswer == JFileChooser.APPROVE_OPTION) {
						//Update the last path dir
						String lastDir = fileChooser.getSelectedFile().getParent();
						
						//Update the last path where files were saved or open. In this way, it is possible to record the path for next actions that open or close files
						variamosEditor.setLastDir(lastDir);
						FileTasks.openAction(FileTasks.OPEN, fileChooser.getSelectedFile(),
								(VariamosGraphEditor) variamosEditor, graph);

					}
				}
			}
			variamosEditor.refresh();
			
			//Stops the animation once the job ends 
			((MainFrame) variamosEditor.getFrame()).waitingCursor(false);
		}
	
	}
}
