package com.variamos.gui.perspeditor.panels;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.SwingWorker;

import com.variamos.configurator.io.ConfigurationIO;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.perspsupport.perspmodel.Refas2Hlcl;
import com.variamos.perspsupport.perspmodel.SolverTasks;

public class MonitoringWorker extends SwingWorker<Void, Void> {
	private String initialConfigFile;
	private String monitoredDirectory;
	private String outputDirectory;
	private int waitBetweenExecs;
	private boolean canceled = false;
	private VariamosGraphEditor editor;
	private String results = "";
	private int waitAfterNoSolution;
	private boolean includeVariables;
	private boolean mapeAP;
	private boolean iterative;
	private boolean firstSolution;

	public String getResults() {
		return results;
	}

	public MonitoringWorker(VariamosGraphEditor editor,
			String initialConfigFile, String monitoredDirectory,
			String outputDirectory, int waitBetweenExecs,
			int waitAfterNoSolution, boolean includeVariables, boolean mapeAP,
			boolean iterative, boolean firstSolution) {
		super();
		this.editor = editor;
		this.initialConfigFile = initialConfigFile;
		this.monitoredDirectory = monitoredDirectory;
		this.outputDirectory = outputDirectory;
		this.waitBetweenExecs = waitBetweenExecs;
		this.waitAfterNoSolution = waitAfterNoSolution;
		this.includeVariables = includeVariables;
		this.mapeAP = mapeAP;
		this.iterative = iterative;
		this.firstSolution = firstSolution;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	@Override
	protected Void doInBackground() throws Exception {

		File monitoredDirectoryFile = new File(monitoredDirectory);
		File outputDirectoryFile = new File(outputDirectory);
		File initialConfigFileObject =new File(initialConfigFile);
		int filePosition = 0, solIndex = 0;
		File monitoredFiles[] = monitoredDirectoryFile.listFiles();
		long lastModifiedFile = 0;

		File monitoredFile = initialConfigFileObject;
		while (!canceled) {
			if (iterative)
				monitoredFile = monitoredFiles[filePosition];
			if (monitoredFile.exists()) {
				results += "ConfigFile loaded: "
						+ monitoredFile.getAbsolutePath() + "\n";
				this.firePropertyChange(
						"results",
						results,
						results + "ConfigFile loaded: "
								+ monitoredFile.getAbsolutePath() + "\n");
				Map<String, Integer> config = ConfigurationIO
						.loadMapFromFile(monitoredFile.getAbsolutePath());
				List<String> selectedAttributes = new ArrayList<String>();
				selectedAttributes.add("ConfigSelected");
				selectedAttributes.add("Selected");
				List<String> notAvailableAttributes = new ArrayList<String>();
				// notAvailableAttributes.add("ConfigNotSelected");
				// notAvailableAttributes.add("NotAvailable");
				List<String> conceptTypes = new ArrayList<String>();
				conceptTypes.add("OPER");
				if (includeVariables) {
					conceptTypes.add("GlobalVariable");// TODO only external
					// variables
					conceptTypes.add("ContextVariable");
				}
				editor.getRefas2hlcl().cleanGUIElements(Refas2Hlcl.DESIGN_EXEC);
				editor.getRefas2hlcl().updateGUIElements(selectedAttributes,
						notAvailableAttributes, conceptTypes, config);
				// editor.editPropertiesRefas();
				if (mapeAP) {
					SolverTasks task = editor.executeSimulation(true, false,
							Refas2Hlcl.SIMUL_MAPE, true, "Simul");
					while (task.getProgress() != 100) {
						Thread.sleep(100);
						if (isCancelled())
							return null;
					}
					task.setTerminated(true);
					if (!task.isCorrectExecution() && includeVariables) {
						results += "No solution for actual configuration... alternative proposed\n";
						this.firePropertyChange(
								"results",
								results,
								results
										+ "No solution for actual configuration... alternative proposed\n");

						Thread.sleep(waitAfterNoSolution * 1000);
						if (canceled)
							return null;
						conceptTypes = new ArrayList<String>();
						conceptTypes.add("GlobalVariable"); // TODO only
															// external
															// variables
						conceptTypes.add("ContextVariable");
						editor.getRefas2hlcl().cleanGUIElements(
								Refas2Hlcl.DESIGN_EXEC);
						editor.getRefas2hlcl().updateGUIElements(
								selectedAttributes, notAvailableAttributes,
								conceptTypes, config);
						task = editor.executeSimulation(true, false,
								Refas2Hlcl.SIMUL_MAPE, true, "Simul");
						while (task.getProgress() != 100) {
							Thread.sleep(100);
							if (canceled)
								return null;
						}
						task.setTerminated(true);
					}
				} else {
					editor.updateDashBoard(false, true);
					editor.editPropertiesRefas();
				}

				ConfigurationIO.saveMapToFile(editor.getRefas2hlcl()
						.getConfiguration().getConfiguration(),
						outputDirectoryFile + "/solution" + solIndex + ".conf");
			}

			filePosition++;
			solIndex++;
			if (!iterative) {
				results += "Waiting for context file...\n";
				this.firePropertyChange("results", results, results
						+ "Waiting for context file...\n");
				boolean noNewFile = true;
				if (!monitoredFile.equals(initialConfigFileObject))
					monitoredFile.delete();
				while (noNewFile) {
					monitoredFiles = monitoredDirectoryFile.listFiles();
					for (File testFile : monitoredFiles)
						if (lastModifiedFile < testFile.lastModified()) {
							monitoredFile = testFile;
							lastModifiedFile = monitoredFile.lastModified();
							noNewFile = false;
							results += "New context file found...\n";
							this.firePropertyChange("results", results, results
									+ "New context file found...\n");
						}
					Thread.sleep(100);
					if (canceled)
						return null;
				}
			}

			if (filePosition >= monitoredFiles.length)
				filePosition = 0;
			Thread.sleep(waitBetweenExecs * 1000);
		}
		return null;
	}

}
