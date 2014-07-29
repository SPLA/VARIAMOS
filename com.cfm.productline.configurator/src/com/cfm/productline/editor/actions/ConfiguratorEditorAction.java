package com.cfm.productline.editor.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.cfm.productline.editor.ProductLineEditor;

@SuppressWarnings("serial")
public abstract class ConfiguratorEditorAction extends AbstractAction{
	
	
	public static final ProductLineEditor getEditor(ActionEvent e)
	{
		if (e.getSource() instanceof Component)
		{
			Component component = (Component) e.getSource();

			while (component != null
					&& !(component instanceof ProductLineEditor))
			{
				component = component.getParent();
			}

			return (ProductLineEditor) component;
		}

		return null;
	}
}
