package com.cfm.productline.configurator.gui.actions;

import java.awt.event.ActionEvent;

import com.cfm.productline.editor.ProductLineEditor;
import com.cfm.productline.editor.actions.ConfiguratorEditorAction;
import com.mxgraph.util.mxResources;

@SuppressWarnings("serial")
public class ConfigureAction extends ConfiguratorEditorAction {

	public ConfigureAction() {
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		ProductLineEditor editor = getEditor(evt);
		editor.bringUpExtension(mxResources.get("configurationTab"));
		editor.getConfigurator().configure(editor.getEditedProductLine());
		
		//editor.getConfigurator().performConfiguration();
	}
	
}
