package com.variamos.gui.perspeditor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.perspsupport.instancesupport.InstView;
import com.variamos.perspsupport.syntaxsupport.MetaView;

@SuppressWarnings("serial")
public class ModelButtonAction extends AbstractEditorAction {

	public ModelButtonAction() {
	}

	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {

		VariamosGraphEditor editor = getEditor(e);
		if (editor != null) {
			((MainFrame)editor.getFrame()).waitingCursor(true);

			List<InstView> metaViews = editor.getInstViews();
			JButton jb = (JButton) e.getSource();
			for (int i = 0; i< metaViews.size(); i++)
			{
				List<InstView> childViews =metaViews.get(i).getChildViews();
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
				}
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