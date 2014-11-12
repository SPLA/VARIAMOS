package com.variamos.gui.refas.editor;

import java.awt.Component;
import java.awt.Container;
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
		int modelInd = editor.getModel();
		if (editor != null) {
			JButton jb = (JButton) e.getSource();
			if (modelInd != 0
					&& jb.getText().equals(mxResources.get("goalModelButton"))) {
				System.out.println(mxResources.get("goalModelButton"));
				editor.setVisibleModel(0);
			}
			if (modelInd != 1
					&& jb.getText().equals(
							mxResources.get("softgoalModelButton"))) {
				System.out.println(mxResources.get("softgoalModelButton"));
				editor.setVisibleModel(1);
			}
			if (modelInd != 2
					&& jb.getText().equals(
							mxResources.get("contextModelButton"))) {

				editor.setGraphEditorFunctions(new RefasGraphEditorFunctions(
						editor));
				System.out.println(mxResources.get("contextModelButton"));
				editor.setVisibleModel(2);
			}
			if (modelInd != 3
					&& jb.getText().equals(
							mxResources.get("sgsatisModelButton"))) {

				editor.setGraphEditorFunctions(new RefasGraphEditorFunctions(
						editor));
				System.out.println(mxResources.get("sgsatisModelButton"));
				editor.setVisibleModel(3);
			}
			if (modelInd != 4
					&& jb.getText().equals(mxResources.get("assetModelButton"))) {

				editor.setGraphEditorFunctions(new RefasGraphEditorFunctions(
						editor));
				System.out.println(mxResources.get("assetModelButton"));
				editor.setVisibleModel(4);
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