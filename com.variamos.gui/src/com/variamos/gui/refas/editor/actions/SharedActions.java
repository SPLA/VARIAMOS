package com.variamos.gui.refas.editor.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.CellEditor;
import javax.swing.JOptionPane;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.view.mxGraph;
import com.variamos.gui.maineditor.VariamosGraphEditor;
import com.variamos.gui.refas.editor.RefasGraph;
import com.variamos.refas.core.refas.Refas;
import com.variamos.refas.core.sematicsmetamodel.AbstractSemanticElement;
import com.variamos.refas.core.sematicsmetamodel.AbstractSemanticVertex;
import com.variamos.refas.core.sematicsmetamodel.SemanticPairwiseRelation;
import com.variamos.refas.core.sematicsmetamodel.SemanticOverTwoRelation;
import com.variamos.refas.core.sematicsmetamodel.SemanticVariable;
import com.variamos.syntaxsupport.metamodel.InstAttribute;
import com.variamos.syntaxsupport.metamodel.InstConcept;
import com.variamos.syntaxsupport.metamodel.InstPairwiseRelation;
import com.variamos.syntaxsupport.metamodel.InstOverTwoRelation;
import com.variamos.syntaxsupport.metamodel.InstVertex;
import com.variamos.syntaxsupport.metamodelsupport.AbstractAttribute;
import com.variamos.syntaxsupport.metamodelsupport.MetaConcept;
import com.variamos.syntaxsupport.metamodelsupport.MetaPairwiseRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaElement;
import com.variamos.syntaxsupport.metamodelsupport.MetaOverTwoRelation;
import com.variamos.syntaxsupport.metamodelsupport.MetaVertex;
import com.variamos.syntaxsupport.metamodelsupport.ModelingAttribute;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticElement;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticOverTwoRelation;
import com.variamos.syntaxsupport.semanticinterface.IntSemanticRelationType;

public class SharedActions {

	private static Set<String> instAttributesToDelete = new HashSet<String>();
	private static boolean additionAttributes = false;

	public static mxGraph cloneGraph(mxGraph source, mxGraph target) {
		if (target == null)
			target = new mxGraph();
		target.selectAll();
		target.removeCells();
		target.addCells(source.cloneCells(source.getChildCells(source
				.getDefaultParent())));
		return target;
	}

	public static mxGraph beforeGraphOperation(mxGraph graph, boolean beforeSave) {
		((RefasGraph) graph).setValidation(false);

		long startTime = System.currentTimeMillis();
		((RefasGraph) graph).setValidation(false);
		mxGraph outGraph = cloneGraph(graph, null);
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("clone clean: " + elapsedTime);
		startTime = System.currentTimeMillis();

		mxIGraphModel refasGraph = outGraph.getModel();
		if (graph instanceof RefasGraph) {

			Object o = refasGraph.getRoot(); // Main Root
			mxCell o1 = (mxCell) refasGraph.getChildAt(o, 0); // Null Root
			for (int i = 0; i < o1.getChildCount(); i++) {
				mxCell mv = (mxCell) refasGraph.getChildAt(o1, i);
				for (int j = 0; j < mv.getChildCount(); j++) {
					mxCell concept = (mxCell) refasGraph.getChildAt(mv, j);
					Object value = concept.getValue();
					for (int k = 0; k < concept.getChildCount(); k++) {
						mxCell concept2 = (mxCell) refasGraph.getChildAt(
								concept, k);
						Object value2 = concept2.getValue();
						updateIdAndObjects(value2, beforeSave);

					}
					updateIdAndObjects(value, beforeSave);
				}
			}
		}
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println("object clean: " + elapsedTime);
		return outGraph;
	}

	private static void updateIdAndObjects(Object value, boolean beforeSave) {
		if (value instanceof InstOverTwoRelation) {
			InstOverTwoRelation ic = (InstOverTwoRelation) value;
			String str = null;// (String) ic.getSemanticOverTwoRelationIden();
			ic.setSemanticOverTwoRelationIden(str);
			str = (String) ic.getSupportMetaElementIdentifier();
			ic.setMetaOverTwoRelationIden(str);
			if (beforeSave) {
				ic.clearEditableMetaVertex();
				ic.clearInstAttributesObjects();
			}
		} else if (value instanceof InstVertex && beforeSave) {
			InstVertex instVertex = (InstVertex) value;
			instVertex.clearEditableMetaVertex();
			instVertex.clearInstAttributesObjects();
		}

		if (value instanceof InstPairwiseRelation) {
			InstPairwiseRelation ic = (InstPairwiseRelation) value;
			ic.updateIdentifiers();
			if (beforeSave) {
				ic.clearMetaPairwiseRelation();
				ic.clearEditableMetaVertex();
				// ic.clearRelations();
				ic.clearInstAttributesObjects();
			}

		}

	}

	public static void beforeLoadGraph(mxGraph graph, VariamosGraphEditor editor) {
		if (graph instanceof RefasGraph) {
			((RefasGraph) graph).setValidation(false);
		}
	}

	public static void afterSaveGraph(mxGraph graph, VariamosGraphEditor editor) {
		if (graph instanceof RefasGraph) {
			((RefasGraph) graph).setValidation(true);
		}
	}

	/**
	 * Load metamodel/semantic objects after loading or cloning the graph
	 * 
	 * @param graph
	 * @param editor
	 * @return
	 */
	public static mxGraph afterOpenCloneGraph(mxGraph graph,
			VariamosGraphEditor editor) {
		mxIGraphModel refasGraph = graph.getModel();
		instAttributesToDelete = new HashSet<String>();
		additionAttributes = false;
		if (graph instanceof RefasGraph) {
			Object o = refasGraph.getRoot(); // Main Root
			Object o1 = refasGraph.getChildAt(o, 0); // Null Root
			for (int mvInd = 0; mvInd < refasGraph.getChildCount(o1); mvInd++) {
				// Root model view mvInd
				mxCell mv0 = (mxCell) refasGraph.getChildAt(o1, mvInd);
				// First vertices and after edges
				Object[] vertexCells = graph.getChildCells(mv0, true, false);
				Object[] edgeCells = graph.getChildCells(mv0, false, true);
				Object[] allCells = new Object[vertexCells.length
						+ edgeCells.length];

				System.arraycopy(vertexCells, 0, allCells, 0,
						vertexCells.length);
				System.arraycopy(edgeCells, 0, allCells, vertexCells.length,
						edgeCells.length);
				for (Object anyCell : allCells) {
					// for (int i = 0; i < refasGraph.getChildCount(mv0); i++) {
					// mxCell mv1 = (mxCell) refasGraph.getChildAt(mv0, i);
					mxCell mv1 = (mxCell) anyCell;
					if (refasGraph.getChildCount(mv0) > 0
							&& mv0.getChildAt(0).getValue()
									.equals(mv0.getValue())) {
						for (int j = 0; j < refasGraph.getChildCount(mv1); j++) {
							mxCell mv2 = (mxCell) refasGraph.getChildAt(mv1, j);
							try {
								loadSupportObjects(editor, mv2.getValue(), mv2,
										graph);
							} catch (Exception e) {
								e.printStackTrace();
								System.err.println(mv2.getValue().toString());
							}
						}
					} else
						try {
							loadSupportObjects(editor, mv1.getValue(), mv1,
									graph);
						} catch (Exception e) {
							e.printStackTrace();
							System.err.println(mv1.getValue().toString());
						}
				}
			}
			((RefasGraph) graph).setValidation(true);
		}
		if (instAttributesToDelete.size() > 0)
			JOptionPane
					.showMessageDialog(
							editor.getFrame(),
							"The model loaded contains a set concept attributes "
									+ instAttributesToDelete.toString()
									+ "\n that are not supported by the current version of VariaMos."
									+ "\n If you save this model, all the non supported attributes will be permanently lost.",
							"Incompatible Model Message",
							JOptionPane.INFORMATION_MESSAGE, null);

		if (additionAttributes)
			JOptionPane
					.showMessageDialog(
							editor.getFrame(),
							"New concept attributes to make the model compatible with the current \n"
									+ "version of VariaMos were added. Saving this file will make it incompatible"
									+ "\n with older versions of the tool.",
							"Incompatible Model Message",
							JOptionPane.INFORMATION_MESSAGE, null);
		return graph;
	}

	private static void loadSupportObjects(VariamosGraphEditor editor,
			Object value, mxCell source, mxGraph graph) {
		Refas refas = ((RefasGraph) editor.getGraphComponent().getGraph())
				.getRefas();
		if (value instanceof InstOverTwoRelation) {
			InstOverTwoRelation instOverTwoRelation = (InstOverTwoRelation) value;
			MetaOverTwoRelation metaOverTwoRelation = (MetaOverTwoRelation) refas
					.getSyntaxRefas()
					.getVertex(
							instOverTwoRelation
									.getSupportMetaElementIdentifier())
					.getEditableMetaElement();
			instOverTwoRelation.setTransSupportMetaElement(metaOverTwoRelation);
			refas.putInstGroupDependency(instOverTwoRelation);
			Iterator<InstAttribute> ias = instOverTwoRelation
					.getInstAttributes().values().iterator();
			System.out.println(instOverTwoRelation.getInstAttributes().size());
			while (ias.hasNext()) {
				InstAttribute ia = (InstAttribute) ias.next();
				AbstractAttribute attribute = instOverTwoRelation
						.getAbstractAttribute(ia.getAttributeName());
				if (attribute != null) {
					ia.setAttribute(attribute);

					List<IntSemanticRelationType> semGD = ((MetaOverTwoRelation) instOverTwoRelation
							.getTransSupportMetaElement())
							.getSemanticRelationTypes();
					ia.setValidationRelationTypes(semGD);
					if (ia.getAttributeType().equals("Boolean")
							&& ia.getValue() instanceof String)
						if (((String) ia.getValue()).equals("0"))
							ia.setValue(false);
						else
							ia.setValue(true);
				} else {
					instAttributesToDelete.add(ia.getAttributeName());
					ias.remove();
				}
			}
			if (instOverTwoRelation.getInstAttributes().size() < instOverTwoRelation
					.getTransSupportMetaElement().getModelingAttributes()
					.size()
					+ instOverTwoRelation.getTransSupportMetaElement()
							.getSemanticAttributes().size()) {
				for (String attributeName : instOverTwoRelation
						.getTransSupportMetaElement().getSemanticAttributes()) {
					if (instOverTwoRelation.getInstAttribute(attributeName) == null
							&& instOverTwoRelation.getTransSupportMetaElement()
									.getSemanticAttribute(attributeName)!= null) {
						instOverTwoRelation.addInstAttribute(attributeName,
								instOverTwoRelation
										.getTransSupportMetaElement()
										.getSemanticAttribute(attributeName),
								null);
						System.out.println("create" + attributeName);
						additionAttributes = true;
					} else if (instOverTwoRelation.getInstAttribute(attributeName) == null) {
						instOverTwoRelation.addInstAttribute(attributeName, instOverTwoRelation
								.getTransSupportMetaElement()
								.getModelingAttribute(attributeName), null);
						System.out.println("create" + attributeName);
						additionAttributes = true;
					}
				}
			}
			editor.refreshElement(instOverTwoRelation);
		} else if (value instanceof InstVertex) {
			InstVertex instVertex = (InstVertex) value;
			MetaVertex metaVertex = (MetaVertex) refas.getSyntaxRefas()
					.getVertex(instVertex.getSupportMetaElementIdentifier())
					.getEditableMetaElement();
			if (metaVertex == null)
				System.err.println("Concept Null"
						+ instVertex.getSupportMetaElementIdentifier());
			else
				instVertex.setTransSupportMetaElement(metaVertex);
			refas.putVariabilityInstVertex(instVertex);
			Iterator<InstAttribute> ias = instVertex.getInstAttributes()
					.values().iterator();
			while (ias.hasNext()) {
				InstAttribute ia = (InstAttribute) ias.next();
				AbstractAttribute attribute = metaVertex
						.getAbstractAttribute(ia.getAttributeName());
				if (attribute != null) {
					ia.setAttribute(attribute);
					if (ia.getAttributeType().equals("Boolean")
							&& ia.getValue() instanceof String)
						if (((String) ia.getValue()).equals("0"))
							ia.setValue(false);
						else
							ia.setValue(true);
				} else {
					instAttributesToDelete.add(ia.getAttributeName());
					ias.remove();
				}
			}
			if (instVertex.getInstAttributes().size() < instVertex
					.getTransSupportMetaElement().getSemanticAttributes()
					.size()
					+ instVertex.getTransSupportMetaElement()
							.getModelingAttributes().size()) {
				for (String attributeName : instVertex
						.getTransSupportMetaElement().getSemanticAttributes()) {
					if (instVertex.getInstAttribute(attributeName) == null
							&& instVertex.getTransSupportMetaElement()
									.getSemanticAttribute(attributeName) != null) {
						instVertex.addInstAttribute(attributeName, instVertex
								.getTransSupportMetaElement()
								.getSemanticAttribute(attributeName), null);
						System.out.println("create" + attributeName);
						additionAttributes = true;
					} else if (instVertex.getInstAttribute(attributeName) == null) {
						instVertex.addInstAttribute(attributeName, instVertex
								.getTransSupportMetaElement()
								.getModelingAttribute(attributeName), null);
						System.out.println("create" + attributeName);
						additionAttributes = true;
					}
				}
			}
		}
		if (value instanceof InstPairwiseRelation) {
			try {
				InstPairwiseRelation instPairwiseRelation = (InstPairwiseRelation) value;
				instPairwiseRelation
						.createAttributes(new HashMap<String, InstAttribute>());
				InstVertex sourceVertex = (InstVertex) source.getSource()
						.getValue();
				InstVertex targetVertex = (InstVertex) source.getTarget()
						.getValue();
				MetaPairwiseRelation metaPairwiseRelation = refas
						.getSyntaxRefas().getValidMetaPairwiseRelation(
								sourceVertex.getTransSupportMetaElement(),
								targetVertex.getTransSupportMetaElement(),
								instPairwiseRelation
										.getSupportMetaPairwiseRelIden(), true);
				instPairwiseRelation.setSourceRelation(sourceVertex, true);
				instPairwiseRelation.setTargetRelation(targetVertex, true);
				if (metaPairwiseRelation != null) {
					instPairwiseRelation
							.setSupportMetaPairwiseRelation(metaPairwiseRelation);
					instPairwiseRelation.setUpdatePairwiseRelationType();
					instPairwiseRelation.setDynamicVariable("relationType",
							instPairwiseRelation.getSemanticPairwiseRelType());

					Iterator<InstAttribute> instAttributesIter = instPairwiseRelation
							.getInstAttributes().values().iterator();
					while (instAttributesIter.hasNext()) {
						try {

							InstAttribute instAttribute = (InstAttribute) instAttributesIter
									.next();

							AbstractAttribute absAttribute = metaPairwiseRelation
									.getAbstractAttribute(instAttribute
											.getAttributeName());
							if (absAttribute == null)
								absAttribute = instPairwiseRelation
										.getSemanticAttribute();
							if (absAttribute != null) {
								instAttribute.setAttribute(absAttribute);
								// if (absAttribute != null)// TODO find a
								// better
								// fix
								if (instAttribute.getAttributeType().equals(
										"Boolean")
										&& instAttribute.getValue() != null
										&& instAttribute.getValue() instanceof String)
									if (((String) instAttribute.getValue())
											.equals("0"))
										instAttribute.setValue(false);
									else
										instAttribute.setValue(true);
								if (instAttribute
										.getIdentifier()
										.equals(SemanticPairwiseRelation.VAR_RELATIONTYPE_IDEN))
									instAttribute.setValue(instPairwiseRelation
											.getSemanticPairwiseRelType());
								List<IntSemanticRelationType> semGD = ((MetaPairwiseRelation) instPairwiseRelation
										.getTransSupportMetaElement())
										.getSemanticRelationTypes();
								instAttribute.setValidationRelationTypes(semGD);
							} else {
								instAttributesToDelete.add(instAttribute
										.getAttributeName());
								instAttributesIter.remove();
							}

						} catch (Exception e) {
							System.err.println("Contained exception");
							e.printStackTrace();
						}
					}
					if (instPairwiseRelation.getInstAttributes().size() < instPairwiseRelation
							.getTransSupportMetaElement()
							.getSemanticAttributes().size()
							+ instPairwiseRelation.getTransSupportMetaElement()
									.getModelingAttributes().size()) {
						for (String attributeName : instPairwiseRelation
								.getTransSupportMetaElement()
								.getSemanticAttributes()) {
							if (instPairwiseRelation
									.getInstAttribute(attributeName) == null
									&& instPairwiseRelation
											.getTransSupportMetaElement()
											.getSemanticAttribute(attributeName) != null) {
								instPairwiseRelation.addInstAttribute(
										attributeName,
										instPairwiseRelation
												.getTransSupportMetaElement()
												.getSemanticAttribute(
														attributeName), null);
								System.out.println("create" + attributeName);
								additionAttributes = true;
							} else if (instPairwiseRelation
									.getInstAttribute(attributeName) == null) {
								instPairwiseRelation.addInstAttribute(
										attributeName,
										instPairwiseRelation
												.getTransSupportMetaElement()
												.getModelingAttribute(
														attributeName), null);
								System.out.println("create" + attributeName);
								additionAttributes = true;
							}
						}

					}
				}
				// TODO add edges to groupDependecies and claims to
				// otherInstEdges
				refas.putConstraintInstEdge(instPairwiseRelation);

				editor.refreshElement(instPairwiseRelation);
			} catch (Exception e) {
				System.err.println("Contained exception");
				e.printStackTrace();
			}
		}

	}
}
