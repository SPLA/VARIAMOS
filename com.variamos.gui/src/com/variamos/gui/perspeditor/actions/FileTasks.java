package com.variamos.gui.perspeditor.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;
import com.variamos.configurator.io.PLGReader;
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.io.ConsoleTextArea;

public class FileTasks extends SwingWorker<Void, Void> {
	public String getErrorTitle() {
		return errorTitle;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	private String executionTime = "";
	private String errorTitle = "";
	private String errorMessage = "";
	private File file;
	private String filename;
	private String ext;
	private int task = 0;
	private int execType;
	private ProgressMonitor progressMonitor;
	public static final int OPEN = 0, SAVE = 1;
	private VariamosGraphEditor variamosEditor;
	private mxGraph graph;

	public FileTasks(ProgressMonitor progressMonitor) {
		super();
		this.progressMonitor = progressMonitor;

	}

	public FileTasks(ProgressMonitor progressMonitor, int execType, File file,
			VariamosGraphEditor variamosEditor, mxGraph graph) {
		this.progressMonitor = progressMonitor;
		this.file = file;
		this.execType = execType;
		this.variamosEditor = variamosEditor;
		this.graph = graph;
	}

	public FileTasks(ProgressMonitor progressMonitor, int execType,
			String filename, String ext, VariamosGraphEditor variamosEditor,
			mxGraph graph) {
		this.progressMonitor = progressMonitor;
		this.filename = filename;
		this.ext = ext;
		this.execType = execType;
		this.variamosEditor = variamosEditor;
		this.graph = graph;
	}

	@Override
	public Void doInBackground() {
		setProgress(0);
		try {
			Thread.sleep(1);
			switch (execType) {
			case OPEN:
				openAction(file);
				break;
			case SAVE:
				saveAction(filename);
				break;
			case 2:
				break;
			case 3:
				break;
			}
		} catch (InterruptedException ignore) {
		} catch (Exception e) {
			ConsoleTextArea.addText(e.getStackTrace());
			errorMessage = "Unmanaged error on file operation.";
			errorTitle = "File action error";
		}
		task = 100;
		setProgress(task);
		((MainFrame) variamosEditor.getFrame()).waitingCursor(false);
		variamosEditor.setFileTask(null);
		return null;
	}

	public boolean openAction(File file) throws InterruptedException {
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
		String[] versionstr = stringBuilder.toString().split(
				"<add as=\"versionNumber\" value=\"");
		String version = "";
		String currentVersion = MainFrame.getVariamosVersionNumber();
		if (versionstr.length > 1) {
			version = (stringBuilder.toString().split(
					"<add as=\"versionNumber\" value=\"")[1]
					.split("\"/></Array>"))[0];

			// TODO: to convert an old version model to a new version (file
			// before
			// loading), validate modelVersion and currentVersion to evaluate
			// the
			// correct: modelVersion.equals("1.0.1.19") &&
			// currentVersion.equals("1.0.1.20")

			// validation example
			if (version.equals("1.0.1.19") && currentVersion.equals("1.0.1.20")) {
				setProgress(50);
				progressMonitor.setNote("Converting Model File...");
				// Include your edition of the text file here, update the file
				// object with the new file
			}
			// System.out.println(stringBuilder.toString());
			// endTime = System.currentTimeMillis();
			// System.out.println("read version " + (endTime - iniTime));
		} else {
			JOptionPane
					.showMessageDialog(
							variamosEditor,
							"VariaMos keeped the compatibility of models until Version Beta 18. \n"
									+ " Nevertheless, models created in version Beta 18 and older are not\n"
									+ " compatible with this version of VariaMos. The model you are trying \n"
									+ "to load seems to be from an old version, please check again. \n"
									+ "The process will continue but the model will be broken.",
							"Model Incompatibility Message",
							JOptionPane.INFORMATION_MESSAGE, null);
		}
		// Read file version
		try {
			PLGReader.loadPLG(file, graph, variamosEditor);
		} catch (Exception e) {
			JOptionPane
					.showMessageDialog(
							variamosEditor,
							"unable to load the model. The structure of the file was not understood.",
							"Model load error: " + e.getMessage(),
							JOptionPane.ERROR_MESSAGE, null);
			return false;
		}
		InstAttribute rootAttributes = (InstAttribute) ((mxCell) graph
				.getModel().getRoot()).getChildAt(0).getValue();

		if (versionstr.length > 1) {
			String modelVersion = (String) rootAttributes
					.getInstAttributeAttribute("versionNumber");

			// TODO: to convert an old version model to a new version (edit
			// xml),
			// validate modelVersion and currentVersion to evaluate the
			// correct: modelVersion.equals("1.0.1.19") &&
			// currentVersion.equals("1.0.1.20")

			// validation example
			if (modelVersion.equals("1.0.1.19")
					&& currentVersion.equals("1.0.1.20")) {

				setProgress(50);
				progressMonitor.setNote("Converting DataModel...");
				// Include your of the graph object conversion here
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
		//variamosEditor.populateIndex(((AbstractGraph) graph).getProductLine());
		setProgress(90);
		progressMonitor.setNote("Load completed.");
		resetEditor(variamosEditor);

		return true;
	}

	public boolean saveAction(String filename) throws InterruptedException {
		setProgress(1);
		int modelViewIndex = variamosEditor.getModelViewIndex();
		int modelSubViewIndex = variamosEditor.getModelSubViewIndex();
		progressMonitor.setNote("Preparing to save file");
		mxCodec codec = new mxCodec();
		mxGraph outGraph = SharedActions.beforeGraphOperation(graph, true,
				variamosEditor.getModelViewIndex(),
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
			file += ".backup."
					+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
					+ "." + ext;
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
			setProgress(95);
			progressMonitor.setNote("Save Completed");
			variamosEditor.setVisibleModel(modelViewIndex, modelSubViewIndex);
			variamosEditor.setSelectedTab(modelViewIndex);
			variamosEditor.updateView();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	protected void resetEditor(VariamosGraphEditor editor) {
		editor.setVisibleModel(editor.getModelViewIndex(),
				editor.getModelSubViewIndex());
		editor.setDefaultButton();
		editor.updateView();
		editor.setModified(false);
		editor.getUndoManager().clear();
		editor.getGraphComponent().zoomAndCenter();
	}

	public int getExecType() {
		return execType;
	}

	public String getExecutionTime() {
		return executionTime;
	}

	@Override
	public void done() {
	}

	public static void openAction(int execType, File file,
			VariamosGraphEditor variamosEditor, mxGraph graph) {
		ProgressMonitor progressMonitor = new ProgressMonitor(variamosEditor,
				"File opening", "", 0, 100);
		variamosEditor.setProgressMonitor(progressMonitor);
		progressMonitor.setMillisToDecideToPopup(5);
		progressMonitor.setMillisToPopup(5);
		progressMonitor.setProgress(0);

		FileTasks task = new FileTasks(progressMonitor, execType, file,
				variamosEditor, graph);
		variamosEditor.setFileTask(task);
		task.addPropertyChangeListener(variamosEditor);
		((MainFrame) variamosEditor.getFrame()).waitingCursor(true);
		task.execute();
	}

	public static void saveAction(int execType, String file, String ext,
			VariamosGraphEditor variamosEditor, mxGraph graph) {
		ProgressMonitor progressMonitor = new ProgressMonitor(variamosEditor,
				"File saving", "", 0, 100);
		variamosEditor.setProgressMonitor(progressMonitor);
		progressMonitor.setMillisToDecideToPopup(5);
		progressMonitor.setMillisToPopup(5);
		progressMonitor.setProgress(0);

		FileTasks task = new FileTasks(progressMonitor, execType, file, ext,
				variamosEditor, graph);
		variamosEditor.setFileTask(task);
		task.addPropertyChangeListener(variamosEditor);
		((MainFrame) variamosEditor.getFrame()).waitingCursor(true);
		task.execute();
	}
}
