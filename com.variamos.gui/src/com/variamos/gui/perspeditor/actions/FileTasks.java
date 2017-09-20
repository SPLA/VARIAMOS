package com.variamos.gui.perspeditor.actions;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;
import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.gui.core.io.ConsoleTextArea;
import com.variamos.gui.core.io.MxGraphReader;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;

public class FileTasks extends SwingWorker<Void, Void> {
	public static enum FileTasksEnum {
		OPEN_MODEL, SAVE_MODEL, SAVE_IMAGE_SVG, SAVE_IMAGE_PNG, SAVE_IMAGE_OTHERS
	}

	private String executionTime = "";
	private String errorTitle = "";
	private String errorMessage = "";
	private File file;
	private String filename;
	private String ext;
	private FileTasksEnum execType;
	private ProgressMonitor progressMonitor;
	private VariamosGraphEditor variamosEditor;
	private mxGraph graph;
	private Color bgColor;

	public FileTasks(ProgressMonitor progressMonitor) {
		super();
		this.progressMonitor = progressMonitor;

	}

	public FileTasks(ProgressMonitor progressMonitor, FileTasksEnum execType, File file,
			VariamosGraphEditor variamosEditor, mxGraph graph) {
		this.progressMonitor = progressMonitor;
		this.file = file;
		this.execType = execType;
		this.variamosEditor = variamosEditor;
		this.graph = graph;
	}

	public FileTasks(ProgressMonitor progressMonitor, FileTasksEnum execType, String filename, String ext,
			VariamosGraphEditor variamosEditor, mxGraph graph) {
		this.progressMonitor = progressMonitor;
		this.filename = filename;
		this.ext = ext;
		this.execType = execType;
		this.variamosEditor = variamosEditor;
		this.graph = graph;
	}

	public FileTasks(ProgressMonitor progressMonitor, FileTasksEnum execType, String filename, String ext,
			VariamosGraphEditor variamosEditor, mxGraph graph, Color bgColor) {
		this(progressMonitor, execType, filename, ext, variamosEditor, graph);
		this.bgColor = bgColor;
	}

	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	public Void doInBackground() {
		setProgress(0);
		try {
			Thread.sleep(1);
			switch (execType) {
			case OPEN_MODEL:
				openModelAction(file);
				break;
			case SAVE_MODEL:
				saveModelAction(filename);
				break;
			case SAVE_IMAGE_SVG:
			case SAVE_IMAGE_PNG:
			case SAVE_IMAGE_OTHERS:
				saveImage(execType);
				break;
			default: setProgress(100);

			}
		} catch (InterruptedException ignore) {
		} catch (Exception e) {
			ConsoleTextArea.addText(e.getStackTrace());
			errorMessage = "Unmanaged error on file operation.";
			errorTitle = "File action error";
		}
		
		//update the progress of this task will notify the user interface because the listener will be called
		int taskProgress = 100;
		setProgress(taskProgress);
		((MainFrame) variamosEditor.getFrame()).waitingCursor(false);

		return null;
	}

	private boolean openModelAction(File file) throws InterruptedException {
		setProgress(1);
		progressMonitor.setNote("Preparing for file load");
		variamosEditor.resetView();
		graph = variamosEditor.getGraphComponent().getGraph();
		SharedActions.beforeLoadGraph(graph, variamosEditor);
		if (progressMonitor.isCanceled()) {
			throw (new InterruptedException());
		}
		setProgress(30);
		progressMonitor.setNote("Loading File...");

		long iniTime = 0;
		long endTime = 0;
		iniTime = System.currentTimeMillis();
		BufferedReader reader = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));

			String line = null;
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
				break;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String[] versionstr = stringBuilder.toString().split("<add as=\"versionNumber\" value=\"");
		String version = "";
		String currentVersion = MainFrame.getVariamosVersionNumber();
		if (versionstr.length > 1) {

			// TODO: to convert an old version model to a new version (file
			// before
			// loading), validate modelVersion and currentVersion to evaluate
			// the
			// correct: modelVersion.equals("1.0.1.19") &&
			// currentVersion.equals("1.0.1.20")

			// convert syntax mm from v19 to v20
			if (versionstr[1].startsWith("1.0.1.19") && currentVersion.equals("1.0.1.20")
					&& (variamosEditor.getPerspective() == 1 || variamosEditor.getPerspective() == 3)) {
				setProgress(50);
				progressMonitor.setNote("Converting MM (1.0.1.19 to 1.0.1.20)...");
				// Include your edition of the text file here, update the file
				// object with the new file

				String parts[] = stringBuilder.toString().split("com.variamos.hlcl.");
				StringBuilder out = new StringBuilder();
				for (String part : parts) {
					if (parts[parts.length - 1].equals(part))
						out.append(part);
					else
						out.append(part + "com.variamos.hlcl.model.domains.");
				}

				if (variamosEditor.getPerspective() == 3) {
					String partsImg[] = out.toString().split("/com/variamos/gui/pl/editor/images/");
					out = new StringBuilder();
					for (String part : partsImg) {
						if (partsImg[partsImg.length - 1].equals(part))
							out.append(part);
						else
							out.append(part + "/com/variamos/gui/perspeditor/images/");
					}
				}

				PrintWriter dfile;
				try {
					dfile = new PrintWriter(file.getAbsolutePath() + "ConvertedToV20.vmsm");
					dfile.print(out);
					dfile.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				file = new File(file.getAbsolutePath() + "ConvertedToV20.vmsm");

			}
			// validation example
			if (versionstr[1].startsWith("1.0.1.20") && currentVersion.equals("1.0.1.21")) {

			}
			// System.out.println(stringBuilder.toString());
			// endTime = System.currentTimeMillis();
			// System.out.println("read version " + (endTime - iniTime));
		} else {
			JOptionPane.showMessageDialog(variamosEditor,
					"VariaMos keeped the compatibility of models until Version Beta 18. \n"
							+ " Nevertheless, models created in version Beta 18 and older are not\n"
							+ " compatible with this version of VariaMos. The model you are trying \n"
							+ "to load seems to be from an old version, please check again. \n"
							+ "The process will continue but the model will be broken.",
					"Model Incompatibility Message", JOptionPane.INFORMATION_MESSAGE, null);
		}
		// Read file version
		try {
			MxGraphReader.loadMxGraph(file, graph, variamosEditor);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(variamosEditor,
					"unable to load the model. The structure of the file was not understood.",
					"Model load error: " + e.getMessage(), JOptionPane.ERROR_MESSAGE, null);
			return false;
		}
		InstAttribute rootAttributes = (InstAttribute) ((mxCell) graph.getModel().getRoot()).getChildAt(0).getValue();

		if (versionstr.length > 1) {
			String modelVersion = (String) rootAttributes.getInstAttributeAttribute("versionNumber");

			// TODO: to convert an old version model to a new version (edit
			// xml),
			// validate modelVersion and currentVersion to evaluate the
			// correct: modelVersion.equals("1.0.1.19") &&
			// currentVersion.equals("1.0.1.20")

			if (modelVersion.equals("1.0.1.19") && currentVersion.equals("1.0.1.20")
					&& (variamosEditor.getPerspective() == 1 || variamosEditor.getPerspective() == 3)) {

				setProgress(50);
				progressMonitor.setNote("Converting MM (1.0.1.19 to 1.0.1.20)...");
				// Assign the new version to avoid reconverting models
				rootAttributes.setInstAttributeAttribute("versionNumber", "1.0.1.20");
				// Include your of the graph object conversion here
			}

			// validation example
			if (modelVersion.equals("1.0.1.20") && currentVersion.equals("1.0.1.21")) {

			}
		}

		// System.out.println("load version "
		// + (System.currentTimeMillis() - endTime));

		variamosEditor.setCurrentFile(file);
		if (progressMonitor.isCanceled()) {
			throw (new InterruptedException());
		}
		setProgress(70);
		progressMonitor.setNote("Updating DataModel...");
		SharedActions.afterOpenCloneGraph(graph, variamosEditor);
		SharedActions.afterOpenCloneGraph(graph, variamosEditor);
		setProgress(90);
		progressMonitor.setNote("Load completed.");
		variamosEditor.resetEditor();

		return true;
	}

	/**
	 * @param filename to save
	 * @return true when ends
	 * @throws InterruptedException
	 * @throws FunctionalException
	 */
	private boolean saveModelAction(String filename) throws InterruptedException, FunctionalException {
		setProgress(1);
		int modelViewIndex = variamosEditor.getModelViewIndex();
		int modelSubViewIndex = variamosEditor.getModelSubViewIndex();
		progressMonitor.setNote("Preparing to save file");
		mxCodec codec = new mxCodec();
		mxGraph outGraph = SharedActions.beforeGraphOperation(graph, true, variamosEditor.getModelViewIndex(),
				variamosEditor.getModelSubViewIndex());
		progressMonitor.setNote("Coding graph...");
		if (progressMonitor.isCanceled()) {
			throw (new InterruptedException());
		}
		setProgress(10);
		String xml = mxXmlUtils.getXml(codec.encode(outGraph.getModel()));
		if (progressMonitor.isCanceled()) {
			throw (new InterruptedException());
		}
		setProgress(70);
		progressMonitor.setNote("DataModel update...");
		SharedActions.afterSaveGraph(graph, variamosEditor);
		setProgress(80);
		if (progressMonitor.isCanceled()) {
			throw (new InterruptedException());
		}
		progressMonitor.setNote("Writing file...");
		try {
			mxUtils.writeFile(xml, filename);

			String file = filename.substring(0, filename.lastIndexOf('.'));
			file += ".backup." + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "." + ext;
			setProgress(87);
			progressMonitor.setNote("Saving backup");
			mxUtils.writeFile(xml, file);

			setProgress(90);
			progressMonitor.setNote("Updating DataModel");
			variamosEditor.updateObjects();
			// variamosEditor.setVisibleModel(0, -1);
			variamosEditor.setDefaultButton();
			variamosEditor.setModified(false);
			variamosEditor.setCurrentFile(new File(filename));
			setProgress(80);
			progressMonitor.setNote("Save Completed");
			variamosEditor.setVisibleModel(modelViewIndex, modelSubViewIndex);
			variamosEditor.setSelectedTab(modelViewIndex);
			variamosEditor.updateView();

		} catch (IOException e) {
			throw new FunctionalException(e);
		}
		return true;
	}

	/**
	 * Saves a model as an image in format svg
	 * 
	 * @param variamosEditor:
	 *            perspective used to save the file
	 * @param graph:
	 *            graph to be saved as an image
	 * @param filename}
	 * @author mxgraph example
	 * @throws IOException
	 */
	private boolean saveImage(FileTasksEnum fileType) throws IOException {

		FileHandlingAPI filesApi = new FileHandlingAPI();
		boolean sucess = false;
		switch (fileType) {
		case SAVE_IMAGE_SVG:
			sucess = filesApi.savesvg(variamosEditor, graph, filename);
			break;
		case SAVE_IMAGE_PNG:
			sucess = filesApi.saveXmlPng(variamosEditor, filename, bgColor);
			break;
		case SAVE_IMAGE_OTHERS:
			sucess = filesApi.saveImages(variamosEditor, filename, bgColor, ext);
			break;
		}
		if (sucess == true) {
			setProgress(80);
			progressMonitor.setNote("Save Completed");
		} else {
			//Shows an error message at the main window
			progressMonitor.setProgress(100);
			JOptionPane.showMessageDialog(variamosEditor, mxResources.get("noImageData"), "Saving error",
					JOptionPane.ERROR_MESSAGE, null);
		}
		return true;
	}

	@Override
	public void done() {
				
		
	}

}