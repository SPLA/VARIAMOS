package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.perspeditor.panels.AboutDialog;
import com.variamos.gui.perspeditor.panels.ExternalContextDialog;

/**
 * A class to call the about dialog. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-03-15
 */
@SuppressWarnings("serial")
public class ExternalContextAction extends AbstractEditorAction {

	public ExternalContextAction() {
		this.putValue(SHORT_DESCRIPTION, mxResources.get("externalContext"));
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {

		ExternalContextDialog ecd = new ExternalContextDialog(getEditor(e));
		ecd.center();
	}
}