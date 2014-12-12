package com.variamos.gui.refas.editor;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import com.cfm.productline.Asset;
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
import com.variamos.refas.core.staticconcepts.*;

public class SimulationGraphEditorFunctions extends AbstractGraphEditorFunctions {

	private ArrayList<PaletteElement> paletteElements = new ArrayList<PaletteElement>();

	public SimulationGraphEditorFunctions(VariamosGraphEditor editor) {
		super(editor);
	}

	public void updateEditor(List<String> validElements,
			mxGraphComponent graphComponent, int modelViewIndex) {
		editor.setPerspective(4);
		editor.editModelReset();
		System.out.println("simulation");
		updateView(validElements, graphComponent, modelViewIndex);
	}
	public void updateView (List<String> validElements, mxGraphComponent graphComponent, int modelViewIndex)
	{ 		
		editor.clearPalettes();		
		EditorPalette palette = editor.insertPalette(mxResources
				.get("metamodelViewPalette"));
		AbstractGraph refasGraph = (AbstractGraph) graphComponent.getGraph();
		loadPalette(palette, validElements, refasGraph);
		palette = editor.insertPalette(mxResources.get("allmodelingPalette"));
		loadPalette(palette, null, refasGraph);
		
	}
	
	
	public void loadPalette(EditorPalette palette,
			List<String> validElements, AbstractGraph plgraph) {
		// Load regular palette
		if (validElements != null) {
			for (int i = 0; i < paletteElements.size(); i++)
				try {
					PaletteElement paletteElement = paletteElements.get(i);
					if (validElements.contains(paletteElement.getId())) {
						String classSingleName = paletteElement.getClassName().substring(paletteElement.getClassName().lastIndexOf(".")+1);
						Class<?> ref = Class.forName( paletteElement.getClassName());
						Object obj = null;
						if (paletteElement.getId().equals(classSingleName))
						{
							obj = ref.newInstance();
						}
						else
						{
							Constructor<?> c = ref.getConstructor(String.class);
							obj = c.newInstance(paletteElement.getId());
						}
							
						palette.addTemplate(
								mxResources.get(paletteElement
										.getElementTitle()),
								new ImageIcon(GraphEditor.class
										.getResource(paletteElement.getIcon())),
								paletteElement.getStyle(), paletteElement
										.getWidth(),
								paletteElement.getHeight(), obj);
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} else
		/*
		 * paletteElements.add(new PaletteElement("","","","",,,""));
		 * paletteElements.add(new PaletteElement("","","","",,,""));
		 */
		{
			palette.addTemplate(
					mxResources.get("goalTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/refas/editor/images/goal.png")),
					"rqgoal", 100, 40, null);
			palette.addTemplate(
					mxResources.get("assumptionTitle"),
					new ImageIcon(
							GraphEditor.class
									.getResource("/com/variamos/gui/refas/editor/images/assump.png")),
					"rqassump", 100, 40, null);
			
		}
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

	public void loadConceptsPalette(EditorPalette palette, AbstractGraph plgraph) {
		// Load regular palette
		palette.addTemplate(
				mxResources.get("goalTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/refas/editor/images/goal.png")),
				"rqgoal", 100, 40, null);
		palette.addTemplate(
				mxResources.get("assumptionTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/refas/editor/images/assump.png")),
				"rqassump", 100, 40, null);
		

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

		palette.addTemplate(
				mxResources.get("groupIconTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/pl/editor/images/plgroup.png")),
				"plgroup", 20, 20, new GroupConstraint());

		palette.addEdgeTemplate(
				mxResources.get("optionalIconTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/pl/editor/images/ploptional.png")),
				"ploptional", 80, 40, ConstraintMode.Optional);

		palette.addEdgeTemplate(
				mxResources.get("mandatoryIconTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/pl/editor/images/plmandatory.png")),
				"plmandatory", 80, 40, ConstraintMode.Mandatory);
		palette.addEdgeTemplate(
				mxResources.get("requiresIconTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/pl/editor/images/plrequires.png")),
				"plrequires", 80, 40, ConstraintMode.Requires);
		palette.addEdgeTemplate(
				mxResources.get("excludesIconTitle"),
				new ImageIcon(
						GraphEditor.class
								.getResource("/com/variamos/gui/pl/editor/images/plexcludes.png")),
				"plexcludes", 80, 40, ConstraintMode.Excludes);

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
						((AbstractGraph) graph)
								.setConsMode((ConstraintMode) cell.getValue());
					}
				}
			}

		});

	}

	public void showGraphPopupMenu(MouseEvent e,
			mxGraphComponent graphComponent, BasicGraphEditor editor) {
		Point pt = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(),
				graphComponent);
		PLEditorPopupMenu menu = new PLEditorPopupMenu(editor);
		// RefasEditorPopupMenu menu = new RefasEditorPopupMenu(editor);
		menu.show(graphComponent, pt.x, pt.y);

		e.consume();
	}

}
