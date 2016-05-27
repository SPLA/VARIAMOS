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
import com.variamos.dynsup.instance.InstAttribute;
import com.variamos.dynsup.instance.InstCell;
import com.variamos.dynsup.instance.InstConcept;
import com.variamos.dynsup.instance.InstElement;
import com.variamos.dynsup.instance.InstVertex;
import com.variamos.dynsup.interfaces.IntInstAttribute;
import com.variamos.dynsup.model.ModelInstance;
import com.variamos.dynsup.model.OpersConcept;
import com.variamos.dynsup.model.OpersElement;
import com.variamos.dynsup.model.OpersOverTwoRel;
import com.variamos.dynsup.model.OpersPairwiseRel;
import com.variamos.dynsup.model.SyntaxElement;
import com.variamos.dynsup.model.SyntaxPairwiseRel;
import com.variamos.dynsup.types.ClassSingleSelectionType;
import com.variamos.io.ConsoleTextArea;

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
	private Map<String, OpersElement> semanticElements;
	private Map<String, InstAttribute> opersElements;
	private Map<String, SyntaxElement> syntaxElements;
	private Map<String, InstElement> instVertex;

	public ClassWidget() {
		super();

		setLayout(new BorderLayout());
		txtValue = new JComboBox<String>();
		add(txtValue, BorderLayout.CENTER);
		revalidate();
	}

	@Override
	public void configure(IntInstAttribute v, mxGraph graph,
			ModelInstance semanticModel, boolean showSimulationCustomizationBox) {
		super.configure(v, graph, semanticModel, showSimulationCustomizationBox);

		ClassLoader classLoader = ClassSingleSelectionType.class
				.getClassLoader();
		@SuppressWarnings("rawtypes")
		Class aClass = null;
		InstAttribute instAttribute = (InstAttribute) v;

		try {
			aClass = classLoader.loadClass(instAttribute.getAttribute()
					.getClassCanonicalName());
		} catch (ClassNotFoundException e) {
			ConsoleTextArea.addText(instAttribute.getAttribute()
					.getClassCanonicalName());
			ConsoleTextArea.addText(e.getStackTrace());
		}
		if (instAttribute.getPairwiseRelValidationList() != null) {
			semanticElements = new HashMap<String, OpersElement>();
			List<OpersPairwiseRel> list = instAttribute
					.getPairwiseRelValidationList();

			for (OpersPairwiseRel groupDependency : list) {
				semanticElements.put(groupDependency.getIdentifier(),
						groupDependency);
				String out = groupDependency.getIdentifier();
				txtValue.addItem(out);
			}
		} else if (instAttribute.getValidationMEList() != null) {
			syntaxElements = new HashMap<String, SyntaxElement>();
			List<InstElement> list = instAttribute.getValidationMEList();

			for (InstElement groupDependency : list) {
				if (groupDependency != null) {
					syntaxElements.put(groupDependency.getEdSyntaxEle()
							.getAutoIdentifier(),
							((InstElement) groupDependency).getEdSyntaxEle());
					String out = groupDependency.getEdSyntaxEle()
							.getAutoIdentifier();
					txtValue.addItem(out);
				}
				// if (instAttribute.getValue()!= null &&
				// out.equals(instAttribute.getValue()))
				// txtValue.setSelectedItem(out);
			}
			// } else if (instAttribute.getOverTwoRelValidationList() != null) {
			// semanticElements = new HashMap<String, OpersElement>();
			// List<OpersRelType> list = instAttribute
			// .getOverTwoRelValidationList();
			//
			// for (OpersRelType groupDependency : list) {
			// semanticElements.put(groupDependency.getIdentifier(),
			// (OpersElement) groupDependency);
			// String out = groupDependency.getIdentifier();
			// txtValue.addItem(out);
			// if (instAttribute.getValue() != null
			// && out.equals(instAttribute.getValue()))
			// txtValue.setSelectedItem(out);
			// if (instAttribute.getValue() == null
			// && instAttribute.getAttributeDefaultValue() != null
			// && out.equals(instAttribute.getAttributeDefaultValue()))
			// txtValue.setSelectedItem(out);
			// }
			// // System.out.println("yr"+txtValue.getSelectedIndex());
			// if (txtValue.getSelectedIndex() == -1
			// && txtValue.getItemCount() > 0) {
			// txtValue.setSelectedItem(0);
			// }
			//
			//
		} else if (instAttribute.getOpersOverTwoRelList() != null) {
			opersElements = new HashMap<String, InstAttribute>();
			List<InstAttribute> list = instAttribute.getOpersOverTwoRelList();

			for (InstAttribute groupDependency : list) {
				opersElements.put(groupDependency.getIdentifier(),
						groupDependency);
				String out = groupDependency.getDisplayName();
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
			if (aClass != null
					&& (aClass.isInterface() || aClass.getSuperclass().equals(
							OpersElement.class))) {
				semanticElements = new HashMap<String, OpersElement>();
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

			if (aClass != null && aClass.equals(InstVertex.class)) {
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
			if (aClass != null && aClass.equals(InstConcept.class)) {
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
			if (aClass != null && aClass.equals(OpersConcept.class)) {
				if (instAttribute.getAttribute().getType().equals("Class")) {
					instVertex = new HashMap<String, InstElement>();
					Collection<InstElement> list = semanticModel
							.getVariabilityVertexCollection();

					for (InstElement concept : list) {
						if (instAttribute
								.getAttribute()
								.getMetaConceptInstanceType()
								.equals(""
										+ ((SyntaxElement) concept
												.getTransSupportMetaElement())
												.getType())) {
							instVertex.put(
									concept.getInstAttribute("identifier")
											.toString(), concept);
							String out = concept.getInstAttribute("identifier")
									.toString();
							// System.out.println(out);
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
						SyntaxElement mc = ic.getTransSupportMetaElement();
						if (mc.getAutoIdentifier().equals(object)
								&& !out.contains(ic))
							out.add(ic);
					}
				}
				InstElement value = ((InstCell) concept.getValue())
						.getInstElement();
				if (value instanceof InstVertex) {
					InstVertex ic = (InstVertex) value;
					SyntaxElement mc = ic.getTransSupportMetaElement();
					if (mc.getAutoIdentifier().equals(object))
						out.add(ic);
				}

			}
			if (mv.getValue() != null && mv.getValue() instanceof InstCell) {
				InstElement value = ((InstCell) mv.getValue()).getInstElement();
				if (value instanceof InstVertex) {
					InstVertex ic = (InstVertex) value;
					SyntaxElement mc = ic.getTransSupportMetaElement();
					if (mc.getAutoIdentifier().equals(object))
						out.add(ic);
				}
			}

		}
		return out;
	}

	@Override
	protected boolean pushValue(IntInstAttribute v) {
		boolean out = false;

		InstAttribute instAttribute = (InstAttribute) v;
		if (instAttribute.getValueObject() != null) {
			if (instAttribute.getValueObject() instanceof OpersOverTwoRel)
				txtValue.setSelectedItem((String) ((OpersOverTwoRel) instAttribute
						.getValueObject()).getIdentifier());
			else if (instAttribute.getValueObject() instanceof SyntaxPairwiseRel)
				txtValue.setSelectedItem((String) ((SyntaxPairwiseRel) instAttribute
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
						|| semanticElements.get(s) != null
						&& (!instAttribute.getValueObject().equals(
								semanticElements.get(s)))) {
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
	protected void pullValue(IntInstAttribute v) {

		InstAttribute instAttribute = (InstAttribute) v;
		v.setValue((String) txtValue.getSelectedItem());
		if (instVertex != null)
			instAttribute.setValueObject(instVertex.get((String) txtValue
					.getSelectedItem()));
		if (semanticElements != null && txtValue.getItemCount() > 0) {
			if (txtValue.getSelectedItem() != null) {
				String s = ((String) txtValue.getSelectedItem()).trim();
				instAttribute.setValueObject(semanticElements.get(s));
			} else {
				String s = ((String) txtValue.getItemAt(0)).trim();
				instAttribute.setValueObject(semanticElements.get(s));
			}
		}
		if (syntaxElements != null && txtValue.getItemCount() > 0) {
			if (txtValue.getSelectedItem() != null) {
				String s = ((String) txtValue.getSelectedItem()).trim();
				instAttribute.setValueObject(syntaxElements.get(s));
			} else {
				String s = ((String) txtValue.getItemAt(0)).trim();
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
