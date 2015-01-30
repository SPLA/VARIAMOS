package com.variamos.gui.refas.editor.widgets;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.refas.editor.SemanticPlusSyntax;
import com.variamos.semantic.semanticsupport.AbstractSemanticElement;
import com.variamos.syntax.instancesupport.InstAttribute;
import com.variamos.syntax.instancesupport.InstCell;
import com.variamos.syntax.instancesupport.InstConcept;
import com.variamos.syntax.instancesupport.InstElement;
import com.variamos.syntax.metamodelsupport.EditableElementAttribute;
import com.variamos.syntax.metamodelsupport.MetaVertex;
import com.variamos.syntax.semanticinterface.IntSemanticElement;
import com.variamos.syntax.types.ClassMultiSelectionType;

/**
 * A class to support class widgets on the interface with multi-selection.
 * Inspired on other widgets from ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-04
 * @see com.variamos.gui.pl.editor.widgets
 */
@SuppressWarnings("serial")
public class MClassWidget extends WidgetR {

	private JList<String> txtValue;
	private Map<String, IntSemanticElement> semanticConcepts;
	private Map<String, InstConcept> concepts;

	public MClassWidget() {
		super();
		setLayout(new BorderLayout());

	}

	@Override
	public void configure(EditableElementAttribute v,
			SemanticPlusSyntax semanticSyntaxObject, mxGraph graph) {
		super.configure(v, semanticSyntaxObject, graph);
		ClassLoader classLoader = ClassMultiSelectionType.class.getClassLoader();
		@SuppressWarnings("rawtypes")
		Class aClass = null;
		InstAttribute instAttribute = (InstAttribute)v;
		try {
			aClass = classLoader.loadClass(instAttribute.getAttribute()
					.getClassCanonicalName());
			//System.out.println("aClass.getName() = " + aClass.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String[] out = null;
		if (aClass.getSuperclass().equals(AbstractSemanticElement.class)) {
			semanticConcepts = new HashMap<String, IntSemanticElement>();
			Collection<IntSemanticElement> list = semanticSyntaxObject
					.getSemanticConcepts().values();

			List<IntSemanticElement> list2 = new ArrayList<IntSemanticElement>();

			for (IntSemanticElement concept : list) {
				if (aClass.isInstance(concept))
					list2.add(concept);
			}
			out = new String[list2.size()];
			int i = 0;
			for (IntSemanticElement concept : list2) {
				if (semanticConcepts.put(concept.getIdentifier(), concept) == null) {
					String str = concept.getIdentifier();
					out[i++] = str.toString();
				}
			}
		}
		if (aClass.equals(InstConcept.class)) {
			concepts = new HashMap<String, InstConcept>();
			List<InstConcept> list = getInstConcepts(instAttribute.getAttribute()
					.getMetaConceptInstanceType(), graph);

			Set<InstConcept> set = new HashSet<InstConcept>();
			set.addAll(list);
			out = new String[set.size()];
			int i = 0;
			for (InstConcept concept : set) {
				concepts.put(concept.getIdentifier(), concept);
				String str = concept.getInstAttribute("name").toString();
				out[i++] = str.toString();
			}

		}

		txtValue = new JList<String>(out);
		JScrollPane panel = new JScrollPane(txtValue);
		panel.setPreferredSize(new Dimension(200, 50));
		panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(panel, BorderLayout.CENTER);
		revalidate();
	}

	public List<InstConcept> getInstConcepts(String object, mxGraph graph) {
		List<InstConcept> out = new ArrayList<InstConcept>();
		mxIGraphModel refasGraph = graph.getModel();
		Object o = refasGraph.getRoot(); // Main Root
		mxCell o1 = (mxCell) refasGraph.getChildAt(o, 0); // Null Root
		for (int i = 0; i < o1.getChildCount(); i++) {
			mxCell mv = (mxCell) refasGraph.getChildAt(o1, i);
			for (int j = 0; j < mv.getChildCount(); j++) {
				mxCell concept = (mxCell) refasGraph.getChildAt(mv, j);
				InstElement value = ((InstCell) concept.getValue())
						.getInstElement();
				if (value instanceof InstConcept) {
					InstConcept ic = (InstConcept) value;
					MetaVertex mc = ic.getTransSupportMetaElement();
					if (mc.getIdentifier().equals(object))
						out.add(ic);
				}

			}
		}
		return out;
	}

	@Override
	protected boolean pushValue(EditableElementAttribute v) {
		if (v.getValue() instanceof int[]) {
			@SuppressWarnings("unchecked")
			List<Integer> values = (List<Integer>) v.getValue();
			int[] valuesArray = new int[values.size()];
			int i = 0;
			for (Integer value : values)
				valuesArray[i++] = value;
			txtValue.setSelectedIndices(valuesArray);
		}
		revalidate();
		repaint();
		return false;
	}

	@Override
	protected void pullValue(EditableElementAttribute v) {
		List<Integer> values = new ArrayList<Integer>();
		int[] valuesArray = txtValue.getSelectedIndices();
		for (int i = 0; i < valuesArray.length; i++)
			values.add(valuesArray[i]);
		v.setValue(values);
		String out = "";
		List<String> tmp = txtValue.getSelectedValuesList();
		for (String str : tmp)
			out += str + ";";
		InstAttribute instAttribute = (InstAttribute)v;
		instAttribute.displayValue(out);
	}

	@Override
	public JComponent getEditor() {
		return txtValue;
	}

}
