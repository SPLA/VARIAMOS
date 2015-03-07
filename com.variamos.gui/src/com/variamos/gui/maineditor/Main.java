package com.variamos.gui.maineditor;

import java.awt.Color;
import java.io.IOException;

import javax.swing.UIManager;

import com.cfm.productline.ProductLine;
//import com.cfm.productline.io.SXFMReader;
import com.variamos.gui.pl.editor.ProductLineGraph;

import fm.FeatureModelException;

public class Main {

	public static void main(String[] ar) throws FeatureModelException,
			IOException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		int options = 4; // 0 old way to load product lines

		new MainFrame();
	}
}
