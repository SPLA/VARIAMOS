package com.variamos.gui.perspeditor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.variamos.dynsup.instance.InstElement;
import com.variamos.gui.core.viewcontrollers.AbstractVariamoGUIAction;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;

@SuppressWarnings("serial")
public class ModelButtonAction extends AbstractVariamoGUIAction {

	public ModelButtonAction() {
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {

		VariamosGraphEditor editor = getEditor(e);
		if (editor != null) {
			((MainFrame)editor.getFrame()).waitingCursor(true);

			List<InstElement> metaViews = editor.getInstViews();
			JButton jb = (JButton) e.getSource();
			for (int i = 0; i< metaViews.size(); i++)
			{
				/*List<InstView> childViews =metaViews.get(i).getChildViews();
				if (childViews.size()>0)
				{
				for (int j = 0; j< childViews.size(); j++)
				{
					if (jb.getText().equals(childViews.get(j).getEditableMetaElement().getName())) //TODO test
					{
						editor.setVisibleModel(i,j);
						editor.updateView();
						System.out.println("view" + i +" "+j);
					}
				}
				}*/
			}
			updateButtons((JPanel) jb.getParent(), jb);
			((MainFrame)editor.getFrame()).waitingCursor(false);
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