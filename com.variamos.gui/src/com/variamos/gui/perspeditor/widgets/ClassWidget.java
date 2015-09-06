package com.variamos.gui.perspeditor.widgets;

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
import com.variamos.perspsupport.instancesupport.InstAttribute;
import com.variamos.perspsupport.instancesupport.InstCell;
import com.variamos.perspsupport.instancesupport.InstConcept;
import com.variamos.perspsupport.instancesupport.InstElement;
import com.variamos.perspsupport.instancesupport.InstEnumeration;
import com.variamos.perspsupport.instancesupport.InstVertex;
import com.variamos.perspsupport.perspmodel.RefasModel;
import com.variamos.perspsupport.semanticinterface.IntSemanticElement;
import com.variamos.perspsupport.semanticinterface.IntSemanticPairwiseRelation;
import com.variamos.perspsupport.semanticinterface.IntSemanticRelationType;
import com.variamos.perspsupport.semanticsupport.AbstractSemanticElement;
import com.variamos.perspsupport.semanticsupport.SemanticConcept;
import com.variamos.perspsupport.semanticsupport.SemanticOverTwoRelation;
import com.variamos.perspsupport.syntaxsupport.EditableElementAttribute;
import com.variamos.perspsupport.syntaxsupport.MetaConcept;
import com.variamos.perspsupport.syntaxsupport.MetaElement;
import com.variamos.perspsupport.syntaxsupport.MetaPairwiseRelation;
import com.variamos.perspsupport.types.ClassSingleSelectionType;

/**
 * A class to support class widgets on the interface. Inspired on other widgets
 * from ProductLine. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Muñoz Fernández <jcmunoz@gmail.com>
 * 
 * @version 1.1
 * @since 2014-12-04
 * @see com.variamos.gui.pl.editor.widgets
 */
@SuppressWarnings("serial")
public class ClassWidget extends WidgetR {

	private JComboBox<String> txtValue;
	private Map<String, IntSemanticElement> semanticElements;
	private Map<String, MetaElement> syntaxElements;
	private Map<String, InstElement> instVertex;

	public ClassWidget() {
		super();

		setLayout(new BorderLayout());
		txtValue = new JComboBox<String>();
		add(txtValue, BorderLayout.CENTER);
		revalidate();
	}

	@Override
	public void configure(EditableElementAttribute v, mxGraph graph,
			RefasModel semanticModel, boolean showSimulationCustomizationBox) {
		super.configure(v, graph, semanticModel, showSimulationCustomizationBox);

		ClassLoader classLoader = ClassSingleSelectionType.class
				.getClassLoader();
		@SuppressWarnings("rawtypes")
		Class aClass = null;
		InstAttribute instAttribute = (InstAttribute) v;

		try {
			aClass = classLoader.loadClass(instAttribute.getAttribute()
					.getClassCanonicalName());
			// System.out.println("aClass.getName() = " + aClass.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (instAttribute.getPairwiseRelValidationList() != null) {
			semanticElements = new HashMap<String, IntSemanticElement>();
			List<IntSemanticPairwiseRelation> list = instAttribute
					.getPairwiseRelValidationList();

			for (IntSemanticPairwiseRelation groupDependency : list) {
				semanticElements.put(groupDependency.getIdentifier(),
						groupDependency);
				String out = groupDependency.getIdentifier();
				txtValue.addItem(out);
			}
		} else if (instAttribute.getValidationMEList() != null) {
			syntaxElements = new HashMap<String, MetaElement>();
			List<MetaPairwiseRelation> list = instAttribute
					.getValidationMEList();

			for (MetaPairwiseRelation groupDependency : list) {
				if (groupDependency != null) {
					syntaxElements.put(groupDependency.getAutoIdentifier(),
							(MetaPairwiseRelation) groupDependency);
					String out = groupDependency.getAutoIdentifier();
					txtValue.addItem(out);
				}
				// if (instAttribute.getValue()!= null &&
				// out.equals(instAttribute.getValue()))
				// txtValue.setSelectedItem(out);
			}
		} else if (instAttribute.getOverTwoRelValidationList() != null) {
			semanticElements = new HashMap<String, IntSemanticElement>();
			List<IntSemanticRelationType> list = instAttribute
					.getOverTwoRelValidationList();

			for (IntSemanticRelationType groupDependency : list) {
				semanticElements.put(groupDependency.getIdentifier(),
						(AbstractSemanticElement) groupDependency);
				String out = groupDependency.getIdentifier();
				txtValue.addItem(out);
				if (instAttribute.getValue() != null
						&& out.equals(instAttribute.getValue()))
					txtValue.setSelectedItem(out);
				if (instAttribute.getValue() == null
						&& instAttribute.getAttributeDefaultValue() != null
						&& out.equals(instAttribute.getAttributeDefaultValue()))
					txtValue.setSelectedItem(out);
			}
			// System.out.println("yr"+txtValue.getSelectedIndex());
			if (txtValue.getSelectedIndex() == -1
					&& txtValue.getItemCount() > 0) {
				txtValue.setSelectedItem(0);
			}

		} else {
			if (aClass.isInterface()
					|| aClass.getSuperclass().equals(
							AbstractSemanticElement.class)) {
				semanticElements = new HashMap<String, IntSemanticElement>();
				System.out.println("ClassWidget old semanticSyntax");
				/*
				 * Collection<IntSemanticElement> list = semanticSyntaxObject
				 * .getSemanticConcepts().values();
				 * 
				 * List<IntSemanticElement> list2 = new
				 * ArrayList<IntSemanticElement>();
				 * 
				 * for (IntSemanticElement concept : list) { if
				 * (aClass.isInstance(concept)) list2.add(concept); }
				 * 
				 * for (IntSemanticElement concept : list2) {
				 * semanticElements.put(concept.getIdentifier(), concept);
				 * String patternString = "([_])"; Pattern p =
				 * Pattern.compile(patternString);
				 * 
				 * String[] split = p.split(concept.getIdentifier()); String out
				 * = split[0] + " "; for (int j = 1; j < split.length; j++) out
				 * += split[j].toLowerCase() + " ";
				 * txtValue.addItem(out.trim()); }
				 */
			}

			if (aClass.equals(InstVertex.class)) {
				instVertex = new HashMap<String, InstElement>();
				List<InstVertex> list = getInstElements(instAttribute
						.getAttribute().getMetaConceptInstanceType(), graph);

				for (InstVertex concept : list) {
					instVertex.put(concept.getIdentifier(), concept);
					String patternString = "([_])";
					Pattern p = Pattern.compile(patternString);
					String[] split = p.split(concept.getInstAttribute("name")
							.toString());
					String out = split[0] + " ";
					for (int j = 1; j < split.length; j++)
						out += split[j].toLowerCase() + " ";
					txtValue.addItem(out.trim());
				}
			}
			if (aClass.equals(InstEnumeration.class)
					|| aClass.equals(InstConcept.class)) {
				if (instAttribute.getAttribute().getType().equals("Class")) {
					instVertex = new HashMap<String, InstElement>();
					List<InstVertex> list = getInstElements(instAttribute
							.getAttribute().getMetaConceptInstanceType(), graph);

					for (InstVertex concept : list) {
						instVertex.put(concept.getInstAttribute("identifier")
								.toString(), concept);
						String out = concept.getInstAttribute("name")
								.toString();
						txtValue.addItem(out);
						if (instAttribute.getValue() != null
								&& out.equals(instAttribute.getValue()))
							txtValue.setSelectedItem(out);
						if (instAttribute.getValue() == null
								&& instAttribute.getAttributeDefaultValue() != null
								&& out.equals(instAttribute
										.getAttributeDefaultValue()))
							txtValue.setSelectedItem(out);
					}
				}
			}
			if (aClass.equals(SemanticConcept.class)) {
				if (instAttribute.getAttribute().getType().equals("Class")) {
					instVertex = new HashMap<String, InstElement>();
					Collection<InstElement> list = semanticModel
							.getVariabilityVertexCollection();

					for (InstElement concept : list) {
						if (instAttribute.getAttribute()
								.getMetaConceptInstanceType()
								.equals(""+((MetaConcept)concept.getTransSupportMetaElement()).getType()))
						{
							instVertex.put(
									concept.getInstAttribute("identifier")
											.toString(), concept);
							String out = concept.getInstAttribute("identifier")
									.toString();
							System.out.println(out);
							txtValue.addItem(out);
							if (instAttribute.getValue() != null
									&& out.equals(instAttribute.getValue()))
								txtValue.setSelectedItem(out);
							if (instAttribute.getValue() == null
									&& instAttribute.getAttributeDefaultValue() != null
									&& out.equals(instAttribute
											.getAttributeDefaultValue()))
								txtValue.setSelectedItem(out);
						}
					}
				}
			}

		}
		if (instAttribute.getValue() == null && txtValue.getItemCount() > 0) {
			txtValue.setSelectedIndex(0);
			instAttribute.setValue((String) txtValue.getSelectedItem());
		}
		pushValue(v);
	}

	public List<InstVertex> getInstElements(String object, mxGraph graph) {
		List<InstVertex> out = new ArrayList<InstVertex>();
		mxIGraphModel refasGraph = graph.getModel();
		Object o = refasGraph.getRoot(); // Main Root
		mxCell o1 = (mxCell) refasGraph.getChildAt(o, 0); // Null Root
		for (int i = 0; i < o1.getChildCount(); i++) {
			mxCell mv = (mxCell) refasGraph.getChildAt(o1, i);
			for (int j = 0; j < mv.getChildCount(); j++) {
				mxCell concept = (mxCell) refasGraph.getChildAt(mv, j);
				for (int k = 0; k < concept.getChildCount(); k++) {
					mxCell concept2 = (mxCell) refasGraph
							.getChildAt(concept, k);
					InstElement value = ((InstCell) concept2.getValue())
							.getInstElement();
					if (value instanceof InstVertex) {
						InstVertex ic = (InstVertex) value;
						MetaElement mc = ic.getTransSupportMetaElement();
						if (mc.getAutoIdentifier().equals(object)
								&& !out.contains(ic))
							out.add(ic);
					}
				}
				InstElement value = ((InstCell) concept.getValue())
						.getInstElement();
				if (value instanceof InstVertex) {
					InstVertex ic = (InstVertex) value;
					MetaElement mc = ic.getTransSupportMetaElement();
					if (mc.getAutoIdentifier().equals(object))
						out.add(ic);
				}

			}
			if (mv.getValue() != null && mv.getValue() instanceof InstCell) {
				InstElement value = ((InstCell) mv.getValue()).getInstElement();
				if (value instanceof InstVertex) {
					InstVertex ic = (InstVertex) value;
					MetaElement mc = ic.getTransSupportMetaElement();
					if (mc.getAutoIdentifier().equals(object))
						out.add(ic);
				}
			}

		}
		return out;
	}

	@Override
	protected boolean pushValue(EditableElementAttribute v) {
		boolean out = false;

		InstAttribute instAttribute = (InstAttribute) v;
		if (instAttribute.getValueObject() != null) {
			if (instAttribute.getValueObject() instanceof SemanticOverTwoRelation)
				txtValue.setSelectedItem((String) ((SemanticOverTwoRelation) instAttribute
						.getValueObject()).getIdentifier());
			else if (instAttribute.getValueObject() instanceof MetaPairwiseRelation)
				txtValue.setSelectedItem((String) ((MetaPairwiseRelation) instAttribute
						.getValueObject()).getAutoIdentifier());
		}
		if (instVertex != null) {
			Object set = instVertex.get((String) txtValue.getSelectedItem());
			if ((instAttribute.getValueObject() == null && set != null)
					|| (instAttribute.getValueObject() != null && !instAttribute
							.getValueObject().equals(set))) {
				instAttribute.setValueObject(set);
				out = true;
			}
		}
		if (semanticElements != null) {
			if (txtValue.getSelectedItem() != null) {
				String s = ((String) txtValue.getSelectedItem()).trim();
				if (instAttribute.getValueObject() == null
						|| !instAttribute.getValueObject().equals(
								semanticElements.get(s))) {
					instAttribute.setValueObject(semanticElements.get(s));
					out = true;
				}
			}
		}
		if (syntaxElements != null) {
			if (txtValue.getSelectedItem() != null) {
				String s = ((String) txtValue.getSelectedItem()).trim();
				if (instAttribute.getValueObject() == null
						|| !instAttribute.getValueObject().equals(
								syntaxElements.get(s))) {
					instAttribute.setValueObject(syntaxElements.get(s));
					out = true;
				}
			}
		}

		group.setText((String) v.getGroup());
		revalidate();
		repaint();
		return out;
	}

	@Override
	protected void pullValue(EditableElementAttribute v) {

		InstAttribute instAttribute = (InstAttribute) v;
		v.setValue((String) txtValue.getSelectedItem());
		if (instVertex != null)
			instAttribute.setValueObject(instVertex.get((String) txtValue
					.getSelectedItem()));
		if (semanticElements != null) {
			if (txtValue.getSelectedItem() != null) {
				String s = ((String) txtValue.getSelectedItem()).trim();
				instAttribute.setValueObject(semanticElements.get(s));
			}
		}
		if (syntaxElements != null) {
			if (txtValue.getSelectedItem() != null) {
				String s = ((String) txtValue.getSelectedItem()).trim();
				instAttribute.setValueObject(syntaxElements.get(s));
			}
		}

		v.setGroup(group.getText());
	}

	@Override
	public JComponent getEditor() {
		return txtValue;
	}

	@Override
	public JComponent getGroup() {
		return group;
	}
}
