package com.variamos.gui.refas.editor;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.refas.editor.RefasGraphEditorFunctions;

@SuppressWarnings("serial")
public class ModelButtonAction extends AbstractEditorAction {

	public ModelButtonAction() {
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {

		VariamosGraphEditor editor = getEditor(e);
		int modelInd = editor.getModelViewIndex();
		if (editor != null) {
			JButton jb = (JButton) e.getSource();
			for (int i = 0; i< Integer.parseInt(mxResources.get("modelViews")); i++)
			{
				if (modelInd != i && jb.getText().equals(mxResources.get("modelViewButton"+i)))
				{
					System.out.println(mxResources.get("modelViewButton"+i));
					editor.setVisibleModel(i);
					editor.updateView();
				}
			}
			updateButtons((JPanel) jb.getParent(), jb);

		}
	}

	public void updateButtons(JPanel panel, JButton jb)
	{
		Component [] buttons = panel.getComponents();
		for (int i =0; i<buttons.length; i++)
		{
			JButton button = (JButton)buttons[i];
			if (button.equals(jb))

				button.setSelected(true);
			else
				button.setSelected(false);
		}
	}
}