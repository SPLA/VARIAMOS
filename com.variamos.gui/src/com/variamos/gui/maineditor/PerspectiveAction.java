package com.variamos.gui.maineditor;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.mxgraph.examples.swing.editor.BasicGraphEditor;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxResources;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.pl.editor.PLPalettesLoader;
import com.variamos.gui.pl.editor.ProductLineGraph;
import com.variamos.gui.pl.editor.ProductLineGraphEditor;
import com.variamos.gui.rq.editor.RQPalettesLoader;

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


		BasicGraphEditor editor = getEditor(e);
		int perspectiveInd = editor.getPerspective();
		if (editor != null) {
			if (!editor.isModified()
					|| JOptionPane.showConfirmDialog(editor,
							mxResources.get("loseChanges")) == JOptionPane.YES_OPTION) {
				ProductLineGraphEditor pge = ((ProductLineGraphEditor) editor);
				mxGraph graph = editor.getGraphComponent().getGraph();
				JButton jb = (JButton) e.getSource();
				if (perspectiveInd != 0
						&& jb.getText().equals(
								mxResources.get("productLineButton"))) {
					pge.editProductLineReset();
					pge.clearPalettes();
					System.out.println("product");
					PLPalettesLoader.loadRegularPalette(pge.insertPalette(mxResources
									.get("productLinePalette")),
							(ProductLineGraph) pge.getGraphComponent().getGraph());
					editor.setPerspective(0);
				}
				if (perspectiveInd != 1
						&& jb.getText().equals(mxResources.get("defectAnalyzerButton"))) {
					pge.editProductLineReset();
					pge.clearPalettes();
					System.out.println("defect");
					editor.setPerspective(1);
				}
				if (perspectiveInd != 2
						&& jb.getText().equals(mxResources.get("requirementsButton"))) {
					pge.editProductLineReset();
					pge.clearPalettes();
					System.out.println("requirements");
					editor.setPerspective(2);

					RQPalettesLoader.loadConceptsPalette(pge
							.insertPalette(mxResources.get("conceptsPalette")),
							(ProductLineGraph) pge.getGraphComponent().getGraph());
					RQPalettesLoader.loadRelationsPalette(
							pge.insertPalette(mxResources.get("relationsPalette")),
							(ProductLineGraph) pge.getGraphComponent()
									.getGraph());
				}
				// Check modified flag and display save dialog
				mxCell root = new mxCell();
				root.insert(new mxCell());
				graph.getModel().setRoot(root);

				editor.setModified(false);
				editor.setCurrentFile(null);
				editor.getGraphComponent().zoomAndCenter();
				
			}
		}
		perspective.updateButtons();
	}
}