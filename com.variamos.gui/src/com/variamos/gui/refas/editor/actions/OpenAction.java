package com.variamos.gui.refas.editor.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.cfm.common.AbstractModel;
import com.cfm.productline.ProductLine;
import com.cfm.productline.io.SXFMReader;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.DefaultFileFilter;
import com.variamos.gui.maineditor.MainFrame;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.pl.configurator.io.PLGReader;

import fm.FeatureModelException;

@SuppressWarnings("serial")
public class OpenAction extends AbstractEditorAction{
	/**
	 * 
	 */
	protected String lastDir;

	/**
	 * 
	 */
	protected void resetEditor(VariamosGraphEditor editor)
	{
		editor.setVisibleModel(0,-1);
		editor.setDefaultButton();
		editor.updateView();
		editor.setModified(false);
		editor.getUndoManager().clear();
		editor.getGraphComponent().zoomAndCenter();
	}
	
	protected void openSXFM(BasicGraphEditor editor, File file) throws IOException, FeatureModelException{
		
		VariamosGraphEditor variamosEditor = (VariamosGraphEditor)editor;
		variamosEditor.editModelReset();
		
		SXFMReader reader = new SXFMReader();
		AbstractModel pl = reader.readFile(file.getAbsolutePath());
		
		variamosEditor.editModel(pl);
		
		editor.setCurrentFile(file);
		resetEditor(variamosEditor);
	}
	
	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e)
	{
		BasicGraphEditor editor = getEditor(e);

		if (editor != null)
		{
			final BasicGraphEditor finalEditor = editor;
			((MainFrame)editor.getFrame()).waitingCursor(true);
			if (!editor.isModified()
					|| JOptionPane.showConfirmDialog(editor,
							mxResources.get("loseChanges")) == JOptionPane.YES_OPTION)
			{
				mxGraph graph = editor.getGraphComponent().getGraph();

				if (graph != null)
				{
					String wd = (lastDir != null) ? lastDir : System
							.getProperty("user.dir");

					JFileChooser fc = new JFileChooser(wd);

					// Adds file filter for supported file format
					DefaultFileFilter defaultFilter = new DefaultFileFilter(
							".plg", mxResources.get("defaultExtension")
									+ " (.plg)")
					{

						public boolean accept(File file)
						{
							String lcase = file.getName().toLowerCase();

							((MainFrame) finalEditor.getFrame()).waitingCursor(false);
							return lcase.endsWith(".plg")
									|| lcase.endsWith(".sxfm");
						}
					};
					//fc.addChoosableFileFilter(defaultFilter);

					fc.addChoosableFileFilter(new DefaultFileFilter(".sxfm",
							mxResources.get("sxfmExtension")
									+ " (.sxfm)"));

					fc.setFileFilter(defaultFilter);

					int rc = fc.showDialog(null,
							mxResources.get("openFile"));

					if (rc == JFileChooser.APPROVE_OPTION)
					{
						lastDir = fc.getSelectedFile().getParent();

						try
						{
							if (fc.getSelectedFile().getAbsolutePath()
									.toLowerCase().endsWith(".sxfm"))
							{
								openSXFM(editor, fc.getSelectedFile());
							}
//							else if (fc.getSelectedFile().getAbsolutePath()
//									.toLowerCase().endsWith(".txt"))
//							{
//								openGD(editor, fc.getSelectedFile(),
//										mxUtils.readFile(fc
//												.getSelectedFile()
//												.getAbsolutePath()));
//							}
							else
							{
//								Document document = mxXmlUtils
//										.parseXml(mxUtils.readFile(fc
//												.getSelectedFile()
//												.getAbsolutePath()));
//
//								mxCodec codec = new mxCodec(document);
//								codec.decode(
//										document.getDocumentElement(),
//										graph.getModel());
								VariamosGraphEditor variamosEditor = (VariamosGraphEditor)editor;
								//variamosEditor.editModelReset();

								SharedActions.beforeLoadGraph(graph, variamosEditor);
								
								PLGReader.loadPLG(fc.getSelectedFile(), graph);
								editor.setCurrentFile(fc
										.getSelectedFile());
								SharedActions.afterOpenCloneGraph(graph, variamosEditor);
									variamosEditor.populateIndex(((AbstractGraph)graph).getProductLine());
								resetEditor(variamosEditor);
							}
						}
						catch (IOException | FeatureModelException ex )
						{
							ex.printStackTrace();
							JOptionPane.showMessageDialog(
									editor.getGraphComponent(),
									ex.toString(),
									mxResources.get("error"),
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
			((MainFrame)editor.getFrame()).waitingCursor(false);
		}
	}

}
