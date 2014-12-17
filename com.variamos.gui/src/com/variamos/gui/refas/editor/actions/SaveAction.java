package com.variamos.gui.refas.editor.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.cfm.common.AbstractModel;
import com.cfm.productline.ProductLine;
import com.cfm.productline.io.SXFMWriter;
import com.mxgraph.canvas.mxICanvas;
import com.mxgraph.canvas.mxSvgCanvas;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.DefaultFileFilter;
import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxIGraphModel;
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
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.pl.editor.ProductLineGraph;

import edu.unal.model.enums.PrologEditorType;
import edu.unal.tranformer.FeatureModelSPLOTransformer;
import fm.FeatureModel;
import fm.FeatureModelException;
import fm.XMLFeatureModel;

@SuppressWarnings("serial")
public class SaveAction extends AbstractEditorAction {

	// @Override
	// public void actionPerformed(ActionEvent evt) {
	// ConfiguratorEditor editor = getEditor(evt);
	// ProductLineGraph graph = (ProductLineGraph) editor.getGraphComponent()
	// .getGraph();
	// ProductLine pl = graph.getProductLine();
	// pl.printDebug(System.out);
	// }

	/**
	 * 
	 */
	protected boolean showDialog;

	/**
	 * 
	 */
	protected String lastDir = null;

	/**
	 * 
	 */
	public SaveAction(boolean showDialog) {
		this.showDialog = showDialog;
	}

	/**
	 * Saves XML+PNG format.
	 */
	protected void saveXmlPng(BasicGraphEditor editor, String filename, Color bg)
			throws IOException {
		mxGraphComponent graphComponent = editor.getGraphComponent();
		mxGraph graph = graphComponent.getGraph();

		// Creates the image for the PNG file
		BufferedImage image = mxCellRenderer.createBufferedImage(graph, null,
				1, bg, graphComponent.isAntiAlias(), null,
				graphComponent.getCanvas());

		// Creates the URL-encoded XML data
		mxCodec codec = new mxCodec();
		String xml = URLEncoder.encode(
				mxXmlUtils.getXml(codec.encode(graph.getModel())), "UTF-8");
		mxPngEncodeParam param = mxPngEncodeParam.getDefaultEncodeParam(image);
		param.setCompressedText(new String[] { "mxGraphModel", xml });

		// Saves as a PNG file
		FileOutputStream outputStream = new FileOutputStream(new File(filename));
		try {
			mxPngImageEncoder encoder = new mxPngImageEncoder(outputStream,
					param);

			if (image != null) {
				encoder.encode(image);

				editor.setModified(false);
				editor.setCurrentFile(new File(filename));
			} else {
				JOptionPane.showMessageDialog(graphComponent,
						mxResources.get("noImageData"));
			}
		} finally {
			outputStream.close();
		}
	}

	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		VariamosGraphEditor editor = getEditor(e);
		VariamosGraphEditor editor2 = getEditor(e);
		AbstractModel pl = null;

		if (editor != null) {
			mxGraphComponent graphComponent = editor.getGraphComponent();
			mxGraph graph = graphComponent.getGraph();
			FileFilter selectedFilter = null;
			// DefaultFileFilter xmlPngFilter = new DefaultFileFilter(".png",
			// "PNG+XML " + mxResources.get("file") + " (.png)");
			// FileFilter vmlFileFilter = new DefaultFileFilter(".html",
			// "VML " + mxResources.get("file") + " (.html)");
			String filename = null;
			boolean dialogShown = false;

			if (showDialog || editor.getCurrentFile() == null) {
				String wd;

				if (lastDir != null) {
					wd = lastDir;
				} else if (editor.getCurrentFile() != null) {
					wd = editor.getCurrentFile().getParent();
				} else {
					wd = System.getProperty("user.dir");
				}

				JFileChooser fc = new JFileChooser(wd);

				// Adds the default file format
				FileFilter defaultFilter = new DefaultFileFilter(".plg",
						mxResources.get("defaultExtension") + " (.plg)");
				fc.addChoosableFileFilter(defaultFilter);

				fc.addChoosableFileFilter(new DefaultFileFilter(".sxfm",
						mxResources.get("sxfmExtension") + " (.sxfm)"));

				fc.addChoosableFileFilter(new DefaultFileFilter(".pl",
						mxResources.get("prologExtension") + " (.pl)"));

				// Adds special vector graphics formats and HTML
				fc.addChoosableFileFilter(new DefaultFileFilter(".svg", "SVG "
						+ mxResources.get("file") + " (.svg)"));

				// Adds a filter for each supported image format
				Object[] imageFormats = ImageIO.getReaderFormatNames();

				// Finds all distinct extensions
				HashSet<String> formats = new HashSet<String>();

				for (int i = 0; i < imageFormats.length; i++) {
					String ext = imageFormats[i].toString().toLowerCase();
					formats.add(ext);
				}

				imageFormats = formats.toArray();

				for (int i = 0; i < imageFormats.length; i++) {
					String ext = imageFormats[i].toString();
					fc.addChoosableFileFilter(new DefaultFileFilter("." + ext,
							ext.toUpperCase() + " " + mxResources.get("file")
									+ " (." + ext + ")"));
				}

				// Adds filter that accepts all supported image formats
				fc.addChoosableFileFilter(new DefaultFileFilter.ImageFileFilter(
						mxResources.get("allImages")));
				fc.setFileFilter(defaultFilter);
				int rc = fc.showDialog(null, mxResources.get("save"));
				dialogShown = true;

				if (rc != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					lastDir = fc.getSelectedFile().getParent();
				}

				filename = fc.getSelectedFile().getAbsolutePath();
				selectedFilter = fc.getFileFilter();

				if (selectedFilter instanceof DefaultFileFilter) {
					String ext = ((DefaultFileFilter) selectedFilter)
							.getExtension();

					if (!filename.toLowerCase().endsWith(ext)) {
						filename += ext;
					}
				}

				if (new File(filename).exists()
						&& JOptionPane.showConfirmDialog(graphComponent,
								mxResources.get("overwriteExistingFile")) != JOptionPane.YES_OPTION) {
					return;
				}
			} else {
				filename = editor.getCurrentFile().getAbsolutePath();
			}

			try {
				String ext = filename.substring(filename.lastIndexOf('.') + 1);

				if (ext.equalsIgnoreCase("svg")) {
					mxSvgCanvas canvas = (mxSvgCanvas) mxCellRenderer
							.drawCells(graph, null, 1, null,
									new CanvasFactory() {
										public mxICanvas createCanvas(
												int width, int height) {
											mxSvgCanvas canvas = new mxSvgCanvas(
													mxDomUtils
															.createSvgDocument(
																	width,
																	height));
											canvas.setEmbedded(true);

											return canvas;
										}

									});

					mxUtils.writeFile(mxXmlUtils.getXml(canvas.getDocument()),
							filename);
				} else if (ext.equalsIgnoreCase("sxfm")) {
					SXFMWriter writer = new SXFMWriter();
					ProductLineGraph plGraph = (ProductLineGraph) graph;
					mxUtils.writeFile(
							writer.getSXFMContent(plGraph.getProductLine()),
							filename);
				} else if (ext.equalsIgnoreCase("pl")) {
					pl = editor2.getEditedModel();
					// pl.printDebug(System.out);
					// ProductLineGraph plGraph = (ProductLineGraph)graph;
					// generatePrologFile(plGraph.getProductLine(), filename);
					generatePrologFile(pl, filename);
				} else if (ext.equalsIgnoreCase("plg")
						|| ext.equalsIgnoreCase("xml")) {
					SharedActions.beforeSaveGraph(graph);
					mxCodec codec = new mxCodec();
					String xml = mxXmlUtils.getXml(codec.encode(graph
							.getModel()));
					SharedActions.afterSaveGraph(graph, editor);
					mxUtils.writeFile(xml, filename);

					editor.setModified(false);
					editor.setCurrentFile(new File(filename));
				} else {
					Color bg = null;

					if ((!ext.equalsIgnoreCase("gif") && !ext
							.equalsIgnoreCase("png"))
							|| JOptionPane.showConfirmDialog(graphComponent,
									mxResources.get("transparentBackground")) != JOptionPane.YES_OPTION) {
						bg = graphComponent.getBackground();
					}

					if ((editor.getCurrentFile() != null
							&& ext.equalsIgnoreCase("png") && !dialogShown)) {
						saveXmlPng(editor, filename, bg);
					} else {
						BufferedImage image = mxCellRenderer
								.createBufferedImage(graph, null, 1, bg,
										graphComponent.isAntiAlias(), null,
										graphComponent.getCanvas());

						if (image != null) {
							ImageIO.write(image, ext, new File(filename));
						} else {
							JOptionPane.showMessageDialog(graphComponent,
									mxResources.get("noImageData"));
						}
					}
				}
			} catch (Throwable ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(graphComponent, ex.toString(),
						mxResources.get("error"), JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void generatePrologFile(AbstractModel pl, String filename)
			throws IOException, FeatureModelException {
		SXFMWriter writer = new SXFMWriter();
		System.out.println(writer.getSXFMContent(pl));

		File f = File.createTempFile("test", "tmp");
		writer.writeSXFM(pl, f);

		FeatureModel featureModel = new XMLFeatureModel(f.getAbsolutePath(),
				XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
		featureModel.loadModel();

		FeatureModelSPLOTransformer transformer = new FeatureModelSPLOTransformer();
		mxUtils.writeFile(transformer.getPrologString(featureModel,
				PrologEditorType.GNU_PROLOG), filename);
	}


}
