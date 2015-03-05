package com.variamos.gui.perspeditor.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class NewAction extends AbstractEditorAction
	{
	public NewAction()
	{
		super();

		this.putValue(SHORT_DESCRIPTION, "N");
	}
		/**
		 * 
		 */
		public void actionPerformed(ActionEvent e)
		{
			VariamosGraphEditor editor = getEditor(e);

			if (editor != null)
			{
				if (editor.getPerspective()==4)
				{
					JOptionPane.showMessageDialog(editor, mxResources.get("saveloadnewerror"),
							"Operation not supported", JOptionPane.INFORMATION_MESSAGE,
							null);
					
					return;
				}
				if (!editor.isModified()
						|| JOptionPane.showConfirmDialog(editor,
								mxResources.get("loseChanges")) == JOptionPane.YES_OPTION)
				{
					((VariamosGraphEditor)editor).resetView();
					System.runFinalization();

				}
			}
		}
	}
