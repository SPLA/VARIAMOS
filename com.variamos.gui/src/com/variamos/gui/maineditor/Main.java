package com.variamos.gui.maineditor;

import java.io.IOException;
import javax.swing.UIManager;
import fm.FeatureModelException;

public class Main {

	public static void main(String[] ar) throws FeatureModelException,
			IOException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (ar.length > 0)
			new MainFrame(ar[0]);
		else
			new MainFrame(null);
	}
}
