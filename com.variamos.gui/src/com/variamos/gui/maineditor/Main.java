package com.variamos.gui.maineditor;

import java.io.IOException;

import javax.swing.UIManager;

import com.cfm.productline.ProductLine;
//import com.cfm.productline.io.SXFMReader;

import com.variamos.gui.pl.editor.ProductLineGraph;
import com.variamos.gui.pl.editor.ProductLineGraphEditor;
import com.variamos.gui.pl.editor.ProductLineMenuBar;

import fm.FeatureModelException;

public class Main {
	
	public static void main(String[] ar) throws FeatureModelException, IOException{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		//ProductLine pl = getProductLine("");
	//	SXFMReader reader = new SXFMReader();
		//ProductLine pl = reader.readFile("fm.splx");
		ProductLine pl = new ProductLine();
//		pl.printDebug(System.out);
//		System.out.println("Roots : " + pl.getRoots());
		
//		printProlog(pl);
		
		
		ProductLineGraph plGraph = new ProductLineGraph();
		ProductLineGraphEditor config = new ProductLineGraphEditor("Configurator - VariaMos", new VariamosGraphComponent(plGraph));
		config.editProductLine(pl);
		config.createFrame(new ProductLineMenuBar(config)).setVisible(true);
	}
}
