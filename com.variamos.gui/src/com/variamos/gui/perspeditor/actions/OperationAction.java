package com.variamos.gui.perspeditor.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

import com.mxgraph.util.mxResources;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.types.OperationActionType;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.DefaultFileFilter;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphComponent;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class OperationAction extends AbstractEditorAction {

	protected String lastDir = null;

	public OperationAction() {
	}

	/**
		 * 
		 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Component editor = getComponentEditor(e);
		VariamosGraphEditor vg = null;
		int perspective = ((MainFrame) editor).getPerspective();
		if (editor instanceof VariamosGraphEditor)
			vg = (VariamosGraphEditor) editor;
		if (editor instanceof MainFrame) {

			vg = ((MainFrame) editor).getEditor(perspective);
		}
		String operation = ((JMenuItem) e.getSource()).getName();
		List<String> operations = new ArrayList<String>();

		// FIXME review why this is needed for verification operations
		if (!operation.startsWith("N:"))
			vg.updateObjects();

		if (operation.startsWith("exec-all-ver-")) {
			// FIXME get all operations to execute
			// for ()
			// operations.add (oper);
		} else
			operations.add(operation);

		if (perspective == 2)
			((VariamosGraphComponent) vg.getGraphComponent())
					.setSimulationStarted(false);
		else
			((VariamosGraphComponent) vg.getGraphComponent())
					.setSimulationStarted(true);

		String filename = null;
		InstElement operationObj = vg.getRefas2hlcl().getRefas()
				.getSyntaxModel().getOperationalModel().getElement(operation);
		if (operationObj != null
				&& operationObj.getInstAttributeValue("operType").equals(
						OperationActionType.Export.toString())) {

			// boolean dialogShown = false;

			// if (vg.getCurrentFile() == null) {
			String wd;

			if (lastDir != null) {
				wd = lastDir;
			} else if (vg.getCurrentFile() != null) {
				wd = vg.getCurrentFile().getParent();
			} else {
				wd = System.getProperty("user.dir");
			}

			JFileChooser fc = new JFileChooser(wd);

			// Adds the default file format
			FileFilter defaultFilter = new DefaultFileFilter(".xls",
					"Excel File Format (.xls)");

			fc.setFileFilter(defaultFilter);
			int rc = fc.showDialog(null, mxResources.get("save"));
			// dialogShown = true;

			if (rc != JFileChooser.APPROVE_OPTION) {
				return;
			} else {
				lastDir = fc.getSelectedFile().getParent();
			}

			filename = fc.getSelectedFile().getAbsolutePath();

			if (!filename.endsWith(".xls"))
				filename += ".xls";
			// }
		}
		vg.callOperations(operations, filename);

		if (operation.startsWith("N:")) {
			vg.editPropertiesRefas();
			vg.updateDashBoard(true, false, true);
		}

		vg.updateSimulResults();
	}
}
