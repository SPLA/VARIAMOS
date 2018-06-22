package com.variamos.gui.perspeditor.actions;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;
import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.common.core.exceptions.MXGraphException;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.gui.api.FileHandlingAPI;
import com.variamos.gui.core.io.ConsoleTextArea;
import com.variamos.gui.core.io.MxGraphReader;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;

/**
 * Swing worker to control asynchronous tasks for saving and loading files
 * 
 * @author Juan C. Munoz Fernandez <jcmunoz@gmail.com>
 * @author refactor by Luisa Rincon <lufe089@gmail.com>
 * @since 9/19/2017 {@link}
 */
public class FileTasks extends SwingWorker<Void, Void> {
	public static enum FileTasksEnum {
		OPEN_MODEL, SAVE_MODEL, SAVE_IMAGE_SVG, SAVE_IMAGE_PNG, SAVE_IMAGE_OTHERS
	}

	private File file;
	private String filename;
	private String ext;
	private FileTasksEnum execType;
	private ProgressMonitor progressMonitor;
	private VariamosGraphEditor variamosEditor;
	private mxGraph graph;
	private Color bgColor;
	private Runnable callFinally;

	public FileTasks(ProgressMonitor progressMonitor) {
		super();
		this.progressMonitor = progressMonitor;

	}

	public FileTasks(ProgressMonitor progressMonitor, FileTasksEnum execType, File file,
			VariamosGraphEditor variamosEditor, mxGraph graph, final Runnable callFinally) {
		this.progressMonitor = progressMonitor;
		this.file = file;
		this.execType = execType;
		this.variamosEditor = variamosEditor;
		this.graph = graph;
		this.callFinally = callFinally;
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

	/*
	 * (non-Javadoc) This method calls asynchronous actions according to the param
	 * execType
	 * 
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	public Void doInBackground() throws InterruptedException, MXGraphException, IOException, FunctionalException {
		setProgress(0);

		Thread.sleep(1);
		switch (execType) {
		case OPEN_MODEL:
			openModelAction(file);
			break;
		case SAVE_MODEL:
			saveModel(filename);
			break;
		// Calls the same method, then no break is required
		case SAVE_IMAGE_SVG:
		case SAVE_IMAGE_PNG:
		case SAVE_IMAGE_OTHERS:
			saveImage(execType);
			break;
		default:
			setProgress(100);

		}

		// update the progress of this task will notify the user interface because the
		// listener will be called
		int taskProgress = 100;
		setProgress(taskProgress);
		((MainFrame) variamosEditor.getFrame()).waitingCursor(false);

		return null;
	}

	/**
	 * Logic for opening xml dynamic files previously created with variamos
	 * 
	 * @param file
	 *            to read
	 * @return
	 * @throws InterruptedException
	 * @throws MXGraphException
	 * @throws IOException
	 */
	private boolean openModelAction(File file) throws InterruptedException, MXGraphException, IOException {
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

		// ###### Open the xml file
		BufferedReader reader = null;
		StringBuilder fileContent = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				fileContent.append(line);
				fileContent.append(ls);
				break;
			}

		} finally {
			// Close the buffer even if the method throws exceptions
			reader.close();

		}
		setProgress(50);

		// Converting models into a new version
		progressMonitor.setNote("Converting MM (1.0.1.19 to 1.0.1.20)...");
		String[] versionstr = fileContent.toString().split("<add as=\"versionNumber\" value=\"");
		if (versionstr.length > 1) {
			String currentVersion = MainFrame.getVariamosVersionNumber();
			// ######## Call the API for converting files
			FileHandlingAPI fileApi = new FileHandlingAPI();
			fileApi.migrateModelVersion(fileContent, variamosEditor.getPerspective(), file, versionstr[1],
					currentVersion);

		} else {
			// When is not possible to convert the file
			JOptionPane.showMessageDialog(variamosEditor,
					"VariaMos keeped the compatibility of models until Version Beta 18. \n"
							+ " Nevertheless, models created in version Beta 18 and older are not\n"
							+ " compatible with this version of VariaMos. The model you are trying \n"
							+ "to load seems to be from an old version, please check again. \n"
							+ "The process will continue but the model will be broken.",
					"Model Incompatibility Message", JOptionPane.INFORMATION_MESSAGE, null);
		}

		// Read file version
		MxGraphReader.loadMxGraph(file, graph, variamosEditor);

		InstAttribute rootAttributes = (InstAttribute) ((mxCell) graph.getModel().getRoot()).getChildAt(0).getValue();

		if (versionstr.length > 1) {
			String modelVersion = (String) rootAttributes.getInstAttributeAttribute("versionNumber");

			// TODO: to convert an old version model to a new version (edit
			// xml),
			// validate modelVersion and currentVersion to evaluate the
			// correct: modelVersion.equals("1.0.1.19") &&
			// currentVersion.equals("1.0.1.20")
			String currentVersion = MainFrame.getVariamosVersionNumber();
			if (modelVersion.equals("1.0.1.19") && currentVersion.equals("1.0.1.20")
					&& (variamosEditor.getPerspective() == 1 || variamosEditor.getPerspective() == 3)) {

				setProgress(50);
				progressMonitor.setNote("Converting MM (1.0.1.19 to 1.0.1.20)...");
				// Assign the new version to avoid reconverting models
				rootAttributes.setInstAttributeAttribute("versionNumber", "1.0.1.20");

			}

			// validation example
			if (modelVersion.equals("1.0.1.20") && currentVersion.equals("1.0.1.21")) {
				// FIXME Complete
			}
		}

		variamosEditor.setCurrentFile(file);
		if (progressMonitor.isCanceled()) {
			throw (new InterruptedException());
		}
		setProgress(70);
		progressMonitor.setNote("Updating DataModel...");
		SharedActions.afterOpenCloneGraph(graph, variamosEditor);
		SharedActions.afterOpenCloneGraph(graph, variamosEditor);
		setProgress(90);
		variamosEditor.resetEditor();
		progressMonitor.setNote("Load completed.");
		System.gc();
		return true;
	}

	/**
	 * @param filename
	 *            to save
	 * @return true when ends
	 * @throws InterruptedException
	 * @throws FunctionalException
	 * @throws IOException
	 */
	private boolean saveModel(String filename) throws InterruptedException, FunctionalException, IOException {
		setProgress(1);
		int modelViewIndex = variamosEditor.getModelViewIndex();
		int modelSubViewIndex = variamosEditor.getModelSubViewIndex();
		progressMonitor.setNote("Preparing to save file");
		setProgress(10);
		mxGraph outGraph = SharedActions.beforeGraphOperation(graph, true, variamosEditor.getModelViewIndex(),
				variamosEditor.getModelSubViewIndex());

		// Get the XML file to save from the graph
		mxCodec codec = new mxCodec();
		String xmlInfo = mxXmlUtils.getXml(codec.encode(outGraph.getModel()));
		SharedActions.afterSaveGraph(graph, variamosEditor);

		if (progressMonitor.isCanceled()) {
			throw (new InterruptedException());
		}
		setProgress(50);

		progressMonitor.setNote("Writing file...");
		FileHandlingAPI fileApi = new FileHandlingAPI();
		fileApi.savesxmlFile(xmlInfo, filename, ext);
		if (progressMonitor.isCanceled()) {
			throw (new InterruptedException());
		}
		setProgress(80);

		progressMonitor.setNote("Updating DataModel");
		variamosEditor.updateObjects();
		variamosEditor.setDefaultButton();
		variamosEditor.setModified(false);
		variamosEditor.setCurrentFile(new File(filename));
		if (progressMonitor.isCanceled()) {
			throw (new InterruptedException());
		}
		setProgress(90);
		progressMonitor.setNote("Save Completed");
		variamosEditor.setVisibleModel(modelViewIndex, modelSubViewIndex);
		variamosEditor.setSelectedTab(modelViewIndex);
		variamosEditor.updateView();

		return true;
	}

	/**
	 * Calls methods inside the filesAPI to save a model as an image
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
		mxGraphComponent graphComponent = variamosEditor.getGraphComponent();
		switch (fileType) {
		case SAVE_IMAGE_SVG:
			sucess = filesApi.savesvg(graph, filename);
			break;
		case SAVE_IMAGE_PNG:
			sucess = filesApi.saveXmlPng(graphComponent, filename, bgColor);
			if (sucess) {
				variamosEditor.setModified(false);
				variamosEditor.setCurrentFile(new File(filename));
			}
			break;
		case SAVE_IMAGE_OTHERS:
			sucess = filesApi.saveImages(graphComponent, filename, bgColor, ext);
			if (sucess) {
				variamosEditor.setModified(false);
				variamosEditor.setCurrentFile(new File(filename));
			}
			break;
		}
		if (sucess == true) {
			setProgress(80);
			progressMonitor.setNote("Save Completed");
		} else {
			// Shows an error message at the main window
			progressMonitor.setProgress(100);
			JOptionPane.showMessageDialog(variamosEditor, mxResources.get("noImageData"), "Saving error",
					JOptionPane.ERROR_MESSAGE, null);
		}
		System.gc();
		return true;
	}

	/*
	 * (non-Javadoc) get the results from doInBackground. If any exception is
	 * launched, then it is handled here
	 * 
	 * @see javax.swing.SwingWorker#done()
	 */
	@Override
	protected void done() {
		try {
			// get the results from doInBackground. If any exception is launched, then it is
			// handled here
			get();
		} catch (InterruptedException e) {
			String msg = String.format("Unexpected problem: %s see the console for more details",
					e.getCause().toString());
			JOptionPane.showMessageDialog(variamosEditor, msg, "error: ", JOptionPane.ERROR_MESSAGE, null);
		} catch (ExecutionException e) {
			if (e.getCause() instanceof FileNotFoundException) {
				String msg = String.format("File not found");
				JOptionPane.showMessageDialog(variamosEditor, msg, "Error", JOptionPane.ERROR_MESSAGE, null);
				ConsoleTextArea.addText(e.getMessage());
				e.printStackTrace();
			} else if (e.getCause() instanceof MXGraphException) {
				JOptionPane.showMessageDialog(variamosEditor,
						"unable to load the model. The structure of the file was not understood.",
						"Model load error: " + e.getMessage(), JOptionPane.ERROR_MESSAGE, null);
				ConsoleTextArea.addText(e.getMessage());
				e.printStackTrace();
			} else if (e.getCause() instanceof IOException) {
				JOptionPane.showMessageDialog(variamosEditor,
						"unable to save/load the model. Impossible to write or read the file by using the mxgraph library",
						"Model load/save error: ", JOptionPane.ERROR_MESSAGE, null);
				ConsoleTextArea.addText(e.getMessage());
				e.printStackTrace();
			}

		} finally {
			// Ends the progress for finishing the progress monitor even if exceptions were
			// launched
			setProgress(100);
			
			//System.out.println("En FileTask.done() .. finally");
			if(callFinally!=null) {
				//System.out.println("**** callFinally!=null ****");
				callFinally.run();
			}
		}

	}

}