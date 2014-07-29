package com.cfm.productline.editor;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;

import com.mxgraph.io.mxCodec;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;

public class PLGReader {
	
	public static ProductLineGraph readPLG(File file){
		
		ProductLineGraph plGraph = new ProductLineGraph();
		try {
			Document document = mxXmlUtils.parseXml(mxUtils.readFile(file.getAbsolutePath()));
			mxCodec codec = new mxCodec(document);
			codec.decode(
					document.getDocumentElement(),
					plGraph.getModel());
			return plGraph;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return plGraph;
	}
	
	public static void loadPLG(File file, ProductLineGraph plGraph){
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
