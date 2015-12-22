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
import com.variamos.editor.logic.ConstraintMode;
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.gui.maineditor.AbstractGraphEditorFunctions;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.EditorPalette;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.pl.editor.ProductLineGraph;
import com.variamos.perspsupport.expressionsupport.SemanticOperationAction;
import com.variamos.perspsupport.expressionsupport.SemanticOperationMenu;
import com.variamos.perspsupport.expressionsupport.SemanticOperationSubAction;
import com.variamos.perspsupport.instancesupport.InstCell;
import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstEnumeration;
import com.variamos.perspsupport.instancesupport.InstOverTwoRelation;
import com.variamos.perspsupport.perspmodel.RefasModel;
import com.variamos.perspsupport.semanticinterface.IntSemanticElement;
import com.variamos.perspsupport.semanticsupport.SemanticConcept;
import com.variamos.perspsupport.semanticsupport.SemanticEnumeration;
import com.variamos.perspsupport.semanticsupport.SemanticOverTwoRelation;
import com.variamos.perspsupport.semanticsupport.SemanticPairwiseRelation;
import com.variamos.perspsupport.syntaxsupport.MetaConcept;
import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.perspsupport.syntaxsupport.MetaEnumeration;
import com.variamos.perspsupport.syntaxsupport.MetaOverTwoRelation;
import com.variamos.perspsupport.syntaxsupport.MetaPairwiseRelation;
import com.variamos.perspsupport.syntaxsupport.MetaVertex;
import com.variamos.perspsupport.syntaxsupport.MetaView;

public class PerspEditorFunctions extends AbstractGraphEditorFunctions {

	private ArrayList<PaletteElement> paletteElements = null;

	public PerspEditorFunctions(VariamosGraphEditor editor) {
		super(editor);
		loadPaletteElements();
	}

	private void loadPaletteElements() {
		paletteElements = new ArrayList<PaletteElement>();
		Collection<InstElement> instElements = new HashSet<InstElement>();
		if (((RefasModel) editor.getEditedModel()).getSyntaxRefas() != null)
			for (InstElement instVertex : ((RefasModel) editor.getEditedModel())
					.getSyntaxRefas().getVertices()) {
				instElements.add(instVertex);
			}
		for (InstElement instElement : instElements) {
			MetaElement metaElement = instElement.getEditableMetaElement();
			paletteElements.add(new PaletteElement(metaElement
					.getAutoIdentifier(), metaElement.getName(), metaElement
					.getImage(), metaElement.getStyle(),
					metaElement.getWidth(), metaElement.getHeight(), null,
					metaElement, instElement));
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
		loadPaletteElements();
		EditorPalette[] palettes = editor.insertPalettes(editor
				.getEditedModel().getInstViewPalettesName(modelViewIndex, -1)
		// getmxResources.get("modelViewPalette" + modelViewIndex)
				);
		String viewName = editor.getEditedModel().getInstViewName(
				modelViewIndex, -1);
		AbstractGraph refasGraph = (AbstractGraph) graphComponent.getGraph();
		loadPalette(viewName, palettes, validElements, refasGraph);
		editor.refreshPalette();
	}

	/**
	 * @param viewName
	 * @param palette
	 * @param validElements
	 * @param plgraph
	 */
	public void loadPalette(String viewName, EditorPalette[] palettes,
			List<String> validElements, AbstractGraph plgraph) {
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
								Object o;
								o = new InstConcept();

								if (editor.getPerspective() == 0) {
									Constructor<?> c = o.getClass()
											.getConstructor(String.class,
													MetaElement.class,
													MetaElement.class);

									switch (((MetaElement) metaVertex)
											.getType()) {
									case 'V':
										obj = (InstElement) c.newInstance("",
												(MetaElement) metaVertex,
												new MetaView());
										break;
									case 'P':
									case 'I':
									case 'X':
										obj = (InstElement) c.newInstance("",
												(MetaElement) metaVertex,
												new MetaPairwiseRelation());
										break;
									case 'E':
										o = new InstEnumeration();
										c = o.getClass().getConstructor(
												String.class, MetaVertex.class,
												MetaElement.class);
										obj = (InstElement) c.newInstance("",
												(MetaElement) metaVertex,
												new MetaEnumeration());
										break;
									case 'O':
										obj = (InstElement) c.newInstance("",
												(MetaElement) metaVertex,
												new MetaOverTwoRelation());
										break;
									case 'C':
										obj = (InstElement) c.newInstance("",
												(MetaElement) metaVertex,
												new MetaConcept('C'));
									}
								} else if (editor.getPerspective() == 1) {
									Constructor<?> c = o.getClass()
											.getConstructor(String.class,
													MetaElement.class,
													IntSemanticElement.class);
									switch (((MetaElement) metaVertex)
											.getType()) {
									case 'M':
										obj = (InstElement) c.newInstance("",
												(MetaElement) metaVertex,
												new SemanticOperationMenu());
										break;
									case 'A':
										obj = (InstElement) c.newInstance("",
												(MetaElement) metaVertex,
												new SemanticOperationAction());
										break;
									case 'S':
										obj = (InstElement) c
												.newInstance(
														"",
														(MetaElement) metaVertex,
														new SemanticOperationSubAction());
										break;
									case 'P':
										obj = (InstElement) c.newInstance("",
												(MetaElement) metaVertex,
												new SemanticPairwiseRelation());
										break;
									case 'E':
										o = new InstEnumeration();
										c = o.getClass().getConstructor(
												String.class, MetaVertex.class,
												MetaElement.class);
										obj = (InstElement) c.newInstance("",
												(MetaElement) metaVertex,
												new SemanticEnumeration());
										break;
									case 'O':
										obj = (InstElement) c.newInstance("",
												(MetaElement) metaVertex,
												new SemanticOverTwoRelation());
										break;
									case 'C':
										obj = (InstElement) c.newInstance("",
												(MetaElement) metaVertex,
												new SemanticConcept());
									}
								} else {
									Constructor<?> c = o.getClass()
											.getConstructor(String.class,
													MetaElement.class,
													MetaElement.class);
									obj = (InstElement) c.newInstance("",
											(MetaElement) metaVertex, null);
								}

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
							} else if (metaVertex instanceof MetaPairwiseRelation) {
								// Not shown on palette
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
						if (paletteElement.getIcon() == null) {
							System.out.println("No icon for concept");
							continue;
						}
						for (EditorPalette palette : palettes) {

							List<InstElement> relations = paletteElement
									.getInstElement().getSourceRelations();
							if (editor.getPerspective() == 2)
								search: for (InstElement relation : relations) {
									InstConcept pairwiseRelation = (InstConcept) relation
											.getSourceRelations().get(0);
									if (pairwiseRelation
											.getSupportMetaElementIden() == null
											|| !pairwiseRelation
													.getSupportMetaElementIden()
													.equals("ViewConceptAsso")
											|| pairwiseRelation
													.getSourceRelations()
													.size() == 0
											|| pairwiseRelation
													.getSourceRelations()
													.get(0)
													.getSourceRelations()
													.size() == 0
									/*
									 * || !pairwiseRelation
									 * .getSourceRelations() .get(0)
									 * .getSourceRelations()
									 * .get(0).getIdentifier() .equals(viewName)
									 */)
										continue;
									String paletteName = (String) pairwiseRelation
											.getInstAttribute("Palette")
											.getValue();

									if ((paletteName.equals("") && palettes[0] == palette)
											|| paletteName.equals(palette
													.getName())) {
										palette.addTemplate(
												// mxResources.get(
												paletteElement
														.getElementTitle(),
												new ImageIcon(
														GraphEditor.class
																.getResource(paletteElement
																		.getIcon())),
												paletteElement.getStyle(),
												paletteElement.getWidth(),
												paletteElement.getHeight(),
												new InstCell(null, obj, false));
										break search;
									}
								}
							else {
								palette.addTemplate(
										// mxResources.get(
										paletteElement.getElementTitle(),
										new ImageIcon(GraphEditor.class
												.getResource(paletteElement
														.getIcon())),
										paletteElement.getStyle(),
										paletteElement.getWidth(),
										paletteElement.getHeight(),
										new InstCell(null, obj, false));

							}
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

		for (EditorPalette palette : palettes) {
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
									.setConsMode((ConstraintMode) cell
											.getValue());
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
