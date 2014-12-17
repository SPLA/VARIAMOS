package com.variamos.gui.common.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class ConfigureAction extends AbstractEditorAction {

	public ConfigureAction() {
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		VariamosGraphEditor editor = getEditor(evt);
		editor.bringUpExtension(mxResources.get("configurationTab"));
		editor.getConfigurator().configure(editor.getEditedModel());
		
		//editor.getConfigurator().performConfiguration();
	}
	
}
