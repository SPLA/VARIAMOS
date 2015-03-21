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
	private String monitoredDirectory;
	private String outputDirectory;
	private boolean iterative;
	private int waitBetweenExecs;
	private boolean firstSolution;
	private boolean canceled = false;
	private VariamosGraphEditor editor;
	private String results = "";
	private int waitAfterNoSolution;

	public String getResults() {
		return results;
	}

	public MonitoringWorker(VariamosGraphEditor editor,
			String monitoredDirectory, String outputDirectory,
			int waitBetweenExecs, int waitAfterNoSolution, boolean iterative,
			boolean firstSolution) {
		super();
		this.editor = editor;
		this.monitoredDirectory = monitoredDirectory;
		this.outputDirectory = outputDirectory;
		this.iterative = iterative;
		this.waitBetweenExecs = waitBetweenExecs;
		this.waitAfterNoSolution = waitAfterNoSolution;
		this.firstSolution = firstSolution;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	@Override
	protected Void doInBackground() throws Exception {

		File monitoredDirectoryFile = new File(monitoredDirectory);
		File outputDirectoryFile = new File(outputDirectory);
		int filePosition = 0, solIndex = 0;
		File monitoredFiles[] = monitoredDirectoryFile.listFiles();
		while (!canceled && iterative) {
			File monitoredFile = monitoredFiles[filePosition];
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
				conceptTypes.add("GlobalVariable");// TODO only external
													// variables
				conceptTypes.add("ContextVariable");
				editor.getRefas2hlcl().cleanGUIElements(Refas2Hlcl.DESIGN_EXEC);
				editor.getRefas2hlcl().updateGUIElements(selectedAttributes,
						notAvailableAttributes, conceptTypes, config);
				// editor.editPropertiesRefas();
				SolverTasks task = editor.executeSimulation(true, false,
						Refas2Hlcl.SIMUL_MAPE, true, "Simul");
				while (task.getProgress() != 100) {
					Thread.sleep(100);
				}
				task.setTerminated(true);
				if (!task.isCorrectExecution()) {
					results += "No solution for actual configuration... alternative proposed\n";
					this.firePropertyChange(
							"results",
							results,
							results
									+ "No solution for actual configuration... alternative proposed\n");

					Thread.sleep(waitAfterNoSolution * 1000);
					conceptTypes = new ArrayList<String>();
					conceptTypes.add("GlobalVariable"); // TODO only external
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
					}
					task.setTerminated(true);
				}
				ConfigurationIO.saveMapToFile(editor.getRefas2hlcl()
						.getConfiguration().getConfiguration(),
						outputDirectoryFile + "/solution" + solIndex + ".conf");
			}

			filePosition++;
			solIndex++;

			if (filePosition >= monitoredFiles.length)
				filePosition = 0;
			Thread.sleep(waitBetweenExecs * 1000);
		}
		return null;
	}

}
