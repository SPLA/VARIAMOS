package com.variamos.gui.perspeditor.panels;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.SwingWorker;

import com.variamos.configurator.io.ConfigurationIO;
import com.variamos.dynsup.translation.ModelExpr2HLCL;
import com.variamos.dynsup.translation.SolverTasks;
import com.variamos.gui.maineditor.VariamosGraphEditor;

/**
 * A class to support the thread for simulation of configurations. Part of PhD
 * work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2015-03-20
 * @see com.variamos.gui.pl.editor.VariabilityAttributeList
 */
public class MonitoringWorker extends SwingWorker<Void, Void> {
	private String initialConfigFile;
	private String monitoredDirectory;
	private String outputDirectory;
	private float waitBetweenExecs;
	private boolean canceled = false;
	private VariamosGraphEditor editor;
	private String results = "";
	private float waitAfterNoSolution;
	private boolean includeVariables;
	private boolean mapeAP;
	private boolean iterative;
	private boolean includeOpers;
	private boolean includeAssets;

	public String getResults() {
		return results;
	}

	public MonitoringWorker(VariamosGraphEditor editor,
			String initialConfigFile, String monitoredDirectory,
			String outputDirectory, float waitBetweenExecs,
			float waitAfterNoSolution, boolean includeVariables,
			boolean includeOpers, boolean includeAssets, boolean mapeAP,
			boolean iterative, boolean firstSolution) {
		super();
		this.editor = editor;
		this.initialConfigFile = initialConfigFile;
		this.monitoredDirectory = monitoredDirectory;
		this.outputDirectory = outputDirectory;
		this.waitBetweenExecs = waitBetweenExecs;
		this.waitAfterNoSolution = waitAfterNoSolution;
		this.includeVariables = includeVariables;
		this.includeOpers = includeOpers;
		this.includeAssets = includeAssets;
		this.mapeAP = mapeAP;
		this.iterative = iterative;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	@Override
	protected Void doInBackground() {
		try {
			File monitoredDirectoryFile = new File(monitoredDirectory);
			File outputDirectoryFile = new File(outputDirectory);
			File initialConfigFileObject = new File(initialConfigFile);
			int filePosition = 0, solIndex = 0;
			File monitoredFiles[] = monitoredDirectoryFile.listFiles();
			long lastModifiedFile = 0;

			File monitoredFile = initialConfigFileObject;
			Map<String, Number> lastConfig = null;
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
					Map<String, Number> config = ConfigurationIO
							.loadMapFromFile(monitoredFile.getAbsolutePath());
					List<String> selectedAttributes = new ArrayList<String>();
					selectedAttributes.add("ConfSel");
					selectedAttributes.add("Sel");
					List<String> notAvailableAttributes = new ArrayList<String>();
					// notAvailableAttributes.add("ConfNotSel");
					// notAvailableAttributes.add("NotAvailable");
					List<String> conceptTypes = new ArrayList<String>();

					if (includeVariables || lastConfig == null) {
						conceptTypes.add("GlobalVariable");// TODO only external
						// variables
						conceptTypes.add("ContextVariable");
						conceptTypes.add("Variable");
					}
					if (includeOpers || lastConfig == null) {
						conceptTypes.add("OPER");
						conceptTypes.add("LeafFeature");
					}
					if (includeAssets || lastConfig == null) {
						conceptTypes.add("Assets");
					}
					// If no change, not continue
					lastConfig = config;
					editor.getRefas2hlcl().cleanGUIElements(
							ModelExpr2HLCL.DESIGN_EXEC);
					editor.getRefas2hlcl().updateGUIElements(
							selectedAttributes, notAvailableAttributes,
							conceptTypes, null, config);
					// editor.editPropertiesRefas();
					if (mapeAP) {
						try {
							SolverTasks task = editor.executeSimulation(true,
									false, ModelExpr2HLCL.SIMUL_MAPE, true,
									"Simul");
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

								Thread.sleep((int) (waitAfterNoSolution * 1000) + 10);
								if (canceled)
									return null;
								conceptTypes = new ArrayList<String>();
								conceptTypes.add("GlobalVariable"); // TODO
																	// only
																	// external
																	// variables
								conceptTypes.add("ContextVariable");
								conceptTypes.add("Variable");
								editor.getRefas2hlcl().cleanGUIElements(
										ModelExpr2HLCL.DESIGN_EXEC);
								editor.getRefas2hlcl().updateGUIElements(
										selectedAttributes,
										notAvailableAttributes, conceptTypes,
										null, config);
								task = editor.executeSimulation(true, false,
										ModelExpr2HLCL.SIMUL_MAPE, true,
										"Simul");
								while (task.getProgress() != 100) {
									Thread.sleep(100);
									if (canceled)
										return null;
								}
								task.setTerminated(true);
							}
						} catch (Exception e) {

						}

					} else {
						editor.updateDashBoard(true, false, true);
						editor.editPropertiesRefas();
					}

					ConfigurationIO.saveMapToFile(editor.getRefas2hlcl()
							.getConfiguration().getConfiguration(),
							outputDirectoryFile + "/solution" + solIndex
									+ ".conf");

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
								this.firePropertyChange("results", results,
										results + "New context file found...\n");
							}
						// if (!includeVariables)
						// noNewFile = false;
						Thread.sleep(100);
						if (canceled)
							return null;
					}
				}

				if (filePosition >= monitoredFiles.length)
					filePosition = 0;
				Thread.sleep((int) (waitBetweenExecs * 1000) + 10);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
