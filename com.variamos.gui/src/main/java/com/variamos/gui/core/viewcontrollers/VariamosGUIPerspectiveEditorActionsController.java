package com.variamos.gui.core.viewcontrollers;

import java.awt.Color;
import java.io.File;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import javax.swing.filechooser.FileFilter;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;
import com.variamos.dynsup.interfaces.APIDynsup;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.gui.core.io.ConsoleTextArea;
import com.variamos.gui.core.mxgraph.editor.DefaultFileFilter;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphComponent;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.PerspEditorGraph;
import com.variamos.gui.perspeditor.actions.FileTasks;
import com.variamos.gui.perspeditor.actions.FileTasks.FileTasksEnum;
import com.variamos.gui.util.FileUtils;

/**
 * This class implements logic for opening, saving, printing files, etc inside
 * the GUI. Methods here defined are called by listeners in the GUI inside the
 * class
 * [VariamosGUIEditorActions]{@link com.variamos.gui.core.viewcontrollers.VariamosGUIPerpectiveEditorActions}
 * 
 * @author Luisa Rincon <lufe089@gmail.com>
 * @see com.variamos.gui.core.viewcontrollers.VariamosGUIPerpectiveEditorActions
 */
public class VariamosGUIPerspectiveEditorActionsController {

	/**
	 * @param variamosEditor:
	 *            main object for handling the GUI in VariaMos
	 */
	public static void newAction(VariamosGraphEditor variamosEditor) {
		if (variamosEditor != null) {

			// When the perspective is the one of configuration, then the action for new is
			// not supported.
			if (variamosEditor.getPerspective() == 4) {
				JOptionPane.showMessageDialog(variamosEditor, mxResources.get("saveloadnewerror"),
						"Operation not supported", JOptionPane.INFORMATION_MESSAGE, null);
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
	 * Create a new Swing worker FileTask object for opening or saving files.
	 * 
	 * @param execType:
	 *            0 open files, 1 save files
	 * @param file:
	 *            file to open or save
	 * @param variamosEditor:
	 *            perspective from which the action is invoked
	 * @param graph
	 * @param message:
	 *            Message to show in the progress monitor window
	 * @param ext
	 *            file extension. Only for saving files {@link} loadAction -
	 *            saveAction
	 */
	private static void callLoadSaveFileTask(FileTasksEnum execType, File file, VariamosGraphEditor variamosEditor,
			mxGraph graph, String message, String ext, Color bgColor) {

		// Notify progress
		ProgressMonitor progressMonitor = new ProgressMonitor(variamosEditor, message, "", 0, 100);
		variamosEditor.setProgressMonitor(progressMonitor);
		progressMonitor.setMillisToDecideToPopup(5);
		progressMonitor.setMillisToPopup(5);
		progressMonitor.setProgress(0);

		// Create the fileTask which is a swiworker that notifies progress in background
		FileTasks task = null;
		switch (execType) {
		case OPEN_MODEL:
			task = new FileTasks(progressMonitor, execType, file, variamosEditor, graph);
			break;
		case SAVE_MODEL:
		case SAVE_IMAGE_SVG:
			task = new FileTasks(progressMonitor, execType, file.getAbsolutePath(), ext, variamosEditor, graph);
		case SAVE_IMAGE_PNG:
		case SAVE_IMAGE_OTHERS:
			task = new FileTasks(progressMonitor, execType, file.getAbsolutePath(), ext, variamosEditor, graph,
					bgColor);
			break;
		}

		variamosEditor.setFileTask(task);

		// Add listener to notify changes into the progress monitor
		task.addPropertyChangeListener(variamosEditor);
		((MainFrame) variamosEditor.getFrame()).waitingCursor(true);
		task.execute();
	}

	/**
	 * @param variamosEditor:
	 *            main object for handling the GUI in VariaMos
	 */
	public static void loadAction(VariamosGraphEditor variamosEditor) {
		if (variamosEditor != null) {
			if (variamosEditor.getPerspective() == 4) {
				JOptionPane.showMessageDialog(variamosEditor, mxResources.get("saveloadnewerror"),
						"Operation not supported", JOptionPane.INFORMATION_MESSAGE, null);

				return;
			}
			final VariamosGraphEditor finalEditor = variamosEditor;

			// Actives the animation that shows there is a work in progress
			((MainFrame) variamosEditor.getFrame()).waitingCursor(true);

			if (!variamosEditor.isModified() || JOptionPane.showConfirmDialog(variamosEditor,
					mxResources.get("loseChanges")) == JOptionPane.YES_OPTION) {
				mxGraph graph = variamosEditor.getGraphComponent().getGraph();

				// File chooser logic to find and select what file will be open.
				if (graph != null) {

					String path = (variamosEditor.getLastDir() != null) ? variamosEditor.getLastDir()
							: System.getProperty("user.dir");
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
						// Update the last path dir
						String lastDir = fileChooser.getSelectedFile().getParent();

						// Update the last path where files were saved or open. In this way, it is
						// possible to record the path for next actions that open or close files
						variamosEditor.setLastDir(lastDir);
						
						// Call a SWING worker for loading files
						VariamosGUIPerspectiveEditorActionsController.callLoadSaveFileTask(FileTasksEnum.OPEN_MODEL,
								fileChooser.getSelectedFile(), (VariamosGraphEditor) variamosEditor, graph,
								"File opening", "", null);
					}
				}
			}
			variamosEditor.refresh();

			// Stops the animation once the job ends
			((MainFrame) variamosEditor.getFrame()).waitingCursor(false);
		}

	}


	/**
	 * 
	 * @param variamosEditor
	 * @param showDialog:
	 *            true (save as action), false (save action)
	 */
	public static void saveAction(VariamosGraphEditor variamosEditor, boolean showDialog) {

		if (variamosEditor != null) {
			final VariamosGraphEditor finalEditor = variamosEditor;
			if (variamosEditor.getPerspective() == 4) {
				JOptionPane.showMessageDialog(variamosEditor, mxResources.get("saveloadnewerror"),
						"Operation not supported", JOptionPane.INFORMATION_MESSAGE, null);

				return;
			}
			((MainFrame) variamosEditor.getFrame()).waitingCursor(true);

			mxGraphComponent graphComponent = variamosEditor.getGraphComponent();
			mxGraph graph = graphComponent.getGraph();
			String filename = null;
			final String defaultExtension = finalEditor.getFileExtension();
			if (showDialog || variamosEditor.getCurrentFile() == null) {
				// Open a file chooser where the user can text the file name
				filename = FileUtils.getFileNameByFileChooserToSaveFiles(variamosEditor, defaultExtension);

				// The name file already exist but the user don't want override the file, then
				// nothing is done
				if (new File(filename).exists() && JOptionPane.showConfirmDialog(graphComponent,
						mxResources.get("overwriteExistingFile")) != JOptionPane.YES_OPTION) {
					((MainFrame) variamosEditor.getFrame()).waitingCursor(false);
					return;
				}
			} else {
				filename = variamosEditor.getCurrentFile().getAbsolutePath();
			}

			// Saves the graph by using the name and format defined previously
			try {
				String ext = filename.substring(filename.lastIndexOf('.') + 1);

				if (ext.equalsIgnoreCase("svg")) {
					File currentFile = new File(filename);
					VariamosGUIPerspectiveEditorActionsController.callLoadSaveFileTask(FileTasksEnum.SAVE_IMAGE_SVG,
							currentFile, (VariamosGraphEditor) variamosEditor, graph, "File saving", ext, null);

				} else if (ext.equalsIgnoreCase(defaultExtension) || ext.equalsIgnoreCase("xml")) {
					// Call the swi worker for saving files
					File currentFile = new File(filename);
					VariamosGUIPerspectiveEditorActionsController.callLoadSaveFileTask(FileTasksEnum.SAVE_MODEL,
							currentFile, (VariamosGraphEditor) variamosEditor, graph, "File saving", ext, null);
					variamosEditor.setCurrentFile(currentFile);

				} else {
					Color bg = null;
					File currentFile = new File(filename);
					if ((!ext.equalsIgnoreCase("gif") && !ext.equalsIgnoreCase("png"))
							|| JOptionPane.showConfirmDialog(graphComponent,
									mxResources.get("transparentBackground")) != JOptionPane.YES_OPTION) {
						bg = graphComponent.getBackground();
					}

					if ((variamosEditor.getCurrentFile() != null && ext.equalsIgnoreCase("png"))) {
						// Call a SWING worker for saving images in png format
						VariamosGUIPerspectiveEditorActionsController.callLoadSaveFileTask(FileTasksEnum.SAVE_IMAGE_PNG,
								currentFile, (VariamosGraphEditor) variamosEditor, graph, "File saving", ext, bg);
					} else {
						// Call a SWING worker for saving images in other formats
						VariamosGUIPerspectiveEditorActionsController.callLoadSaveFileTask(
								FileTasksEnum.SAVE_IMAGE_OTHERS, currentFile, (VariamosGraphEditor) variamosEditor,
								graph, "File saving", ext, bg);
					}
				}
			} catch (Exception ex) {
				// FIXME: Improve exceptions handling
				ConsoleTextArea.addText(ex.getStackTrace());
				JOptionPane.showMessageDialog(graphComponent, ex.toString(), mxResources.get("error"),
						JOptionPane.ERROR_MESSAGE);
			} finally {
				// Ends the waiting
				((MainFrame) variamosEditor.getFrame()).waitingCursor(false);
			}
		}

	}
}
