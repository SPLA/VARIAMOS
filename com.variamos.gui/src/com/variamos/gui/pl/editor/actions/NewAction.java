package com.variamos.gui.pl.editor.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class NewAction extends AbstractEditorAction
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
					((VariamosGraphEditor)editor).resetView();
					

				}
			}
		}
	}
