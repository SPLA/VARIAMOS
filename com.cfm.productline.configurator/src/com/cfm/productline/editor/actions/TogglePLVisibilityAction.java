package com.cfm.productline.editor.actions;

import java.awt.event.ActionEvent;

import com.cfm.productline.editor.ProductLineGraph;
import com.mxgraph.swing.mxGraphComponent;

@SuppressWarnings("serial")
public class TogglePLVisibilityAction extends ConfiguratorEditorAction {
	
	boolean visibility = true;
	
	public TogglePLVisibilityAction(){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof mxGraphComponent){
			mxGraphComponent graphComponent = (mxGraphComponent) e
					.getSource();
			ProductLineGraph graph = (ProductLineGraph)graphComponent.getGraph();
			
			visibility = !visibility;
			graph.setPLElementsVisibility(visibility);
		}
		
	}

}
