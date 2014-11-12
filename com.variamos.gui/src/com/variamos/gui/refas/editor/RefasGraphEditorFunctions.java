package com.variamos.gui.refas.editor;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import com.cfm.productline.Asset;
import com.cfm.productline.VariabilityElement;
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
import com.variamos.gui.pl.editor.PLEditorPopupMenu;
import com.variamos.gui.pl.editor.ProductLineGraph;
import com.variamos.pl.editor.logic.ConstraintMode;
import com.variamos.refas.concepts.*;

public class RefasGraphEditorFunctions extends AbstractGraphEditorFunctions {
	
	public RefasGraphEditorFunctions (VariamosGraphEditor editor)
	{
		super(editor);
	}
	
	public void updateEditor (){
		editor.editProductLineReset();
		editor.clearPalettes();
		System.out.println("requirements");
		editor.setPerspective(2);
		editor.loadRegularPalette(editor.insertPalette(mxResources
				.get("conceptsPalette")));
		editor.loadRegularPalette(editor.insertPalette(mxResources
				.get("relationsPalette")));
	}
	
	public void loadRegularPalette(EditorPalette palette, mxGraphComponent graphComponent) {
		//Load regular palette
		//EditorPalette palette = insertPalette(mxResources.get("productLinePalette"));
		if (palette.getName().equals(mxResources
				.get("conceptsPalette")))
		loadConceptsPalette(palette, (AbstractGraph)graphComponent.getGraph());
		else
		loadRelationsPalette(palette, (AbstractGraph)graphComponent.getGraph());
	}
	public  void loadConceptsPalette(EditorPalette palette,
			AbstractGraph plgraph) {
			// Load regular palette
			palette.addTemplate(
					mxResources.get("goalTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/refas/editor/images/goal.png")),
					"rqgoal", 100, 40, new Goal());
			palette.addTemplate(
					mxResources.get("assumptionTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/refas/editor/images/assump.png")),
					"rqassump", 100, 40, new Assumption());
			palette.addTemplate(
					mxResources.get("operationalizationTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/refas/editor/images/operational.png")),
					"rqoper", 100, 40, new Operationalization());

			palette.addTemplate(
					mxResources.get("softGoalTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/refas/editor/images/softgoal.png")),
					"rqsoftgoal", 100, 40, new SoftGoal());

			palette.addTemplate(
					mxResources.get("contextGroupTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/refas/editor/images/contextgrp.png")),
					"rqcontextgrp", 100, 40, new ContextGroup());
			palette.addTemplate(
					mxResources.get("globalContextTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/refas/editor/images/globCnxtVar.png")),
					"rqglobcnxt", 100, 40, new ContextVariable());
			palette.addTemplate(
					mxResources.get("localContextTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/refas/editor/images/localCnxtVar.png")),
					"rqlocalcnxt", 100, 40, new ContextVariable());		

			palette.addTemplate(
					mxResources.get("softDependencyTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/refas/editor/images/softdep.png")),
					"rqsoftdep", 100, 40, new SoftDependency());
			palette.addTemplate(
					mxResources.get("claimTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/refas/editor/images/claim.png")),
					"rqclaim", 110, 50, new Claim());
			palette.addTemplate(
					mxResources.get("assetTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/refas/editor/images/component.png")),
					"rqcompon", 110, 50, new Asset());
			
			final AbstractGraph graph = plgraph;

			palette.addListener(mxEvent.SELECT, new mxIEventListener() {
				public void invoke(Object sender, mxEventObject evt) {
					Object tmp = evt.getProperty("transferable");
					graph.setConsMode(ConstraintMode.None);

					if (tmp instanceof mxGraphTransferable) {
						mxGraphTransferable t = (mxGraphTransferable) tmp;
						Object obj = t.getCells()[0];

						if (graph.getModel().isEdge(obj)) {
							mxCell cell = (mxCell) obj;
							((ProductLineGraph) graph)
									.setConsMode((ConstraintMode) cell.getValue());
						}
					}
				}

			});

		}
		
		public static void loadRelationsPalette(EditorPalette palette,
				AbstractGraph plgraph) {
			// Load regular palette
		
			palette
			.addTemplate(
					mxResources.get("groupIconTitle"),
					new ImageIcon(
							GraphEditor.class
							.getResource("/com/variamos/gui/pl/editor/images/plgroup.png")),
							"plgroup", 20, 20, new GroupConstraint());
			
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
		

		final AbstractGraph graph = plgraph;
		
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
						((AbstractGraph) graph).setConsMode( (ConstraintMode) cell.getValue());
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
			//RefasEditorPopupMenu menu = new RefasEditorPopupMenu(editor);
			menu.show(graphComponent, pt.x, pt.y);

			e.consume();
		}
}
