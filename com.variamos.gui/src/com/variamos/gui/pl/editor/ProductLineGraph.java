package com.variamos.gui.pl.editor;

import java.awt.Image;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.canvas.mxGraphicsCanvas2D;
import com.mxgraph.shape.mxStencil;
import com.mxgraph.shape.mxStencilRegistry;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;

public class ProductLineGraph extends mxGraph {

	public static final String PL_EVT_NODE_CHANGE = "plEvtNodeChange";

	public ProductLineGraph() {

	}

	public void loadStencil() {
		try {
			String filename = ProductLineGraph.class.getResource(
					"/com/variamos/gui/perspeditor/style/shapes.xml").getPath();
			Document doc;

			doc = mxXmlUtils.parseXml(mxUtils.readFile(filename));

			Element shapes = doc.getDocumentElement();
			NodeList list = shapes.getElementsByTagName("shape");

			for (int i = 0; i < list.getLength(); i++) {
				Element shape = (Element) list.item(i);
				mxStencilRegistry.addStencil(shape.getAttribute("name"),
						new mxStencil(shape) {
							@Override
							protected mxGraphicsCanvas2D createCanvas(
									final mxGraphics2DCanvas gc) {
								// Redirects image loading to graphics canvas
								return new mxGraphicsCanvas2D(gc.getGraphics()) {
									@Override
									protected Image loadImage(String src) {
										// Adds image base path to relative
										// image URLs
										if (!src.startsWith("/")
												&& !src.startsWith("http://")
												&& !src.startsWith("https://")
												&& !src.startsWith("file:")) {
											src = gc.getImageBasePath() + src;
										}

										// Call is cached
										return gc.loadImage(src);
									}
								};
							}
						});
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}