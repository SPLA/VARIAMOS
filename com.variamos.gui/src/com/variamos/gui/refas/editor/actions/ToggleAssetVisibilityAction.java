package com.variamos.gui.refas.editor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.swing.mxGraphComponent;
import com.variamos.gui.maineditor.AbstractEditorAction;
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.gui.pl.editor.ProductLineGraph;

@SuppressWarnings("serial")
public class ToggleAssetVisibilityAction extends AbstractEditorAction {
	
	boolean visibility = true;
	
	public ToggleAssetVisibilityAction(){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof mxGraphComponent){
			mxGraphComponent graphComponent = (mxGraphComponent) e
					.getSource();
			AbstractGraph graph = (AbstractGraph)graphComponent.getGraph();
			
			visibility = !visibility;
			graph.setAssetsVisibility(visibility);
		}
		
	}

}
