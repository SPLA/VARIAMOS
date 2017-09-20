package com.variamos.gui.perspeditor.actions;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxSvgCanvas;
import com.mxgraph.io.mxCodec;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxCellRenderer.CanvasFactory;
import com.mxgraph.util.mxDomUtils;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.util.png.mxPngEncodeParam;
import com.mxgraph.util.png.mxPngImageEncoder;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;

public class FileHandlingAPI {

	/**
	 * Saves an image in format svg
	 * @param variamosEditor: perspective used to save the file
	 * @param graph: graph to be saved as an image
	 * @param filename}
	 * @author mxgraph example
	 * return true images saved correctly, false image can not be saved
	 * @throws IOException
	 */
	public boolean savesvg(final VariamosGraphEditor variamosEditor, mxGraph graph, String filename)
			throws IOException {
		mxSvgCanvas canvas = (mxSvgCanvas) mxCellRenderer.drawCells(graph, null, 1, null, new CanvasFactory() {
			@Override
			public mxICanvas createCanvas(int width, int height) {
				mxSvgCanvas canvas = new mxSvgCanvas(mxDomUtils.createSvgDocument(width, height));
				canvas.setEmbedded(true);
				return canvas;			
			}

		});

		mxUtils.writeFile(mxXmlUtils.getXml(canvas.getDocument()), filename);
		return true;
	}
	

	/**
	 * Saves XML+PNG format.
	 * 
	 * @param editor:
	 *            Variamos main editor window
	 * @param filename
	 * @param bg:
	 *            backaground color
	 * @throws IOException
	 * @author mxgraph example
	 * @return true images saved correctly, false image can not be saved
	 * @see https://github.com/jgraph/jgraphx/tree/master/examples/com/mxgraph/examples/swing
	 */
	public boolean saveXmlPng(final VariamosGraphEditor editor, String filename, Color bg) throws IOException {
		((MainFrame) editor.getFrame()).waitingCursor(true);
		mxGraphComponent graphComponent = editor.getGraphComponent();
		mxGraph graph = graphComponent.getGraph();
		
		//For avoiding null pointer exceptions when saving the image a background color is defined
		if (bg==null) {
			bg = graphComponent.getBackground();
		}
			
		// Creates the image for the PNG file
		BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 2, bg, graphComponent.isAntiAlias(), null,
				graphComponent.getCanvas());

		// Creates the URL-encoded XML data
		mxCodec codec = new mxCodec();
		String xml = URLEncoder.encode(mxXmlUtils.getXml(codec.encode(graph.getModel())), "UTF-8");
		mxPngEncodeParam param = mxPngEncodeParam.getDefaultEncodeParam(image);
		param.setCompressedText(new String[] { "mxGraphModel", xml });

		// Saves as a PNG file
		FileOutputStream outputStream = new FileOutputStream(new File(filename));
		try {
			mxPngImageEncoder encoder = new mxPngImageEncoder(outputStream, param);

			if (image != null) {
				encoder.encode(image);

				editor.setModified(false);
				editor.setCurrentFile(new File(filename));
				((MainFrame) editor.getFrame()).waitingCursor(false);
				 return true;
			} else {
				return false;
			}
		} finally {
			outputStream.close();
		}
	}
	
	/**
	 * @param variamosEditor
	 * @param filename
	 * @param backgroundColor
	 * @param extension: expected extensions are .gif, .bmp, .wbmp, jpeg
	 * @return true images saved correctly, false image can not be saved
	 * @throws IOException
	 */
	public boolean saveImages(VariamosGraphEditor variamosEditor, String filename, Color backgroundColor, String extension) throws IOException {
		mxGraphComponent graphComponent = variamosEditor.getGraphComponent();
		mxGraph graph = graphComponent.getGraph();
		BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 2, backgroundColor,
				graphComponent.isAntiAlias(), null, graphComponent.getCanvas());
		if (image != null) {
			ImageIO.write(image, extension, new File(filename));
			return true;
		} else {
			return false;
		}
	}
	
}
