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

import com.mxgraph.swing.mxGraphComponent;
import com.variamos.dynsup.instance.InstCell;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstOverTwoRel;
import com.variamos.dynsup.model.OpersConcept;
import com.variamos.dynsup.model.OpersElement;
import com.variamos.dynsup.model.OpersLabeling;
import com.variamos.dynsup.model.OpersSubOperation;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.gui.core.mxgraph.editor.BasicGraphEditor;
import com.variamos.gui.core.mxgraph.editor.EditorPalette;
import com.variamos.gui.maineditor.AbstractGraphEditorFunctions;
import com.variamos.gui.maineditor.VariamosGraphEditor;

public class PerspEditorFunctions extends AbstractGraphEditorFunctions {

	private ArrayList<PaletteElement> paletteElements = null;

	public PerspEditorFunctions(VariamosGraphEditor editor) {
		super(editor);
		loadPaletteElements();
	}

	private void loadPaletteElements() {
		paletteElements = new ArrayList<PaletteElement>();
		Collection<InstElement> instElements = new HashSet<InstElement>();
		if (editor.getEditedModel().getSyntaxModel() != null)
			for (InstElement instVertex : editor.getEditedModel()
					.getSyntaxModel().getVertices()) {
				instElements.add(instVertex);
			}
		for (InstElement instElement : instElements) {
			SyntaxElement metaElement = instElement.getEdSyntaxEle();
			paletteElements.add(new PaletteElement(metaElement
					.getAutoIdentifier(), metaElement.getName(), metaElement
					.getImage(), metaElement.getStyle(),
					metaElement.getWidth(), metaElement.getHeight(), null,
					metaElement, instElement));
		}
	}

	@Override
	public void updateEditor(List<String> validElements,
			mxGraphComponent graphComponent, int modelViewIndex) {
		// editor.setPerspective(2);
		// editor.editModelReset();
		// System.out.println("requirements perspective");
		updateView(validElements, graphComponent, modelViewIndex);
	}

	@Override
	public void updateView(List<String> validElements,
			mxGraphComponent graphComponent, int modelViewIndex) {
		editor.reloadMenus();
		editor.clearPalettes();
		loadPaletteElements();
		EditorPalette[] palettes = editor.insertPalettes(editor
				.getEditedModel().getInstViewPalettesName(modelViewIndex, -1)
		// getmxResources.get("modelViewPalette" + modelViewIndex)
				);
		String viewName = editor.getEditedModel().getInstViewName(
				modelViewIndex, -1);
		PerspEditorGraph refasGraph = (PerspEditorGraph) graphComponent
				.getGraph();
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
			List<String> validElements, PerspEditorGraph plgraph) {
		// Load regular palette
		if (validElements != null) {
			for (int i = 0; i < paletteElements.size(); i++)
				try {
					PaletteElement paletteElement = paletteElements.get(i);
					if (validElements.contains(paletteElement.getId())) {
						InstElement obj = null;
						if (paletteElement.getMetaElement() != null) {
							SyntaxElement metaVertex = paletteElement
									.getMetaElement();
							InstElement instElement = paletteElement
									.getInstElement();
							if (metaVertex instanceof SyntaxElement) {
								Object o;
								o = new InstConcept();

								if (editor.getPerspective() == 3) {
									Constructor<?> c = o.getClass()
											.getConstructor(String.class,
													InstElement.class,
													SyntaxElement.class);

									switch (metaVertex.getType()) {
									case 'V':
										obj = (InstElement) c.newInstance("",
												instElement, new SyntaxElement(
														'V'));
										break;
									case 'P':
									case 'I':
									case 'X':
										obj = (InstElement) c.newInstance("",
												instElement, new SyntaxElement(
														'P'));
										break;
									case 'E':
										o = new InstConcept();
										c = o.getClass().getConstructor(
												String.class,
												InstElement.class,
												SyntaxElement.class);
										obj = (InstElement) c.newInstance("",
												instElement,
												new SyntaxElement());
										break;
									case 'O':
										obj = (InstElement) c.newInstance("",
												instElement, new SyntaxElement(
														'O'));
										break;
									case 'C':
										obj = (InstElement) c.newInstance("",
												instElement, new SyntaxElement(
														'C'));
									}
								} else if (editor.getPerspective() == 1) {
									Constructor<?> c = o.getClass()
											.getConstructor(String.class,
													InstElement.class,
													OpersElement.class);
									switch (metaVertex.getType()) {
									case 'M':
										obj = (InstElement) c
												.newInstance("", instElement,
														new OpersConcept());
										break;
									case 'A':
										obj = (InstElement) c
												.newInstance("", instElement,
														new OpersConcept());
										break;
									case 'S':
										obj = (InstElement) c.newInstance("",
												instElement,
												new OpersSubOperation());
										break;
									case 'L':
										obj = (InstElement) c.newInstance("",
												instElement,
												new OpersLabeling());
										break;
									case 'P':
										obj = (InstElement) c
												.newInstance("", instElement,
														new OpersConcept());
										break;
									case 'E':
										o = new InstConcept();
										c = o.getClass().getConstructor(
												String.class,
												InstElement.class,
												SyntaxElement.class);
										obj = (InstElement) c
												.newInstance("", instElement,
														new OpersConcept());
										break;
									case 'O':
										obj = (InstElement) c
												.newInstance("", instElement,
														new OpersConcept());
										break;
									case 'C':
										obj = (InstElement) c
												.newInstance("", instElement,
														new OpersConcept());
									}
								} else {
									Constructor<?> c = null;
									switch (metaVertex.getType()) {
									case 'O':
										o = new InstOverTwoRel();
										c = o.getClass().getConstructor(
												String.class,
												SyntaxElement.class,
												InstElement.class);
										obj = (InstElement) c.newInstance("",
												null, instElement);
										break;
									default:
										c = o.getClass().getConstructor(
												String.class,
												InstElement.class,
												SyntaxElement.class);
										obj = (InstElement) c.newInstance("",
												instElement, null);
									}

								}

							} /*
							 * else if (metaVertex instanceof SyntaxOverTwoRel)
							 * { // MetaElement metaElement = ; Object o = new
							 * InstOverTwoRel(); Constructor<?> c =
							 * o.getClass().getConstructor( String.class,
							 * SyntaxElement.class, SyntaxElement.class); if
							 * (editor.getPerspective() != 2) obj =
							 * (InstElement) c.newInstance("", (SyntaxElement)
							 * metaVertex, new SyntaxConcept()); else
							 * 
							 * obj = (InstElement) c .newInstance( "",
							 * (SyntaxOverTwoRel) metaVertex, null); }
							 */
							/*
							 * else if (metaVertex instanceof SyntaxEnum) { //
							 * MetaElement metaElement = new //
							 * MetaEnumeration(); Object o = new InstEnum();
							 * Constructor<?> c = o.getClass().getConstructor(
							 * String.class, SyntaxElement.class,
							 * SyntaxElement.class); if (editor.getPerspective()
							 * != 2) obj = (InstElement) c.newInstance("",
							 * (SyntaxElement) metaVertex, new SyntaxEnum());
							 * else
							 * 
							 * obj = (InstElement) c.newInstance("",
							 * (SyntaxElement) metaVertex, null); }
							 */

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
							ImageIcon icon = null;
							try {
								icon = new ImageIcon(
										PerspEditorFunctions.class
												.getResource(paletteElement
														.getIcon()));
							} catch (Exception e) {

							}
							if (editor.getPerspective() == 2)
								search: for (InstElement relation : relations) {
									InstConcept pairwiseRelation = (InstConcept) relation
											.getSourceRelations().get(0);
									if (pairwiseRelation.getSupInstEleId() == null
											|| !pairwiseRelation
													.getSupInstEleId().equals(
															"SyMViewNode")
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
												icon,
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
										paletteElement.getElementTitle(), icon,
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

		final PerspEditorGraph graph = plgraph;

	}

	@Override
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
