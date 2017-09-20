package com.variamos.gui.api;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxSvgCanvas;
import com.mxgraph.io.mxCodec;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxCellRenderer.CanvasFactory;
import com.mxgraph.util.mxDomUtils;
import com.mxgraph.util.mxUtils;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.util.png.mxPngEncodeParam;
import com.mxgraph.util.png.mxPngImageEncoder;
import com.mxgraph.view.mxGraph;

/**
 * API that exposes methods for loading and saving files. This API is used by
 * the GUI for loading and saving files
 * 
 * @author Luisa Rincon <lufe089@gmail.com>
 *
 */
public class FileHandlingAPI {

	/**
	 * 
	 * @param xmlInfo
	 *            string to save inside an xml file
	 * @param filename
	 * @param ext
	 * @return true if none exception raise
	 * @throws IOException
	 */
	public boolean savesxmlFile(String xmlInfo, String filename, String ext) throws IOException {

		// Saves the info into a new xmlfile
		mxUtils.writeFile(xmlInfo, filename);

		// Creates a backup with the current date for the file to save
		String file = filename.substring(0, filename.lastIndexOf('.'));
		file += ".backup." + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "." + ext;
		mxUtils.writeFile(xmlInfo, file);
		return true;
	}

	/**
	 * Saves an image in format svg
	 * 
	 * @param graph:
	 *            graph to be saved as an image
	 * @param filename}
	 * @author mxgraph example return true images saved correctly, false image can
	 *         not be saved
	 * @throws IOException
	 */
	public boolean savesvg(mxGraph graph, String filename) throws IOException {
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
	public boolean saveXmlPng(mxGraphComponent graphComponent, String filename, Color bgColor) throws IOException {

		mxGraph graph = graphComponent.getGraph();

		// Creates the image for the PNG file
		BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 2, bgColor, graphComponent.isAntiAlias(),
				null, graphComponent.getCanvas());

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
	 * @param bgColor
	 * @param extension:
	 *            expected extensions are .gif, .bmp, .wbmp, jpeg
	 * @return true images saved correctly, false image can not be saved
	 * @throws IOException
	 */
	public boolean saveImages(mxGraphComponent graphComponent, String filename, Color bgColor, String extension)
			throws IOException {
		mxGraph graph = graphComponent.getGraph();
		BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 2, bgColor, graphComponent.isAntiAlias(),
				null, graphComponent.getCanvas());
		if (image != null) {
			ImageIO.write(image, extension, new File(filename));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param fileContent
	 * @param variamosPerspective
	 *            in which the file is open. This determines the extension by
	 *            default that will have the file
	 * @param file
	 *            to read
	 * @param versionHead
	 *            line that specifies in which version the model was made
	 * @param currentVersion
	 *            current variamosVersion
	 * @throws FileNotFoundException
	 */

	public void migrateModelVersion(StringBuilder fileContent, int variamosPerspective, File file, String versionHead,
			String currentVersion) throws FileNotFoundException {

		// TODO: to convert an old version model to a new version (file
		// before
		// loading), validate modelVersion and currentVersion to evaluate
		// the
		// correct: modelVersion.equals("1.0.1.19") &&
		// currentVersion.equals("1.0.1.20")

		// convert syntax mm from v19 to v20
		if (versionHead.startsWith("1.0.1.19") && currentVersion.equals("1.0.1.20")
				&& (variamosPerspective == 1 || variamosPerspective == 3)) {

			// Include your edition of the text file here, update the file
			// object with the new file
			String parts[] = fileContent.toString().split("com.variamos.hlcl.");
			StringBuilder out = new StringBuilder();
			for (String part : parts) {
				if (parts[parts.length - 1].equals(part))
					out.append(part);
				else
					out.append(part + "com.variamos.hlcl.model.domains.");
			}

			if (variamosPerspective == 3) {
				String partsImg[] = out.toString().split("/com/variamos/gui/pl/editor/images/");
				out = new StringBuilder();
				for (String part : partsImg) {
					if (partsImg[partsImg.length - 1].equals(part))
						out.append(part);
					else
						out.append(part + "/com/variamos/gui/perspeditor/images/");
				}
			}

			PrintWriter dfile;
			dfile = new PrintWriter(file.getAbsolutePath() + "ConvertedToV20.vmsm");
			dfile.print(out);
			dfile.close();

			file = new File(file.getAbsolutePath() + "ConvertedToV20.vmsm");

		}
	}

}
