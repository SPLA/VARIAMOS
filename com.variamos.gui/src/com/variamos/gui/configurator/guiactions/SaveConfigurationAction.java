package com.variamos.gui.configurator.guiactions;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.mxgraph.util.mxResources;
import com.variamos.configurator.io.ConfigurationIO;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.DefaultFileFilter;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.pl.editor.ConfiguratorPanel;

/**
 * To document. Copied from configurator actions. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-17
 * @see com.variamos.gui.maineditor.pl.configurator.guiactions.SaveConfigurationAction
 */
@SuppressWarnings("serial")
public class SaveConfigurationAction extends AbstractEditorAction {

	
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
		VariamosGraphEditor editor = getEditor(e);

		if (editor != null)
		{
		//	FileFilter selectedFilter = null;
			String filename = null;
		//	boolean dialogShown = false;

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
						".xls", "Configuration Files (.xls)");

				fc.setFileFilter(defaultFilter);
				int rc = fc.showDialog(null, mxResources.get("save"));
		//		dialogShown = true;

				if (rc != JFileChooser.APPROVE_OPTION)
				{
					return;
				}
				else
				{
					lastDir = fc.getSelectedFile().getParent();
				}

				filename = fc.getSelectedFile().getAbsolutePath();
				
				if( !filename.endsWith(".xls") )
					filename += ".xls";
			}

			try
			{
				//ConfiguratorPanel configurator = getEditor(e).getConfigurator();
				editor.saveConfiguration(filename);
				
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


 	/**
 	 * @param pl
 	 * @param filename
 	 * @throws IOException
 	 * @throws FeatureModelException
 	 * 
 	 * jcmunoz: Commented unused method and imports of the method
 	 */
/* 	private void generatePrologFile(ProductLine pl, String filename) throws IOException, FeatureModelException {
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
*/
}