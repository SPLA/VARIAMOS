package com.variamos.gui.maineditor;

import java.io.IOException;

import javax.swing.UIManager;

import com.variamos.common.core.exceptions.FunctionalException;
import com.variamos.gui.core.io.ConsoleTextArea;

public class Main {

	public static void main(String[] ar) throws FunctionalException,
			IOException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			ConsoleTextArea.addText(e.getStackTrace());
		}
		if (ar.length > 0)
			new MainFrame(ar);
		else
			new MainFrame(null);
	}
}

