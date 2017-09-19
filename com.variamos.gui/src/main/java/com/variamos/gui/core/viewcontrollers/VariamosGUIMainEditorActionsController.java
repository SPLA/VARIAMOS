package com.variamos.gui.core.viewcontrollers;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxSvgCanvas;
import com.mxgraph.io.mxCodec;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxCellRenderer.CanvasFactory;
import com.mxgraph.util.mxDomUtils;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.util.png.mxPngEncodeParam;
import com.mxgraph.util.png.mxPngImageEncoder;
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

/**
 * This class implements logic for opening, saving, printing files, etc inside the GUI. Methods here defined are called by listeners in 
 * the GUI inside the class [VariamosGUIEditorActions]{@link com.variamos.gui.core.viewcontrollers.VariamosGUIMainEditorActions} 
 * @author Luisa Rincon <lufe089@gmail.com>
 * @see com.variamos.gui.core.viewcontrollers.VariamosGUIMainEditorActions 
 */
public class VariamosGUIMainEditorActionsController {

	
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

	
	/**
	 * Saves XML+PNG format.
	 * @param editor: Variamos main editor window
	 * @param filename
	 * @param bg: backaground color
	 * @throws IOException
	 * @see https://github.com/jgraph/jgraphx/tree/master/examples/com/mxgraph/examples/swing
	 */
	public static void saveXmlPng(VariamosGraphEditor editor, String filename, Color bg) throws IOException {
		mxGraphComponent graphComponent = editor.getGraphComponent();
		mxGraph graph = graphComponent.getGraph();

		// Creates the image for the PNG file
		BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 2, bg, graphComponent.isAntiAlias(), null,
				graphComponent.getCanvas());

		// Creates the URL-encoded XML data
		mxCodec codec = new mxCodec();
		String xml = URLEncoder.encode(mxXmlUtils.getXml(codec.encode(graph.getModel())), "UTF-8");
		mxPngEncodeParam param = mxPngEncodeParam.getDefaultEncodeParam(image);
		param.setCompressedText(new String[] { "mxGraphModel", xml });

		// Saves as a PNG file
		FileOutputStream outputStream = new FileOutputStream(new File(filename));
		try {
			mxPngImageEncoder encoder = new mxPngImageEncoder(outputStream, param);

			if (image != null) {
				encoder.encode(image);

				editor.setModified(false);
				editor.setCurrentFile(new File(filename));
			} else {
				JOptionPane.showMessageDialog(graphComponent, mxResources.get("noImageData"));
			}
		} finally {
			outputStream.close();
		}
	}
	
	
	/**
	 * 
	 * @param variamosEditor
	 * @param showDialog: true (save as action), false (save action) 
	 */
	public static void saveAction(VariamosGraphEditor variamosEditor, boolean showDialog) {

		if (variamosEditor != null) {
			final VariamosGraphEditor finalEditor = variamosEditor;
			if (variamosEditor.getPerspective() == 4) {
				JOptionPane.showMessageDialog(variamosEditor, mxResources.get("saveloadnewerror"), "Operation not supported",
						JOptionPane.INFORMATION_MESSAGE, null);

				return;
			}
			((MainFrame) variamosEditor.getFrame()).waitingCursor(true);

			mxGraphComponent graphComponent = variamosEditor.getGraphComponent();
			mxGraph graph = graphComponent.getGraph();
			FileFilter selectedFilter = null;
			String filename = null;
			boolean dialogShown = false;
			final String fileExtension = finalEditor.getFileExtension();
			final String fileExtensionName = finalEditor.getExtensionName();

			if (showDialog || variamosEditor.getCurrentFile() == null) {
				String path;

				if (variamosEditor.getLastDir() != null) {
					path = variamosEditor.getLastDir();
				} else if (variamosEditor.getCurrentFile() != null) {
					path = variamosEditor.getCurrentFile().getParent();
				} else {
					path = System.getProperty("user.dir");
				}

				JFileChooser fileChooser = new JFileChooser(path);

				// Adds the default file format
				FileFilter defaultFilter = new DefaultFileFilter("." + fileExtension,
						fileExtensionName + " (." + fileExtension + ")");
				fileChooser.addChoosableFileFilter(defaultFilter);
						
				// Adds special vector graphics formats and HTML
				fileChooser.addChoosableFileFilter(new DefaultFileFilter(".svg", "SVG " + mxResources.get("file") + " (.svg)"));

				// Adds a filter for each supported image format
				Object[] imageFormats = ImageIO.getReaderFormatNames();

				// Finds all distinct extensions
				HashSet<String> formats = new HashSet<String>();

				for (int i = 0; i < imageFormats.length; i++) {
					String ext = imageFormats[i].toString().toLowerCase();
					formats.add(ext);
				}

				imageFormats = formats.toArray();

				for (int i = 0; i < imageFormats.length; i++) {
					String ext = imageFormats[i].toString();
					fileChooser.addChoosableFileFilter(new DefaultFileFilter("." + ext,
							ext.toUpperCase() + " " + mxResources.get("file") + " (." + ext + ")"));
				}

				// Adds filter that accepts all supported image formats
				fileChooser.addChoosableFileFilter(new DefaultFileFilter.ImageFileFilter(mxResources.get("allImages")));
				fileChooser.setFileFilter(defaultFilter);
				int rc = fileChooser.showDialog(null, mxResources.get("save"));
				dialogShown = true;

				if (rc != JFileChooser.APPROVE_OPTION) {
					//Indicates that the waiting is over.
					((MainFrame) finalEditor.getFrame()).waitingCursor(false);
					return;
				} else {
					//Update the last path dir
					String lastDir = fileChooser.getSelectedFile().getParent();
					
					//Update the last path where files were saved or open. In this way, it is possible to record the path for next actions that open or close files
					variamosEditor.setLastDir(lastDir);
				}

				filename = fileChooser.getSelectedFile().getAbsolutePath();
				selectedFilter = fileChooser.getFileFilter();

				if (selectedFilter instanceof DefaultFileFilter) {
					String ext = ((DefaultFileFilter) selectedFilter).getExtension();

					if (!filename.toLowerCase().endsWith(ext)) {
						filename += ext;
					}
				}

				if (new File(filename).exists() && JOptionPane.showConfirmDialog(graphComponent,
						mxResources.get("overwriteExistingFile")) != JOptionPane.YES_OPTION) {
					((MainFrame) finalEditor.getFrame()).waitingCursor(false);
					return;
				}
			} else {
				filename = variamosEditor.getCurrentFile().getAbsolutePath();
			}

			try {
				String ext = filename.substring(filename.lastIndexOf('.') + 1);

				if (ext.equalsIgnoreCase("svg")) {
					mxSvgCanvas canvas = (mxSvgCanvas) mxCellRenderer.drawCells(graph, null, 1, null,
							new CanvasFactory() {
								@Override
								public mxICanvas createCanvas(int width, int height) {
									mxSvgCanvas canvas = new mxSvgCanvas(mxDomUtils.createSvgDocument(width, height));
									canvas.setEmbedded(true);

									((MainFrame) finalEditor.getFrame()).waitingCursor(false);
									return canvas;
								}

							});

					mxUtils.writeFile(mxXmlUtils.getXml(canvas.getDocument()), filename);

				} else if (ext.equalsIgnoreCase(fileExtension) || ext.equalsIgnoreCase("xml")) {

					FileTasks.saveAction(FileTasks.SAVE, filename, ext, variamosEditor, graph);

					variamosEditor.setCurrentFile(new File(filename));

				} else {
					Color bg = null;

					if ((!ext.equalsIgnoreCase("gif") && !ext.equalsIgnoreCase("png"))
							|| JOptionPane.showConfirmDialog(graphComponent,
									mxResources.get("transparentBackground")) != JOptionPane.YES_OPTION) {
						bg = graphComponent.getBackground();
					}

					if ((variamosEditor.getCurrentFile() != null && ext.equalsIgnoreCase("png") && !dialogShown)) {
						VariamosGUIMainEditorActionsController.saveXmlPng(variamosEditor, filename, bg);
					} else {
						BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 2, bg,
								graphComponent.isAntiAlias(), null, graphComponent.getCanvas());

						if (image != null) {
							ImageIO.write(image, ext, new File(filename));
						} else {
							JOptionPane.showMessageDialog(graphComponent, mxResources.get("noImageData"));
						}
					}
				}
			} catch (Throwable ex) {
				ConsoleTextArea.addText(ex.getStackTrace());
				JOptionPane.showMessageDialog(graphComponent, ex.toString(), mxResources.get("error"),
						JOptionPane.ERROR_MESSAGE);
			}
			//Ends the waiting
			((MainFrame) variamosEditor.getFrame()).waitingCursor(false);
		}

	}
}
