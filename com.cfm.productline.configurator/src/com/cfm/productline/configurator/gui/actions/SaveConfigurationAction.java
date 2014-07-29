package com.cfm.productline.configurator.gui.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.cfm.productline.ProductLine;
import com.cfm.productline.configurator.Configurator;
import com.cfm.productline.configurator.io.ConfigurationDTO;
import com.cfm.productline.configurator.io.ConfigurationIO;
import com.cfm.productline.editor.actions.ConfiguratorEditorAction;
import com.cfm.productline.io.SXFMWriter;
import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.mxgraph.examples.swing.editor.DefaultFileFilter;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;

import edu.unal.model.enums.PrologEditorType;
import edu.unal.tranformer.FeatureModelSPLOTransformer;
import fm.FeatureModel;
import fm.FeatureModelException;
import fm.XMLFeatureModel;

@SuppressWarnings("serial")
public class SaveConfigurationAction extends ConfiguratorEditorAction {

	
	/**
	 * 
	 */
	protected boolean showDialog;

	/**
	 * 
	 */
	protected String lastDir = null;

	/**
	 * 
	 */
	public SaveConfigurationAction(boolean showDialog)
	{
		this.showDialog = showDialog;
	}


	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e)
	{
		BasicGraphEditor editor = getEditor(e);

		if (editor != null)
		{
			FileFilter selectedFilter = null;
			String filename = null;
			boolean dialogShown = false;

			if (showDialog || editor.getCurrentFile() == null)
			{
				String wd;

				if (lastDir != null)
				{
					wd = lastDir;
				}
				else if (editor.getCurrentFile() != null)
				{
					wd = editor.getCurrentFile().getParent();
				}
				else
				{
					wd = System.getProperty("user.dir");
				}

				JFileChooser fc = new JFileChooser(wd);

				// Adds the default file format
				FileFilter defaultFilter = new DefaultFileFilter(
						".conf", "Configuration Files (.conf)");

				fc.setFileFilter(defaultFilter);
				int rc = fc.showDialog(null, mxResources.get("save"));
				dialogShown = true;

				if (rc != JFileChooser.APPROVE_OPTION)
				{
					return;
				}
				else
				{
					lastDir = fc.getSelectedFile().getParent();
				}

				filename = fc.getSelectedFile().getAbsolutePath();
				
				if( !filename.endsWith(".conf") )
					filename += ".conf";
			}

			try
			{
				Configurator configurator = getEditor(e).getConfigurator();
				ConfigurationIO.saveToFile(configurator.getConfigurationDTO(), filename);
			}
			catch (Throwable ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,
						ex.toString(), mxResources.get("error"),
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void generatePrologFile(ProductLine pl, String filename) throws IOException, FeatureModelException {
		SXFMWriter writer = new SXFMWriter();
		System.out.println(writer.getSXFMContent(pl));
		
		File f = File.createTempFile("test", "tmp");
		writer.writeSXFM(pl, f);
		
		FeatureModel featureModel = new XMLFeatureModel(
				f.getAbsolutePath(),
				XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
		featureModel.loadModel();
		
		FeatureModelSPLOTransformer transformer = new FeatureModelSPLOTransformer();
		mxUtils.writeFile(transformer.getPrologString(featureModel, PrologEditorType.GNU_PROLOG), filename);
	}

}