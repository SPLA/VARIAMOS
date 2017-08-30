package com.variamos.gui.core.io;


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
public class MxGraphReader {

	

	/**
	 * Read an mxGraph file
	 * @param file
	 *            File to load
	 * @param mxGraph
	 *            Destination Graph Load file on existing LineGraph
	 */
	public static void loadMxGraph(File file, mxGraph mxGraph, JComponent jframe) {
		try {
			Document document = mxXmlUtils.parseXml(readFile(
					file.getAbsolutePath(), jframe));
			mxCodec codec = new mxCodec(document);
			codec.decode(document.getDocumentElement(), mxGraph.getModel());
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
