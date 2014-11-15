package com.variamos.gui.maineditor;

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

		int options = 3; // 0 old way to load product lines

		switch (options) {
		case 0:
			// ProductLine pl = getProductLine("");
			// SXFMReader reader = new SXFMReader();
			// ProductLine pl = reader.readFile("fm.splx");
			ProductLine pl = new ProductLine();
			// pl.printDebug(System.out);
			// System.out.println("Roots : " + pl.getRoots());

			// printProlog(pl);
			ProductLineGraph plGraph = new ProductLineGraph();
			VariamosGraphEditor config = new VariamosGraphEditor(
					"Configurator - VariaMos", new VariamosGraphComponent(
							plGraph),0, pl);
			config.createFrame().setVisible(true);
			break;

		case 1: // load ProductLine perspective directly
			

			// String file = "fm.splx"
			String file=null;
			VariamosGraphEditor config2 = VariamosGraphEditor.loader(
					"Configurator - VariaMos", file, "ProductLine");
			config2.createFrame().setVisible(true);
			break;

		case 2: //load Refas perspective directly
			String file2=null;
			VariamosGraphEditor.loader(
					"Configurator - VariaMos", file2, "modeling");
			//config3.setPerspective(2);
			//config3.updateEditor();
			break;
			
		case 3: //load Refas perspective directly
			String file3=null;
			VariamosGraphEditor.loader(
					"Configurator - VariaMos", file3, "metamodeling");
			//config3.setPerspective(2);
			//config3.updateEditor();
			break;
		}
		
		//ProductLine pl = getProductLine("");
	//	SXFMReader reader = new SXFMReader();
		//ProductLine pl = reader.readFile("fm.splx");
	//	ProductLine pl = new ProductLine();
//		pl.printDebug(System.out);
//		System.out.println("Roots : " + pl.getRoots());
		
//		printProlog(pl);
	
	}
}
