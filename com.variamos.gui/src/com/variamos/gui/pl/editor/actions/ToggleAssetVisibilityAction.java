package com.variamos.gui.pl.editor.actions;

import java.awt.event.ActionEvent;

import com.mxgraph.swing.mxGraphComponent;
import com.variamos.gui.maineditor.AbstractEditorAction;
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
			ProductLineGraph graph = (ProductLineGraph)graphComponent.getGraph();
			
			visibility = !visibility;
			graph.setAssetsVisibility(visibility);
		}
		
	}

}
