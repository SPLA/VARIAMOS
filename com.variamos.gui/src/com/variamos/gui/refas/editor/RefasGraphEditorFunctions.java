package com.variamos.gui.refas.editor;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.variamos.gui.maineditor.AbstractGraph;
import com.variamos.gui.maineditor.AbstractGraphEditorFunctions;
import com.variamos.gui.maineditor.BasicGraphEditor;
import com.variamos.gui.maineditor.EditorPalette;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.pl.editor.PLEditorPopupMenu;
import com.variamos.gui.pl.editor.ProductLineGraph;
import com.variamos.pl.editor.logic.ConstraintMode;
import com.variamos.syntaxsupport.metametamodel.MetaConcept;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metametamodel.MetaElement;
import com.variamos.syntaxsupport.metametamodel.MetaEnumeration;
import com.variamos.syntaxsupport.metametamodel.MetaVertex;
import com.variamos.syntaxsupport.metametamodel.MetaGroupDependency;
import com.variamos.syntaxsupport.metametamodel.MetaView;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstEnumeration;
import com.variamos.syntaxsupport.metamodel.InstGroupDependency;

public class RefasGraphEditorFunctions extends AbstractGraphEditorFunctions {

	private ArrayList<PaletteElement> paletteElements = new ArrayList<PaletteElement>();

	public RefasGraphEditorFunctions(VariamosGraphEditor editor) {
		super(editor);
		List<MetaView> modelMetaViews = editor.getMetaViews();
		Set<MetaElement> instConcepts = new HashSet<MetaElement>(); 
		for (int i = 0 ; i< modelMetaViews.size();i++)
		{
			instConcepts.addAll(modelMetaViews.get(i).getElements());
		}
			Iterator<MetaElement> elements = instConcepts.iterator();
			while (elements.hasNext())
			{
				MetaElement metaElement = elements.next();
				paletteElements.add(new PaletteElement(metaElement.getIdentifier(), metaElement.getName(),
						metaElement.getImage(), metaElement.getStyle(), metaElement.getWidth(), metaElement.getHeight(),
						null, metaElement));
				
			}				
	}

	public void updateEditor(List<String> validElements,
			mxGraphComponent graphComponent, int modelViewIndex) {
		editor.setPerspective(2);
		editor.editModelReset();
		//System.out.println("requirements perspective");
		updateView(validElements, graphComponent, modelViewIndex);
	}
	public void updateView (List<String> validElements, mxGraphComponent graphComponent, int modelViewIndex)
	{ 		
		editor.clearPalettes();		
		EditorPalette palette = editor.insertPalette(mxResources
				.get("modelViewPalette" + modelViewIndex));
		AbstractGraph refasGraph = (AbstractGraph) graphComponent.getGraph();
		loadPalette(palette, validElements, refasGraph);
		//palette = editor.insertPalette(mxResources.get("conceptsPalette"));
		//loadPalette(palette, null, refasGraph);
		
	}
	
	
	/**
	 * @param palette
	 * @param validElements
	 * @param plgraph
	 */
	public void loadPalette(EditorPalette palette,
			List<String> validElements, AbstractGraph plgraph) {
		// Load regular palette
		if (validElements != null) {
			for (int i = 0; i < paletteElements.size(); i++)
				try {
					PaletteElement paletteElement = paletteElements.get(i);
					if (validElements.contains(paletteElement.getId())) {
						Object obj = null;
						if (paletteElement.getMetaElement()!= null)
						{
							MetaElement metaVertex = paletteElement.getMetaElement();
							if (metaVertex instanceof MetaConcept)
							{
							Object o = new InstConcept();
							Constructor<?> c = o.getClass().getConstructor(MetaConcept.class);
							obj =c.newInstance((MetaConcept)metaVertex);
							}
							else if (metaVertex instanceof MetaGroupDependency)
							{
							Object o = new InstGroupDependency();
							Constructor<?> c = o.getClass().getConstructor(MetaGroupDependency.class);
							obj =c.newInstance((MetaGroupDependency)metaVertex);
							}
							else if (metaVertex instanceof MetaEnumeration)
							{
							Object o = new InstEnumeration();
							Constructor<?> c = o.getClass().getConstructor(MetaEnumeration.class);
							obj =c.newInstance((MetaEnumeration)metaVertex);
							}
								
						}
						else
						{
						String classSingleName = paletteElement.getClassName().substring(paletteElement.getClassName().lastIndexOf(".")+1);
						Class<?> ref = Class.forName( paletteElement.getClassName());
						
						if (paletteElement.getId().equals(classSingleName))
						{
							obj = ref.newInstance();
						}
						else
						{
							Constructor<?> c = ref.getConstructor(String.class);
							obj = c.newInstance(paletteElement.getId());
						}
						}
						palette.addTemplate(
								//mxResources.get(
								paletteElement.getElementTitle(),
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
