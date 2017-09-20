package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

//import com.cfm.common.AbstractModel;
//import com.cfm.productline.io.SXFMReader;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.core.mxgraph.editor.BasicGraphEditor;
import com.variamos.gui.core.mxgraph.editor.DefaultFileFilter;
import com.variamos.gui.core.viewcontrollers.AbstractVariamoGUIAction;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class OpenAction extends AbstractVariamoGUIAction {
	/**
	 * 
	 */
	protected String lastDir;

	/**
	 * 
	 */
	protected void resetEditor(VariamosGraphEditor editor) {
		editor.setVisibleModel(0, -1);
		editor.setDefaultButton();
		editor.updateView();
		editor.setModified(false);
		editor.getUndoManager().clear();
		editor.getGraphComponent().zoomAndCenter();
	}

	
	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		BasicGraphEditor editor = getEditor(e);

		if (editor != null) {
			VariamosGraphEditor variamosEditor = (VariamosGraphEditor) editor;
			if (variamosEditor.getPerspective() == 4) {
				JOptionPane.showMessageDialog(editor,
						mxResources.get("saveloadnewerror"),
						"Operation not supported",
						JOptionPane.INFORMATION_MESSAGE, null);

				return;
			}
			final VariamosGraphEditor finalEditor = editor;
			((MainFrame) editor.getFrame()).waitingCursor(true);
			if (!editor.isModified()
					|| JOptionPane.showConfirmDialog(editor,
							mxResources.get("loseChanges")) == JOptionPane.YES_OPTION) {
				mxGraph graph = editor.getGraphComponent().getGraph();

				if (graph != null) {
					String wd = (lastDir != null) ? lastDir : System
							.getProperty("user.dir");

					JFileChooser fc = new JFileChooser(wd);

					final String fileExtension = finalEditor.getFileExtension();
					final String fileExtensionName = finalEditor
							.getExtensionName();

					// Adds file filter for supported file format
					DefaultFileFilter defaultFilter = new DefaultFileFilter("."
							+ fileExtension, fileExtensionName + " (."
							+ fileExtension + ")") {

						public boolean accept(File file) {
							String lcase = file.getName().toLowerCase();

							((MainFrame) finalEditor.getFrame())
									.waitingCursor(false);
							return super.accept(file)
									|| lcase.endsWith("." + fileExtension);
						}
					};
					// fc.addChoosableFileFilter(defaultFilter);

					// fc.addChoosableFileFilter(new DefaultFileFilter(".sxfm",
					// mxResources.get("sxfmExtension") + " (.sxfm)"));

					fc.setFileFilter(defaultFilter);

					int rc = fc.showDialog(null, mxResources.get("openFile"));

					if (rc == JFileChooser.APPROVE_OPTION) {
						lastDir = fc.getSelectedFile().getParent();

						if (fc.getSelectedFile().getAbsolutePath()
								.toLowerCase().endsWith(".sxfm")) {
							//openSXFM(editor, fc.getSelectedFile());
						}
						// else if (fc.getSelectedFile().getAbsolutePath()
						// .toLowerCase().endsWith(".txt"))
						// {
						// openGD(editor, fc.getSelectedFile(),
						// mxUtils.readFile(fc
						// .getSelectedFile()
						// .getAbsolutePath()));
						// }
						else {
							

							FileTasks.openAction(FileTasks.OPEN,
									fc.getSelectedFile(),
									(VariamosGraphEditor) editor, graph);

							
						}
					}
				}
			}
			variamosEditor.refresh();
			((MainFrame) editor.getFrame()).waitingCursor(false);
		}
	}
}
