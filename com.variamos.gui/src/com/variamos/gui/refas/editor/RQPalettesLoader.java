package com.variamos.gui.refas.editor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;



import com.cfm.productline.VariabilityElement;
import com.mxgraph.examples.swing.GraphEditor;
import com.variamos.gui.maineditor.EditorPalette;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.variamos.gui.pl.editor.ProductLineGraph;
import com.variamos.pl.editor.logic.ConstraintMode;
import com.variamos.pl.editor.logic.PaletteDatabase;
import com.variamos.pl.editor.logic.PaletteDatabase.PaletteDefinition;
import com.variamos.pl.editor.logic.PaletteDatabase.PaletteEdge;
import com.variamos.pl.editor.logic.PaletteDatabase.PaletteNode;
import com.variamos.refas.concepts.Goal;

/**
 * @author jcmunoz
 * Not required - maybe script and bd loads for dynamic palettes
 */



public class RQPalettesLoader {
	public static void loadConceptsPalette(EditorPalette palette,
			ProductLineGraph plgraph) {
		// Load regular palette
		palette.addTemplate(
				mxResources.get("goalTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/refas/editor/images/goal.png")),
				"rqgoal", 100, 40, new Goal());
/*		palette.addTemplate(
				mxResources.get("functionalRequirementTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/refas/editor/images/funreq.png")),
				"rqfunreq", 100, 40, new VariabilityElement());
*/		palette.addTemplate(
				mxResources.get("operationalizationTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/refas/editor/images/operational.png")),
				"rqoper", 100, 40, new VariabilityElement());

		palette.addTemplate(
				mxResources.get("softGoalTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/refas/editor/images/softgoal.png")),
				"rqsoftgoal", 100, 40, new VariabilityElement());

		palette.addTemplate(
				mxResources.get("contextGroupTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/refas/editor/images/contextgrp.png")),
				"rqcontextgrp", 100, 40, new VariabilityElement());
		palette.addTemplate(
				mxResources.get("globalContextTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/refas/editor/images/globCnxtVar.png")),
				"rqglobcnxt", 100, 40, new VariabilityElement());
		palette.addTemplate(
				mxResources.get("localContextTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/refas/editor/images/localCnxtVar.png")),
				"rqlocalcnxt", 100, 40, new VariabilityElement());		

		palette.addTemplate(
				mxResources.get("softDependencyTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/refas/editor/images/softdep.png")),
				"rqsoftdep", 100, 40, new VariabilityElement());
		palette.addTemplate(
				mxResources.get("claimTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/refas/editor/images/claim.png")),
				"rqclaim", 110, 50, new VariabilityElement());
		palette.addTemplate(
				mxResources.get("componentTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/refas/editor/images/component.png")),
				"rqcompon", 110, 50, new VariabilityElement());
		
		final ProductLineGraph graph = plgraph;

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
			ProductLineGraph plgraph) {
		// Load regular palette
	
	palette
	.addTemplate(
			mxResources.get("meansendTitle"),
			new ImageIcon(
					GraphEditor.class
					.getResource("/com/variamos/gui/refas/editor/images/meansend.png")),
					"rqmeansend", 80, 40, new VariabilityElement());
		
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
	

	final ProductLineGraph graph = plgraph;
	
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

	public static void loadScriptedPalettes(EditorPalette palette) {
		// Load palette from file
	/*	try {
			FileReader reader;
			reader = new FileReader(new File("palettes.pal"));
			Gson gson = new GsonBuilder()
					.setPrettyPrinting()
					.serializeNulls()
					.registerTypeAdapter(Object.class,
							new NaturalDeserializer()).create();
			PaletteDatabase db = gson.fromJson(reader, PaletteDatabase.class);
			loadPaletteDatabase(db, palette);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
*/
	}

	private static void loadPaletteDatabase(PaletteDatabase db,
			EditorPalette palette) {
		for (PaletteDefinition pal : db.palettes) {
			palette.setName(pal.name);
			for (PaletteNode node : pal.nodes) {
				palette.addTemplate(
						node.name,
						new ImageIcon(GraphEditor.class.getResource(node.icon)),
						node.styleName, node.width, node.height, node.prototype);
			}

			for (PaletteEdge edge : pal.edges) {
				palette.addEdgeTemplate(edge.name, new ImageIcon(
						GraphEditor.class.getResource(edge.icon)),
						edge.styleName, edge.width, edge.height, edge.value);
			}
		}
	}
}
