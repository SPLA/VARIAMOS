package com.cfm.productline.configurator.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.JCheckBoxMenuItem;

import com.cfm.productline.editor.actions.ConfiguratorEditorAction;

@SuppressWarnings("serial")
public class ChangeOperationModeAction extends ConfiguratorEditorAction {
	private JCheckBoxMenuItem mode;
	public ChangeOperationModeAction(JCheckBoxMenuItem mode) {
//		super(configurator);
		this.mode = mode;
//		setLabel("Mode Automatic");
		mode.setSelected(false);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
//		boolean auto = !mode.isSelected();
//		if( auto ){
//			configurator.setOperationMode(OperationMode.MANUAL);
//		}else
//			configurator.setOperationMode(OperationMode.AUTOMATIC);
	}
	
}
