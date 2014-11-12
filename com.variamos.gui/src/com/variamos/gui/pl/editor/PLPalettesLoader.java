package com.variamos.gui.pl.editor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.cfm.productline.Asset;
import com.cfm.productline.VariabilityElement;
import com.cfm.productline.constraints.GenericConstraint;
import com.cfm.productline.constraints.GroupConstraint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mxgraph.examples.swing.GraphEditor;
import com.variamos.gui.maineditor.EditorPalette;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxResources;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.variamos.pl.editor.logic.ConstraintMode;
import com.variamos.pl.editor.logic.PaletteDatabase;
import com.variamos.pl.editor.logic.PaletteDatabase.NaturalDeserializer;
import com.variamos.pl.editor.logic.PaletteDatabase.PaletteDefinition;
import com.variamos.pl.editor.logic.PaletteDatabase.PaletteEdge;
import com.variamos.pl.editor.logic.PaletteDatabase.PaletteNode;

public class PLPalettesLoader {
	public static void loadRegularPalette(EditorPalette palette, ProductLineGraph plgraph) {
		//Load regular palette
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
		//Load palette from file
/*		try {
			FileReader reader;
			reader = new FileReader(new File("palettes.pal"));
			Gson gson = new GsonBuilder().setPrettyPrinting()
					.serializeNulls()
					.registerTypeAdapter(Object.class, new NaturalDeserializer())
					.create();
			PaletteDatabase db = gson.fromJson(reader, PaletteDatabase.class);
			loadPaletteDatabase(db,palette);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	*/	
	}
	private static void loadPaletteDatabase(PaletteDatabase db, EditorPalette palette) {
		for(PaletteDefinition pal : db.palettes){
			palette.setName(pal.name);
			for(PaletteNode node : pal.nodes ){
				palette.addTemplate(
						node.name,
						new ImageIcon(
								GraphEditor.class
										.getResource(node.icon)),
						node.styleName, node.width, node.height, node.prototype);
			}
			
			for(PaletteEdge edge : pal.edges){
				palette.addEdgeTemplate(
						edge.name,
						new ImageIcon(
								GraphEditor.class
										.getResource(edge.icon)),
						edge.styleName, edge.width, edge.height, edge.value);
			}
		}
	}
}
