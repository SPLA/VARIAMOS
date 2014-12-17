package com.variamos.pl.configurator.io;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;

import com.mxgraph.io.mxCodec;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;
 
/**
 * @author unknown
 *
 */
public class PLGReader {
	
	
	/**
	 * @param file File to load
	 * Load file on a new ProductLineGraph
	 * jcmunoz: Not Used method Commented - Reference to the ProductLineGraph should not exist. 
	 */
//	public static ProductLineGraph readPLG(File file){
//		
//		ProductLineGraph plGraph = new ProductLineGraph();
//		try {
//			Document document = mxXmlUtils.parseXml(mxUtils.readFile(file.getAbsolutePath()));
//			mxCodec codec = new mxCodec(document);
//			codec.decode(
//					document.getDocumentElement(),
//					plGraph.getModel());
//			return plGraph;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return plGraph;
//	}
	
	/**
	 * @param file File to load
	 * @param plGraph Destination Graph
	 * Load file on existing LineGraph
	 */
	public static void loadPLG(File file, mxGraph plGraph){
		try {
			Document document = mxXmlUtils.parseXml(mxUtils.readFile(file.getAbsolutePath()));
			mxCodec codec = new mxCodec(document);
			codec.decode(
					document.getDocumentElement(),
					plGraph.getModel());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
