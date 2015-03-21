package com.variamos.gui.perspeditor.panels;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.SwingWorker;

import com.variamos.configurator.io.ConfigurationIO;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.perspsupport.perspmodel.Refas2Hlcl;

public class MonitoringWorker extends SwingWorker<Void, Void> {
	private String monitoredDirectory;
	private String outputDirectory;
	private boolean iterative;
	private int time;
	private boolean firstSolution;
	private boolean canceled = false;
	private VariamosGraphEditor editor;

	public MonitoringWorker(VariamosGraphEditor editor,
			String monitoredDirectory, String outputDirectory, int time,
			boolean iterative, boolean firstSolution) {
		super();
		this.editor = editor;
		this.monitoredDirectory = monitoredDirectory;
		this.outputDirectory = outputDirectory;
		this.iterative = iterative;
		this.time = time;
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
				System.out.println(monitoredFile.getAbsolutePath());
				Map<String, Integer> config = ConfigurationIO
						.loadMapFromFile(monitoredFile.getAbsolutePath());
				List<String> selectedAttributes = new ArrayList<String>();
				selectedAttributes.add("ConfigSelected");
				selectedAttributes.add("Selected");
				List<String> notAvailableAttributes = new ArrayList<String>();
				notAvailableAttributes.add("ConfigNotSelected");
				notAvailableAttributes.add("NotAvailable");
				editor.getRefas2hlcl().cleanGUIElements(Refas2Hlcl.DESIGN_EXEC);
				editor.getRefas2hlcl().updateGUIElements(selectedAttributes,
						notAvailableAttributes, config);
				//editor.editPropertiesRefas();				
				editor.executeSimulation(true, false, Refas2Hlcl.SIMUL_EXEC, true,
						"Simul");
				// ConfigurationIO.saveMapToFile(editor.getRefas2hlcl()
				// .getConfiguration().getConfiguration(), outputDirectoryFile +
				// "solution"
				// + solIndex + ".conf");
			}

			filePosition++;
			solIndex++;

			if (filePosition >= monitoredFiles.length)
				filePosition = 0;
			Thread.sleep(time * 1000);
		}
		return null;
	}

}
