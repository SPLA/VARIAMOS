package com.variamos.gui.maineditor;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public abstract class AbstractEditorAction extends AbstractAction{
	
	
	public static final VariamosGraphEditor getEditor(ActionEvent e)
	{
		if (e.getSource() instanceof Component)
		{
			Component component = (Component) e.getSource();

			while (component != null
					&& !(component instanceof VariamosGraphEditor))
			{
				component = component.getParent();
			}

			return (VariamosGraphEditor) component;
		}

		return null;
	}
}
