package com.cfm.productline.configurator.gui.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.cfm.productline.ProductLine;
import com.cfm.productline.configurator.Configurator;
import com.cfm.productline.configurator.io.ConfigurationIO;
import com.cfm.productline.editor.PLGReader;
import com.cfm.productline.editor.ProductLineGraph;
import com.cfm.productline.editor.actions.ConfiguratorEditorAction;
import com.cfm.productline.io.SXFMReader;

import fm.FeatureModelException;

@SuppressWarnings("serial")
public class LoadConfigurationAction extends ConfiguratorEditorAction {
	protected String lastDir;
	private Configurator configurator;
	
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
			configurator.setConfiguration(ConfigurationIO.loadFromFile(fileAbsPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
