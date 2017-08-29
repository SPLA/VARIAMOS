package com.variamos.configurator.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.ProgressMonitorInputStream;

import org.w3c.dom.Document;

import com.mxgraph.io.mxCodec;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;
import com.variamos.io.ConsoleTextArea;

/**
 * @author unknown
 *
 */
@Deprecated
public class PLGReader {

	/**
	 * @param file
	 *            File to load Load file on a new ProductLineGraph jcmunoz: Not
	 *            Used method Commented - Reference to the ProductLineGraph
	 *            should not exist.
	 */
	// public static ProductLineGraph readPLG(File file){
	//
	// ProductLineGraph plGraph = new ProductLineGraph();
	// try {
	// Document document =
	// mxXmlUtils.parseXml(mxUtils.readFile(file.getAbsolutePath()));
	// mxCodec codec = new mxCodec(document);
	// codec.decode(
	// document.getDocumentElement(),
	// plGraph.getModel());
	// return plGraph;
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// return plGraph;
	// }

	/**
	 * @param file
	 *            File to load
	 * @param plGraph
	 *            Destination Graph Load file on existing LineGraph
	 */
	public static void loadPLG(File file, mxGraph plGraph, JComponent jframe) {
		try {
			Document document = mxXmlUtils.parseXml(readFile(
					file.getAbsolutePath(), jframe));
			mxCodec codec = new mxCodec(document);
			codec.decode(document.getDocumentElement(), plGraph.getModel());
		} catch (IOException e) {
			ConsoleTextArea.addText(e.getStackTrace());
		}
	}

	private static String readFile(String filename, JComponent jframe)
			throws IOException {
		if (jframe != null) {
			ProgressMonitorInputStream progressMonitor = new ProgressMonitorInputStream(
					jframe, "Reading " + filename,
					new FileInputStream(filename));
			progressMonitor.getProgressMonitor().setMillisToDecideToPopup(50);
			progressMonitor.getProgressMonitor().setMillisToPopup(50);
			return mxUtils.readInputStream(progressMonitor);
		} else

			return mxUtils.readInputStream(new FileInputStream(filename));
	}
}
