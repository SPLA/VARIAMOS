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
import com.variamos.refas.core.sematicsmetamodel.AbstractSemanticElement;
import com.variamos.refas.core.sematicsmetamodel.AbstractSemanticVertex;
import com.variamos.refas.core.sematicsmetamodel.SemanticGroupDependency;
import com.variamos.refas.core.staticconcepts.SemanticPlusSyntax;
import com.variamos.syntaxsupport.metametamodel.MetaEdge;
import com.variamos.syntaxsupport.metametamodel.MetaElement;
import com.variamos.syntaxsupport.metametamodel.MetaVertex;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.semanticinterface.IntDirectSemanticEdge;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticGroupDependency;
import com.variamos.syntaxsupport.type.ClassType;

@SuppressWarnings("serial")
public class ClassWidget extends WidgetR {

	private JComboBox<String> txtValue;
	private Map<String, IntSemanticElement> semanticConcepts;
	private Map<String, MetaElement> syntaxElements;
	private Map<String, InstConcept> concepts;

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
		if (v.getValidationDRList() != null) {
			semanticConcepts = new HashMap<String, IntSemanticElement>();
			List<IntDirectSemanticEdge> list = v.getValidationDRList();

			for (IntDirectSemanticEdge groupDependency : list) {
				semanticConcepts.put(groupDependency.getIdentifier(), groupDependency);
				String out = groupDependency.getIdentifier();
				txtValue.addItem(out);
			}
		} else 
			if (v.getValidationMEList() != null) {
				syntaxElements = new HashMap<String, MetaElement>();
				List<MetaEdge> list = v.getValidationMEList();

				for (MetaEdge groupDependency : list) {
					syntaxElements.put(groupDependency.getIdentifier(),
							(MetaEdge) groupDependency);
					String out = groupDependency.getIdentifier();
					txtValue.addItem(out);
				}
			} else
		if (v.getValidationGDList() != null) {
			semanticConcepts = new HashMap<String, IntSemanticElement>();
			List<IntSemanticGroupDependency> list = v.getValidationGDList();

			for (IntSemanticGroupDependency groupDependency : list) {
				semanticConcepts.put(groupDependency.getIdentifier(),
						(AbstractSemanticVertex) groupDependency);
				String out = groupDependency.getIdentifier();
				txtValue.addItem(out);
			}
		} else {
			if (aClass.getSuperclass().equals(AbstractSemanticElement.class)) {
				semanticConcepts = new HashMap<String, IntSemanticElement>();
				Collection<IntSemanticElement> list = semanticSyntaxObject
						.getSemanticConcepts().values();

				List<IntSemanticElement> list2 = new ArrayList<IntSemanticElement>();

				for (IntSemanticElement concept : list) {
					if (aClass.isInstance(concept))
						list2.add(concept);
				}

				for (IntSemanticElement concept : list2) {
					semanticConcepts.put(concept.getIdentifier(), concept);
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
				concepts = new HashMap<String, InstConcept>();
				List<InstConcept> list = getInstConcepts(v.getAttribute()
						.getObject(), graph);

				for (InstConcept concept : list) {
					concepts.put(concept.getIdentifier(), concept);
					String patternString = "([_])";
					Pattern p = Pattern.compile(patternString);

					String[] split = p.split(concept.getInstAttribute("name")
							.toString());
					String out = split[0] + " ";
					for (int j = 1; j < split.length; j++)
						out += split[j].toLowerCase() + " ";
					txtValue.addItem(out);
				}
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
					MetaVertex mc = ic.getMetaConcept();
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
		{
			if (v.getObject() instanceof SemanticGroupDependency)
			txtValue.setSelectedItem((String) ((SemanticGroupDependency) v
					.getObject()).getIdentifier());
			else
				if (v.getObject() instanceof MetaEdge)
					txtValue.setSelectedItem((String) ((MetaEdge) v
							.getObject()).getIdentifier());
		}
		if (concepts != null)
			v.setObject(concepts.get((String) txtValue.getSelectedItem()));
		if (semanticConcepts != null) {
			if (txtValue.getSelectedItem() != null) {
				String s = ((String) txtValue.getSelectedItem()).trim();
				v.setObject(semanticConcepts.get(s));
			}
		}
		if (syntaxElements != null) {
			if (txtValue.getSelectedItem() != null) {
				String s = ((String) txtValue.getSelectedItem()).trim();
				v.setObject(syntaxElements.get(s));
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
		if (semanticConcepts != null) {
			if (txtValue.getSelectedItem() != null) {
				String s = ((String) txtValue.getSelectedItem()).trim();
				v.setObject(semanticConcepts.get(s));
			}
		}
		if (syntaxElements != null) {
			if (txtValue.getSelectedItem() != null) {
				String s = ((String) txtValue.getSelectedItem()).trim();
				v.setObject(syntaxElements.get(s));
			}
		}
	}

	@Override
	public JComponent getEditor() {
		return txtValue;
	}

}
