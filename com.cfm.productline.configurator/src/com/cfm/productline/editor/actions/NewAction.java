package com.cfm.productline.editor.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.cfm.productline.editor.ProductLineEditor;
import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;

@SuppressWarnings("serial")
public class NewAction extends ConfiguratorEditorAction
	{
		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			BasicGraphEditor editor = getEditor(e);

			if (editor != null)
			{
				if (!editor.isModified()
						|| JOptionPane.showConfirmDialog(editor,
								mxResources.get("loseChanges")) == JOptionPane.YES_OPTION)
				{
					((ProductLineEditor)editor).editProductLineReset();
					mxGraph graph = editor.getGraphComponent().getGraph();

					// Check modified flag and display save dialog
					mxCell root = new mxCell();
					root.insert(new mxCell());
					graph.getModel().setRoot(root);

					editor.setModified(false);
					editor.setCurrentFile(null);
					editor.getGraphComponent().zoomAndCenter();
				}
			}
		}
	}