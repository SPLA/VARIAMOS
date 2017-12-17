package com.variamos.gui.core.viewcontrollers;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;
import com.variamos.dynsup.interfaces.APIDynsup;
import com.variamos.dynsup.model.InstanceModel;
import com.variamos.gui.core.io.ConsoleTextArea;
import com.variamos.gui.core.maineditor.models.ParadigmTypeEnum;
import com.variamos.gui.core.mxgraph.editor.DefaultFileFilter;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.ParadigmChooserPane;
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
				
				VariamosGUIPerspectiveEditorActionsController.changeVariamosParadigmView(((MainFrame)variamosEditor.getFrame()).getGraphEditors());

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
	
	public static void changeVariamosParadigmView(List<VariamosGraphEditor> graphEditors) {
		ParadigmTypeEnum paradigmChoosed = ParadigmChooserPane.showInputDialog();
		/*if(paradigmChoosed==null || paradigmChoosed.equals(ParadigmTypeEnum.EMPTY)) {
			dispose();
			System.exit(0);
		}*/
		
		File syntax = null, semantic = null;
		String prefijo = "src/main/resources/defaultmodels/";
		switch(paradigmChoosed) {
			case CONSTRAINTGRAPHS:
				syntax = new File(prefijo+"constraintgraphs/syntax.vmsm");
				semantic = new File(prefijo+"constraintgraphs/semantic.vmom");
				break;
			case CUSTOMIZED:
				semantic = VariamosGUIPerspectiveEditorActionsController.getFileFromChooser(graphEditors.get(0));
				syntax = VariamosGUIPerspectiveEditorActionsController.getFileFromChooser(graphEditors.get(2));								
				break;
			case EMPTY:
				break;
			case FEATURES:
				syntax = new File(prefijo+"features/syntax.vmsm");
				semantic = new File(prefijo+"features/semantic.vmom");
				break;
			case ASSETS:
				syntax = new File(prefijo+"assets/syntax.vmsm");
				semantic = new File(prefijo+"assets/semantic.vmom");
				break;
			case REFAS:
				syntax = new File(prefijo+"refas/syntax.vmsm");
				semantic = new File(prefijo+"refas/semantic.vmom");
				break;
			default:
				break;		
		}
		
		if(syntax!=null && semantic!=null) {
			System.out.println("syntax:"+syntax.exists()+", semantic:"+semantic.exists());
			final VariamosGraphEditor graphEditor2 = graphEditors.get(2);
			final File sintaxFile = syntax;
			VariamosGUIPerspectiveEditorActionsController.loadAction(graphEditors.get(0),semantic,
				new Runnable() {
					public void run() {
						VariamosGUIPerspectiveEditorActionsController.loadAction(graphEditor2,sintaxFile,
							new Runnable() {
							    public void run() {
							    	
									graphEditor2.updatePespectiveMenuTab(graphEditor2,
										mxResources.get("modelingPerspButton"));
																	    	
							    }
						});
					}
				});
		}else {
			System.out.println("S");
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
		VariamosGUIPerspectiveEditorActionsController.callLoadSaveFileTask(execType, file, variamosEditor, graph, message, ext, bgColor, null);
	}
	private static void callLoadSaveFileTask(FileTasksEnum execType, File file, VariamosGraphEditor variamosEditor,
				mxGraph graph, String message, String ext, Color bgColor,final Runnable callBack) {

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
			task = new FileTasks(progressMonitor, execType, file, variamosEditor, graph, callBack);
			break;
		case SAVE_MODEL:
		case SAVE_IMAGE_SVG:
			progressMonitor.setMillisToDecideToPopup(0);
			progressMonitor.setMillisToPopup(0);
			task = new FileTasks(progressMonitor, execType, file.getAbsolutePath(), ext, variamosEditor, graph);
		case SAVE_IMAGE_PNG:
		case SAVE_IMAGE_OTHERS:
			progressMonitor.setMillisToDecideToPopup(0);
			progressMonitor.setMillisToPopup(0);
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
	
	private static boolean graphExists(VariamosGraphEditor variamosEditor) {
		mxGraph graph = variamosEditor.getGraphComponent().getGraph();
		
		// File chooser logic to find and select what file will be open.
		if (graph != null) {
			return true;
		}
		
		return false;
	}
	
	private static boolean loadActionBefore(VariamosGraphEditor variamosEditor) {
		if (variamosEditor != null) {
			if (variamosEditor.getPerspective() == 4) {
				JOptionPane.showMessageDialog(variamosEditor, mxResources.get("saveloadnewerror"),
						"Operation not supported", JOptionPane.INFORMATION_MESSAGE, null);

				return false;
			}
			// Actives the animation that shows there is a work in progress
			((MainFrame) variamosEditor.getFrame()).waitingCursor(true);

			if (!variamosEditor.isModified() || JOptionPane.showConfirmDialog(variamosEditor,
					mxResources.get("loseChanges")) == JOptionPane.YES_OPTION) {
				
				return VariamosGUIPerspectiveEditorActionsController.graphExists(variamosEditor);
			}
		}
		return false;
	}
	
	private static void loadActionAfter(VariamosGraphEditor variamosEditor) {
		variamosEditor.refresh();

		// Stops the animation once the job ends
		((MainFrame) variamosEditor.getFrame()).waitingCursor(false);
	}

	/**
	 * @param variamosEditor:
	 *            main object for handling the GUI in VariaMos
	 */
	public static void loadAction(VariamosGraphEditor variamosEditor) {
		//JOptionPane.showMessageDialog(null, "Hi. In loadaction.");
		if(VariamosGUIPerspectiveEditorActionsController.loadActionBefore(variamosEditor)) {
			File f = VariamosGUIPerspectiveEditorActionsController.getFileFromChooser(variamosEditor);
			if(f!=null) {
				VariamosGUIPerspectiveEditorActionsController.loadAction(variamosEditor, f,null);
			}
			VariamosGUIPerspectiveEditorActionsController.loadActionAfter(variamosEditor);
		}
	}
	
	private static File getFileFromChooser(VariamosGraphEditor variamosEditor) {
		final VariamosGraphEditor finalEditor = variamosEditor;
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
			variamosEditor.setModified(false);
			return fileChooser.getSelectedFile();
		}				
		return null;
	}

	/**
	 * @param variamosEditor:
	 *            main object for handling the GUI in VariaMos
	 * @param theFile:
	 * 			  the file to load
	 */
	public static void loadAction(VariamosGraphEditor variamosEditor, File theFile, final Runnable callBack) {
		if(VariamosGUIPerspectiveEditorActionsController.graphExists(variamosEditor)) {
			mxGraph graph = variamosEditor.getGraphComponent().getGraph();

			// Call a SWING worker for loading files
			VariamosGUIPerspectiveEditorActionsController.callLoadSaveFileTask(FileTasksEnum.OPEN_MODEL,
					theFile, (VariamosGraphEditor) variamosEditor, graph,
					"File opening", "", null, callBack);
			VariamosGUIPerspectiveEditorActionsController.loadActionAfter(variamosEditor);					
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
					VariamosGUIPerspectiveEditorActionsController.callLoadSaveFileTask(FileTasksEnum.SAVE_IMAGE_OTHERS,
							currentFile, (VariamosGraphEditor) variamosEditor, graph, "File saving", ext, bg);
				}
			}

		}

	}

}
