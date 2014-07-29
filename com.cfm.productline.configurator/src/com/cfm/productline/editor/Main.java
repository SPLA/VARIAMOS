package com.cfm.productline.editor;

import java.io.IOException;

import javax.swing.UIManager;

import com.cfm.productline.ProductLine;
import com.cfm.productline.io.SXFMReader;

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
		SXFMReader reader = new SXFMReader();
		//ProductLine pl = reader.readFile("fm.splx");
		ProductLine pl = new ProductLine();
//		pl.printDebug(System.out);
//		System.out.println("Roots : " + pl.getRoots());
		
//		printProlog(pl);
		
		
		ProductLineGraph plGraph = new ProductLineGraph();
		ProductLineEditor config = new ProductLineEditor("Configurator", new ProductLineGraphComponent(plGraph));
		config.editProductLine(pl);
		config.createFrame(new ProductLineMenuBar(config)).setVisible(true);
	}
}
