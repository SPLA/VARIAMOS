package com.variamos.gui.pl.configurator.guiactions;

import java.awt.event.ActionEvent;

import javax.swing.JCheckBoxMenuItem;

import com.variamos.gui.maineditor.AbstractEditorAction;

/**
 * @author unkown
 * jcmunoz: Unused class
 *
 */
@SuppressWarnings("serial")
public class ChangeOperationModeAction extends AbstractEditorAction {
//	private JCheckBoxMenuItem mode;
	public ChangeOperationModeAction(JCheckBoxMenuItem mode) {
//		super(configurator);
//		this.mode = mode;
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