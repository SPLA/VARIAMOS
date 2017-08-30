package com.variamos.gui.configurator.guiactions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.pl.editor.ConfiguratorPanel;
import com.variamos.io.importExport.core.ConfigurationIO;

/**
 * To document. Copied from configurator actions. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-11-17
 * @see com.variamos.gui.maineditor.pl.configurator.guiactions.LoadConfigurationAction
 */
@SuppressWarnings("serial")
public class LoadConfigurationAction extends AbstractEditorAction {
	protected String lastDir;
	private ConfiguratorPanel configurator;
	
	public LoadConfigurationAction() {
//		super(configurator);
//		setLabel("Load Product Line...");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		configurator = getEditor(e).getConfigurator();
		
		String wd = (lastDir != null) ? lastDir : System.getProperty("user.dir");
		JFileChooser fc = new JFileChooser(wd);
		fc.setFileFilter(new FileFilter(){

			public boolean accept(File file){
				if( file.isDirectory() )
					return true;
				
				String lcase = file.getName().toLowerCase();

				return lcase.endsWith(".conf");
			}

			@Override
			public String getDescription() {
				return "Configuration File (.conf)";
			}
		});
		
		int response = fc.showOpenDialog(configurator);
		if( response != JFileChooser.APPROVE_OPTION )
			return;
		
		File f = fc.getSelectedFile();
		
		if( f.getName().endsWith(".conf") ){
			loadConf(f.getAbsolutePath());
			return;
		}
	}

	private void loadConf(String fileAbsPath) {
		try {
			configurator.setConfiguration(ConfigurationIO.loadObjectFromJSONFile(fileAbsPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
