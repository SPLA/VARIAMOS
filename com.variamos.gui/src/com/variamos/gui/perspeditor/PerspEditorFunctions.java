package com.variamos.gui.perspeditor;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import com.mxgraph.examples.swing.GraphEditor;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxResources;
import com.variamos.editor.logic.ConstraintMode;
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.gui.maineditor.AbstractGraphEditorFunctions;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.EditorPalette;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.pl.editor.ProductLineGraph;
import com.variamos.perspsupport.instancesupport.InstCell;
import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstEnumeration;
import com.variamos.perspsupport.instancesupport.InstOverTwoRelation;
import com.variamos.perspsupport.perspmodel.RefasModel;
import com.variamos.perspsupport.syntaxsupport.MetaConcept;
import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.perspsupport.syntaxsupport.MetaEnumeration;
import com.variamos.perspsupport.syntaxsupport.MetaOverTwoRelation;
import com.variamos.perspsupport.syntaxsupport.MetaVertex;

public class PerspEditorFunctions extends AbstractGraphEditorFunctions {

	private ArrayList<PaletteElement> paletteElements = new ArrayList<PaletteElement>();

	public PerspEditorFunctions(VariamosGraphEditor editor) {
		super(editor);
		Collection<MetaElement> metaElements = new HashSet<MetaElement>();
		for (InstElement instVertex : ((RefasModel) editor.getEditedModel())
				.getSyntaxRefas().getVertices()) {
			metaElements.add(instVertex.getEditableMetaElement());
		}
		for (MetaElement metaElement : metaElements) {
			paletteElements.add(new PaletteElement(metaElement.getIdentifier(),
					metaElement.getName(), metaElement.getImage(), metaElement
							.getStyle(), metaElement.getWidth(), metaElement
							.getHeight(), null, metaElement));
		}
	}

	public void updateEditor(List<String> validElements,
			mxGraphComponent graphComponent, int modelViewIndex) {
		// editor.setPerspective(2);
		editor.editModelReset();
		// System.out.println("requirements perspective");
		updateView(validElements, graphComponent, modelViewIndex);
	}

	public void updateView(List<String> validElements,
			mxGraphComponent graphComponent, int modelViewIndex) {
		editor.clearPalettes();
		EditorPalette[] palettes = editor.insertPalettes(editor.getEditedModel()
				.getInstViewName(modelViewIndex, -1)
		// getmxResources.get("modelViewPalette" + modelViewIndex)
				);
		AbstractGraph refasGraph = (AbstractGraph) graphComponent.getGraph();
		loadPalette(palettes, validElements, refasGraph);
		editor.refreshPalette();
	}

	/**
	 * @param palette
	 * @param validElements
	 * @param plgraph
	 */
	public void loadPalette(EditorPalette[] palettes, List<String> validElements,
			AbstractGraph plgraph) {
		// Load regular palette
		if (validElements != null) {
			for (int i = 0; i < paletteElements.size(); i++)
				try {
					PaletteElement paletteElement = paletteElements.get(i);
					if (validElements.contains(paletteElement.getId())) {
						InstElement obj = null;
						if (paletteElement.getMetaElement() != null) {
							MetaElement metaVertex = paletteElement
									.getMetaElement();

							if (metaVertex instanceof MetaConcept) {
								// MetaElement metaElement = new MetaConcept();
								Object o = new InstConcept();
								Constructor<?> c = o.getClass().getConstructor(
										String.class, MetaElement.class,
										MetaElement.class);
								if (editor.getPerspective() != 2)
									obj = (InstElement) c.newInstance("",
											(MetaElement) metaVertex,
											new MetaConcept());
								else
									obj = (InstElement) c.newInstance("",
											(MetaElement) metaVertex, null);

							} else if (metaVertex instanceof MetaOverTwoRelation) {
								// MetaElement metaElement = ;
								Object o = new InstOverTwoRelation();
								Constructor<?> c = o.getClass().getConstructor(
										String.class,
										MetaOverTwoRelation.class,
										MetaElement.class);
								if (editor.getPerspective() != 2)
									obj = (InstElement) c.newInstance("",
											(MetaOverTwoRelation) metaVertex,
											new MetaOverTwoRelation());
								else

									obj = (InstElement) c.newInstance("",
											(MetaOverTwoRelation) metaVertex,
											null);
							} else if (metaVertex instanceof MetaEnumeration) {
								// MetaElement metaElement = new
								// MetaEnumeration();
								Object o = new InstEnumeration();
								Constructor<?> c = o.getClass().getConstructor(
										String.class, MetaVertex.class,
										MetaElement.class);
								if (editor.getPerspective() != 2)
									obj = (InstElement) c.newInstance("",
											(MetaVertex) metaVertex,
											new MetaEnumeration());
								else

									obj = (InstElement) c.newInstance("",
											(MetaVertex) metaVertex, null);
							}

						} else {
							System.out
									.println("Not supported element for palette");
							/*
							 * String classSingleName = paletteElement
							 * .getClassName().substring(
							 * paletteElement.getClassName() .lastIndexOf(".") +
							 * 1); Class<?> ref = Class.forName(paletteElement
							 * .getClassName());
							 * 
							 * if
							 * (paletteElement.getId().equals(classSingleName))
							 * { obj = ref.newInstance(); } else {
							 * Constructor<?> c = ref
							 * .getConstructor(String.class); obj =
							 * c.newInstance(paletteElement.getId()); }
							 */
						}
						for (EditorPalette palette : palettes)
						{
						palette.addTemplate(
								// mxResources.get(
								paletteElement.getElementTitle(),
								new ImageIcon(GraphEditor.class
										.getResource(paletteElement.getIcon())),
								paletteElement.getStyle(), paletteElement
										.getWidth(),
								paletteElement.getHeight(), new InstCell(null,
										obj, false));
						}
					}
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
		}

		final AbstractGraph graph = plgraph;

		for (EditorPalette palette : palettes)
		{
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

	}

	public void showGraphPopupMenu(MouseEvent e,
			mxGraphComponent graphComponent, BasicGraphEditor editor) {
		Point pt = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(),
				graphComponent);
		PerspEditorPopupMenu menu = new PerspEditorPopupMenu(editor);
		// RefasEditorPopupMenu menu = new RefasEditorPopupMenu(editor);
		menu.show(graphComponent, pt.x, pt.y);

		e.consume();
	}

}
