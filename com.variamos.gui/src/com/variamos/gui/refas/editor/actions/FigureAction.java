package com.variamos.gui.refas.editor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.maineditor.AbstractEditorAction;

@SuppressWarnings("serial")
public class FigureAction extends AbstractEditorAction{
	
	private String figureName;
	
	public FigureAction(String figureName) {
		this.figureName = figureName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof mxGraphComponent){
			mxGraphComponent graphComponent = (mxGraphComponent) e
					.getSource();
			mxGraph graph = graphComponent.getGraph();

			if (!graph.isSelectionEmpty()){
				graph.setCellStyles("shape", figureName);
				graph.setCellStyles("direction", "north");
			}
		}
	}

}
