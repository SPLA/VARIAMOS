package com.variamos.gui.pl.editor;

<<<<<<< HEAD
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import com.cfm.productline.Asset;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.constraints.GenericConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.mxgraph.examples.swing.GraphEditor;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.EditorPalette;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.gui.maineditor.AbstractGraphEditorFunctions;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.pl.editor.logic.ConstraintMode;

public class PLGraphEditorFunctions extends AbstractGraphEditorFunctions {
	public PLGraphEditorFunctions (VariamosGraphEditor editor)
	{
		super(editor);
	}
	
	public void updateEditor (){
		editor.editProductLineReset();
		editor.clearPalettes();
		System.out.println("productlines");
		editor.setPerspective(0);
		editor.loadRegularPalette(editor.insertPalette(mxResources
				.get("productLinePalette")));
	}
	public void loadRegularPalette(EditorPalette palette, mxGraphComponent graphComponent) {
		//Load regular palette
		//EditorPalette palette = insertPalette(mxResources.get("productLinePalette"));
		
		palette
			.addTemplate(
					mxResources.get("varElementIconTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/pl/editor/images/plnode.png")),
					"plnode", 80, 40, new VariabilityElement());
		
		palette
		.addEdgeTemplate(
				mxResources.get("optionalIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/variamos/gui/pl/editor/images/ploptional.png")),
						"ploptional", 80, 40, ConstraintMode.Optional);
		
		palette
		.addEdgeTemplate(
				mxResources.get("mandatoryIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/variamos/gui/pl/editor/images/plmandatory.png")),
						"plmandatory", 80, 40, ConstraintMode.Mandatory);
		palette
		.addEdgeTemplate(
				mxResources.get("requiresIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/variamos/gui/pl/editor/images/plrequires.png")),
						"plrequires", 80, 40, ConstraintMode.Requires);
		palette
		.addEdgeTemplate(
				mxResources.get("excludesIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/variamos/gui/pl/editor/images/plexcludes.png")),
						"plexcludes", 80, 40, ConstraintMode.Excludes);
		
		palette
		.addTemplate(
				mxResources.get("groupIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/variamos/gui/pl/editor/images/plgroup.png")),
						"plgroup", 20, 20, new GroupConstraint());
		
		palette
		.addTemplate(
				mxResources.get("constraintIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/variamos/gui/pl/editor/images/plcons.png")),
						"plcons", 60, 30, new GenericConstraint());
		
		//For the assets
		palette.addTemplate("Asset", new ImageIcon(
				GraphEditor.class
				.getResource("/com/variamos/gui/pl/editor/images/plcons.png")), 
					"plasset", 60, 30, new Asset());
		
		final AbstractGraph graph = (AbstractGraph) graphComponent.getGraph();
		
		palette.addListener(mxEvent.SELECT, new mxIEventListener()
		{
			public void invoke(Object sender, mxEventObject evt)
			{
				Object tmp = evt.getProperty("transferable");
				graph.setConsMode(ConstraintMode.None);
				
				if (tmp instanceof mxGraphTransferable)
				{
					mxGraphTransferable t = (mxGraphTransferable) tmp;
					Object obj = t.getCells()[0];

					if (graph.getModel().isEdge(obj))
					{
						mxCell cell = (mxCell)obj;
						((ProductLineGraph) graph).setConsMode( (ConstraintMode) cell.getValue());
					}
				}
			}

		});
		
	}
	public void showGraphPopupMenu(MouseEvent e, mxGraphComponent graphComponent, BasicGraphEditor editor)
	{
		Point pt = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(),
				graphComponent);
		PLEditorPopupMenu menu = new PLEditorPopupMenu(editor);
		menu.show(graphComponent, pt.x, pt.y);

		e.consume();
	}
	
=======
import javax.swing.ImageIcon;

import com.cfm.productline.Asset;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.constraints.GenericConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.mxgraph.examples.swing.GraphEditor;
import com.mxgraph.examples.swing.editor.EditorPalette;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.variamos.gui.maineditor.AbstractGraphEditorFunctions;
import com.variamos.pl.editor.logic.ConstraintMode;

public class PLGraphEditorFunctions extends AbstractGraphEditorFunctions {
	public PLGraphEditorFunctions ()
	{}
	public void loadRegularPalette(EditorPalette palette, mxGraphComponent graphComponent) {
		//Load regular palette
		//EditorPalette palette = insertPalette(mxResources.get("productLinePalette"));
		
		palette
			.addTemplate(
					mxResources.get("varElementIconTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/pl/editor/images/plnode.png")),
					"plnode", 80, 40, new VariabilityElement());
		
		palette
		.addEdgeTemplate(
				mxResources.get("optionalIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/variamos/gui/pl/editor/images/ploptional.png")),
						"ploptional", 80, 40, ConstraintMode.Optional);
		
		palette
		.addEdgeTemplate(
				mxResources.get("mandatoryIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/variamos/gui/pl/editor/images/plmandatory.png")),
						"plmandatory", 80, 40, ConstraintMode.Mandatory);
		palette
		.addEdgeTemplate(
				mxResources.get("requiresIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/variamos/gui/pl/editor/images/plrequires.png")),
						"plrequires", 80, 40, ConstraintMode.Requires);
		palette
		.addEdgeTemplate(
				mxResources.get("excludesIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/variamos/gui/pl/editor/images/plexcludes.png")),
						"plexcludes", 80, 40, ConstraintMode.Excludes);
		
		palette
		.addTemplate(
				mxResources.get("groupIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/variamos/gui/pl/editor/images/plgroup.png")),
						"plgroup", 20, 20, new GroupConstraint());
		
		palette
		.addTemplate(
				mxResources.get("constraintIconTitle"),
				new ImageIcon(
						GraphEditor.class
						.getResource("/com/variamos/gui/pl/editor/images/plcons.png")),
						"plcons", 60, 30, new GenericConstraint());
		
		//For the assets
		palette.addTemplate("Asset", new ImageIcon(
				GraphEditor.class
				.getResource("/com/variamos/gui/pl/editor/images/plcons.png")), 
					"plasset", 60, 30, new Asset());
		
		final ProductLineGraph graph = (ProductLineGraph) graphComponent.getGraph();
		
		palette.addListener(mxEvent.SELECT, new mxIEventListener()
		{
			public void invoke(Object sender, mxEventObject evt)
			{
				Object tmp = evt.getProperty("transferable");
				graph.setConsMode(ConstraintMode.None);
				
				if (tmp instanceof mxGraphTransferable)
				{
					mxGraphTransferable t = (mxGraphTransferable) tmp;
					Object obj = t.getCells()[0];

					if (graph.getModel().isEdge(obj))
					{
						mxCell cell = (mxCell)obj;
						((ProductLineGraph) graph).setConsMode( (ConstraintMode) cell.getValue());
					}
				}
			}

		});
		
	}
>>>>>>> branch 'development' of https://github.com/jcmunozf/VARIAMOS
}
