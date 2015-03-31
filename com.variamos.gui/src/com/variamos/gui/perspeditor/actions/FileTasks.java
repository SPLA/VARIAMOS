package com.variamos.gui.perspeditor.actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;

import com.mxgraph.io.mxCodec;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;
import com.variamos.configurator.io.PLGReader;
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.perspsupport.perspmodel.Refas2Hlcl;
import com.variamos.perspsupport.perspmodel.SolverTasks;

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
			e.printStackTrace();
			errorMessage = "Unmanaged error on file operation.";
			errorTitle = "File action error";
		}
		task = 100;
		setProgress((int) task);
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
		PLGReader.loadPLG(file, graph, variamosEditor);
		variamosEditor.setCurrentFile(file);
		if (progressMonitor.isCanceled()) {
			throw (new InterruptedException());
		}
		setProgress(70);
		progressMonitor.setNote("Updating DataModel...");
		SharedActions.afterOpenCloneGraph(graph, variamosEditor);
		variamosEditor.populateIndex(((AbstractGraph) graph).getProductLine());
		setProgress(90);
		progressMonitor.setNote("Load completed.");
		resetEditor(variamosEditor);

		return true;
	}

	public boolean saveAction(String filename) throws InterruptedException {
		setProgress(1);
		int modelViewIndex =variamosEditor.getModelViewIndex();
		int modelSubViewIndex =variamosEditor.getModelSubViewIndex();
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
			variamosEditor.setVisibleModel(modelViewIndex,
					modelSubViewIndex);
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
