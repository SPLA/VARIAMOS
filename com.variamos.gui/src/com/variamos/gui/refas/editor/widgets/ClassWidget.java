package com.variamos.gui.refas.editor.widgets;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.view.mxGraph;
import com.variamos.refas.core.sematicsmetamodel.AbstractSemanticConcept;
import com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency;
import com.variamos.refas.core.staticconcepts.SemanticPlusSyntax;
import com.variamos.syntaxsupport.metametamodel.MetaElement;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.type.ClassType;

@SuppressWarnings("serial")
public class ClassWidget extends WidgetR {

	private JComboBox<String> txtValue;
	private Map<String,AbstractSemanticConcept> semanticConcepts;
	private Map<String,InstConcept> concepts;

	public ClassWidget() {
		super();

		setLayout(new BorderLayout());
		txtValue = new JComboBox<String>();
		add(txtValue, BorderLayout.CENTER);
		revalidate();
	}

	@Override
	public void configure(InstAttribute v,
			SemanticPlusSyntax semanticSyntaxObject, mxGraph graph) {

		ClassLoader classLoader = ClassType.class.getClassLoader();
		@SuppressWarnings("rawtypes")
		Class aClass = null;
		try {
			aClass = classLoader.loadClass(v.getAttribute().getEnumType());
			System.out.println("aClass.getName() = " + aClass.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (aClass.getSuperclass().equals(AbstractSemanticConcept.class)) {
			semanticConcepts = new HashMap<String,AbstractSemanticConcept>();
			Collection<AbstractSemanticConcept> list = semanticSyntaxObject
					.getSemanticConcepts().values();

			List<AbstractSemanticConcept> list2 = new ArrayList<AbstractSemanticConcept>();

			for (AbstractSemanticConcept concept : list) {
				if (aClass.isInstance(concept))
					list2.add(concept);
			}

			for (AbstractSemanticConcept concept : list2) {
				semanticConcepts.put(concept.getIdentifier(),concept);
				String patternString = "([_])";
				Pattern p = Pattern.compile(patternString);

				String[] split = p.split(concept.getIdentifier());
				String out = split[0] + " ";
				for (int j = 1; j < split.length; j++)
					out += split[j].toLowerCase() + " ";
				txtValue.addItem(out);
			}
		}
		if (aClass.equals(InstConcept.class)) {
			concepts = new HashMap<String,InstConcept>();
			List<InstConcept> list = getInstConcepts(v.getAttribute().getObject(), graph);

			for (InstConcept concept : list) {
				concepts.put(concept.getIdentifier(),concept);
				String patternString = "([_])";
				Pattern p = Pattern.compile(patternString);

				String[] split = p.split(concept.getInstAttribute("name").toString());
				String out = split[0] + " ";
				for (int j = 1; j < split.length; j++)
					out += split[j].toLowerCase() + " ";
				txtValue.addItem(out);
			}
		}
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
				Object value = concept.getValue();
				if (value instanceof InstConcept) {
					InstConcept ic = (InstConcept) value;
					MetaElement mc = ic.getMetaConcept();
					if (mc.getIdentifier().equals(object))
						out.add(ic);
				}

			}
		}
		return out;
	}

	@Override
	protected void pushValue(InstAttribute v) {
		if (v.getObject() != null)
		txtValue.setSelectedItem((String) ((SemanticGroupDependency)v.getObject()).getIdentifier());
		if (concepts != null)
			v.setObject(concepts.get((String) txtValue.getSelectedItem()));
		if (semanticConcepts != null)
		{
			if (txtValue.getSelectedItem() != null)
			{
			String s =((String) txtValue.getSelectedItem()).trim();
			v.setObject(semanticConcepts.get(s));
			}
		}
	
		revalidate();
		repaint();
	}

	@Override
	protected void pullValue(InstAttribute v) {
		v.setValue((String) txtValue.getSelectedItem());
		if (concepts != null)
			v.setObject(concepts.get((String) txtValue.getSelectedItem()));
		if (semanticConcepts != null)
		{
			if (txtValue.getSelectedItem() != null)
			{
			String s  = ((String) txtValue.getSelectedItem()).trim();
			v.setObject(semanticConcepts.get(s));
			}
		}
	}

	@Override
	public JComponent getEditor() {
		return txtValue;
	}

}
