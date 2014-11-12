package com.variamos.gui.maineditor;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.pl.editor.PLGraphEditorFunctions;
import com.variamos.gui.pl.editor.PLPalettesLoader;
import com.variamos.gui.pl.editor.ProductLineGraph;
import com.variamos.gui.refas.editor.RQPalettesLoader;
<<<<<<< HEAD
import com.variamos.gui.refas.editor.RefasGraph;
=======
>>>>>>> branch 'development' of https://github.com/jcmunozf/VARIAMOS
import com.variamos.gui.refas.editor.RefasGraphEditor;
import com.variamos.gui.refas.editor.RefasGraphEditorFunctions;

@SuppressWarnings("serial")
public class PerspectiveAction extends AbstractEditorAction {
	
	private PerspectiveToolBar perspective;

	
	public PerspectiveAction (PerspectiveToolBar perspective)
	{
		this.perspective = perspective;
	}
	/**
		 * 
		 */
	public void actionPerformed(ActionEvent e) {


		VariamosGraphEditor editor = getEditor(e);
		int perspectiveInd = editor.getPerspective();
		if (editor != null) {
			if (!editor.isModified()
					|| JOptionPane.showConfirmDialog(editor,
							mxResources.get("loseChanges")) == JOptionPane.YES_OPTION) {
				mxGraph graph = editor.getGraphComponent().getGraph();
				JButton jb = (JButton) e.getSource();
				if (perspectiveInd != 0
						&& jb.getText().equals(
								mxResources.get("productLineButton"))) {
<<<<<<< HEAD
					editor.setGraphEditorFunctions (new PLGraphEditorFunctions(editor));
					editor.updateEditor();
//					editor.editProductLineReset();
//					editor.clearPalettes();
//					System.out.println("product");
//					editor.loadRegularPalette(editor.insertPalette(mxResources
//							.get("productLinePalette")));
=======
					editor.setGraphEditorFunctions (new PLGraphEditorFunctions());
					editor.editProductLineReset();
					editor.clearPalettes();
					System.out.println("product");
					editor.loadRegularPalette(editor.insertPalette(mxResources
							.get("productLinePalette")));
>>>>>>> branch 'development' of https://github.com/jcmunozf/VARIAMOS
				/*	PLPalettesLoader.loadRegularPalette(editor.insertPalette(mxResources
									.get("productLinePalette")),
							(ProductLineGraph) editor.getGraphComponent().getGraph());
					editor.setPerspective(0);
					*/
				}
				if (perspectiveInd != 1
						&& jb.getText().equals(mxResources.get("defectAnalyzerButton"))) {
					editor.editProductLineReset();
					editor.clearPalettes();
					System.out.println("defect");
					editor.setPerspective(1);
				}
				if (perspectiveInd != 2
						&& jb.getText().equals(mxResources.get("requirementsButton"))) {

<<<<<<< HEAD
					editor.setGraphEditorFunctions (new RefasGraphEditorFunctions(editor));
					editor.updateEditor();
//					editor.editProductLineReset();
//					editor.clearPalettes();
//					System.out.println("requirements");
//					editor.setPerspective(2);
//					editor.loadRegularPalette(editor.insertPalette(mxResources
//							.get("conceptsPalette")));
//					editor.loadRegularPalette(editor.insertPalette(mxResources
//							.get("relationsPalette")));
=======
					editor.setGraphEditorFunctions (new RefasGraphEditorFunctions());
					editor.editProductLineReset();
					editor.clearPalettes();
					System.out.println("requirements");
					editor.setPerspective(2);
					editor.loadRegularPalette(editor.insertPalette(mxResources
							.get("conceptsPalette")));
					editor.loadRegularPalette(editor.insertPalette(mxResources
							.get("relationsPalette")));
>>>>>>> branch 'development' of https://github.com/jcmunozf/VARIAMOS
					/*
					 	RQPalettesLoader.loadConceptsPalette(editor
					 
							.insertPalette(mxResources.get("conceptsPalette")),
							(ProductLineGraph) editor.getGraphComponent().getGraph());
					RQPalettesLoader.loadRelationsPalette(
							editor.insertPalette(mxResources.get("relationsPalette")),
							(ProductLineGraph) editor.getGraphComponent()
									.getGraph());
									*/
				}
				// Check modified flag and display save dialog
				mxCell root = new mxCell();
				root.insert(new mxCell());
				graph.getModel().setRoot(root);
				
				graph.addCell(new mxCell("mv0"));
				graph.addCell(new mxCell("mv1"));
				graph.addCell(new mxCell("mv2"));
				graph.addCell(new mxCell("mv3"));
				graph.addCell(new mxCell("mv4"));
				editor.setModified(false);
				editor.setCurrentFile(null);
				editor.getGraphComponent().zoomAndCenter();
				
			}
		}
		perspective.updateButtons();
	}
}
