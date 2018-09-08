package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;
import java.net.URISyntaxException;

import com.mxgraph.util.mxResources;
import com.variamos.gui.core.viewcontrollers.AbstractVariamoGUIAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.panels.CustomizeDialog;

/**
 * A class to configure the assembly process
 * 
 * @author Daniel Correa <yo@danielgara.com>
 * @version 1.1
 * @since 2017-11-21
 */
@SuppressWarnings("serial")
public class CustomizeDerivation extends AbstractVariamoGUIAction {

	public CustomizeDerivation() {}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {

		VariamosGraphEditor editor = getEditor(e);
		try {
			new CustomizeDialog(editor);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}