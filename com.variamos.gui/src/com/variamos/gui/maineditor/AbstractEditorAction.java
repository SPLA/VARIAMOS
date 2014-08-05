package com.variamos.gui.maineditor;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.variamos.gui.pl.editor.ProductLineGraphEditor;

@SuppressWarnings("serial")
public abstract class AbstractEditorAction extends AbstractAction{
	
	
	public static final ProductLineGraphEditor getEditor(ActionEvent e)
	{
		if (e.getSource() instanceof Component)
		{
			Component component = (Component) e.getSource();

			while (component != null
					&& !(component instanceof ProductLineGraphEditor))
			{
				component = component.getParent();
			}

			return (ProductLineGraphEditor) component;
		}

		return null;
	}
}
