package com.variamos.gui.maineditor;

import java.awt.Color;
import java.util.EventObject;

import com.cfm.productline.constraints.ExcludesConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.cfm.productline.constraints.MandatoryConstraint;
import com.cfm.productline.constraints.OptionalConstraint;
import com.cfm.productline.constraints.RequiresConstraint;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.pl.editor.ProductLineGraph;
import com.variamos.pl.editor.logic.ConstraintMode;

@SuppressWarnings("serial")
public class VariamosGraphComponent extends mxGraphComponent {

	public VariamosGraphComponent(mxGraph graph) {
		super(graph);

		setToolTips(true);
		setAutoExtend(true);
		setAutoScroll(false);
		setDragEnabled(false);
		setPanning(true);
		configureConnectionHandler();
		configureSelectionHandler();
		
		getViewport().setOpaque(true);
		getViewport().setBackground(Color.WHITE);
		
		// Installs automatic validation
	    graph.getModel().addListener( mxEvent.CHANGE, new mxIEventListener() {
	      public void invoke(Object sender, mxEventObject evt) {
	        clearCellOverlays();
	        validateGraph();
	      }
	    });
	}
	
	private void configureSelectionHandler() {
		graph.getSelectionModel().addListener(mxEvent.CHANGE, new mxIEventListener() {
			
			@Override
			public void invoke(Object sender, mxEventObject evt) {
				
			}
		});
	}

	private void configureConnectionHandler(){
		getConnectionHandler().setCreateTarget(false);
		getConnectionHandler().setEnabled(true);
		getConnectionHandler().addListener(mxEvent.CONNECT, onConnect);
	}
	
	private mxIEventListener onConnect = /**
	 * @author 
	 * jcmunoz: Creates relations between nodes, works for group constraints now.
	 *
	 */
	new mxIEventListener(){

		@Override
		public void invoke(Object sender, mxEventObject evt) {
			mxCell insertedCell = (mxCell) evt.getProperty("cell");
			ProductLineGraph graph = (ProductLineGraph) getGraph();
			
			mxCell source = (mxCell) insertedCell.getSource();
			mxCell target = (mxCell) insertedCell.getTarget();
			
			if( source.getValue() instanceof GroupConstraint ){
				
				//Remove previous cells between them
				Object[] edges = graph.getEdgesBetween(source, target, false);
				graph.removeCells(edges);
				graph.addCell(insertedCell);
				
				GroupConstraint gc = (GroupConstraint) source.getValue();
				gc.addChildId(target.getId());
				//gc.printDebug(System.out);
				return;
			}
			
			if( target.getValue() instanceof GroupConstraint ){
				
				//Remove previous cells between them
				Object[] edges = graph.getEdgesBetween(source, target, false);
				graph.removeCells(edges);
				graph.addCell(insertedCell);
				
				GroupConstraint gc = (GroupConstraint) target.getValue();
				//If there was a parent, remove it
				if( gc.getParent() != null ){
					//Removing parent
					Object[] parentEdges = graph.getEdgesBetween(graph.getCellById( gc.getParent() ), target, false);
					graph.removeCells(parentEdges);
				}
				
				gc.setParent(source.getId());
				//gc.printDebug(System.out);
				return;
			}
			
			ConstraintMode mode = graph.getConsMode();
			
			switch(mode){
				case Optional:
					OptionalConstraint op = new OptionalConstraint(source.getId(), target.getId());
					insertedCell.setValue(op);
					insertedCell.setStyle("ploptional");
					break;
				case Mandatory:
					MandatoryConstraint om = new MandatoryConstraint(source.getId(), target.getId());
					insertedCell.setValue(om);
					insertedCell.setStyle("plmandatory");
					break;
				
				case Requires:
					RequiresConstraint rq = new RequiresConstraint(source.getId(), target.getId());
					insertedCell.setValue(rq);
					insertedCell.setStyle("plrequires");
					break;
				case Excludes:
					ExcludesConstraint ec = new ExcludesConstraint(source.getId(), target.getId());
					insertedCell.setValue(ec);
					insertedCell.setStyle("plexcludes");
					break;
					
				case Default:
					graph.removeCells(new Object[]{ insertedCell });
					//Create a new constraint
					graph.connectDefaultConstraint(source, target);
					break;
				default:
					break;
			}
			
			//System.out.println("Source = " + source.getValue() + ", Target = " + target.getValue());
			
		}
		
	};
	
	@Override
	public String getEditingValue(Object cell, EventObject trigger) {
		return super.getEditingValue(cell, trigger);
	}
}
