package com.variamos.gui.refas.editor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.mxgraph.util.mxResources;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.refas.editor.RefasGraphEditorFunctions;
import com.variamos.syntaxsupport.metamodelsupport.MetaView;

@SuppressWarnings("serial")
public class ModelButtonAction extends AbstractEditorAction {

	public ModelButtonAction() {
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {

		VariamosGraphEditor editor = getEditor(e);
		List<MetaView> metaViews = editor.getMetaViews();
		if (editor != null) {
			JButton jb = (JButton) e.getSource();
			for (int i = 0; i< metaViews.size(); i++)
			{
				List<MetaView> childViews =metaViews.get(i).getChildViews();
				if (childViews.size()>0)
				{
				for (int j = 0; j< childViews.size(); j++)
				{
					if (jb.getText().equals(childViews.get(j).getName())) //TODO test
					{
						editor.setVisibleModel(i,j);
						editor.updateView();
					}
				}
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