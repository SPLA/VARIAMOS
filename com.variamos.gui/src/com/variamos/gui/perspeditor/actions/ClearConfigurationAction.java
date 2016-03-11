package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.perspsupport.translation.ModelExpr2HLCL;

@SuppressWarnings("serial")
public class ClearConfigurationAction extends AbstractEditorAction {

	public ClearConfigurationAction() {

		this.putValue(SHORT_DESCRIPTION,
				mxResources.get("restartConfiguration"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		editor.clearElementState(ModelExpr2HLCL.DESIGN_EXEC);
	}
}